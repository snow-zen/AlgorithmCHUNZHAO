package Week_03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 简单题目
 *
 * @author snow-zen
 */
public class SimpleSolution {

    /**
     * <a href="/problems/majority-element/">169. 多数元素</a>
     * <p>
     * 采用分治思想，时间复杂度为O(nlogn)，空间复杂度为O(logn)
     */
    public int majorityElement(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int lo, int hi) {
        // 终止条件
        if (lo == hi) {
            return nums[lo];
        }

        int mid = lo + (hi - lo) / 2;
        // 分割
        int left = majorityElement(nums, lo, mid);
        int right = majorityElement(nums, mid + 1, hi);

        if (left == right) {
            return left;
        }

        // 组合
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * <a href="/problems/lemonade-change/">860. 柠檬水找零</a>
     * <p>
     * 贪心算法求解，时间复杂度O(n)，空间复杂度O(1)
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                // 不用找
                five++;
            } else if (bill == 10) {
                // 付10元，找5元
                five--;
                ten++;
            } else if (ten > 0) {
                // 付20元，有10元，找1张10元、1张5元
                ten--;
                five--;
            } else {
                // 付20元，没有10元，找3张5元
                five -= 3;
            }
            // 由于每次找零都会减5元数量
            if (five < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * <a href="/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a>
     * <p>
     * 贪心算法求解，时间复杂度O(n)，空间复杂度O(1)
     */
    public int maxProfit(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            // 寻找局部最小值
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            int min = prices[i++];
            // 寻找局部最大值
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }

    /**
     * <a href="/problems/assign-cookies/">455. 分发饼干</a>
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) {
            if (g[i] <= s[j]) {
                // 胃口比饼干尺寸小的
                i++;
            }
        }
        return i;
    }

    /**
     * <a href="/problems/walking-robot-simulation/">874. 模拟行走机器人</a>
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + " " + obstacle[1]);
        }
        // 四方每次位移距离
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int d = 0, x = 0, y = 0, result = 0;
        for (int command : commands) {
            if (command == -1) { // 右转
                d++;
                if (d == 4) {
                    d = 0;
                }
            } else if (command == -2) { // 左转
                d--;
                if (d == -1) {
                    d = 3;
                }
            } else {
                // 移动且没有碰到障碍物
                while (command-- > 0 && !set.contains((x + dirs[d][0]) + " " + (y + dirs[d][1]))) {
                    x += dirs[d][0];
                    y += dirs[d][1];
                }
            }
            result = Math.max(result, x * x + y * y);
        }
        return result;
    }
}
