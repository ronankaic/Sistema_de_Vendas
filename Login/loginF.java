package Login;

import java.util.Scanner;

public class LoginF {

    Scanner ler = new Scanner(System.in);
    Funcionario func = new Funcionario();
    
    public void loginFunc(){

        System.out.println("Login de funcionario");
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
                    System.out.println("Voce possui "+count+" tentativa(s).");
                }
                ler.nextLine();
                System.out.print("Login: ");
                logFunc = ler.nextLine();
                System.out.print("Senha: ");
                senhFunc = ler.nextInt();

                count--;
                System.out.println("Voce possui "+count+" tentativa(s).");
                if (count == 0){
                    System.out.println("Login negado.");
                    //o que fazer se o funcionário n conseguir logar? cotinuar executando o programa mas só com o admin operando ele? >> para diferenciar quem está usando, pedir o login em funcionalidades que requerem login de admin?
                    break;
                }
            } while (!logFunc.equals(func.nomeF) || senhFunc != func.senhaF);

            if (logFunc.equals(func.nomeF) && senhFunc == func.senhaF){
                System.out.println("Login bem-sucedido.");
            }
        }
        ler.close();
    }

    public void main(String[] args) {

        LoginF logF = new LoginF();
        Funcionario func = new Funcionario();
        PrimeiroAcessoF primFunc = new PrimeiroAcessoF();

        if (func.quantidadeF == 0){
            System.out.println("Nao foram encontrados funcionarios cadastrados.");
            primFunc.CadFunc();
            primFunc.setIdF();
            primFunc.setQuantidadeF();
            //System.out.println("Qtd de funcionarios: "+func.quantidadeF);
        } else {
            logF.loginFunc();
        }
    }
}
