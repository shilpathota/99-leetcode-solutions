# Unique Paths

## Leet code link - https://leetcode.com/problems/unique-paths/description/

## Complexity - Medium

## Description
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

#### Example 1:

<img src ="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" />

```
Input: m = 3, n = 7
Output: 28
```
#### Example 2:
```
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 ```

#### Constraints:
```
1 <= m, n <= 100
```

---
## Solution
Since robot can move either down or right, there is only one path
to reach the cells in the first row: right->right->...->right.

The same is valid for the first column, though the path here is down->down->
...->down.

What about the "inner" cells (m, n)? To such cell one could move
either from the cell on the left (m, n - 1), or from the cell above
(m - 1, n). That means that the total number of paths to move into (m, n) cell
is uniquePaths(m - 1, n) + uniquePaths(m, n - 1).

<img src="https://leetcode.com/problems/unique-paths/Figures/62/inner_cell2.png" />

```java
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
}
```
This solution is not fast enough to pass all the testcases, though it
could be used as a starting point for the DP solution

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for(int[] arr:res){
            Arrays.fill(arr,1);
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                res[i][j] = res[i-1][j]+res[i][j-1];
            }
        }
        return res[m-1][n-1];
    }
}
```
#### Complexity

Time complexity: O(N×M).

Space complexity: O(N×M).
