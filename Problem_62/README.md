# Customer Who Visited but Did Not Make Any Transactions

## Leet code Link - https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/description/

## Complexity - Easy

## Description
SQL Schema
Pandas Schema
#### Table: Visits
```
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| visit_id    | int     |
| customer_id | int     |
+-------------+---------+
visit_id is the column with unique values for this table.
This table contains information about the customers who visited the mall.
 ```

#### Table: Transactions
```
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| transaction_id | int     |
| visit_id       | int     |
| amount         | int     |
+----------------+---------+
transaction_id is column with unique values for this table.
This table contains information about the transactions made during the visit_id.
 ```

Write a solution to find the IDs of the users who visited without making any transactions and the number of times they made these types of visits.

Return the result table sorted in any order.

The result format is in the following example.

 

#### Example 1:
```
Input: 
Visits
+----------+-------------+
| visit_id | customer_id |
+----------+-------------+
| 1        | 23          |
| 2        | 9           |
| 4        | 30          |
| 5        | 54          |
| 6        | 96          |
| 7        | 54          |
| 8        | 54          |
+----------+-------------+
Transactions
+----------------+----------+--------+
| transaction_id | visit_id | amount |
+----------------+----------+--------+
| 2              | 5        | 310    |
| 3              | 5        | 300    |
| 9              | 5        | 200    |
| 12             | 1        | 910    |
| 13             | 2        | 970    |
+----------------+----------+--------+
Output: 
+-------------+----------------+
| customer_id | count_no_trans |
+-------------+----------------+
| 54          | 2              |
| 30          | 1              |
| 96          | 1              |
+-------------+----------------+
Explanation: 
Customer with id = 23 visited the mall once and made one transaction during the visit with id = 12.
Customer with id = 9 visited the mall once and made one transaction during the visit with id = 13.
Customer with id = 30 visited the mall once and did not make any transactions.
Customer with id = 54 visited the mall three times. During 2 visits they did not make any transactions, and during one visit they made 3 transactions.
Customer with id = 96 visited the mall once and did not make any transactions.
As we can see, users with IDs 30 and 96 visited the mall one time without making any transactions. Also, user 54 visited the mall twice and did not make any transactions.
```
---
## Solution
For this approach, we remove the visits that have transactions directly using NOT IN. Let's start by identifying these visits. For this problem, they are all the visit_id from the table Transactions.

SELECT visit_id FROM Transactions
Next, in the main query, we can COUNT the visit_id at the customer_id level from table Visits excluding the visits we identified in the subquery. The aggregate value is grouped at the customer_id level as we are looking for the total result for each customer. This column is also renamed as requested by the final output.

#### Implementation
```MySQL
SELECT 
  customer_id, 
  COUNT(visit_id) AS count_no_trans 
FROM 
  Visits 
WHERE 
  visit_id NOT IN (
    SELECT 
      visit_id 
    FROM 
      Transactions
  ) 
GROUP BY 
  customer_id
  ```
### Approach 2: Removing Records Using LEFT JOIN and IS NULL
#### Algorithm
For this approach, we want to exclude visits that involved transactions from the complete set of visits by using LEFT JOIN. To do this, we have all visits as the left table (table Visits) to join the visits from table Transactions on the shared column visit_id. To remove the records from the right table, we set its key as NULL, so the remains in the Visits table are the records of visits where no transactions occurred.

To get the final output, we want to COUNT the number of such visits associated with each customer_id, and have the aggregated value grouped at the customer_id level. Lastly, we update the column as requested in the original problem statement.

#### Implementation
```MySQL
SELECT 
  customer_id, 
  COUNT(*) AS count_no_trans 
FROM 
  Visits AS v 
  LEFT JOIN Transactions AS t ON v.visit_id = t.visit_id 
WHERE 
  t.visit_id IS NULL 
GROUP BY 
  customer_id
```
