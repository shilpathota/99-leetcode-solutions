class Solution {
    public String longestPalindrome(String s) {
        if(s.length()<=1) return s;
        //Preprocess for even length
        int N = s.length();

        StringBuilder str = new StringBuilder();
        str.append('#');
        for(int i=0;i<N;i++){
            str.append(s.charAt(i)+"#");
        }
        //initialization 
        char[] T = str.toString().toCharArray();
        int[] P = new int[T.length];
        int C=0;
        int R=0;
        int maxLength=0;
        int centerIndex=0;
        //updating the radii
        for(int i=0;i<T.length;i++){
            int mirror = 2*C-i;
            //calculate P[i]
            //if P[i] is within the border of longest substring or 0
            P[i] = R>i ? Math.min((R-i),P[mirror]) : 0;

            //outside the border so it should be atleast P[i]
            while(i + 1 + P[i] < T.length && i - 1 - P[i] >= 0 &&T[i+1+P[i]]==T[i-P[i]-1]){
                P[i]++;
            }

            //if P[i] is outside the border update R and C
            if(P[i]>R){
                C=i;
                R=i+P[i];
            }
            // calculate the maxlength
            if(P[i]>maxLength){
                maxLength = P[i];
                centerIndex = i; 
            }

        }

        int start = (centerIndex-maxLength)/2;
        //return the substring
        return s.substring(start,start+maxLength);
    }
}
