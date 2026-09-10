package services;

import entities.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        carregarUsuariosMock();
    }

    public List<Usuario> listarUsuarios() {return Collections.unmodifiableList(usuarios);}

    public Usuario cadastrarUsuario(String nome, String cpf, String telefone, String email){

        validarNome(nome);
        validarCPF(cpf);
        validarTelefone(telefone);
        validarEmail(email);

        Usuario usuario = new Usuario(nome, cpf, telefone, email);
        usuarios.add(usuario);
        return usuario;
    }

    private void validarNome(String nome){
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome precisa ser preenchido.");
        }

        if(nome.length()<3){
            throw new IllegalArgumentException("Nome precisa ter pelo menos 3 caracteres.");
        }
    }

    public Optional<Usuario> buscarUsuarioPorCpf(String cpf){
        return usuarios.stream().filter(usuario -> usuario.getCpf().equalsIgnoreCase(cpf)).findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email){
        return usuarios.stream().filter(usuario -> usuario.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorId(int id){
        return usuarios.stream().filter(usuario -> usuario.getId() == id).findFirst();
    }

    private void validarCPF(String cpf){
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF precisa ser preenchido.");
        }
        if(cpf.length() != 11){
            throw new IllegalArgumentException("CPF precisa ter 11 caracteres.");
        }

        if(buscarUsuarioPorCpf(cpf).isPresent()){
            throw new IllegalArgumentException("Já existe um usuário com esse CPF.");
        }
    }
    private void validarTelefone(String telefone){
        if(telefone == null || telefone.isEmpty()){
            throw new IllegalArgumentException("Telefone precisa ser preenchido.");
        }

        if (!telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException(
                    "Telefone deve conter apenas números e ter 10 ou 11 dígitos.");
        }
    }
    private void validarEmail(String email){
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email precisa ser preenchido.");
        }

        int posicaoArroba = email.indexOf("@");

        if (posicaoArroba <= 0 || email.indexOf(".", posicaoArroba) == -1) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (buscarUsuarioPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse email.");
        }
    }


    public void carregarUsuariosMock() {

        cadastrarUsuario(
                "João Silva",
                "12345678901",
                "11999990001",
                "joao.silva@email.com"
        );

        cadastrarUsuario(
                "Maria Oliveira",
                "23456789012",
                "11999990002",
                "maria.oliveira@email.com"
        );

        cadastrarUsuario(
                "Pedro Santos",
                "34567890123",
                "11999990003",
                "pedro.santos@email.com"
        );

        cadastrarUsuario(
                "Ana Costa",
                "45678901234",
                "11999990004",
                "ana.costa@email.com"
        );

        cadastrarUsuario(
                "Carlos Pereira",
                "56789012345",
                "11999990005",
                "carlos.pereira@email.com"
        );

        cadastrarUsuario(
                "Fernanda Lima",
                "67890123456",
                "11999990006",
                "fernanda.lima@email.com"
        );
    }
}
