# Missing Element in Sorted Array

## Leet Code Link - https://leetcode.com/problems/missing-element-in-sorted-array/description/

## Complexity - Medium

## Description
Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.

 

#### Example 1:
```
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.
```
#### Example 2:
```
Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
```
#### Example 3:
```
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 ```

#### Constraints:
```
1 <= nums.length <= 5 * 104
1 <= nums[i] <= 107
nums is sorted in ascending order, and all the elements are unique.
1 <= k <= 108
 ```
---
## Solution
we cannot traverse all integers one by one. Instead, we can traverse the nums array. Of course, the difference between two adjacent numbers in "nums" may not necessarily be 1. Therefore, we can calculate how many missing elements exist between these two numbers.

For each index i, we can set missed_in_gap = nums[i] - nums[i - 1] - 1 to get the number of missing elements between the interval [nums[i], nums[i - 1]].

We start from i = 1 and check if the interval [nums[i - 1], nums[i]] contains less than k missing elements.

If it contains missing elements less than k, which is missed_in_gap < k, then the k 
th
  missing element must be on the right side of nums[i], so we can reduce k by missed_in_gap, and move on to the next interval [nums[i], nums[i + 1]] by incrementing i by 1.

  If we have finished the iteration while there is still k left, the k 
th
  missing element is at the k 
th
  position to the right of nums[n - 1], which is answer = nums[n - 1] + k.

  Otherwise (missed_in_gap >= k,), the k 
th
  missing element must be between nums[i - 1] and nums[i]. The k 
th
  missing element is at the k 
th
  position to the right of nums[i - 1], which is answer = nums[i - 1] + k.

```java
class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        
        for (int i = 1; i < n; ++i) {
            int missedInGap = nums[i] - nums[i - 1] - 1;
            if (missedInGap >= k) {
                return nums[i - 1] + k;
            }
            k -= missedInGap;
        }
        
        return nums[n - 1] + k;
    }
}
```
#### Complexity
Let n be the length of the input array nums.

Time complexity: O(n)

The algorithm requires iterating over the entire array at most once to calculate the number of missing elements between each adjacent pair of numbers.
Space complexity: O(1)

We only use a constant amount of extra space for variables missed_in_gap and i.



Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?

Another approach to solving this problem is to use binary search. Instead of focusing on the number of missing elements in every two adjacent numbers like nums[i] and nums[i + 1]. We will focus on the total number of missing elements on nums[i]'s left, that is, within the range [nums[0], nums[i]].

For an index i, we can get the number of missing elements on its left using the following observation. The total number of integers within the range of [nums[0], nums[i]] is nums[i] - nums[0] + 1. We know that there are i + 1 existing elements, so the number of missing elements is:

(nums[i] - nums[0] + 1) - (i + 1) = nums[i] - nums[0] - i.

<img src = "https://leetcode.com/problems/missing-element-in-sorted-array/Figures/1060/b2.png" />

Given an index i:

If nums[i] - nums[0] - i >= k, the k 
th
  missing element is on nums[i]'s left.
Otherwise, the k 
th
  missing element is on nums[i]'s right.
Another key point is, the number of missing elements increases as i moves to the right.

<img src = "https://leetcode.com/problems/missing-element-in-sorted-array/Figures/1060/b1.png" />

Now is the time for binary search, we can define the search space as left = 0, right = n - 1. We will then find the midpoint of the range using mid = right - (right - left) / 2 and calculate the number of missing elements between nums[mid] and nums[0].
We use nums[mid] - nums[0] - i as mentioned above to decide which direction to move the search range:

If the count is less than k, we can update the search range to the right side of the mid.
Otherwise, we update the search range to the left side of the mid.
We can repeat this process until left >= right. At this point, left, is the rightmost index such that [nums[0], nums[left]] has fewer missing elements than k.

The answer, the k 
th
  missing element must be somewhere to the right of nums[left]. There will be no elements in the array between the answer and nums[left].

We don't know the answer yet, but let's focus on the range [nums[0], answer]. We know that it contains left + 1 elements from the input array, and k missing elements (by definition, the last element of the range answer is the k 
th
  missing element). Therefore the total number of elements in the range is:

elements from the input + missing elements = left + 1 + k

Finally, as we know, answer - nums[0] + 1 is the size of the range. Setting these equations equal to each other, we can rearrange for answer.

answer - nums[0] + 1 = left + 1 + k -> answer = nums[0] + left + k.


```java
class Solution {
    public int missingElement(int[] nums, int k) {
        int low =0; int high = nums.length-1;
        while(low<high){
            int mid = high - (high-low) /2;
            if(nums[mid]-nums[0]-mid <k){
                low = mid;
            }
            else{
                high = mid-1;
            }
        }
        return nums[0]+k+low;
    }
}
```
#### complexity 
LLet n be the length of the input array nums.

Time complexity: O(logn)

The algorithm repeatedly divides the search space in half using binary search until it finds the k 
th
  missing element.
Space complexity: O(1)

The algorithm only uses a constant amount of extra space for variables left, right, and mid.
