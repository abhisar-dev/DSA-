class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        m, n = len(grid) - 1, len(grid[0]) - 1
        @cache
        def dfs(i, j, p):
            if i == m and j == n: return 1
            if i > m or j > n: return 0
            if grid[i][j] == 0: return (dfs(i + 1, j, 'd') + dfs(i, j + 1, 'r')) % 1000000007
            if p == 'd': return dfs(i, j + 1, 'r') % 1000000007
            if p == 'r': return dfs(i + 1, j, 'd') % 1000000007
        return dfs(0, 0, '')

# 作者：_G_
# 链接：https://leetcode.cn/problems/twisted-mirror-path-count/solutions/3772153/ji-yi-hua-sou-suo-by-admiring-meninskyul-v0ui/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。