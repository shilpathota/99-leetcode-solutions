# Random Pick Index

## Leet Code Link -https://leetcode.com/problems/random-pick-index/description/

## Complexity - Medium

## Description
Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the array nums.
int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 

#### Example 1:
```
Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 ```

#### Constraints:
```
1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
target is an integer from nums.
At most 104 calls will be made to pick.
```
---
## Solution
In this approach, we will simply store the indices of all the numbers equal to target that are present in the array and will return an index at random. The drawback of this approach is, every time the pick method is called, an extra space is required, which is dependent on the number of occurences of the number target in the given array.

```java
class Solution {

    private int[] nums;
    
    private Random rand;
    
    public Solution(int[] nums) {
        // Do not allocate extra space for the nums array
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        List<Integer> indices = new ArrayList<>();
        int n = this.nums.length;
        int count = 0;
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            if (this.nums[i] == target) {
                indices.add(i);
            }
        }
        int l = indices.size();
        // pick an index at random
        int randomIndex = indices.get(rand.nextInt(l));
        return randomIndex;
    }
}
```
#### Complexity 
Time Complexity

pick - If N represents the size of the nums array, the time complexity of pick method is O(N).

Space Complexity: O(N) (space required by indices in pick method).

One more solution we can do is use Hashmap and when we create object we store all the elements along with the indices in hashmap. So when picking element you can just select the random value between the indices that are against the number which matches the target

```java
class Solution {

    private HashMap<Integer, List<Integer>> indices;
    private Random rand;
    
    public Solution(int[] nums) {
        this.rand = new Random();
        this.indices = new HashMap<Integer, List<Integer>>();
        int l = nums.length;
        for (int i = 0; i < l; ++i) {
            if (!this.indices.containsKey(nums[i])) {
                this.indices.put(nums[i], new ArrayList<>());
            }
            this.indices.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        int l = indices.get(target).size();
        // pick an index at random
        int randomIndex = indices.get(target).get(rand.nextInt(l));
        return randomIndex;
    }
}
```
#### Complexity 
Time Complexity

If N represents the size of the nums array, building indices takes O(N) time.

pick method takes O(1) time.

Space Complexity: O(N) required for indices.

The other method is reservoir sampling. Which means while picking the element we increment the counter and use it to select the random value and we choose the index if the probability is 0
```java
class Solution {
    int[] nums;
    Random rand;
    public Solution(int[] nums) {
        this.nums=nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int n = this.nums.length;
        int count =0;
        int idx=0;
        for(int i=0;i<n;i++){
            if(nums[i]==target){
                count++;
            
                if(rand.nextInt(count)==0){
                    idx=i;
                }
            }
        }
        return idx;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

```

#### Complexity
Time Complexity

If N represents the size of the nums array, pick method takes O(N) time
Space Complexity: O(1)
