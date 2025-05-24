package tad.fila;

public class MinhaFila implements FilaIF<Integer> {

    private int tamanho;
    private int cauda;
    private int cabeca;
    private int count;
    private Integer[] meusDados;

    public MinhaFila(int tamanhoInicial) {
        this.tamanho = tamanhoInicial;
        this.meusDados = new Integer[tamanho];
        this.cabeca = 0;
        this.cauda = 0;
        this.count = 0;
    }

    public MinhaFila() {
        this(10);
    }

    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        if (isFull()) {
            throw new FilaCheiaException();
        }
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
        count++;
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }
        Integer item = meusDados[cabeca];
        cabeca = (cabeca + 1) % tamanho;
        count--;
        return item;
    }

    @Override
    public Integer verificarCauda() {
        if (isEmpty()) {
            return null;
        }
        int index = (cauda - 1 + tamanho) % tamanho;
        return meusDados[index];
    }

    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) {
            return null;
        }
        return meusDados[cabeca];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return count == tamanho;
    }
}