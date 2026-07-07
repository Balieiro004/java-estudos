package ui;

import entities.Cliente;
import entities.Locacao;
import entities.Veiculo;
import enums.CategoriaVeiculo;
import system.SistemaLocadora;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private SistemaLocadora sistema;

    public Menu(SistemaLocadora sistema) {
        this.sistema = sistema;
    }

    public void cadastrarCliente(){
        System.out.println("Cadastrando Cliente");

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Cpf: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        try{
            Cliente cliente = sistema.getClienteService().cadastrarCliente(nome,cpf,telefone);
            System.out.println("Cliente cadastrado com sucesso");
            System.out.println(cliente);

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }catch(Exception e){
            System.out.println("erro: "+e.getMessage());

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }
    }

    public void listarClientes(){
        List<Cliente> clientes = sistema.getClienteService().getClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();
    }

    public void buscarClientePorId(){
        System.out.println("Buscando Cliente por id");
        System.out.print("Id do Cliente: ");
        int id = sc.nextInt();

        Cliente cliente = sistema.getClienteService().buscarClientePorId(id);

        if(cliente != null){
            System.out.println(cliente);
        }else {
            System.out.println("Cliente não encontrado.");
        }

        sc.nextLine();
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();
    }

    public void deletarCliente(){
        System.out.println("Excluir Cliente");
        System.out.print("Id do Cliente: ");
        int id = sc.nextInt();

        try{
            sistema.getClienteService().excluirClientePorId(id);
            System.out.println("Cliente deletado com sucesso!");
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }

        sc.nextLine();
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();

    }

    public void cadastrarVeiculo(){
        System.out.println("Cadastrando Veiculo");

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.println("Valor da diaria: ");
        double valorDiaria = sc.nextDouble();
        System.out.print("Categoria: ");
        String categoria = sc.next();

        CategoriaVeiculo categoriaVeiculo = CategoriaVeiculo.valueOf(categoria);


        try{
            Veiculo veiculo = sistema.getVeiculoService().cadastrarVeiculo(modelo, placa, ano, valorDiaria, categoriaVeiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
            System.out.println(veiculo);

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());

            System.out.println("Tecle ENTER para continuar");
            sc.nextLine();
        }
    }

    public void listarVeiculos(){
        List<Veiculo> listVeiculos = sistema.getVeiculoService().getVeiculos();

        for(Veiculo veiculo : listVeiculos){
            System.out.println(veiculo);
        }
        System.out.println("Tecle ENTER para continuar");
        sc.nextLine();
    }

    public void buscarVeiculoPorPlaca(){
        System.out.println("Buscando Veiculo por placa");
        System.out.print("Placa: ");
        String placa = sc.nextLine();

        Veiculo veiculo = sistema.getVeiculoService().buscarVeiculoPorPlaca(placa);

        if(veiculo != null){
            System.out.println(veiculo);
        }else{
            System.out.println("Placa não encontrada.");
        }
    }

    public void buscarVeiculoPorId(){
        System.out.println("Buscando Veiculo por id");
        System.out.print("Id do Veiculo: ");
        int id = sc.nextInt();

        Veiculo veiculo = sistema.getVeiculoService().buscarVeiculoPorId(id);
        if(veiculo != null){
            System.out.println(veiculo);
        }else{
            System.out.println("Veiculo não encontrado.");
        }
    }

    public void excluirVeiculoPorId(){
        System.out.println("Excluir Veiculo por id");
        System.out.print("Id do Veiculo: ");
        int id = sc.nextInt();

        try{
            sistema.getVeiculoService().excluirVeiculoPorId(id);
            System.out.println("Veiculo excluido com sucesso!");
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void excluirVeiculoPorPlaca(){
        System.out.println("Excluir Veiculo por placa");
        System.out.print("Placa: ");
        String placa = sc.nextLine();

        try{
            sistema.getVeiculoService().excluirVeiculoPorPlaca(placa);
            System.out.println("Veiculo excluido com sucesso!");
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void criarLocacao(){
        System.out.println("Locação de veiculo");
        System.out.print("Id Cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("Id do Veiculo: ");
        int idVeiculo = sc.nextInt();
        sc.nextLine();

        System.out.print("Data Retirada (dd/MM/yyyy): ");
        LocalDate dataRetirada = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Data Devolução (dd/MM/yyyy): ");
        LocalDate dataDevolucao = LocalDate.parse(sc.nextLine(), formatter);


        try{
            Locacao locacao = sistema.getLocacaoService().criarLocacao(idCliente, idVeiculo, dataRetirada, dataDevolucao);
            System.out.println("Locacao criada com sucesso!");
            System.out.println(locacao);
        }catch(IllegalArgumentException e){
            System.out.println("erro: " + e.getMessage());
        }
    }
}
