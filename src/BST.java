// Build a BST w/ a minimum height of 5
// Create methods to search for a number, remove a number, add a number, and print the tree
// User should be able to input function they want done with values


public class BST {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);

    }


    private static class BinaryTree {
        Node root;

        private BinaryTree(int value) {
            root = new Node(value);
        }

        private void add(Node root, int value) {
            if (root == null)
                root.data = value;
            else if (root.data > value)
                add(root.right, value);
            else
                add(root.left, value);
        }

        private boolean find(Node root, int value) {
            if (root == null)
                return false;
            else if (root.data == value) {
                return true;
            }
            else if (root.data > value)
                return find(root.right,value);
            else
                return find(root.left,value);
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        private Node(int root) {
            data = root;
            left = null;
            right = null;
        }
    }
}
