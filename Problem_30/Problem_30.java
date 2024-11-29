class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;

        HashMap<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i=0;i<s.length();i++){
            int sc= map.getOrDefault(Character.valueOf(s.charAt(i)),0);
            map.put(Character.valueOf(s.charAt(i)), sc+1);
            int tc= map.getOrDefault(Character.valueOf(t.charAt(i)),0);
            map.put(Character.valueOf(t.charAt(i)), tc-1);
        }
        for(Integer i:map.values()){
            if(i!=0) return false;
        }
        return true;
    }
}
