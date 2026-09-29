class Solution:
    def removeDuplicates(self, nums: list[int]) -> int:
        j = 1
        i = 1
        while j < len(nums):
            if nums[j] != nums[i-1]:
                nums[i] = nums[j]
                i = i + 1
            j = j + 1
        return i
            
