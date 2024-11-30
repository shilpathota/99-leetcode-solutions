# 

## Leet code Link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

## Complexity - Easy

## Description

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
#### Example 1:
```plaintext
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
```
#### Example 2:
```plaintext
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 ```

#### Constraints:
```plaintext
1 <= prices.length <= 105
0 <= prices[i] <= 104
```
---
## Solution
Here we can use brute force algorithm by using 2 loops 1 starting from 0 and other starting from 1 compare each `n` with `n-1` and note the difference. 

This takes `n(n-1)/2` complexity which is O(n^2)

The best solution would be in the same loop tracking the min and maxprofit so that we dont need to have a new loop
```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice =Integer.MAX_VALUE; int maxProfit=0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<minPrice) minPrice = prices[i];
            else if(prices[i]-minPrice > maxProfit) maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
}
```
Time Complexity is O(N)

Space Complexity is O(1)

