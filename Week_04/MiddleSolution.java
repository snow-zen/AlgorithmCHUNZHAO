package Week_04;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 中等题目
 *
 * @author snow-zen
 */
public class MiddleSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/minimum-path-sum/">64. 最小路径和</a>
     * <p>
     * 状态转移方程：dp[i][j] = min(dp[i][j-1],dp[i-1][j]) + grid[i][j];
     *
     * @param grid 网格
     * @return 路径数字总和最小
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // 处理边界问题，dp数组行列都+1
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (i == 1) {
                    dp[i][j] = dp[i][j - 1] + grid[i - 1][j - 1];
                } else if (j == 1) {
                    dp[i][j] = dp[i - 1][j] + grid[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i - 1][j - 1];
                }
            }
        }
        return dp[row][col];
    }

    /**
     * <a href="https://leetcode-cn.com/problems/decode-ways/">91. 解码方法</a>
     */
    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        int[] dp = new int[len];

        char[] charArr = s.toCharArray();
        if (charArr[0] == '0') {
            return 0;
        }
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            if (charArr[i] != '0') {
                dp[i] = dp[i - 1];
            }

            int num = 10 * (charArr[i - 1] - '0') + (charArr[i] - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i]++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len - 1];
    }

    /**
     * <a href="https://leetcode-cn.com/problems/maximal-square/">221. 最大正方形</a>
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[height + 1][width + 1];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '1') {
                    // 从上边、左上、左边选出最小值+1
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                    maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
                }
            }
        }

        return maxSide * maxSide;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/task-scheduler/">621. 任务调度器</a>
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxExec = 0;

        for (char task : tasks) {
            int exec = freq.getOrDefault(task, 0) + 1;
            freq.put(task, exec);
            maxExec = Math.max(maxExec, exec);
        }

        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    /**
     * <a href="https://leetcode-cn.com/problems/palindromic-substrings/">647. 回文子串</a>
     */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
}
