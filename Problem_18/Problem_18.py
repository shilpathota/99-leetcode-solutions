class Solution:
    def removeElement(self, nums: list[int], val: int) -> int:
        i = 0
        j = 0
        while j < len(nums):
            if j>=0 and nums[j] != val:
                nums[i] = nums[j]
                i = i + 1
            j = j + 1
        return i
