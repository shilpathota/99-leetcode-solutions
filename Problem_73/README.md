# Can Place Flowers

## Leet code Link - https://leetcode.com/problems/can-place-flowers/description/

## Complexity - Easy

## Description
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

 

#### Example 1:
```
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
```
#### Example 2:
```
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 ```

#### Constraints:
```
1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length
```

---

## Solution
As we can see we have to count the number of flowers can be planted based on adjacent zeros and increase the count. If count is greater than n then return true else false.

Also take care of edge conditions where first and last element are 0 and adjaent are zero too

```java
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if the current plot is empty.
            if (flowerbed[i] == 0) {
                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                
                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }
}
```
#### Complexity

Time complexity: O(n). A single scan of the flowerbed array of size n is done.

Space complexity: O(1). Constant extra space is used.
