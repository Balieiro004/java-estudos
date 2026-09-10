package entities;

import enums.StatusEmprestimo;

import java.time.LocalDate;

public class Emprestimo {

    private static int contador;

    private int id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private StatusEmprestimo statusEmprestimo;

    public Emprestimo(Usuario usuario,
                      Livro livro,
                      LocalDate dataEmprestimo,
                      LocalDate dataDevolucaoPrevista) {

        contador++;
        this.id = contador;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.statusEmprestimo = StatusEmprestimo.ATIVO;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void devolvido() {
        validarSeEmprestimoPodeSerDevolvido();

        this.statusEmprestimo = StatusEmprestimo.DEVOLVIDO;
        this.dataDevolucaoReal = LocalDate.now();
    }

    public void atrasado() {
        validarSeEmprestimoEstaAtivo();

        this.statusEmprestimo = StatusEmprestimo.ATRASADO;
    }

    private void validarSeEmprestimoPodeSerDevolvido() {
        if (statusEmprestimo == StatusEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException(
                    "O empréstimo já foi devolvido."
            );
        }
    }

    private void validarSeEmprestimoEstaAtivo() {
        if (statusEmprestimo != StatusEmprestimo.ATIVO) {
            throw new IllegalStateException(
                    "Somente empréstimos ativos podem ficar atrasados."
            );
        }
    }

    @Override
    public String toString() {
        return "========Emprestimo========" +
                "\nId: " + id +
                "\nUsuario: " + usuario +
                "\nLivro: " + livro +
                "\nData Emprestimo: " + dataEmprestimo +
                "\nData Devolucao Prevista: " + dataDevolucaoPrevista +
                "\nData Devolucao Real: " + dataDevolucaoReal +
                "\nStatus Emprestimo: " + statusEmprestimo;
    }
}
