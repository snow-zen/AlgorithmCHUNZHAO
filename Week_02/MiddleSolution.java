package Week_02;

import java.util.*;

/**
 * 中等题目
 *
 * @author xuejun zeng
 */
public class MiddleSolution {

    /**
     * <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/">94. 二叉树的中序遍历</a>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode node, List<Integer> buf) {
        // 递归终止
        if (node == null) {
            return;
        }
        inorder(node.left, buf);
        buf.add(node.val);
        inorder(node.right, buf);
    }

    /**
     * <a href="https://leetcode-cn.com/problems/binary-tree-preorder-traversal/">144. 二叉树的前序遍历</a>
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode node, List<Integer> buf) {
        // 递归终止
        if (node == null) {
            return;
        }
        buf.add(node.val);
        preorder(node.left, buf);
        preorder(node.right, buf);
    }

    /**
     * <a href="https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/">429. N 叉树的层序遍历</a>
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 上层节点
        List<Node> preLayer = Collections.singletonList(root);
        while (!preLayer.isEmpty()) {
            // 记录当前层节点
            List<Node> currentLayer = new ArrayList<>();
            // 上层节点对应值
            List<Integer> preVal = new ArrayList<>();
            for (Node node : preLayer) {
                preVal.add(node.val);
                currentLayer.addAll(node.children);
            }
            res.add(preVal);
            // 当前层节点变为上层节点，继续遍历
            preLayer = currentLayer;
        }
        return res;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/chou-shu-lcof/">剑指 Offer 49. 丑数</a>
     *
     * @param n 第几个丑数
     * @return 返回第几个丑数对应的值
     */
    public int nthUglyNumber(int n) {
        // 质因子数组
        int[] primeFactor = {2, 3, 5};
        // 最小堆
        Queue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        // 当前顺序
        int count = 0;

        while (!heap.isEmpty()) {
            // 每次获取最小的丑数
            long num = heap.poll();

            // 命中元素
            if (++count >= n) {
                return (int) num;
            }

            // 将每次从堆提取的丑数乘以质因子得到新的丑数
            for (int p : primeFactor) {
                if (!heap.contains(p * num)) {
                    heap.offer(p * num);
                }
            }
        }
        return -1;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/top-k-frequent-elements/">347. 前 K 个高频元素</a>
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (k <= 0) {
            return new int[0];
        }
        // 记录每个数字出现的次数
        Map<Integer, Integer> buf = new HashMap<>();
        for (int num : nums) {
            buf.put(num, buf.getOrDefault(num, 0) + 1);
        }

        // 最大堆，以各数字的比较次数作为比较键
        Queue<int[]> minPq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2[1], p1[1]));

        // 插入堆
        for (Map.Entry<Integer, Integer> entry : buf.entrySet()) {
            minPq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        // 提取堆中最大的k个元素
        int[] res = new int[k];
        for (int i = 0; i < k && !minPq.isEmpty(); i++) {
            res[i] = minPq.poll()[0];
        }
        return res;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/">236. 二叉树的最近公共祖先</a>
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 终止条件：达到树底或找到对应元素
        if (root == null || root == p || root == q) {
            return root;
        }

        // 左边的最近公共先祖
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 右边的最近公共先祖
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // return left == null ? right : (right == null ? left : root); // 展开
        if (left == null) {
            // 左子树没有找到，说明两个节点都在右子树中，即right为公共祖先
            return right;
        }
        if (right == null) {
            // 右子树没有找到，说明两个节点都在左子树，即left为公共祖先
            return left;
        } else {
            // 左右子树都找到，说明两个节点一个在左子树，一个在右子树。
            // 因左右子树节点不存在重叠，所以两者返回的所谓公共祖先可能为目标节点自身
            // 因此返回当前节点作为两目标节点的公共祖先
            return root;
        }
    }

    /**
     * 记录中序数组中对应值的下标
     */
    private Map<Integer, Integer> indexMap;

    /**
     * <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. 从前序与中序遍历序列构造二叉树</a>
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 开始记录中序数组中对应值的下标
        indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeInternal(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     * 构建树的递归调用方法
     *
     * <p>
     * 对应某一根节点，其根节点、左子树节点、右子树节点在前序遍历中表现为
     * <pre>[根节点,[左子树节点],[右子树节点]]</pre>
     *
     * <p>
     * 对应某一根节点，其根节点、左子树节点、右子树节点在中序遍历中表现为
     * <pre>[[左子树节点],根节点,[右子树节点]]</pre>
     *
     * @param preorder 前序数组
     * @param inorder  中序数组
     * @param preLeft  前序数组扫描左边界
     * @param preRight 前序数组扫描右边界
     * @param inLeft   中序数组扫描左边界
     * @param inRight  中序数组扫描右边界
     */
    private TreeNode buildTreeInternal(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        // 递归终止条件
        if (preLeft > preRight) {
            return null;
        }

        // 获取根节点在中序数组中下标
        int inRoot = indexMap.get(preorder[preLeft]);

        // 创建根节点
        TreeNode root = new TreeNode(preorder[preLeft]);

        // 根节点左子树大小
        int sizeLeft = inRoot - inLeft;

        // 递归创建左子树
        // 左子树前序范围：[preLeft + 1, preLeft + sizeLeft]
        // 左子树中序范围：[inLeft, inRoot - 1]
        root.left = buildTreeInternal(preorder, inorder, preLeft + 1, preLeft + sizeLeft, inLeft, inRoot - 1);
        // 递归创建右子树
        // 右子树前序范围：[preLeft + sizeLeft + 1, preRight]
        // 右子树中序范围：[inRoot + 1, inRight]
        root.right = buildTreeInternal(preorder, inorder, preLeft + sizeLeft + 1, preRight, inRoot + 1, inRight);

        return root;
    }

    /**
     * <a href="https://leetcode-cn.com/problems/combinations/">77. 组合</a>
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {
        // 深度临界值
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 组合不同状态
        // 当i <= n - (k - path.size()) + 1，即可结束组合
        // k - path.size()为剩余可组合个数，而n - (k - path.size()) + 1即代表组合数的上限
        // 当超过上限，后续数字达不到所需组合个数，因此没有意义，则进行剪枝
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
        }
    }

    /**
     * <a href="https://leetcode-cn.com/problems/permutations/">46. 全排列</a>
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    private void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        if (first == n) {
            res.add(new ArrayList<>(output));
            return;
        }

        // 通过交换两两元素位置来达到不同的组合
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, res, first + 1);
            // 交换递归后，回溯状态
            Collections.swap(output, first, i);
        }
    }

    private boolean[] visits;

    /**
     * <a href="https://leetcode-cn.com/problems/permutations-ii/">47. 全排列 II</a>
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        visits = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, 0, perm);
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 当前元素已被访问过
            // 当前元素等于上一元素，同时上一元素还没被访问，则跳过
            if (visits[i] || (i > 0 && nums[i] == nums[i - 1] && !visits[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            visits[i] = true;
            backtrack(nums, res, idx + 1, perm);
            // 回溯
            visits[i] = false;
            perm.remove(idx);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
