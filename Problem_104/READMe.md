# Max Consecutive Ones III

## Leet code link - https://leetcode.com/problems/max-consecutive-ones-iii/description/

## Complexity - Medium

## Description
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 

#### Example 1:
```
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
```
#### Example 2:
```
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 ```

#### Constraints:
```
1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
```
---
## Solution
The problem has asked for longest contiguous subarray that contains only 1s. What makes this problem a little trickier is the k flips allowed from 0 --> 1. This means a contiguous subarray of 1's might not just contain 1's but also may contain some 0's. The number of 0's allowed in a given subarray is given by k.

<img src = "https://leetcode.com/problems/max-consecutive-ones-iii/Figures/1004/1004_Max_Consecutive_Ones_1.png" />

The above diagram shows the `flip` which helped us to get the longest contiguous subarray if only one flip was allowed.

##### Approach: Sliding Window

To find the longest subarray with contiguous 1's we might need to find all the subarrays first. But do we really need to do that? If we find all the subarrays we are essentially finding out so many unnecessary overlapping subarrays too.

We can use a simple sliding window approach to solve this problem.

In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current window and then we have the left pointer whose job is to contract a given window. At any point in time only one of these pointers move and the other one remains fixed.

The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has reached the limit of 0's allowed, we contract (if possible) and save the longest window till now.

The answer is the longest desirable window.

##### Algorithm

Initialize two pointers. The two pointers help us to mark the left and right end of the window/subarray with contiguous 1's.

left = 0, right = 0, window_size = 0

We use the right pointer to expand the window until the window/subarray is desirable. i.e. number of 0's in the window are in the allowed range of [0, k].

Once we have a window which has more than the allowed number of 0's, we can move the left pointer ahead one by one until we encounter 0 on the left too. This step ensures we are throwing out the extra zero.

<img src = "https://leetcode.com/problems/max-consecutive-ones-iii/Figures/1004/1004_Max_Consecutive_Ones_2.png" />

<img src = "https://leetcode.com/problems/max-consecutive-ones-iii/Figures/1004/1004_Max_Consecutive_Ones_3.png" />

Observe we don't contract the window if it's not needed and thus save on some computation.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int i=0; int j=0;
        while(j<nums.length){
             // If we included a zero in the window we reduce the value of k.
            // Since k is the maximum zeros allowed in a window.
            if(nums[j]==0){
                k--;
            }
             // A negative k denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
            if(k<0){
                //adds 1 to the solution if it is 0. so we have one more k
                k += 1 - nums[i];
                i++;
            }
            j++;
        }
        return j-i;
    }
}
```
#### Complexity

Time Complexity: O(N), where N is the number of elements in the array. In worst case we might end up visiting every element of array twice, once by left pointer and once by right pointer.

Space Complexity: O(1). We do not use any extra space.
