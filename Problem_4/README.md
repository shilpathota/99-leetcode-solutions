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
Create a seperate function which does this job of comparing 2 strings.<br/>
So we have to pass 2 strings as parameters to this function<br/>


How many characters to iterate?<br/>
We do not have to iterate through all of the characters in the string<br/>
Find the minimum length of the strings and whichever is minimum, we can consider that length to iterate<br/>


Iterate until the charcters do not match. If it does not match, we return the substring that matched till now. else return the substring until minimum length if the iterations are completed.<br/>
![image](https://github.com/shilpathota/99-leetcode-solutions/assets/36531617/28254904-11b1-44da-82b1-77786e9b7de1)


Next we will go to second step - Selecting 2 strings<br/>
For this we can use divide and conquer<br/>

