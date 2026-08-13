package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;
import entities.Receita;
import enums.Especialidades;
import system.SistemaClinicaMedica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaClinicaMedica sistemaClinicaMedica;
    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

    public Menu(SistemaClinicaMedica sistemaClinicaMedica, Scanner sc) {
        this.sc = sc;
        this.sistemaClinicaMedica = sistemaClinicaMedica;
    }

    public void iniciar(){

        boolean executando = true;


        while(executando){

            System.out.println("\n========Menu Principal========");
            System.out.println("1.Cadastrar Paciente");
            System.out.println("2.Listar Pacientes");
            System.out.println("3.Buscar Paciente por Id");
            System.out.println("4.Excluir Paciente");
            System.out.println("5.Cadastrar Medico");
            System.out.println("6.Listar Medicos");
            System.out.println("7.Buscar Medico Por Id");
            System.out.println("8.Excluir Medico Por Id");
            System.out.println("9.Criar Consulta");
            System.out.println("10.listar Consultas");
            System.out.println("11.Reagendar Consulta");
            System.out.println("12.Consultar Agenda Medico");
            System.out.println("13.Consultar Agenda Paciente");
            System.out.println("14.Confirmar Consulta");
            System.out.println("15.Cancelar Consulta");
            System.out.println("16.Realizar Consulta");
            System.out.println("17.Emitir Receita");
            System.out.println("0.Sair");

            System.out.print("Opção: ");
            int opcao = Integer.parseInt(sc.nextLine());

            switch(opcao){
                case 1:{
                    cadastrarPaciente();
                    break;
                }
                case 2:{
                    listarPacientes();
                    break;
                }
                case 3:{
                    buscarPacientePorId();
                    break;
                }
                case 4:{
                    ecluirPaciente();
                    break;
                }
                case 5:{
                    cadastrarMedico();
                    break;
                }
                case 6:{
                    listarMedicos();
                    break;
                }
                case 7:{
                    buscarMedicoPorId();
                    break;
                }
                case 8:{
                    excluirMedicoPorId();
                    break;
                }
                case 9:{
                    criarConsulta();
                    break;
                }
                case 10:{
                    listarConsultas();
                    break;
                }
                case 11:{
                    reagendarConsulta();
                    break;
                }
                case 12:{
                    consultarAgendaMedico();
                    break;
                }
                case 13:{
                    consultarAgendaPaciente();
                    break;
                }
                case 14:{
                    confirmarConsulta();
                    break;
                }
                case 15:{
                    cancelarConsulta();
                    break;
                }
                case 16:{
                    realizarConsulta();
                    break;
                }
                case 17:{
                    emitirReceita();
                    break;
                }
                case 0:{
                    executando = false;
                    System.out.println("Saindo....");
                    break;
                }
                default:{
                    System.out.println("Opção invalida.");
                }
            }
        }

    }

    private void cadastrarPaciente(){
        System.out.println("========Cadastrar Paciente========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Convenio: ");
        String convenio = sc.nextLine();

        try{
            Paciente paciente = sistemaClinicaMedica.getPacienteService().cadastrarPaciente(nome,cpf,convenio);
            System.out.println("Paciente cadastrado com sucesso!");
            System.out.println(paciente);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void listarPacientes(){
        System.out.println("========Listar Pacientes========");

        List<Paciente> pacientes = sistemaClinicaMedica.getPacienteService().listarPacientes();

        if(pacientes.isEmpty()){
            System.out.println("Lista de pacientes vazia!");
        }else{
            pacientes.forEach(System.out::println);
        }
    }

    private void buscarPacientePorId(){
        System.out.println("========Buscar Paciente por Id========");

        int idPaciente = lerIdPaciente();

        Paciente paciente = sistemaClinicaMedica.getPacienteService().buscarPacientePorId(idPaciente);

        System.out.println(paciente == null ? "Paciente não encontrado!" : paciente);
    }

    private void ecluirPaciente(){
        System.out.println("========Ecluir Paciente========");

        int idPaciente = lerIdPaciente();

        try{
            sistemaClinicaMedica.getPacienteService().excluirPaciente(idPaciente);
            System.out.println("Paciente excluido com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void cadastrarMedico(){
        System.out.println("========Cadastrar Medico========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.println("Crm: ");
        String crm = sc.nextLine();

        System.out.println("===Especialidade===");
        System.out.println("1.Clinico Geral");
        System.out.println("2.Cardiologia");
        System.out.println("3.Pediatria");
        System.out.println("4.Ortopedia");
        System.out.println("5.Dematologia");
        System.out.println("6.Psiquiatria");
        System.out.println("7.Neurologia");
        System.out.println("8.Ginecologia");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        Especialidades especialidade;

        switch(opcao){
            case 1:{
                especialidade = Especialidades.CLINICO_GERAL;
                break;
            }
            case 2:{
                especialidade = Especialidades.CARDIOLOGIA;
                break;
            }
            case 3:{
                especialidade = Especialidades.PEDIATRIA;
                break;
            }
            case 4:{
                especialidade = Especialidades.ORTOPEDIA;
                break;
            }
            case 5:{
                especialidade = Especialidades.DERMATOLOGIA;
                break;
            }
            case 6:{
                especialidade = Especialidades.PSIQUIATRIA;
                break;
            }
            case 7:{
                especialidade = Especialidades.NEUROLOGIA;
                break;
            }
            case 8:{
                especialidade = Especialidades.GINECOLOGIA;
                break;
            }
            default:
                System.out.println("Tipo de conta inválida");
                return;
        }
        System.out.print("Valor da Consulta: R$ ");
        double valorConsulta = Double.parseDouble(sc.nextLine());

        try{
            Medico medico = sistemaClinicaMedica.getMedicoService().cadastrarMedico(nome,cpf,crm, especialidade, valorConsulta);
            System.out.println("Medico cadastrado com sucesso!");
            System.out.println(medico);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void listarMedicos(){
        System.out.println("========Listar Medicos========");
        List<Medico> medicos = sistemaClinicaMedica.getMedicoService().listarMedicos();

        if(medicos.isEmpty()){
            System.out.println("Nenhum medico encontrado!");
        }else {
            medicos.forEach(System.out::println);
        }
    }

    private void buscarMedicoPorId(){
        System.out.println("========Buscar Medico========");
        int idMedico = lerIdMedico();

        Medico medico = sistemaClinicaMedica.getMedicoService().buscarMedicoPorId(idMedico);

        System.out.println(medico == null ? "Nenhum medico encontrado!" : medico);
    }

    private void excluirMedicoPorId(){
        System.out.println("========Excluir Medico========");
        int idMedico = lerIdMedico();

        try{
            sistemaClinicaMedica.getMedicoService().excluirMedicoPorId(idMedico);
            System.out.println("Medico excluido com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void criarConsulta(){
        System.out.println("========Criar Consulta========");

        System.out.print("Id Paciente: ");
        int idPaciente = Integer.parseInt(sc.nextLine());

        int idMedico = lerIdMedico();

        LocalDate dataConsulta = lerDataConsulta();
        LocalTime horaConsulta = lerHoraConsulta();

        try{
            Consulta consulta = sistemaClinicaMedica.getConsultaService().criarConsulta(idPaciente, idMedico, dataConsulta, horaConsulta);
            System.out.println("Consulta criado com sucesso!");
            System.out.println(consulta);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void listarConsultas(){
        System.out.println("========Listar Consultas========");

        List<Consulta> consultas = sistemaClinicaMedica.getConsultaService().listarConsultas();

        if(consultas.isEmpty()){
            System.out.println("Nenhum consulta encontrada!");
        }else {
            consultas.forEach(System.out::println);
        }
    }

    private void reagendarConsulta(){
        System.out.println("========Reagendar Consulta========");

        int idConsulta = lerIdConsulta();

        LocalDate dataConsulta = lerDataConsulta();
        LocalTime horaConsulta = lerHoraConsulta();

        try{
            Consulta consulta = sistemaClinicaMedica.getConsultaService().reagendarConsulta(idConsulta, dataConsulta, horaConsulta);
            System.out.println("Conculta reagendada com sucesso!");
            System.out.println(consulta);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }

    }

    private void consultarAgendaMedico(){

        System.out.println("========Agenda do Médico========");

        int idMedico = lerIdMedico();
        LocalDate dataConsulta = lerDataConsulta();

        try {
            List<Consulta> consultas = sistemaClinicaMedica.getConsultaService().consultarAgendaMedico(idMedico, dataConsulta);

            if (consultas.isEmpty()) {
                System.out.println("Nenhum consulta encontrada!");
            } else {
                consultas.forEach(System.out::println);
            }
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void consultarAgendaPaciente(){
        System.out.println("========Agenda do Paciente========");
        int idPaciente = lerIdPaciente();

        try{
            List<Consulta> consultas = sistemaClinicaMedica.getConsultaService().consultarAgendaPaciente(idPaciente);

            if (consultas.isEmpty()) {
                System.out.println("Nenhum consulta encontrada!");
            }else  {
                consultas.forEach(System.out::println);
            }
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void confirmarConsulta(){
        System.out.println("========Confirmar Consulta========");
        int idConsulta = lerIdConsulta();

        try{
            Consulta consulta = sistemaClinicaMedica.getConsultaService().confirmarConsulta(idConsulta);
            System.out.println("Consulta confirmar com sucesso!");
            System.out.println(consulta);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void cancelarConsulta(){
        System.out.println("========Cancelar Consulta========");
        int idConsulta = lerIdConsulta();

        try{
            Consulta consulta = sistemaClinicaMedica.getConsultaService().cancelarConsulta(idConsulta);
            System.out.println("Consulta cancelada com sucesso!");
            System.out.println(consulta);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void realizarConsulta(){
        System.out.println("========Realizar Consulta========");
        int idConsulta = lerIdConsulta();

        try {
            Consulta consulta = sistemaClinicaMedica.getConsultaService().realizarConsulta(idConsulta);
            System.out.println("Consulta realizar com sucesso!");
            System.out.println(consulta);
            System.out.printf("Valor da consulta: R$ %.2f%n", consulta.getValor());
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private void emitirReceita(){
        System.out.println("========Emitir Receita========");
        System.out.print("Id da consulta: ");
        int idConsulta = Integer.parseInt(sc.nextLine());

        System.out.print("Receita: ");
        String descricao = sc.nextLine();

        try{
            Receita receita =  sistemaClinicaMedica.getConsultaService().emitirReceita(idConsulta, descricao);
            System.out.println("Receita emitir com sucesso!");
            System.out.println(receita);
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }

    }
    private int lerIdMedico() {
        System.out.print("Id: ");
        return Integer.parseInt(sc.nextLine());
    }

    private int lerIdPaciente() {
        System.out.print("Id: ");
        return Integer.parseInt(sc.nextLine());
    }

    private LocalDate lerDataConsulta() {
        System.out.print("Data Consulta: ");
        return LocalDate.parse(sc.nextLine(), fomatter);
    }

    private LocalTime lerHoraConsulta() {
        System.out.print("Hora Consulta: ");
        return LocalTime.parse(sc.nextLine(), formatterHora);
    }

    private int lerIdConsulta(){
        System.out.print("Id Consulta: ");
        return Integer.parseInt(sc.nextLine());
    }
}
