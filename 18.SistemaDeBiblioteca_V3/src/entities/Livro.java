package entities;

import enums.StatusLivro;

public class Livro {

    private static int contador;
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublicacao;
    private StatusLivro statusLivro;

    public Livro(String titulo, String autor, String isbn, int anoPublicacao, StatusLivro statusLivro) {
        contador++;
        this.id = contador;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.statusLivro = statusLivro;
    }
}
