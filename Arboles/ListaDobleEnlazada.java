public class ListaDobleEnlazada<T> {
    private NodoDoble<T> head;

    public ListaDobleEnlazada() {
        this.head = null;
    }

    public void insertar(T data) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(data);
        if (head == null) {
            head = nuevoNodo;
            head.setNext(head);
            head.setPrev(head);
        } else {
            NodoDoble<T> temp = head;
            while (temp.getNext() != head) {
                temp = temp.getNext();
            }
            temp.setNext(nuevoNodo);
            nuevoNodo.setPrev(temp);
            nuevoNodo.setNext(head);
            head.setPrev(nuevoNodo);
        }
    }

    public void eliminar(T data) {
        if (head == null) return;
        if (head.getData().equals(data)) {
            if (head.getNext() == head) {
                head = null;
            } else {
                NodoDoble<T> temp = head;
                while (temp.getNext() != head) {
                    temp = temp.getNext();
                }
                head = head.getNext();
                temp.setNext(head);
                head.setPrev(temp);
            }
            return;
        }
        NodoDoble<T> current = head;
        while (current.getNext() != head && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        if (current.getNext() != head) {
            NodoDoble<T> toDelete = current.getNext();
            current.setNext(toDelete.getNext());
            toDelete.getNext().setPrev(current);
        }
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> temp = head;
        do {
            sb.append(temp.getData()).append(" <-> ");
            temp = temp.getNext();
        } while (temp != head);
        sb.append("(head)");
        return sb.toString();
    }
}
