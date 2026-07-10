package services;

import entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        carregarClientesMock();
    }

    public Cliente cadastrarCliente(String nome, String cpf, String telefone){

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone é obrigatório");
        }

        if(buscarClientePorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF");
        }

        Cliente cliente = new Cliente(nome, cpf, telefone);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> buscarClientes() {
        return clientes;
    }

    public Cliente buscarClientePorId(int id){
        for(Cliente cliente : clientes){
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
    private Cliente buscarClientePorCpf(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public void excluirClientePorId(int id){
        Cliente cliente = buscarClientePorId(id);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clientes.remove(cliente);
    }

    public void carregarClientesMock() {
        cadastrarCliente("João Silva", "123.456.789-01", "(11) 99999-1111");
        cadastrarCliente("Maria Oliveira", "234.567.890-12", "(11) 99999-2222");
        cadastrarCliente("Pedro Santos", "345.678.901-23", "(11) 99999-3333");
        cadastrarCliente("Ana Costa", "456.789.012-34", "(11) 99999-4444");
        cadastrarCliente("Lucas Ferreira", "567.890.123-45", "(11) 99999-5555");
        cadastrarCliente("Fernanda Lima", "678.901.234-56", "(11) 99999-6666");
    }
}
