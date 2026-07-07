package services;

import entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {carregarClientesMock();}

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente cadastrarCliente(String nome,String cpf,String telefone){

        if(nome == null ||  nome.isBlank() ){
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if(cpf == null || cpf.isBlank() ){
            throw new IllegalArgumentException("Cpf é obrigatório.");
        }

        if(telefone == null || telefone.isBlank() ){
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }

        if(buscarClientePorNome(nome) != null){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse nome.");
        }

        if(buscarClientePorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse cpf.");
        }

        Cliente cliente = new Cliente(nome,cpf,telefone);
        clientes.add(cliente);
        return cliente;
    }

    private Cliente buscarClientePorNome(String nome){
        for (Cliente cliente : clientes) {
            if(cliente.getNome().equals(nome)){
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarClientePorId(int id){
        for (Cliente cliente : clientes) {
            if(cliente.getId()==id){
                return cliente;
            }
        }
        return null;
    }

    public void excluirClientePorId(int id){
        Cliente cliente = buscarClientePorId(id);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clientes.remove(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }


    private void carregarClientesMock(){
        cadastrarCliente("João Silva", "111.111.111-11", "(11) 99999-1111");
        cadastrarCliente("Maria Oliveira", "222.222.222-22", "(11) 99999-2222");
        cadastrarCliente("Pedro Santos", "333.333.333-33", "(11) 99999-3333");
        cadastrarCliente("Ana Souza", "444.444.444-44", "(11) 99999-4444");
        cadastrarCliente("Carlos Pereira", "555.555.555-55", "(11) 99999-5555");
        cadastrarCliente("Fernanda Lima", "666.666.666-66", "(11) 99999-6666");
    }

}
