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

    private void agendada(){this.statusDaConsulta = StatusDaConsulta.AGENDADA;}
    private void confirmada(){this.statusDaConsulta = StatusDaConsulta.CONFIRMADA;}
    private void cancelada(){this.statusDaConsulta = StatusDaConsulta.CANCELADA;}
    private void realizada(){this.statusDaConsulta = StatusDaConsulta.REALIZADA;}



    @Override
    public String toString() {
        return "========Consulta========" +
                "\nId: " + id +
                "\nPaciente: " + paciente +
                "\nMedico: " + medico +
                "\nData Consulta: " + dataConsulta +
                "\nHora Consulta: " + horaConsulta;
    }
}
