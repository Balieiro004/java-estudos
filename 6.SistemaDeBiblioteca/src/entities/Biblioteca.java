package entities;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public String cadastrarLivro(String titulo, String autor) {
        if(titulo == null || autor == null
        || titulo.trim().isEmpty() || autor.trim().isEmpty()){
            return "O titulo e o autor precisam ser preenchido.";
        }
        livros.add(new Livro(titulo, autor));
        return "Livro cadastrado com sucesso!";
    }

    public String cadastrarUsuario(String nome) {

        if(nome == null ||  nome.trim().isEmpty()){
            return "O nome precisa ser preenchido.";
        }

        if(buscarUsuarioPorNome(nome) != null){
            return "Usuário já cadastrado.";
        }
        usuarios.add(new Usuario(nome));
        return "Usuario cadastrado com sucesso!";
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Usuario> listarUsuarios() {return usuarios;}

    public String emprestarLivro(int idUsuario, int idLivro) {

        Usuario usuarioEncontrado = buscarUsuarioPorId(idUsuario);

        if (usuarioEncontrado == null) {
            return "Usuario não encontrado.";
        }

        Livro livroEncontrado = buscarLivroPorId(idLivro);

        if (livroEncontrado == null) {
            return "Livro não encontrado.";
        }

        if(livroEncontrado.isEmprestado()){
            return "Livro já está emprestado.";
        }
        livroEncontrado.emprestar(usuarioEncontrado);
        return "Livro emprestado com sucesso para " + usuarioEncontrado.getNome();
    }

    public String devolverLivro(int idUsuario, int idLivro) {

        Usuario usuarioEncontrado = buscarUsuarioPorId(idUsuario);

        if (usuarioEncontrado == null) {
            return "Usuario não encontrado.";
        }

        Livro livroEncontrado = buscarLivroPorId(idLivro);

        if (livroEncontrado == null) {
            return "Livro não encontrado.";
        }

        if(!livroEncontrado.isEmprestado()){
            return "Livro não está emprestado.";
        }

        if(livroEncontrado.getUsuarioComLivro().getId() != idUsuario){return "Esse usuário não está com o livro.";}

        livroEncontrado.devolver();
        return "Livro devolvido com sucesso, obrigado " + usuarioEncontrado.getNome();
    }

    public List<Livro> listarLivrosEmprestados(){

        List<Livro> livrosEmprestados = new ArrayList<>();

        for(Livro livro : livros){

            if(livro.isEmprestado()){
                livrosEmprestados.add(livro);
            }
        }
        return livrosEmprestados;
    }

    private Usuario buscarUsuarioPorId(int idUsuario) {
        for(Usuario usuario : usuarios){
            if(usuario.getId() == idUsuario){
                return usuario;
            }
        }
        return null;
    }

    private Livro buscarLivroPorId(int idLivro) {
        for(Livro livro : livros){
            if(livro.getId() == idLivro){
                return livro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }
}
