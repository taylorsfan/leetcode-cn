public class Solution20200531 {


    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricChiild(root.left, root.right);
    }

    private static boolean isSymmetricChiild(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left==null||right==null||left.val!=right.val)return false;
        return isSymmetricChiild(left.right, right.left) && isSymmetricChiild(left.left, right.right);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 4, 4, 3};
        TreeNode root = createBinaryTreeArray(arr, 0);
        System.out.println(isSymmetric(root));
        System.out.println("前序遍历");
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.left.left.val);
        System.out.println(root.left.right.val);
        System.out.println(root.right.val);
        System.out.println(root.right.left.val);
        System.out.println(root.right.right.val);
        System.out.println("前序遍历");
        //前序遍历
        print(root);
    }

    /**
     * 数组变成树
     */
    private static TreeNode createBinaryTreeArray(int[] arr, int index) {
        if (index < arr.length) {
            TreeNode treeNode = new TreeNode(arr[index]);
            treeNode.left = createBinaryTreeArray(arr, 2 * index + 1);
            treeNode.right = createBinaryTreeArray(arr, 2 * index + 2);
            return treeNode;
        }
        return null;
    }

    private static TreeNode print(TreeNode treeNode) {
        TreeNode left;
        TreeNode right;
        System.out.println(treeNode.val);
        if (treeNode.left != null) {
            left = treeNode.left;
            print(left);
        }
        if (treeNode.right != null) {
            right = treeNode.right;
            print(right);
        }
        return null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}