package Week_01;

/**
 * 困难题目
 *
 * @author xuejun zeng
 */
public class DiffSolution {

    /**
     * 接雨水
     */
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (leftMax < rightMax) {
                // 此时对于left下标位置来说，leftMax边界是可信的。
                // 即使leftMax到rightMax中间有其他更大的，也不影响当前结果
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];
                left++;
            } else {
                // 此时对于right下标位置来说，rightMax边界是可信的。
                // 即使leftMax到rightMax中间有其他更大的，也不影响当前结果
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
