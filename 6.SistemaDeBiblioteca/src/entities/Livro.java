package entities;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private boolean emprestado;
    private Usuario usuarioComLivro;

    private static int contador = 0;

    public Livro() {
    }

    public Livro(String titulo, String autor) {
        contador++;
        this.id = contador;
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

    public void emprestar(Usuario usuario) {
        this.emprestado = true;
        this.usuarioComLivro = usuario;
    }

    public void devolver(){
        this.emprestado = false;
        this.usuarioComLivro = null;
    }

    public Usuario getUsuarioComLivro() {
        return usuarioComLivro;
    }

    public void setUsuarioComLivro(Usuario usuarioComLivro) {
        this.usuarioComLivro = usuarioComLivro;
    }

    @Override
    public String toString() {

        String usuario = "Nenhum";

        if(usuarioComLivro != null){
            usuario = usuarioComLivro.getNome();
        }
        return "\nLivro " +
                "\nid: " + id +
                "\ntitulo: " + titulo +
                "\nautor: " + autor +
                "\nemprestado: " + emprestado +
                "\ncom usuario: " + usuario +
                "\n======================";
    }
}
