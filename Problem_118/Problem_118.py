class Solution:
    def maxProfit(self, k: int, prices: list[int]) -> int:
        n = len(prices)

        if n == 0 or k == 0:
            return 0

        # With n days, at most n // 2 complete transactions are possible.
        # If k reaches that limit, this is the unlimited-transactions problem.
        if k >= n // 2:
            return sum(
                max(0, prices[day] - prices[day - 1])
                for day in range(1, n)
            )

        # buy[t]: best value while holding the stock for transaction t.
        # sell[t]: best profit after completing at most t transactions.
        buy = [float("-inf")] * (k + 1)
        sell = [0] * (k + 1)

        for price in prices:
            for transaction in range(1, k + 1):
                buy[transaction] = max(
                    buy[transaction],
                    sell[transaction - 1] - price,
                )

                sell[transaction] = max(
                    sell[transaction],
                    buy[transaction] + price,
                )

        return sell[k]
