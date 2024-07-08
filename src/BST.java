import java.util.Scanner;

public class BST {
    private TreeNode root;

    public BST() {
        root = null;
    }

    public BST(TreeNode original) {
        root = copy(original);
    }

    private TreeNode copy(TreeNode p) {
        if (p == null) {
            return null;
        }
        TreeNode temp = new TreeNode();
        temp.data = p.data;
        temp.left = copy(p.left);
        temp.right = copy(p.right);
        return temp;
    }

    public boolean eqauls(BST other) {
        return equals(root, other.root);
    }

    private static boolean equals(TreeNode first, TreeNode second) {
        if (first == null && second == null) {
            return true;
        }
        return first != null && second != null
                && first.data == second.data
                && equals(first.left, second.left)
                && equals(first.right, second.right);
    }

    public TreeNode search(int key) {
        return search(root, key);
    }

    private TreeNode search(TreeNode p, int key) {
        if (p == null) {
            return null;
        } else if (p.data == key) {
            return p;
        } else if (p.data < key) {
            return search(p.left, key);
        } else {
            return search(p.right, key);
        }
    }

    public TreeNode nonRecursiveSearch(int key) {
        TreeNode p = root;
        while (p != null) {
            if (p.data == key) {
                return p;
            } else if (p.data < key) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public boolean insert(int key) {
        TreeNode p = root;
        TreeNode q = null;
        while (p != null) {
            q = p;
            if (key == p.data) {
                return false;
            }
            if (key < p.left.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        TreeNode temp = new TreeNode(key);
        if (root == null) {
            root = temp;
        } else if (key < q.data) {
            q.left = temp;
        } else {
            q.right = temp;
        }
        return true;
    }

    public static BST readBST() {
        BST bst = new BST();
        java.util.Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer number, negative or zero to quit: ");
        int key = input.nextInt();
        while (key > 0) {
            bst.insert(key);
            System.out.print("Enter an integer number, negative or zero to quit: ");
            key = input.nextInt();
        }
        return bst;
    }

    public static BST createBST(int... args) {
        BST bst = new BST();
        for (int arg : args) {
            bst.insert(arg);
        }
        return bst;
    }

    public boolean delete(int key) {
        TreeNode p = root;
        TreeNode q = null;
        while (p != null && p.data != key) {
            q = p;
            if (key < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return false;
        }

        if (p.left != null && p.right != null) {
            TreeNode s = p.left;
            TreeNode ps = p;
            while (s.right != null) {
                ps = s;
                s = s.right;
            }
            p.data = s.data;
            p = s;
            q = ps;
        }

        TreeNode c;
        if (p.left == null) {
            c = p.right;
        } else {
            c = p.left;
        }

        if (p == root) {
            root = c;
        } else {
            if (p == q.left) {
                q.left = c;
            } else {
                q.right = c;
            }
        }

        return true;
    }

    private boolean isBst(TreeNode p, int min, int max) {
        if (p == null) {
            return true;
        }
        if (p.data < min || p.data > max) {
            return false;
        }
        return isBst(p.left, min, p.data - 1) && isBst(p.right, min, p.data + 1);
    }
}
