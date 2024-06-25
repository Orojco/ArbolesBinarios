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