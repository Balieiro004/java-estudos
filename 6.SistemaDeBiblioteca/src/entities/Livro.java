package entities;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private boolean emprestado;

    private static int contator = 0;

    public Livro() {
    }

    public Livro(String titulo, String autor) {
        contator++;
        this.id = contator;
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
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

    public int getId() {
        return id;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public void emprestar(){
        this.emprestado = true;
    }

    public void devolver(){
        this.emprestado = false;
    }

    @Override
    public String toString() {
        return "\nLivro " +
                "\nid: " + id +
                "\ntitulo: " + titulo +
                "\nautor: " + autor +
                "\nemprestado: " + emprestado +
                "\n======================";
    }
}
