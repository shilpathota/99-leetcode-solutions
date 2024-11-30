# Moving Average from Data Stream

## Leet code Link - https://leetcode.com/problems/moving-average-from-data-stream/description/

## Complexity - Easy

## Description
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

- `MovingAverage(int size)` Initializes the object with the size of the window size.
- `double next(int val)` Returns the moving average of the last size values of the stream.

#### Example 1:
```plaintext
Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 ```

#### Constraints:
```plaintext
1 <= size <= 1000
-105 <= val <= 105
At most 104 calls will be made to next.
```
---
## Solution
the goal is to find the moving average which means I want to remove the oldest added value for the newly added and calculate average if the capacity is exceeded.

Here we have used a list to store the list of values. the Current Sum and moving average are modified in each loop according to the values added

```java
class MovingAverage {
    private int size;
    private int num;
    private List<Integer> list;
    private double currentSum = 0;private double movingavg=0.0;
    public MovingAverage(int size) {
        this.size = size;
        this.num=0;
        this.list=new ArrayList<>();
    }
    
    public double next(int val) {
        list.add(val);
        num++;
        if(num<=size){
            currentSum+=val;
            movingavg = currentSum/num;
        }
        else{
            currentSum= currentSum-list.get(num-size-1);
            currentSum=currentSum+val;
            movingavg = currentSum/size;
        }
        return movingavg;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
```

**Time Complexity** - For next operation it would be O(1)

**Space Complesity** - Here we are storing all the elements added and removed so it would be O(N)

### IS there any optimization that can be done here?

By definition of the moving window, at each step, we add a new element to the window, and at the same time we remove the oldest element from the window. Here, we could apply a data structure called double-ended queue (a.k.a deque) to implement the moving window, which would have the constant time complexity (O(1)) to add or remove an element from both its ends. With the deque, we could reduce the space complexity down to O(N) where N is the size of the moving window.
![image](https://github.com/user-attachments/assets/4aa3bc93-d20f-4a8e-bf50-8336ae614c24)

Secondly, to calculate the sum, we do not need to reiterate the elements in the moving window.

We could keep the sum of the previous moving window, then in order to obtain the sum of the new moving window, we simply add the new element and deduce the oldest element. With this measure, we then can reduce the time complexity to constant.

Follow the intuition, we replace the queue with the deque and add a new variable window_sum in order to calculate the sum of moving window in constant time.

```java
class MovingAverage {
  int size, windowSum = 0, count = 0;
  Deque queue = new ArrayDeque<Integer>();

  public MovingAverage(int size) {
    this.size = size;
  }

  public double next(int val) {
    ++count;
    // calculate the new sum by shifting the window
    queue.add(val);
    int tail = count > size ? (int)queue.poll() : 0;

    windowSum = windowSum - tail + val;

    return windowSum * 1.0 / Math.min(size, count);
  }
}
```
#### Complexity

**Time Complexity:** O(1), as we explained in intuition.
**Space Complexity:** O(N), where N is the size of the moving window.

This solution can also be obtained by using ciruclar quque with fixed size.

Other than the deque data structure, one could also apply another fun data structure called circular queue, which is basically a queue with the circular shape.

- The major advantage of circular queue is that by adding a new element to a full circular queue, it automatically discards the oldest element. Unlike deque, we do not need to explicitly remove the oldest element.
- Another advantage of circular queue is that a single index suffices to keep track of both ends of the queue, unlike deque where we have to keep a pointer for each end.

- No need to resort to any library, one could easily implement a circular queue with a fixed-size array. The key to the implementation is the correlation between the index of head and tail elements, which we could summarize in the following formula:
```plaintext
tail=(head+1)modsize
```
In other words, the tail element is right next to the head element. Once we move the head forward, we would overwrite the previous tail element.

```java
class MovingAverage {
  int size, head = 0, windowSum = 0, count = 0;
  int[] queue;
  public MovingAverage(int size) {
    this.size = size;
    queue = new int[size];
  }

  public double next(int val) {
    ++count;
    // calculate the new sum by shifting the window
    int tail = (head + 1) % size;
    windowSum = windowSum - queue[tail] + val;
    // move on to the next head
    head = (head + 1) % size;
    queue[head] = val;
    return windowSum * 1.0 / Math.min(size, count);
  }
}
```

#### Complexity

**Time Complexity:** O(1), as we can see that there is no loop in the next(val) function.

**Space Complexity:** O(N), where N is the size of the circular queue.

