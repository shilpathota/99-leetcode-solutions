# Container with most water

## Leet Code link - https://leetcode.com/problems/container-with-most-water/description/

## Complexity - Medium

## Description
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

 #### Example 1:
 <img src = "https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg" />
```
 Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
```
#### Example 2:
```
Input: height = [1,1]
Output: 1
 ```

#### Constraints:
```
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
```
---
## solution
The brute force solution for this would be iterating through all heights and inner loop with the height next element and calculate the area. this will cost O(N^2)

The best approch would be two pointer slution where we move the pointer if the left or right is lesser and calculate the height and store the max height in the varibale and return it

```java
class Solution {
    public int maxArea(int[] height) {
        int max_area = 0;
        int left =0;int right = height.length-1;
        while(left<right){
            int width = right-left;
            max_area = Math.max(Math.min(height[left],height[right])*width,max_area);
            if(height[left]<height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return max_area;
    }
}
```
#### Complexity 
Time complexity: O(n). Single pass.

Space complexity: O(1). Constant space is used.
