# Take Gifts From the Richest Pile

## Leet code Link - https://leetcode.com/problems/take-gifts-from-the-richest-pile/description/

## Complexity - Easy

## Description
You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:

- Choose the pile with the maximum number of gifts.
- If there is more than one pile with the maximum number of gifts, choose any.
- Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
- Return the number of gifts remaining after k seconds.

 

#### Example 1:
```
Input: gifts = [25,64,9,4,100], k = 4
Output: 29
Explanation: 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.
```
#### Example 2:
```
Input: gifts = [1,1,1,1], k = 4
Output: 4
Explanation: 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can't take any pile with you. 
So, the total gifts remaining are 4.
 ```

#### Constraints:
```
1 <= gifts.length <= 103
1 <= gifts[i] <= 109
1 <= k <= 103
```
---
## Solution
Let us discuss the brute force approach. On looking at the solution, we know we have to extract the highest element on changing push it back to the array.

Now pick the highest element again. This way we should perform this operation k times and then sum the elements in the array.

```java
public class Solution {

    public long pickGifts(int[] gifts, int k) {
        int n = gifts.length;

        // Perform the operation k times
        for (int i = 0; i < k; i++) {
            // Initialize the index of the richest pile (maximum element)
            int richestPileIndex = 0;

            // Iterate through the array to find the index of the maximum element
            for (
                int currentPileIndex = 0;
                currentPileIndex < n;
                currentPileIndex++
            ) {
                // If we find a new maximum, update the index
                if (gifts[richestPileIndex] < gifts[currentPileIndex]) {
                    richestPileIndex = currentPileIndex;
                }
            }

            // Replace the richest pile with the floor of its square root
            gifts[richestPileIndex] = (int) Math.floor(
                Math.sqrt(gifts[richestPileIndex])
            );
        }

        // Calculate the sum of the remaining gifts in the array
        long numberOfRemainingGifts = Arrays.stream(gifts)
            .mapToLong(gift -> gift) // Map to long to avoid overflow
            .sum();

        return numberOfRemainingGifts;
    }
}
```
### complexity 
Let n be the size of the gifts array.

Time complexity: O(k×n)

We use two nested for loops: the outer loop runs k times, and the inner loop runs n times. After the loops, summing the array values requires an additional pass through the array, which adds an extra O(n) complexity. However, the overall time complexity remains O(k×n)+O(n)=O(k×n).

Space complexity: O(1)

If we are allowed to modify the input, we can apply the operations directly on it, requiring only a constant amount of extra space. However, if we need to create a copy of the input, the space complexity would increase to O(n).

One more approach is Sort the array and then get the max element which means remove it from the arraylist apply the square root and then try inserting in correct position using binary search.

finally sum all the elements. Handle the case where binary search returns negative index if not found. So insert at the index returned +1 excluding the - sign

```java
public class Solution {

    public long pickGifts(int[] gifts, int k) {
        int n = gifts.length;

        // Create a list from the gifts array and sort it
        List<Integer> sortedGifts = new ArrayList<>();
        for (int gift : gifts) {
            sortedGifts.add(gift);
        }
        Collections.sort(sortedGifts);

        // Perform the operation k times
        for (int i = 0; i < k; i++) {
            // Get the largest element (last element in the sorted list)
            int maxElement = sortedGifts.get(n - 1);
            sortedGifts.remove(n - 1);

            // Calculate the square root of the max element
            int sqrtElement = (int) Math.floor(Math.sqrt(maxElement));

            // Find the index where the square root should be inserted
            int spotOfSqrt = Collections.binarySearch(sortedGifts, sqrtElement);

            // If the value isn't found, binarySearch returns a negative index
            if (spotOfSqrt < 0) {
                spotOfSqrt = -(spotOfSqrt + 1);
            }

            sortedGifts.add(spotOfSqrt, sqrtElement); // Insert the square root at the correct index
        }

        // Calculate the sum of the remaining gifts in the array
        long numberOfRemainingGifts = 0;
        for (int gift : sortedGifts) {
            numberOfRemainingGifts += gift;
        }

        return numberOfRemainingGifts;
    }
}
```
### complexity 
Let n be the size of the gifts array.

Time complexity: O(k×(n+logn))

At each step, we use the upper bound function to find the correct position for the square root of the maximum element. This function is implemented using binary search, so its time complexity is O(logn). Additionally, we insert a value into the array at the correct position, which has a time complexity of O(n), because all elements after the insertion point need to be shifted to the right. Since we are performing k operations in total, the overall time complexity becomes O(k×(n+logn)).

Space complexity: O(n)

Here, we avoid modifying the input directly by creating an array, sortedGifts, of size n. However, if we were allowed to modify the input in place, the space complexity could be reduced to O(1).

The Third approach is using priority queue. We know the natural behaviour of poll will return me the highest element and insert will insert into correct place.

```java
class Solution {
    public long pickGifts(int[] gifts, int k) {
        long sum=0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int gift:gifts){
            minHeap.add(gift);
        }

        for(int i=0;i<k;i++){
            int highest = minHeap.poll();
            int sq = (int)Math.floor(Math.sqrt(highest));
            minHeap.offer(sq);
        }
        for(int val:minHeap){
            sum= sum+val;
        }
        return sum;
    }
}
```
### complexity
Let n be the size of the gifts array.

Time complexity: O(n+k×logn)

The initialization of the heap requires O(n) time. On each step, we pop the maximum element and push the square root of that element back into the heap. Both operations (pop and push) have a time complexity of O(logn) because a heap is a balanced binary tree. Since we perform this operation k times, the overall time complexity is O(n+k×logn).

Space complexity: O(n)

The space complexity is O(n) since the heap contains exactly n elements.

I can also preserve the natural ordering of the min Heap and modify the code to add negative numbers and perform operation and use negation while adding and oeprting 

```java
public class Solution {

    public long pickGifts(int[] gifts, int k) {
        // Convert int[] to List<Integer> manually for efficient initialization of the heap
        // Negate values to simulate max-heap
        List<Integer> giftsList = new ArrayList<>();
        for (int gift : gifts) {
            giftsList.add(-gift);
        }

        // Initialize giftsHeap from giftsList
        PriorityQueue<Integer> giftsHeap = new PriorityQueue<>(giftsList);
        // Perform the operation 'k' times
        for (int i = 0; i < k; i++) {
            // Get the maximum element from the heap (top element)
            int maxElement = -giftsHeap.poll();

            // Insert the floor of the square root of the maximum element back into the heap
            giftsHeap.offer(-(int) Math.sqrt(maxElement));
        }

        // Accumulate the sum of the elements in the heap
        long numberOfRemainingGifts = 0;
        while (!giftsHeap.isEmpty()) {
            numberOfRemainingGifts -= giftsHeap.poll();
        }

        return numberOfRemainingGifts;
    }
}
```
