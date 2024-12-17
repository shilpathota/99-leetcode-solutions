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

#### Complexity
Time complexity : O(2n)=O(n). In the worst case each character will be visited twice by i and j.

Space complexity : O(min(m,n)). Same as the previous approach. We need O(k) space for the sliding window, where k is the size of the Set. The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.

there is other efficient and intersting solution using two pointers where instead of incrementing the i pointer we make the pointer move to the value that is in map. In map we store the value of next index. Which means if the character is already present the i is moved to the character next index so it does not iterate through all the elements again.

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max_w=0;
        HashMap<Character,Integer> map = new HashMap<>();
        int i=0;
        for(int j=0;j<s.length();j++){
            if(map.containsKey(s.charAt(j))){
                i=Math.max(map.get(s.charAt(j)),i);
            }
            max_w = Math.max(max_w,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return max_w;
    }
}
```
#### complexity
Time complexity : O(n). Index j will iterate n times.

Space complexity : O(min(m,n)). Same as the previous approach.


