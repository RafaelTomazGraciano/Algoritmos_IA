package grafo;

import java.util.*;

public class Grafo {
    private Map<String, No> nos;

    public Grafo() {
        this.nos = new LinkedHashMap<>();
    }

    public No adicionarNo(String valor) {
        return nos.computeIfAbsent(valor, No::new);
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        No noOrigem = adicionarNo(origem);
        No noDestino = adicionarNo(destino);
        noOrigem.adicionarVizinho(noDestino, peso);
    }

    public No getNo(String valor) {
        return nos.get(valor);
    }

    public Map<No, Integer> getVizinhos(No no) {
        return no.getVizinhos();
    }

}
