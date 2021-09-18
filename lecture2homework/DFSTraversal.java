package lecture2homework;

/* Breadth First Traversal (Or Level Order Traversal)
* Depth First Traversals
*   Inorder Traversal (Left-Root-Right)
*   Preorder Traversal (Root-Left-Right)
*   Postorder Traversal (Left-Right-Root).
*
*                                   1
*                                  /  \
*                                 2    3
*                               /   \
*                              4     5
*
* Breadth First Traversal : 1 2 3 4 5
*
* Depth First Traversals:
      Preorder Traversal : 1 2 4 5 3
      Inorder Traversal  :  4 2 5 1 3
      Postorder Traversal : 4 5 2 3 1
*
* */

import com.sun.source.tree.Tree;

import java.util.*;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}

public class DFSTraversal {
    public class PreOrderRecursively {
        public List<Integer> preOrder(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            res.add(root.key);
            helper(root.left, res);
            helper(root.right, res);
        }
        // Time: O(n); Space: O(h); h is the height of the tree.
    }

    public class PreOrderIteratively {
        public List<Integer> preOrder(TreeNode root) {
            List<Integer> preorder = new ArrayList<Integer>();
            if (root == null) {
                return preorder;
            }
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.offerFirst(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pollFirst();
                // the left subtree should be traversed before right subtree,
                // since stack is LIFO, we should push right into the stack first,
                // so for the next step the top element of the stack is the left
                // subtree.
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                }
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                }
                preorder.add(cur.key);
            }
            return preorder;
        }
        // Time: O(n); Space: O(height)
    }

    public class InOrderRecursively {
        public List<Integer> inOrder(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            helper(root.left, res);
            res.add(root.key);
            helper(root.right, res);
        }
        // Time: O(n); Space: O(height of the tree)
    }

    public class InOrderIteratively {
        public List<Integer> inOrder(TreeNode root) {
            List<Integer> inorder = new ArrayList<Integer>();
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                // always try go to the left side to see if there is any node
                // should be traversed before the cur node, cur node is stored
                // on stack since it has not been traversed yet.
                if (cur != null) {
                    stack.offerFirst(cur);
                    cur = cur.left;
                } else {
                    // if can not go left, meaning all the nodes on the left side of
                    // the top node on stack have been traversed, the next traversing
                    // node should be the top node on stack.
                    cur = stack.pollFirst();
                    inorder.add(cur.key);
                    // the next subtree we want to traverse if cur.right
                    cur = cur.right;
                }
            }
            return inorder;
        }
        // Time: O(n); Space: O(height)
    }

    public class PostOrderRecursively {
        public List<Integer> postOrder(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            helper(root.left, res);
            helper(root.right, res);
            res.add(root.key);
        }
        // O(n); O(height)
    }

    public class PostOrderIteratively {
        // Method 1: post-order is the reverse order of pre-order with traversing
        // rught subtree before traversing left subtree.
        public List<Integer> postOrderI(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if (root == null) {
                return result;
            }
            Deque<TreeNode> preOrder = new LinkedList<TreeNode>();
            preOrder.offerFirst(root);
            while (!preOrder.isEmpty()) {
                TreeNode current = preOrder.pollFirst();
                // conduct the result for the special pre-order traversal.
                result.add(current.key);
                // in pre-order the right subtree will be traversed before the
                // left subtree so pushing left child first.
                if (current.left != null) {
                    preOrder.offerFirst(current.left);
                }
                if (current.right != null) {
                    preOrder.offerFirst(current.right);
                }
            }
            // reverse the pre-order traversal sequence to get the post-order result.
            Collections.reverse(result);
            return result;
        }

        // Method 2: check the relation between the current node and the previous node
        // to determine which direction should go next.
        public List<Integer> postOrderII(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if (root == null) {
                return result;
            }
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.offerFirst(root);
            // to record the previous node on the way of DFS so that
            // we can determine the direction.
            TreeNode prev = null;
            while (!stack.isEmpty()) {
                TreeNode cur = stack.peekFirst();
                // if we are going down, either left/right direction.
                if (prev == null || cur == prev.left || cur == prev.right) {
                    // if we can still go down, try go left first.
                    if (cur.left != null) {
                        stack.offerFirst(cur.left);
                    } else if (cur.right != null) {
                        stack.offerFirst(cur.right);
                    } else {
                        // if we cannot go either way, meaning cur is a leaf node.
                        stack.pollFirst();
                        result.add(cur.key);
                    }
                } else if (prev == cur.right || prev == cur.left && cur.right == null) {
                    // if we are going up from the right side or going up from the left side
                    // but we cannot go right afterwards.
                    stack.pollFirst();
                    result.add(cur.key);
                } else {
                    // otherwise, we are going up from the left side and we can go down right side.
                    stack.offerFirst(cur.right);
                }
                prev = cur;
            }
            return result;
        }
        // O(n); O(height)
    }




}
