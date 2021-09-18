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

/* Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
 */

import com.sun.jdi.IntegerType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BFSTraversal {
    public TreeNode reconstruct(int[] level, int[] in) {
        // Assumptions: level, in are not null,
        // there is no duplicate in the binary tree.
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        List<Integer> lList = new ArrayList<Integer>();
        for (int num : level) {
            lList.add(num);
        }
        return helper(lList, inMap);
    }

    private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
        if (level.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(level.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (inMap.get(num) < inMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inMap);
        root.right = helper(right, inMap);
        return root;
    }
}
