class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        hold = -prices[0]
        sold = float("-inf")
        rest = 0
        for price in prices:
            old_hold, old_sold, old_rest = hold, sold, rest

            hold = max(old_hold, old_rest - price)
            rest = max(old_rest, old_sold)
            sold = old_hold + price

        return max(sold, rest)
