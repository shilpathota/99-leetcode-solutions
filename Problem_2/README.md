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
