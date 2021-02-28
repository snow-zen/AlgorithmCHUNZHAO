package Week_05;

/**
 * 中等题目
 *
 * @author snow-zen
 */
public class MiddleSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/counting-bits/">338. 比特位计数</a>
     */
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            // i 对于 i/2 的值来说，进行了右移1位操作
            // i & 1相当于 i % 2 判断当前数奇偶性。如果为奇数则进行+1操作
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
