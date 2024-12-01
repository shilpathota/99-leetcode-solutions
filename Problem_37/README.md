# 197. Rising Temperature

## Leet code Link - https://leetcode.com/problems/rising-temperature/description/

## Complexity - Easy

## Description

#### Table: Weather
```plaintext
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id is the column with unique values for this table.
There are no different rows with the same recordDate.
This table contains information about the temperature on a certain day.
 ```

Write a solution to find all dates' id with higher temperatures compared to its previous dates (yesterday).

Return the result table in any order.

The result format is in the following example.

 

#### Example 1:
```plaintext
Input: 
Weather table:
+----+------------+-------------+
| id | recordDate | temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
Output: 
+----+
| id |
+----+
| 2  |
| 4  |
+----+
Explanation: 
In 2015-01-02, the temperature was higher than the previous day (10 -> 25).
In 2015-01-04, the temperature was higher than the previous day (20 -> 30).
```
---
## Solution
### Using JOIN and DATEDIFF()

By doing a self-join on the Weather table, we create a Cartesian product of the table with itself, creating pairs of days. We then use the DATEDIFF function to restrict these pairs to only include consecutive days. Lastly, we filter these pairs of consecutive days further to only include pairs where the temperature is higher on the second day. The resulting ids represent the days where the temperature was higher than the previous day.

Let's break this down step by step:

#### Step 1: Defining the Main Query Structure
```MySQL
SELECT 
    w1.id
FROM 
    Weather w1
JOIN 
    Weather w2
```
Here, we are setting up a query to retrieve the id from the Weather table aliased as w1. To find the records where the temperature is greater than the previous day, we are performing a self-join on the Weather table, creating a second alias w2. This allows us to compare each record in w1 with each record in w2.

#### Step 2: Join Condition
```MySQL
ON 
    DATEDIFF(w1.recordDate, w2.recordDate) = 1
```
In the join condition, we are using the DATEDIFF function to find pairs of records where the recordDate differs by exactly one day. This condition ensures that we are comparing each day's temperature with the temperature of the previous day.

#### Step 3: Filter Records with Higher Temperature
```MySQL
WHERE 
    w1.temperature > w2.temperature;
```
After finding pairs of days that are consecutive, we apply a filter in the WHERE clause to only get the records where the temperature on a day (represented by a record in w1) is greater than the temperature on the previous day (represented by a record in w2). This is the main condition to fulfill the requirement of finding the ids where the temperature is higher than the previous day.

#### Implementation
```MySQL
SELECT 
    w1.id
FROM 
    Weather w1
JOIN 
    Weather w2
ON 
    DATEDIFF(w1.recordDate, w2.recordDate) = 1
WHERE 
    w1.temperature > w2.temperature;
```
