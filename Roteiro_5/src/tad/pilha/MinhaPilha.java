package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho;
	private Integer[] meusDados;
	private int topo;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
		this.topo = -1;
	}

	public MinhaPilha() {
		this(10);
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (isFull()) {
			throw new PilhaCheiaException();
		}
		meusDados[++topo] = item;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		Integer item = meusDados[topo];
		meusDados[topo--] = null;
		return item;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[topo];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException();
		}
		int elementosParaCopiar = Math.min(k, topo + 1);
		MinhaPilha novaPilha = new MinhaPilha(elementosParaCopiar);

		for (int i = 0; i < elementosParaCopiar; i++) {
			try {
				novaPilha.empilhar(meusDados[topo - i]);
			} catch (PilhaCheiaException e) {
			}
		}
		return novaPilha;
	}

	@Override
	public boolean isEmpty() {
		return topo == -1;
	}

	private boolean isFull() {
		return topo == tamanho - 1;
	}
}