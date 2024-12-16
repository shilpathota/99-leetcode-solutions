# Kids With the Greatest Number of Candies

## Leet code Link - https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/

## Complexity - Easy

## Description
There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.

Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.

Note that multiple kids can have the greatest number of candies.

 

#### Example 1:
```
Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true] 
Explanation: If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
```
#### Example 2:
```
Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false] 
Explanation: There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
```
#### Example 3:
```
Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]
 ```

#### Constraints:
```plaintext
n == candies.length
2 <= n <= 100
1 <= candies[i] <= 100
1 <= extraCandies <= 50
```

## Solution
 When I look at the solution, I know I have to loop through the array and get the highest. If I get the highest, Then I  can compare the difference of the highest to the current element.

 If the difference is less than or equal to extra cnadies then we enter as true else mark as false in the output.

 ```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> out = new ArrayList<>();
        //highest number
        int highest =0;
        for(int i=0;i<candies.length;i++){
            highest = Math.max(highest, candies[i]);
        }
        //loop and see if the diff of highest number is greater than extra then mark it false. If the diff is less mark it true
        for(int candy:candies){
            if(highest - candy <= extraCandies){
                out.add(true);
                continue;
            }
            out.add(false);
        }
        return out;
    }
}
```
## Complexity 
Time Complexity - We are looping twice so O(N) + O(N) which is O(N)

Space Complexity - we are using the output array so O(1) without counting output or input.
