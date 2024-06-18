public class Solution {
    public bool IsValid(string s) {
                var stack = new char[s.Length];
        int p = -1;
        var dict = new Dictionary<char, char>() 
        {
            {')', '('},
            {'}', '{'},
            {']', '['}
        };

        for(int i = 0; i < s.Length; i++)
        {
            if (!dict.ContainsKey(s[i]))
            {
                p++;
                stack[p] = s[i];
            }
            else if (p >= 0 && p < s.Length && dict[s[i]] == stack[p])
            {
                p--;
            }
            else
                return false;
        }
        return p == -1;
    }
}
