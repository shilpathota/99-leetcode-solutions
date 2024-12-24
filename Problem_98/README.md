# Custom Sort String

## Leet Code link - https://leetcode.com/problems/custom-sort-string/description/

## Complexity - Medium

## Description
You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

 

#### Example 1:
```
Input: order = "cba", s = "abcd"

Output: "cbad"

Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".

Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
```
#### Example 2:
```
Input: order = "bcafg", s = "abcd"

Output: "bcad"

Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "dbca" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.
```
 

#### Constraints:
```
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
```

---
## Solution
As we go over the order characters, if we find the characters in the string, we place them in the string builder.
We can use simple inbuilt comparator and customizing it.
```java
class Solution {
    public String customSortString(String order, String s) {
        // Create char array for editing
        int N = s.length();
        Character[] result = new Character[N];
        for (int i = 0; i < N; i++) {
            result[i] = s.charAt(i);
        }

        // Define the custom comparator
        Arrays.sort(result, (c1, c2) -> {
            // The index of the character in order determines the value to be sorted by
            return order.indexOf(c1) - order.indexOf(c2);
        });

        // Return the result
        String resultString = "";
        for (Character c: result) {
            resultString += c;
        }
        return resultString;
    }
}
```

As sorting takes O(NlogN)

Next method is, we get the frequency of the characters and add characters in order specified and then add remaining characters

```java
class Solution {
    public String customSortString(String order, String s) {
         // Count frequency of characters in s
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
                // Add characters from order in the specified order
       for (char c : order.toCharArray()) {
            while (charCount[c - 'a'] > 0) {
                sb.append(c);
                charCount[c - 'a']--;
            }
        }
  // Add remaining characters not in order
        for (char c = 'a'; c <= 'z'; c++) {
            while (charCount[c - 'a'] > 0) {
                sb.append(c);
                charCount[c - 'a']--;
            }
        }
        return sb.toString();
    }
}
```

We can also use Map instead of array
```java
class Solution {
    public String customSortString(String order, String s) {
        // Create a frequency table
        Map<Character, Integer> freq = new HashMap<>();

        // Initialize frequencies of letters
        // freq[c] = frequency of char c in s
        int N = s.length();
        for (int i = 0; i < N; i++) {
            char letter = s.charAt(i);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // Iterate order string to append to result
        int K = order.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            char letter = order.charAt(i);
            while (freq.getOrDefault(letter, 0) > 0) {
                result.append(letter);
                freq.put(letter, freq.get(letter) - 1);
            }
        }

        // Iterate through freq and append remaining letters
        // This is necessary because some letters may not appear in `order`
        for (char letter : freq.keySet()) {
            int count = freq.get(letter);
            while (count > 0) {
                result.append(letter);
                count--;
            }
        }

        // Return the result
        return result.toString();
    }
}
```
#### Complexity
Here, we define N as the length of string s, and K as the length of string order.

Time Complexity: O(N)

It takes O(N) time to populate the frequency table, and all other hashmap operations performed take O(1) time in the average case. Building the result string also takes O(N) time because each letter from s is appended to the result in the custom order, making the overall time complexity O(N).

Space Complexity: O(N)

A hash map and a result string are created, which results in an additional space complexity of O(N).
