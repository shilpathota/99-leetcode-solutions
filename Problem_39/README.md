# Kth Largest Element in an Array

## Leet code Link - https://leetcode.com/problems/kth-largest-element-in-an-array/description/

## Complexity - Medium

## Description
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?


#### Example 1:
```plaintext
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```
#### Example 2:
```plaintext
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 ```

#### Constraints:
```plaintext
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
```
---
## Solution
This problem can be solved in multiple ways. One straight forward solution would be sort the array and return the element which is length - k so it returns the kth largest element

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        // Can't sort int[] in descending order in Java;
        // Sort ascending and then return the kth element from the end
        return nums[nums.length - k];
    }
}
```

### Complexity Analysis 
Time complexity: O(n⋅logn)

Sorting nums requires O(n⋅logn) time.

Space Complexity: O(logn) or O(n)

The space complexity of the sorting algorithm depends on the implementation of each programming language:

- In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, which has a space complexity of O(logn)
- In C++, the sort() function provided by STL uses a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logn)
- In Python, the sort() function is implemented using the Timsort algorithm, which has a worst-case space complexity of O(n)

  Second solution would be using the min heap by using priority queue which would heapify based on the input values.

The problem is asking for the k  th largest element. Let's push all the elements onto a min-heap, but pop from the heap when the size exceeds k. When we pop, the smallest element is removed. By limiting the heap's size to k, after handling all elements, the heap will contain exactly the k largest elements from the array.
   
- Initialize a min-heap heap.
- Iterate over the input. For each num:
  - Push num onto the heap.
  - If the size of heap exceeds k, pop from heap.
- Return the top of the heap.

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num: nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        
        return heap.peek();
    }
}
```
Time complexity: O(n⋅logk)

Operations on a heap cost logarithmic time relative to its size. Because our heap is limited to a size of k, operations cost at most O(logk). We iterate over nums, performing one or two heap operations at each iteration.

We iterate n times, performing up to logk work at each iteration, giving us a time complexity of O(n⋅logk).

Because k≤n, this is an improvement on the previous approach.

Space complexity: O(k)

The heap uses O(k) space.

Third solution would be using the quick sort algorithm in a little modified way which is QuickSelect.

Quickselect, also known as Hoare's selection algorithm, is an algorithm for finding the k  th  smallest element in an unordered list. It is significant because it has an average runtime of O(n).

Quickselect uses the same idea as Quicksort. First, we choose a pivot index. The most common way to choose the pivot is randomly. We partition nums into 3 sections: elements equal to the pivot, elements greater than the pivot, and elements less than the pivot.

Next, we count the elements in each section. Let's denote the sections as follows:

- left is the section with elements less than the pivot
- mid is the section with elements equal to the pivot
- right is the section with elements greater than the pivot
Quickselect is normally used to find the k th  smallest element, but we want the k th  largest. To account for this, we will swap what left and right represent - left will be the section with elements greater than the pivot, and right will be the section with elements less than the pivot.

If the number of elements in left is greater than or equal to k, the answer must be in left - any other element would be less than the k th  largest element. We restart the process in left.

![image](https://github.com/user-attachments/assets/39890046-7f66-4129-9429-0bf7a77ae64e)

If the number of elements in left and mid is less than k, the answer must be in right - any element in left or mid would not be large enough to be the k  th   largest element. We restart the process in right.
![image](https://github.com/user-attachments/assets/bc9a267e-1754-4237-b748-0513ed682690)

There's one extra step if the answer is in right. When we go to search in right, we are effectively "deleting" the elements in left and mid (since they will never be considered again). Because the elements in left and mid are greater than the answer, deleting them means we must shift k. Let's say we're looking for the 8  th  greatest element, but then we delete the 4 greatest elements. In the remaining data, we would be looking for the 4 th   greatest element, not the 8 th . Therefore, we need to subtract the length of left and mid from k when we search for right.

We don't need to modify k when we search in left because when we search in left, we delete elements smaller than the answer, which doesn't affect k.

![image](https://github.com/user-attachments/assets/388a05ed-477d-409e-b767-bb6ec39be9a7)

If the answer is in neither left or right, it must be in mid. Since mid only has elements equal to the pivot, the pivot must be the answer.

The easiest way to implement this repetitive process is by using recursion.

Define a quickSelect function that takes arguments nums and k. This function will return the k th  greatest element in nums (the nums and k given to it as input, not the original nums and k).

- Select a random element as the pivot.
- Create left, mid, and right as described above.
- If k <= left.length, return quickSelect(left, k).
- If left.length + mid.length < k, return quickSelect(right, k - left.length - mid.length).
- Otherwise, return pivot.
  
Call quickSelect with the original nums and k, and return the answer.

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num: nums) {
            list.add(num);
        }
        
        return quickSelect(list, k);
    }
    
    public int quickSelect(List<Integer> nums, int k) {
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);
        
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int num: nums) {
            if (num > pivot) {
                left.add(num);
            } else if (num < pivot) {
                right.add(num);
            } else {
                mid.add(num);
            }
        }
        
        if (k <= left.size()) {
            return quickSelect(left, k);
        }
        
        if (left.size() + mid.size() < k) {
            return quickSelect(right, k - left.size() - mid.size());
        }
        
        return pivot;
    }
}
```
Given n as the length of nums,

Time complexity: O(n) on average, O(n^2) in the worst case

Each call we make to quickSelect will cost O(n) since we need to iterate over nums to create left, mid, and right. The number of times we call quickSelect is dependent on how the pivots are chosen. The worst pivots to choose are the extreme (greatest/smallest) ones because they reduce our search space by the least amount. Because we are randomly generating pivots, we may end up calling quickSelect O(n) times, leading to a time complexity of O(n^2).

However, the algorithm mathematically almost surely has a linear runtime. For any decent size of nums, the probability of the pivots being chosen in a way that we need to call quickSelect O(n) times is so low that we can ignore it.

On average, the size of nums will decrease by a factor of ~2 on each call. You may think: that means we call quickSelect O(logn) times, wouldn't that give us a time complexity of O(n⋅logn)? Well, each successive call to quickSelect would also be on a nums that is a factor of ~2 smaller. This recurrence can be analyzed using the master theorem with a = 1, b = 2, k = 1:

T(n)=T(n/2)+O(n)=O(n)

Space complexity: O(n)

We need O(n) space to create left, mid, and right. Other implementations of Quickselect can avoid creating these three in memory, but in the worst-case scenario, those implementations would still require O(n) space for the recursion call stack.

