# Missing Ranges

## Leet Code Link - https://leetcode.com/problems/missing-ranges/description/

## Complexity - Medium

## Description
You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are within the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the shortest sorted list of ranges that exactly covers all the missing numbers. That is, no element of nums is included in any of the ranges, and each missing number is covered by one of the ranges.

 

 

#### Example 1:
```
Input: nums = [0,1,3,50,75], lower = 0, upper = 99
Output: [[2,2],[4,49],[51,74],[76,99]]
Explanation: The ranges are:
[2,2]
[4,49]
[51,74]
[76,99]
```
#### Example 2:
```
Input: nums = [-1], lower = -1, upper = -1
Output: []
Explanation: There are no missing ranges since there are no missing numbers.
 ```

#### Constraints:
```
-109 <= lower <= upper <= 109
0 <= nums.length <= 100
lower <= nums[i] <= upper
All the values of nums are unique.
```

---
## Solution
As the input array nums is sorted ascending and all the elements in it are within the given [lower, upper] bounds, we can simply check consecutive elements to see if they differ by one or not. If they don't, then we have found a missing range.

When nums[i + 1] - nums[i] <= 1, we know that there are no missing elements between nums[i + 1] and nums[i].

When nums[i + 1] - nums[i] > 1, we know that the range of elements, [nums[i] + 1, nums[i + 1] - 1], is missing.

However, there are two edge cases:

If we don't start with lower as the first element of the array, we will need to include [lower, num[0] - 1] as a missing range as well.

Similarly, if we don't end with upper as the last element of the array, we will need to include [nums[n - 1] + 1, upper] as a missing range as well where n is the length of nums.

```java
class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> out = new ArrayList<>();
        if(nums.length==0){ out.add(new ArrayList<Integer>(){{
                    add(lower);add(upper);}});
                    return out;
        }
        else if(nums.length==1&&lower==upper&&lower==nums[0])return out;
            if(lower!=nums[0]){
             out.add(new ArrayList<Integer>(){{
                    add(lower);add(nums[0]-1);}});
            }
        for(int i=1;i<nums.length;i++){
            List<Integer> in = new ArrayList<>();
            if(nums[i]-nums[i-1]>1){
                   in.add(nums[i-1]+1); in.add(nums[i]-1);
                    out.add(in);
            }

        }
         if(upper!=nums[nums.length-1]){
            out.add(new ArrayList<Integer>(){{
                    add(nums[nums.length-1]+1);add(upper);}});
            }
        return out;
    }
}
```
#### Complexity 
Time complexity: O(n).

We iterate over all the elements of nums and check whether an element differs by 1 or greater from its succeeding element, which takes O(n) time.
All of the ranges are also added to the missing_ranges list. In the worst-case scenario, n+1 elements could be added to the list again, which would take O(n) time. This would occur if we did not begin with lower as the first element of the array, if each subsequent element in 'nums' differed by more than 1, and if we did not end with upper as the last element of the array.

Space complexity: O(1).

Except for a few integer variables like n and i that use constant space, we do not consume any space (if we ignore the space consumed by the input and output).
