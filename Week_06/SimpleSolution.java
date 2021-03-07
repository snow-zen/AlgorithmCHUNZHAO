package Week_06;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 简单题目
 *
 * @author snow-zen
 */
public class SimpleSolution {

    /**
     * <a href="/problems/first-unique-character-in-a-string/">387. 字符串中的第一个唯一字符</a>
     */
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        char[] charArray = s.toCharArray();

        // 统计频率
        for (char c : charArray) {
            freq[c - 'a']++;
        }

        // 返回第一个频率为1的字符下标
        for (int i = 0; i < charArray.length; i++) {
            if (freq[charArray[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <a href="/problems/reverse-string-ii/">541. 反转字符串 II</a>
     */
    public String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i += 2 * k) {
            // 右指针防止越界
            int l = i, r = Math.min(charArray.length - 1, i + k - 1);
            while (l < r) {
                char tmp = charArray[l];
                charArray[l++] = charArray[r];
                charArray[r--] = tmp;
            }
        }

        return String.valueOf(charArray);
    }

    /**
     * <a href="/problems/reverse-words-in-a-string/">151. 翻转字符串里的单词</a>
     */
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        int len = s.length();
        int l, r = len - 1;

        while (r >= 0) {
            // 跳过后缀空格
            while (r >= 0 && s.charAt(r) == ' ') {
                r--;
            }

            l = r;

            // l移动到单词左边界
            while (l >= 0 && s.charAt(l) != ' ') {
                l--;
            }

            // 添加单词
            for (int i = l + 1; i <= r; i++) {
                res.append(s.charAt(i));
            }
            // 添加空格分割单词
            res.append(' ');

            r = l;
        }

        // 清除res后缀空格
        r = res.length() - 1;

        while (r >= 0 && res.charAt(r) == ' ') {
            res.deleteCharAt(r--);
        }

        return res.toString();
    }

    /**
     * <a href="/problems/reverse-only-letters/">917. 仅仅反转字母</a>
     */
    public String reverseOnlyLetters(String s) {
        Deque<Character> buf = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        char[] charArray = s.toCharArray();

        for (char c : charArray) {
            if (Character.isLetter(c)) {
                buf.push(c);
            }
        }

        for (char c : charArray) {
            if (Character.isLetter(c)) {
                res.append(buf.pop());
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

    /**
     * <a href="/problems/valid-palindrome-ii/">680. 验证回文字符串 Ⅱ</a>
     */
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        for (; l < r && s.charAt(l) == s.charAt(r); l++, r--) ;
        if (l < r) {
            return palindrome(s, l + 1, r) || palindrome(s, l, r - 1);
        } else {
            return true;
        }
    }

    private boolean palindrome(String s, int l, int r) {
        for (; l < r && s.charAt(l) == s.charAt(r); l++, r--) ;
        return l >= r;
    }
}
