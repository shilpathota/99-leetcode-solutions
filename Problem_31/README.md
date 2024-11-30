# Group Anagrams

## Leet code Link - https://leetcode.com/problems/group-anagrams/description/

## Complexity - Medium

## Description
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

#### Example 1:
```plaintext
Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
```

#### Example 2:
```plaintext
Input: strs = [""]

Output: [[""]]
```

#### Example 3:
```plaintext
Input: strs = ["a"]

Output: [["a"]]
```
#### Constraints
```plaintext
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
```
---
## Solution
The anagrams can be found by the frequency of the characters in the words. Our goal is to create hashmap where key would be the letters with the frequency of characters. This can also be obtained by using sort method

Add all those with the same key which is the output.

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(String str:strs){
           int[] s = new int[26];
           for(char c : str.toCharArray()){
                s[c-'a']++;
           }
           String key = Arrays.toString(s);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(str);
        }
     return new ArrayList<>(map.values());
    }
}
```


