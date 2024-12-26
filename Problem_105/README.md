# Accounts Merge

## Leet code Link - https://leetcode.com/problems/accounts-merge/description/

## Complexity - Medium

## Description
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

 

#### Example 1:
```
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
```
#### Example 2:
```
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 ```

#### Constraints:
```
1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
```
---
## Solution
We are given a list of accounts where each account consists of a list containing the name of the person the account belongs to and some emails that belong to the person. One person is allowed to have multiple accounts, but each email can only belong to one person. Therefore, we can say two accounts must belong to the same person if the accounts have an email in common. Note that we cannot just use the user's name to determine which email addresses belong to the same user since different users may have the same name.

Our goal is, for each person, we want to identify all of the emails that belong to that person. Therefore, every time we find two accounts with an email in common, we will merge the two accounts into one.

Whenever we must work with a set of elements (emails) that are connected (belong to the same user), we should always consider visualizing our input as a graph. In this problem, converting the input into a graph will facilitate the process of "merging" two accounts.

Emails can be represented as nodes, and an edge between nodes will signify that they belong to the same person. Since all of the emails in an account belong to the same person, we can connect all of the emails with edges. Thus, each account can be represented by a connected component. What if two accounts have an email in common? Then we can add an edge between the two connected components, effectively merging them into one connected component.

We can use DFS for this problem
- First, we Create an adjacency list for each of the emails
- Then for merging we use visited set and also have a merged list.
- We iterate through the accounts again. Add the name to the merged list.
- Go to DFS where we add the email if present in adjlist, it iterates over the neighbours to get the list of other email id and add them to the merged list. this is done only if it is not visited
- When the control is back we have the list of all merged accounts which we sort and then add to the final output.
- Now return the output.

```java
  class Solution {
    Set<String> visited = new HashSet<>();
    Map<String, List<String>> adjList = new HashMap<>();
    public void DFS(List<String> mergeaccount, String current){
            visited.add(current);
            mergeaccount.add(current);
            if(!adjList.containsKey(current)){
                return;
            }
            for(String neigh : adjList.get(current)){
                if(!visited.contains(neigh)){
                DFS(mergeaccount,neigh);
                                }

            }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> mergeaccounts = new ArrayList<>();
        
        //adjacency list preparation
        for(List<String> account : accounts){
            String firstemail = account.get(1);
            for(int i=2;i<account.size();i++){
                if(!adjList.containsKey(firstemail)){
                    adjList.put(firstemail,new ArrayList<String>());
                }
                adjList.get(firstemail).add(account.get(i));
                if(!adjList.containsKey(account.get(i))){
                    adjList.put(account.get(i),new ArrayList<String>());
                }
                adjList.get(account.get(i)).add(firstemail);
            }
        }
        // merge the list 
        for(int i=0;i<accounts.size();i++){
            String name = accounts.get(i).get(0);
            String firstemail = accounts.get(i).get(1);
            if(!visited.contains(firstemail)){
                List<String> mergeaccount = new ArrayList<>();
                mergeaccount.add(name);
                DFS(mergeaccount, firstemail);
                Collections.sort(mergeaccount.subList(1,mergeaccount.size()));
                mergeaccounts.add(mergeaccount);
            }
        }
        // // use DFS to go over adjancency list and add all the neighbours
        //sort the list
        //return the list
        return mergeaccounts;
    }
}
```

#### complexity
Here N is the number of accounts and K is the maximum length of an account.

Time complexity: O(NKlogNK)

In the worst case, all the emails will end up belonging to a single person. The total number of emails will be N∗K, and we need to sort these emails. DFS traversal will take NK operations as no email will be traversed more than once.

Space complexity: O(NK)

Building the adjacency list will take O(NK) space. In the end, visited will contain all of the emails hence it will use O(NK) space. Also, the call stack for DFS will use O(NK) space in the worst case.

The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, Collections.sort() dumps the specified list into an array this will take O(NK) space then Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(logNK). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort, and Insertion Sort with the worst-case space complexity of O(logNK).
