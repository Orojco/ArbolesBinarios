public class NodoArbol<T> {
    private T data;
    private NodoArbol<T> left;
    private NodoArbol<T> right;

    public NodoArbol(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodoArbol<T> getLeft() {
        return left;
    }

    public void setLeft(NodoArbol<T> left) {
        this.left = left;
    }

    public NodoArbol<T> getRight() {
        return right;
    }

    public void setRight(NodoArbol<T> right) {
        this.right = right;
    }
}

public class ArbolBinario<T extends Comparable<T>> {
    private NodoArbol<T> root;

    public ArbolBinario() {
        this.root = null;
    }

    public void insertar(T data) {
        root = insertarRec(root, data);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> root, T data) {
        if (root == null) {
            root = new NodoArbol<>(data);
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(insertarRec(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(insertarRec(root.getRight(), data));
        }
        return root;
    }

    public void eliminar(T data) {
        root = eliminarRec(root, data);
    }

    private NodoArbol<T> eliminarRec(NodoArbol<T> root, T data) {
        if (root == null) return root;

        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(eliminarRec(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(eliminarRec(root.getRight(), data));
        } else {
            if (root.getLeft() == null) return root.getRight();
            else if (root.getRight() == null) return root.getLeft();

            root.setData(minValue(root.getRight()));
            root.setRight(eliminarRec(root.getRight(), root.getData()));
        }
        return root;
    }

    private T minValue(NodoArbol<T> root) {
        T minv = root.getData();
        while (root.getLeft() != null) {
            minv = root.getLeft().getData();
            root = root.getLeft();
        }
        return minv;
    }

    public void imprimir() {
        imprimirRec(root);
        System.out.println();
    }

    private void imprimirRec(NodoArbol<T> root) {
        if (root != null) {
            imprimirRec(root.getLeft());
            System.out.print(root.getData() + " ");
            imprimirRec(root.getRight());
        }
    }
}

