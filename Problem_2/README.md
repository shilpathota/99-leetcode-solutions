<h1>Climbing Stairs</h1>
<b>Link to LeetCode - </b> https://leetcode.com/problems/climbing-stairs/description/<br/>
<b>Difficulty Level - </b> Easy<br/>
<p>
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


<b>Example 1:</b>

Input: n = 2
Output: 2<br/>
<b>Explanation:</b><br/> There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
<br/>
<b>Example 2:</b>

Input: n = 3
Output: 3<br/>
<b>Explanation:</b><br/> There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step<br/>
 

<b>Constraints:</b>

1 <= n <= 45
</p>
<br/>
<b>Inference - </b><br/>
From the above problem You need to climb a staircase with n steps.<br/> Each time you can either climb 1 step or 2 steps. The goal is to find out how many distinct ways you can reach the top.<br />
<h4>Step-by-Step Explanation</h4>
<b>Understanding the Choices:</b>

If you're at step 0, there's only 1 way to start: take 1 step to reach step 1.<br/>
If you're at step 1, there are 2 ways to proceed:<br/>
Take 1 more step to reach step 2.<br/>
Take 2 steps to reach step 2.<br/>
<b>Building the Pattern:</b><br/>

For each step, you can get there by either:<br/>
Coming from the step right before it (taking 1 step).<br/>
Coming from two steps before it (taking 2 steps).<br/>
<b>Pattern Example:</b><br/>

If you want to reach step 3, you can:<br/>
Come from step 2 (taking 1 step).<br/>
Come from step 1 (taking 2 steps).<br/>
Therefore, the number of ways to reach step 3 is the sum of:<br/>

The number of ways to reach step 2.<br/>
The number of ways to reach step 1.<br/>
<b>General Pattern:</b><br/>

If ways(n) represents the number of ways to reach step n, then:<br/>
<pre>ways(n)=ways(n−1)+ways(n−2)</pre><br/>
This is because you can get to step n either from step n-1 or step n-2.<br/>
<img src = "https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_2/Climbing%20Stairs.drawio.png"/>
<br/>
Fron the above inference, we can say that if we can get the optimal solution for the ways to get to n-1 step and n-2 step can resolve the ways to n step.<br/>
This is the basic principle behind Dynamic Programming<br/>

# Solution 1
There are number of approaches that this problem can be solved. Let's see one by one and estimate its pros and cons
1. It can be solved the way we solve fibonacci series but ways(n) = fib(n+1)<br/>
so, we need to add  1 to the n to get the value.<br/>
Clearly, this can be solved by recursion.<br/>
As this is recursion which means the steps are repeated and we know within the recursion body, we have to add n-1 and n-2 <br/>
 <ul>
  <li>If n<=2 && n>0 then we can return n</li>
   <li>Else we can return countStairs of n-1 + countStairs of n-2</li>
 </ul>
<p>This is the most simple solution and it definitely works but what about the complexity. Let's calculate it</p>
<p>Time Complexity: The loop has to repeat 2 times as we have 2 ways to reach each step. This leads to complexity of 2<sup>n</sup></p>
<p>Space Complexity: Consider the recursive stack space and it is O(n)</p>
<p>Is this the optimal solution? No, Definitely not</p>
<p>There are ways to improvize this solution</p>
  ```java
       public int climbStairs(int n) {
        if(n==1||n==2) return n;
        return climbStairs(n-1)+climbStairs(n-2);
    }
   ```
 <p>The algorithm looks as follows</p>
<img src="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_2/Climbing%20Stairs2.drawio.png"/>


# Solution 2
<p>What if we store the solution in the recursion and check if the countStairs of n-1 already exists in the storage and if present we can fetch it stopping the recursion.<br/>This will definitely save the iterations. This process is called memoization. </p>
In the above solution we are calculating the n-1 and n-2 as independent and no mattar if we have done that already.<br/>
There is lot of repetition activities that can be reduced by storing already calculated values and not doing it again.<br/>
For this, we can have an array with n elements which will be fed as -1 initially<br/>
If we calculate climbstairs for n-k then it stores to the array if the value is -1<br/>
Else it uses the value instead of recalculating it<br/>
<h4>Step by Step Algorithm</h4><br/>
Initialization of dp array: We create an array dp of size (n + 1) and initialize all its elements to 0.<br/>
<pre>
dp = [0] * (n + 1)</pre><br/>
Base Cases: We set the values of dp[0] and dp[1] to 1. This is because there's only one way to climb 0 or 1 step.<br/>
<pre>
dp[0] = 1
dp[1] = 1
</pre><br/>
Iterative Step: We iterate from 2 to n (inclusive) to fill in the dp array with the number of ways to climb i steps.<br/>

<pre>for i in range(2, n + 1):
    dp[i] = dp[i-1] + dp[i-2]</pre><br/>
For each step i, the number of ways to reach that step is the sum of the number of ways to reach i-1 steps (if we climb 1 step from i-1) and the number of ways to reach i-2 steps (if we climb 2 steps from i-2).<br/>
Return Result: We return the last element of the dp array, which represents the number of ways to climb n steps.<br/>
<pre>
return dp[-1]</pre><br/>
This algorithm utilizes dynamic programming to efficiently compute the number of ways to climb the staircase. It avoids redundant calculations by storing previously computed results in the dp array.<br/>
<br/>
```java

    public int climb(int n,int[] arr){
        if(arr[n]!=-1) return arr[n];
        arr[n] = climb(n-1,arr)+climb(n-2,arr);
        return arr[n];
    }
   
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        for(int i=0;i<=n;i++){
            arr[i]=-1;
        }
        arr[0]=1;
        arr[1]=1;
                if(arr[n]!=-1) return arr[n];

        arr[n] = climb(n,arr);
        return arr[n];
    }

```
What about Complexity?<br/>
<b>Time Complexity - </b> Here we are performing iteractions for all the n elements. So it would be O(n)<br/>
<b>Space Complexity - </b> We are using array of n elements so the space it occupies is O(n)<br/>
# Solution 3
Can we get better solution than this? Yes, What if we can reduce the space complexity by just having 1 variable and removing the array.<br/>
For this purpose, we can use temp variable.<br/>
<b>Step by Step Solution:</b>
Base Cases: If n is less than or equal to 3, we return n. This is because for n = 1 or n = 2, the number of ways to climb the stairs is equal to n. And for n = 3, there are 3 ways: 1+1+1, 1+2, and 2+1.<br/>
<pre>
if n <= 3:
    return n</pre><br/>
Initialization of Variables: We initialize prev1 to 3, prev2 to 2, and cur to 0. These variables will keep track of the number of ways to climb the stairs for the current step and the previous two steps.<br/>
<pre>
prev1 = 3
prev2 = 2
cur = 0</pre>
Iterative Step: We iterate from 3 to n-1 (exclusive) to compute the number of ways to climb the stairs for each step.
<pre>
for _ in range(3, n):
    cur = prev1 + prev2
    prev2 = prev1
    prev1 = cur</pre><br>
For each step, cur stores the sum of the number of ways to climb the previous step (prev1) and the step before the previous one (prev2).<br>
We update prev1 with the current value of cur and prev2 with the previous value of prev1.<br>
Return Result: We return the value of cur, which represents the number of ways to climb n steps.<br>
<pre>
return cur</pre><br>
This algorithm efficiently computes the number of ways to climb the staircase by keeping track of only the necessary information for the current and previous steps, reducing the memory footprint compared to storing all values in an array.<br>
Let's analyze Complexity<br/>
<b>Time Complexity - </b> Here we are performing iteractions for all the n elements. So it would be O(n)<br/>
<b>Space Complexity - </b> We are using 1 varibale which is temperory so the space it occupies is O(1)<br/>


Now that we got best solution - Lets implement it
```java
  public int climbStairs(int n) {
        if (n <= 3) return n;

        int prev1 = 3;
        int prev2 = 2;
        int cur = 0;

        for (int i = 3; i < n; i++) {
            cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }

        return cur;        
    }
```

