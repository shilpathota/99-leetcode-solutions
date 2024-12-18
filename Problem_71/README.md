# Add Strings

## Leet code Link - https://leetcode.com/problems/add-strings/description/

## Complexity - Easy

## description

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

 

#### Example 1:
```
Input: num1 = "11", num2 = "123"
Output: "134"
```
#### Example 2:
```
Input: num1 = "456", num2 = "77"
Output: "533"
```
#### Example 3:
```
Input: num1 = "0", num2 = "0"
Output: "0"
 ```

#### Constraints:
```
1 <= num1.length, num2.length <= 104
num1 and num2 consist of only digits.
num1 and num2 don't have any leading zeros except for the zero itself.
```

---

## Solution
WE cannot convert the whole string to integer at once. But can do with digit by digit.

```java
class Solution {
    public String addStrings(String num1, String num2) {
        int n1=0;int n2=0;int i=num1.length()-1;int j=num2.length()-1;
        int carry=0;
        StringBuilder s= new StringBuilder();
        while(i>=0||j>=0){
            n1=i>=0?num1.charAt(i)-'0':0;
            n2=j>=0?num2.charAt(j)-'0':0;
            int val = (n1+n2+carry)%10;
            carry = (n1+n2+carry)/10;
            s.append(val);
            i--;
            j--;
        }
        if(carry!=0) s.append(carry);
        return s.reverse().toString();
    }
}
```
#### Complexity

Time Complexity: O(max(N 
1
​
 ,N 
2
​
 )), where N 
1
​
  and N 
2
​
  are length of nums1 and nums2. Here we do max(N 
1
​
 ,N 
2
​
 ) iterations at most.

Space Complexity: O(max(N 
1
​
 ,N 
2
​
 )), because the length of the new string is at most max(N 
1
​
 ,N 
2
​
 )+1.
