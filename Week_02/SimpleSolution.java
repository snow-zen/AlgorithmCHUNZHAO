package Week_02;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单题目
 *
 * @author xuejun zeng
 */
public class SimpleSolution {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    /**
     * <a href="/problems/n-ary-tree-postorder-traversal/">590. N叉树的后序遍历</a>
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postorderInternal(root, res);
        return res;
    }

    private void postorderInternal(Node node, List<Integer> buf) {
        // 递归终止
        if (node == null) {
            return;
        }
        if (node.children != null) {
            for (Node child : node.children) {
                postorderInternal(child, buf);
            }
        }
        buf.add(node.val);
    }

    /**
     * <a href="/problems/n-ary-tree-preorder-traversal/">589. N叉树的前序遍历</a>
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        preorderInternal(root, res);
        return res;
    }

    private void preorderInternal(Node node, List<Integer> buf) {
        // 递归终止
        if (node == null) {
            return;
        }
        buf.add(node.val);
        if (node.children != null) {
            for (Node child : node.children) {
                preorderInternal(child, buf);
            }
        }
    }
}
