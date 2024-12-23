# Zigzag Conversion

## https://leetcode.com/problems/zigzag-conversion/description/

## Complexity - Medium

## Description
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

#### Example 1:
```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```
#### Example 2:
```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
```
#### Example 3:
```
Input: s = "A", numRows = 1
Output: "A"
 ```

#### Constraints:
```
1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
```
---
## solution
We can consider the edge case as what if the length of the string is s and num of rows < or equal to string length then it can returns s directly.

If not we create each row a sringbuilder object and and there is an varibale that determines the change in direction.

If the current row is 0 or numrows -1 then we reverse direction

At the end put all the string builders together

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s; // Edge case
        
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        
        int currentRow = 0;
        boolean goingDown = false;
        
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown; // Change direction
            }
            currentRow += goingDown ? 1 : -1;
        }
        
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        
        return result.toString();
    }
}

```
Time Complexity and Space complexity is O(N)

**Initialize variables:**
- answer, an empty string to store the final result.
- n, length of the input string.
- charsInSection, to store the number of characters in each section we defined.
**Iterate on each row from numRows:**
- index will start at the first character in the current row, i.e. index is equal to currRow.
- We will traverse in the current row and append all characters to the answer string.

First, we append the first character of the current section. If the current row is not the first or last row then we append the second character of the current section. Then update the index to jump to the next section in the current row and repeat this process again.

**Return answer**

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder answer = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);

        for (int currRow = 0; currRow < numRows; ++currRow) {
            int index = currRow;

            while (index < n) {
                answer.append(s.charAt(index));

                // If currRow is not the first or last row
                // then we have to add one more character of current section.
                if (currRow != 0 && currRow != numRows - 1) {
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;

                    if (secondIndex < n) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                // Jump to same row's first character of next section.
                index += charsInSection;
            }
        }

        return answer.toString();
    }
}
```
Here, n is the length of the input string.

Time complexity: O(n).

We iterate over each index of the input only once and perform constant work at each index.

Space complexity: O(1).

We have not used any additional space other than for building the output, which is not counted.
