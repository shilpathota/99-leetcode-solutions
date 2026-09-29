class Solution:
    def merge(self, nums1: list[int], m: int, nums2: list[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        j = n - 1 
        i = m - 1
        k = m + n - 1
        while j >= 0:
            if i>= 0 and nums1[i] > nums2[j]: 
                nums1[k] = nums1[i]
                i = i - 1  
            else:
                nums1[k] = nums2[j]
                j = j - 1
            k = k - 1
