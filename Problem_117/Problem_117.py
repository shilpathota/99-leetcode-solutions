class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        hold1 = -prices[0]
        sell1 = 0
        hold2 = -prices[0]
        sell2 = 0

        for price in prices[1:]:
            old_hold1, old_sell1, old_hold2, old_sell2 = hold1, sell1, hold2, sell2

            hold1 = max(old_hold1, -price)
            sell1 = max(old_sell1, old_hold1 + price)
            hold2 = max(old_hold2, old_sell1 - price)
            sell2 = max(old_sell2, old_hold2 + price)
        return sell2
