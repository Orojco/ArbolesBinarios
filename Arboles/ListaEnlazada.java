public class ListaEnlazada<T> {
    private Nodo<T> head;

    public ListaEnlazada() {
        this.head = null;
    }

    public void insertar(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(nuevoNodo);
        }
    }

    public void eliminar(T data) {
        if (head == null) return;
        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }
        Nodo<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> temp = head;
        while (temp != null) {
            sb.append(temp.getData()).append(" -> ");
            temp = temp.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}

