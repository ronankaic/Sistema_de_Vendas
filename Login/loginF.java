package Login;

import java.util.Scanner;

public class LoginF {

    Scanner ler = new Scanner(System.in);
    Funcionario func = new Funcionario();
    
    public void loginFunc(){

        System.out.println("Login de funcionário");
        System.out.print("Login: ");
        String logFunc = ler.nextLine();
        System.out.print("Senha: ");
        int senhFunc = ler.nextInt();

        if (logFunc.equals(func.nomeF) && senhFunc == func.senhaF){
            System.out.println("Login bem-sucedido.");
        } else if (!logFunc.equals(func.nomeF) || senhFunc != func.senhaF){
            
            System.out.println("Login ou senha incorretos.");

            int count = 3;
            do{
                System.out.println("Tente novamente.");
                if (count==3){
                    System.out.println("Você possui "+count+" tentativa(s).");
                }
                ler.nextLine();
                System.out.print("Login: ");
                logFunc = ler.nextLine();
                System.out.print("Senha: ");
                senhFunc = ler.nextInt();

                count--;
                if (count !=3 && count != 0){
                    System.out.println("Você possui "+count+" tentativa(s).");
                }
                if (count == 0){
                    System.out.println("Login negado.");
                    //voltar para a tela inicial, se o usuário quiser tentar logar novamente, ele seleciona a opção
                    break;
                }
            } while (!logFunc.equals(func.nomeF) || senhFunc != func.senhaF);

            if (logFunc.equals(func.nomeF) && senhFunc == func.senhaF){
                System.out.println("Login bem-sucedido.");
                //função para acessar o sistema
            }
        }
    }

    public void main(String[] args) {

        LoginF logF = new LoginF();
        Funcionario func = new Funcionario();
        PrimeiroAcessoF primFunc = new PrimeiroAcessoF();

        if (func.quantidadeF == 0){
            System.out.println("Não foram encontrados funcionários cadastrados.");
            primFunc.CadFunc();
            primFunc.setIdF();
            primFunc.setQuantidadeF();
        } else {
            logF.loginFunc();
        }
    }
}
