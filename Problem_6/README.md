# Plus One

## Leet code Link - https://leetcode.com/problems/plus-one/description/

## Complexity - Easy
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

 

#### Example 1:
```
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
```
#### Example 2:
```
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
```
#### Example 3:
```
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
 ```

#### Constraints:
```
1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
```
## Solution

#### Approach
1. Create an integer pointing to the last element of the array digits
2. While the element at i == 9, set the element at i to '0' (9+1 = 10) and decrement the i pointer.
3. If all the values are 9 upto the first element i.e. 'i' reached position 0, For Example, [9,9,9][9,9,9][9,9,9] then, create a new array of size digits.length + 1 filled with zeros [0,0,0,0][0,0,0,0][0,0,0,0] and set the element at 0th position to 1 ([1,0,0,0][1,0,0,0][1,0,0,0]) and return the new array
4. else set the element at i to digits[i]+1digits[i] + 1digits[i]+1
5. return digits

#### Pictorial Representation
<img src="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_6/Problem_6_1.drawio.png"/><br>

```java
class Solution {
    public int[] plusOne(int[] digits) {
       int n = digits.length;

        //Move along the input array starting from the end
        for (int idx = n - 1; idx >= 0; --idx) {
            //Set all the nines at the end of the array to zeros
            if (digits[idx] == 9) {
                digits[idx] = 0;
            }
            //Here we have the rightmost not-nine
            else {
                // Increase this rightmost not-nine by 1
                digits[idx]++;

                // and the job is done
                return digits;
            }
        }

        // We're here because all the digits are nines
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
```

My solution
```java

class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (digits[i] == 9){
            if (i == 0){
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                return res;
            }
            digits[i] = 0;
            i -= 1;
        }
        digits[i] += 1;
        return digits;
    }
}
```
### Complexity
Time complexity:O(n)

Space complexity:O(n)
