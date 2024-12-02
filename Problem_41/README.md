# Product of Array Except Self

## Leet code Link - https://leetcode.com/problems/product-of-array-except-self/description/

## Complexity - Medium

## Desription 
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

#### Example 1:
```plaintext
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```
#### Example 2:
```plaintext
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 ```

#### Constraints:
```plaintext
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
```
 

### Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

---
## Solution

Initially, we might have thought of a brute force algorithm where you have 2 loops and go over each element if it is the same element skip with applying product 

```java
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    prod *= nums[j];
                }
            }
            res[i] = prod;
        }
        return res;
    }
}
```
But the time Complexity here is O(n^2) and space complexity is O(1) as we are not counting the output space.

There is other method where we can divide the total by the current number so that it gets removed from the product but here there should be additional chek of 0. the number is 0 then the product would be 0. If the zerocount is =1 then check if the current number is 0 and then update the prodcut. If more than 1 number is 0 then return new array as the prodcut would be 0

```java
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int prod = 1, zeroCount = 0;
        for (int num : nums) {
            if (num != 0) {
                prod *= num;
            } else {
                zeroCount++;
            }
        }
        
        if (zeroCount > 1) {
            return new int[nums.length]; 
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (zeroCount > 0) {
                res[i] = (nums[i] == 0) ? prod : 0;
            } else {
                res[i] = prod / nums[i];
            }
        }
        return res;
    }
}
```

Time complexity is O(N) and the space complexity is O(1)

Instead we can use prefix and suffix where prefix is the product of all numbers infront of it and suffix is the product of all numbers after it.

```java
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        suff[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            pref[i] = nums[i - 1] * pref[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = nums[i + 1] * suff[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = pref[i] * suff[i];
        }
        return res;
    }
}
```
But here we are additionally using the space for 2 new arrays prefix and suffix. which has time complexity as O(N) but the space complexity also increases to O(N)

### Can we optimize further with space complexity?

Yes, we can do it by not using the extra arrays for prefix and suffix but use output array to accommodate the calculations

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        output[0]=1;
        //storing prefixes
        for(int i=1;i<nums.length;i++){
                output[i] = output[i-1]*nums[i-1];
        }
        //multiply by postfixes
        int postfix = 1;
        for(int i=nums.length-1;i>=0;i--){          
            output[i] *=postfix;
            postfix*=nums[i];
        }
        return output;
    }
}  

```
Here the Time complexity is O(N) and the Space Complexity is O(1)

---
## My Mind Map

![IMG_3129](https://github.com/user-attachments/assets/f171fdf5-6907-4611-8eb7-60765795632b)


