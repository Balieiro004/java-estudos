package services;

import entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {carregarClientesMock();}

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String telefone) {

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("Cpf é obrigatório.");
        }

        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
        if(buscarClientePorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");
        }


        Cliente cliente = new Cliente(nome, cpf, telefone);
        clientes.add(cliente);
        return cliente;
    }

    public void deletarClientePorId(int id){
        Cliente cliente = buscarClientePorId(id);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clientes.remove(cliente);
    }

    private Cliente buscarClientePorCpf(String cpf) {
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClientePorId(int id){
        for(Cliente cliente : clientes){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }

    public void carregarClientesMock() {
        cadastrarCliente("João Silva", "11111111111", "11999990001");
        cadastrarCliente("Maria Oliveira", "22222222222", "11999990002");
        cadastrarCliente("Pedro Santos", "33333333333", "11999990003");
        cadastrarCliente("Ana Costa", "44444444444", "11999990004");
        cadastrarCliente("Carlos Pereira", "55555555555", "11999990005");
        cadastrarCliente("Fernanda Lima", "66666666666", "11999990006");
    }
}
