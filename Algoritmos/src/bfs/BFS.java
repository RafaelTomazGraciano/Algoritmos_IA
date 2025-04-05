package bfs;

import arvore.Arvore;

import java.util.*;

public class BFS {

    private Queue<Arvore> fila;
    private Set<Arvore> visitados;
    private Map<Arvore, Arvore> pai;
    private List<Arvore> caminho;

    public BFS() {
        fila = new LinkedList<>();
        visitados = new HashSet<>();
        pai = new HashMap<>();
        caminho = new ArrayList<>();
    }

    public Arvore busca(int valor, Arvore raiz) {
        fila.clear();
        visitados.clear();
        pai.clear();

        fila.add(raiz);
        visitados.add(raiz);
        pai.put(raiz, null);

        while (!fila.isEmpty()) {

            System.out.println("Fila atual: ");
            for(Arvore arvore: fila){
                System.out.println(arvore.getValor() + " ");
            }
            System.out.println();

            Arvore atual = fila.remove();

            if (atual.getValor() == valor) {
                construirCaminho(pai, atual);
                return atual;
            }

            for (Arvore filho : atual.getFilhos()) {
                if (!visitados.contains(filho)) {
                    fila.add(filho);
                    visitados.add(filho);
                    pai.put(filho, atual);
                }
            }
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