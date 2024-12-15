# Merge Intervals

## Leet Code Link - https://leetcode.com/problems/merge-intervals/description/

## Complexity - Medium

## Description 
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

#### Example 1:
```plaintext
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
```
#### Example 2:
```plaintext
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

#### Constraints:
```plaintext
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
```

---

## Solution
As soon as we look at the problem, I think of comparing each and every interval with all other intervals to see if start of this interval matches or within other intervals but this has time complexity of O(N^2)

So this would be better if it is sorted, so I can only compare the next interval if it overlaps and this sorting should be done on the first element.

After sorting, we can compare the last and first of the next interval to determine if overlaps or not. If overlaps then put the second part of interval as the max of both the intervals.

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        ArrayList<int[]> out = new ArrayList<>();
        for(int[] inter:intervals){
            if(out.isEmpty()||out.getLast()[1]<inter[0]){
                out.add(inter);
            }
            else{
                out.getLast()[1]=Math.max(out.getLast()[1],inter[1]);
            }
        }
        return out.toArray(new int[out.size()][]);
    }
}
```

As we are sorting the time complexity is O(N LOG N) Other than the sort invocation, we do a simple linear scan of the list, so the runtime is dominated by the O(nlogn) complexity of sorting.

space complexity is O(LOG N ) or O(N) -- If we can sort intervals in place, we do not need more than constant additional space, although the sorting itself takes O(logn) space. Otherwise, we must allocate linear space to store a copy of intervals and sort that.

