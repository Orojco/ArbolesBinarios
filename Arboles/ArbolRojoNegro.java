class NodoRojoNegro<T extends Comparable<T>> {
    private T data;
    private NodoRojoNegro<T> parent;
    private NodoRojoNegro<T> left;
    private NodoRojoNegro<T> right;
    private boolean color;

    public NodoRojoNegro(T data) {
        this.data = data;
        this.color = true; // Los nuevos nodos son rojos por defecto
    }

    public T getData() { return data; }
    public NodoRojoNegro<T> getParent() { return parent; }
    public void setParent(NodoRojoNegro<T> parent) { this.parent = parent; }
    public NodoRojoNegro<T> getLeft() { return left; }
    public void setLeft(NodoRojoNegro<T> left) { this.left = left; }
    public NodoRojoNegro<T> getRight() { return right; }
    public void setRight(NodoRojoNegro<T> right) { this.right = right; }
    public boolean getColor() { return color; }
    public void setColor(boolean color) { this.color = color; }
}

public class ArbolRojoNegro<T extends Comparable<T>> {
    private NodoRojoNegro<T> root;
    private final NodoRojoNegro<T> TNULL;

    public ArbolRojoNegro() {
        TNULL = new NodoRojoNegro<>(null);
        TNULL.setColor(false); // TNULL es negro
        root = TNULL;
    }

    private void inOrderHelper(NodoRojoNegro<T> node) {
        if (node != TNULL) {
            inOrderHelper(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderHelper(node.getRight());
        }
    }

    private void balanceInsert(NodoRojoNegro<T> k) {
        NodoRojoNegro<T> u;
        while (k.getParent().getColor() == true) {
            if (k.getParent() == k.getParent().getParent().getRight()) {
                u = k.getParent().getParent().getLeft();
                if (u.getColor() == true) {
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()) {
                        k = k.getParent();
                        rotateRight(k);
                    }
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    rotateLeft(k.getParent().getParent());
                }
            } else {
                u = k.getParent().getParent().getRight();
                if (u.getColor() == true) {
                    u.setColor(false);
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()) {
                        k = k.getParent();
                        rotateLeft(k);
                    }
                    k.getParent().setColor(false);
                    k.getParent().getParent().setColor(true);
                    rotateRight(k.getParent().getParent());
                }
            }
            if (k == root) {
                break;
            }
        }
        root.setColor(false);
    }

    public void insert(T key) {
        NodoRojoNegro<T> node = new NodoRojoNegro<>(key);
        node.setParent(null);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setColor(true);

        NodoRojoNegro<T> y = null;
        NodoRojoNegro<T> x = root;

        while (x != TNULL) {
            y = x;
            if (node.getData().compareTo(x.getData()) < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        node.setParent(y);
        if (y == null) {
            root = node;
        } else if (node.getData().compareTo(y.getData()) < 0) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }

        if (node.getParent() == null) {
            node.setColor(false);
            return;
        }

        if (node.getParent().getParent() == null) {
            return;
        }

        balanceInsert(node);
    }

    private void rotateLeft(NodoRojoNegro<T> x) {
        NodoRojoNegro<T> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != TNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rotateRight(NodoRojoNegro<T> x) {
        NodoRojoNegro<T> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != TNULL) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    public void printInOrder() {
        inOrderHelper(this.root);
    }

    public static void main(String[] args) {
        ArbolRojoNegro<Integer> tree = new ArbolRojoNegro<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(25);
        tree.insert(5);
        System.out.println("Inorder traversal of the constructed Red-Black tree is:");
        tree.printInOrder();
    }
}
