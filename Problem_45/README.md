# Pow(x,n)

## Leet code Link - https://leetcode.com/problems/powx-n/description/

## Complexity - Medium

## Description

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

 

#### Example 1:
```plaintext
Input: x = 2.00000, n = 10
Output: 1024.00000
```
#### Example 2:
```plaintext
Input: x = 2.10000, n = 3
Output: 9.26100
```
#### Example 3:
```plaintext
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
```
 

#### Constraints:
```plaintext
-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
Either x is not zero or n > 0.
-104 <= xn <= 104
```
---

## Solution
On the first look of the problem, I know I should multiple the number so many times as n. So I can loop till n and then numtiply x by itself. But there is negative number in that case I have to divide the result. Also the edge condition is important here which is anything if the power is 0 then the value is 1.

Considering this, instead of looping with for we can use recursion where the same operation is performed.

But this is straight forward and have O(N) as N loops. But if we think of optimization we can write x^n as x^(2*n/2) == (x*x)^(n/2) but for odd case this gives points so we can make this as x*(x^(n-1)/2) == x* (x*x)^(n-1/2). this way we are reducing the operations from n to every loop it reduces by half which is log n operations. Great improvement.

The solution now would be

```java
class Solution {
    public double binaryexp(double x,long n){
    if(n==0) return 1.0000;
        if(n<0){
            n=(-1)*n;
            x = 1/x;
        }
            if(n%2==0){
               return binaryexp(x*x,n/2);
            }
            else{
                return x*binaryexp(x*x,(n-1)/2);
            }
    }
    public double myPow(double x, int n) {
       return binaryexp(x, (long) n);
    }
}
```
#### complexity
Time complexity - O(log n)

Space Complexity - O(1)

##
##
