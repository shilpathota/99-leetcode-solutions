# Add Two Integers

## Leet code link - https://leetcode.com/problems/add-two-integers/description/

## complexity - Easy

## Description
Given two integers num1 and num2, return the sum of the two integers.
 

#### Example 1:
```
Input: num1 = 12, num2 = 5
Output: 17
Explanation: num1 is 12, num2 is 5, and their sum is 12 + 5 = 17, so 17 is returned.
```
#### Example 2:
```
Input: num1 = -10, num2 = 4
Output: -6
Explanation: num1 + num2 = -6, so -6 is returned.
 ```

#### Constraints:
```
-100 <= num1, num2 <= 100
```
---
## Solution
the solution is straight forward where I return the sum of 2 numbers
```java
class Solution {
    public int sum(int num1, int num2) {
        return num1+num2;
    }
}
```
