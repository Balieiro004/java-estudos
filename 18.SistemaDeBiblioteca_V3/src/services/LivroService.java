package services;

import entities.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivroService {

    private final int anoAtual = LocalDate.now().getYear();

    private List<Livro> livros = new ArrayList<>();

    public List<Livro> listarLivros() {return Collections.unmodifiableList(livros);}

    public Livro cadastrarLivro(String titulo, String autor, String isbn, int anoPublicacao){

        validarTitulo(titulo);
        validarAutor(autor);
        validarIsbn(isbn);
        validarAnoPublicacao(anoPublicacao);

        Livro livro = new Livro(titulo, autor, isbn, anoPublicacao);
        livros.add(livro);
        return livro;
    }

    public Livro buscarLivroPorId(int id){
        for(Livro livro : livros){
            if(livro.getId() == id){
                return livro;
            }
        }
        return null;
    }

    private void validarTitulo(String titulo){
        if(titulo == null || titulo.isEmpty()){
            throw new IllegalStateException("Titulo precisa ser preenchido.");
        }

        if(titulo.length() < 3){
            throw new IllegalStateException("Titulo precisa ter mais de 2  caracteres.");
        }
    }

    private void validarAutor(String autor){
        if(autor == null || autor.isEmpty()){
            throw new IllegalStateException("Autor precisa ser preenchido.");
        }

        if(autor.length() < 3){
            throw new IllegalStateException("Autor precisa ter mais de 2 caracteres.");
        }
    }
    private void validarIsbn(String isbn){
        if(isbn == null || isbn.isEmpty()){
            throw new IllegalStateException("Isbn precisa ser preenchido.");
        }
        if(isbn.length() < 3){
            throw new IllegalStateException("Isbn precisa ter mais de 2 caracteres.");
        }
    }

    private void validarAnoPublicacao(int anoPublicacao){
        if(anoPublicacao < 1 || anoPublicacao >  anoAtual){
            throw new IllegalStateException("Ano precisa ser entre 1 e o ano " + anoAtual + ".");
        }
    }
}
