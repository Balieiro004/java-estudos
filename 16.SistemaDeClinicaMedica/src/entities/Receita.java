package entities;

import java.time.LocalDate;

public class Receita {

    private static int contador;
    private int id;

    private Paciente paciente;
    private Medico medico;
    private String descricao;
    private LocalDate dataEmissao;

    public Receita(Paciente paciente, Medico medico, String descricao) {

        contador++;
        this.id = contador;

        this.paciente = paciente;
        this.medico = medico;
        this.descricao = descricao;
        this.dataEmissao = LocalDate.now();
    }

    public int getId() {return id;}

    public Paciente getPaciente() {return paciente;}

    public Medico getMedico() {return medico;}

    public String getDescricao() {return descricao;}

    public LocalDate getDataEmissao() {return dataEmissao;}

    @Override
    public String toString() {
        return "======== Receita ========" +
                "\nId: " + id +
                "\nPaciente: " + paciente.getNome() +
                "\nMédico: " + medico.getNome() +
                "\nData de Emissão: " + dataEmissao +
                "\nReceita: " + descricao;
    }
}
