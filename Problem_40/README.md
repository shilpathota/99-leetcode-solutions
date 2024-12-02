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

Next we should use characters outside the ASCII characters so can choose one among the unicode characters like Pi 
```java
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        // Iterate through the list of strings
        for (String s : strs) {
            // Append each string to the StringBuilder followed by the delimiter
            encodedString.append(s);
            encodedString.append("π");
        }
        // Return the entire encoded string
        return encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        // Split the encoded string at each occurrence of the delimiter
        // Note: We use -1 as the limit parameter to ensure trailing empty strings are included
        String[] decodedStrings = s.split("π", -1);
        // Convert the array to a list and return it
        // Note: We remove the last element because it's an empty string resulting from the final delimiter
        return new ArrayList<>(Arrays.asList(decodedStrings).subList(0, decodedStrings.length - 1));
    }
}
```
Let n denote the total number of characters across all strings in the input list and k denote the number of strings.

Time Complexity: O(n).

Both encoding and decoding processes iterate over every character in the input, thus they both have a linear time complexity of O(n).

Space Complexity: O(k).

We don't count the output as part of the space complexity, but for each word, we are using some space for the delimiter.

### But is this Ideal solution?

While the non-ASCII delimiter approach can work well for many applications, it assumes that the delimiter character will not appear in the strings to be encoded. However, in many practical situations, we cannot make this assumption. The strings might contain any possible character, including our chosen delimiter. Therefore, we need a different approach that can handle this situation.

For our purpose, we select /: as the delimiter. This choice provides us with a unique pattern to signal the end of a string during the encoding and decoding process. However, there's still a potential issue: What happens if one of our strings naturally contains the sequence /:? Let's examine how we can resolve this situation. What if the string contains the same sequence naturally? We can append escape sequence and identify the sequence in the words.

```java
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // Initialize a StringBuilder to hold the encoded strings
        StringBuilder encodedString = new StringBuilder();

        // Iterate over each string in the input list
        for (String s : strs) {
            // Replace each occurrence of '/' with '//'
            // This is our way of "escaping" the slash character
            // Then add our delimiter '/:' to the end
            encodedString.append(s.replace("/", "//")).append("/:");
        }

        // Return the final encoded string
        return encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        // Initialize a List to hold the decoded strings
        List<String> decodedStrings = new ArrayList<>();

        // Initialize a StringBuilder to hold the current string being built
        StringBuilder currentString = new StringBuilder();

        // Initialize an index 'i' to start of the string
        int i = 0;

        // Iterate while 'i' is less than the length of the encoded string
        while (i < s.length()) {
            // If we encounter the delimiter '/:'
            if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i + 1) == ':') {
                // Add the currentString to the list of decodedStrings
                decodedStrings.add(currentString.toString());

                // Clear currentString for the next string
                currentString = new StringBuilder();

                // Move the index 2 steps forward to skip the delimiter
                i += 2;
            }
            // If we encounter an escaped slash '//'
            else if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                // Add a single slash to the currentString
                currentString.append('/');

                // Move the index 2 steps forward to skip the escaped slash
                i += 2;
            }
            // Otherwise, just add the character to currentString and move the index 1 step forward.
            else {
                currentString.append(s.charAt(i));
                i++;
            }
        }

        // Return the list of decoded strings
        return decodedStrings;
    }
}
```
Let n denote the total number of characters across all strings in the input list and k denote the number of strings.

Time Complexity: O(n).

Both encoding and decoding processes iterate over every character in the input, thus they both have a linear time complexity of O(n).

Space Complexity: O(k).

We don't count the output as part of the space complexity, but for each word, we are using some space for the escape character and delimiter.

Chunked transfer encoding is a method used in data communication protocols to send data in self-contained chunks, each of which is accompanied by its length or size. In the context of our problem, this technique can be very useful.

Through this process, we have successfully encoded and decoded our list of strings using chunked transfer encoding. Even though our list contained a string with the delimiter sequence, we were still able to accurately encode and decode the list.

The advantage of this method is that it doesn't matter what characters our string consists of. It could include the delimiter, or any other special or non-ASCII characters, and we would still correctly encode and decode the list of strings. This is because we always know where each string starts and ends, thanks to the length prefix.

Numbers being in the string can't confuse the algorithm either since the number characters would be after the delimiter /:. For example, let's say we had ["Hello", "32World", "Example"]. It would encode to "5/:Hello7:/32World7:/Example". We read the 7, then stop upon seeing the delimiter, and the 32 being a number is irrelevant.

```java
public class Codec {
    public String encode(List<String> strs) {
        // Initialize a StringBuilder to hold the encoded string.
        StringBuilder encodedString = new StringBuilder();
        for (String s : strs) {
            // Append the length, the delimiter, and the string itself.
            encodedString.append(s.length()).append("/:").append(s);
        }
        return encodedString.toString();
    }

    public List<String> decode(String s) {
        // Initialize a list to hold the decoded strings.
        List<String> decodedStrings = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            // Find the delimiter.
            int delim = s.indexOf("/:", i);
            // Get the length, which is before the delimiter.
            int length = Integer.parseInt(s.substring(i, delim));
            // Get the string, which is of 'length' length after the delimiter.
            String str = s.substring(delim + 2, delim + 2 + length);
            // Add the string to the list.
            decodedStrings.add(str);
            // Move the index to the start of the next length.
            i = delim + 2 + length;
        }
        return decodedStrings;
    }
}
```
Complexity Analysis
Let n denote the total number of characters across all strings in the input list and k denote the number of strings.

Time Complexity: O(n).

We are iterating through each string once.

Space Complexity: O(k).

We don't count the output as part of the space complexity, but for each word, we are using some space for the length and delimiter.

But I have found a solution even better where I separate the size to the strings so even if there is string that has numbers or anything.

Here is the easy solution where I have used 2 storages though it increases complexity in terms of space 
```java
public class Codec {

  public String encode(List<String> strs) {
        if (strs.isEmpty()) return "";
        StringBuilder res = new StringBuilder();
        List<Integer> sizes = new ArrayList<>();
        for (String str : strs) {
            sizes.add(str.length());
        }
        for (int size : sizes) {
            res.append(size).append(',');
        }
        res.append('#');
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        if (str.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(cur.toString()));
            i++;
        }
        i++;
        for (int sz : sizes) {
            res.add(str.substring(i, i + sz));
            i += sz;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
```

time Complexity - O(N)

Space Complexity - O(N)

