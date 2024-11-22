class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()>haystack.length()) return -1;
        for(int j=0;j<haystack.length()-needle.length()+1;j++){
            if((j+needle.length())<=haystack.length() &&
                    needle.equals(haystack.substring(j,j+needle.length()))){
                return j;
            }
        }
        return -1;
    }
}
