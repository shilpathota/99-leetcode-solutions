class Solution {
    public int[] decrypt(int[] code, int k) {
        int[] output = new int[code.length];
        int n=code.length;
        int l=0;
        int r=0;
        if(k>0){
                int total_sum=0;

                for(int i=0;i<k;i++){
                        total_sum+=code[(i+1)%(code.length)];
                }
                        output[0]=total_sum;

                for(int i=1;i<code.length;i++){
                    total_sum= total_sum-code[(i)%(code.length)]+code[(i+k)%(code.length)];
                    output[i]=total_sum;
                }
        }else if(k<0){
            int total_sum=0;
            for(int i=k;i<0;i++)
            {
                total_sum += code[(code.length + i) % code.length];
            }
            for(int i=0;i<code.length;i++)
            {
                output[i] = total_sum;
                total_sum -= code[(code.length + i + k) % code.length];
                total_sum += code[i % code.length];
            }
        }else{
            for(int i=0;i<output.length;i++){
                output[i]=0;
            }
        }
        return output;
    }
}
