# Is Subsequence

## Leet code Link - https://leetcode.com/problems/is-subsequence/description/

## Complexity - Easy

## Description
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

 

#### Example 1:
```
Input: s = "abc", t = "ahbgdc"
Output: true
```
#### Example 2:
```
Input: s = "axc", t = "ahbgdc"
Output: false
 ```

#### Constraints:
```
0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 ```

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?

---
## Solution
We use two pointer technique where one pointer to the s and other pointer to t. If the character equals move both i pointing to s and j pointing to t. if not equals move j pointer. Once we are out of loop validate if the value of i is at the end or not

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        char[] sc = s.toCharArray();
        char[] st = t.toCharArray();
        int j=0;int i=0;
        while (i<sc.length&&j<st.length){
            if(sc[i]==st[j]){
                i++;
                j++;
            }
           else{
            j++;
           }
        }
        if(i!=sc.length){
            return false;
        }
        return true;
    }
}
```
Let ∣S∣ be the length of the source string, and ∣T∣ be the length of the target string.

Time Complexity: O(∣T∣)

Space Complexity: O(1)

We replace the recursion with iteration. In the iteration, a constant memory is consumed regardless of the input.
