# Lexicographical Numbers

## Leet Code Link -https://leetcode.com/problems/lexicographical-numbers/description/

## Complexity - Medium

## Description
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

 

#### Example 1:
```
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
```
#### Example 2:
```
Input: n = 2
Output: [1,2]
 ```

#### Constraints:
```
1 <= n <= 5 * 104
```
---
## Solution
this is lexicographical order which means the first digit itertively appends till the limit is reached. If you imagine a graph. this is DFS algorithm.

```java
class Solution {
    List<Integer> dfslist = new ArrayList<>();
    public void DFS(int start,int n){
        if(start > n) return;
        int currentNumber = start;
        dfslist.add(currentNumber);
        for(int i=0;i<=9;i++){
            int nextnum = currentNumber*10+i;
            if(nextnum>n){
                break;
            }
            else{
                DFS(nextnum,n);
            }
        }
    }
    public List<Integer> lexicalOrder(int n) {
        for(int start = 1; start<=9;start++){
            DFS(start,n);
        }
        return dfslist;
    }
}
```
Time Complexity: O(n)

The algorithm generates all numbers from 1 to n in lexicographical order. Each number is visited exactly once and added to the result list. The total number of operations is proportional to the number of elements generated, which is n.

Space Complexity: O(log 
10
​
 (n))

We only consider the recursion stack depth. The depth of recursion is proportional to the number of digits d in n. Given that the maximum value for n is 50,000, the maximum number of digits d is 5. Thus, the recursion stack depth and corresponding space complexity is O(d), which simplifies to O(log 
10
​
 (n)), but with a maximum constant value of 5 for practical constraints. It can also be argued as O(1). This is because, when substituting n as 50,000, the result is approximately 5 (specifically 4.698970004336), which is extremely small and does not significantly affect the overall complexity in this range.

 There is one iterative approach

 ```java
class Solution {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> lexicographicalNumbers = new ArrayList<>();
        int currentNumber = 1;

        // Generate numbers from 1 to n
        for (int i = 0; i < n; ++i) {
            lexicographicalNumbers.add(currentNumber);

            // If multiplying the current number by 10 is within the limit, do it
            if (currentNumber * 10 <= n) {
                currentNumber *= 10;
            } else {
                // Adjust the current number by moving up one digit
                while (currentNumber % 10 == 9 || currentNumber >= n) {
                    currentNumber /= 10; // Remove the last digit
                }
                currentNumber += 1; // Increment the number
            }
        }

        return lexicographicalNumbers;
    }
}
```
Time Complexity: O(n)

The algorithm generates numbers in lexicographical order and iterates up to n times to populate the lexicographicalNumbers array. Each iteration involves constant-time operations (checking conditions and updating currentNumber). Thus, the time complexity is linear in terms of n.

Space Complexity: O(1)

The algorithm uses a constant amount of additional space for variables like currentNumber and loop counters. Therefore, the space complexity is O(1).
