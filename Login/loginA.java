package Login;

import java.util.Scanner;


/*- Abrir o programa > sistema de login
     ^ Ao abrir o programa pela primeira vez, registrar o login de admin/dono e então pedir o login de funcionário
     ^ Pedir por qual vai ser o valor usado para calcular taxa de cartão(apenas o login de dono pode dar entrada nesse valor ou alterá-lo) > débito e crédito
      ^ Pedir pela chave pix que vai ser usada*/

public class loginA {

    Scanner ler = new Scanner(System.in);
    admin ad = new admin();

    public void loginAdmin(){

        System.out.println("Login de Administrador");
        System.out.print("Login: ");
        String logAdmin = ler.nextLine();
        System.out.print("Senha: ");
        int senhAdmin = ler.nextInt();

        if (logAdmin.equals(ad.nomeA) && senhAdmin == ad.senhaA){
            System.out.println("Login bem-sucedido.");
        } else if (!logAdmin.equals(ad.nomeA) || senhAdmin != ad.senhaA){
            
            System.out.println("Login ou senha incorretos.");

            int count = 3;
            do{
                System.out.println("Tente novamente.");
                if (count==3){
                    System.out.println("Voce possui "+count+" tentativa(s).");
                }
                ler.nextLine();
                System.out.print("Login: ");
                logAdmin = ler.nextLine();
                System.out.print("Senha: ");
                senhAdmin = ler.nextInt();

                count--;
                System.out.println("\nVoce possui "+count+" tentativa(s).");
                if (count == 0){
                    System.out.println("Login negado.");
                    //fechar programa
                    break;
                }
            } while (!logAdmin.equals(ad.nomeA) || senhAdmin != ad.senhaA);

            if (logAdmin.equals(ad.nomeA) && senhAdmin == ad.senhaA){
                System.out.println("Login bem-sucedido.");
            }
        }
        ler.close();
    }

    public static void main(String[] args) {

        loginA log = new loginA();
        admin ad = new admin();
        dados dd = new dados();
        primeiroAcessoA primAc = new primeiroAcessoA();

        if (ad.quantidadeA == 0){
            primAc.CadAd();
            primAc.setIdA();
            primAc.setQuantidadeA();
            dd.pedirPor();
            //System.out.println("Qtd de admins: "+ad.quantidadeA); //imprime 0 mesmo que o primeiroAcesso imprima 1 ou a quuantidade atual
        } else {
            log.loginAdmin();
        }
        
    }
}
