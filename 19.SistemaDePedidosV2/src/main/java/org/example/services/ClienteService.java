package org.example.services;

import org.example.entities.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrar(String nome, String email) {

        if(buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse email");
        }
        Cliente cliente = new Cliente(nome, email);
        clientes.add(cliente);
    }

    public List<Cliente> listar() {

        return Collections.unmodifiableList(clientes);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public void removerClientePorEmail(String email) {
        Optional<Cliente> cliente = buscarPorEmail(email);

        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clientes.remove(cliente.get());
    }
}
