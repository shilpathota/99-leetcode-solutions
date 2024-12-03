# Basic Calculator II

## Leet Code Link - https://leetcode.com/problems/basic-calculator-ii/description/

## Complexity - Medium

## Description

Given a string s which represents an expression, evaluate this expression and return its value. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 

#### Example 1:
```plaintext
Input: s = "3+2*2"
Output: 7
```
#### Example 2:
```plaintext
Input: s = " 3/2 "
Output: 1
```
#### Example 3:
```plaintext
Input: s = " 3+5 / 2 "
Output: 5
 ```

#### Constraints:
```plaintext
1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
```
---

## Solution
The solution for this is to calculate the operator precedence where we have * and / with highest precedence over + and -

So we park the operation for later if we encounter the operator + or -

### Algorithm goes as follows:
1. Scan the input string s from left to right and evaluate the expressions based on the following rules

2. If the current character is a digit 0-9 ( operand ), add it to the number currentNumber.
3. Otherwise, the current character must be an operation (+,-,*, /). Evaluate the expression based on the type of operation.
4. Addition (+) or Subtraction (-): We must evaluate the expression later based on the next operation. So, we must store the currentNumber to be used later. Let's push the currentNumber in the Stack.
5. Stack data structure follows Last In First Out (LIFO) principle. Hence, the last pushed number in the stack would be popped out first for evaluation. In addition, when we pop from the stack and evaluate this expression in the future, we need a way to determine if the operation was Addition (+) or Subtraction (-). To simplify our evaluation, we can push -currentNumber in a stack if the current operation is subtraction (-) and assume that the operation for all the values in the stack is addition (+). This works because (a - currentNumber) is equivalent to (a + (-currentNumber)).

6. Multiplication (*) or Division (/): Pop the top values from the stack and evaluate the current expression. Push the evaluated value back to the stack.
7. Once the string is scanned, pop from the stack and add to the result.

```java
class Solution {
    public int calculate(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
```
### Complexity Analysis
Time Complexity: O(n), where n is the length of the string s. We iterate over the string s at most twice.

Space Complexity: O(n), where n is the length of the string s.

### What if we want to reduce the space complexity?

same algorithm can be used without using stack
#### Algorithm
The approach works similar to Approach 1 with the following differences :

1. Instead of using a stack, we use a variable lastNumber to track the value of the last evaluated expression.
2. If the operation is Addition (+) or Subtraction (-), add the lastNumber to the result instead of pushing it to the stack. The currentNumber would be updated to lastNumber for the next iteration.
3. If the operation is Multiplication (*) or Division (/), we must evaluate the expression lastNumber * currentNumber and update the lastNumber with the result of the expression. This would be added to the result after the entire string is scanned.
```java
class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }
}
```
### Complexity Analysis
Time Complexity: O(n), where n is the length of the string s.

Space Complexity: O(1), as we use constant extra space to store lastNumber, result and so on.
