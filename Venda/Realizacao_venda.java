package Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Realizacao_venda {

    /*- Realização de venda
     ^ Valor pago, valor total, troco e total de itens
     ^ Lista de produtos(adicionar, alterar(nome e preço), excluir) da venda >> a ser feito
     ^ Forma de pagamento default é dinheiro > caso não informado, será dinheiro
     ^ Opção de FP em cada venda
          ^ Caso seja cartão, cálculo automático de taxa > solicitado no registro de login
     ^ Opção de registrar/finalizar venda >> do-while com opção pra finalizar?*/

    Scanner ler = new Scanner(System.in);
    Produto p = new Produto();
    List<Produto> vendaAtual = new ArrayList<Produto>();
    //List<Produto> vendido = new ArrayList<Produto>(); //lista p armazenar tudo que vender, p ser usada no balanço
    int FP;
    float valorTotal = 0;

    public void infoDaVenda(){
        
        int enc;

        do{
            System.out.printf("Nome do produto: ");
            p.nome = ler.nextLine();
            System.out.printf("Preço: ");
            p.prec = ler.nextFloat();
            System.out.println("Digite 1 para encerrar venda.");
            enc = ler.nextInt();
            ler.nextLine();
            
            vendaAtual.add(p);
            for (int i=0; i<vendaAtual.size(); i++){
                valorTotal += p.prec;
            }            
        } while(enc != 1);

        System.out.printf("Quantidade total de itens: " +vendaAtual.size()+ ". O valor total ficou em RS%.2f" +valorTotal+ ".");
        //System.out.println("Nome do produto: "+p.getNome()+"\nPreco: "+p.getPrec());
        System.out.println("\nForma de pagamento: ");
        System.out.println("1 - dinheiro\n2 - pix\n3 - cartao");
        FP = ler.nextInt();
        pagamento();

        //vendido.add(p);
        //vendaAtual = null; //Sempre que finalizar a venda, zerar a vendaAtual

        ler.close();
    }

    public void pagamento(){

        Double pago = 0., troco, valorDeb, valorCred, taxaDeb = 0.9770, taxaCred = 0.9450; //taxas a serem inseridas no login de admin
        char debCred;

        valorDeb = valorTotal/taxaDeb;
        valorCred = valorTotal/taxaCred;

        switch (FP) { //case para escolher forma de pagamento
            case 1:
                System.out.print("Pago em especie: ");
                pago = ler.nextDouble(); //posso colocar aqui mesmo que a conta que usa 'pago' esteja antes de sua atribuição de valor?
                System.out.println("Valor total: " + valorTotal);
                troco = pago - valorTotal;
                System.out.println("Troco: %.2f" + troco);
                break;
            case 2:
                System.out.println("Chave pix - @email"); //tem como eixibir imagem no front-end? >>img do QR code
                System.out.println("Valor total: %.2f" + valorTotal + ".\nPeça comprovante."); //n é pedido valor a ser pago pq n tem troco evai ser mostrado o comprovante
            case 3:
                System.out.println("Valor a pagar: " + valorTotal);
                System.out.println("Debito ou credito?\n1 - debito\n2 - credito"); //desenvolver
                debCred = ler.next().charAt(0);
                if (debCred == '1'){
                    System.out.println("Valor total: "+valorDeb);
                } else if (debCred == '2'){
                    System.out.println("Valor total: "+valorCred);
                } else {
                    System.out.println("Escolha entre debito e credito."); //a intenção é não entrar nesse else, então n precisa de do-while
                }
            default:
                System.out.println("Escolha uma forma de pagamento válida");
                break;
        }
    }
    
    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        String venda;

        Realizacao_venda RV = new Realizacao_venda();

        System.out.print("Iniciar venda: ");
        venda = ler.nextLine();
        if (venda.equalsIgnoreCase("S")){
            RV.infoDaVenda();
        } else if (venda.equalsIgnoreCase("N")){
            System.out.println("Ok. Venda cancelada.");
        }
        

        ler.close();

        
    }
}