class Solution:
    def canJump(self, nums: list[int]) -> bool:
        farthest = 0

        for index, jump_length in enumerate(nums):
            # This position lies outside everything reached so far.
            if index > farthest:
                return False

            farthest = max(farthest, index + jump_length)

            if farthest >= len(nums) - 1:
                return True

        return True
