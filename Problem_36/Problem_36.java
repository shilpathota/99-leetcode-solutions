class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int ROW_L = matrix.length;
        int COL_L= matrix[0].length;

        for(int i=1;i<ROW_L;i++){
            //diagonal
            for(int j=1;j<COL_L;j++){
                     if(matrix[i][j]!=matrix[i-1][j-1]) return false;
            }
        }
        return true;
    }
}
