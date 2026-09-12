# Maximum Score of Non-overlapping Intervals

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a 2D integer array `intervals`, where `intervals[i] = [li, ri, weighti]`. Interval `i` starts at position `li` and ends at `ri`, and has a weight of `weighti`. You can choose  *up to*  4  **non-overlapping**  intervals. The  **score**  of the chosen intervals is defined as the total sum of their weights.

Return the lexicographically smallest array of at most 4 indices from `intervals` with  **maximum**  score, representing your choice of non-overlapping intervals.

Two intervals are said to be  **non-overlapping**  if they do not share any points. In particular, intervals sharing a left or right boundary are considered overlapping.

 

 **Example 1:** 

 **Input:**  intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]

 **Output:**  [2,3]

 **Explanation:** 

You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.

 **Example 2:** 

 **Input:**  intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]

 **Output:**  [1,3,5,6]

 **Explanation:** 

You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.

 

 **Constraints:** 

- 1 <= intevals.length <= 5 * 104
- intervals[i].length == 3
- intervals[i] = [li, ri, weighti]
- 1 <= li <= ri <= 109
- 1 <= weighti <= 109

## Solution

**Language:** Java  
**Runtime:** 180 ms (beats 33.33%)  
**Memory:** 169.6 MB (beats 55.55%)  
**Submitted:** 2026-09-12T17:19:42.827Z  

```java
class Solution {
  public int[] maximumWeight(List<List<Integer>> intervals) {
    // Convert input to Interval objects
    List<Interval> indexedIntervals = new ArrayList<>();
    for (int i = 0; i < intervals.size(); ++i) {
      List<Integer> interval = intervals.get(i);
      indexedIntervals.add(new Interval(interval.get(0), interval.get(1), interval.get(2), i));
    }
    indexedIntervals.sort(Comparator.comparingInt(Interval::left));
    T[][] memo = new T[indexedIntervals.size()][5];
    return dp(indexedIntervals, memo, 0, 4).selected.stream().mapToInt(Integer::intValue).toArray();
  }

  private record T(long weight, List<Integer> selected) {}
  private record Interval(int left, int right, int weight, int originalIndex) {}

  private T dp(List<Interval> intervals, T[][] memo, int i, int quota) {
    if (i == intervals.size() || quota == 0)
      return new T(0, List.of());
    if (memo[i][quota] != null)
      return memo[i][quota];

    T skip = dp(intervals, memo, i + 1, quota);

    Interval interval = intervals.get(i);
    final int j = findFirstGreater(intervals, i + 1, interval.right);
    T nextRes = dp(intervals, memo, j, quota - 1);

    List<Integer> newSelected = new ArrayList<>(nextRes.selected);
    newSelected.add(interval.originalIndex);
    Collections.sort(newSelected);
    T pick = new T(interval.weight + nextRes.weight, newSelected);
    return memo[i][quota] =
               (pick.weight > skip.weight ||
                (pick.weight == skip.weight && compareLists(pick.selected, skip.selected) < 0))
                   ? pick
                   : skip;
  }

  // Binary searches the first interval that starts after `rightBoundary`.
  private int findFirstGreater(List<Interval> intervals, int startFrom, int rightBoundary) {
    int l = startFrom;
    int r = intervals.size();
    while (l < r) {
      final int m = (l + r) / 2;
      if (intervals.get(m).left > rightBoundary)
        r = m;
      else
        l = m + 1;
    }
    return l;
  }

  // Compares two lists of integers lexicographically.
  private int compareLists(List<Integer> list1, List<Integer> list2) {
    final int minSize = Math.min(list1.size(), list2.size());
    for (int i = 0; i < minSize; ++i) {
      final int comparison = Integer.compare(list1.get(i), list2.get(i));
      if (comparison != 0)
        return comparison;
    }
    return Integer.compare(list1.size(), list2.size());
  }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/)