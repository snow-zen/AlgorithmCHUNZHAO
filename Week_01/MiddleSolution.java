package Week_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中等题目
 *
 * @author xuejun zeng
 */
public class MiddleSolution {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> buf = new HashMap<>();
        for (String str : strs) {
            char[] count = new char[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            String key = String.valueOf(count);
            if (!buf.containsKey(key)) {
                buf.put(key, new ArrayList<>());
            }
            buf.get(key).add(str);
        }
        return new ArrayList<>(buf.values());
    }

}
