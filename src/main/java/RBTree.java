import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RBTree {
    public static void main(String[] args) {
        final RBTree tree = new RBTree();
        try (BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)))) {
            while (true) {
                try {
                    int value = Integer.parseInt(reader.readLine());
                    tree.add(value);
                    System.out.println("finish");
                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        private Node root;

        private enum ColorNode {
            RED, BLACK
        }

        private class Node {
            private Node left, right;
            private int value;
            private ColorNode color;


            @Override
            public String toString() {
                return "Node{" +
                        " value=" + value +
                        ", color=" + color +
                        '}';
            }
        }

        private Node leftSwap(Node node) {
            Node childLeft = node.left;
            Node child = childLeft.right;
            childLeft.right = node;
            node.left = child;
            childLeft.color = node.color;
            node.color = ColorNode.RED;
            return childLeft;
        }

        private Node rightSwap(Node node) {
            Node childRight = node.right;
            Node child = childRight.left;
            childRight.left = node;
            node.right = child;
            childRight.color = node.color;
            node.color = ColorNode.RED;
            return childRight;
        }


        private void swapColors(Node node) {
            node.right.color = ColorNode.BLACK;
            node.left.color = ColorNode.BLACK;
            node.color = ColorNode.RED;
        }
        public boolean add(int value){
            if(root != null){
                boolean result = addNode(root, value);
                root = balance(root);
                root.color = ColorNode.BLACK;
                return result;
            } else {
                root = new Node();
                root.color = ColorNode.BLACK;
                root.value = value;
                return true;
            }
        }
        public boolean addNode(Node node, int value) {
            if (node.value == value) {
                return false;
            } else {
                if (node.value > value) {
                    if (node.left != null) {
                        boolean result = addNode(node.left, value);
                        node.left = balance(node.left);
                        return result;
                    } else {
                        node.left = new Node();
                        node.left.color = ColorNode.RED;
                        node.left.value = value;
                        return true;
                    }
                } else {
                    if (node.right != null) {
                        boolean result = addNode(node.right, value);
                        node.right = balance(node.right);
                        return result;
                    } else {
                        node.right = new Node();
                        node.right.color = ColorNode.RED;
                        node.right.value = value;
                        return true;
                    }
                }
            }
        }


        private Node balance(Node node) {
            Node result = node;
            boolean needBalance;
            do {
                needBalance = false;
                if (result.right != null && result.right.color == ColorNode.RED &&
                        (result.left == null || result.left.color == ColorNode.BLACK)) {
                    needBalance = true;
                    result = rightSwap(result);
                }
                if (result.left != null && result.left.color == ColorNode.RED &&
                        (result.left.left != null && result.left.left.color == ColorNode.RED)) {
                    needBalance = true;
                    result = leftSwap(result);
                }
                if (result.left != null && result.left.color == ColorNode.RED &&
                        (result.right != null && result.right.color == ColorNode.RED)) {
                    needBalance = true;
                    swapColors(result);
                }
            }
            while (needBalance);
            return result;
        }
    }




