package services;

import entities.Colaborador;
import entities.Reserva;
import entities.Sala;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();

    private  ColaboradorService colaboradorService;
    private SalaService salaService;

    public ReservaService(ColaboradorService colaboradorService, SalaService salaService) {
        this.colaboradorService = colaboradorService;
        this.salaService = salaService;

        carregarReservasMock();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva criarReserva(Colaborador colaborador, Sala sala, LocalDate data, LocalTime horaInicial, LocalTime horaFinal){

        if(colaborador == null) return null;

        if(sala == null) return null;

        if(data == null) return null;

        if(horaInicial == null || horaFinal == null) return null;

        if(!horaInicial.isBefore(horaFinal)) return null;

        if(data.isBefore(LocalDate.now())) return null;

        for (Reserva reserva : reservas) {
            if (reserva.getSala().getId() == sala.getId() && reserva.getData().equals(data)) {

                boolean horarioConflitante = horaInicial.isBefore(reserva.getHoraFinal()) && horaFinal.isAfter(reserva.getHoraInicial());

                if (horarioConflitante) { return null; }
            }
        }

        Reserva reserva = new Reserva(colaborador, sala, data, horaInicial, horaFinal);
        reservas.add(reserva);
        return reserva;
    }

    public String deletarReserva(int idReserva){
        Reserva reserva = buscaReservaPorId(idReserva);

        if(reserva == null) return "Reserva não encontrada";

        reservas.remove(reserva);
        return "Reserva deletada com sucesso";
    }

    private Reserva buscaReservaPorId(int idReserva){
        for(Reserva reserva : reservas){
            if(reserva.getId() == idReserva) return reserva;
        }
        return null;
    }

    private void carregarReservasMock() {
        reservas.add(new Reserva(
                colaboradorService.getColaboradores().get(0),
                salaService.getSalas().get(0),
                LocalDate.now().plusDays(1),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)));

        reservas.add(new Reserva(
                colaboradorService.getColaboradores().get(1),
                salaService.getSalas().get(1),
                LocalDate.now().plusDays(1),
                LocalTime.of(10, 30),
                LocalTime.of(11, 30)));

        reservas.add(new Reserva(
                colaboradorService.getColaboradores().get(2),
                salaService.getSalas().get(0),
                LocalDate.now().plusDays(2),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0)));

        reservas.add(new Reserva(
                colaboradorService.getColaboradores().get(3),
                salaService.getSalas().get(2),
                LocalDate.now().plusDays(3),
                LocalTime.of(8, 0),
                LocalTime.of(9, 30)));

        reservas.add(new Reserva(
                colaboradorService.getColaboradores().get(4),
                salaService.getSalas().get(1),
                LocalDate.now().plusDays(3),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0)));
    }
}
