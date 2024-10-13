package Login;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner ler = new Scanner(System.in);

        int opcao;
        System.out.println("Bem-vindo!");
        try{
            do{
                System.out.println("Escolha uma opção abaixo: ");
                System.out.println("1 - Primeiro acesso(cadastro de administrador)\n2 - Cadastro de funcionário\n3 - Acessar sistema\n4 - Fechar o programa");
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
                        System.out.println("Faça o login para acessar o sistema.\n5 - Login de admin\n6 - Login de funcionário");
                        int op = ler.nextInt();
                        while (op != 5 && op != 6){
                            System.out.println("Opção inválida, deseja acessar outra funcionalidade do programa?");
                            char function = ler.next().charAt(0); //habilitar botões para sim e não
                            if (function == 's'){
                                break;
                            } else if (function == 'n'){
                                System.out.println("Digite\n5 - Login de admin\n6 - Login de funcionário");
                                op = ler.nextInt();
                                break;
                            }
                        }
                        if (op == 5){
                            LoginA logA = new LoginA();
                            logA.main(args);
                        } else if (op == 6){
                            LoginF logF = new LoginF();
                            logF.main(args);
                        }
                        break;
                    case 4:
                        System.out.println("O programa será encerrado.");
                        //fechar o programa.
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while(opcao != 4);
        } catch (InputMismatchException err) {
            System.out.println("Por favor, digite apenas números. Tente novamente");
        }
        
        System.out.println("Executado");
        ler.close();
    }
}
//conversão de string para double das taxas de cartão para poderem ser usadas;
//verificação de existência das informações pós cadastro de admin para serem solicitadas apenas se não existirem;
//Concerto do erro do Scanner ao chamar uma segunda função consecutiva;
//mensagem e encerramento do programa caso seja dado uma letra em vez de número em opcao >> não consegui colocar o try-catch em loop