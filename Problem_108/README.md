# Word Break

## Leet code Link - https://leetcode.com/problems/word-break/description/

## Complexity - Medium

## Description
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

#### Example 1:
```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```
#### Example 2:
```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
```
#### Example 3:
```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 ```

#### Constraints:
```
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
```

---

## Solution
We can solve this problem in multiple ways where we have to compare with each and every subset with the words in dict and match them. But this increases the complexity

Instead we can do reverse, Say we compare each value of dictionary starting from the string starting position and if it matches any of the work then move the index to word length. 

What if we compare the words from the end instead of beginning and store the result in boolean array which says tru or false. This way I dont need to clculate it again.

So we use a boolean array which we mark true if the value matches with the word and if the array character is also true which is added by word length. For Example, dp[0] is true if dp[4] is alread ytru which means the rest of the string is already valdiated.

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[s.length()]=true;

        for(int i=s.length()-1;i>=0;i--){
            for(String word : wordDict){
                if(i+word.length()<=s.length() &&s.substring(i,i+word.length()).equals(word)){
                    dp[i] = dp[i+word.length()];
                }
                if(dp[i]){
                    break;
                }
            }
        }
        return dp[0];
    }
}
```
#### Cmplexity
Given n as the length of s, m as the length of wordDict, and k as the average length of the words in wordDict,

Time complexity: O(n⋅m⋅k)

The logic behind the time complexity is identical to the previous approach. It costs us O(m⋅k) to calculate each state, and we calculate O(n) states in total.

Space complexity: O(n)

We use an array dp of length n.
