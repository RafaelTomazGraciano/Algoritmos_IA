package iddfs;

import arvore.Arvore;

import java.util.*;

public class IDDFS {

    private List<Arvore> caminho;

    public IDDFS() {
        caminho = new ArrayList<>();
    }

    public Arvore busca(int valor, Arvore raiz) {
        int profundidadeMaxima = calcularProfundidadeMaxima(raiz);

        for (int limite = 0; limite <= profundidadeMaxima; limite++) {
            System.out.println();
            Deque<Arvore> pilha = new ArrayDeque<>();
            Map<Arvore, Integer> profundidades = new HashMap<>();
            Map<Arvore, Arvore> pai = new HashMap<>();
            Set<Arvore> visitados = new HashSet<>();

            pilha.push(raiz);
            profundidades.put(raiz, 0);
            pai.put(raiz, null);
            visitados.add(raiz);

            while (!pilha.isEmpty()) {
                Arvore atual = pilha.pop();
                int profundidadeAtual = profundidades.get(atual);

                System.out.println("Visitando: " + atual.getValor() + " (profundidade " + profundidadeAtual + ")");

                if (atual.getValor() == valor) {
                    construirCaminho(pai, atual);
                    return atual;
                }

                if (profundidadeAtual < limite) {
                    List<Arvore> filhos = atual.getFilhos();
                    ListIterator<Arvore> iterador = filhos.listIterator(filhos.size());

                    while (iterador.hasPrevious()) {
                        Arvore filho = iterador.previous();
                        if (!visitados.contains(filho)) {
                            pilha.push(filho);
                            profundidades.put(filho, profundidadeAtual + 1);
                            pai.put(filho, atual);
                            visitados.add(filho);
                        }
                    }
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

    private int calcularProfundidadeMaxima(Arvore raiz) {
        if (raiz == null){
            return 0;
        }
        int max = 0;
        for (Arvore filho : raiz.getFilhos()) {
            max = Math.max(max, calcularProfundidadeMaxima(filho));
        }
        return max + 1;
    }

}
