package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {

    private static int contador = 0;

    private int id;
    private Colaborador colaborador;
    private Sala sala;
    private LocalDate data;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public Reserva(Colaborador colaborador, Sala sala, LocalDate data, LocalTime horaInicial, LocalTime horaFinal) {
        contador++;
        this.id = contador;
        this.colaborador = colaborador;
        this.sala = sala;
        this.data = data;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public int getId() {return id;}

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
    @Override
    public String toString() {
        return "Reserva" +
                "\nId: " + id +
                "\nColaborador: " + colaborador.getNome() +
                "\nSala: " + sala.getNome() +
                "\nData: " + data +
                "\nHora Inicial: " + horaInicial +
                "\nHora Final: " + horaFinal;
    }
}
