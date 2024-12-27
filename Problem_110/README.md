# Valid Parenthesis String

## Leet code link - https://leetcode.com/problems/valid-parenthesis-string/description/

## Complexity - Medium

## Description
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

- Any left parenthesis '(' must have a corresponding right parenthesis ')'.
- Any right parenthesis ')' must have a corresponding left parenthesis '('.
- Left parenthesis '(' must go before the corresponding right parenthesis ')'.
- '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

#### Example 1:
```
Input: s = "()"
Output: true
```
#### Example 2:
```
Input: s = "(*)"
Output: true
```
#### Example 3:
```
Input: s = "(*))"
Output: true
 ```

#### Constraints:
```
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
```

 ---
 ## solution
 <img src = "blob:https://leetcode.com/38708f43-6a77-4285-8db5-e6a8f5a6a44c" />
 
-  Initialize two variables, openCount and closeCount, to keep track of the number of open and close parentheses (or asterisks) encountered so far.
- Calculate the length of the input string s and store it in the variable length.
- Traverse the string from both ends simultaneously using a single loop:
- Iterate over the indices i from 0 to length (inclusive).
- For each index i:
- If the character at index i is '(' or '*', increment openCount.
- Otherwise, decrement openCount.
- If the character at index length - i is ')' or '*', increment closeCount.
- Otherwise, decrement closeCount.
- If at any point during the loop, either openCount or closeCount becomes negative, it means there are more closing parentheses than open parentheses (or asterisks), which makes the string invalid. In this case, return false.
- After the loop finishes traversing the entire string without returning, openCount and closeCount are non-negative, which means that the string is valid, so return true.

```java
class Solution {
    public boolean checkValidString(String s) {
        int openCount = 0;
        int closeCount = 0;
        int length = s.length()-1;
        for(int i=0;i<=length;i++){
            if(s.charAt(i)=='('||s.charAt(i)=='*'){
                openCount++;
            }else{
                openCount--;
            }
            if(s.charAt(length-i)==')'||s.charAt(length-i)=='*'){
                closeCount++;
            }else{
                closeCount--;
            }
                    if(openCount < 0 || closeCount < 0){
                return false;
            }
        }

                    return true;
    }
}
```
#### Complexity
Let n be the length of the input string.

Time complexity: O(n)

The time complexity is O(n), as we iterate through the string once.

Space complexity: O(1)

The space complexity is O(1), as we use a constant amount of extra space to store the openCount and closeCount variables.
