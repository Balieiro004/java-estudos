package entities;

import enums.StatusReserva;

import java.time.LocalDate;

public class Reserva {

    private static int contador;
    private int id;
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private int quantidadeDeHospede;
    private double valorDiaria;
    private StatusReserva statusReserva;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut, int quantidadeDeHospede, double valorDiaria) {
        contador++;
        this.id = contador;
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quantidadeDeHospede = quantidadeDeHospede;
        this.valorDiaria = valorDiaria;
        pendente();
    }

    public int getId() {
        return id;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDate dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDate dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public int getQuantidadeDeHospede() {
        return quantidadeDeHospede;
    }

    public void setQuantidadeDeHospede(int quantidadeDeHospede) {
        this.quantidadeDeHospede = quantidadeDeHospede;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void pendente() {

        validarSeReservaJaEstaPendente();

        this.statusReserva = StatusReserva.PENDENTE;
    }

    public void confirmar() {

        validarSeReservaFoiCancelada();
        validarSeReservaJaEstaConfirmada();

        this.statusReserva = StatusReserva.CONFIRMADA;
    }

    public void cancelar() {

        validarSeReservaPodeSerCancelada();

        this.statusReserva = StatusReserva.CANCELADA;
    }

    public void iniciar() {

        validarSeReservaPodeEntrarEmAndamento();

        this.statusReserva = StatusReserva.EM_ANDAMENTO;
    }

    public void finalizar() {

        validarSeReservaPodeSerFinalizada();

        this.statusReserva = StatusReserva.FINALIZADA;
    }

    private void validarSeReservaJaEstaPendente() {

        if (statusReserva == StatusReserva.PENDENTE) {
            throw new IllegalStateException("A reserva já está pendente.");
        }
    }

    private void validarSeReservaFoiCancelada() {

        if (statusReserva == StatusReserva.CANCELADA) {
            throw new IllegalStateException("A reserva já foi cancelada.");
        }
    }

    private void validarSeReservaJaEstaConfirmada() {

        if (statusReserva == StatusReserva.CONFIRMADA) {
            throw new IllegalStateException("A reserva já está confirmada.");
        }
    }

    private void validarSeReservaPodeEntrarEmAndamento() {

        if (statusReserva != StatusReserva.CONFIRMADA) {
            throw new IllegalStateException("Somente reservas confirmadas podem entrar em andamento.");
        }
    }

    private void validarSeReservaPodeSerFinalizada() {

        if (statusReserva != StatusReserva.EM_ANDAMENTO) {
            throw new IllegalStateException("Somente reservas em andamento podem ser finalizadas.");
        }
    }

    private void validarSeReservaPodeSerCancelada() {

        if (statusReserva == StatusReserva.FINALIZADA) {
            throw new IllegalStateException("Reservas finalizadas não podem ser canceladas.");
        }

        if (statusReserva == StatusReserva.CANCELADA) {
            throw new IllegalStateException("A reserva já foi cancelada.");
        }
    }

    @Override
    public String toString() {
        return "========Reserva========" +
                "\nId: " + id +
                "\nHospede: " + hospede +
                "\nQuarto: " + quarto +
                "\nData CheckIn: " + dataCheckIn +
                "\nData CheckOut: " + dataCheckOut +
                "\nQuantidade De Hospede: " + quantidadeDeHospede +
                "\nValor Diaria: " + valorDiaria +
                "\nStatus Reserva: " + statusReserva;
    }
}

