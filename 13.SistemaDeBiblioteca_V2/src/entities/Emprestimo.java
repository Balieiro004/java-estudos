package entities;

import java.time.LocalDate;

public class Emprestimo {

    private static int contador = 0;
    private int id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private double multa;

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        contador++;
        this.id = contador;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucao = null;
        this.multa = 0;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void devolver(LocalDate dataDevolucao) {

        if (this.dataDevolucao != null) {
            throw new IllegalStateException("Este empréstimo já foi encerrado.");
        }

        this.dataDevolucao = dataDevolucao;
    }

    public void renovar(LocalDate novaDataPrevista) {

        if (!novaDataPrevista.isAfter(dataPrevistaDevolucao)) {
            throw new IllegalArgumentException(
                    "A nova data deve ser posterior à atual."
            );
        }

        this.dataPrevistaDevolucao = novaDataPrevista;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "========Emprestimo========" +
                "\nId: " + id +
                "\nUsuario: " + usuario.getNome() +
                "\nLivro: " + livro.getTitulo() +
                "\nData Emprestimo: " + dataEmprestimo +
                "\nData Prevista Devolucao: " + dataPrevistaDevolucao +
                "\nData Devolucao: " + dataDevolucao +
                "\nMulta: " + multa;
    }
}
