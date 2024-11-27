# 125. Valid Palindrome

## Leet Code Link - https://leetcode.com/problems/valid-palindrome/description/

## Complexity - Easy

## Description
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

#### Example 1:
```plaintext
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
```

#### Example 2:
```plaintext
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
```

#### Example 3:
```plaintext
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
```
#### Constraints:
```plaintext
1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
```
---
## Solution
This is clearly a problem to be solved using two pointer.
```java
class Solution {
    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (
                Character.toLowerCase(s.charAt(i)) !=
                Character.toLowerCase(s.charAt(j))
            ) return false;
        }

        return true;
    }
}
```
