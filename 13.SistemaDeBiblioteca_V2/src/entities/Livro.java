package entities;

import enums.CategoriaLivro;

public class Livro {

    private static int contador;
    private int id;
    private String titulo;
    private String isbn;
    private Autor autor;
    private CategoriaLivro categoria;
    private int anoPublicacao;
    private int quantidadeExemplares;
    private int quantidadeDisponivel;

    public Livro(String titulo, String isbn, Autor autor, CategoriaLivro categoria, int anoPublicacao, int quantidadeExemplares) {
        contador++;
        this.id = contador;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeExemplares = quantidadeExemplares;
        this.quantidadeDisponivel = quantidadeExemplares;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public CategoriaLivro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLivro categoria) {
        this.categoria = categoria;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeExemplares() {
        return quantidadeExemplares;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public boolean isDisponivel(){ return quantidadeDisponivel > 0;}

    public void emprestar() {
        if (quantidadeDisponivel <= 0) {
            throw new IllegalArgumentException("Livro indisponível.");
        }
        quantidadeDisponivel--;
    }

    public void devolver(){
        if (quantidadeDisponivel < quantidadeExemplares) {
            quantidadeDisponivel++;
        }
    }

    @Override
    public String toString() {
        return "========Livro========" +
                "\nId: " + id +
                "\nTitulo: " + titulo +
                "\nIsbn: " + isbn +
                "\nAutor: " + autor.getNome() +
                "\nCategoria: " + categoria +
                "\nAnoPublicacao: " + anoPublicacao +
                "\nQuantidade Exemplares: " + quantidadeExemplares +
                "\nQuantidade Disponivel: " + quantidadeDisponivel;
    }
}
