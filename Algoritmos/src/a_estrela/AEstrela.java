package a_estrela;

import arvore.Arvore;

import java.util.*;
import java.util.function.Function;

public class AEstrela {

    private List<Arvore> caminho;

    public AEstrela() {
        caminho = new ArrayList<>();
    }

    public Arvore busca(int valor, Arvore inicio, Function<Arvore, Integer> heuristica) {
        // g(n): custo do nó inicial até o nó n
        Map<Arvore, Integer> g = new HashMap<>();

        // f(n): estimativa do custo total do caminho passando por n (g(n) + h(n))
        Map<Arvore, Integer> f = new HashMap<>();

        // pred(n): mapeia o pai de cada nó no caminho
        Map<Arvore, Arvore> pred = new HashMap<>();

        // closedSet: nós já expandidos
        Set<Arvore> closedSet = new HashSet<>();

        // openSet: nós a serem expandidos, ordenados pelo menor f(n)
        PriorityQueue<Arvore> openSet = new PriorityQueue<>(Comparator.comparingInt(f::get));

        g.put(inicio, 0);

        // Aplica a heurística ao nó inicial: estimativa da distância até o objetivo
        f.put(inicio, heuristica.apply(inicio));

        openSet.add(inicio);

        while (!openSet.isEmpty()) {
            Arvore atual = openSet.poll();

            System.out.println("\nExpandindo no: " + atual.getValor());
            System.out.println("g(" + atual.getValor() + ") = " + g.get(atual));
            System.out.println("h(" + atual.getValor() + ") = " + heuristica.apply(atual));
            System.out.println("f(" + atual.getValor() + ") = " + f.get(atual));

            if (atual.getValor() == valor) {
                construirCaminho(pred, atual);
                return atual;
            }

            closedSet.add(atual);

            for (Arvore vizinho : atual.getFilhos()) {
                if (closedSet.contains(vizinho)) continue;

                int custoEntreNos = 1; // custo entre os nós
                int gTentativo = g.getOrDefault(atual, Integer.MAX_VALUE) + custoEntreNos;

                // Se encontramos um caminho melhor até vizinho
                if (gTentativo < g.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    pred.put(vizinho, atual);
                    g.put(vizinho, gTentativo);

                    // h(vizinho) = heurística estimada até o objetivo
                    int heuristicaVizinho = heuristica.apply(vizinho);
                    f.put(vizinho, gTentativo + heuristicaVizinho);

                    // atualizar a prioridade
                    if (openSet.contains(vizinho)) {
                        openSet.remove(vizinho);
                    }

                    openSet.add(vizinho);
                    System.out.println("Atualizando vizinho: " + vizinho.getValor());
                    System.out.println("  g = " + gTentativo + ", h = " + heuristicaVizinho + ", f = " + f.get(vizinho));
                }
            }
            System.out.print("Fila atual(openSet): ");
            for (Arvore a : openSet) {
                System.out.print(a.getValor() + "(f=" + f.get(a) + ") ");
            }
            System.out.println();
        }

        return null;
    }

    private void construirCaminho(Map<Arvore, Arvore> pai, Arvore objetivo) {
        Deque<Arvore> pilha = new ArrayDeque<>();

        Arvore atual = objetivo;
        while (atual != null) {
            pilha.push(atual);
            atual = pai.get(atual);
        }

        caminho.clear();
        while (!pilha.isEmpty()) {
            caminho.add(pilha.pop());
        }
    }

    public List<Arvore> getCaminho() {
        return caminho;
    }

}
