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

This function splits the array into two halves and recursively finds the longest common prefix for each half<br/>
1. We start by checking if the input array strs is null or empty. If it is, we return an empty string as there's no common prefix to find.
2. We define a recursive function longestCommonPrefix that takes the array of strings, a left index, and a right index.
3. The purpose of this function is to recursively divide the array into smaller subarrays until we reach individual strings, and then merge the results to find the common prefix.
4. If the left index equals the right index (left == right), it means we are looking at a single string. In this case, we return the string itself as the common prefix.
5. If the left index does not equal the right index, we find the midpoint of the current subarray (mid = (left + right) / 2).
6. We then recursively find the longest common prefix for the left half (lcpLeft) and the right half (lcpRight) of the subarray.
7. After obtaining the common prefixes for the left and right halves, we merge them by finding the common prefix between lcpLeft and lcpRight using a helper function commonPrefix which is defined above.

![image](https://github.com/shilpathota/99-leetcode-solutions/assets/36531617/04378486-4886-45b6-b7a3-45e33fe176fd)

Let us build the pseudo code around this to have a better idea


