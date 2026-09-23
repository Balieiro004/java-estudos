package ui;

import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;
import system.SistemaDeBibliotevaV3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaDeBibliotevaV3 sistemaDeBibliotevaV3;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

            System.out.println("5.Cadastrar usuario");
            System.out.println("6.Listar usuarios");
            System.out.println("7.Buscar usuario por id");

            System.out.println("8.Cadastrar Emprestimo");
            System.out.println("9.Listar Emprestimo");
            System.out.println("10.Buscar Emprestimo por id");
            System.out.println("11.Devolver Emprestimo");
            System.out.println("12.Listar Emprestimos Ativos");
            System.out.println("13.Listar Emprestimos Atrasados");
            System.out.println("14.Listar Emprestimos Por Usuario");

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
                case 7:{
                    buscarUsuarioPorId();
                    break;
                }
                case 8:{
                    cadastrarEmprestimo();
                    break;
                }
                case 9:{
                    listarEmprestimos();
                    break;
                }
                case 10:{
                    buscarEmprestimoPorId();
                    break;
                }
                case 11:{
                    devolverEmprestimo();
                    break;
                }
                case 12:{
                    listarEmprestimosAtivos();
                    break;
                }
                case 13:{
                    listarEmprestimosAtrasados();
                    break;
                }
                case 14:{
                    listarEmprestimosPorUsuario();
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

    private void cadastrarUsuario(){
        System.out.println("========Cadastrar Usuario=======");

        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();


        try{
            Usuario usuario = sistemaDeBibliotevaV3.getUsuarioService().cadastrarUsuario(nome, cpf, telefone, email);
            System.out.println("Usuario cadastrado com sucesso!");
            System.out.println(usuario);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarUsuarios(){
        System.out.println("=======Lista de Usuarios=======");

        List<Usuario> usuarios = sistemaDeBibliotevaV3.getUsuarioService().listarUsuarios();
        if(usuarios.isEmpty()){
            System.out.println("Nenhum usuario encontrado!");
        }else{
            usuarios.forEach(System.out::println);
        }
    }

    private void buscarUsuarioPorId(){
        System.out.println("=======Buscar Usuario por Id=======");

        System.out.print("Usuario id: ");
        int id = Integer.parseInt(sc.next());

        Optional<Usuario> usuario = sistemaDeBibliotevaV3.getUsuarioService().buscarUsuarioPorId(id);

        usuario.ifPresentOrElse(System.out::println, () -> System.out.println("Usuario não encontrado"));
    }

    private void cadastrarEmprestimo(){
        System.out.println("========Cadastrar Emprestimo=======");

        System.out.print("Id Usuario: ");
        int idUsuario = Integer.parseInt(sc.next());

        System.out.print("Id Livro: ");
        int idLivro = Integer.parseInt(sc.next());

        System.out.print("Data Emprestimo: ");
        LocalDate dataEmprestimo = LocalDate.parse(sc.next(), formatter);

        System.out.print("Data Devolucao: ");
        LocalDate dataDevolucaoPrevista = LocalDate.parse(sc.next(), formatter);

        try{
            Emprestimo emprestimo = sistemaDeBibliotevaV3.getEmprestimoService().cadastrarEmprestimo(idUsuario, idLivro, dataEmprestimo, dataDevolucaoPrevista);
            System.out.println("Emprestimo Cadastrado com sucesso!");
            System.out.println(emprestimo);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    private void listarEmprestimos(){
        System.out.println("=======Listar Emprestimos=======");
        List<Emprestimo> emprestimos = sistemaDeBibliotevaV3.getEmprestimoService().listarEmprestimos();
        if(emprestimos.isEmpty()){
            System.out.println("Nenhum emprestimo encontrado!");
        }else {
            emprestimos.forEach(System.out::println);
        }
    }
    private void buscarEmprestimoPorId(){
        System.out.println("=======Buscar Emprestimo por Id=======");

        System.out.print("Id Emprestimo: ");
        int idEmprestimo = Integer.parseInt(sc.next());

        Optional<Emprestimo> emprestimo = sistemaDeBibliotevaV3.getEmprestimoService().buscarEmprestimoPorId(idEmprestimo);
        emprestimo.ifPresentOrElse(System.out::println, () -> System.out.println("Emprestimo não encontrado"));
    }

    private void devolverEmprestimo(){
        System.out.println("=======Devolver Emprestimo=======");
        System.out.print("Id Emprestimo: ");
        int idEmprestimo = Integer.parseInt(sc.next());


        try{
            sistemaDeBibliotevaV3.getEmprestimoService().devolverEmprestimo(idEmprestimo);
            System.out.println("Emprestimo devolvido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarEmprestimosAtivos(){
        System.out.println("=======Listar Emprestimos Ativos=======");
        List<Emprestimo> emprestimos = sistemaDeBibliotevaV3.getEmprestimoService().listarEmprestimosAtivos();
        if(emprestimos.isEmpty()){
            System.out.println("Nenhum emprestimo encontrado!");
        }else{
            emprestimos.forEach(System.out::println);
        }
    }

    private void listarEmprestimosAtrasados(){
        System.out.println("=======Listar Emprestimos Atrasados=======");
        List<Emprestimo> emprestimos = sistemaDeBibliotevaV3.getEmprestimoService().listarEmprestimosAtrasados();
        if(emprestimos.isEmpty()){
            System.out.println("Nenhum emprestimo encontrado!");
        }else {
            emprestimos.forEach(System.out::println);
        }
    }

    private void listarEmprestimosPorUsuario(){
        System.out.println("=======Listar Emprestimos por Usuario=======");
        System.out.print("Id Usuario: ");
        int idUsuario = Integer.parseInt(sc.next());

        List<Emprestimo> emprestimos = sistemaDeBibliotevaV3.getEmprestimoService().listarEmprestimosPorUsuario(idUsuario);
        if(emprestimos.isEmpty()){
            System.out.println("Nenhum emprestimo encontrado!");
        }else  {
            emprestimos.forEach(System.out::println);
        }
    }

}
