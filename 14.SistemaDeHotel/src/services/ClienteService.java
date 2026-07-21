package services;

import entities.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    public Cliente cadastrarCliente(String nome, String cpf, String telefone, String email) {

        validarNome(nome);
        validacaoCpf(cpf);
        validarTelefone(telefone);
        validarEmail(email);
        if(buscarClientePorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");
        }

        Cliente cliente = new Cliente(nome, cpf, telefone, email);
        clientes.add(cliente);
        return cliente;
    }

    private void validarNome(String nome) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    }

    private void validacaoCpf(String cpf) {

        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("Cpf é obrigatório.");
        }
        if(cpf.length() != 11){
            throw new IllegalArgumentException("Cpf precisa de 11 numeros");
        }
    }

    private void validarTelefone(String telefone) {
        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
    }

    private void validarEmail(String email) {
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email é obrigatório.");
        }
    }

    private Cliente buscarClientePorCpf(String cpf){
        for (Cliente cliente : clientes) {
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }
}
