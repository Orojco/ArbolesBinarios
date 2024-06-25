class NodoAVL<T extends Comparable<T>> {
    T data;
    int height;
    NodoAVL<T> left, right;

    NodoAVL(T data) {
        this.data = data;
        height = 1;
    }
}

public class AVLTree<T extends Comparable<T>> {
    private NodoAVL<T> root;

    private int height(NodoAVL<T> N) {
        if (N == null) return 0;
        return N.height;
    }

    private NodoAVL<T> rightRotate(NodoAVL<T> y) {
        NodoAVL<T> x = y.left;
        NodoAVL<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private NodoAVL<T> leftRotate(NodoAVL<T> x) {
        NodoAVL<T> y = x.right;
        NodoAVL<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private int getBalance(NodoAVL<T> N) {
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }

    public void insert(T key) {
        root = insertRec(root, key);
    }

    private NodoAVL<T> insertRec(NodoAVL<T> node, T key) {
        if (node == null) return new NodoAVL<>(key);

        if (key.compareTo(node.data) < 0)
            node.left = insertRec(node.left, key);
        else if (key.compareTo(node.data) > 0)
            node.right = insertRec(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.data) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.right.data) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private void inOrder(NodoAVL<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(5);
        tree.insert(25);
        tree.insert(15);
        System.out.println("Inorder traversal of the constructed AVL tree is:");
        tree.inOrderTraversal();
    }
}
