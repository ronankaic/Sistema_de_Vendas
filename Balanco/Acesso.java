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
            GROUP BY forma_pagamento
            ORDER BY EXTRACT(MONTH FROM data_hora) AS 'mês', EXTRACT(DAY FROM data_hora) AS 'dia';
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
                    ORDER BY EXTRACT(MONTH FROM data_hora), EXTRACT(DAY FROM data_hora); */
                } else if (fpag == 6){
                    /*SELECT * FROM saidas
                    WHERE forma_pagamento = 'Pix'
                    ORDER BY EXTRACT(MONTH FROM data_hora), EXTRACT(DAY FROM data_hora); */
                } else if (fpag == 7){
                    /*SELECT * FROM saidas
                    WHERE forma_pagamento = 'Cartão_debito', forma_pagamento = 'Cartão_credito' >> se der erro em mostrar as duas FP tentar com AND/OR
                    GROUP BY forma_pagamento
                    ORDER BY EXTRACT(MONTH FROM data_hora), EXTRACT(DAY FROM data_hora); */
                }
                break;
            case 2:
                /* SELECT EXTRACT(DAY FROM data_hora) AS 'dia', id, produto_id, nome_produto, preço, quantidade, forma_pagamento FROM saidas
                GROUP BY forma_pagamento
                SUM(preço) AS 'Valor total vendido';*/
                break;
            case 3:
                /*SELECT EXTRACT(MONTH FROM data_hora) AS 'Mês', EXTRACT(DAY FROM data_hora) AS 'Dia', id, produto_id, nome_produto, preço, quantidade, forma_pagamento
                SUM(preço) AS 'Valor total vendido'
                WHERE EXTRACT(DAY FROM data_hora)
                    SUM(preço) AS 'Total do dia'
                FROM saidas
                GROUP BY forma_pagamento; */
                break;
            case 4:
                /* SELECT EXTRACT(YEAR FROM data_hora) AS 'Ano', EXTRACT(MONTH FROM data_hora) AS 'Mês', id, produto_id, nome_produto, preço, quantidade, forma_pagamento
                SUM(preço) AS 'Valor total vendido'
                WHERE EXTRACT(MONTH FROM data_hora)
                    SUM(preço) AS 'Total do mês'
                FROM saidas
                GROUP BY forma_pagamento; */
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