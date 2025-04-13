package dfs;


import grafo.Grafo;
import grafo.No;

public class ExecucaoDFS {

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.adicionarAresta("A", "B", 1);
        grafo.adicionarAresta("A", "C", 1);
        grafo.adicionarAresta("B", "D", 1);
        grafo.adicionarAresta("C", "D", 1);

        No raiz = grafo.getNo("A");

        DFS dfs = new DFS();
        No encontrado = dfs.busca("D", raiz);

        if (encontrado != null) {
            System.out.println("Caminho até " + encontrado.getValor() + ":");
            for (No no : dfs.getCaminho()) {
                System.out.print(no.getValor() + " ");
            }
        } else {
            System.out.println("Nó não encontrado.");
        }

    }

}
