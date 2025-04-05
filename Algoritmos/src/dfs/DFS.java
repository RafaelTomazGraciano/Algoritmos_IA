package dfs;

import arvore.Arvore;

import java.util.*;

public class DFS {

    private Deque<Arvore> pilha;
    private Set<Arvore> visitados;
    private Map<Arvore, Arvore> pai;
    private List<Arvore> caminho;

    public DFS() {
        pilha = new ArrayDeque<>();
        visitados = new HashSet<>();
        pai = new HashMap<>();
        caminho = new ArrayList<>();
    }

    public Arvore busca(int valor, Arvore raiz) {
        pilha.clear();
        visitados.clear();
        pai.clear();

        pilha.push(raiz);
        visitados.add(raiz);
        pai.put(raiz, null);

        while (!pilha.isEmpty()) {

            System.out.println("Pilha atual: ");
            for(Arvore arvore: pilha){
                System.out.println(arvore.getValor() + " ");
            }
            System.out.println();

            Arvore atual = pilha.pop();

            if (atual.getValor() == valor) {
                construirCaminho(pai, atual);
                return atual;
            }

            for (Arvore filho : atual.getFilhos()) {
                if (!visitados.contains(filho)) {
                    pilha.push(filho);
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
