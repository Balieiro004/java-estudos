package entities;

import enums.StatusReserva;

import java.time.LocalDate;

public class Reserva {

    private static int contador = 0;
    private int id;
    private Cliente cliente;
    private Quarto quarto;

    private LocalDate checkIn;
    private LocalDate checkOutPrevisto;
    private LocalDate checkOutReal;

    private StatusReserva status;

    public Reserva(Cliente cliente, Quarto quarto, LocalDate checkIn, LocalDate checkOutPrevisto, LocalDate checkOutReal) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOutPrevisto = checkOutPrevisto;
        this.checkOutReal = checkOutReal;
        ativa();
    }

    public void ativa(){this.status = StatusReserva.ATIVA;}
    public void finalizada(){this.status = StatusReserva.FINALIZADA;}
    public void cancelada(){this.status = StatusReserva.CANCELADA;}

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOutPrevisto() {
        return checkOutPrevisto;
    }

    public void setCheckOutPrevisto(LocalDate checkOutPrevisto) {
        this.checkOutPrevisto = checkOutPrevisto;
    }

    public LocalDate getCheckOutReal() {
        return checkOutReal;
    }

    public void setCheckOutReal(LocalDate checkOutReal) {
        this.checkOutReal = checkOutReal;
    }

    public StatusReserva getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "========Reserva========" +
                "\nId: " + id +
                "\nCliente: " + cliente.getNome() +
                "\nQuarto: " + quarto.getNumero() +
                "\nCheckIn: " + checkIn +
                "\nCheckOut Previsto: " + checkOutPrevisto +
                "\nCheckOut Real: " + checkOutReal +
                "\nStatus: " + status;
    }
}
