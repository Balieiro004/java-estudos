package ui;

import entities.Livro;
import entities.Usuario;
import enums.CategoriaLivro;
import system.SystemaBiblioteca;

import java.util.List;
import java.util.Scanner;

public class Menu {

    SystemaBiblioteca sistemaBiblioteca;
    Scanner sc;

    public Menu(SystemaBiblioteca sistemaBiblioteca, Scanner sc) {
        this.sistemaBiblioteca = sistemaBiblioteca;
        this.sc = sc;
    }

    public void iniciar(){

        int opcao;

        do{
            System.out.println("=======MENU=======");
            System.out.println("1 Cadastrar Livro");
            System.out.println("2 Listar Livros");
            System.out.println("3 Buscar Livro por id");
            System.out.println("4 Cadastrar Usuário");
            System.out.println("5 Listar Usuários");
            System.out.println("6 Realizar Empréstimo");
            System.out.println("7 Devolver Livro");
            System.out.println("8 Renovar Empréstimo");
            System.out.println("9 Histórico do Usuário");
            System.out.println("10 Reservar Livro");
            System.out.println("0 Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao){
                case 1: {
                    cadastrarLivro();
                    break;
                }
                case 2: {
                    listarLivros();
                    break;
                }
                case 3: {
                    buscarLivroPorId();
                    break;
                }
                case 4: {
                    cadastrarUsuario();
                    break;
                }
                case 5: {
                    listarUsuarios();
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    break;
                }
                case 9: {
                    break;
                }
                case 10: {
                    break;
                }
                case 0: {
                    System.out.println("Saindo...");
                    break;
                }
                default:{
                    System.out.println("Opção Inválida.");
                }
            }

        }while(opcao!=0);
    }


    private void cadastrarLivro(){
        System.out.println("=======Cadastrar Livro=======");
        System.out.println("Titulo:");
        String titulo = sc.nextLine();

        System.out.println("Isbn:");
        String isbn = sc.nextLine();

        System.out.println("idAutor: ");
        int idAutor = Integer.parseInt(sc.nextLine());

        System.out.println("Categorias");

        System.out.println("1.ROMANCE");
        System.out.println("2.FICCAO");
        System.out.println("3.TERROR");
        System.out.println("4.BIOGRAFIA");
        System.out.println("5.PROGRAMACAO");
        System.out.println("6.HISTORIA");

        System.out.print("Escolha a categoria:");
        int categoria = Integer.parseInt(sc.nextLine());

        CategoriaLivro categoriaLivro = null;

        switch (categoria){
            case 1: {
                categoriaLivro = CategoriaLivro.ROMANCE;
                break;
            }case 2: {
                categoriaLivro = CategoriaLivro.FICCAO;
                break;
            }case 3: {
                categoriaLivro = CategoriaLivro.TERROR;
                break;
            }case 4: {
                categoriaLivro = CategoriaLivro.BIOGRAFIA;
                break;
            }case 5: {
                categoriaLivro = CategoriaLivro.PROGRAMACAO;
                break;
            }case 6: {
                categoriaLivro = CategoriaLivro.HISTORIA;
                break;
            }default:{
                System.out.println("Opção invalida.");
            }
        }

        System.out.print("Ano publicação: ");
        int anoPublicacao = Integer.parseInt(sc.nextLine());

        System.out.print("Quantidade de exemplares: ");
        int quantidadeDeExemplares = Integer.parseInt(sc.nextLine());

        try{
            Livro livro = sistemaBiblioteca.getLivroService().cadastrarLivro(titulo, isbn, idAutor, categoriaLivro, anoPublicacao, quantidadeDeExemplares);
            System.out.println("Livro cadastrado com sucesso!");
            System.out.println(livro);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarLivros(){
        List<Livro> livros = sistemaBiblioteca.getLivroService().getLivros();

        if(livros.isEmpty()){
            System.out.println("Lista vazia!");
        }else{
            for(Livro livro : livros){
                System.out.println(livro);
            }
        }
    }

    private void buscarLivroPorId(){
        System.out.println("=======Buscar Livro por Id=======");
        System.out.print("Id do livro: ");
        int id = Integer.parseInt(sc.nextLine());

        Livro livro = sistemaBiblioteca.getLivroService().buscarLivroPorId(id);
        System.out.println(livro != null ? livro : "Livro não encontrado!");
    }

    private void cadastrarUsuario(){
        System.out.println("=======Cadastrar Usuario=======");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

       try{
           Usuario usuario = sistemaBiblioteca.getUsuarioService().cadastrarUsuario(nome, cpf, telefone, email);
           System.out.println("Usuario cadastrado com sucesso!");
           System.out.println(usuario);
       }catch (IllegalArgumentException e){
           System.out.println("Erro: " + e.getMessage());
       }
    }

    private void listarUsuarios(){
       List<Usuario> usuarios = sistemaBiblioteca.getUsuarioService().listarUsuarios();

       if(usuarios.isEmpty()){
           System.out.println("Lista vazia!");
       }else {
           for(Usuario usuario : usuarios){
               System.out.println(usuario);
           }
       }
    }
}
