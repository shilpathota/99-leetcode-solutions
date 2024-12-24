# Shortest Path in Binary Matrix

## Leet Code link - https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

## Complexity -Medium

## Description
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.

All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.

#### Example 1:

<img src="https://assets.leetcode.com/uploads/2021/02/18/example1_1.png" />

```
Input: grid = [[0,1],[1,0]]
Output: 2
```
#### Example 2:
<img src="https://assets.leetcode.com/uploads/2021/02/18/example2_1.png" />

```
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
```
#### Example 3:
```
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
```
 

#### Constraints:
```
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
```

---
## Solution
As we wanted to get the shortest path we use BFS. We can use Queue for this purpose.

```java
class Solution {
        private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        if(grid[0][0]!=0 || grid[grid.length-1][grid.length-1]!=0) return -1;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0,1});
        visited[0][0]=true;
        while(!queue.isEmpty()){
           int[] cell = queue.remove();
           int row = cell[0]; int col = cell[1]; int dist = cell[2];
           if(row==grid.length-1 && col == grid[0].length-1){
                return dist;
           }
           for(int[] neighbour : getneighbours(row,col,grid)){
                int neirow = neighbour[0];
                int neicol = neighbour[1];
                if(visited[neirow][neicol] == true){
                    continue;
                }
                queue.add(new int[]{neirow,neicol,dist+1});
                visited[neirow][neicol] = true;
           }
        }
        return -1;
    }
    public List<int[]> getneighbours(int row,int col, int[][] grid){
        List<int[]> neighbours = new ArrayList<>();
        for(int i=0;i<directions.length;i++){
            int newrow = row+directions[i][0];
            int newcol = col+directions[i][1];
            if(newrow <0 || newcol <0 || newrow >=grid.length || newcol >= grid[0].length || grid[newrow][newcol]!=0){
                continue;
            }
            neighbours.add(new int[]{newrow,newcol});
        }
        return neighbours;
    }
}
```
#### Complexity 
Let N be the number of cells in the grid.

Time complexity : O(N).

Same as approach 1. Processing a cell is O(1), and each of the N cells is processed at most once, giving a total of O(N).

Space complexity : O(N).

Same as approach 1. The visited set also requires O(N) space; in the worst case, it will hold the row and column of each of the N cells.

