# Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]<br/>
Output: "fl"<br/>
Example 2:

Input: strs = ["dog","racecar","car"]<br/>
Output: ""<br/>
Explanation: There is no common prefix among the input strings.<br/>
 

<b>Constraints:</b><br/>

1 <= strs.length <= 200<br/>
0 <= strs[i].length <= 200<br/>
strs[i] consists of only lowercase English letters.<br/>

## Solution
### Inference
Lets first understand the problem. We have to find the common prefix among all the list of arrays.<br/>
There are 2 possible steps here to solve this:<br/>
1. Finding the common prefix - Compare the 2 strings by iterating each of the character until the minimum length among 2 strings.
2. Selecting the 2 string to be compared and send to the compare function

Let us analyze the first step:<br/>
Finding the common prefix -<br/>

