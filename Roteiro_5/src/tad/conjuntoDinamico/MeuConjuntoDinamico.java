package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private Integer[] meusDados = null;
    private int posInsercao = 0;

    @Override
    public void inserir(Integer item) {
        if (meusDados == null || posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao++] = item;
    }

    private Integer[] aumentarArray() {
        int newSize = (meusDados == null) ? 10 : meusDados.length * 2;
        Integer[] newArray = new Integer[newSize];
        if (meusDados != null) {
            System.arraycopy(meusDados, 0, newArray, 0, meusDados.length);
        }
        return newArray;
    }

    @Override
    public Integer remover(Integer item) throws Exception {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                Integer removido = meusDados[i];
                for (int j = i; j < posInsercao - 1; j++) {
                    meusDados[j] = meusDados[j + 1];
                }
                posInsercao--;
                return removido;
            }
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer predecessor(Integer item) throws Exception {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i > 0) {
                    return meusDados[i - 1];
                } else {
                    return null;
                }
            }
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer sucessor(Integer item) throws Exception {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i < posInsercao - 1) {
                    return meusDados[i + 1];
                } else {
                    return null;
                }
            }
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) throws Exception {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        throw new Exception("Elemento não encontrado");
    }

    @Override
    public Integer minimum() throws Exception {
        if (posInsercao == 0) {
            throw new Exception("Conjunto vazio");
        }
        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] < min) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() throws Exception {
        if (posInsercao == 0) {
            throw new Exception("Conjunto vazio");
        }
        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i] > max) {
                max = meusDados[i];
            }
        }
        return max;
    }
}