package Login;

import java.util.Scanner;

public class Dados {

    Scanner ler = new Scanner(System.in);
    String taxaDebString, taxaCredString, email;
    Double taxaDeb, taxaCred;
    
    public void pedirPor(){
        System.out.println("Informe os seguintes valores em numero decimal usando ponto, nunca vírgula.");
        System.out.print("Taxa de débito: ");
        taxaDebString = ler.nextLine();
        taxaDeb = Double.parseDouble(taxaDebString); //conversão de string para double
        System.out.print("Taxa de crédito: ");
        taxaCredString = ler.nextLine();
        taxaCred = Double.parseDouble(taxaCredString); //conversão de string para double
        System.out.print("Informe o email para Pix: ");
        email = ler.nextLine();
        System.out.println("Informações cadastradas.");
    }

    public void tabelaDados(){
        
        /*CREATE TABLE IF NOT EXISTS Dados (id INTEGER PRIMARY KEY AUTOINCREMENT,
        taxa_deb DOUBLE, taxa_cred DOUBLE, email TEXT*/

        /*INSERT INTO Dados (taxa_deb, taxa_cred, email)
         * VALUES (taxaDeb, taxaCred, email)*/
        //try-catch SQL e se der certo:
        //System.out.println("Informações cadastradas.");
    }

    public void main(){

        pedirPor();
        //tabelaDados();
    }
}