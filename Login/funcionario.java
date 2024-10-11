package Login;

public class funcionario {
    
    public String nomeF;
    public int idF;
    public int senhaF;
    public int quantidadeF = 1; //quantos funcionários quiser

    public String getNomeF() { return nomeF; }
    public int getIdF() { return idF; }
    public int getSenhaF() { return senhaF; }
    public int getQuantidadeF() { return quantidadeF; }

    public void setNomeF(String nomeF) { this.nomeF = nomeF; }
    public void setIdF(int idF) { this.idF = idF; }
    public void setSenhaF(int senhaF) { this.senhaF = senhaF; }
    public void setQuantidadeF(int quantidadeF) { this.quantidadeF = quantidadeF; }
}
