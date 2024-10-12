package Login;

import java.util.Scanner;
import java.util.Random;

public class PrimeiroAcessoA {

    Admin ad = new Admin();
    Funcionario func = new Funcionario();
    
    public void setIdA(){
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100000
        int randNumA = rand.nextInt(limite);
        if (randNumA > 10000){ //ID de admin obrigatoriamente < 10.000, ou seja, tem até 4 algarismos
            do{
                randNumA = rand.nextInt(limite);
            } while(randNumA > 10000);
        }

        this.ad.idA = randNumA;
        if (randNumA == func.idF){ //caso gere um ID igual ao id de algum funcionario
            randNumA = rand.nextInt(limite);
            this.ad.idA = randNumA;
        }
        System.out.println("ID de administrador: "+ad.idA);
    }

    public void CadAd(){ //public ou private?

        Scanner ler = new Scanner(System.in);
        
        //ler.next();
        System.out.println("Cadastro de Administrador");
        System.out.print("Login(seu nome): ");
        ad.nomeA = ler.nextLine();
        System.out.print("Senha(sequência numérica): "); //como limitar pra 7 digitos? >> mensagem de erro se passar de 7
        ad.senhaA = ler.nextInt();

        System.out.println("Login e senha definidos. Por favor, digite-os novamente para verificação.\n");
        System.out.print("Digite seu login: ");
        String VerLoginA = ler.next();
        System.out.print("Digite sua senha: ");
        int VerSenhaA = ler.nextInt();

        if (VerLoginA.equals(ad.nomeA) && VerSenhaA == ad.senhaA){

            System.out.println("Login e senha verificados.");
            
        } else if (!VerLoginA.equals(ad.nomeA) || VerSenhaA != ad.senhaA){

            System.out.println("Login ou senha incorretos.");

            do{
                System.out.println("Tente novamente.\n");
                System.out.print("Login: ");
                VerLoginA = ler.next();
                System.out.print("Senha: ");
                VerSenhaA = ler.nextInt();
            } while (!VerLoginA.equals(ad.nomeA) || VerSenhaA != ad.senhaA);

            System.out.println("Login e senha verificados.");
        }
        ler.close();
    }

    public void setQuantidadeA(){
        this.ad.quantidadeA++;
        System.out.println("Admin cadastrado.");
        System.out.println("Qtd de admins: "+this.ad.quantidadeA);
    }

    /*public void CadFunc(){ //public ou private?

        Scanner ler = new Scanner(System.in);
        
        System.out.println("\nCadastro de Funcionario");
        System.out.print("Login(seu nome): ");
        if (ler.hasNextLine()) { // Verifica se há uma linha disponível
            func.nomeF = ler.nextLine(); //problema com o nextLine, não está recebendo o input << concertar
        } else {
            System.out.println("Entrada inválida. Por favor, digite seu login.");
            return; // Sai da função se a entrada for inválida
        }
        System.out.print("Senha(sequência numérica): "); //como limitar pra 7 digitos? >> mensagem de erro se passar de 7
        func.senhaF = ler.nextInt();
        ler.nextLine();

        System.out.println("Login e senha definidos. Por favor, digite-os novamente para verificação.\n");
        System.out.print("Digite seu login: ");
        String VerLoginF = ler.next();
        System.out.print("Digite sua senha: ");
        int VerSenhaF = ler.nextInt();

        if (VerLoginF.equals(func.nomeF) && VerSenhaF == func.senhaF){

            System.out.println("Login e senha verificados.");
            
        } else if (!VerLoginF.equals(func.nomeF) || VerSenhaF != func.senhaF){

            System.out.println("Login ou senha incorretos.");

            do{
                System.out.println("Tente novamente.\n");
                System.out.print("Login: ");
                VerLoginF = ler.next();
                System.out.print("Senha: ");
                VerSenhaF = ler.nextInt();
            } while (!VerLoginF.equals(func.nomeF) || VerSenhaF != func.senhaF);

            System.out.println("Login e senha verificados.");
        }
        ler.close();
    }

    public void setQuantidadeF(){
        this.func.quantidadeF++;
        System.out.println("Funcionario adicionado");
        System.out.println("Qtd de func: "+this.func.quantidadeF);
    }*/

    public void main(String[] args) {

        //PrimeiroAcessoA primAc = new PrimeiroAcessoA();
        Dados dd = new Dados();
        //primAc.CadAd();
        CadAd();
        //primAc.setIdA();
        setIdA();
        //primAc.setQuantidadeA();
        setQuantidadeA();
        dd.pedirPor();
    }
}
