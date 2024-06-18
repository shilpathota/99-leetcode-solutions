# Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"
Output: true


Example 2:

Input: s = "()[]{}"
Output: true


Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.

# Solution
## Step by Step Analysis
1. Create an empty stack to keep track of opening brackets
2. Loop through every character in the string
3. If the character is an opening bracket, push it onto the stack
4. If the character is a closing bracket and If the stack is empty, there is no matching opening bracket, so return false
5. Otherwise, get the top of the stack and check if it's the matching opening bracket
6.  If it is matching, pop the opening bracket from the stack
7.  Otherwise, the brackets don't match, so return false
8.  When the loop is exited without return then return If the stack is empty, all opening brackets have been closed, so return true Otherwise, there are unmatched opening brackets, so return false

Lets analyze the complexity<br>
<b>Time Complexity</b> The time complexity of the solution is O(n)O(n)O(n), where n is the length of the input string. This is because we traverse the string once and perform constant time operations for each character.

<b>Space Complexity</b> The space complexity of the solution is O(n)O(n)O(n), where n is the length of the input string. This is because the worst-case scenario is when all opening brackets are present in the string and the stack will have to store them all.
