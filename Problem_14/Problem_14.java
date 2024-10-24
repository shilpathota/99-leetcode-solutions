class Solution {
    public int mySqrt(int x) {
        if(x==0||x==1) return x;
        long left = 1;
        long right = x/2; long result = 0;
        while(left <= right){
            int middle = (int) Math.floor((left + right)/2);
            result = (long) middle * middle;
            if((long) x == result){
                return middle;
            }
            else if((long) x > result){
                left = middle + 1;
            } else{
                right = middle - 1;
            }
        }
        return (int) right;
    }
}
