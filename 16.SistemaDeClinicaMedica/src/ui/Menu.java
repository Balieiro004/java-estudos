package ui;

import entities.Paciente;
import system.SistemaClinicaMedica;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;
    private SistemaClinicaMedica sistemaClinicaMedica;
    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
            sistemaClinicaMedica.getPacienteService().ecluirPaciente(idPaciente);
            System.out.println("Paciente excluido com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " +  e.getMessage());
        }
    }

    private int lerIdPaciente() {
        System.out.print("Id: ");
        return Integer.parseInt(sc.nextLine());
    }
}
