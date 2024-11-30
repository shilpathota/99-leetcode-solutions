# Toeplitz Matrix

## Leet code Link - https://leetcode.com/problems/toeplitz-matrix/description/

## Complexity - Easy

## Description

Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

#### Example 1:
![image](https://github.com/user-attachments/assets/12d90f22-104c-45c2-a24b-8c37e46d1ec0)

```plaintext
Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: true
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
```

#### Example 2:
![image](https://github.com/user-attachments/assets/4eb9a781-7cce-4151-82ed-90e66f1e983c)

```plaintext
Input: matrix = [[1,2],[2,2]]
Output: false
Explanation:
The diagonal "[1, 2]" has different elements.
```

#### Constraints
```plaintext
m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99
```

---
## Solution
Our goal is to return true if the diagonal elements are equal. If you observe the row index and column index, on increment of the index the value should be equal.

As you loop through row and column compare the index with the neighbour incrementing the index. if does not equal return false.

```java
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
```

#### Complexity
Time Complexity - O(M*N) where M and N are the rows and columns

Space Complexity here is O(1) as we are not using any extra space except 2 integers which is O(1)

#### Is there any other optiized Solution? Lets see

Looks like we can use Hashmap for this 
```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> groups = new HashMap();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!groups.containsKey(r-c))
                    groups.put(r-c, matrix[r][c]);
                else if (groups.get(r-c) != matrix[r][c])
                    return false;
            }
        }
        return true;
    }
}
```

But this solution though takes same time the space complexity is O( M+N) as we are using space in hashmap

So our solution is the best


