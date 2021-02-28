package Week_05;

/**
 * 简单题目
 *
 * @author snow-zen
 */
public class SimpleSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/number-of-1-bits/">191. 位1的个数</a>
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // 消除最低位1
            count++;
        }
        return count;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/power-of-two/">231. 2的幂</a>
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        long x = n; // 防止溢出
        // 2^n在二进制中只有1位为1，其他位都为0
        // 在通过 x & (x - 1) 消除最后一位1后，结果必定为0
        return (x & (x - 1)) == 0;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/reverse-bits/">190. 颠倒二进制位</a>
     */
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 1; i <= 32; i++) {
            res = res << 1;
            res = (n & 1) | res; // (n & 1)表示只取第一位，或上res表示合并最低位
            n = n >> 1;
        }
        return res;
    }
}
