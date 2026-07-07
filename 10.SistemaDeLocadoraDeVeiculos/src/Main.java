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
            System.out.println("01 - Cadastrar cliente");
            System.out.println("02 - Listar clientes");
            System.out.println("03 - Buscar Cliente por ID");
            System.out.println("04 - Excluir cliente");
            System.out.println("05 - Cadastrar Veiculo");
            System.out.println("06 - Listar Veiculos");
            System.out.println("07 - Buscar Veiculo por Placa");
            System.out.println("08 - Buscar Veiculo por Id");
            System.out.println("09 - Excluir Veiculo por Id");
            System.out.println("10 - Excluir Veiculo por Placa");
            System.out.println("11 - Alugar Veiculo");
            System.out.println("0 - Sair");
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
                    menu.cadastrarVeiculo();
                    break;
                case 6:
                    menu.listarVeiculos();
                    break;
                case 7:
                    menu.buscarVeiculoPorPlaca();
                    break;
                case 8:
                    menu.buscarVeiculoPorId();
                    break;
                case 9:
                    menu.excluirVeiculoPorId();
                    break;
                case 10:
                    menu.excluirVeiculoPorPlaca();
                    break;
                case 11:
                    menu.criarLocacao();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Saindo....");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
        sc.close();
    }
}