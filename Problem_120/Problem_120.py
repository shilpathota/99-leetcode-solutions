class Solution:
    def jump(self, nums: list[int]) -> int:
        jumps = 0
        current_end = 0
        farthest = 0

        # Do not process the final index because reaching it requires no new jump.
        for index in range(len(nums) - 1):
            farthest = max(farthest, index + nums[index])

            # The current BFS-like window has been completely examined.
            if index == current_end:
                jumps += 1
                current_end = farthest

        return jumps
