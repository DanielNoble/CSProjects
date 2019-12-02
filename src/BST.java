// Build a BST w/ a minimum height of 5
// Create methods to search for a number, remove a number, add a number, and print the tree
// User should be able to input function they want done with values

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BST {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);
        tree.add(6);
        tree.add(14);
        tree.add(7);
        tree.add(11);
        tree.add(15);
        tree.add(17);
        tree.add(18);
        tree.add(16);
        tree.print();
    }


    private static class BinaryTree {
        Node root;
        static final int MINSPACE = 1;

        public BinaryTree(int value) {
            root = new Node(value);
        }

        public boolean contains(int value) {
            return contains(root, value);                                                                               // User in main doesn't call with node, so create new method to access node
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
            root = add(root, value);
        }

        private Node add(Node node, int value) {
            if (node == null)
                node = new Node(value);
            else if (node.data > value)
                node.left = add(node.left, value);
            else
                node.right = add(node.right, value);
            return node;
        }

        public void remove(int value) {
            remove(root, value);
        }

        private Node remove(Node root, int value) {
            if (root == null)
                return null;
            if (root.data > value) {
                root.left = remove(root.left, value);
            } else if (root.data < value) {
                root.right = remove(root.right, value);
            } else {                                                                                                    // Found value to remove
                if (root.right == null)                                                                                 // If there's no right, return left
                    return root.left;
                else if (root.left == null)                                                                             // If there's no left, return right
                    return root.right;
                else {
                    root.data = getMin(root.right);                                                                     // Set the smallest value greater than the removed value for minimum reorganization
                    remove(root.right, root.data);                                                                      // Remove the previous value from its original spot
                }
            }
            return root;
        }


        private int getMin(Node node) {                                                                                 // Proceeds left recursively until data is null
            if (node.left == null)
                return node.data;
            else
                return getMin(node.left);
        }

        private int maxDepth(Node node) {                                                                               // print helper class
            if (node == null)
                return 0;
            else {
                int lDepth = maxDepth(node.left);
                int rDepth = maxDepth(node.right);
                return (lDepth > rDepth ? lDepth : rDepth) + 1;                                                         // Depth
            }
        }

        private int leftDepth(Node node) {                                                                              // print helper class
            if (node == null)
                return 0;
            else {
                return leftDepth(node.left) + 1;
            }
        }

        private int getMaxPrintWidth(Node node) {
            if (node == null)
                return 0;

            int self = getPrintWidth(node);
            int left = getMaxPrintWidth(node.left);
            int right = getMaxPrintWidth(node.right);

            return Math.max(Math.max(self, left), right);
        }

            private int getPrintWidth(Node node) {
                if (node == null)
                    return 0;

                return Integer.toString(node.data).length();
            }

        private int calcLeadingSpace(int height, int maxWidth) {
            int factor = (int) (Math.pow(2,height) - 1);
            return factor * (maxWidth + MINSPACE) / 2;
        }

        private int calcBetweenSpaces(int height, int maxWidth) {
            int factor = (int) (Math.pow(2,height));
            return (factor - 1) * maxWidth + factor * MINSPACE;
        }

        public void print() {
            if (root == null)
                System.out.println("Binary tree is empty");
            else {
                int leftDepth = leftDepth(root);
                int maxDepth = maxDepth(root);
                int maxPrintWidth = getMaxPrintWidth(root);

                List<Node> nodes = Collections.singletonList(root);
                printHelper(nodes, maxPrintWidth, maxDepth - 1, maxDepth - leftDepth);
            }
        }

        private void printHelper(List<Node> nodes, int maxWidth, int height, int leftmostHeight) {
            int leadingSpace = calcLeadingSpace(height, maxWidth);
            int betweenSpace = calcBetweenSpaces(height, maxWidth);
            int leftLeadingSpace = calcLeadingSpace(leftmostHeight,maxWidth);
            int index = 0;
            int length = nodes.size();

            if (leadingSpace >= leftLeadingSpace)
                printSpaces(leadingSpace - leftLeadingSpace);
            else {
                leftLeadingSpace -= leadingSpace;
                while (leftLeadingSpace > 0) {
                    leftLeadingSpace -= maxWidth + betweenSpace;
                    index++;
                }

                if (leftLeadingSpace < 0)
                    printSpaces(-leftLeadingSpace);
            }

            for (; index < length; index++) {
                printValue(nodes.get(index),maxWidth);
                printSpaces(betweenSpace);
            }

            System.out.print("\n");


            if (height != 0) {
                List<Node> newNodes = createNextRow(nodes);
                printHelper(newNodes, maxWidth, height - 1, leftmostHeight);
            }
        }

        private void printSpaces(int spaces) {
            for (int i = 0; i < spaces; i++)
                System.out.print(" ");
        }

        private void printValue(Node node, int maxWidth) {
            if (node == null)
                printSpaces(maxWidth);
            else {
                int printWidth = getPrintWidth(node);
                int spaces = maxWidth - printWidth;
                if (spaces == 0)
                    System.out.print(node.data);
                else  {
                    int halfSpaces = spaces / 2;
                    printSpaces(halfSpaces);
                    System.out.print(node.data);
                    printSpaces(halfSpaces + ((spaces % 2 == 0) ? 0 : 1));
                }
            }
        }

        private List<Node> createNextRow(List<Node> oldNodes) {
            List<Node> newNodes = new ArrayList<>(2 * oldNodes.size());

            for (Node node : oldNodes) {
                if (node == null) {
                    newNodes.add(null);
                    newNodes.add(null);
                }
                else {
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                }
            }
            return newNodes;
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        private Node(int data) {
            this(data,null,null);
        }

        private Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}