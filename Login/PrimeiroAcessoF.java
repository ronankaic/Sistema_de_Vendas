package Login;

import java.util.Scanner;
import java.util.Random;

public class PrimeiroAcessoF {

    Funcionario func = new Funcionario();
    Admin ad = new Admin();
    Scanner ler = new Scanner(System.in);

    public void setIdF(){
        Random rand = new Random();
        int limite = 100000; // gera um inteiro aleatório entre 0 e 100.000
        int randNumF = rand.nextInt(limite);
        if (randNumF < 10000){ //ID de funcioinário obrigatoriamente > 10.000, ou seja, tem 5 algarismos ou +
            do{
                randNumF = rand.nextInt(limite);
            } while(randNumF < 10000);
        }
        this.func.idF = randNumF;
        System.out.println("ID de funcionário: "+func.idF);
    }

    public void CadFunc(){

        System.out.println("\nCadastro de funcionário");
        System.out.print("Login(seu nome): ");
        this.func.nomeF = ler.nextLine();
        System.out.print("Senha(sequência numérica de 5 digitos): ");
        this.func.senhaF = ler.nextInt();
        while (func.senhaF < 10000 || func.senhaF > 100000) {
            System.out.println("A senha deve ter no mínimo e no máximo 5 digitos.");
            System.out.print("Senha: ");
            this.func.senhaF = ler.nextInt();
        }
        ler.nextLine();

        System.out.println("Login e senha definidos. Por favor, digite-os novamente para verificação.\n");
        System.out.print("Digite seu login: ");
        String VerLoginF = ler.next();
        System.out.print("Digite sua senha: ");
        int VerSenhaF = ler.nextInt();

        if (VerLoginF.equals(func.nomeF) && VerSenhaF == func.senhaF){

            System.out.println("Login e senha verificados.");
            
        } else if (!VerLoginF.equals(func.nomeF) || VerSenhaF != func.senhaF){

            System.out.println("\nLogin ou senha incorretos.");

            do{
                System.out.println("Tente novamente.\n");
                System.out.print("Login: ");
                VerLoginF = ler.next();
                System.out.print("Senha: ");
                VerSenhaF = ler.nextInt();
            } while (!VerLoginF.equals(func.nomeF) || VerSenhaF != func.senhaF);

            System.out.println("Login e senha verificados.");
        }
    }

    public void setQuantidadeF(){ //com a implementação do BD, retirar pois a quantidade vai ser incrementada automaticamente e consultada pelo login no BD
        this.func.quantidadeF++;
        System.out.println("Funcionário cadastrado.");
        System.out.println("Qtd de func: "+this.func.quantidadeF+"\n");
    }

    public void tabelaFunc(){
        
        /*CREATE TABLE IF NOT EXISTS Funcionario (id INTEGER PRIMARY KEY,
        nome TEXT,
        senha INTEGER PRIMARY KEY,
        quantidade INTEGER AUTOINCREMENT*/

        /*INSERT INTO Funcionario (id, nome, senha)
         * VALUES (func.idA, func.nomeF, func.senhaF)
        */
        //se tiver sucesso:
        //System.out.println("Funcionário cadastrado.");
        //System.out.println("Qtd de func: "+this.func.quantidadeF+"\n"); >> pegar do BD
    }

    public void main(String[] args) {

        CadFunc();
        setIdF();
        setQuantidadeF();
        tabelaFunc();

    }
}
