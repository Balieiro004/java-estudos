import entities.Colaborador;
import entities.Equipamento;
import entities.Estoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Estoque estoque = new Estoque();

        estoque.cadastrarColaborador("Anderson","anderson@email.com");
        estoque.cadastrarColaborador("Balieiro", "balieiro@email.com");

        for (int i = 0; i < 5; i++) {

            System.out.println(estoque.cadastrarEquipamento("serviceTag", "001" + i, "4SRTRB" + i));
        }

        boolean executando = true;
        while(executando){
            int opcao;

            System.out.println("1-Cadastrar Colaborador");
            System.out.println("2-Cadastrar Equipamento");
            System.out.println("3-Listar Colaboradores");
            System.out.println("4-Listar Equipamentos");
            System.out.println("5-Emprestar Equipamento");
            System.out.println("6-Devolver Equipamento");
            System.out.println("7-Listar equipamentos Emprestados");
            System.out.println("8-Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Cadastrar Colaborador");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("E-mail: ");
                    String email = sc.nextLine();
                    System.out.println(estoque.cadastrarColaborador(nome,email));
                    break;
                case 2:
                    System.out.println("Cadastrar Equipamento");
                    System.out.print("Nome: ");
                    nome = sc.nextLine();
                    System.out.print("Patrimonio: ");
                    String patrimonio = sc.nextLine();
                    System.out.print("Service tag: ");
                    String serviceTag = sc.nextLine();
                    System.out.println(estoque.cadastrarEquipamento(nome, patrimonio, serviceTag));
                    break;
                case 3:
                    var listaColaboradores = estoque.getColaboradores();

                    if(listaColaboradores.isEmpty()){
                        System.out.println("Nenhuma colaborador encontrado!");
                    }else {
                        for(Colaborador colaborador : listaColaboradores){
                            System.out.println(colaborador);
                            System.out.println("*****************************");
                        }
                    }
                    break;
                case 4:
                    var listaEquipamentos = estoque.getEquipamentos();

                    if(listaEquipamentos.isEmpty()){
                        System.out.println("Nenhuma equipamento encontrado!");
                    }else {
                        for(Equipamento equipamento1 : listaEquipamentos){
                            System.out.println(equipamento1);
                            System.out.println("*****************************");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Emprestar Equipamento");
                    int[] dadosEmprestimo = lerDadosEmprestimo(sc);

                    System.out.println(estoque.emprestarEquipamento(dadosEmprestimo[0], dadosEmprestimo[1]));
                    break;
                case 6:
                    System.out.println("Devolver Equipamento");

                    int[] dadosDevoluvao = lerDadosEmprestimo(sc);

                    System.out.println(estoque.devolverEquipamento(dadosDevoluvao[0], dadosDevoluvao[1]));
                    break;
                case 7:
                    System.out.println("Listar Equipamentos Emprestados");

                    List<Equipamento> listaEquipamentosEmprestados = estoque.listarEquipamentosEmprestados();

                    if(listaEquipamentosEmprestados.isEmpty()){
                        System.out.println("Nenhuma equipamento emprestado!");
                    }else {
                        for(Equipamento equipamento1 : listaEquipamentosEmprestados){
                            System.out.println(equipamento1);
                            System.out.println("*****************************");
                        }
                    }
                    break;
                case 8:
                    System.out.println("Saindo....");
                    executando = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        sc.close();
    }

    private static int[] lerDadosEmprestimo(Scanner sc){
        System.out.print("Id Colaborador: ");
        int idColaborador = sc.nextInt();

        System.out.print("Id Equipamento: ");
        int idEquipamento = sc.nextInt();

        sc.nextLine();

        return new int[]{idColaborador, idEquipamento};
    }
}