 # K Closest Points to Origin

 ## Leet Code Link - https://leetcode.com/problems/k-closest-points-to-origin/description/

 ## Complexity - Medium

 ## Description
 Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

 

#### Example 1:
<img src ="https://assets.leetcode.com/uploads/2021/03/03/closestplane1.jpg" />

```
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
```
#### Example 2:
```
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 ```

#### Constraints:
```
1 <= k <= points.length <= 104
-104 <= xi, yi <= 104
```
---
## Solution
On thinking of the solution, we know that we have to find the distance of each point. So lets keep it sepearate and create a method with the distance calculation. 

Also we do not need to find the sqrt as the number itself might be enough to say if it is greater or not.

Then we sort the array based on the distance. We pick the top k elements.

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Sort the array with a custom lambda comparator function
        Arrays.sort(points, (a, b) -> squaredDistance(a) - squaredDistance(b));
        
        // Return the first k elements of the sorted array
        return Arrays.copyOf(points, k);
    }
    
    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
};
```
The time complexity would be O(N Log N) as we are doing sorting and the space complexity is O(log N) to O(N)

The best solution might be using heap and using priority queue which stores the values sorted and the heapify process takes O(N) 

```java
class Solution {
    public int distanceCal(int x,int y){
        return x*x+y*y;
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> prio = new PriorityQueue<>((a,b)->b[0]-a[0]);
        
        for(int i=0;i<points.length;i++){
            int dist = distanceCal(points[i][0],points[i][1]);
                 if(prio.size()<k){
                     prio.add(new int[]{dist,i});

                 }
                 else if(dist<prio.peek()[0]){
                        prio.poll();
                      prio.add(new int[]{dist,i});

                 }

            }
        int[][] result = new int[k][2];
        for(int j=0;j<k;j++){
            result[j] = points[prio.poll()[1]];
        }
        return result;
    }
}
```
#### Complexity 
Here N refers to the length of the given array points.

Time complexity: O(N⋅logk)

Adding to/removing from the heap (or priority queue) only takes O(logk) time when the size of the heap is capped at k elements.

Space complexity: O(k)

The heap (or priority queue) will contain at most k elements.



