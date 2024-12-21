# Group Shifted Strings

## Leet Code Link - https://leetcode.com/problems/group-shifted-strings/description/

## Complexity - Medium

## Description
Perform the following shift operations on a string:

Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
We can keep shifting the string in both directions to form an endless shifting sequence.

For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

 

#### Example 1:
```
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]

Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
```

#### Example 2:
```
Input: strings = ["a"]

Output: [["a"]]
```
 

#### Constraints:
```
1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
```

---
## Solution
All the strings in the below table belong to the same shifting sequence. We want to represent each one of these by a single string. We can represent each of these strings by abc as shown in the below table.

To make every string the same, the first character in all the strings will also have to be the same. Hence, we will first convert the first character of all the strings to any character, let's say a. To convert the first character to a we would require some number of shifting say shift (shift $$\geq $$ 0), and we need to shift the other characters of string by the same value shift.

In this way, the strings in the same shifting sequence will end up having the same value.

Algorithm

Iterate over strings, and for each string:

a. Find its Hash value, that is, the string starts with an a after some shifts. The value of shift is equal to the first character of the string. Then shift all the characters by the same value shift. Notice that we also have to do a mod of 26 on the resulting character for the circular shift.

b. Map the original string to the above Hash value in the map mapHashToList. More specifically, add the original string to the list corresponding to its Hash value.

Iterate over the mapHashToList and store the list for every key in the map in the answer array groups.

Any hash function that results in collisions for strings with a common shifting sequence will group the strings as required. Let's consider another possible way to implement the getHash() function: we can map each string to the collection of differences in ASCII code values between each of its consecutive characters. This works because strings in the same shifting sequence will have the same collection of differences between all the consecutive letters. For example, every string in the group ["acf","gil","xzc"] can be represented as 2,3 because c - a = i - g = z - x = 2 and f - c = l - i = c - z = 3. Here the last expression c - z is -23, however, we are doing a circular shift, so we will do a modulo operation on the result to make it 3. As in the previous implementation, we will use a string for the hash key. So let's shift the values back to the alphabetical range so that our list of differences 2,3 becomes "cd". Let's take a look at the code for this hashing method:

```java

class Solution {
    public String getHash(String str){
        char[] ch = str.toCharArray();
        StringBuilder strb = new StringBuilder();

        for(int i=1;i<ch.length;i++){
            strb.append((char)((ch[i]-ch[i-1]+26)%26+'a'));
        }
        return strb.toString();
    }
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String,List<String>> buck = new HashMap<>();
        List<List<String>> out = new ArrayList<>();
        for(String str : strings){
            String hashKey = getHash(str);
            List<String> l= buck.getOrDefault(hashKey,new ArrayList<String>());
            l.add(str);
            buck.put(hashKey,l);
        }
        for(List<String> iter : buck.values()){
            out.add(iter);
        }
        return out;
    }
}
```
#### Complexity 
Let N be the length of strings and K be the maximum length of a string in strings.

Time complexity: O(N∗K)

We iterate over all N strings and for each string, we iterate over all the characters to generate the Hash value, which takes O(K) time. To sum up, the overall time complexity is O(N∗K).

Space complexity: O(N∗K)

We need to store all the strings plus their Hash values in mapHashToList. In the worst scenario, when each string in the given list belongs to a different Hash value, the maximum number of strings stored in mapHashToList is 2∗N. Each string takes at most O(K) space. Hence the overall space complexity is O(N∗K).
