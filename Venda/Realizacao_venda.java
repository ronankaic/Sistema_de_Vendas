package Venda;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Realizacao_venda {
    private static final String URL_PRODUTOS;
    private static final String URL_SAIDA;
    private static final Scanner scanner;
    private static final List<Produto> carrinho;
    private int ano;
    private int mes;
    private int dia;

    public void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connProdutos = DriverManager.getConnection(URL_PRODUTOS);
            Connection connSaida = DriverManager.getConnection(URL_SAIDA);
            criarTabelaSaida(connSaida);
            if (connProdutos != null && connSaida != null) {
                boolean continuar = true;

                while (true) {
                    label55:
                    while (continuar) {
                        limparTerminal();
                        System.out.println("Escolha uma opção:");
                        System.out.println("1. Listar produtos");
                        System.out.println("2. Cadastrar produto");
                        System.out.println("3. Adicionar produto ao carrinho");
                        System.out.println("4. Excluir produto");
                        System.out.println("5. Finalizar compra");
                        System.out.println("6. Ver lista de saídas");
                        System.out.println("7. Sair");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcao) {
                            case 1:
                                listarProdutos(connProdutos);
                                break;
                            case 2:
                                cadastrarProduto(connProdutos);
                                break;
                            case 3:
                                adicionarProdutosAoCarrinho(connProdutos);
                                break;
                            case 4:
                                excluirProduto(connProdutos);
                                break;
                            case 5:
                                if (!carrinho.isEmpty()) {
                                    double total = calcularTotal(carrinho);
                                    String formaPagamento = escolherFormaPagamento();
                                    total = aplicarTaxa(total, formaPagamento);
                                    limparTerminal();
                                    System.out.printf("Total: R$ %.2f%n", total);
                                    System.out.printf("Forma de Pagamento: %s%n", formaPagamento);
                                    if (formaPagamento.equals("Pix")) {
                                        System.out.println();
                                        System.out.println("E-mail para pagamento via Pix: sandrasxr123@gmail.com");
                                    }


                                    registrarSaida(connSaida, carrinho, total, formaPagamento, ano, mes, dia);
                                    carrinho.clear();
                                    boolean voltar = true;

                                    while (true) {
                                        if (!voltar) {
                                            continue label55;
                                        }

                                        System.out.println("\nDeseja voltar para a tela inicial: ");
                                        System.out.println("1. Recomeçar a Venda");
                                        System.out.println("2. Finalizar o Programa");
                                        int resposta = scanner.nextInt();
                                        if (resposta == 1) {
                                            voltar = false;
                                        } else if (resposta == 2) {
                                            continuar = false;
                                            voltar = false;
                                        } else {
                                            System.out.println("Opção inválida. Por favor, digite '1' ou '2'.");
                                        }
                                    }
                                }

                                break;
                            case 6:
                                listarSaidas(connSaida);
                                break;
                            case 7:
                                continuar = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }

                    connProdutos.close();
                    connSaida.close();
                    break;
                }
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException var11) {
            ClassNotFoundException e = var11;
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        }

    }

    private static void criarTabelaSaida(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS saidas (id INTEGER PRIMARY KEY AUTOINCREMENT, produto_id INTEGER, nome_produto TEXT, preco REAL, quantidade INTEGER, forma_pagamento TEXT, ano INTERGER, mes INTERGER, dia INTERGER);";

        try {
            Statement stmt = conn.createStatement();

            try {
                stmt.executeUpdate(sql);
            } catch (Throwable var6) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            System.out.println("Erro ao criar tabela de saídas: " + e.getMessage());
        }

    }

    private static void listarProdutos(Connection conn) {
        limparTerminal();
        String sql = "SELECT * FROM produtos;";

        try {
            Statement stmt = conn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery(sql);

                try {
                    System.out.println("Lista de produtos:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");
                        int estoque = rs.getInt("estoque");
                        System.out.printf("ID: %d, Nome: %s, Preço: %.2f, Estoque: %d%n", id, nome, preco, estoque);
                    }
                } catch (Throwable var11) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var10) {
                            var11.addSuppressed(var10);
                        }
                    }

                    throw var11;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var12) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var9) {
                        var12.addSuppressed(var9);
                    }
                }

                throw var12;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var13) {
            SQLException e = var13;
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        scanner.nextLine();
    }

    private static void cadastrarProduto(Connection conn) {
        String sql = "SELECT MAX(id) AS max_id FROM produtos;";
        int ultimoId = 0;

        try {
            Statement stmt = conn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery(sql);

                try {
                    if (rs.next()) {
                        ultimoId = rs.getInt("max_id") + 1;
                    }
                } catch (Throwable var16) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var13) {
                            var16.addSuppressed(var13);
                        }
                    }

                    throw var16;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var17) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var12) {
                        var17.addSuppressed(var12);
                    }
                }

                throw var17;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var18) {
            SQLException e = var18;
            System.out.println("Erro ao buscar o último ID: " + e.getMessage());
        }

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Digite a quantidade em estoque: ");
        int estoque = scanner.nextInt();
        String insertSql = "INSERT INTO produtos (id, nome, preco, estoque) VALUES (" + ultimoId + ", '" + nome + "', " + preco + ", " + estoque + ");";

        try {
            Statement stmt = conn.createStatement();

            try {
                stmt.executeUpdate(insertSql);
                System.out.println("Produto cadastrado com sucesso! Último ID: " + ultimoId);
            } catch (Throwable var14) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var11) {
                        var14.addSuppressed(var11);
                    }
                }

                throw var14;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }

    }

    private static void adicionarProdutosAoCarrinho(Connection conn) {
        listarProdutos(conn);
        boolean continuar = true;

        while (true) {
            while (continuar) {
                System.out.print("Digite o ID do produto que deseja adicionar ao carrinho (ou 0 para finalizar): ");
                int idProduto = scanner.nextInt();
                if (idProduto == 0) {
                    continuar = false;
                } else {
                    String sql = "SELECT * FROM produtos WHERE id = " + idProduto + ";";

                    try {
                        Statement stmt = conn.createStatement();

                        try {
                            ResultSet rs = stmt.executeQuery(sql);

                            try {
                                if (rs.next()) {
                                    String nome = rs.getString("nome");
                                    double preco = rs.getDouble("preco");
                                    int estoque = rs.getInt("estoque");
                                    System.out.print("Digite a quantidade desejada: ");
                                    int quantidade = scanner.nextInt();
                                    if (quantidade > 0 && quantidade <= estoque) {
                                        carrinho.add(new Produto(idProduto, nome, preco, quantidade));
                                        System.out.printf("Produto adicionado: %s - R$ %.2f - Quantidade: %d%n", nome, preco, quantidade);
                                        atualizarEstoque(conn, idProduto, estoque - quantidade);
                                    } else {
                                        System.out.println("Quantidade inválida ou não disponível em estoque.");
                                    }
                                } else {
                                    System.out.println("Produto não encontrado.");
                                }
                            } catch (Throwable var13) {
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (Throwable var12) {
                                        var13.addSuppressed(var12);
                                    }
                                }

                                throw var13;
                            }

                            if (rs != null) {
                                rs.close();
                            }
                        } catch (Throwable var14) {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (Throwable var11) {
                                    var14.addSuppressed(var11);
                                }
                            }

                            throw var14;
                        }

                        if (stmt != null) {
                            stmt.close();
                        }
                    } catch (SQLException var15) {
                        SQLException e = var15;
                        System.out.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
                    }
                }
            }

            return;
        }
    }

    private static void excluirProduto(Connection conn) {
        listarProdutos(conn);
        System.out.print("Digite o ID do produto que deseja excluir: ");
        int idProduto = scanner.nextInt();
        String sql = "DELETE FROM produtos WHERE id = " + idProduto + ";";

        try {
            Statement stmt = conn.createStatement();

            try {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("Produto excluído com sucesso.");
                } else {
                    System.out.println("Produto não encontrado.");
                }
            } catch (Throwable var7) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var8) {
            SQLException e = var8;
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }

    }

    private static void atualizarEstoque(Connection conn, int idProduto, int novoEstoque) {
        String sql = "UPDATE produtos SET estoque = ? WHERE id = ?;";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            try {
                pstmt.setInt(1, novoEstoque);
                pstmt.setInt(2, idProduto);
                pstmt.executeUpdate();
            } catch (Throwable var8) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException var9) {
            SQLException e = var9;
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
        }

    }

    private static double calcularTotal(List<Produto> carrinho) {
        double total = 0.0;

        Produto produto;
        for (Iterator var3 = carrinho.iterator(); var3.hasNext(); total += produto.getPreco() * (double) produto.getQuantidade()) {
            produto = (Produto) var3.next();
        }

        return total;
    }

    private static String escolherFormaPagamento() {
        limparTerminal();
        System.out.printf("Total: %.2f%n", calcularTotal(carrinho));
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1. Dinheiro");
        System.out.println("2. Pix");
        System.out.println("3. Cartão");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                return "Dinheiro";
            case 2:
                return "Pix";
            case 3:
                return "Cartão";
            default:
                System.out.println("Opção inválida. O pagamento será considerado como Dinheiro.");
                return "Dinheiro";
        }
    }

    private static double aplicarTaxa(double total, String formaPagamento) {
        if (formaPagamento.equals("Cartão")) {
            System.out.println("Escolha o tipo de cartão:");
            System.out.println("1. Débito");
            System.out.println("2. Crédito");
            int tipoCartao = scanner.nextInt();
            if (tipoCartao == 1) {
                return total;
            }

            if (tipoCartao == 2) {
                total *= 1.05;
            }
        }

        return total;
    }

    private static void registrarSaida(Connection conn, List<Produto> carrinho, double total, String formaPagamento, int ano, int mes, int dia) {
        LocalDate dataAtual = LocalDate.now();
        ano = dataAtual.getYear();
        mes = dataAtual.getMonthValue();
        dia = dataAtual.getDayOfMonth();

        String sql = "INSERT INTO saidas (produto_id, nome_produto, preco, quantidade, forma_pagamento, ano, mes, dia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            try {
                Iterator var7 = carrinho.iterator();

                while (true) {
                    if (!var7.hasNext()) {
                        pstmt.executeBatch();
                        System.out.println("Compra registrada com sucesso!");
                        break;
                    }


                    Produto produto = (Produto) var7.next();
                    pstmt.setInt(1, produto.getId());
                    pstmt.setString(2, produto.getNome());
                    pstmt.setDouble(3, produto.getPreco());
                    pstmt.setInt(4, produto.getQuantidade());
                    pstmt.setString(5, formaPagamento);
                    pstmt.setInt(6, ano);
                    pstmt.setInt(7, mes);
                    pstmt.setInt(8, dia);
                    pstmt.addBatch();
                }
            } catch (Throwable var10) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            System.out.println("Erro ao registrar a saída: " + e.getMessage());
        }

    }

    public static void listarSaidas(Connection conn) {
        String sql = "SELECT * FROM saidas;";

        try {
            Statement stmt = conn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery(sql);

                try {
                    System.out.println("Lista de saídas:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nomeProduto = rs.getString("nome_produto");
                        double preco = rs.getDouble("preco");
                        int quantidade = rs.getInt("quantidade");
                        String formaPagamento = rs.getString("forma_pagamento");
                        int dia = rs.getInt("dia");
                        int mes = rs.getInt("mes");
                        int ano = rs.getInt("ano");
                        System.out.printf("ID: %d, Produto: %s, Preço: %.2f, Quantidade: %d, Forma de Pagamento: %s, Data:  %d/%d/%d%n", id, nomeProduto, preco, quantidade, formaPagamento, dia, mes, ano);
                    }
                } catch (Throwable var13) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var12) {
                            var13.addSuppressed(var12);
                        }
                    }

                    throw var13;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var14) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var11) {
                        var14.addSuppressed(var11);
                    }
                }

                throw var14;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var15) {
            SQLException e = var15;
            System.out.println("Erro ao listar saídas: " + e.getMessage());
        }

        scanner.nextLine();
    }

    private static void limparTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                (new ProcessBuilder(new String[]{"cmd", "/c", "cls"})).inheritIO().start().waitFor();
            } else {
                (new ProcessBuilder(new String[]{"bash", "-c", "clear"})).inheritIO().start().waitFor();
            }
        } catch (Exception var1) {
            Exception e = var1;
            System.out.println("Erro ao limpar o terminal: " + e.getMessage());
        }

    }

    static {
        try {
            String jarDir = (new File(Realizacao_venda.class.getProtectionDomain().getCodeSource().getLocation().toURI())).getParent();
            String produtosDbPath = jarDir + File.separator + "produtos.db";
            String saidaDbPath = jarDir + File.separator + "saida.db";
            URL_PRODUTOS = "jdbc:sqlite:" + produtosDbPath;
            URL_SAIDA = "jdbc:sqlite:" + saidaDbPath;
        } catch (Exception var3) {
            Exception e = var3;
            throw new RuntimeException("Erro ao definir a URL dos bancos de dados: " + e.getMessage());
        }

        scanner = new Scanner(System.in);
        carrinho = new ArrayList();
    }
}