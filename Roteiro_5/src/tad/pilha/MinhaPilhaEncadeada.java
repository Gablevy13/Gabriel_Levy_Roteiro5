package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	private ListaEncadeadaIF<Integer> lista = new ListaEncadeadaImpl<>();

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		lista.insert(item);
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (lista.isEmpty()) {
			throw new PilhaVaziaException();
		}
		try {
			NodoListaEncadeada<Integer> topo = lista.remove(lista.topo());
			return topo.getChave();
		} catch (Exception e) {
			throw new PilhaVaziaException();
		}
	}

	@Override
	public Integer topo() {
		if (lista.isEmpty()) {
			return null;
		}
		return lista.topo().getChave();
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
		if (lista.isEmpty()) {
			throw new PilhaVaziaException();
		}
		MinhaPilhaEncadeada novaPilha = new MinhaPilhaEncadeada();
		int elementosParaCopiar = Math.min(k, lista.size());

		NodoListaEncadeada<Integer> atual = lista.topo();
		for (int i = 0; i < elementosParaCopiar; i++) {
			try {
				novaPilha.empilhar(atual.getChave());
				atual = atual.getProximo();
			} catch (PilhaCheiaException e) {
			}
		}
		return novaPilha;
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}
}