package dfs;

import grafo.No;

import java.util.*;

public class DFS {

    private Deque<No> pilha;
    private Set<No> visitados;
    private Map<No, No> pai;
    private List<No> caminho;

    public DFS() {
        pilha = new ArrayDeque<>();
        visitados = new HashSet<>();
        pai = new HashMap<>();
        caminho = new ArrayList<>();
    }

    public No busca(String objetivo, No raiz) {
        pilha.clear();
        visitados.clear();
        pai.clear();

        pilha.push(raiz);
        visitados.add(raiz);
        pai.put(raiz, null);

        while (!pilha.isEmpty()) {

            System.out.println("Pilha atual: ");
            for(No No: pilha){
                System.out.println(No.getValor() + " ");
            }
            System.out.println();

            No atual = pilha.pop();

            if (atual.getValor().equals(objetivo)) {
                construirCaminho(pai, atual);
                return atual;
            }

            for (No vizinho : atual.getVizinhos().keySet()) {
                if (!visitados.contains(vizinho)) {
                    pilha.push(vizinho);
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
