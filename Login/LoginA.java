package Login;

import java.util.Scanner;


/*- Abrir o programa > sistema de login
     ^ Ao abrir o programa pela primeira vez, registrar o login de admin/dono e então pedir o login de funcionário
     ^ Pedir por qual vai ser o valor usado para calcular taxa de cartão(apenas o login de dono pode dar entrada nesse valor ou alterá-lo) > débito e crédito
      ^ Pedir pela chave pix que vai ser usada*/

public class LoginA {

    Scanner ler = new Scanner(System.in);
    Admin ad = new Admin();
    String permissaoLoginA;
    //Acesso acess = new Acesso();

    public void loginAdmin(){

        System.out.println("Login de administrador");
        System.out.print("Login: ");
        String logAdmin = ler.nextLine();
        System.out.print("Senha: ");
        int senhAdmin = ler.nextInt();
        //String permissaoLoginA;

        if (logAdmin.equals(ad.nomeA) && senhAdmin == ad.senhaA){
            System.out.println("Login bem-sucedido.");
            permissaoLoginA = "permitido";
        } else if (!logAdmin.equals(ad.nomeA) || senhAdmin != ad.senhaA){
            
            System.out.println("Login ou senha incorretos.");

            int count = 3;
            do{
                System.out.println("Tente novamente.");
                if (count==3){
                    System.out.println("Você possui "+count+" tentativa(s).");
                }
                ler.nextLine();
                System.out.print("Login: ");
                logAdmin = ler.nextLine();
                System.out.print("Senha: ");
                senhAdmin = ler.nextInt();

                count--;
                if (count !=3 && count != 0){
                    System.out.println("Você possui "+count+" tentativa(s).");
                }
                if (count == 0){
                    System.out.println("Login negado.");
                    permissaoLoginA = "negado";
                    //voltar para tela inicial
                    break;
                }
            } while (!logAdmin.equals(ad.nomeA) || senhAdmin != ad.senhaA);

            if (logAdmin.equals(ad.nomeA) && senhAdmin == ad.senhaA){
                System.out.println("Login bem-sucedido.");
                permissaoLoginA = "permitido";
                //função para acessar o programa
            }
        }
    }

    public void main(String[] args) {

        LoginA logA = new LoginA();
        Admin ad = new Admin();
        Dados dd = new Dados();
        PrimeiroAcessoA primAc = new PrimeiroAcessoA();

        if (ad.quantidadeA == 0){
            System.out.println("Não foram encontrados administradores cadastrados. Por favor, cadastre ao menos um administrador.");
            primAc.CadAd();
            primAc.setIdA();
            primAc.setQuantidadeA();
            if (dd.taxaDebString == "" || dd.taxaCredString == "" || dd.email == ""){ //verificação se já tem as info cadastradas
                dd.pedirPor();
            } else {
                System.out.println("Foram encontradas informações cadastradas. Deseja conferir?");
                char conf = ler.next().charAt(0);
                if (conf == 's'){ //botão
                    System.out.printf("Taxa de débito: "+dd.taxaDeb+"\nTaxa de crédito: "+dd.taxaCred+"\nEmail para pix: ", dd.email);
                }
            }
        } else {
            logA.loginAdmin();
        }
        
    }
}
