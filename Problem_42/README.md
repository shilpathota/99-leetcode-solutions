# Remove All Adjacent Duplicates In String

## Leet code Link - https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/

## Complexity - Easy

## Description

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

 #### Example 1:
```plaintext
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
```
#### Example 2:
```plaintext
Input: s = "azxxzy"
Output: "ay"
 ```

#### Constraints:
```plaintext
1 <= s.length <= 105
s consists of lowercase English letters.
```

---
## Solution
We can use stack to store the element and pop it if it matches with this element and return the string as shown below:
```java
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> st = new Stack<>();
        for(Character c:s.toCharArray()){
            if(st.size()>0 && st.peek()==c){
                st.pop();
            }
            else{
                st.add(c);
            }
        }
        StringBuilder output =new StringBuilder();
        st.stream().forEach(ch -> output.append(ch));
        return output.toString();
    }
}
```
But the above solution can be obtained without using the stack and can be achieved using string builder
```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == c) {
                sb.deleteCharAt(len - 1); // Remove the last character if it matches
            } else {
                sb.append(c); // Add the character to the result
            }
        }
        return sb.toString();
    }
}
```
The complexity involved is O(N) and for Space Complexity it is O(N)

this can also be achieved using two pointer where we have i to point to the end of stack and j for looping through the elements in string

We increment the i if it is not equal but decrement i and as the loop continues we can use j to put the element into i and then if equals decrement i. This way all the unique elements comes near to each other and we can return substring from 0 to i

```java
class Solution {
    public String removeDuplicates(String s) {
        char[] arr = s.toCharArray(); // Convert the string to a char array
        int i = 0; // Initialize the "stack" pointer

        for (int j = 0; j < s.length(); j++) {
            if (i > 0 && arr[i - 1] == arr[j]) {
                i--; // Remove the last character (simulate stack pop)
            } else {
                arr[i] = arr[j]; // Add the character (simulate stack push)
                i++;
            }
        }

        return new String(arr, 0, i); // Create the result string from the valid portion of the array
    }
}

```

Even for this we would need the same time complexity and space complexity


---
## My Mindmap

![IMG_3130](https://github.com/user-attachments/assets/1132301c-289c-4764-8d99-5988d550f327)

