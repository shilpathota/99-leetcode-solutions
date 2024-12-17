# Single Number

## Leet code Link - https://leetcode.com/problems/single-number/description/

## Complexity - Easy

## Description
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

#### Example 1:
```
Input: nums = [2,2,1]
Output: 1
```
#### Example 2:
```
Input: nums = [4,1,2,1,2]
Output: 4
```
#### Example 3:
```
Input: nums = [1]
Output: 1
 ```

#### Constraints:
```
1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
```
---
## Solution
On looking at the problem we know we have to track the count of elements and the immediate thought would be hashmap

```java
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> count = new HashMap<>();

        for(int i:nums){
            count.put(i,count.getOrDefault(i,0)+1);
        }
        for(int i : count.keySet()){
            if(count.get(i)==1){
                return i;
            }
        }
        return nums[0];
    }
}
```
time Complexity and space complexity is O(N)

Looks like there is even better solution with reduced space complexity

If we take XOR of zero and some bit, it will return that bit
a⊕0=a
If we take XOR of two same bits, it will return 0
a⊕a=0
a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
So we can XOR all bits together to find the unique number.

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}
```
Time Complexity is O(N) and space complesity is O(1)
