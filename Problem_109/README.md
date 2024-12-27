# Multiply Strings

## Leet code link - https://leetcode.com/problems/multiply-strings/description/

## Complexity - Medium

## Description
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

 

#### Example 1:
```
Input: num1 = "2", num2 = "3"
Output: "6"
```
#### Example 2:
```
Input: num1 = "123", num2 = "456"
Output: "56088"
 ```

#### Constraints:
```
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
```

---
## Solution
We are given two non-negative integers that are represented as strings and asked to return the product of the two integers, also in the form of a string. There are a few subtle challenges and edge cases that we must consider to solve this problem. So, before determining how to multiply two numbers in string format, let's first consider a simpler variation of the problem: adding two numbers in string format.
We can add two numbers represented as strings by adding digits from the given numbers in each place. The sum of two digits must be between 0 and 18. The ones place is added to the result while the tens place is carried and summed with the next pair of digits. When summing two numbers, the carried digit will always be zero or one. This process can be repeated for each digit, as shown below.

 when we multiply two digits, one from the first number and one from the second number, then their product will have some zeros appended at the end. The number of zeros depends on the place of each digit, and (as demonstrated in the image below) when the result is added to the answer, the trailing zeros do not affect the answer (because any number plus zero is itself).
So it is not necessary for us to append zeros at the end of each result before adding the result to the final answer. Instead, we can directly add the multiplication result at the place where the least significant digit will shift to after to appending some zeros.

As an example, when we multiply two tens place digits, two zeros are appended at the end of the multiplication result, and the result will be added at the hundreds place in the final answer. One more example for clarity, if we multiplied a digit in the thousands place (3 trailing zeros) by a digit in the hundreds place (2 trailing zeros), the product will have 5 trailing zeros (the sum of trailing zeros of each digit) so the result will only affect the hundred thousands place and the millions place in the final answer.

<img src="https://leetcode.com/problems/multiply-strings/Figures/43/Slide32.JPG" />

##### Algorithm
- Reverse both numbers.
- Initialize answer with N+M zeros.
- For each digit at position i in secondNumber:
- For each digit at position j in firstNumber:
- Multiply the digit from secondNumber by the digit from firstNumber and add previously carried value to the multiplication result. The previously carried value can be found at position i + j in the answer.
- Take the remainder of multiplication with 10 to get the ones place digit of the multiplication result.
- Put the last digit at current position (position i + j) in answer.
- Divide the multiplication by 10 to get the new value for carry and add it to answer at the next position. Note, the next position is located at (i + j + 1).
- If the last digit in answer is zero, before reversing answer, we must pop the zero from answer. Otherwise, there would be a leading zero in the final answer.
- Reverse answer and return it.

```java
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
          StringBuilder firstNumber = new StringBuilder(num1);
        StringBuilder secondNumber = new StringBuilder(num2);

        // Reverse both the numbers.
        firstNumber.reverse();
        secondNumber.reverse();

        // To store the multiplication result of each digit of secondNumber with firstNumber.
        int N = firstNumber.length() + secondNumber.length();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            answer.append(0);
        }

        for (int place2 = 0; place2 < secondNumber.length(); place2++) {
            int digit2 = secondNumber.charAt(place2) - '0';

            // For each digit in secondNumber multiply the digit by all digits in firstNumber.
            for (int place1 = 0; place1 < firstNumber.length(); place1++) {
                int digit1 = firstNumber.charAt(place1) - '0';

                // The number of zeros from multiplying to digits depends on the
                // place of digit2 in secondNumber and the place of the digit1 in firstNumber.
                int currentPos = place1 + place2;

                // The digit currently at position currentPos in the answer string
                // is carried over and summed with the current result.
                int carry = answer.charAt(currentPos) - '0';
                int multiplication = digit1 * digit2 + carry;

                // Set the ones place of the multiplication result.
                answer.setCharAt(
                    currentPos,
                    (char) ((multiplication % 10) + '0')
                );

                // Carry the tens place of the multiplication result by
                // adding it to the next position in the answer array.
                int value =
                    (answer.charAt(currentPos + 1) - '0') + multiplication / 10;
                answer.setCharAt(currentPos + 1, (char) (value + '0'));
            }
        }

        // Pop excess 0 from the rear of answer.
        if (answer.charAt(answer.length() - 1) == '0') {
            answer.deleteCharAt(answer.length() - 1);
        }

        answer.reverse();
        return answer.toString();
    }
}
```
#### Complexity
Here N and M are the number of digits in num1 and num2 respectively.

Time complexity: O(M⋅N).

During multiplication, we perform N operations for each of the M digits of the second number, so we need M⋅N time for it.

Space complexity: O(M+N).

The space used to store the output is not included in the space complexity. However, because strings are immutable in Python, Java, and Javascript, a temporary data structure, using O(M+N) space, is required to store the answer while it is updated.

On the other hand, in C++, strings are mutable, so we do not need a temporary data structure to store answer and can update answer directly. Thus, the C++ approach is a constant space solution.
