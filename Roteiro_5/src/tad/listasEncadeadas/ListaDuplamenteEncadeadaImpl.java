package tad.listasEncadeadas;

import java.util.ArrayList;
import java.util.List;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	private NodoListaDuplamenteEncadeada<T> cabeca;
	private NodoListaDuplamenteEncadeada<T> cauda;
	private int tamanho;

	public ListaDuplamenteEncadeadaImpl() {
		cabeca = new NodoListaDuplamenteEncadeada<>();
		cauda = new NodoListaDuplamenteEncadeada<>();
		cabeca.setProximo(cauda);
		cauda.setAnterior(cabeca);
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
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			if (atual.getChave().equals(chave)) {
				return atual;
			}
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);
		NodoListaDuplamenteEncadeada<T> anterior = cauda.getAnterior();

		novoNo.setAnterior(anterior);
		novoNo.setProximo(cauda);
		anterior.setProximo(novoNo);
		cauda.setAnterior(novoNo);
		tamanho++;
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if (isEmpty()) throw new ListaVaziaException();

		NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		while (atual != cauda) {
			if (atual.getChave().equals(chave)) {
				atual.getAnterior().setProximo(atual.getProximo());
				((NodoListaDuplamenteEncadeada<T>) atual.getProximo()).setAnterior(atual.getAnterior());
				tamanho--;
				return atual;
			}
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		return null;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		while (atual != cauda) {
			sb.append(atual.getChave());
			if (atual.getProximo() != cauda) sb.append(", ");
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
		while (atual != cabeca) {
			sb.append(atual.getChave());
			if (atual.getAnterior() != cabeca) sb.append(", ");
			atual = atual.getAnterior();
		}
		return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		return (nodo != null && nodo.getProximo() != cauda) ? nodo.getProximo() : null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		return (nodo != null && nodo.getAnterior() != cabeca) ? nodo.getAnterior() : null;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		List<T> elementos = new ArrayList<>();
		NodoListaDuplamenteEncadeada<T> atual = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		while (atual != cauda) {
			elementos.add(atual.getChave());
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		return elementos.toArray((T[]) java.lang.reflect.Array.newInstance(clazz, elementos.size()));
	}

	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(elemento);
		NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();

		novoNo.setAnterior(cabeca);
		novoNo.setProximo(primeiro);
		primeiro.setAnterior(novoNo);
		cabeca.setProximo(novoNo);
		tamanho++;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		if (isEmpty()) return null;
		NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
		ultimo.getAnterior().setProximo(cauda);
		cauda.setAnterior(ultimo.getAnterior());
		tamanho--;
		return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		if (isEmpty()) return null;
		NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		cabeca.setProximo(primeiro.getProximo());
		((NodoListaDuplamenteEncadeada<T>) primeiro.getProximo()).setAnterior(cabeca);
		tamanho--;
		return primeiro;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Não implementado");
	}
}