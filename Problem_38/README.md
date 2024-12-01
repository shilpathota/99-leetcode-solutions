# Top K Frequent Elements

## Leet code link - https://leetcode.com/problems/top-k-frequent-elements/description/

## Complexity - Medium

## Description

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

#### Example 1:
```plaintext
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```
#### Example 2:
```plaintext
Input: nums = [1], k = 1
Output: [1]
 ```

#### Constraints:
```plaintext
1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 ```

---
## Solution
As we have seen in earlier problems, to get the frequency of the elements we use Hashmap which keeps track of the frequency of each element

Now we should use the bucket sort which puts the elements into respective buckets based on the frequency. For this we use the list and value as the arraylist with the all the elements in the corresponding frequency

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
           HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer>[] freq = new ArrayList[nums.length + 1];
        int[] output = new int[k];
        for(int i=0;i<freq.length;i++){
            freq[i] = new ArrayList();
        }
        for(int n : nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
         for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }
        int index=0;
        for(int i=freq.length-1;i>=0;i--){
            for (int n : freq[i]) {
                output[index++] = n;
                if (index == k) {
                    return output;
                }
            }
        }
        return output;
    }
}
```
### Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Time Complesity for the above algorithm is O(n log k)

Space Complexity is O(n + k)

## My Mind Map

![MindMap](https://github.com/user-attachments/assets/5d448356-3c38-432d-9ca1-70c832a8e979)

