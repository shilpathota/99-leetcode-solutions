## Search Insert Position
### Leet Code Link - https://leetcode.com/problems/search-insert-position/description/
### Complexity - Easy
## Description 
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

 

**Example 1:**

Input: nums = [1,3,5,6], target = 5
Output: 2<br/>
**Example 2:**

Input: nums = [1,3,5,6], target = 2
Output: 1<br/>
**Example 3:**

Input: nums = [1,3,5,6], target = 7
Output: 4
 

**Constraints:**

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104

## Solution
This problem clearly says the Binary search algorithm and can be used to resolve this. <br/>
For Binary search, To know more about it - [Binary Search](https://github.com/shilpathota/Algorithms_Made_Easy/blob/main/BinarySearch/README.md)

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int index =-1;
        if(target<nums[left]) return 0;
        else if(target>nums[right]) return right+1;
        while(left<=right){
            int middle = (int) Math.floor((left+right)/2);
            if(nums[middle]==target){
                index = middle;
                break;
            }
            else if(nums[middle]<target){
                left = middle+1;
            }
            else{
                right = middle-1;
            }
            index=left;
        }
        return index;
    }
}
```
### complexity
Time complexity : O(logN).

Let us compute the time complexity with the help of master theorem

T(N)=aT( 
b
N
​
 )+Θ(N 
d
 ).

The equation represents dividing the problem up into a subproblems of size  
b
N
​
  in Θ(N 
d
 ) time.

Here at each step there is only one subproblem i.e. a = 1, its size is half of the initial problem i.e. b = 2, and all this happens in a constant time i.e. d = 0. As a result, log 
b
​
 a=d and hence we're dealing with case 2 that results in O(n 
log 
b
​
 a
 log 
d+1
 N) = O(logN) time complexity.

Space complexity: O(1)


