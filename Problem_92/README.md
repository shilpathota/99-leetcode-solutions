# Equal Row and Column Pairs

## Leet Code Link - https://leetcode.com/problems/equal-row-and-column-pairs/description/

## Complexity - Medium

## Description
Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

 

#### Example 1:
<img src = "https://assets.leetcode.com/uploads/2022/06/01/ex1.jpg" />

```
Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
```
#### Example 2:
<img src = "https://assets.leetcode.com/uploads/2022/06/01/ex2.jpg" />

```
Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
 ```

#### Constraints:

```
n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
```
---
## Solution
Here we can go over each row and column and compare with the values of row with each column by iterating over the row in the inner loop.

```java
class Solution {
    public int equalPairs(int[][] grid) {
        int count = 0, n = grid.length;
        
        // Check each row r against each column c.
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                boolean match = true;

                // Iterate over row r and column c.
                for (int i = 0; i < n; ++i) {
                    if (grid[r][i] != grid[i][c]) {
                        match = false;
                        break;
                    }
                }

                // If row r equals column c, increment count by 1.
                count += match ? 1 : 0;
            }
        }
        
        return count;
    }
}
```

#### Complexity
Let n×n be the size of grid.

Time complexity: O(n ^
3
 )

There are a total of O(n ^
2
 ) pairs when iterating over each row R and column C. Traversing each element in R and C takes O(n) time.
Space complexity: O(1)

we are use constant amount of extra space to store the answer count.

Other best possible way is using hashmap. First loop through the row and convert each row to string and add as a key and the count.

Now loop through the column element and store it and check if the value is preent in hashmap and ncrement the count

```java
class Solution {
    public int equalPairs(int[][] grid) {
        HashMap<String,Integer> map = new HashMap<>();
        int count = 0;
        for(int i=0;i<grid.length;i++){
            String row = Arrays.toString(grid[i]);
            map.put(row,1+map.getOrDefault(row, 0));
        }
        for(int i=0;i<grid.length;i++){
            int[] col = new int[grid.length];
            for(int j=0;j<grid[0].length;j++){
                col[j] = grid[j][i];
            }
            count += map.getOrDefault(Arrays.toString(col), 0);
        }
        return count;
    }
}
```
#### Complexity 
Let n×n be the size of grid.

Time complexity: O(n ^
2
 )

We iterate over each row and column only once, converting one array of length n into a hashable object takes O(n) time.
Operations like adding or checking on hash map take O(1) time.
Space complexity: O(n ^
2
 )

We store each row of the grid in the hash map, in the worst-case scenario, row_counter might contains n distinct rows of length n.
