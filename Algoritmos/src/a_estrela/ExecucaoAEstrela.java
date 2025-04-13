package a_estrela;

import grafo.Grafo;
import grafo.No;

public class ExecucaoAEstrela {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.adicionarAresta("A", "B", 1);
        grafo.adicionarAresta("A", "C", 2);
        grafo.adicionarAresta("B", "D", 4);
        grafo.adicionarAresta("C", "D", 2);

        No raiz = grafo.getNo("A");

        AEstrela aEstrela = new AEstrela();
        No encontrado = aEstrela.busca("D", raiz, no->0);

        if (encontrado != null) {
            System.out.println("Caminho até " + encontrado.getValor() + ":");
            for (No no : aEstrela.getCaminho()) {
                System.out.print(no.getValor() + " ");
            }
        } else {
            System.out.println("Nó não encontrado.");
        }

    }
}
