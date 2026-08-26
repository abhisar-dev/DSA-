# Restaurant

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Martha is interviewing at Subway. One of the rounds of the interview requires her to cut a bread of size $l \times b$ into smaller identical pieces such that each piece is a square having maximum possible side length with no left over piece of bread.


**Input Format**

The first line contains an integer $T$. $T$ lines follow. Each line contains two space separated integers $l$ and $b$ which denote length and breadth of the bread. 



**Constraints**

+ $1 \le T \le 1000$
+ $1 \le l, b \le 1000$

**Output Format**

$T$ lines, each containing an integer that denotes the number of squares of maximum size, when the bread is cut as per the given condition.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-26T07:33:22.861Z  

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'restaurant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER b
     */

    public static int restaurant(int l, int b) {
    // Write your code here
int area = l*b;
 while(b!=0){
        int rem = l%b;
        l=b;
        b = rem;
    }
    return area/(l*l);
 }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]);

                int b = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.restaurant(l, b);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/restaurant/problem)