import entities.Pessoa;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        var pessoa = new Pessoa("Anderson", "admin", "123456");

        int tentativas = 0;
        while(tentativas < 3) {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            if(pessoa.validarAcesso(usuario, senha)) {
                System.out.println("Login realizado com sucesso!");
                break;
            }else{
                System.out.println("Credenciais invalidas!. Restam " +(2 - tentativas));
                if(tentativas == 2){
                    System.out.println("Usuario bloqueado!");
                }
                tentativas++;
            }
        }
        sc.close();
    }
}