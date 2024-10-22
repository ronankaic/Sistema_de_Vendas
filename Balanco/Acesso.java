package Balanco;

import Login.Admin;
import Login.Funcionario;
import java.util.Scanner;

public class Acesso {

    Admin ad = new Admin();
    Funcionario func = new Funcionario();
    Scanner ler = new Scanner(System.in);

    public void acessar(){
        
        System.out.print("Seu ID: ");
        int id = ler.nextInt();
        String nome = "Leticia"; //apagar
        this.ad.idA = id; //
        this.ad.nomeA = nome; //

        if (id == ad.idA){ //verificação no BD
            System.out.printf("Olá, "+ad.getNomeA()+ ". ");
            visualizacao();
        } else if (id == func.idF){ //verificação no BD
            System.out.printf("Olá, "+func.getNomeF()+ ". ");
            visualizacao();
        } else {
            System.out.println("ID não reconhecido.");
        }
    }

    public void visualizacao(){

        int vis;
        
        /*comando para realizar a consulta de todas as vendas
         *  SELECT * saidas
            ORDER BY ano, mes, dia;
        */

        System.out.println("Como deseja filtrar o balanço?");
        System.out.println("1 - Por forma de pagamento\n2 - Por dia\n3 - Por mês\n4 - Por ano"); //botões
        vis = ler.nextInt();

        switch (vis) {
            case 1: 
                System.out.println("5 - Dinheiro\n6 - Pix\n7 - Cartão"); //abrir como coluna  a ser selecionada
                int fpag = ler.nextInt();
                if (fpag == 5){
                    /*SELECT * FROM saidas
                    WHERE forma_pagamento = 'Dinheiro'
                    ORDER BY ano, mes, dia; */
                } else if (fpag == 6){
                    /*SELECT * FROM saidas
                    WHERE forma_pagamento = 'Pix'
                    ORDER BY ano, mes, dia; */
                } else if (fpag == 7){
                    /*SELECT * FROM saidas
                    WHERE forma_pagamento = 'Cartão'
                    ORDER BY ano, mes, dia; */
                }
                break;
            case 2:
                System.out.println("Entre o dia desejado"); //campo de escrita
                int dia_selecionado = ler.nextInt();
                //try
                /*SELECT * from saidas
                where dia = dia_selecionado
                ORDER BY ano, mes, dia, forma_pagamento;*/
                System.out.println("O valor total vendido no dia foi de: "); //+valorTotal
                //catch (exception: tal dia não existe no bd){
                //sout("Dia sem entradas")
                //}
                break;
            case 3:
                System.out.println("Entre o mês desejado"); //campo de escrita
                int mes_selecionado = ler.nextInt(); //coluna de seleção
                //try:
                /*SELECT id, id_produto, nome_produto, preco, forma_pagamento, quantidade, mes, ano from saidas
                WHERE mes = mes_selecionado
                ORDER BY ano, mes, forma_pagamento */
                System.out.println("O valor total vendido no mês foi de: "); //+valorTotal
                //catch (exception: tal mês não existe no bd){
                //sout("Mês sem entradas")
                //}
                break;
            case 4:
            System.out.println("Entre o ano desejado"); //campo de escrita
            int ano_selecionado = ler.nextInt(); //coluna de seleção
            //try:
            /*SELECT id, id_produto, nome_produto, preco, forma_pagamento, quantidade, mes, ano from saidas
            WHERE mes = ano_selecionado
            ORDER BY ano, mes, forma_pagamento */
            System.out.println("O valor total vendido no ano foi de: "); //+valorTotal
            //catch (exception: tal ano não existe no bd){
            //sout("Ano sem entradas")
            //}
                break;
            default:
                System.out.println("Entrada inválida.");
                break;
        }
    }

    public static void main(String[] args){
        Acesso ac = new Acesso();
        ac.acessar();
    }
}