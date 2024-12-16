# Check If N and Its Double Exist

## Leet code Link - https://leetcode.com/problems/check-if-n-and-its-double-exist/description/

## Complexity - Easy

## description 
Given an array arr of integers, check if there exist two indices i and j such that :

- i != j
- 0 <= i, j < arr.length
- arr[i] == 2 * arr[j]
 

#### Example 1:
```plaintext
Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
```
#### Example 2:
```plaintext
Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.
 ```

#### Constraints:
```plaintext
2 <= arr.length <= 500
-103 <= arr[i] <= 103
```
---
## Solution
#### Solution 1
Brute force thought would be straight. Have 2 loops where second loops checks and compares with each element to all other elements and if matches the condition return true else false.

This solution definitely needs some help from the HashSet where I can store the visited elements and while looping once I can track the element if already present in set. This way I can eliminate 1 loop

While validating condition we can consider searching for double the current elemetnt and if already cntains something that is haf of it and current is even number

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> list = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            if(list.contains(2*arr[i])||(list.contains(arr[i]/2)&&arr[i]%2==0)){
                return true;
            }
           list.add(arr[i]);
        }
        return false;
    }
}
```
### complexity
The time complexity would be O(N) and Space complexity would be O(N)
#### solution 2
We can also use map for this problem where map with keep track of count. If non zero then it checks for double element and if 0 it checks if it exists twice. because double of 0 is double

```java
class Solution {

    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            // Count occurrences of each number
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {
            // Check for double
            if (num != 0 && map.containsKey(2 * num)) {
                return true;
            }
            // Handle zero case (ensure there are at least two zeros)
            if (num == 0 && map.get(num) > 1) {
                return true;
            }
        }
        return false;
    }
}
```
Let n be the size of the input array arr.

Time complexity: O(n)

The algorithm consists of two main loops. The first loop iterates through the array arr and inserts or updates each element in the hash map. The insertion operation in the hash map takes O(1) on average, so the time complexity of this loop is O(n).

The second loop also iterates through the array arr and performs constant-time operations for each element, including lookups in the hash map (which are O(1) on average). Therefore, the time complexity of this loop is also O(n).

As a result, the overall time complexity is O(n)+O(n)=O(n).

Space complexity: O(n)

The space complexity is dominated by the hash map, which stores up to n unique elements from the array arr. Each key-value pair in the hash map consumes space, so in the worst case, the space required is proportional to the number of unique elements in arr, which is O(n).

No additional data structures that depend on the size of the input are used, so the auxiliary space is O(1). Therefore, the total space complexity is O(n).

#### Solution 3
We can also use binary search but for this the array should be sorted so the sorting increases the time complexity of O(n logn)
```java
class Solution {

    public boolean checkIfExist(int[] arr) {
        // Step 1: Sort the array
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            // Step 2: Calculate the target (double of current number)
            int target = 2 * arr[i];
            // Step 3: Custom binary search for the target
            int index = customBinarySearch(arr, target);
            // If the target exists and is not the same index
            if (index >= 0 && index != i) {
                return true;
            }
        }
        // No valid pair found
        return false;
    }

    //Implementation of binary search
    private int customBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Avoid potential overflow
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }
}
```
Let n be the size of the input array arr.

Time complexity: O(nlogn)

The sort function sorts the array in O(nlogn) time. Sorting is the most time-consuming operation here.

The for loop iterates through each element in the array, and for each element, it calls the customBinarySearch function. The binary search operation itself takes O(logn) time, as it divides the search space in half at each step.

Therefore, the time complexity of the loop is O(n) for iterating through the array, and for each iteration, the binary search takes O(logn). Thus, the total time complexity for the loop is O(nlogn).

Combining both parts, the overall time complexity is O(nlogn).

Space complexity: O(n) or O(logn)

The space taken by the sorting algorithm depends on the language of implementation:

In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has a space complexity of O(logn).

In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort, with a worst-case space complexity of O(logn).

In Python, the sort() method sorts a list using the Timsort algorithm which is a combination of Merge Sort and Insertion Sort and has a space complexity of O(n).

The binary search uses constant space for variables like left, right, and mid. The loop also does not use any additional space other than a few variables. Therefore, the space used by the loop is O(1).



