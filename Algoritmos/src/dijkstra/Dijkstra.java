package dijkstra;

import arvore.Arvore;

import java.util.*;

public class Dijkstra {
    private List<Arvore> caminho;

    public Dijkstra() {
        caminho = new ArrayList<>();
    }

    public Arvore busca(int destino, Arvore origem) {
        Map<Arvore, Integer> distancia = new HashMap<>();
        Map<Arvore, Arvore> predecessores = new HashMap<>();
        Set<Arvore> visitados = new HashSet<>();

        PriorityQueue<Arvore> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));

        // Inicialização
        distancia.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Arvore atual = fila.poll();

            System.out.println("\nVisitando: " + atual.getValor());

            if (atual.getValor() == destino) {
                construirCaminho(predecessores, atual);
                System.out.println("Destino encontrado: " + atual.getValor());
                return atual;
            }

            visitados.add(atual);

            for (Arvore vizinho : atual.getFilhos()) {
                if (visitados.contains(vizinho)) continue;

                int custo = 1; // custo fixo
                int novaDistancia = distancia.getOrDefault(atual, Integer.MAX_VALUE) + custo;

                if (novaDistancia < distancia.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    distancia.put(vizinho, novaDistancia);
                    predecessores.put(vizinho, atual);

                    if (fila.contains(vizinho)) fila.remove(vizinho);
                    fila.add(vizinho);

                    System.out.println("Atualizando vizinho: " + vizinho.getValor());
                    System.out.println("Nova distancia: " + novaDistancia);
                }
            }

            System.out.print("Fila atual: ");
            for (Arvore a : fila) {
                System.out.print(a.getValor() + "(d=" + distancia.get(a) + ") ");
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
