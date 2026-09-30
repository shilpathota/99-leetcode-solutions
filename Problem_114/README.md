# 122. Best Time to Buy and Sell Stock II

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104

## Solution

<img width="901" height="167" alt="image" src="https://github.com/user-attachments/assets/22ecaeb9-c757-4b22-aa22-3c4f8e49f0fe" />

### Greedy Intution

<img width="977" height="495" alt="image" src="https://github.com/user-attachments/assets/7d9f7b14-14a1-4ce1-bb58-3e7f204b49d7" />

### dp INTUTION

<img width="970" height="661" alt="image" src="https://github.com/user-attachments/assets/fe90c55e-54b7-4eb2-8332-90930bb8cc09" />

### Oneliner Memory

<img width="938" height="422" alt="image" src="https://github.com/user-attachments/assets/ce5d3819-9ea4-4ccb-b31d-0f02ca8db342" />

