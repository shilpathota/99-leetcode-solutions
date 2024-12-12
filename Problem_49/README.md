# Random Pick with Weight

## Leet code Link - https://leetcode.com/problems/random-pick-with-weight/description/

## Complexity - Medium

## Description
You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).

For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 

#### Example 1:
```plaintext
Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
```
#### Example 2:
```plaintext
Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 ```

#### Constraints:
```plaintext
1 <= w.length <= 104
1 <= w[i] <= 105
pickIndex will be called at most 104 times.
```
---
## Solution
The basic idea here is to have the sampling with weight. Which means suppose I'm throwing a ball rnadomly. Suppose I have divided my ground into zones with each zone is as big as its probability.

Like Yellow zone is 200 meters out of 1000 with probability or weight being 0.2. Green zone is 600 meters which means probability is 0.6 and remaining is red zone which is 0.2 200 meters

If you throw a ball where would it ball mostly. As per our basic intution, we have a big green zone so mostly it would fall in this region.

This is the basic idea behind this problem. The array provided where the value is the weight. so the value increases its weight increases. The total area would be sum of all weights.

Let us create an array with the prefix sum that is cumulative sum of the previous values. So the values after it until the next element would have the same index returned.

<img src="https://github.com/shilpathota/FlowCharts/blob/main/algo.drawio.png" />

From the above picture if we choose random number between the range from 1 to 27, and use binary search to determine the index to return it.

```java
class Solution {
    int sum=0;
    int[] arr;
    
    public Solution(int[] w) {
        arr =new int[w.length];
        for(int i=0;i<w.length;i++){
            sum+=w[i];
            arr[i]= sum;
        }
    }
    public int binarySearch(int rand,int L,int R){
        if(R>L){
        int mid = L+(R-L)/2;
        if(arr[mid]==rand) return mid;
        else if (arr[mid]>mid){
            binarySearch(rand,mid+1,R);
        }else{
            binarySearch(rand,L,mid-1);
        }
        }
        return L;
    }
    public int pickIndex() {
        int rand = (int)(Math.random() % sum);
        return binarySearch(rand,0,arr.length-1);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
```

#### Complexity
TimeComplexity - for constructor it would be O(N) and for pickIndex ( log n)

Space Complexity - For the constructor function, the space complexity remains O(N), which is again due to the construction of the prefix sums.


For the pickIndex() function, its space complexity would be O(1), since it uses constant memory. Note, here we consider the prefix sums that it operates on, as the input of the function.
