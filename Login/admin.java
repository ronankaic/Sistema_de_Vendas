package Login;

public class admin {
    
    public String nomeA;
    public int idA; //como gerar número aleatório?
    public int senhaA;
    public int quantidadeA = 1; //no máximo 2 administradores >> como por a restrição?
                           //^ mudar pra 1 pra testar o login mesmo, sem ser o cadastro

    public String getNomeA() { return nomeA; }
    public int getIdA() { return idA; }
    public int getSenhaA() { return senhaA; }
    public int getQuantidadeA() { return quantidadeA; }

    public void setNomeA(String nomeA) { this.nomeA = nomeA; }
    public void setIdA(int idA) { this.idA = idA; }
    public void setSenhaA(int senhaA) { this.senhaA = senhaA; }
    public void setQuantidadeA(int quantidadeA) { this.quantidadeA = quantidadeA; }
}
