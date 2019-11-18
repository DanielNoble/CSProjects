// Build a BST w/ a minimum height of 5
// Create methods to search for a number, remove a number, add a number, and print the tree
// User should be able to input function they want done with values


import com.sun.deploy.security.SelectableSecurityManager;

public class BST {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);

    }


    private static class BinaryTree {
        Node root;

        private BinaryTree(int value) {
            root = new Node(value);
        }

        public boolean contains(int value) {
            return contains(root, value);
        }

        private boolean contains(Node root, int value) {
            if (root == null)
                return false;
            else if (root.data == value)
                return true;
            else if (root.data > value)
                return contains(root.left,value);
            else
                return contains(root.right,value);
        }

        public void add(int value) {
            add(root, value);
        }

        private void add(Node root, int value) {
            if (root == null)
                root = new Node(value);
            else if (root.data > value) {
                if (root.left == null)
                    root.left = new Node(value);
                else
                    add(root.left,value);
            }
            else {
                if (root.right == null)
                    root.right = new Node(value);
                else
                    add(root.right,value);
            }
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        private Node(int root) {
            this(root,null,null);
        }

        private Node(int root, Node left, Node right) {
            root = root;
            left = left;
            right = right;
        }
    }
}