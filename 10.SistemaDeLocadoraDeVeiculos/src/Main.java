import system.SistemaLocadora;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaLocadora sistema = new SistemaLocadora();
        Menu menu = new Menu(sistema);
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("Menu");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar Cliente por ID");
            System.out.println("4 - Excluir cliente");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    menu.cadastrarCliente();
                    break;
                case 2:
                    menu.listarClientes();
                    break;
                case 3:
                    menu.buscarClientePorId();
                    break;
                case 4:
                    menu.deletarCliente();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
        sc.close();
    }
}