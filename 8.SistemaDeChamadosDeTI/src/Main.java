import entities.Chamado;
import entities.Colaborador;
import entities.SistemaChamados;
import entities.Tecnico;
import entities.enuns.Especialidade;
import entities.enuns.StatusChamado;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        SistemaChamados sistema = new SistemaChamados();

        sistema.cadastrarColaborador("João", "joao@email.com");
        sistema.cadastrarColaborador("Maria", "Maria@email.com");
        sistema.cadastrarTecnico("Carol", "Carol@email.com", Especialidade.INFRAESTRUTURA);
        sistema.cadastrarTecnico("Anderson", "Anderson@email.com", Especialidade.SISTEMAS);

        Colaborador colaboradorBusca = sistema.buscarColaboradorPorId(1);
        Tecnico tecnicoBusca =  sistema.buscarTecnicoPorId(3);
        sistema.cadastrarChamado("Teste", "Teste para Abertura",colaboradorBusca, tecnicoBusca);
        sistema.cadastrarChamado("Teste", "Teste para Abertura",colaboradorBusca, tecnicoBusca);
        sistema.cadastrarChamado("Teste", "Teste para Abertura",colaboradorBusca, tecnicoBusca);
        sistema.cadastrarChamado("Teste", "Teste para Abertura",colaboradorBusca, tecnicoBusca);

        boolean executando = true;

        while (executando) {
            int opcao;

            System.out.println("1.Cadastrar Chamado");
            System.out.println("2.Cadastrar Colaborador");
            System.out.println("3.Cadastrar Tecnico");
            System.out.println("4.Listasr Chamados");
            System.out.println("5.Listasr Colaborador");
            System.out.println("6.Listasr Tecnico");
            System.out.println("7.Buscar Colaborador por Id");
            System.out.println("8.Buscar Tecnico por Id");
            System.out.println("9.Buscar Chamado por Id");
            System.out.println("10.Deletar chamado por Id");
            System.out.println("0.Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: {
                    System.out.println("Cadastrar Chamado");
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descricao: ");
                    String descricao = sc.nextLine();
                    sc.nextLine();
                    System.out.print("Solicitante ID: ");
                    int solicitanteId = sc.nextInt();
                    sc.nextLine();
                    Colaborador colaborador = sistema.buscarColaboradorPorId(solicitanteId);
                    System.out.print("Tecnico ID: ");
                    int tecnicoId = sc.nextInt();
                    Tecnico tecnico = sistema.buscarTecnicoPorId(tecnicoId);

                    Chamado chamadoAberto = sistema.cadastrarChamado(titulo, descricao, colaborador, tecnico);
                    System.out.println(chamadoAberto != null ? "Chamado Aberto" : "Chamado não foi aberto verifique os campos");
                    break;
                }
                case 2: {
                    System.out.println("Cadastrar Colaborador");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.println(sistema.cadastrarColaborador(nome, email));
                    break;
                }
                case 3: {
                    System.out.println("Cadastrar Tecnico");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Especialidade: ");
                    Especialidade especialidade = Especialidade.valueOf(sc.nextLine());

                    System.out.println(sistema.cadastrarTecnico(nome, email, especialidade));
                    break;
                }
                case 4: {
                    System.out.println("Listar Chamados");
                    List<Chamado> listaChamado = sistema.getChamados();

                    for (Chamado chamadoImprimir : listaChamado) {
                        System.out.println(chamadoImprimir);
                    }
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 5: {
                    System.out.println("Lista de Colaboradores");
                    List<Colaborador> listaColaborador = sistema.getColaboradores();
                    for (Colaborador colaborador : listaColaborador) {
                        System.out.println(colaborador);
                    }
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 6: {
                    System.out.println("Listar Tecnicos");
                    List<Tecnico> listaTecnico = sistema.getTecnicos();
                    for (Tecnico tecnico : listaTecnico) {
                        System.out.println(tecnico);
                    }
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 7: {
                    System.out.println("Buscar Colaborador por Id");
                    System.out.print("Id do Colaborador: ");
                    int idColaborador = sc.nextInt();

                    Colaborador colaborador = sistema.buscarColaboradorPorId(idColaborador);
                    System.out.println(colaborador != null ? colaborador : "Não encontrou o colaborador");

                    sc.nextLine();
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 8:{
                    System.out.println("Buscar Tecnico por Id");
                    System.out.print("Id do Tecnico: ");
                    int idTecnico = sc.nextInt();

                    Tecnico tecnico = sistema.buscarTecnicoPorId(idTecnico);
                    System.out.println(tecnico != null ? tecnico : "Não encontrou o tecnico");
                    sc.nextLine();

                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                    }
                case 9: {
                    System.out.println("Buscar Chamado por Id");
                    System.out.print("Id do Chamado: ");
                    int idChamado = sc.nextInt();

                    Chamado Chamado = sistema.buscarChamadoPorId(idChamado);
                    System.out.println(Chamado != null ? Chamado : "Chamado não encontrado");

                    sc.nextLine();
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 10: {
                    System.out.println("Deletar chamado por Id");
                    System.out.print("Id do Chamado: ");
                    int idChamado = sc.nextInt();

                    System.out.println(sistema.removerChamadoPorId(idChamado));

                    sc.nextLine();
                    System.out.println("========================\nDe um ENTER para continuar.");
                    sc.nextLine();
                    break;
                }
                case 0: {
                    executando = false;
                    break;
                }
                default:
                    System.out.println("Opção Invalida");
            }
        }
        sc.close();
    }
}