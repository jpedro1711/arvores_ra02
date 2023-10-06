import java.util.Random;
import java.util.Scanner;

public class MainArvores {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        arvoreAVL arvoreAVL = new arvoreAVL();
        arvoreBusca arvoreBusca = new arvoreBusca();
        /*
        arvoreAVL.add(10);
        arvoreAVL.add(20);
        arvoreAVL.add(30);
        arvoreAVL.add(40);
        arvoreAVL.add(50);
        arvoreAVL.add(60);
        arvoreAVL.add(70);
        arvoreAVL.add(80);
        arvoreAVL.add(90);

        arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);

        System.out.println(arvoreAVL.buscar(10)); */
        int n = 100;
        int[] numeros = new int[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            numeros[i] = random.nextInt(999) + 1;
        }

        // Comentar se for testar inserção
        for (int i = 0; i < n; i++) {
            arvoreBusca.inserir(numeros[i]);
        }

        for (int i = 0; i < n; i++) {
            arvoreAVL.add(numeros[i]);
        }

        Integer op = 0;

        while (op != 3) {
            System.out.println("Escolha uma opção: \n1- Inserir na AVl \n2- Inserir na árvore de busca \n3 - Buscar elemento \n4 - Excluir \n5 - Sair");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    for (int i = 0; i < n; i++) {
                        arvoreAVL.add(numeros[i]);
                    }
                    arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);
                    System.out.println("Elementos da avl: " + arvoreAVL.n);
                    break;
                case 2:
                    for (int i = 0; i < n; i++) {
                        arvoreBusca.inserir(numeros[i]);
                    }
                    arvoreBusca.imprime(arvoreBusca.getRaiz(), "", true);
                    System.out.println("Elementos da árvore de busca: " + arvoreBusca.n);
                    break;
                case 3:
                    arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);
                    System.out.println("Digite o valor: ");
                    int valor = sc.nextInt();
                    System.out.println(arvoreAVL.buscar(arvoreAVL.getRaiz(), valor));
                    System.out.println("Digite qualquer número para continuar");
                    int o = sc.nextInt();
                    System.out.println(arvoreBusca.buscar(arvoreBusca.getRaiz(), valor));
                    break;
                case 4:
                    arvoreAVL.imprime(arvoreAVL.getRaiz(), "", true);
                    System.out.println("Digite o valor: ");
                    valor = sc.nextInt();
                    System.out.println(arvoreAVL.remover(arvoreAVL.getRaiz(), valor));
                    System.out.println("Digite qualquer número para continuar");
                    o = sc.nextInt();
                    System.out.println(arvoreBusca.remover(arvoreBusca.getRaiz(), valor));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}