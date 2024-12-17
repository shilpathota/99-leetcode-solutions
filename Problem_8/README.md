# Longest Substring Without Repeating Characters

## Leet Code Link - https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
## Complexity - Medium

## Description
Given a string s, find the length of the longest 
substring
 without repeating characters.

 

#### Example 1:
```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```
#### Example 2:
```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```
#### Example 3:
```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 ```

#### Constraints:
```
0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
```
---
## Solution
WE know that we have to track the characters that is already visited. For this, we make use of Set.

If the character is already present in set then we increment the left pointer and then if the character is not present then increment right pointer and also calculate max length.

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max_w=0;
        char[] sc=s.toCharArray();
        if(sc.length<2){
            return sc.length;
        }
        int l=0;int r=0;
        HashSet sett = new HashSet<Character>();
        while(r<sc.length){
            if(sett.contains(sc[r])){
                sett.remove(sc[l]);
                l++;
            }else{
            sett.add(sc[r]);
            max_w=Math.max(r-l+1,max_w);
            r++;
            }
        }

        return max_w;
    }
}
```
