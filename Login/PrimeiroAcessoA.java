package Login;

import java.util.Scanner;
import java.util.Random;

public class PrimeiroAcessoA {

    Admin ad = new Admin();
    Funcionario func = new Funcionario();
    Scanner ler = new Scanner(System.in);

    public void setIdA(){
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100.000
        int randNumA = rand.nextInt(limite);
        if (randNumA > 10000){ //ID de admin obrigatoriamente < 10.000, ou seja, tem até 4 algarismos
            do{
                randNumA = rand.nextInt(limite);
            } while(randNumA > 10000);
        }
        this.ad.idA = randNumA;
        System.out.println("ID de administrador: "+ad.idA);
    }

    public void CadAd(){

        System.out.println("Cadastro de administrador");
        System.out.print("Login(seu nome): ");
        this.ad.nomeA = ler.nextLine();
        System.out.print("Senha(sequência numérica de 5 digitos): ");
        this.ad.senhaA = ler.nextInt();
        while (ad.senhaA < 10000 || ad.senhaA > 100000) {
            System.out.println("A senha deve ter no mínimo e no máximo 5 digitos.");
            System.out.print("Senha: ");
            this.ad.senhaA = ler.nextInt();
        }

        System.out.println("Login e senha definidos. Por favor, digite-os novamente para verificação.\n");
        System.out.print("Digite seu login: ");
        String VerLoginA = ler.next();
        System.out.print("Digite sua senha: ");
        int VerSenhaA = ler.nextInt();

        if (VerLoginA.equals(ad.nomeA) && VerSenhaA == ad.senhaA){

            System.out.println("Login e senha verificados.");

        } else if (!VerLoginA.equals(ad.nomeA) || VerSenhaA != ad.senhaA){

            System.out.println("\nLogin ou senha incorretos.");

            do{
                System.out.println("Tente novamente.\n");
                System.out.print("Login: ");
                VerLoginA = ler.next();
                System.out.print("Senha: ");
                VerSenhaA = ler.nextInt();
            } while (!VerLoginA.equals(ad.nomeA) || VerSenhaA != ad.senhaA);

            System.out.println("Login e senha verificados.");
        }
    }

    public void setQuantidadeA(){ //com a implementação do BD, retirar pois a quantidade vai ser incrementada automaticamente e consultada pelo login no BD
        this.ad.quantidadeA++;
        System.out.println("Administrador cadastrado.");
        System.out.println("Qtd de admins: "+this.ad.quantidadeA+"\n");
    }

    public void tabelaAdmin(){
        
        /*CREATE TABLE IF NOT EXISTS Admin (id INTEGER PRIMARY KEY,
        nome TEXT,
        senha INTEGER PRIMARY KEY,
        quantidade INTEGER AUTOINCREMENT*/

        /*INSERT INTO Admin (id, nome, senha)
         * VALUES (ad.idA, ad.nomeA, ad.senhaA)
         */
        //se tiver sucesso:
        //System.out.println("Administrador cadastrado.");
        //System.out.println("Qtd de admins: "+this.ad.quantidadeA+"\n"); >> pegar do BD
    }

    public void main(String[] args) {

        Dados dd = new Dados();
        CadAd();
        setIdA();
        setQuantidadeA();
        tabelaAdmin();
        if (dd.taxaDebString == "" || dd.taxaCredString == "" || dd.email == ""){ //verificação no BD se já tem as info cadastradas
            dd.main();
        } else {
            System.out.println("Foram encontradas informações cadastradas.");
            System.out.printf("Taxa de débito: "+dd.taxaDeb+"\nTaxa de crédito: "+dd.taxaCred+"\nEmail para pix: ", dd.email); //importar os valores do BD
            System.out.println("\nDeseja sobrescrevê-las?");
            char sobr = ler.next().charAt(0);
            if (sobr == 's'){ //botão
                dd.main();
            } else if (sobr == 'n'){
                System.out.println("Ok, as informações serão mantidas.\n");
            }
        }
    }
}
