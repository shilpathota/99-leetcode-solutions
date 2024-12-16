# Rank Transform of an Array

## Leet Code Link - https://leetcode.com/problems/rank-transform-of-an-array/description/

## Complexity - Easy

## Description
Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

- Rank is an integer starting from 1.
- The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
- Rank should be as small as possible.
 

#### Example 1:
```plaintext
Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
```
#### Example 2:
```plaintext
Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
```
#### Example 3:
```plaintext
Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
 ```

#### Constraints:
```plaintext
0 <= arr.length <= 105
-109 <= arr[i] <= 109
```

---
## Solution
As soon as I see the question, I know I have to sort the arry to get the rank and also I have to return in the order of arr, so I have to preserve the order of the arr. So I make a copy of hte array.

sort the original array. As I loop through, I will store the value of rank in the hashmap. At the end, I fetch the value of the unsorted array from the hashmap and put back into arra and return it.

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer,Integer> sortedarr = new HashMap<>();
        int[] unsorted = arr.clone();
        Arrays.sort(arr);
        int rank =1;
        for(int i=0;i<arr.length;i++){
            if(i>0&&arr[i]==arr[i-1]){
                continue;
            }
            sortedarr.put(arr[i],rank);
            rank++;
        }
        for(int i=0;i<unsorted.length;i++){
            arr[i] = sortedarr.get(unsorted[i]);
        }
        return arr;
    }
}
```
#### complexity
As we perform the sorting It would take time complexity as O(N logN ) 

Space Complexity is 2 times N and one hashmap. So O(N)

