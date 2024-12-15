# LRU Cache

## Leet code Link - https://leetcode.com/problems/lru-cache/description/

## Complexity - Medium

## Description
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

#### Implement the LRUCache class:
```plaintext
LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
```
 

#### Example 1:
```plaintext
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 ```

#### Constraints:
```plaintext
1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
```
---
## Solution
We need a way to store data in an ordered manner such that elements can be removed from any position in constant time.

A linked list is a great candidate for this task. Removing from arbitrary positions is one of the few things that a linked list does better than an array.

Let's say you have a linked list A -> B -> C -> D -> E. We can remove the C from the list by performing B.next = D. As C is no longer reachable, it is effectively "removed" from the list. If you were to traverse from the head (A), you would visit nodes A, B, D, E. This operation is done in constant time, no matter how large the list is.

To remove C from the list, we needed a reference to the node before it B, so that we could change B.next. In general, if we want to remove a node from the list, we need a pointer to the node before it. Because of this, we shall use a doubly linked list. That way, when we want to remove a node, we have a prev pointer to reference the node before it.

As each node represents an element in the data structure, we can also store the key-value pair in each node.

Let's think about how we can implement the data structure again. We need to achieve the following functionality:

- Store a key-value pair
- Update a key-value pair
- Given a key, determine if it exists in the data structure. If it does, return the value. If it doesn't, return -1.
- When a new key-value pair is added, create a new linked list node and put it at the back.
- When an existing key is updated or fetched, find its associated linked list node. Move it to the back.
- When a new key-value pair is added and the size of the data structure exceeds capacity, remove the linked list node at the front.
- Tasks 4 - 6 follow the process that we determined in the overview.

Tasks 1, 2, and 3 can all easily be achieved using a standard built-in hash map. How do we accomplish tasks 4, 5, and 6?

In tasks 4 and 5, we need to add nodes to the back of the linked list. Because we are aiming for an O(1) time complexity, we must keep a reference to the tail of our linked list. In task 6, we need to remove from the front of the linked list. This means we must also keep a reference to the head (although we would probably do this anyways since you always want to keep the head of a linked list).

We can easily detect when the size of the data structure exceeds capacity by checking the size of our hash map.
n our hash map, instead of mapping each key to its value (int: int), let's have it map each key to its associated node (int: ListNode).

Now, in task 5, when we update or fetch a key, we can reference the hash map to find the key's node in O(1). Once we have the node, we can remove it from the list in O(1). Finally, we can move it to the back by referencing the linked list's tail.

So if we are storing ListNode in the hash map instead of the values, how do we implement the get method? Remember that our ListNode objects also have key and val attributes. Therefore, to get a value associated with key, we can first use the hash map to get the key's node in O(1), and then just check node.val.

For our LRUCache class, we need the following attributes:

- capacity - to detect when we need to start deleting key-value pairs.
- dic - short for dictionary, this will be our hash map that maps keys to nodes.
- head - the head of our linked list
- tail - the tail of our linked list
- Before we start implementation, let's talk about an edge case.

We know that we are going to need to remove from the front of the linked list and add to the end of the linked list frequently. We plan on doing this by using the head and tail attributes. What happens if the linked list is empty? This is a frustrating case - we will need to check for it every time and handle it completely differently.

Imagine that the linked list is empty and we call put to create a new key-value pair. We create a node for this key-value pair, then we need to set it as both the head and tail (since it's the only node).

What if capacity = 1 and we call put again with a new key? You can imagine the headache that might ensue - we need to delete the only existing node, which means we are deleting both the head and tail. Then we need to add the new node, but since the linked list is empty again, we will be setting the new node as the head and tail again.

The cleanest way to handle the empty list case is by using sentinel nodes.

We will have our head and tail attributes both set to dummy nodes. The "real" head will be head.next and the "real" tail will be tail.prev. These dummy nodes sit just "outside" of our linked list. What is the purpose? We never want head or tail to be null.

```java
class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!dic.containsKey(key)) {
            return -1;
        }

        ListNode node = dic.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (dic.containsKey(key)) {
            ListNode oldNode = dic.get(key);
            remove(oldNode);
        }

        ListNode node = new ListNode(key, value);
        dic.put(key, node);
        add(node);

        if (dic.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            dic.remove(nodeToDelete.key);
        }
    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```
#### Complexity Analysis

**Time complexity:** O(1) for both get and put.

##### For get:

Check if a key is in a hash map. This costs O(1).
Get a node associated with a key. This costs O(1).
Call remove and add. Both methods cost O(1).
##### For put:

Check if a key is in a hash map. This costs O(1).
If it is, we get a node associated with a key and call remove. Both cost O(1).
Create a new node and insert it into the hash map. This costs O(1).
Call add. This method costs O(1).
If the capacity is exceeded, we call remove and delete from the hash map. Both cost O(1).
**Space complexity:** O(capacity)

We use extra space for the hash map and for our linked list. Both cannot exceed a size of capacity.
