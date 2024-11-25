# 622. Design Circular Queue

### Design Your Implementation of the Circular Queue

The circular queue is a linear data structure in which the operations are performed based on the FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called a "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is space in front of the queue. But using the circular queue, we can use the space to store new values.

---

### Implement the `MyCircularQueue` Class

- **MyCircularQueue(k)**: Initializes the object with the size of the queue to be `k`.
- **int Front()**: Gets the front item from the queue. If the queue is empty, return `-1`.
- **int Rear()**: Gets the last item from the queue. If the queue is empty, return `-1`.
- **boolean enQueue(int value)**: Inserts an element into the circular queue. Return `true` if the operation is successful.
- **boolean deQueue()**: Deletes an element from the circular queue. Return `true` if the operation is successful.
- **boolean isEmpty()**: Checks whether the circular queue is empty or not.
- **boolean isFull()**: Checks whether the circular queue is full or not.

---

### Example 1:

**Input**
```plaintext
["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
```
**Output**
```plaintext
[null, true, true, true, false, 3, true, true, true, 4]
```
**Explanation**
```plaintext
MyCircularQueue myCircularQueue = new MyCircularQueue(3);
myCircularQueue.enQueue(1); // return True
myCircularQueue.enQueue(2); // return True
myCircularQueue.enQueue(3); // return True
myCircularQueue.enQueue(4); // return False
myCircularQueue.Rear();     // return 3
myCircularQueue.isFull();   // return True
myCircularQueue.deQueue();  // return True
myCircularQueue.enQueue(4); // return True
myCircularQueue.Rear();     // return 4
```
### Constraints

- `1 <= k <= 1000`
- `0 <= value <= 1000`
- At most 3000 calls will be made to `enQueue`, `deQueue`, `Front`, `Rear`, `isEmpty`, and `isFull`.

---

## Solution

We can implement this solution with Arrays or linked list. 

### Array Implementation
```java
class MyCircularQueue {
    private int[] queue;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    public MyCircularQueue(int k) {
        queue = new int[k];
        this.capacity=k;
        this.size=this.front=this.rear=0;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        queue[rear]=value;
        rear=(rear + 1)%capacity;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;

        front=(front + 1)%capacity;
        size--;
        return true;
    }
    
    public int Front() {
        if(isEmpty()) return -1;
        return queue[front];
    }
    
    public int Rear() {
        if(isEmpty()) return -1;
        return queue[(rear - 1 + capacity) % capacity];
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    public boolean isFull() {
        return size==capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
```
## Linked list implementation
```java
class MyCircularQueue {
    private static class Node{
        int data;
        Node next,prev;

        public Node(int data){
            this.data=data;
            next=prev=null;
        }
    }

    private Node queue;
    private int size;
    private int front;
    private Node rearNode;
    private int rear;
    private int capacity;

    public MyCircularQueue(int k) {
        queue = null;
        this.capacity=k;
        this.size=this.front=this.rear=0;
    }
    
    public boolean enQueue(int value) {
        if(isFull()) return false;
        Node newNode = new Node(value);
        if(queue==null) {queue= newNode;rearNode=newNode;}
        else{
            newNode.prev=rearNode;
            rearNode.next=newNode;
            rearNode = newNode;
        }
        rear=(rear + 1)%capacity;
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if(isEmpty()) return false;
        if(queue.next !=null){
            queue=queue.next;
            queue.prev=null;
        }
        else{
            queue = null;
        }
        front=(front + 1)%capacity;
        size--;
        return true;
    }
    
    public int Front() {
        if(isEmpty()) return -1;
        return queue.data;
    }
    
    public int Rear() {
        if(isEmpty()) return -1;
        return rearNode.data;
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    public boolean isFull() {
        return size==capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
```

## Complexity
**Time complexity:** O(1). All of the methods in our circular data structure is of constant time complexity.

**Space Complexity:** O(N). The overall space complexity of the data structure is linear, where N is the pre-assigned capacity of the queue. However, it is worth mentioning that the memory consumption of the data structure remains as its pre-assigned capacity during its entire life cycle.
