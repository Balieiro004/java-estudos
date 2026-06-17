package entities;

import entities.enums.StatusEquipamento;

public class Equipamento {

    private static int contador;
    private int id;
    private String nome;
    private String patrimonio;
    private String serviceTag;
    private StatusEquipamento status;
    private Colaborador colaboradorComEquipamento;

    public Equipamento() {
    }

    public Equipamento(String nome, String patrimonio, String serviceTag) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.patrimonio = patrimonio;
        this.serviceTag = serviceTag;
        this.status = StatusEquipamento.DISPONIVEL;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public StatusEquipamento getStatus() {
        return status;
    }

    public void setStatus(StatusEquipamento  status) {
        this.status = status;
    }

    public void emprestarEquipamento(Colaborador colaborador) {
        this.status = StatusEquipamento.EMPRESTADO;
        this.colaboradorComEquipamento = colaborador;
    }

    public void devolverEquipamento() {
        this.status = StatusEquipamento.DISPONIVEL;
        this.colaboradorComEquipamento = null;
    }

    public Colaborador getColaboradorComEquipamento() {
        return colaboradorComEquipamento;
    }

    public void setColaboradorComEquipamento(Colaborador colaboradorComEquipamento) {
        this.colaboradorComEquipamento = colaboradorComEquipamento;
    }

    @Override
    public String toString() {

        String colaborador = "Nenhum";

        if(colaboradorComEquipamento != null){
            colaborador = colaboradorComEquipamento.getNome();
        }
        return "Equipamento" +
                "\nid: " + id +
                "\nnome: " + nome +
                "\npatrimonio: " + patrimonio +
                "\nserviceTag: " + serviceTag +
                "\nstatus: " + status +
                "\ncolaborador: " + colaborador;
    }
}
