# 408. Valid Word Abbreviation
## Leet code Link - https://leetcode.com/problems/valid-word-abbreviation/description/
## Complexity - Easy
A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.

For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")

"sub4u4" ("sub stit u tion")

"12" ("substitution")

"su3i1u2on" ("su bst i t u ti on")

"substitution" (no substrings replaced)

The following are not valid abbreviations:

"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)

"s010n" (has leading zeros)

"s0ubstitution" (replaces an empty substring)

Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.

A substring is a contiguous non-empty sequence of characters within a string.

### Example 1:

**Input:** word = "internationalization", abbr = "i12iz4n"

**Output:** true

**Explanation:** The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
### Example 2:

**Input:** word = "apple", abbr = "a2e"

**Output:** false

**Explanation:** The word "apple" cannot be abbreviated as "a2e".
 

## Constraints:

1 <= word.length <= 20

word consists of only lowercase English letters.

1 <= abbr.length <= 10

abbr consists of lowercase English letters and digits.

All the integers in abbr will fit in a 32-bit integer.

## Solution

Pseudo Code
<pre>
 1. If abbreviation length > word length -> return false
 2. Loop till abbreviation Length and check each character of abbreviation
 3. if it is a number, initialize num to zero and start the loop till it does not find the digit. 
 4. Add the num multiplied by 10 te push it to 10's place and add the character -'0' which gives numeric value. this gives the number
 5. add the number to word index and increment the abbreviation index
 6. If the character is not number then check if the word index  is greater than length or if the character does not match return false
 7. otherwise increment the index of both word and abbreaviation
 8. return wordIndex == word length
</pre>
