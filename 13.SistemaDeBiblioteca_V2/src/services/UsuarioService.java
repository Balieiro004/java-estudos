package services;

import entities.Emprestimo;
import entities.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {carregarUsuariosMock();}

    public List<Usuario> listarUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public Usuario cadastrarUsuario(String nome, String cpf, String telefone, String email ){

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("Cpf é obrigatório.");
        }

        if(buscarUsuarioPorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um usuário com esse CPF.");
        }

        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }

        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email é obrigatório.");
        }

        if(buscarUsuarioPorEmail(email) != null){
            throw new IllegalArgumentException("Já existe um usuario com esse email.");
        }

        Usuario usuario = new Usuario(nome, cpf, telefone, email);
        usuarios.add(usuario);
        return usuario;
    }

    private Usuario buscarUsuarioPorCpf(String cpf){
        for(Usuario usuario : usuarios){
            if(usuario.getCpf().equals(cpf)){
                return usuario;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorEmail(String email){
        for(Usuario usuario : usuarios){
            if(usuario.getEmail().equalsIgnoreCase(email)){
                return usuario;
            }
        }
        return null;
    }

    protected Usuario buscarUsuarioPorId(int id){
        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public List<Emprestimo> consultarEmprestimos(int idUsuario){

        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if(usuario == null){
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return usuario.getEmprestimos();
    }

    private void carregarUsuariosMock() {
        cadastrarUsuario(
                "João Silva",
                "11111111111",
                "11999990001",
                "joao.silva@email.com"
        );

        cadastrarUsuario(
                "Maria Oliveira",
                "22222222222",
                "11999990002",
                "maria.oliveira@email.com"
        );

        cadastrarUsuario(
                "Pedro Santos",
                "33333333333",
                "11999990003",
                "pedro.santos@email.com"
        );

        cadastrarUsuario(
                "Ana Costa",
                "44444444444",
                "11999990004",
                "ana.costa@email.com"
        );

        cadastrarUsuario(
                "Carlos Pereira",
                "55555555555",
                "11999990005",
                "carlos.pereira@email.com"
        );

        cadastrarUsuario(
                "Fernanda Lima",
                "66666666666",
                "11999990006",
                "fernanda.lima@email.com"
        );
    }
}
