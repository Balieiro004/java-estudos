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
}
