# Simplify Path

## Leet code link - https://leetcode.com/problems/simplify-path/description/

## Complexity - Medium

## Description
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:
```plaintext
A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.
```
 

#### Example 1:
```plaintext
Input: path = "/home/"

Output: "/home"

Explanation:

The trailing slash should be removed.
```
#### Example 2:
```plaintext
Input: path = "/home//foo/"

Output: "/home/foo"

Explanation:

Multiple consecutive slashes are replaced by a single one.
```
#### Example 3:
```plaintext
Input: path = "/home/user/Documents/../Pictures"

Output: "/home/user/Pictures"

Explanation:

A double period ".." refers to the directory up a level (the parent directory).
```
#### Example 4:
```plaintext
Input: path = "/../"

Output: "/"

Explanation:

Going one level up from the root directory is not possible.
```
#### Example 5:
```plaintext
Input: path = "/.../a/../b/c/../d/./"

Output: "/.../b/d"

Explanation:

"..." is a valid name for a directory in this problem.
```
 

#### Constraints:
```plaintext
1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
```

---

## Solution
On looking at the problem the first intution was spliting the string on / so I get the directories separated.

Second was storing only the directory names and ignore . or empty strings. Also if we encounter .. and it is not empty array that was stored, then remove the last added element. For this purpose we can use arraylist

At the end join all the values in the string with / . also, dont forget the / at the beginning.
```java
class Solution {
    public String simplifyPath(String path) {
        String[] splitpath = path.split("/");
        ArrayList<String> str = new ArrayList<>();
        for(int i=0;i<splitpath.length;i++){
            if(splitpath[i].equals("..")){
                if(!str.isEmpty()){
                    str.remove(str.size()-1);
                }
            }
            else if(splitpath[i].equals(".")||splitpath[i].equals("")){
                continue;
            }
            else{
                str.add(splitpath[i]);
            }
        }
        return "/"+String.join("/",str);
    }
}
```
The Time Complexity is O(N) and Space Complexity is also O(N)
