class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
      
        res = ""
        strs.sort()
        first = strs[0];
        last = strs[-1];
        for i in range(0,min(len(first),len(last))):
            if first[i]!=last[i]:
                return res;
            
            res+=first[i];
        
        return res;
        
