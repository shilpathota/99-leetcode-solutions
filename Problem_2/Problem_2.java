class Solution {

  #solution 1
    public int climbStairs(int n) {
        if(n==1||n==2) return n;
        return climbStairs(n-1)+climbStairs(n-2);
    }
  #solution 2

    public int climb(int n,int[] arr){
        if(arr[n]!=-1) return arr[n];
        arr[n] = climb(n-1,arr)+climb(n-2,arr);
        return arr[n];
    }
   
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        for(int i=0;i<=n;i++){
            arr[i]=-1;
        }
        arr[0]=1;
        arr[1]=1;
                if(arr[n]!=-1) return arr[n];

        arr[n] = climb(n,arr);
        return arr[n];
    }

  #solution 3

    public int climbStairs(int n) {
        if (n <= 3) return n;

        int prev1 = 3;
        int prev2 = 2;
        int cur = 0;

        for (int i = 3; i < n; i++) {
            cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }

        return cur;        
    }


}
