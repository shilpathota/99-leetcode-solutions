class Solution:
    def maxProfit(self, prices: list[int], fee: int) -> int:
        hold = -prices[0]
        cash = 0

        for price in prices:
            old_hold, old_cash = hold, cash
            hold = max(old_hold, old_cash - price)
            cash = max(old_cash, old_hold + price - fee)

        return cash
