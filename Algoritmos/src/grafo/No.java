package grafo;

import java.util.*;

public class No {
    private String valor;
    private Map<No, Integer> vizinhos;

    public No(String valor) {
        this.valor = valor;
        this.vizinhos = new LinkedHashMap<>();
    }

    public String getValor() {
        return valor;
    }

    public Map<No, Integer> getVizinhos() {
        return vizinhos;
    }

    public void adicionarVizinho(No destino, int peso) {
        vizinhos.put(destino, peso);
    }
}
