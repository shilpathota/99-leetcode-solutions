# Number of Islands

## Leet Code Link - https://leetcode.com/problems/number-of-islands/description/

## Complexity - Medium

## Description
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

#### Example 1:
```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```
#### Example 2:
```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 ```

#### Constraints:
```
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
```

---
## Solution
We have to use graphs in this problem as we should get the elements of all connected graphs. For this, we can use DFS or BFS. Here is one of the iterative approach where we use BFS algorithm.

```java
class Solution {
    HashSet<String> visit = new HashSet<>();
    int count=0;
    public void bfs(int row, int col, char[][] grid){
        int r_length = grid.length;
        int c_length = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row,col});
        visit.add(String.valueOf(row)+','+String.valueOf(col));
        while(!q.isEmpty()){
            int[] current = q.poll();
            int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
            for(int[] eachdir : directions){
                int newr = eachdir[0]+current[0];
                int newc=eachdir[1]+current[1];
                if((newr>=0 &&newr<r_length )
                &&(newc>=0 &&newc<c_length )
                && !visit.contains(String.valueOf(newr)+','+String.valueOf(newc))
                && grid[newr][newc]=='1'){
                    visit.add(String.valueOf(newr)+','+String.valueOf(newc));
                    q.add(new int[]{newr,newc});
                }
            }
        }
    }
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        int r_length = grid.length;
        int c_length = grid[0].length;

        for(int i=0;i<r_length;i++){
            for(int j=0;j<c_length;j++){
                if(grid[i][j]=='1' && !visit.contains(String.valueOf(i)+','+String.valueOf(j))
                ){
                    bfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }
}
```
Time Complexity : O(M*N)

Space Complexity : O(M*N)

USing DFS recursive approach
```java
class Solution {
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
```
Time complexity : O(M×N) where M is the number of rows and
N is the number of columns.

Space complexity : worst case O(M×N) in case that the grid map
is filled with lands where DFS goes by M×N deep.
