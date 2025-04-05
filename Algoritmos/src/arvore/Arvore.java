package arvore;

import java.util.ArrayList;
import java.util.List;

public class Arvore {
    private int valor;
    private List<Arvore> filhos;

    public Arvore(int valor) {
        this.valor = valor;
        this.filhos = new ArrayList<Arvore>();
    }

    public int getValor() {
        return valor;
    }

    public List<Arvore> getFilhos() {
        return filhos;
    }

    public Arvore addFilho(int valor) {
        Arvore novoFilho = new Arvore(valor);
        filhos.add(novoFilho);
        return novoFilho;
    }

}
