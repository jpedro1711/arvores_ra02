import java.util.Random;
import java.util.Scanner;

public class MainArvores {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        arvoreAVL arvoreAVL = new arvoreAVL();
        arvoreBusca arvoreBusca = new arvoreBusca();


        povoarArvores(arvoreBusca, arvoreAVL, 10000);

        arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);

        System.out.println("fb avl: " + arvoreAVL.calcularFB(arvoreAVL.getRaiz()));
        System.out.println("fb busca: " + arvoreAVL.calcularFB(arvoreBusca.getRaiz()));
        /* System.out.println("Digite o elemento a ser removido da de busca: ");
        int op = sc.nextInt();

        arvoreBusca.remover(arvoreBusca.getRaiz(), op);

        System.out.println("Digite o elemento a ser removido da AVL: ");
        op = sc.nextInt();
        arvoreAVL.remover(arvoreAVL.getRaiz(), op); */

        sc.close();
    }

    public static void povoarArvores(arvoreBusca arvoreBusca, arvoreAVL arvoreAVL, int numElementos) {
        int[] vet = new int[numElementos];

        Random random = new Random();

        for (int i = 0; i < numElementos; i++) {
            int num = random.nextInt(999) + 1;
            vet[i] = num;
        }

        for (int i = 0; i < numElementos; i++) {
            int num = vet[i];
            arvoreBusca.inserir(num);
            arvoreAVL.add(num);
        }

    }
}