# Dot Product of Two Sparse Vectors

## Leet Code Link - https://leetcode.com/problems/dot-product-of-two-sparse-vectors/description/

## Complexity - Medium

## Description
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums

dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 

#### Example 1:
```
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
```
#### Example 2:
```
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
```
#### Example 3:
```
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 ```

#### Constraints:
```
n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
```

## Solution
By seeing the question, we know that directly looping through elements and then summing the prodcut of current object to the given object will give me the result

```java
class SparseVector {
    int[] nums;
    SparseVector(int[] nums) {
        this.nums = nums;   
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += this.nums[i]*vec.nums[i];
        }
        return sum;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```
#### Complexity
Let n be the length of the input array.

Time complexity: O(n) for both constructing the sparse vector and calculating the dot product.

Space complexity: O(1) for constructing the sparse vector as we simply save a reference to the input array and O(1) for calculating the dot product.

But is this optimal?

solution to the dot product of two vectors is a straightforward approach that works well when the vectors are dense (i.e., most of the elements are non-zero). However, since the problem mentions sparse vectors (where most of the elements are 0), your approach is suboptimal. A more efficient solution can take advantage of the sparsity to avoid unnecessary calculations involving 0 values.

#### Understanding Sparsity
In a sparse vector, many elements are 0. Computing the dot product involves multiplying corresponding elements, but any multiplication with 0 will always result in 0 and doesn’t contribute to the final sum.

In your solution, you iterate through the entire array even when the majority of elements are 0. This wastes computational effort and is unnecessary for sparse vectors.

Instead of storing the entire vector, we can store only the non-zero elements and their indices. This reduces both storage and computation. By iterating only through the non-zero elements of the vectors, we can compute the dot product much faster.


```
import java.util.HashMap;

class SparseVector {
    private HashMap<Integer, Integer> map; // Store index and value of non-zero elements

    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]); // Only store non-zero elements
            }
        }
    }

    // Return the dot product of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int sum = 0;
        for (int key : this.map.keySet()) {
            if (vec.map.containsKey(key)) {
                sum += this.map.get(key) * vec.map.get(key); // Multiply non-zero values
            }
        }
        return sum;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```

Can this be even more optimized?

Can we eliminate the use of HashMap. Yes, we can use two pointer technique instead of using hashmap

```java
import java.util.ArrayList;
import java.util.List;

class SparseVector {
    private List<int[]> nonZeroElements; // Store index and value of non-zero elements

    SparseVector(int[] nums) {
        nonZeroElements = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroElements.add(new int[] {i, nums[i]}); // Store index and value
            }
        }
    }

    // Return the dot product of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int sum = 0;
        int p1 = 0, p2 = 0;

        while (p1 < this.nonZeroElements.size() && p2 < vec.nonZeroElements.size()) {
            int[] e1 = this.nonZeroElements.get(p1);
            int[] e2 = vec.nonZeroElements.get(p2);

            if (e1[0] == e2[0]) { // Matching indices
                sum += e1[1] * e2[1];
                p1++;
                p2++;
            } else if (e1[0] < e2[0]) {
                p1++;
            } else {
                p2++;
            }
        }
        return sum;
    }
}

```
#### Complexity 
Let n be the length of the input array and L and L 
2
​
  be the number of non-zero elements for the two vectors.

Time complexity: O(n) for creating the <index, value> pair for non-zero values; O(L+L 
2
​
 ) for calculating the dot product.

Space complexity: O(L) for creating the <index, value> pairs for non-zero values. O(1) for calculating the dot product.
