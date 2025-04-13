package iddfs;

import grafo.Grafo;
import grafo.No;

import java.util.*;

public class IDDFS {

    private List<No> caminho;

    public IDDFS() {
        caminho = new ArrayList<>();
    }

    public No busca(String objetivo, No raiz, Grafo grafo) {
        int profundidadeMaxima = calcularProfundidadeMaxima(raiz, grafo);

        for (int limite = 0; limite <= profundidadeMaxima; limite++) {
            System.out.println("Profundidade limite: " + limite);
            Deque<No> pilha = new ArrayDeque<>();
            Map<No, Integer> profundidades = new LinkedHashMap<>();
            Map<No, No> pai = new HashMap<>();
            Set<No> visitados = new HashSet<>();

            pilha.push(raiz);
            profundidades.put(raiz, 0);
            pai.put(raiz, null);
            visitados.add(raiz);

            while (!pilha.isEmpty()) {
                No atual = pilha.pop();
                int profundidadeAtual = profundidades.get(atual);

                System.out.println("Visitando: " + atual.getValor() + " (profundidade " + profundidadeAtual + ")");

                if (atual.getValor().equals(objetivo)) {
                    construirCaminho(pai, atual);
                    return atual;
                }

                if (profundidadeAtual < limite) {
                    for (Map.Entry<No, Integer> vizinhoEntry : grafo.getVizinhos(atual).entrySet()) {
                        No vizinho = vizinhoEntry.getKey();
                        if (!visitados.contains(vizinho)) {
                            pilha.push(vizinho);
                            profundidades.put(vizinho, profundidadeAtual + 1);
                            pai.put(vizinho, atual);
                            visitados.add(vizinho);
                        }
                    }
                }
            }
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

    private int calcularProfundidadeMaxima(No raiz, Grafo grafo) {
        if (raiz == null) {
            return 0;
        }
        int max = 0;
        for (Map.Entry<No, Integer> vizinhoEntry : grafo.getVizinhos(raiz).entrySet()) {
            No vizinho = vizinhoEntry.getKey();  // Obtendo o nó vizinho
            max = Math.max(max, calcularProfundidadeMaxima(vizinho, grafo));
        }
        return max + 1;
    }
}

