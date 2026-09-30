class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        hold = -prices[0]
        cash = 0

        for price in prices[1:]:
            old_hold = hold
            old_cash = cash

            hold = max(hold, old_cash - price)
            cash = max(old_cash, old_hold + price)

        return cash
