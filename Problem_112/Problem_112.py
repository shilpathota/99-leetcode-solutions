class Solution:
    def majorityElement(self, nums: list[int]) -> list[int]:
        freq = {}
        for num in nums:
            freq[num] = freq.get(num, 0) + 1
        return [num for num,count in freq.items() if count>len(nums)//3]
