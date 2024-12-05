# Nested List Weight Sum

## LeetcodeLink - https://leetcode.com/problems/nested-list-weight-sum/description/

## Complexity - Medium

## Description

You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.

Return the sum of each integer in nestedList multiplied by its depth.

 

#### Example 1:

![image](https://github.com/user-attachments/assets/81184892-871d-4ca6-a6c6-81819230246d)
```plaintext
Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
```
#### Example 2:
![image](https://github.com/user-attachments/assets/c0ffcd97-16f0-46c8-8d4b-9148c80e0bb3)

```plaintext
Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
```
#### Example 3:
```plaintext
Input: nestedList = [0]
Output: 0
 ```
#### Constraints:
```plaintext
1 <= nestedList.length <= 50
The values of the integers in the nested list is in the range [-100, 100].
The maximum depth of any integer is less than or equal to 50.
```

---

## Solution

As you can see the list and it should be iterated deeper and deeper. The immediate thought was recursion. If I find the list I should loop again but if it is and element I should store it to the depth so I can calculate at the end

For this I used a method to recurse and pass the depth it so that it can be incremented on each time loop is called. I stored depth as key and value as the sum of all the values so that I can just multiply with depth at the end

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public HashMap<Integer,Integer> calDepth(HashMap<Integer,Integer> output,List<NestedInteger> nest,int depth){
        for(NestedInteger n : nest){
            if(n.getInteger()==null){
                output = calDepth(output,n.getList(),depth+1);
            }else{
                    output.put(depth,output.getOrDefault(depth, 0)+n.getInteger());
            }
        }
        return output;
    }
    public int depthSum(List<NestedInteger> nestedList) {
        HashMap<Integer,Integer> output = new HashMap<>();
        int result =0;
        int depth =1;
        output = calDepth(output, nestedList,depth);
        for(Map.Entry<Integer,Integer> out:output.entrySet()){
            result+=out.getKey()*out.getValue();
        }
        return result;
    }
}
```
#### Complexity
Time complexity was O(N)

Space Complexity was O(N)

But there are other solutions too where on seeing the depth we can think of DFS algorithm
```java
class Solution {
     public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                total += nested.getInteger() * depth;
            } else {
                total += dfs(nested.getList(), depth + 1);
            }
        }
        return total;
    }
}
```
Here we are not using the output variable and calculating the total on runtime

The complexity remains same as above as the DFS stacks the calls and so space is also O(N)

We can also achieve this using BFS algorithm

```java
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        int depth = 1;
        int total = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nested = queue.poll();
                if (nested.isInteger()) {
                    total += nested.getInteger() * depth;
                } else {
                    queue.addAll(nested.getList());
                }
            }
            depth++;
        }
        return total;
    }
}

```
Here we use queue where we add all the list of elements

