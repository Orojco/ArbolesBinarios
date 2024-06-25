class NodoAVL<T extends Comparable<T>> {
    private T data;
    private NodoAVL<T> left;
    private NodoAVL<T> right;
    private int height;

    public NodoAVL(T data) {
        this.data = data;
        this.height = 1;
    }

    public T getData() { return data; }
    public NodoAVL<T> getLeft() { return left; }
    public void setLeft(NodoAVL<T> left) { this.left = left; }
    public NodoAVL<T> getRight() { return right; }
    public void setRight(NodoAVL<T> right) { this.right = right; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
