package ui;

import entities.Cliente;
import entities.Pedido;
import entities.Produto;
import system.SistemaPedidos;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private SistemaPedidos sistemaPedidos;
    private Scanner sc;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(SistemaPedidos sistemaPedidos, Scanner sc) {
        this.sistemaPedidos = sistemaPedidos;
        this.sc = sc;
    }

    public void iniciar(){

        int opcao;
        do {
            System.out.println("===============MENU===============");

            System.out.println("1.Cadastrar Cliente");
            System.out.println("2.Listar Cliente");
            System.out.println("3.Buscar Cliente por Id");
            System.out.println("4.Excluir Cliente por Id");
            System.out.println("5.Cadastrar Produto");
            System.out.println("6.Listar Produto");
            System.out.println("7.Buscar Produto por Id");
            System.out.println("8.Excluir Produto por Id");
            System.out.println("9.Criar Pedido");
            System.out.println("10.buscar Pedido Por Id");
            System.out.println("11.adicionar Item ao Pedido");
            System.out.println("12.Listar Pedidos");
            System.out.println("13.finalizar Pedido");
            System.out.println("14.Cancelar Pedido");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarClientePorId();
                    break;
                case 4:
                    deletarClientePorId();
                    break;
                case 5:
                    cadastrarProduto();
                    break;
                case 6:
                    listarProdutos();
                    break;
                case 7:
                    buscarProdutoPorId();
                    break;
                case 8:
                    deletarProdutoPorId();
                    break;
                case 9:
                    criarPedido();
                    break;
                case 10:
                    buscarPedidoPorId();
                    break;
                case 11:
                    adicionarItem();
                    break;
                case 12:
                    listarPedidos();
                    break;
                case 13:
                    finalizarPedido();
                    break;
                case 14:
                    cancelarPedido();
                    break;
                case 0:
                    System.out.println("Saindo.....");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.println("========Cadastrar Cliente========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Cpf: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        try{
            Cliente cliente = sistemaPedidos.getClienteService().cadastrarCliente(nome, cpf, telefone);
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println(cliente);
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarClientes(){

        List<Cliente> clientes = sistemaPedidos.getClienteService().listarClientes();

        if(clientes.isEmpty()){
            System.out.println("Lista vazia.");
        }else{
            for(Cliente cliente:clientes){
                System.out.println(cliente);
            }
        }
    }

    private void buscarClientePorId(){
        System.out.println("========Buscar Cliente por Id========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        Cliente cliente = sistemaPedidos.getClienteService().buscarClientePorId(id);

        if(cliente == null){
            System.out.println("Cliente não encontrado!");
        }else {
            System.out.println(cliente);
        }
    }
    private void deletarClientePorId(){
        System.out.println("========Deletar Cliente========");
        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            sistemaPedidos.getClienteService().deletarClientePorId(id);
            System.out.println("Cliente deletado com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarProduto(){
        System.out.println("========Cadastrar Produto========");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preco: ");
        double preco = Double.parseDouble(sc.nextLine());

        System.out.print("Estoque: ");
        int estoque = Integer.parseInt(sc.nextLine());

        try{
            Produto produto = sistemaPedidos.getProdutoService().cadastrarProduto(nome, preco, estoque);
            System.out.println("Produto cadastrado com sucesso!");
            System.out.println(produto);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarProdutos(){
        List<Produto> produtos = sistemaPedidos.getProdutoService().listarProdutos();

        if(produtos.isEmpty()){
            System.out.println("Lista vazia.");
        }else{
            for(Produto produto:produtos){
                System.out.println(produto);
            }
        }
    }

    private void buscarProdutoPorId(){
        System.out.println("========Buscar Produto por Id========");
        int idPedido = lerIdPedido();

        Produto produto = sistemaPedidos.getProdutoService().buscarProdutoPorId(idPedido);

        if(produto == null){
            System.out.println("Produto não encontrado!");
        }else{
            System.out.println(produto);
        }
    }

    private void deletarProdutoPorId(){
        System.out.println("========Excluir Produto por Id========");
        int idPedido = lerIdPedido();

        try{
            sistemaPedidos.getProdutoService().deletarProdutoPorId(idPedido);
            System.out.println("Produto excluido com sucesso!");
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void criarPedido(){
        System.out.println("========Criar Pedido========");
        System.out.print("Id Cliene: ");
        int id = Integer.parseInt(sc.nextLine());

        try{
            Pedido pedido = sistemaPedidos.getPedidoService().criarPedido(id);
            System.out.println("Pedido criado com sucesso!");
            System.out.println(pedido);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscarPedidoPorId(){
        System.out.println("========Buscar Pedido por Id========");

        int idPedido = lerIdPedido();

        Pedido pedido = sistemaPedidos.getPedidoService().buscarPedidoPorId(idPedido);
        if(pedido == null){
            System.out.println("Pedido não encontrado!");
        }else{
            System.out.println(pedido);
        }
    }

    private void adicionarItem(){
        System.out.println("========Adicionar Item========");
        int idPedido = lerIdPedido();

        System.out.print("Id Produto: ");
        int idProduto = Integer.parseInt(sc.nextLine());

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(sc.nextLine());

        try{
            sistemaPedidos.getPedidoService().adicionarItem(idPedido, idProduto, quantidade);
            System.out.println("Item adicionado com sucesso!");
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPedidos(){
        List<Pedido> pedidos = sistemaPedidos.getPedidoService().listarPedidos();

        if(pedidos.isEmpty()){
            System.out.println("Lista vazia.");
        }else {
            for(Pedido pedido:pedidos){
                System.out.println(pedido);
            }
        }
    }

    private void finalizarPedido(){
        System.out.println("========Finalizar Pedido========");
        int idPedido = lerIdPedido();

        try{
            Pedido pedido = sistemaPedidos.getPedidoService().finalizarPedido(idPedido);

            System.out.println("Pedido finalizado com sucesso!");
            System.out.println("Total: R$ " + pedido.calcularTotal());

        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cancelarPedido(){
        System.out.println("========Cancelar Pedido========");
        int idPedido = lerIdPedido();

        try{
            Pedido pedido = sistemaPedidos.getPedidoService().cancelarPedido(idPedido);

            System.out.println("Pedido cancelado com sucesso!");
            System.out.println(pedido);
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private int lerIdPedido() {
        System.out.print("Id do Pedido: ");
        return Integer.parseInt(sc.nextLine());
    }
}
