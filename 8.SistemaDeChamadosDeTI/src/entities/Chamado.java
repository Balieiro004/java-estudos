package entities;

import entities.enuns.StatusChamado;

public class Chamado {

    private static int contador = 0;
    private int id;
    private String titulo;
    private String descricao;

    private Colaborador colaborador;
    private Tecnico tecnicoResponsavel;

    private StatusChamado statusChamado;

    public Chamado() {
    }

    public Chamado(String titulo, String descricao, Colaborador colaborador, Tecnico tecnicoResponsavel) {
        contador++;
        id = contador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.colaborador = colaborador;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.statusChamado = StatusChamado.ABERTO;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public StatusChamado getStatusChamado() {
        return statusChamado;
    }

    public void iniciarAtendimento() {
        this.statusChamado = StatusChamado.EM_ANDAMENTO;
    }

    public void fecharChamado() {
        this.statusChamado = StatusChamado.ENCERRADO;
    }

    public void reabrirChamado() {
        this.statusChamado = StatusChamado.ABERTO;
    }

    @Override
    public String toString() {
        return "========================" +
                "\nChamado" +
                "\nId: " + id +
                "\nTitulo: " + titulo +
                "\nDescricao: " + descricao +
                "\nColaborador: " + colaborador.getNome() +
                "\nTecnico Responsavel: " + tecnicoResponsavel.getNome()+
                "\nArea atuação do Técnico: " + tecnicoResponsavel.getEspecialidade() +
                "\nStatusChamado: " + statusChamado;
    }
}
