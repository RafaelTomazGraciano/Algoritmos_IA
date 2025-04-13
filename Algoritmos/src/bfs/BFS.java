package bfs;

import grafo.No;

import java.util.*;

public class BFS {
    private Queue<No> fila;
    private Set<No> visitados;
    private Map<No, No> pai;
    private List<No> caminho;

    public BFS() {
        fila = new LinkedList<>();
        visitados = new HashSet<>();
        pai = new HashMap<>();
        caminho = new ArrayList<>();
    }

    public No busca(String objetivo, No raiz) {
        fila.clear();
        visitados.clear();
        pai.clear();

        fila.add(raiz);
        visitados.add(raiz);
        pai.put(raiz, null);

        while (!fila.isEmpty()) {

            System.out.println("Fila atual:");
            for (No no : fila) {
                System.out.print(no.getValor() + " ");
            }
            System.out.println();

            No atual = fila.remove();

            if (atual.getValor().equals(objetivo)) {
                construirCaminho(pai, atual);
                return atual;
            }

            for (No vizinho : atual.getVizinhos().keySet()) {
                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    visitados.add(vizinho);
                    pai.put(vizinho, atual);
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
}