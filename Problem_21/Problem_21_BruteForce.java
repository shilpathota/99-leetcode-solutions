class Solution {
    public boolean isPalindorme(String s,int i, int j){
        while(i<j){
        if(s.charAt(i)!=s.charAt(j)){
            return false;
        }
        i++;
        j--;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        if(s.length()<=1) return s;
        int len= s.length()-1;int max=Integer.MIN_VALUE;String ans="";
        for(int i=0;i<=len;i++){
            for(int j=len;j>0&&j>i;j--){
                 if(s.charAt(i)==s.charAt(j)&& isPalindorme(s, i, j)){
                    if(max<j-i+1){
                        ans=s.substring(i,j+1);
                        max=ans.length();
                    }
                 }

            }
        }
        return ans==""?String.valueOf(s.charAt(0)):ans;
    }
}
