class Solution {
    private int[] prefixSum;
    private int[] values;
    private Integer[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        this.values = stoneValue;
        this.memo = new Integer[n][n];
        this.prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        return dp(0, n - 1);
    }

    private int dp(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int maxScore = 0;
        for (int k = i; k < j; k++) {
            int leftSum = prefixSum[k + 1] - prefixSum[i];
            int rightSum = prefixSum[j + 1] - prefixSum[k + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + dp(i, k));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + dp(k + 1, j));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(dp(i, k), dp(k + 1, j)));
            }
        }

        return memo[i][j] = maxScore;
    }
}
