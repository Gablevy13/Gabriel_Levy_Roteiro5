package tad.fila;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	private class Node {
		Integer data;
		Node next;

		Node(Integer data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node head;
	private Node tail;

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		Integer removedItem = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		return removedItem;
	}

	@Override
	public Integer verificarCauda() {
		return (tail != null) ? tail.data : null;
	}

	@Override
	public Integer verificarCabeca() {
		return (head != null) ? head.data : null;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}
}