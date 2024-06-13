class Solution {

  #solution 1
    public int climbStairs(int n) {
        if(n==1||n==2) return n;
        return climbStairs(n-1)+climbStairs(n-2);
    }
  #solution 2
  
}
