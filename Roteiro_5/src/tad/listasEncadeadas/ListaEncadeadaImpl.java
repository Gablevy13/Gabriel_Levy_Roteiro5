package tad.listasEncadeadas;

import java.util.ArrayList;
import java.util.List;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	protected NodoListaEncadeada<T> cabeca;
	protected NodoListaEncadeada<T> cauda;
	private int tamanho;

	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<>();
		cauda = new NodoListaEncadeada<>();
		cabeca.setProximo(cauda);
		tamanho = 0;
	}

	@Override
	public boolean isEmpty() {
		return cabeca.getProximo() == cauda;
	}

	@Override
	public int size() {
		return tamanho;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			if (atual.getChave().equals(chave)) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> atual = cabeca;
		while (atual.getProximo() != cauda) {
			atual = atual.getProximo();
		}
		novoNo.setProximo(cauda);
		atual.setProximo(novoNo);
		tamanho++;
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if (isEmpty()) throw new ListaVaziaException();

		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();

		while (atual != cauda) {
			if (atual.getChave().equals(chave)) {
				anterior.setProximo(atual.getProximo());
				tamanho--;
				return atual;
			}
			anterior = atual;
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder sb = new StringBuilder();
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			sb.append(atual.getChave());
			if (atual.getProximo() != cauda) sb.append(", ");
			atual = atual.getProximo();
		}
		return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		List<T> elementos = new ArrayList<>();
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			elementos.add(atual.getChave());
			atual = atual.getProximo();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = elementos.size() - 1; i >= 0; i--) {
			sb.append(elementos.get(i));
			if (i > 0) sb.append(", ");
		}
		return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> nodo = search(chave);
		return (nodo != null && nodo.getProximo() != cauda) ? nodo.getProximo() : null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			if (atual.getChave().equals(chave)) {
				return (anterior != cabeca) ? anterior : null;
			}
			anterior = atual;
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		List<T> elementos = new ArrayList<>();
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			elementos.add(atual.getChave());
			atual = atual.getProximo();
		}
		return elementos.toArray((T[]) java.lang.reflect.Array.newInstance(clazz, elementos.size()));
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Não implementado");
	}
}