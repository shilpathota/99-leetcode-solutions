# Minimum Add to Make Parentheses Valid

## Leet code Link - https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/

## Complexity - Medium

## Description
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

 

#### Example 1:
```
Input: s = "())"
Output: 1
```
#### Example 2:
```
Input: s = "((("
Output: 3
 ```

#### Constraints:
```
1 <= s.length <= 1000
s[i] is either '(' or ')'.
```
---
## Solution 
As soon as we see parantheses to be validated, we can think of stack where we push and pop to balance. If there is no balance, the stack will not become empty. so we can return the number of values in stack

```java
class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push(s.charAt(i));
            }
            else if(!st.isEmpty()&&s.charAt(i)==')'&&st.peek()=='('){
                st.pop();
            }
            else{
                st.push(s.charAt(i));
            }
        }
        return st.size();
    }
}
```
#### Complexity
Time Complexity is O(N) 

Space Complexity is O(N)

But as this is only counter may be we do not need stack to store the values. instead we can use counter to increment or decrement. 

This way we reduce space complexity to O(1)

```java
class Solution {

    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAddsRequired = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else {
                // If an open bracket exists, match it with the closing one
                // If not, we need to add an open bracket.
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    minAddsRequired++;
                }
            }
        }

        // Add the remaining open brackets as closing brackets would be required.
        return minAddsRequired + openBrackets;
    }
}
```
