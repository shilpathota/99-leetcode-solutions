# Interval List Intersections

## Leet code Link - https://leetcode.com/problems/interval-list-intersections/description/

## Complexity - Medium

## Description
You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

 

#### Example 1:

<img src="https://assets.leetcode.com/uploads/2019/01/30/interval1.png" />

```
Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
```
#### Example 2:
```
Input: firstList = [[1,3],[5,9]], secondList = []
Output: []
 ```

#### Constraints:
```
0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 109
endi < starti+1
0 <= startj < endj <= 109
endj < startj+1
```

---
## Solution
We know that we have to compare the intervals of each of the entry in both the arrays. We can take the first point which is max of first entries and min of second entries.

result is added if low is less than high which means there is intersection. We can increment both the arrays based on values.

```java
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m=firstList.length; int n = secondList.length;
        int i=0;int j=0;
        List<int[]> result = new ArrayList<>();
        while(i<m && j<n ){
            int low = Math.max(firstList[i][0],secondList[j][0]);
            int high = Math.min(firstList[i][1],secondList[j][1]);

            if(low<=high){
                result.add(new int[]{low,high});
            }
            
                if(firstList[i][1] < secondList[j][1] ){
                    i++;
                }else{
                    j++;
                }
        }
        return result.toArray(new int[result.size()][]);
    }
}
```

#### Complexity 
Time Complexity: O(M+N), where M,N are the lengths of A and B respectively.

Space Complexity: O(M+N), the maximum size of the answer.
