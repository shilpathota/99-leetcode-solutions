# 1249. Minimum Remove to Make Valid Parentheses

## Leet Code Link - https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/

## Complexity - Medium

## Description

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

#### Example 1:
```plaintext
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
```
#### Example 2:
```plaintext
Input: s = "a)b(c)d"
Output: "ab(c)d"
```
#### Example 3:
```plaintext
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
```
#### Constraints
```plaintext
1 <= s.length <= 105
s[i] is either '(' , ')', or lowercase English letter.
```
---
## Solution 
We use stack to push and pop the string so that we see if it is balanced. 
```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stch = new Stack<Integer>();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) == '(') {
                stch.push(i);
            } if (s.charAt(i) == ')') {
                if (stch.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stch.pop();
                }
            }
        }
        while(stch.size()>0){
            indexesToRemove.add(stch.pop());
        }
         StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

Time Complexity is O(N)

### Solution 2-
```java
class Solution {

    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }  
        return sb;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }
}
```
