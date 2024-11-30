# Majority Element

## Leet code Link - https://leetcode.com/problems/majority-element/description/

## Complexity - Easy

## Description
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

 

#### Example 1:
```plaintext
Input: nums = [3,2,3]
Output: 3
```
#### Example 2:
```plaintext
Input: nums = [2,2,1,1,1,2,2]
Output: 2
 ```

#### Constraints:
```plaintext
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
 ```

---
## Solution
In this approach, I would like to use hashmap which makes the space complexity as O(N) but as I add the counter element I have additional pointers to track the highest element and its count

This approach will need to go over all the elements in O(N) times as only once
```java
class Solution {
    int count=0;int freqelement =-1;
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap();
        freqelement = nums[0];
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
            if(count<map.get(nums[i])){
                freqelement = nums[i];
                count = map.get(nums[i]);
            }
        }
        return freqelement;
    }
}
```

But can we optimize it even better with space complexity. In that case, can we do without using map.

```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
```
The above solution increments the count for the same element if other element then reduces the count. If the count reaches 0 which means each other element nulls the desired element but the majority element increments the count and so we return the max element.
