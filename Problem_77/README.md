# Maximum Swap

## Leet Code Link - https://leetcode.com/problems/maximum-swap/description/

## Complexity - Medium

## Description

You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.

 

#### Example 1:
```
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
```
#### Example 2:
```
Input: num = 9973
Output: 9973
Explanation: No swap.
 ```

#### Constraints:
```
0 <= num <= 108
```
---
## Solution
We need to maximize the value of a given integer by swapping any two digits, but we can do this only once. We aim to figure out the best two digits to swap so that the resulting number is as large as possible.

One approach would be to consider all possible swaps by swapping each pair of digits and returning the largest resulting integer.

We convert the number to a string so that individual digits can be easily accessed and manipulated. This allows us to treat the number as an array of characters, making it easier to swap positions without the complexities involved in extracting digits mathematically. Once in string form, we swap every digit with each digit after it to check all possible outcomes. After each swap, we convert the modified string back to an integer and keep track of the largest value we encounter.

```java
class Solution {

    public int maximumSwap(int num) {
        String numStr = Integer.toString(num); // Convert num to string for easy manipulation
        int n = numStr.length();
        int maxNum = num; // Track the maximum number found

        // Try all possible swaps
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                char[] numeral = numStr.toCharArray(); // Convert string to char array for swapping

                // Swap digits at index i and j
                char temp = numeral[i];
                numeral[i] = numeral[j];
                numeral[j] = temp;

                int tempNum = Integer.parseInt(new String(numeral)); // Convert the char array back to integer

                maxNum = Math.max(maxNum, tempNum); // Update maxNum if the new number is larger
            }
        }

        return maxNum; // Return the largest number after all possible swaps
    }
}
```
But the complexity is O(n^2) for Time complexity and space complexity is O(n)

There is other two pass solution where we scan from right to left and record the max number and then scan left to see if the number is less than max and swap it if found

```java
class Solution {

    public int maximumSwap(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;
        int[] maxRightIndex = new int[n];

        maxRightIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            maxRightIndex[i] = (numArr[i] > numArr[maxRightIndex[i + 1]])
                ? i
                : maxRightIndex[i + 1];
        }

        for (int i = 0; i < n; ++i) {
            if (numArr[i] < numArr[maxRightIndex[i]]) {
                char temp = numArr[i];
                numArr[i] = numArr[maxRightIndex[i]];
                numArr[maxRightIndex[i]] = temp;
                return Integer.parseInt(new String(numArr));
            }
        }

        return num;
    }
}
```
the time complexity is O(N) and space complexity is O(N) but we are making two passes which is two iterations.

 Let's see if we can reduce our approach by using a pass to record the last occurrence of each digit in the given integer, and then use that information to find an optimal swap (if one exists).

 We can reduce one pass by having some pointer to store the max index and then the swap index which is potentially good to swap while we iterate from right to left

 ```java
class Solution {
    public int maximumSwap(int num) {
        char[] numst = Integer.toString(num).toCharArray();
        int n = numst.length-1;
        int max_i=-1;int swap1=-1; int swap2=-1;
        for(int i=n;i>=0;i--){
            if(max_i==-1||numst[i]>numst[max_i]){
                max_i=i;
            }
            else if(numst[i]<numst[max_i]){
                swap1=i;
                swap2=max_i;
            }
        }
        if(swap1!=-1&&swap2!=-1){
            char temp= numst[swap1];
            numst[swap1]=numst[swap2];
            numst[swap2]=temp;
        }

        return Integer.parseInt(new String(numst));
        
    }
}
```
#### complexity
Let n be the number of digits in the input number.

Time complexity: O(n)

Converting the integer num to its string representation takes O(n).

The loop iterates over the string once from right to left, performing constant-time operations for each character, making the loop cost O(n).

Swap runs in constant time O(1).

Converting the modified string back to an integer takes O(n) time.

Thus, the overall time complexity is dominated by the traversal and conversions, giving us O(n).

Space complexity: O(n)

The numStr variable is a string representation of the input number, which requires O(n) space to store.

The other variables (maxDigitIndex, swapIdx1, swapIdx2) require O(1) space since they are just integer indices.

Therefore, the overall space complexity is O(n), mainly due to the string representation of the number.

 
