# Merge Strings Alternately

## Leet Code Link - https://leetcode.com/problems/merge-strings-alternately/description/

## Complexity - Easy

## Description

You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

 

#### Example 1:
```plaintext
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
```
#### Example 2:
```plaintext
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s
```
#### Example 3:
```plaintext
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
 ```

#### Constraints:
```plaintext
1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
```
---
## Solution
My intution on looking t the problem is iterate both the words and get the characters until one of them run out of characters.

then add those characters which are left in the other word outside the loop.

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int i=0;int j=0;
        StringBuilder build = new StringBuilder();
        while(i!=word1.length()&&j!=word2.length()){
            build.append(word1.charAt(i));
            build.append(word2.charAt(j));
            i++;
            j++;
        }
        while(i<word1.length()){
            build.append(word1.charAt(i));i++;
        }
        while(j<word2.length()){
            build.append(word2.charAt(j));j++;
        }
        return build.toString();
    }
}
```
#### Complexity
Here, i is the length of word1 and j is the length of word2.

Time complexity: O(i+j)

We iterate over word1 and word2 once pushing their letters into result. It would take O(i+j) time.
Space complexity: O(1)

Without considering the space consumed by the input strings (word1 and word2) and the output string (result), we do not use more than constant space.

We can also do with single loop but still complexity does not change but simplifies the code

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < m) {
                result.append(word1.charAt(i));
            }
            if (i < n) {
                result.append(word2.charAt(i));
            }
        }

        return result.toString();
    }
}
```
