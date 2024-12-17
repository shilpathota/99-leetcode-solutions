# Add Digits

## Leet code Link - https://leetcode.com/problems/add-digits/description/

## Complexity - Easy

## Description

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

 

#### Example 1:
```
Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.
```
#### Example 2:
```
Input: num = 0
Output: 0
 ```

#### Constraints:
```
0 <= num <= 231 - 1
 ```
---
## Solution
We can iterate over each digit by having %10 and then dividing by 10 we get the remianing digits seperated. 

We can repeat this process until we get single digit.
```java
class Solution {
    public int addDigits(int num) {
        int digitalRoot = 0;
        while (num > 0) {
            digitalRoot += num % 10;
            num = num / 10;
            
            if (num == 0 && digitalRoot > 9) {
                num = digitalRoot;
                digitalRoot = 0;  
            }    
        }     
        return digitalRoot;
    }
}
```

Follow up: Could you do it without any loop/recursion in O(1) runtime?
Formula for the Digital Root

There is a known formula to compute a digital root in a decimal numeral system

dr 
10
​
 (n)=0,if n=0

dr 
10
​
 (n)=9,if n=9k

dr 
10
​
 (n)=nmod9,if n
!
=9k

How to derive it? Probably, you already know the following proof from school, where it was used for divisibility by 9: "The original number is divisible by 9 if and only if the sum of its digits is divisible by 9". Let's revise it briefly.

The input number could be presented in a standard way, where d 
0
​
 ,d 
1
​
 ,..d 
k
​
  are digits of n:

n=d 
0
​
 +d 
1
​
 ⋅10 
1
 +d 
2
​
 ⋅10 
2
 +...+d 
k
​
 ⋅10 
k
 

One could expand each power of ten, using the following:

10=9⋅1+1
100=99+1=9⋅11+1
1000=999+1=9⋅111+1
...
10 
k
 =1 
k times
00..0
​
 
​
 = 
k times
99..9
​
 
​
 +1=9⋅ 
k times
11..1
​
 
​
 +1

That results in

n=d 
0
​
 +d 
1
​
 ⋅(9⋅1+1)+d 
2
​
 ⋅(9⋅11+1)+...+d 
k
​
 ⋅(9⋅ 
k times
11..1
​
 
​
 +1)

and could be simplified as

n=(d 
0
​
 +d 
1
​
 +d 
2
​
 +...+d 
k
​
 )+9⋅(d 
1
​
 ⋅1+d 
2
​
 ⋅11+...+d 
k
​
 ⋅ 
k times
11..1
​
 
​
 )

The last step is to take the modulo from both sides:

nmod9=(d 
0
​
 +d 
1
​
 +d 
2
​
 +...+d 
k
​
 )mod9

and to consider separately three cases: the sum of digits is 0, the sum of digits is divisible by 9, and the sum of digits is not divisible by nine:

dr 
10
​
 (n)=0,if n=0

dr 
10
​
 (n)=9,if n=9k

dr 
10
​
 (n)=nmod9,if n
!
=9k

```java
class Solution {
    public int addDigits(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}
```
though two last cases could be merged into one

dr 
10
​
 (n)=0,if n=0

dr 
10
​
 (n)=1+(n−1)mod9,if n
!
=0

```java
class Solution {
    public int addDigits(int num) {
        return num == 0 ? 0 : 1 + (num - 1) % 9;
    }
}
```
### Complexity
Time Complexity: O(1).

Space Complexity: O(1).
