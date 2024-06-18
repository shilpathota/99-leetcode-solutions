# Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]<br/>
Output: "fl"<br/>
Example 2:

Input: strs = ["dog","racecar","car"]<br/>
Output: ""<br/>
Explanation: There is no common prefix among the input strings.<br/>
 

<b>Constraints:</b><br/>

1 <= strs.length <= 200<br/>
0 <= strs[i].length <= 200<br/>
strs[i] consists of only lowercase English letters.<br/>

## Solution 1
### Inference
Lets first understand the problem. We have to find the common prefix among all the list of arrays.<br/>
There are 2 possible steps here to solve this:<br/>
1. Finding the common prefix - Compare the 2 strings by iterating each of the character until the minimum length among 2 strings.
2. Selecting the 2 string to be compared and send to the compare function

Let us analyze the first step:<br/>
Finding the common prefix -<br/>
Create a seperate function which does this job of comparing 2 strings.<br/>
So we have to pass 2 strings as parameters to this function<br/>


How many characters to iterate?<br/>
We do not have to iterate through all of the characters in the string<br/>
Find the minimum length of the strings and whichever is minimum, we can consider that length to iterate<br/>


Iterate until the charcters do not match. If it does not match, we return the substring that matched till now. else return the substring until minimum length if the iterations are completed.<br/>
![image](https://github.com/shilpathota/99-leetcode-solutions/assets/36531617/28254904-11b1-44da-82b1-77786e9b7de1)


Next we will go to second step - Selecting 2 strings<br/>
For this we can use divide and conquer<br/>

This function splits the array into two halves and recursively finds the longest common prefix for each half<br/>
1. We start by checking if the input array strs is null or empty. If it is, we return an empty string as there's no common prefix to find.
2. We define a recursive function longestCommonPrefix that takes the array of strings, a left index, and a right index.
3. The purpose of this function is to recursively divide the array into smaller subarrays until we reach individual strings, and then merge the results to find the common prefix.
4. If the left index equals the right index (left == right), it means we are looking at a single string. In this case, we return the string itself as the common prefix.
5. If the left index does not equal the right index, we find the midpoint of the current subarray (mid = (left + right) / 2).
6. We then recursively find the longest common prefix for the left half (lcpLeft) and the right half (lcpRight) of the subarray.
7. After obtaining the common prefixes for the left and right halves, we merge them by finding the common prefix between lcpLeft and lcpRight using a helper function commonPrefix which is defined above.

![image](https://github.com/shilpathota/99-leetcode-solutions/assets/36531617/04378486-4886-45b6-b7a3-45e33fe176fd)

Let us build the pseudo code around this to have a better idea


<pre>
 function longestCommonPrefix(strs)
    if strs is null or strs.length == 0 then
        return ""
    end if
    
    return divideAndConquer(strs, 0, strs.length - 1)
end function

function divideAndConquer(strs, left, right)
    if left == right then
        return strs[left]
    else
        mid = (left + right) / 2
        lcpLeft = divideAndConquer(strs, left, mid)
        lcpRight = divideAndConquer(strs, mid + 1, right)
        return mergeLCP(lcpLeft, lcpRight)
    end if
end function

function mergeLCP(lcpLeft, lcpRight)
    minLength = min(lcpLeft.length, lcpRight.length)
    for i = 0 to minLength - 1 do
        if lcpLeft[i] != lcpRight[i] then
            return lcpLeft.substring(0, i)
        end if
    end for
    return lcpLeft.substring(0, minLength)
end function

-- Example usage:
strs1 = ["flower", "flow", "flight"]
strs2 = ["dog", "racecar", "car"]

print(longestCommonPrefix(strs1))  -- Output: "fl"
print(longestCommonPrefix(strs2))  -- Output: ""

</pre>
longestCommonPrefix Function:

Checks if the input array strs is empty or null. If true, returns an empty string.
Otherwise, calls the divideAndConquer function with the entire array.
divideAndConquer Function:

Checks if left equals right. If true, returns strs[left], which is the current string.
Otherwise, calculates the midpoint (mid) of the current segment and recursively calls divideAndConquer on the left and right halves.
Returns the result of merging the longest common prefixes (lcpLeft and lcpRight) of the left and right halves using the mergeLCP function.
mergeLCP Function:

Determines the minimum length between lcpLeft and lcpRight.
Iterates through the characters of lcpLeft and lcpRight until a mismatch is found or the end of the shortest prefix is reached.
Returns the longest common prefix found up to the first mismatch.
Example Usage:

Demonstrates how to use the longestCommonPrefix function with example arrays strs1 and strs2, printing their respective results.

## Solution 2

We can use sorting technique to sort the array first and then compare the first and last words cna be a better approach with less number of iterations
### Step- by - Step Solution
1. Sort the array
2. Store the first and last elements of the array
3. compare each of the letters from the beginning to the min length of either one.
4. Store the result in a variable and keep appending the characters to it.
5. If the condition is not met or if the loop is completed then return the result.


Let's dig into the code now
