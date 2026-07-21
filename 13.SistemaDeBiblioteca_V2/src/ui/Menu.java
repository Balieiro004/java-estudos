package ui;

import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;
import enums.CategoriaLivro;
import system.SystemaBiblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    SystemaBiblioteca sistemaBiblioteca;
    Scanner sc;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                    criarEmprestimo();
                    break;
                }
                case 7: {
                    devolverLivro();
                    break;
                }
                case 8: {
                    renovarEmprestimo();
                    break;
                }
                case 9: {
                    consultarEmprestimos();
                    break;
                }
                case 10: {
                    //TODO: implementar sistema de reserva.
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

    private void consultarEmprestimos(){
        System.out.println("=======Consultar Emprestimos=======");
        System.out.print("Id do usuario: ");
        int idUsuario = Integer.parseInt(sc.nextLine());

        List<Emprestimo> historico = sistemaBiblioteca.getUsuarioService().consultarEmprestimos(idUsuario);

        if(historico.isEmpty()){
            System.out.println("Lista vazia!");
        }else  {
            for(Emprestimo emprestimo : historico){
                System.out.println(emprestimo);
            }
        }
    }


    private void criarEmprestimo(){
        System.out.println("=======Criar Emprestimo=======");
        System.out.print("Id usuario: ");
        int idUsuario = Integer.parseInt(sc.nextLine());

        System.out.print("Id livro: ");
        int idLivro = Integer.parseInt(sc.nextLine());

        System.out.print("Data de emprestimo: ");
        LocalDate dataEmprestimo = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Data prevista devolucao: ");
        LocalDate dataPrevista = LocalDate.parse(sc.nextLine(), formatter);

        try{
            Emprestimo emprestimo = sistemaBiblioteca.getEmprestimoService().criarEmprestimo(idUsuario, idLivro, dataEmprestimo, dataPrevista);
            System.out.println("Emprestimo Criado com sucesso!");
            System.out.println(emprestimo);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void devolverLivro(){
        System.out.println("=======Devolver Livro=======");
        System.out.print("Id do empréstimo: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Data da devolução (dd/MM/yyyy): ");
        LocalDate dataDevolucao = LocalDate.parse(sc.nextLine(), formatter);

        try{
            Emprestimo emprestimo = sistemaBiblioteca.getEmprestimoService().devolverLivro(id, dataDevolucao);
            System.out.println("Livro devolvido com sucesso!");
            System.out.println(emprestimo);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void renovarEmprestimo(){
        System.out.println("=======Renovar Emprestimo=======");
        System.out.print("Id do emprestimo: ");
        int idEmprestimo = Integer.parseInt(sc.nextLine());

        System.out.print("Nova data para devolução (dd/MM/yyyy): ");
        LocalDate dataEmprestimo = LocalDate.parse(sc.nextLine(), formatter);

        try{
            Emprestimo emprestimo = sistemaBiblioteca.getEmprestimoService().renovarEmprestimo(idEmprestimo, dataEmprestimo);
            System.out.println("Emprestimo renovado com sucesso!");
            System.out.println(emprestimo);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

}
