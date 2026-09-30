class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        hold = -prices[0]
        cash = 0

        for price in prices[1:]:
            prev_hold = hold
            hold = max(hold, -price)
            cash = max(cash, prev_hold+price)
        return cash
