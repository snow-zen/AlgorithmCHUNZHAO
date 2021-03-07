package Week_06;

/**
 * 中等题目
 *
 * @author snow-zen
 */
public class MiddleSolution {

    /**
     * <a href="/problems/string-to-integer-atoi/">8. 字符串转换整数 (atoi)</a>
     */
    public int myAtoi(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        int i = 0;

        // 清除前导空格
        while (i < len && charArray[i] == ' ') {
            i++;
        }

        if (i == len) {
            return 0;
        }

        // 记录符号
        int sign = 1;
        if (charArray[i] == '-') {
            sign = -1;
            i++;
        } else if (charArray[i] == '+') {
            i++;
        }

        // 转换字符为整数
        int res = 0;
        while (i < charArray.length) {
            char c = charArray[i];

            if (!(c >= '0' && c <= '9')) {
                break;
            }

            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (c - '0');
            i++;
        }
        return res;
    }
}
