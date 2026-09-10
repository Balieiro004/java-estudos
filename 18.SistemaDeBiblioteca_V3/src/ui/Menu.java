package ui;

import entities.Livro;
import system.SistemaDeBibliotevaV3;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaDeBibliotevaV3 sistemaDeBibliotevaV3;

    public Menu(SistemaDeBibliotevaV3 sistemaDeBibliotevaV3, Scanner sc){
        this.sistemaDeBibliotevaV3 = sistemaDeBibliotevaV3;
        this.sc = sc;
    }

    public void iniciar(){

        boolean executando = true;
        while(executando){
            System.out.println("=======MENU PRINCIPAL=======");
            System.out.println("1.Cadastrar Livro");
            System.out.println("2.Listar Livros");
            System.out.println("3.Buscar livro por id");
            System.out.println("4.Buscar livro por titulo");

            System.out.println("5.Cadstrar usuario");
            System.out.println("6.Listar usuarios");

            System.out.println("0.Sair");

            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            switch (opcao){
                case 1:{
                    cadastrarLivro();
                    break;
                }
                case 2:{
                    listarLivros();
                    break;
                }
                case 3:{
                    buscarLivroPorId();
                    break;
                }
                case 4:{
                    buscarLivroPorTitulo();
                    break;
                }
                case 5:{
                    cadastrarUsuario();
                    break;
                }
                case 6:{
                    listarUsuarios();
                    break;
                }
                case 0:{
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                }
                default:{
                    System.out.println("Opção inválida!");
                }
            }
        }
    }

    private void cadastrarLivro(){
        System.out.println("=======Cadastrar Livro=======");

        System.out.print("Titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.next();

        System.out.print("Ano publicacao: ");
        int anoPublicacao = Integer.parseInt(sc.next());

        try{
            Livro livro = sistemaDeBibliotevaV3.getLivroService().cadastrarLivro(titulo, autor,isbn, anoPublicacao);
            System.out.println("Livro cadastrado com sucesso!");
            System.out.println(livro);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarLivros(){
        System.out.println("=======Listar Livros=======");

        List<Livro> listaDeLivros = sistemaDeBibliotevaV3.getLivroService().listarLivros();

        if(listaDeLivros.isEmpty()){
            System.out.println("Nenhum livro encontrado!");
        }else {
            listaDeLivros.forEach(System.out::println);
        }
    }
    private void buscarLivroPorId(){
        System.out.println("=======Buscar Livro por Id=======");

        System.out.print("Livro id: ");
        int id = Integer.parseInt(sc.next());

        Optional<Livro> livro = sistemaDeBibliotevaV3.getLivroService().buscarLivroPorId(id);

        livro.ifPresentOrElse(System.out::println, () -> System.out.println("Livro não encontrado."));
    }
    private void buscarLivroPorTitulo(){
        System.out.println("=======Buscar Livro por Titulo=======");

        sc.nextLine();
        System.out.print("Livro titulo: ");
        String titulo = sc.nextLine();

        Optional<Livro> livro = sistemaDeBibliotevaV3.getLivroService().buscarLivroPorTitulo(titulo);

        livro.ifPresentOrElse(System.out::println, () -> System.out.println("Livro não encontrado"));
    }

    private void cadastrarUsuario(){}
    private void listarUsuarios(){}
}
