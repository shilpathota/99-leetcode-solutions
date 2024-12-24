# Element Appearing More Than 25% In Sorted Array

## Leet code Link - https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/description/

## Complexity - Easy

## Description
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

 

#### Example 1:
```
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
```
#### Example 2:
```
Input: arr = [1,1]
Output: 1
 ```

#### Constraints:
```
1 <= arr.length <= 104
0 <= arr[i] <= 105
```
---
## Solution
We can count the frequency of each num in arr using a hash map counts. Once we have all the frequencies, we can iterate over the keys of counts and check which one has a value greater than n / 4, where n is the length of arr.

If a key in counts has a value greater than n / 4, it must occupy more than 25% of arr and thus would be our answer.

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        Map<Integer, Integer> counts = new HashMap();
        int target = arr.length / 4;
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            if (counts.get(num) > target) {
                return num;
            }
        }

        return -1;
    }
}
```
#### Complexity 
Given n as the length of arr,

Time complexity: O(n)

We iterate over arr once to calculate counts. This costs O(n). Next, we iterate over counts, which also costs O(n).

Space complexity: O(n)

In the worst-case scenario, counts can contain at most O(n) keys and thus grow to a size of O(n).

We already know that the input is sorted. So we can complare the value of size and if euqlas then return the element
- Calculate size = n / 4.
- Iterate i from 0 until arr.length - size:
- If arr[i] = arr[i + size], return arr[i].
- The code should never reach this point since it's guaranteed an answer exists. Return anything.

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int size = arr.length / 4;
        for (int i = 0; i < arr.length - size; i++) {
            if (arr[i] == arr[i + size]) {
                return arr[i];
            }
        }
        
        return -1;
    }
}
```
#### Complexity 
Given n as the length of arr,

Time complexity: O(n)

We iterate over  3n/4  indices, performing O(1) work at each iteration.

Space complexity: O(1)

We aren't using any extra space except for the integer size.

The better approach is binary search where we reduce the search by half everytime. Here we know that the number is more than 25% which means if we divide the input into 4 parts then the border should =overlap with the target atleast once

We will only consider the elements at each of these indices as candidates since one of them must be the answer. For a given candidate, we can find its frequency by identifying its block size. To identify its block size, we find the leftmost index in which candidate appears as left and the rightmost index in which candidate appears as right. Then, the size of the block is right - left + 1. We can calculate left and right using binary search.

- Set n = arr.length.
- Create the array candidates with elements arr[n / 4], arr[n / 2], arr[3 * n / 4].
- Set target = n / 4.
- For each candidate in candidates:
- Calculate the leftmost index of candidate as left using binary search.
- Calculate the rightmost index of candidate as right using binary search.
- If right - left + 1 > target, return candidate.
- The code should never reach this point since it's guaranteed an answer exists. Return anything.

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int[] candidates = {arr[n/4],arr[n/2],arr[3*n/4]};
        int target = arr.length/4;
        for(int candidate : candidates){
            int left = lowerbinarySearch(arr,candidate);
            int right = upperbinarySearch(arr, candidate)-1;
            if(right-left+1>target){
                return candidate;
            }
        }
        return -1;
    }

    public int lowerbinarySearch(int[] arr,int target){
        int left = 0; int right = arr.length;
        while(left<right){
                    int mid = left +(right-left)/2;
            if(arr[mid]>=target){
                right = mid;
            }
            else{
                left = mid+1;
            }
        }
        return left;
    }
   public int upperbinarySearch(int[] arr,int target){
        int left = 0; int right = arr.length;
        while(left<right){
            int mid = left +(right-left)/2;
            if(arr[mid]>target){
                right = mid;
            }
            else{
                left = mid+1;
            }
        }
        return left;
    }
}
```
#### Complexity 
Given n as the length of arr,

Time complexity: O(logn)

We have three candidates. For each candidate, we perform two binary searches over arr, each costing O(logn).

Space complexity: O(1)

We aren't using any extra space except for a few integers.
