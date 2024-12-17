# Defuse the Bomb

## Leet Code Link - https://leetcode.com/problems/defuse-the-bomb/description/
## Complexity - Easy

## Description
You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

 

Example 1:

Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.<br/>
Example 2:

Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0. <br/>
Example 3:

Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.<br/>
 

Constraints:<br/>

n == code.length
1 <= n <= 100
1 <= code[i] <= 100
-(n - 1) <= k <= n - 1
## Solution
As this is circular calculation of the array of elements, we can use sliding window technique to get the optimal solution.<br/>

We are given a circular array code of length n and a key k, we need to update each element in code as follows:

- If k > 0, replace each element with the sum of the next k elements.
- If k < 0, replace each element with the sum of the previous |k| elements.
- If k == 0, replace all elements with 0.
- Since the array is circular, when we go beyond the end, we wrap back to the start using the modulo operator %. For example, i % n keeps an index i within bounds of an array of length n, so if i exceeds n, it wraps back to 0, 1, etc. This lets us navigate the circular array without additional conditions to reset indices.

Given the low constraints on n and k, we can use a simple brute-force approach to simulate the required operation for each index based on k:

- If k is 0, we return an array of size n filled with 0s.
- If k is positive, we replace each element with the sum of the next k elements, using the modulo operator to handle circular bounds.
- If k is negative, we replace each element with the sum of the previous |k| elements, again using the modulo operator for circular bounds.

#### Algorithm
- Create an array result of the same length as code to store the decrypted values.
- If k is 0, return result, as it should contain only zeros.
- Loop through each element in code with index i:
- If k is positive:
- For each j from i + 1 to i + k:
- Add code[j % code.length] to result[i].
- If k is negative:
- For each j from i - |k| to i - 1:
- Add code[(j + code.length) % code.length] to result[i].
- After processing all elements, return result.

```java
class Solution {

    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        // If k is 0, return the result directly.
        if (k == 0) {
            return result;
        }
        for (int i = 0; i < result.length; i++) {
            if (k > 0) {
                // If k is greater than 0, store the sum of next k numbers.
                for (int j = i + 1; j < i + k + 1; j++) {
                    result[i] += code[j % code.length];
                }
            } else {
                // If k is less than 0, store the sum of previous -1*k numbers.
                for (int j = i - Math.abs(k); j < i; j++) {
                    result[i] += code[(j + code.length) % code.length];
                }
            }
        }
        return result;
    }
}
```
#### complexity 
Let n be the size of the given code array.

Time Complexity: O(n⋅∣k∣)

The outer loop iterates over each element in code, so it runs n times, where n is the length of code. For each element, the inner loop runs ∣k∣ times (either forward or backward, depending on the value of k). Therefore, the overall time complexity is O(n⋅∣k∣).

Space complexity: O(n)

The algorithm creates a new array result of the same length as code to store decrypted values, resulting in a space complexity of O(n).

We can use sliding window algorithm 

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        if(k==0) return result;
        int start =1; int end =k;int sum =0;
        if(k<0){
            start = code.length-Math.abs(k);
            end = code.length-1;
        }
        for(int i=start;i<=end;i++){
            sum+=code[i];
        }
        for(int i=0;i<code.length;i++){
            result[i]=sum;
            sum-=code[start%code.length];
            sum+=code[(end+1)%code.length];
            start++;
            end++;
        }
        return result;
    }
}
```
#### complexity

Let n be the size of the given code array.

Time Complexity: O(n)

The first loop calculates the initial sum for the window, which takes O(∣k∣) time. The second loop iterates through each element in the code array, which takes O(n) time. Therefore, the overall time complexity is O(∣k∣+n). In the worst case, |k| can be as large as n, and the time complexity simplifies to O(n).

Space complexity: O(n)

The algorithm creates a new array result of the same length as code to store decrypted values, resulting in a space complexity of O(n).
