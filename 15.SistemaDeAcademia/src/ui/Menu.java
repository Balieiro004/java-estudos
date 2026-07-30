package ui;

import entities.Aluno;
import entities.Matricula;
import entities.Pagamento;
import enums.FormaPagamento;
import system.SistemaAcademia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaAcademia sistemaAcademia;
    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaAcademia sistemaAcademia, Scanner sc) {
        this.sistemaAcademia = sistemaAcademia;
        this.sc = sc;
    }

    public void iniciar(){

        int opcao = 0;
        do {
            System.out.println("========Iniciando Menu========");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Buscar Aluno por Id");
            System.out.println("4. Excluir Aluno por Id");
            System.out.println("5. Criar Matricula");
            System.out.println("6. Listar Matriculas");
            System.out.println("7. Buscar Matricula Por Id");
            System.out.println("8. Cancelar Matricula Por Id");
            System.out.println("9. Suspender Matricula Por Id");
            System.out.println("10. Reativar Matricula Por Id");
            System.out.println("11. Encerrar Matricula Por Id");
            System.out.println("12. Registrar Pagamento");
            System.out.println("13. Listar Pagamentos");
            System.out.println("14. Buscar Pagamento por Id");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:{
                    cadastrarAluno();
                    break;
                }
                case 2:{
                    listarAlunos();
                    break;
                }
                case 3:{
                    buscarAlunoPorId();
                    break;
                }
                case 4:{
                    excluirAlunoPorId();
                    break;
                }
                case 5:{
                    criarMatricula();
                    break;
                }
                case 6:{
                    listarMatriculas();
                    break;
                }
                case 7:{
                    buscarMatriculaPorId();
                    break;
                }
                case 8:{
                    cancelarMatriculaPorId();
                    break;
                }
                case 9:{
                    suspenderMatriculaPorId();
                    break;
                }
                case 10:{
                    reativarMatriculaPorId();
                    break;
                }
                case 11:{
                    encerrarMatriculaPorId();
                    break;
                }
                case 12:{
                    registrarPagamento();
                    break;
                }
                case 13:{
                    listPagamentos();
                    break;
                }
                case 14:{
                    buscarPagamentoPorId();
                }
                case 0:{
                    System.out.println("Saindo.....");
                    break;
                }
                default:{
                    System.out.println("Opção inválida.");
                }
            }
        }while (opcao != 0);
    }

    private void cadastrarAluno(){

        System.out.println("========Cadastro de Aluno========");
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.println("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        try{
            Aluno aluno = sistemaAcademia.getAlunoService().cadastrarAluno(nome,cpf,telefone,email);
            System.out.println("Aluno Cadastrado com sucesso!");
            System.out.println(aluno);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarAlunos(){
        List<Aluno> alunos = sistemaAcademia.getAlunoService().listarAlunos();

        if(alunos.isEmpty()){
            System.out.println("Nenhum Aluno encontrado!");
        }else {
            alunos.forEach(System.out::println);
        }
    }

    private void buscarAlunoPorId(){
        System.out.println("========Buscar Aluno por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Aluno aluno = sistemaAcademia.getAlunoService().buscarAlunoPorId(id);
        if(aluno == null){
            System.out.println("Nenhum Aluno encontrado!");
        }else  {
            System.out.println(aluno);
        }
    }

    private void excluirAlunoPorId(){
        System.out.println("========Excluir Aluno por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            sistemaAcademia.getAlunoService().excluirAlunoPorId(id);
            System.out.println("Aluno Excluido com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void criarMatricula(){
        System.out.println("========Criar Matricula========");
        System.out.print("Id Aluno: ");
        int idAluno = Integer.parseInt(sc.nextLine());

        System.out.println("Plano: ");
        System.out.println("1.Mensal");
        System.out.println("2.Trimestral");
        System.out.println("3.Semestral");
        System.out.println("4.Anual");
        System.out.print("Opção: ");
        int IdMatricula = Integer.parseInt(sc.nextLine());

        System.out.print("Data inicio: ");
        LocalDate dataInicio = LocalDate.parse(sc.nextLine(), fomatter);

        try{
            Matricula matricula = sistemaAcademia.getMatriculaService().criarMatricula(idAluno, IdMatricula, dataInicio);
            System.out.println("Matricula Criada com sucesso!");
            System.out.println(matricula);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarMatriculas(){
        List<Matricula> matriculas = sistemaAcademia.getMatriculaService().getMatriculas();

        if(matriculas.isEmpty()){
            System.out.println("Nenhum Matricula encontrada!");
        }else {
            matriculas.forEach(System.out::println);
        }
    }

    private void buscarMatriculaPorId(){
        System.out.println("========Buscar Matricula por Id========");

        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        Matricula matricula = sistemaAcademia.getMatriculaService().buscarMatriculaPorId(idMatricula);
        if(matricula == null){
            System.out.println("Nenhum Matricula encontrada!");
        }else  {
            System.out.println(matricula);
        }
    }

    private void cancelarMatriculaPorId(){
        System.out.println("========Cancelar Matricula por Id========");

        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        try{
            sistemaAcademia.getMatriculaService().cancelarMatriculaPorId(idMatricula);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void suspenderMatriculaPorId(){
        System.out.println("========Suspender Matricula========");
        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        try{
            sistemaAcademia.getMatriculaService().suspenderMatriculaPorId(idMatricula);
            System.out.println("Matricula Suspendida com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void reativarMatriculaPorId(){
        System.out.println("========Reativar Matricula========");
        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        try {
            sistemaAcademia.getMatriculaService().reativarMatriculaPorId(idMatricula);
            System.out.println("Matricula Reativa com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void encerrarMatriculaPorId(){
        System.out.println("========Encerrar Matricula========");
        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        try{
            sistemaAcademia.getMatriculaService().encerrarMatriculaPorId(idMatricula);
            System.out.println("Matricula encerrada com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void registrarPagamento(){
        System.out.println("========Registrar Pagamento========");

        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        System.out.print("Data do Pagamento: ");
        LocalDate dataPagamento = LocalDate.parse(sc.nextLine(), fomatter);
        FormaPagamento formaPagamento = null;

        System.out.println("Escola a Forma de Pagamento: ");
        System.out.println("1. Pix");
        System.out.println("2. Cartão");
        System.out.println("3. Dinheiro");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1:{
                formaPagamento = FormaPagamento.PIX;
                break;
            }
            case 2:{
                formaPagamento = FormaPagamento.CARTAO;
                break;
            }
            case 3:{
                formaPagamento = FormaPagamento.DINHEIRO;
                break;
            }
        }

        System.out.print("Valor pago R$: ");
        double valorPagamento = Double.parseDouble(sc.nextLine());

        try{
            Pagamento pagamento = sistemaAcademia.getPagamentoService().registrarPagamento(idMatricula, dataPagamento, valorPagamento, formaPagamento);
            System.out.println("Pagamento registrado com sucesso!");
            System.out.println(pagamento);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listPagamentos(){
        System.out.println("========Listar Pagamentos========");
        List<Pagamento> listaPagamentos = sistemaAcademia.getPagamentoService().listPagamentos();

        if(listaPagamentos.isEmpty()){
            System.out.println("Nenhum pagamento foi encontrado!");
        }else {
            listaPagamentos.forEach(System.out::println);
        }
    }

    private void buscarPagamentoPorId(){
        System.out.println("========Buscar Pagamento========");
        System.out.print("Id matricula: ");
        int idMatricula = Integer.parseInt(sc.nextLine());

        Pagamento pagamento = sistemaAcademia.getPagamentoService().buscarPagamentoPorId(idMatricula);

        if(pagamento == null){
            System.out.println("Pagamento não encontrado!");
        }else {
            System.out.println(pagamento);
        }
    }
}
