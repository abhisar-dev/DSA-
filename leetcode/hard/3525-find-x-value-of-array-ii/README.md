# Find X Value of Array II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array of  **positive**  integers `nums` and a  **positive**  integer `k`. You are also given a 2D array `queries`, where `queries[i] = [indexi, valuei, starti, xi]`.

You are allowed to perform an operation  **once**  on `nums`, where you can remove any  **suffix**  from `nums` such that `nums` remains  **non-empty**.

The  **x-value**  of `nums`  **for a given**  `x` is defined as the number of ways to perform this operation so that the  **product**  of the remaining elements leaves a  *remainder*  of `x`  **modulo**  `k`.

For each query in `queries` you need to determine the  **x-value**  of `nums` for `xi` after performing the following actions:

- Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
- Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix).

Return an array `result` of size `queries.length` where `result[i]` is the answer for the `ith` query.

A  **prefix**  of an array is a subarray that starts from the beginning of the array and extends to any point within it.

A  **suffix**  of an array is a subarray that starts at any point within the array and extends to the end of the array.

 **Note**  that the prefix and suffix to be chosen for the operation can be  **empty**.

 **Note**  that x-value has a  *different*  definition in this version.

 

 **Example 1:** 

 **Input:**  nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]

 **Output:**  [2,2,2]

 **Explanation:** 

- For query 0, nums becomes [1, 2, 2, 4, 5], and the empty prefix must be removed. The possible operations are: Remove the suffix [2, 4, 5]. nums becomes [1, 2]. Remove the empty suffix. nums becomes [1, 2, 2, 4, 5] with a product 80, which gives remainder 2 when divided by 3.
- For query 1, nums becomes [1, 2, 2, 3, 5], and the prefix [1, 2, 2] must be removed. The possible operations are: Remove the empty suffix. nums becomes [3, 5]. Remove the suffix [5]. nums becomes [3].
- For query 2, nums becomes [1, 2, 2, 3, 5], and the empty prefix must be removed. The possible operations are: Remove the suffix [2, 2, 3, 5]. nums becomes [1]. Remove the suffix [3, 5]. nums becomes [1, 2, 2].

 **Example 2:** 

 **Input:**  nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]

 **Output:**  [1,0]

 **Explanation:** 

- For query 0, nums becomes [2, 2, 4, 8, 16, 32]. The only possible operation is: Remove the suffix [2, 4, 8, 16, 32].
- For query 1, nums becomes [2, 2, 4, 8, 16, 32]. There is no possible way to perform the operation.

 **Example 3:** 

 **Input:**  nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]

 **Output:**  [5]

 

 **Constraints:** 

- 1 <= nums[i] <= 109
- 1 <= nums.length <= 105
- 1 <= k <= 5
- 1 <= queries.length <= 2 * 104
- queries[i] == [indexi, valuei, starti, xi]
- 0 <= indexi <= nums.length - 1
- 1 <= valuei <= 109
- 0 <= starti <= nums.length - 1
- 0 <= xi <= k - 1

## Solution

**Language:** C++  
**Runtime:** 389 ms (beats 34.54%)  
**Memory:** 300.5 MB (beats 30.91%)  
**Submitted:** 2026-09-22T13:38:26.428Z  

```cpp
struct Node {
  int remain[5] = {0};
  int prod = 1;
};

class SegmentTree {
 public:
  explicit SegmentTree(const vector<int>& nums, int k)
      : n(nums.size()), k(k), tree(4 * n) {
    build(nums, 0, 0, n - 1);
  }

  // Updates nums[i] to val.
  void update(int i, int val) {
    update(0, 0, n - 1, i, val);
  }

  // Returns the result of the range query from nums[i..j].
  Node query(int i, int j) const {
    return query(0, 0, n - 1, i, j);
  }

 private:
  const int n;        // the size of the input array
  const int k;        // the modulo value
  vector<Node> tree;  // the segment tree

  void build(const vector<int>& nums, int cur, int left, int right) {
    if (left == right) {
      tree[cur].remain[nums[left]] = 1;
      tree[cur].prod = nums[left];
      return;
    }
    const int mid = (left + right) / 2;
    build(nums, 2 * cur + 1, left, mid);
    build(nums, 2 * cur + 2, mid + 1, right);
    tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
  }

  void update(int treeIndex, int lo, int hi, int i, int val) {
    if (lo == hi) {
      for (int j = 0; j < k; ++j)
        tree[treeIndex].remain[j] = 0;
      tree[treeIndex].remain[val] = 1;
      tree[treeIndex].prod = val;
      return;
    }
    const int mid = (lo + hi) / 2;
    if (i <= mid)
      update(2 * treeIndex + 1, lo, mid, i, val);
    else
      update(2 * treeIndex + 2, mid + 1, hi, i, val);
    tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
  }

  Node query(int treeIndex, int lo, int hi, int i, int j) const {
    if (i <= lo && hi <= j)  // [lo, hi] lies completely inside [i, j].
      return tree[treeIndex];
    if (j < lo || hi < i)  // [lo, hi] lies completely outside [i, j].
      return Node();
    const int mid = (lo + hi) / 2;
    return merge(query(2 * treeIndex + 1, lo, mid, i, j),
                 query(2 * treeIndex + 2, mid + 1, hi, i, j));
  }

  Node merge(const Node& left, const Node& right) const {
    Node node;
    node.prod = (left.prod * right.prod) % k;
    for (int i = 0; i < k; ++i)
      node.remain[i] = left.remain[i];
    for (int i = 0; i < k; ++i)
      node.remain[(i * left.prod) % k] += right.remain[i];
    return node;
  }
};

class Solution {
 public:
  vector<int> resultArray(vector<int>& nums, int k,
                          vector<vector<int>>& queries) {
    for (int& num : nums)
      num %= k;

    for (vector<int>& query : queries)
      query[1] %= k;

    const int n = nums.size();
    vector<int> ans;
    SegmentTree tree(nums, k);

    for (const vector<int>& query : queries) {
      const int index = query[0];
      const int value = query[1];
      const int start = query[2];
      const int x = query[3];
      tree.update(index, value);
      ans.push_back(tree.query(start, n - 1).remain[x]);
    }

    return ans;
  }
};
```

---

[View on LeetCode](https://leetcode.com/problems/find-x-value-of-array-ii/)