# 680. Valid Palindrome II

## Leet Code Link - https://leetcode.com/problems/valid-palindrome-ii/description/

## Complexity - Easy

## Description
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

 

#### Example 1:

**Input:** s = "aba"

**Output:** true
#### Example 2:

**Input:** s = "abca"

**Output:** true

**Explanation:** You could delete the character 'c'.
#### Example 3:

**Input:** s = "abc"

**Output:** false
 

#### Constraints:

1 <= s.length <= 105

s consists of lowercase English letters.

## Solution
Here we use a method of 2 pointer as we can have pointer on both ends and compare them 

<pre>
  1. Create a check Palindrom function where you use i and j and loop them till i is less than j
  2. Compare the values of i and j in the string and if not equal then it would return false else it would increment i and decrement j and proceed 
  3. In the main function we do the same operation but when there is a not equal condition arrives, we call the palindrome function created above and pass the value of i and j-1 or i+1 and jas we need to try both ends where it should work removing 1 element after i or before j.
  4. If either of them is true we return true else false.
</pre>

## Complexity
![image](https://github.com/user-attachments/assets/9e0d6db9-9e9d-482c-8042-9502c83af570)
