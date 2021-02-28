package Week_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 困难题目
 *
 * @author snow-zen
 */
public class DiffSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/n-queens/">51. N 皇后</a>
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queen = new int[n];
        Arrays.fill(queen, -1);
        solve(0, queen, n, 0, 0, 0, res);
        return res;
    }

    private void solve(int row, int[] queen, int n, int cols, int diagonal1, int diagonal2, List<List<String>> res) {
        if (row == n) {
            res.add(print(queen, n));
            return;
        }

        // (col | diagonal1 | diagonal2)表示当前层不可放置的位置，对其进行取反
        // (1 << n) - 1)对其进行与操作，表示只取低n位的位置
        int availablePos = ((1 << n) - 1) & (~(cols | diagonal1 | diagonal2));
        // 获取最低位的位置
        int pos = availablePos & (-availablePos);
        while (availablePos != 0) {
            // 消除最低位1
            availablePos &= (availablePos - 1);
            // 获取最低位的位置转换为数组索引，例如 (100)2 - 1 = (11)2 统计1的个数即为索引
            int col = Integer.bitCount(pos - 1);

            queen[row] = col;
            solve(row + 1, queen, n, cols | pos, (diagonal1 | pos) << 1, (diagonal2 | pos) >> 1, res);
            queen[row] = -1;
        }
    }

    private List<String> print(int[] queen, int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queen[i]] = 'Q';
            res.add(String.valueOf(row));
        }
        return res;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/n-queens-ii/">52. N皇后 II</a>
     */
    public int totalNQueens(int n) {
        return total(0, n, 0, 0, 0);
    }

    private int total(int row, int n, int cols, int diagonal1, int diagonal2) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        int availablePos = ((1 << n) - 1) & (~(cols | diagonal1 | diagonal2));

        while (availablePos != 0) {
            int pos = availablePos & (-availablePos);
            availablePos &= (availablePos - 1);

            count += total(row + 1, n, cols | pos, (diagonal1 | pos) << 1, (diagonal2 | pos) >> 1);
        }
        return count;
    }
}
