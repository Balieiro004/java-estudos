package services;

import entities.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

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
            throw new IllegalArgumentException("Já existe um usuaário com esse CPF.");
        }
    }
    private void validarTelefone(String telefone){
        if(telefone == null || telefone.isEmpty()){
            throw new IllegalArgumentException("Telefone precisa ser preenchido.");
        }
    }
    private void validarEmail(String email){
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email precisa ser preenchido.");
        }

        if(buscarUsuarioPorEmail(email).isPresent()){
            throw new IllegalArgumentException("Já existe um usuário com esse email.");
        }
    }


}
