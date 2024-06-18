public class Solution {
    public string LongestCommonPrefix(string[] strs) {
                StringBuilder res = new StringBuilder();
        Array.Sort(strs);
        String first = strs[0];
        String last = strs[strs.Length - 1];
        for(int i=0;i<Math.Min(first.Length,last.Length);i++){
            if(first[i]!=last[i]){
                return res.ToString();
            }
            res.Append(first[i]);
        }
        return res.ToString();
    }
}
