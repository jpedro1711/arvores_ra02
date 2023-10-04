import java.util.Random;
import java.util.Scanner;

public class MainArvores {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        arvoreAVL arvoreAVL = new arvoreAVL();
        arvoreBusca arvoreBusca = new arvoreBusca();

        /* arvoreAVL.add(10);
        arvoreAVL.add(20);
        arvoreAVL.add(30);
        arvoreAVL.add(40);
        arvoreAVL.add(50);
        arvoreAVL.add(60);
        arvoreAVL.add(70);
        arvoreAVL.add(80);
        arvoreAVL.add(90);

        arvoreAVL.remover(arvoreAVL.getRaiz(), 10);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 20);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 30);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 50);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 40);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 90);

        arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 80);
        arvoreAVL.remover(arvoreAVL.getRaiz(), 70);

        System.out.println("-------------------");

        arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);

        System.out.println("-------------------");
        arvoreAVL.remover(arvoreAVL.getRaiz(), 60);
        arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);
        System.out.println("fb avl: " + arvoreAVL.calcularFB(arvoreAVL.getRaiz()));
        System.out.println("fb busca: " + arvoreAVL.calcularFB(arvoreBusca.getRaiz())); */

        povoarArvores(arvoreBusca, arvoreAVL, 10000);
        System.out.println("Digite o elemento a ser removido da de busca: ");
        int op = sc.nextInt();

        arvoreBusca.remover(arvoreBusca.getRaiz(), op);

        System.out.println("Digite o elemento a ser removido da AVL: ");
        op = sc.nextInt();
        arvoreAVL.remover(arvoreAVL.getRaiz(), op);

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