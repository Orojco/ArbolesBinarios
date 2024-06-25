public class ListaCircularEnlazada<T> {
    private Nodo<T> head;

    public ListaCircularEnlazada() {
        this.head = null;
    }

    public void insertar(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (head == null) {
            head = nuevoNodo;
            head.setNext(head);
        } else {
            Nodo<T> temp = head;
            while (temp.getNext() != head) {
                temp = temp.getNext();
            }
            temp.setNext(nuevoNodo);
            nuevoNodo.setNext(head);
        }
    }

    public void eliminar(T data) {
        if (head == null) return;
        if (head.getData().equals(data)) {
            if (head.getNext() == head) {
                head = null;
            } else {
                Nodo<T> temp = head;
                while (temp.getNext() != head) {
                    temp = temp.getNext();
                }
                head = head.getNext();
                temp.setNext(head);
            }
            return;
        }
        Nodo<T> current = head;
        while (current.getNext() != head && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        if (current.getNext() != head) {
            current.setNext(current.getNext().getNext());
        }
    }

    public void imprimir() {
        if (head == null) return;
        Nodo<T> temp = head;
        do {
            System.out.print(temp.getData() + " -> ");
            temp = temp.getNext();
        } while (temp != head);
        System.out.println("(head)");
    }
}
