# AlgosPlay
Playing around with algorithms

## Data Structures

### 1. Fenwick Tree
Implementation of Fenwick Tree using Array.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/datastructures/FenwickTree.java

### 2. Skip List
Skip List implementation with dynamic level depth and node access.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/datastructures/SkipList.java

### 3. Fenwick Tree for BigInteger
Implementation of Fenwick Tree using Array of BigIntegers.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/datastructures/BigFenwickTree.java

### 4. Bucket Sort
Bucket sort implementation using array of linked lists.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/utils/BucketSorter.java

## HackerRank Challenge solutions

### 1. Almost Equal - Difficulty - Expert
Implemented using two pointer algorithm and MO's sorting.<br />
Used sorted grouped skip list for managing changes to the current segment of heights.<br />
Sorting the segment allows for range based checking which decreases complexity even more. <br />
Grouping same height values allows repeating heights to be processed together. <br />
Performance:<br />
Total running time for test case #10 (100000 heights with many repeating and 100000 queries) = 2.5 seconds.<br />
(If data is read through saneet.algosplay.utils.FastScanner.java) <br />
Problem: https://www.hackerrank.com/challenges/almost-equal-advanced<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/AlmostEqual.java

### 2. Direct Connections - Difficulty - Difficult
Fully optimized implementation using Fenwick Trees.<br />
Total running time for a large test case = 625 milliseconds. (If data is read through saneet.algosplay.utils.FastScanner.java) <br />
Problem: https://www.hackerrank.com/challenges/direct-connections<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/DirectConnections.java

### 3. Mr. X and his Shots - Difficulty - Moderate
Solved and optimized by sorting the lists and then processing a small window of
comparisons for each shot vs fielder combination.<br />
Problem: https://www.hackerrank.com/challenges/x-and-his-shots<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/MrXAndHisShots.java

### 4. Teacher Candy Distribution Problem
Implemented using 2 passes. Memoization and Dynamic Programming.<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/CandyProblem.java

### 5. Larry's Array
Solved by counting the number of inversions.<br />
Problem: https://www.hackerrank.com/challenges/larrys-array<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/LaryssArray.java

### 6. Kaprekar Numbers
Solved for both original and modified Kaprekar numbers.<br />
Problem: https://www.hackerrank.com/challenges/kaprekar-numbers<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/KaprekarNumbers.java

### 7. Encryption
Problem: https://www.hackerrank.com/challenges/encryption<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/Encryption.java

### 8. Huffman Decoding
Problem: https://www.hackerrank.com/challenges/tree-huffman-decoding<br />
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/HuffmanDecode.java

### 9. Other Random problems
Solution: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/hackerrank/OtherRandom.java


## Miscellaneous Algorithms

### 1. PageView Affinity problem
Problem: Input will be a seres of page-user mappings per line.
Affinity between two pages increases if the same user viewed them.
Find the page pair with the highest affinity.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/misc/PageViewAffinityProblem.java

### 2. Printing prime numbers
Optimal implementation that checks if prime by dividing it with all
previous prime numbers smaller than its square root.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet/algosplay/misc/PrimeNumbers.java

## Other Utilities

### 1. Fast Scanner for HackerRank problems
Scanner class that uses BufferedReader instead of Java scanner for much faster input reading.<br />
Code: https://github.com/Saneet/AlgosPlay/blob/master/src/saneet.algosplay.utils.FastScanner.java