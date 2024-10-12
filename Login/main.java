package Login;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        int opcao;
        System.out.println("Bem-vindo!");
        do{
            System.out.println("Escolha uma opcao abaixo: ");
            System.out.println("1 - Primeiro acesso(cadastro de administrador)\n2 - Cadastro de funcionario\n3 - Acessar sistema\n4 - Fechar o programa");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    PrimeiroAcessoA primA = new PrimeiroAcessoA();
                    primA.main(args);
                    break;
                case 2:
                    PrimeiroAcessoF primF = new PrimeiroAcessoF();
                    primF.main(args);
                    break;
                case 3:
                    System.out.println("Faça o login para acessar o sistema.\n5 - Login de admin\n6 - Login de funcionario");
                    int op = ler.nextInt();
                    if (op == 5){
                        LoginA logA = new LoginA();
                        logA.main(args);
                    } else if (op == 6){
                        LoginF logF = new LoginF();
                        logF.main(args);
                    }
                case 4:
                    System.out.println("O programa sera encerrado.");
                    //fechar o programa.
                default:
                    System.out.println("Opcao invalida."); //4 tbm ta entrando aqui
                    break;
            }
        } while(opcao != 4);
        
        System.out.println("Executado");
        ler.close();
    }
}
