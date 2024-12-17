# Longest Harmonious Subsequence

## Leet code Link - https://leetcode.com/problems/longest-harmonious-subsequence/description/

## Complexity - Easy

## Description
We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.<br/>

Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.<br/>

A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.<br/>

 

#### Example 1:
```
Input: nums = [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
```
#### Example 2:
```
Input: nums = [1,2,3,4]
Output: 2
```
#### Example 3:
```
Input: nums = [1,1,1,1]
Output: 0
 ```

#### Constraints:
```
1 <= nums.length <= 2 * 104<br/>
-109 <= nums[i] <= 109<br/>
```
## Solution
In the brute force solution, we consider every possible subsequence that can be formed using the elements of the given array. For every subsequence, we find the maximum and minimum values in the subsequence. If the difference between the maximum and the minimum values obtained is 1, it means the current subsequence forms a harmonious subsequence. Thus, we can consider the number of elements in this subsequence to be compared with the length of the last longest harmonious subsequence.

In order to obtain all the subseqeuences possible, we make use of binary number representation of decimal numbers. For a binary number of size n, a total of 2 
n
  different binary numbers can be generated. We generate all these binary numbers from 0 to 2 
n
 . For every binary number generated, we consider the subsequence to be comprised of only those elements of nums which have a 1 at the corresponding position in the current binary number. The following figure shows an example of the way the elements of nums are considered in the current subsequence.
```java
public class Solution {
    public int findLHS(int[] nums) {
        int res = 0;
        for (int i = 0; i < (1 << nums.length); i++) {
            int count = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                    count++;
                }
            }
            if (max - min == 1)
                res = Math.max(res, count);
        }
        return res;
    }
}
```
 #### complexity
 Time complexity : O(2^n). Number of subsequences generated will be 2^n.

Space complexity : O(1). Constant space required.

We can improvize this by incrementing the count through the inner loop for each subsequence when it finds less than 1
```java
 public class Solution {
    public int findLHS(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i])
                    count++;
                else if (nums[j] + 1 == nums[i]) {
                    count++;
                    flag = true;
                }
            }
            if (flag)
                res = Math.max(count, res);
        }
        return res;
    }
}
```
### Complexity 
Time complexity : O(n^2 ). Two nested loops are there.

Space complexity : O(1). Constant space required.

We can use sorting 
```java
public class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int prev_count = 1, res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            if (i > 0 && nums[i] - nums[i - 1] == 1) {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                res = Math.max(res, count + prev_count);
                prev_count = count;
            } else {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++;
                    i++;
                }
                prev_count = count;
            }
        }
        return res;
    }
}
```
#### Complexity
Time complexity : O(nlogn). Sorting takes O(nlogn) time.

Space complexity : O(logn). logn space is required by sorting in average case.

We are using sliding window algorithm
```java
class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        if(nums.length==0){
            return 0;
        }
        int l=0; int r=0; int maxLength=0;
        int n=nums.length;
        while(r<n){
            if(Math.abs(nums[r]-nums[l])==1){
                maxLength= Math.max(maxLength,r-l+1);
                r++;
            }else if(Math.abs(nums[r]-nums[l])<1){
                r++;
            }
            else{
                l++;
            }
        }
        return maxLength;
    }
}
```

We can also use Hashmap

```java
public class Solution {
    public int findLHS(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.containsKey(key + 1))
                res = Math.max(res, map.get(key) + map.get(key + 1));
        }
        return res;
    }
}
```
#### Complexity
Time complexity : O(n). One loop is required to fill map and one for traversing the map.

Space complexity : O(n). In worst case map size grows upto size n.

In single loop
```java
public class Solution {
    public int findLHS(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1))
                res = Math.max(res, map.get(num) + map.get(num + 1));
            if (map.containsKey(num - 1))
                res = Math.max(res, map.get(num) + map.get(num - 1));
        }
        return res;
    }
}
```
Time complexity : O(n). Only one loop is there.

Space complexity : O(n). map size grows upto size n.

