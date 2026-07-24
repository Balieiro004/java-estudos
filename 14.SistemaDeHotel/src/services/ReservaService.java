package services;

import entities.Cliente;
import entities.Quarto;
import entities.Reserva;
import enums.StatusQuarto;
import enums.StatusReserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservaService {

    private ClienteService clienteService;
    private QuartoService quartoService;
    private List<Reserva> reservas = new ArrayList<>();

    public ReservaService(ClienteService clienteService, QuartoService quartoService) {
        this.clienteService = clienteService;
        this.quartoService = quartoService;
    }

    public List<Reserva> listarReservas() {return Collections.unmodifiableList(reservas);}


    public Reserva criarReserva(int idCliente, int numeroQuarto, LocalDate checkIn, LocalDate checkOutPrevisto, LocalDate checkOutReal){

        Cliente cliente = validarClientePorId(idCliente);
        Quarto quarto = validarQuartoPorNumero(numeroQuarto);
        validarSeQuartoEstaDisponivel(quarto.getStatus());
        validarDatasDeEntradaESaida(checkIn, checkOutPrevisto);
        quarto.ocupado();

        Reserva reserva = new Reserva(cliente, quarto, checkIn, checkOutPrevisto, checkOutReal);
        reservas.add(reserva);
        return reserva;
    }

    public Reserva finalizarReserva(int idReserva, LocalDate checkOutReal){

        Reserva reserva = validarReservaPorId(idReserva);
        validarReservaPorStatus(reserva);
        reserva.setCheckOutReal(checkOutReal);

        reserva.finalizada();
        reserva.getQuarto().disponivel();

        return reserva;
    }

    private void validarReservaPorStatus(Reserva reserva){
        if(reserva.getStatus() == StatusReserva.FINALIZADA){
            throw new IllegalArgumentException("Reserva já foi finalizada");
        }
        if(reserva.getStatus() == StatusReserva.CANCELADA){
            throw new IllegalArgumentException("Reserva ja foi cancelada");
        }
    }
    private Reserva validarReservaPorId(int idReserva){
        Reserva reserva = buscarReservaPorId(idReserva);

        if(reserva != null){
            return reserva;
        }
        throw new IllegalArgumentException("Reserva Não encontrada.");
    }

    private Reserva buscarReservaPorId(int idReserva){
        for (Reserva reserva : reservas) {
            if(reserva.getId() == idReserva){
                return reserva;
            }
        }
        return null;
    }

    private Cliente validarClientePorId(int idCliente){
        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente;
    }

    private Quarto validarQuartoPorNumero(int numeroQuarto){
        Quarto quarto = quartoService.buscarQuartoPorNumero(numeroQuarto);
        if(quarto == null){
            throw new IllegalArgumentException("Quarto não encontrado");
        }
        return quarto;
    }

    private void validarSeQuartoEstaDisponivel(StatusQuarto status){
        if(status == StatusQuarto.OCUPADO){
            throw new IllegalArgumentException("Quarto está ocupado no momento.");
        }

        if(status == StatusQuarto.MANUTENCAO){
            throw new IllegalArgumentException("Quarto em manutenção no momento.");
        }
    }

    private void validarDatasDeEntradaESaida(LocalDate checkIn, LocalDate checkOutPrevisto){
        if(checkIn == null || checkOutPrevisto == null){
            throw new IllegalArgumentException("Data precisa ser preenchida");
        }

        if(checkIn.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data de chekin não pode ser anterior a hoje.");
        }

        if(!checkOutPrevisto.isAfter((checkIn))){
            throw new IllegalArgumentException("A data de chekOut deve ser posterior à data do chelIn");
        }
    }

}
