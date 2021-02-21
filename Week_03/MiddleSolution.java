package Week_03;

import java.util.*;

/**
 * 中等题目
 *
 * @author snow-zen
 */
public class MiddleSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/powx-n/">50. Pow(x, n)</a>
     * <p>
     * 递归求解，时间复杂度O(logn)，空间复杂度O(logn)
     */
    public double myPow(double x, int n) {
        return n < 0 ? 1.0 / pow(x, -n) : pow(x, n);
    }

    private double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double res = pow(x, n / 2);
        return n % 2 == 0 ? res * res : res * res * x;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/subsets/">78. 子集</a>
     * <p>
     * 回溯算法求解，时间复杂度O(n * 2^n)，空间复杂度O(n)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsInternal(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void subsetsInternal(int first, int[] nums, List<Integer> buf, List<List<Integer>> res) {
        // 缓存结果
        res.add(new ArrayList<>(buf));
        for (int i = first; i < nums.length; i++) {
            buf.add(nums[i]);
            subsetsInternal(i + 1, nums, buf, res);
            // 回溯
            buf.remove(buf.size() - 1);
        }
    }

    /**
     * <a href="https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/">17. 电话号码的字母组合</a>
     * <p>
     * 回溯求解
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        letterCombine(0, digits, sb, res);
        return res;
    }

    private Map<Character, List<Character>> map = new HashMap<>();

    {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    private void letterCombine(int index, String digits, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char digit = digits.charAt(index);
        for (Character c : map.get(digit)) {
            sb.append(c);
            letterCombine(index + 1, digits, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * <a href="https://leetcode-cn.com/problems/word-ladder/">127. 单词接龙</a>
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 缓存
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 定义队列
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // 访问记录
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                // 是否匹配endWord
                if (matchWord(word, endWord, queue, visited, wordSet)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    private boolean matchWord(String word, String endWord, Queue<String> queue,
                              Set<String> visited, Set<String> wordSet) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            char originChar = charArray[i];
            // 遍历26个字母
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }
                charArray[i] = k;
                String nextWord = String.valueOf(charArray);
                // 字典缓存中存在
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    // 没有被访问过
                    if (!visited.contains(nextWord)) {
                        queue.offer(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            charArray[i] = originChar;
        }
        return false;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/number-of-islands/">200. 岛屿数量</a>
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '0') {
                    num(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void num(char[][] grid, int i, int j) {
        int xLen = grid.length;
        int yLen = grid[0].length;

        if (i < 0 || j < 0 || i >= xLen || j >= yLen || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        num(grid, i - 1, j);
        num(grid, i + 1, j);
        num(grid, i, j - 1);
        num(grid, i, j + 1);
    }

    int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    /**
     * <a href="https://leetcode-cn.com/problems/minesweeper/">529. 扫雷游戏</a>
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            bfs(board, x, y);
        }
        return board;
    }

    public void bfs(char[][] board, int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        // 访问标识
        boolean[][] vis = new boolean[board.length][board[0].length];

        queue.offer(new int[]{sx, sy});
        vis[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cnt = 0, x = pos[0], y = pos[1];
            // 记录周围8格的地雷数
            for (int i = 0; i < 8; i++) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                    continue;
                }
                if (board[tx][ty] == 'M') {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                // 存在地雷
                board[x][y] = (char) (cnt + '0');
            } else {
                // 不存在地雷
                board[x][y] = 'B';
                // 把周围格子加入搜索范围
                for (int i = 0; i < 8; i++) {
                    int tx = x + dirX[i];
                    int ty = y + dirY[i];
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                        continue;
                    }
                    queue.offer(new int[]{tx, ty});
                    vis[tx][ty] = true;
                }
            }
        }
    }

    /**
     * <a href="https://leetcode-cn.com/problems/jump-game/">55. 跳跃游戏</a>
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightMost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array/">33. 搜索旋转排序数组</a>
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) { // [l,mid]有序
                if (nums[0] <= target && target < nums[mid]) { // 如果target在[l,mid]区间内
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { // [l,mid]无序
                if (nums[mid] < target && target <= nums[n - 1]) { // 如果target在[mid,n-1]区间内
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/search-a-2d-matrix/">74. 搜索二维矩阵</a>
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int xLen = matrix.length;
        int yLen = matrix[0].length;
        int col = yLen - 1;
        int row = 0;

        while (row < xLen && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/">153. 寻找旋转排序数组中的最小值</a>
     */
    public int findMin(int[] nums) {
        return findMinInternal(nums, 0, nums.length - 1);
    }

    private int findMinInternal(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }

        int mid = lo + (hi - lo) / 2;
        int res1 = findMinInternal(nums, lo, mid);
        int res2 = findMinInternal(nums, mid + 1, hi);
        return Math.min(res1, res2);
    }

}
