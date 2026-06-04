import entities.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Produto produto = new Produto("Notebook Dell", 10);
        int quantidade  = 0;
        String msg = "";
        boolean executando = true;

        while(executando){
            System.out.println("1 - Adicionar estoque");
            System.out.println("2 - Remover estoque");
            System.out.println("3 - Consultar estoque");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            switch(opcao){
                case 1:
                    System.out.println("Adicionar estoque");
                    System.out.print("Quantidade: ");
                    quantidade = sc.nextInt();
                    msg  = produto.adicionarEstoque(quantidade);
                    System.out.println(msg);
                    break;
                case 2:
                    System.out.print("Remover estoque");
                    System.out.print("Quantidade: ");
                    quantidade = sc.nextInt();
                    msg = produto.removerEstoque(quantidade);
                    System.out.println(msg);
                    break;
                case 3:
                    System.out.println(produto.exibirProduto());
                    break;
                case 4:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }
}