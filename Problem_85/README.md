# Palindrome Linked List

## Leet code Link - https://leetcode.com/problems/palindrome-linked-list/description/

## Complexity - Easy

## Description
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.


#### Example 1:
<img src="https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg" />

```
Input: head = [1,2,2,1]
Output: true
```
#### Example 2:
<img src="https://assets.leetcode.com/uploads/2021/03/03/pal2linked-list.jpg" />

```
Input: head = [1,2]
Output: false
 ```

#### Constraints:
```
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
``` 

Follow up: Could you do it in O(n) time and O(1) space?

---
## Solution
In Linked list, traversing and comparing with last element cannot be done directly. Some kind of traversing is required. 

We can use ArrayList to store the values and then compare the first to last and determine if it is palindrome. But this has to sacrifice O(N) space complexity

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // Convert LinkedList into ArrayList.
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // Use two-pointer technique to check for palindrome.
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            // Note that we must use ! .equals instead of !=
            // because we are comparing Integer, not int.
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
```
#### Complexity
Time complexity : O(n), where n is the number of nodes in the Linked List.

In the first part, we're copying a Linked List into an Array List. Iterating down a Linked List in sequential order is O(n), and each of the n writes to the ArrayList is O(1). Therefore, the overall cost is O(n).

In the second part, we're using the two pointer technique to check whether or not the Array List is a palindrome. Each of the n values in the Array list is accessed once, and a total of O(n/2) comparisons are done. Therefore, the overall cost is O(n). The Python trick (reverse and list comparison as a one liner) is also O(n).

This gives O(2n)=O(n) because we always drop the constants.

Space complexity : O(n), where n is the number of nodes in the Linked List.

We are making an Array List and adding n values to it.

We can use recursive method where I can recursively move till last element.

Recursion gives us an elegant way to iterate through the nodes in reverse. For example, this algorithm will print out the values of the nodes in reverse. Given a node, the algorithm checks if it is null. If it is null, nothing happens. Otherwise, all nodes after it are processed, and then the value for the current node is printed.
```
function print_values_in_reverse(ListNode head)
    if head is NOT null
        print_values_in_reverse(head.next)
        print head.val
```
If we iterate the nodes in reverse using recursion, and iterate forward at the same time using a variable outside the recursive function, then we can check whether or not we have a palindrome

```java
class Solution {

    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}
```
#### Complexity
ime complexity : O(n), where n is the number of nodes in the Linked List.

The recursive function is run once for each of the n nodes, and the body of the recursive function is O(1). Therefore, this gives a total of O(n).

Space complexity : O(n), where n is the number of nodes in the Linked List.

I hinted at the start that this is not using O(1) space. This might seem strange, after all we aren't creating any new data structures. So, where is the O(n) extra memory we're using? Understanding what is happening here requires understanding how the computer runs a recursive function.

Each time a function is called within a function, the computer needs to keep track of where it is up to (and the values of any local variables) in the current function before it goes into the called function. It does this by putting an entry on something called the runtime stack, called a stack frame. Once it has created a stack frame for the current function, it can then go into the called function. Then once it is finished with the called function, it pops the top stack frame to resume the function it had been in before the function call was made.

Before doing any palindrome checking, the above recursive function creates n of these stack frames because the first step of processing a node is to process the nodes after it, which is done with a recursive call. Then once it has the n stack frames, it pops them off one-by-one to process them.

So, the space usage is on the runtime stack because we are creating n stack frames. Always make sure to consider what's going on the runtime stack when analyzing a recursive function. It's a common mistake to forget to.

Not only is this approach still using O(n) space, it is worse than the first approach because in many languages (such as Python), stack frames are large, and there's a maximum runtime stack depth of 1000 (you can increase it, but you risk causing memory errors with the underlying interpreter). With every node creating a stack frame, this will greatly limit the maximum Linked List size the algorithm can handle.

The only way we can avoid using O(n) extra space is by modifying the input in-place.

The strategy we can use is to reverse the second half of the Linked List in-place (modifying the Linked List structure), and then comparing it with the first half. Afterwards, we should re-reverse the second half and put the list back together. While you don't need to restore the list to pass the test cases, it is still good programming practice because the function could be a part of a bigger program that doesn't want the Linked List broken.

Algorithm

Specifically, the steps we need to do are:

- Find the end of the first half.
- Reverse the second half.
- Determine whether or not there is a palindrome.
- Restore the list.
- Return the result.

```java
class Solution {

    public boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }        

        // Restore the list and return the result.
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
```
#### Complexity
T
ime complexity : O(n), where n is the number of nodes in the Linked List.

Similar to the above approaches. Finding the middle is O(n), reversing a list in place is O(n), and then comparing the 2 resulting Linked Lists is also O(n).

Space complexity : O(1).

We are changing the next pointers for half of the nodes. This was all memory that had already been allocated, so we are not using any extra memory and therefore it is O(1).

I have seen some people on the discussion forum saying it has to be O(n) because we're creating a new list. This is incorrect, because we are changing each of the pointers one-by-one, in-place. We are not needing to allocate more than O(1) extra memory to do this work, and there is O(1) stack frames going on the stack. It is the same as reversing the values in an Array in place (using the two-pointer technique).

The downside of this approach is that in a concurrent environment (multiple threads and processes accessing the same data), access to the Linked List by other threads or processes would have to be locked while this function is running, because the Linked List is temporarily broken. This is a limitation of many in-place algorithms though.
