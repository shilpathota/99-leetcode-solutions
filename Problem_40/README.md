# Encode and Decode Strings

## Leet code Link - https://leetcode.com/problems/encode-and-decode-strings/description/

## Complexity - Medium

## Description

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
```plaintext
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
```
Machine 2 (receiver) has the function:
```plaintext
vector<string> decode(string s) {
  //... your code
  return strs;
}
```
So Machine 1 does:
```java
string encoded_string = encode(strs);
```
and Machine 2 does:
```cpp
vector<string> strs2 = decode(encoded_string);
```
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

You are not allowed to solve the problem using any serialize methods (such as eval).

#### Example 1:
```plaintext
Input: dummy_input = ["Hello","World"]
Output: ["Hello","World"]
Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

Machine 2:
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
```
#### Example 2:
```plaintext
Input: dummy_input = [""]
Output: [""]
 ```

#### Constraints:
```plaintext
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] contains any possible characters out of 256 valid ASCII characters.
 ```

### Follow up: Could you write a generalized algorithm to work on any possible set of characters?

---

## Solution
First thought would be use the delimiters to separate the words and then use the same delimiters and split the string to get the strings back. But what delimiters should we use. If we use some common delimiters like # , : . What if the same characters are present in the string. What are the other solutions?

Next we should use characters outside the 
