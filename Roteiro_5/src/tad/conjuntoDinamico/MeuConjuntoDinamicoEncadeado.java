package tad.conjuntoDinamico;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private class Node {
        Integer data;
        Node next;

        Node(Integer data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void inserir(Integer item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public Integer remover(Integer item) throws Exception {
        if (head == null) {
            throw new Exception("Conjunto vazio");
        }

        Node prev = null;
        Node current = head;

        while (current != null) {
            if (current.data.equals(item)) {
                if (prev == null) { // Remove head
                    head = head.next;
                    if (head == null) tail = null;
                } else {
                    prev.next = current.next;
                    if (current.next == null) tail = prev;
                }
                size--;
                return current.data;
            }
            prev = current;
            current = current.next;
        }

        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer predecessor(Integer item) throws Exception {
        if (head == null) throw new Exception("Conjunto vazio");

        Node prev = null;
        Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return (prev != null) ? prev.data : null;
            }
            prev = current;
            current = current.next;
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer sucessor(Integer item) throws Exception {
        Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return (current.next != null) ? current.next.data : null;
            }
            current = current.next;
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public int tamanho() {
        return size;
    }

    @Override
    public Integer buscar(Integer item) throws Exception {
        Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return current.data;
            }
            current = current.next;
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer minimum() throws Exception {
        if (size == 0) throw new Exception("Conjunto vazio");
        Integer min = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < min) min = current.data;
            current = current.next;
        }
        return min;
    }

    @Override
    public Integer maximum() throws Exception {
        if (size == 0) throw new Exception("Conjunto vazio");
        Integer max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > max) max = current.data;
            current = current.next;
        }
        return max;
    }
}