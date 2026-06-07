import entities.Biblioteca;
import entities.Livro;
import entities.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        System.out.println(biblioteca.cadastrarLivro("Titulo1", "Autor1"));
        System.out.println(biblioteca.cadastrarLivro("Titulo2", "Autor2"));
        biblioteca.cadastrarLivro("Titulo3", "Autor3");
        biblioteca.cadastrarLivro("Titulo4", "Autor4");

        biblioteca.cadastrarUsuario("Pedro");
        biblioteca.cadastrarUsuario("Joao");
        biblioteca.cadastrarUsuario("Maria");


        boolean executando = true;
        while(executando){
            int opcao;
            int idUsuario;
            int idLivro;
            System.out.println("1 Cadastrar Livro");
            System.out.println("2 Cadastrar usuário");
            System.out.println("3 Listar livros");
            System.out.println("4 Listar usuarios");
            System.out.println("5 Emprestar livro");
            System.out.println("6 Devolver livro");
            System.out.println("7 Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Castrar livro");
                    System.out.print("Nome: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.println(biblioteca.cadastrarLivro(titulo, autor));
                    break;
                case 2:
                    System.out.println("Cadastrar usuario");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.println(biblioteca.cadastrarUsuario(nome));
                    break;
                case 3:
                    var livros = biblioteca.ListarLivros();

                    if(livros.isEmpty()){
                        System.out.println("Nenhum livro encontrado");
                    }else{
                        for(Livro livro : livros){
                            System.out.println(livro);
                        }
                    }
                    break;
                case 4:
                    var usuarios = biblioteca.ListarUsuarios();
                    if(usuarios.isEmpty()){
                        System.out.println("Nenhum usuario encontrado");
                    }else{
                        for(Usuario usuario : usuarios){
                            System.out.println(usuario);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Emprestar livro");

                    int[] dadosEmprestimo = lerDadosEmprestimo(sc);

                    System.out.println(biblioteca.EmprestarLivro(dadosEmprestimo[0], dadosEmprestimo[1]));
                    break;
                case 6:
                    System.out.println("Devolver livro");

                    int[] dadosDevolucao = lerDadosEmprestimo(sc);

                    System.out.println(biblioteca.devolverLivro(dadosDevolucao[0], dadosDevolucao[1]));
                    break;
                case 7:
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }
    private static int[] lerDadosEmprestimo(Scanner sc){
        System.out.print("Id usuário: ");
        int idUsuario = sc.nextInt();

        System.out.print("Id livro: ");
        int idLivro = sc.nextInt();

        sc.nextLine();

        return new int[]{idUsuario, idLivro};
    }
}


