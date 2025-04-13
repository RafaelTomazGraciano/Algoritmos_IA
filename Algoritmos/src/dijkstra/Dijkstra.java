package dijkstra;

import grafo.No;

import java.util.*;

public class Dijkstra {
    private List<No> caminho;

    public Dijkstra() {
        caminho = new ArrayList<>();
    }

    public No busca(String objetivo, No origem) {
        Map<No, Integer> distancia = new HashMap<>();
        Map<No, No> predecessores = new HashMap<>();
        Set<No> visitados = new HashSet<>();

        PriorityQueue<No> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));

        distancia.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            No atual = fila.poll();

            System.out.println("\nVisitando: " + atual.getValor());

            if (atual.getValor().equals(objetivo)) {
                construirCaminho(predecessores, atual);
                System.out.println("Destino encontrado: " + atual.getValor());
                return atual;
            }

            visitados.add(atual);

            for (Map.Entry<No, Integer> vizinhoEntry : atual.getVizinhos().entrySet()) {
                No vizinho = vizinhoEntry.getKey();
                int peso = vizinhoEntry.getValue();

                if (visitados.contains(vizinho)) continue;

                int novaDistancia = distancia.get(atual) + peso;

                if (novaDistancia < distancia.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    distancia.put(vizinho, novaDistancia);
                    predecessores.put(vizinho, atual);

                    // Atualiza a fila de prioridade
                    fila.remove(vizinho);
                    fila.add(vizinho);

                    System.out.println("Atualizando vizinho: " + vizinho.getValor());
                    System.out.println("Nova distância: " + novaDistancia);
                }
            }

            System.out.print("Fila atual: ");
            for (No n : fila) {
                System.out.print(n.getValor() + "(d=" + distancia.get(n) + ") ");
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
