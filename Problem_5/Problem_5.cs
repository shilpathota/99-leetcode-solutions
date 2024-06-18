public class Solution {
    public int RomanToInt(string s) {
                Dictionary<char, int> m = new Dictionary<char,int>();
        
        m.Add('I', 1);
        m.Add('V', 5);
        m.Add('X', 10);
        m.Add('L', 50);
        m.Add('C', 100);
        m.Add('D', 500);
        m.Add('M', 1000);
        
        int ans = 0;
        
        for (int i = 0; i < s.Length; i++) {
            if (i < s.Length - 1 && m[s[i]] < m[s[i + 1]]) {
                ans -= m[s[i]];
            } else {
                ans += m[s[i]];
            }
        }
        
        return ans;
    }
}
