package a_estrela;

import grafo.No;

import java.util.*;
import java.util.function.Function;

public class AEstrela {

    private List<No> caminho;

    public AEstrela() {
        caminho = new ArrayList<>();
    }

    public No busca(String objetivo, No inicio, Function<No, Integer> heuristica) {
        Map<No, Integer> g = new HashMap<>();
        Map<No, Integer> f = new HashMap<>();
        Map<No, No> pred = new HashMap<>();
        Set<No> closedSet = new HashSet<>();

        PriorityQueue<No> openSet = new PriorityQueue<>(Comparator.comparingInt(f::get));

        g.put(inicio, 0);
        f.put(inicio, heuristica.apply(inicio));
        openSet.add(inicio);

        while (!openSet.isEmpty()) {
            No atual = openSet.poll();

            System.out.println("\nExpandindo nó: " + atual.getValor());
            System.out.println("g("+ atual.getValor() + ") = " + g.get(atual));
            System.out.println("h("+ atual.getValor() + ") = " + heuristica.apply(atual));
            System.out.println("f("+ atual.getValor() + ") = " + f.get(atual));

            if (atual.getValor().equals(objetivo)) {
                construirCaminho(pred, atual);
                return atual;
            }

            closedSet.add(atual);

            for (Map.Entry<No, Integer> vizinhoEntry : atual.getVizinhos().entrySet()) {
                No vizinho = vizinhoEntry.getKey();
                int peso = vizinhoEntry.getValue();

                if (closedSet.contains(vizinho)) continue;

                int gTentativo = g.get(atual) + peso;

                if (gTentativo < g.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    pred.put(vizinho, atual);
                    g.put(vizinho, gTentativo);

                    int heuristicaVizinho = heuristica.apply(vizinho);
                    f.put(vizinho, gTentativo + heuristicaVizinho);

                    // Atualiza a prioridade na fila
                    openSet.remove(vizinho); // caso já esteja
                    openSet.add(vizinho);

                    System.out.println("Atualizando vizinho: " + vizinho.getValor());
                    System.out.println(" g = " + gTentativo + ", h = " + heuristicaVizinho + ", f = " + f.get(vizinho));
                }
            }

            System.out.print("Fila atual (openSet): ");
            for (No n : openSet) {
                System.out.print(n.getValor() + "(f=" + f.get(n) + ") ");
            }
            System.out.println();
        }

        return null;
    }

    private void construirCaminho(Map<No, No> pai, No objetivo) {
        Deque<No> pilha = new ArrayDeque<>();
        No atual = objetivo;
        while (atual != null) {
            pilha.push(atual);
            atual = pai.get(atual);
        }

        caminho.clear();
        while (!pilha.isEmpty()) {
            caminho.add(pilha.pop());
        }
    }

    public List<No> getCaminho() {
        return caminho;
    }
}

