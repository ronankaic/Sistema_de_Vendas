package Login;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        int opcao;

        do{
            System.out.println("Escolha uma opcao abaixo: ");
            System.out.println("1 - cadastro administrador\n2 - cadastro funcionario");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    primeiroAcessoA primA = new primeiroAcessoA();
                    primA.main(args);
                    break;
                case 2:
                    primeiroAcessoF primF = new primeiroAcessoF();
                    primF.main(args);
                    break;
                case 3:
                    System.out.println("Encerrou o programa.");
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while(opcao != 3);
        
        System.out.println();
    }
}
