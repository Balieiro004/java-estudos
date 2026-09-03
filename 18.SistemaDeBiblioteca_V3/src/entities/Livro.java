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

    public Livro(String titulo, String autor, String isbn, int anoPublicacao) {
        contador++;
        this.id = contador;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.statusLivro = StatusLivro.DISPONIVEL;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    public void disponivel(){
        validarSeLivroEstaEmprestado();
        this.statusLivro = StatusLivro.DISPONIVEL;
    }

    public void emprestado(){
        validarSeLivroEstaDisponivel();
        this.statusLivro = StatusLivro.EMPRESTADO;
    }

    private void validarSeLivroEstaDisponivel(){

        if (statusLivro != StatusLivro.DISPONIVEL) {
            throw new IllegalStateException("O livro não está disponível para empréstimo.");
        }
    }

    private void validarSeLivroEstaEmprestado() {
        if (statusLivro != StatusLivro.EMPRESTADO) {
            throw new IllegalStateException("O livro não está emprestado.");
        }
    }

    @Override
    public String toString() {
        return "========Livro========" +
                "\nId: " + id +
                "\nTitulo: " + titulo  +
                "\nAutor: " + autor +
                "\nIsbn: " + isbn +
                "\nAno Publicacao: " + anoPublicacao +
                "\nStatus Livro: " + statusLivro;
    }
}
