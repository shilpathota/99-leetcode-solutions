class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(String str:strs){
           int[] s = new int[26];
           for(char c : str.toCharArray()){
                s[c-'a']++;
           }
           String key = Arrays.toString(s);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(str);
        }
     return new ArrayList<>(res.values());
    }
}
