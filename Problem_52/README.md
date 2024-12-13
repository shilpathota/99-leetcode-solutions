# Subarray Sum Equals K

## LeetCodeLink - https://leetcode.com/problems/subarray-sum-equals-k/description/

## Complexity - Medium

## Description
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

#### Example 1:
```plaintext
Input: nums = [1,1,1], k = 2
Output: 2
```
#### Example 2:
```plaintext
Input: nums = [1,2,3], k = 3
Output: 2
 ```

#### Constraints:
```plaintext
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
```
---
## Solution
As per this solution, On first encounter I know I have to get the each and every subarray of this array and then calculate the sum and compare it with the target value k.

Which will give the 2 loops one outer loop and other inner loop 

```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
```

Time complexity : O(n^3). Considering every possible subarray takes O(n^2 ) time. For each of the subarray we calculate the sum taking O(n) time in the worst case, taking a total of O(n^3 ) time.

Space complexity : O(1). Constant space is used.

But we can optimize this

Instead of considering all the start and end points and then finding the sum for each subarray corresponding to those points, we can directly find the sum on the go while considering different end points. i.e. We can choose a particular start point and while iterating over the end points, we can add the element corresponding to the end point to the sum formed till now. Whenever the sum equals the required k value, we can update the count value. We do so while iterating over all the end indices possible for every start index. Whenever, we update the start index, we need to reset the sum value to 0.

```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
```
this way my complexity is reduced to O(n^2)

But is there anything else that I can do

The idea behind this approach is as follows: If the cumulative sum(represented by sum[i] for sum up to i th  index) up to two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought further, if the cumulative sum up to two indices, say i and j is at a difference of k i.e. if sum[i]−sum[j]=k, the sum of elements lying between indices i and j is k.

Based on these thoughts, we make use of a hashmap map which is used to store the cumulative sum up to all the indices possible along with the number of times the same sum occurs. We store the data in the form: (sum i ,no.ofoccurrencesofsum i). We traverse over the array nums and keep on finding the cumulative sum. Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. Further, for every sum encountered, we also determine the number of times the sum sum−k has occurred already, since it will determine the number of times a subarray with sum k has occurred up to the current index. We increment the count by the same amount.

After the complete array has been traversed, the count gives the required result.

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result =0;
        HashMap<Integer,Integer> prefix = new HashMap<>();
        prefix.put(0,1);
        int prefixSum=0;
        for (int num:nums){
            prefixSum+=num;
            result+=prefix.getOrDefault(prefixSum-k,0);
            prefix.put(prefixSum,prefix.getOrDefault(prefixSum,0)+1);
        }
        return result;
    }
}
```
This way I'm only iterating the array once and so my time complexity is O(N) but my space complexity is also O(N) as we are using hashmap

