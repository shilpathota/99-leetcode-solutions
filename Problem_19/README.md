# Find the Index of the First Occurrence in a String

### Leet Code Link - https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/

### Complexity - Easy

### Description
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 

#### Example 1:

**Input:** haystack = "sadbutsad", needle = "sad"

**Output:** 0

**Explanation:** "sad" occurs at index 0 and 6.

The first occurrence is at index 0, so we return 0.
#### Example 2:

**Input:** haystack = "leetcode", needle = "leeto"
**Output:** -1
**Explanation:** "leeto" did not occur in "leetcode", so we return -1.
 

#### Constraints:

1 <= haystack.length, needle.length <= 104

haystack and needle consist of only lowercase English characters.

### Solution
#### My Solution to this problem
<pre>
  1. if needle length is greater than haystack length then return -1
  2. loop through the haystack length removing needle length.
  3. if index of haylength + needle length less than haystack length and if the substring of haystack is same as  needle then return the index
  4. else loop through the elements and once out of loop return -1                  
</pre>
The Complexity of this algorithm is O(N*M)
![image](https://github.com/user-attachments/assets/ae53b740-a10f-4d60-97ae-1e37e07adad9)

#### Sliding window solution
<pre>
Declare m and n as variables, and initialize them with the length of needle and haystack respectively.

Declare the window_start variable, and initialize it with 0. Now, iterate window_start till starting index of the last substring of length m, i.e till n - m.

For each window_start, iterate variable i from 0 to m - 1. Check if the i  th character in the window i.e index window_start + i is equal to the i th  character in the needle, if yes, then increment i by 1. If not, reset window_start to window_start + 1.

If all the i  th  characters in the window are equal to the i th  characters of needle, then return the window_start.

If we are done iterating over all values of window_start and none of them return a match, then return -1.
</pre>
This has complexity of 

**Time complexity:** O(nm). For every window_start, we may have to iterate at most m times. There are n−m+1 such window_start's. Thus, it is O((n−m+1)⋅m), which is O(nm).

One example where the worst case occurs is when needle is "aaaaab", while haystack is all a's (Let's say, "aaaaaaaaaa"). In this, we always have to take the last character of needle into comparison to conclude that the current m-substring is not equal to needle. Thus, we will have to iterate m times for every window_start.

**Space complexity:** O(1).

There are a handful of variables in the code (m, n, i, window_start), and all of them use constant space, hence, the space complexity is constant.

#### 
