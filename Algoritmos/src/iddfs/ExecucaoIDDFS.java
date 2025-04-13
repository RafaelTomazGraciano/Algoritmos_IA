import grafo.Grafo;
import grafo.No;
import iddfs.IDDFS;

public class ExecucaoIDDFS {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.adicionarAresta("A", "B", 1);
        grafo.adicionarAresta("A", "C", 1);
        grafo.adicionarAresta("B", "D", 1);
        grafo.adicionarAresta("C", "D", 1);

        No raiz = grafo.getNo("A");

        IDDFS iddfs = new IDDFS();
        No encontrado = iddfs.busca("D", raiz, grafo);

        if (encontrado != null) {
            System.out.println("Caminho até " + encontrado.getValor() + ":");
            for (No no : iddfs.getCaminho()) {
                System.out.print(no.getValor() + " ");
            }
        } else {
            System.out.println("Valor não encontrado.");
        }
    }
}
