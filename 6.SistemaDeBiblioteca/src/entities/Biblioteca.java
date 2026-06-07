package entities;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public String cadastrarLivro(String titulo, String autor) {
        if(titulo == null || autor == null
        || titulo.isEmpty() || autor.isEmpty()){
            return "O titulo e o autor precisam ser preenchido.";
        }
        livros.add(new Livro(titulo, autor));
        return "Livro cadastrado com sucesso!";
    }

    public String cadastrarUsuario(String nome) {

        if(nome == null || nome.isEmpty()){
            return "O nome e autor precisam ser preenchido.";
        }
        usuarios.add(new Usuario(nome));
        return "Usuario cadastrado com sucesso!";
    }

    public ArrayList<Livro> listarLivros() {
        return livros;
    }

    public ArrayList<Usuario> listarUsuarios() {return usuarios;}

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
}
