# Sqrt(x)

### Leet Code Link - https://leetcode.com/problems/sqrtx/description/
### Complexity - Easy

## Description
Integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 

**Example 1:**

Input: x = 4
Output: 2
**Explanation:** The square root of 4 is 2, so we return 2.<br />
**Example 2:**

Input: x = 8
Output: 2
**Explanation:** The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.<br />
 

**Constraints:**

0 <= x <= 231 - 1

## Solution
As this solution finding includes iterating from smaller to bigger number to find square root and we have a target too. <br/>
This is clearly eligible for Binary Search Algorithm. For more information you can go over this - [Binary Search] (https://github.com/shilpathota/Algorithms_Made_Easy/blob/main/BinarySearch/README.md)
