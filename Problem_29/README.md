# Contains Duplicate

## Leet Code Link - https://leetcode.com/problems/contains-duplicate/description/

## Complexity - Easy

## Description

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

#### Example 1:
```plaintext
Input: nums = [1,2,3,1]

Output: true

Explanation:

The element 1 occurs at the indices 0 and 3.
```
#### Example 2:
```plaintext
Input: nums = [1,2,3,4]

Output: false

Explanation:

All elements are distinct.
```
#### Example 3:
```plaintext
Input: nums = [1,1,1,3,3,4,3,2,4,2]

Output: true
```
#### Constraints
```plaintext
1 <= nums.length <= 105
-109 <= nums[i] <= 109
```

## Solution
This problem can be solved in multiple ways. As Brute force algorithm, we can have 2 loops and compare each element to the next element until all the elements are exhausted.

There can be better solution using HashSet where you sacrifice the memory and use extra set to store the elements and compare the values as you go over the loop

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
      HashSet<Integer> set = new HashSet();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
```
this is of complexity O(N)
### Time Complexity

- **Looping through the array**:
  - The program iterates through each element in the array once. This contributes \(O(n)\), where \(n\) is the size of the input array.

- **HashSet operations**:
  - The operations `contains` and `add` for a `HashSet` both have an average time complexity of \(O(1)\), assuming a good hash function and no significant hash collisions.

- **Overall Time Complexity**:
  \[
  O(n) \times O(1) = O(n)
  \]
  Thus, the overall time complexity of the solution is \(O(n)\) in the average case.

---

### Space Complexity

- The `HashSet` is used to store elements from the array.
  - In the worst case (when there are no duplicates), all \(n\) elements of the array are stored in the `HashSet`.
  - Hence, the space complexity is \(O(n)\).

---

### Summary

- **Time Complexity**: \(O(n)\)
- **Space Complexity**: \(O(n)\)

---

### Why This is Efficient

- Compared to solutions involving nested loops (\(O(n^2)\)), this approach is significantly faster for large inputs due to its linear time complexity.
- The `HashSet` ensures quick lookups and insertions, avoiding the need for sorting or multiple passes through the array.


But there is one more solution which uses arrays only and does not require extra memory

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int temp;
        for(int i=1; i<nums.length; i++){
            if(nums[i]==nums[i-1]){
                return true;
            }
            temp = nums[i];
            if(nums[i]<nums[i-1]){
                for(int j=i-2; j>=0; j--){
                    if(nums[j]==temp){
                        return true;
                    }
                }
                nums[i] = nums[i-1];
                nums[i-1]=temp;
            }
        }
        return false;
    }
}
```
#### What are we doing here?
1. we are having a temp variable -> temp
2. start the loop from the second element and compare with the first element.
3. Compare with the previous element and if equals return true;
4. Else if the previous element is out of ordet then we swap the elements and compare with all the elements below it
5. This back comparing adds extra complexity
6. The worst case it is O(N^2) and best case O(N)

