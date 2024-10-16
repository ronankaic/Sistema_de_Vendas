package Balanco;

import Login.LoginA;

public class Acesso {

    /*é melhor pedir um login geral, aí a pessoa fornece o ID dela, o sistema confere se é admin ou funionário e então
     * faz autenticação e aí permite visualizar/alterar
     * ou
     * login separado pra admin e funcionário
     */

    LoginA logA = new LoginA();

    public void acessoA(){
        
        logA.loginAdmin();
        if (logA.permissaoLogin.equals("Permitido")){
            //
        } else {
            //Acesso negado
        }
    }

    public void acessoF(){
        //
    }

    public void main(String[] args){
        //
    }
}
