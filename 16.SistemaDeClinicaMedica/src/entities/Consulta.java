package entities;

import enums.StatusDaConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private static int contador;
    private int id;

    private Paciente paciente;
    private Medico medico;

    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    private StatusDaConsulta statusDaConsulta;

    public Consulta(Paciente paciente, Medico medico, LocalDate dataConsulta, LocalTime horaConsulta) {
        contador++;
        this.id=contador;
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        agendada();
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public StatusDaConsulta getStatusDaConsulta() {
        return statusDaConsulta;
    }

    public void agendada(){this.statusDaConsulta = StatusDaConsulta.AGENDADA;}
    public void confirmada(){this.statusDaConsulta = StatusDaConsulta.CONFIRMADA;}

    public void cancelada(){
        if (statusDaConsulta == StatusDaConsulta.REALIZADA) {
            throw new IllegalStateException("Consulta já realizada.");
        }

        this.statusDaConsulta = StatusDaConsulta.CANCELADA;
    }
    public void realizada(){this.statusDaConsulta = StatusDaConsulta.REALIZADA;}



    @Override
    public String toString() {
        return "========Consulta========" +
                "\nId: " + id +
                "\nPaciente: " + paciente.getNome() +
                "\nMedico: " + medico.getNome() +
                "\nEspecialidade: " + medico.getEspecialidade() +
                "\nData Consulta: " + dataConsulta +
                "\nHora Consulta: " + horaConsulta;
    }
}
