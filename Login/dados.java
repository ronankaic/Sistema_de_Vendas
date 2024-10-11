package Login;

import java.util.Scanner;

public class dados {

    Scanner ler = new Scanner(System.in);
    Double taxaDeb, taxaCred; //exportar para outro arquivo
    String email;
    
    public void pedirPor(){
        System.out.println("Informe em decimal a taxa de debito: ");
        taxaDeb = ler.nextDouble();
        System.out.println("Informe em decimal a taxa de credito: ");
        taxaCred = ler.nextDouble();
        System.out.println("Informe o email para Pix: ");
        email = ler.nextLine();
    }
}
