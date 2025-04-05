package dfs;

import arvore.Arvore;

public class ExecucaoDFS {

    public static void main(String[] args) {
        Arvore raiz = new Arvore(10);

        Arvore filho1 = raiz.addFilho(15);
        Arvore filho2 = raiz.addFilho(58);
        Arvore filho3 = raiz.addFilho(105);

        // Adicionando filhos ao filho1
        Arvore filho1_1 = filho1.addFilho(90);
        Arvore filho1_2 = filho1.addFilho(37);

        // Adicionando filhos ao filho2
        Arvore filho2_1 = filho2.addFilho(129);
        Arvore filho2_2 = filho2.addFilho(58);

        // Adicionando filhos ao filho3
        Arvore filho3_1 = filho3.addFilho(90);
        Arvore filho3_2 = filho3.addFilho(68);

        // Adicionando mais profundidade
        filho1_1.addFilho(74);
        filho1_2.addFilho(111);
        filho2_1.addFilho(207);
        filho2_2.addFilho(153);
        filho3_1.addFilho(182);
        filho3_2.addFilho(91);

        DFS dfs = new DFS();
        Arvore busca = dfs.busca(207, raiz);

        if(busca != null){
            System.out.println(busca.getValor() + "\n");
        }else{
            System.out.println("Nao encontrado\n");
        }

        System.out.println("Caminho");
        for (Arvore arvore : dfs.getCaminho()) {
            System.out.print(arvore.getValor() + " ");
        }

    }

}
