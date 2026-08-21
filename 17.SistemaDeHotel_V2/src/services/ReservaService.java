package services;

import entities.Hospede;
import entities.Quarto;
import entities.Reserva;
import enums.StatusQuarto;
import enums.StatusReserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservaService {

    private HospedeService hospedeService;
    private QuartoService quartoService;
    private List<Reserva> reservas = new ArrayList<>();

    public ReservaService(HospedeService hospedeService, QuartoService quartoService) {
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
    }

    public List<Reserva> listarReservas() {return Collections.unmodifiableList(reservas);}

    public Reserva criarReserva(int idHospede, int numeroQuarto, LocalDate dataCheckIn, LocalDate dataCheckOut, int quantidadeDeHospede, double valorDiaria){

        Hospede hospede = validarSeHospedeExiste(idHospede);
        Quarto quarto = validarSeQuartoExiste(numeroQuarto);
        validarDatasDeEntradaESaida(dataCheckIn, dataCheckOut);
        validarQuantidadeDeHospede(quantidadeDeHospede, quarto);
        validarValorDiaria(valorDiaria);
        validarSeQuartoEstaDisponivel(quarto);

        Reserva reserva = new Reserva(hospede,quarto, dataCheckIn, dataCheckOut, quantidadeDeHospede, valorDiaria);
        reservas.add(reserva);
        return  reserva;
    }

    public Reserva buscarReservaPorId(int idReserva){
        for(Reserva reserva : reservas){
            if(reserva.getId() == idReserva){
                return reserva;
            }
        }
        return null;
    }
    public double calcularHospedagemPorId(int idReserva){
        Reserva reserva = buscarReservaObrigatoria(idReserva);
        double valorReserva = reserva.getValorDiaria();

        int quantidadeDias = pegarQuantidadeDeDiasHospedados(reserva.getDataCheckIn(), reserva.getDataCheckOut());

        return valorReserva * quantidadeDias;
    }

    public void confirmarReserva(int idReserva){
        Reserva reserva = buscarReservaObrigatoria(idReserva);
        reserva.confirmar();
        reserva.getQuarto().reservado();
    }

    public void cancelarReserva(int idReserva){
        Reserva reserva = buscarReservaObrigatoria(idReserva);
        reserva.cancelar();
    }

    public void realizarCheckIn(int idReserva, LocalDate dataCheckIn){
        Reserva reserva = buscarReservaObrigatoria(idReserva);
        validarDataCheckIn(reserva, dataCheckIn);
        reserva.setDataCheckIn(dataCheckIn);
        reserva.iniciar();
        reserva.getQuarto().ocupado();
    }

    public void realizarCheckOut(int idReserva, LocalDate dataCheckOut){
        Reserva reserva = buscarReservaObrigatoria(idReserva);
        validarDataCheckOut(reserva, dataCheckOut);
        reserva.setDataCheckOut(dataCheckOut);
        reserva.finalizar();
        reserva.getQuarto().disponivel();
    }

    public List<Reserva> listarReservasHospede(int idHospede){
        validarSeHospedeExiste(idHospede);

        List<Reserva> reservaHospede = new ArrayList<>();
        for(Reserva reserva : reservas){
            if(reserva.getHospede().getId() == idHospede){
                reservaHospede.add(reserva);
            }
        }
        return Collections.unmodifiableList(reservaHospede);
    }

    public List<Reserva> listarReservasQuarto(int numeroQuarto){

        validarSeQuartoExiste(numeroQuarto);
        List<Reserva> reservaQuarto = new ArrayList<>();

        for(Reserva reserva : reservas){
            if(reserva.getQuarto().getNumero() == numeroQuarto){
                reservaQuarto.add(reserva);
            }
        }

        return Collections.unmodifiableList(reservaQuarto);
    }
    public List<Quarto> listarQuartosDisponiveis(){
        List<Quarto> quartosDisponiveis = new ArrayList<>();

        for(Quarto quarto : quartoService.listarQuartos()){

            if(quarto.getStatusQuarto() == StatusQuarto.DISPONIVEL){
                quartosDisponiveis.add(quarto);
            }
        }
        return Collections.unmodifiableList(quartosDisponiveis);
    }

    private void validarSeQuartoEstaDisponivel(Quarto quarto){

        if(quarto.getStatusQuarto() != StatusQuarto.DISPONIVEL){
            throw new IllegalArgumentException("O quarto " + quarto.getNumero() + " não está disponivel.");
        }
    }

    public List<Reserva> listarReservasAtivas(){
        List<Reserva> reservasAtiva = new ArrayList<>();
        for(Reserva reserva : reservas){
            if(reserva.getStatusReserva() == StatusReserva.EM_ANDAMENTO){
                reservasAtiva.add(reserva);
            }
        }
        return Collections.unmodifiableList(reservasAtiva);
    }

    public List<Reserva> listarReservasFinalizadas(){
        List<Reserva> reservasFinalizadas = new ArrayList<>();
        for(Reserva reserva : reservas){
            if(reserva.getStatusReserva() == StatusReserva.FINALIZADA){
                reservasFinalizadas.add(reserva);
            }
        }
        return Collections.unmodifiableList(reservasFinalizadas);
    }

    public List<Reserva> listarReservas(LocalDate dataInicio, LocalDate dataFim){
        validarDatasEntradaESaida(dataInicio, dataFim);
        List<Reserva> reservasEncontradas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getDataCheckIn().isBefore(dataFim) && reserva.getDataCheckOut().isAfter(dataInicio)) {

                reservasEncontradas.add(reserva);
            }
        }
        return Collections.unmodifiableList(reservasEncontradas);
    }

    private void validarDatasEntradaESaida(LocalDate dataInicio, LocalDate dataFim){
        if(dataInicio == null || dataFim == null ){
            throw new IllegalArgumentException("Datas devem ser preenchidas corretamente.");
        }

        if(!dataFim.isAfter(dataInicio)){
            throw new IllegalArgumentException("A data de fim deve ser posterior à data de início.");
        }
    }

    private Hospede validarSeHospedeExiste(int idHospede) {
        Hospede hospede = hospedeService.buscarHospedePorId(idHospede);

        if(hospede == null){
            throw new IllegalArgumentException("Hospede não encontrado");
        }
        return  hospede;
    }

    private Quarto validarSeQuartoExiste(int numeroQuarto) {
        Quarto quarto = quartoService.buscarQuartoPorNumero(numeroQuarto);
        if(quarto == null){
            throw new IllegalArgumentException("Quarto não encontrado");
        }
        return  quarto;
    }

    private void validarDatasDeEntradaESaida(LocalDate dataChekIn, LocalDate dataCheckOut){
        if(dataChekIn == null || dataCheckOut == null){
            throw new IllegalArgumentException("Data precisa ser preenchida");
        }

        if(dataChekIn.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data de chekin não pode ser anterior a hoje.");
        }

        if(!dataCheckOut.isAfter((dataChekIn))){
            throw new IllegalArgumentException("A data de chekOut deve ser posterior à data do checkIn");
        }
    }
    private void validarQuantidadeDeHospede(int quantidadeDeHospede, Quarto quarto){
        if(quantidadeDeHospede <= 0){
            throw new IllegalArgumentException("A quantidade de Hospedes deve ser maior que zero");
        }

        if(quantidadeDeHospede > quarto.getCapacidade()){
            throw new IllegalArgumentException("Capacidade de hospedes não pode ultrapassar a capacidade");
        }
    }
    private void validarValorDiaria(double valorDiaria){
        if(valorDiaria <= 0){
            throw new IllegalArgumentException("Valor da diaria deve ser maior que zero");
        }
    }

    private int pegarQuantidadeDeDiasHospedados(LocalDate dataCheckIn, LocalDate dataCheckOut){
        return (int) ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
    }

    private Reserva buscarReservaObrigatoria(int idReserva){
        Reserva reserva = buscarReservaPorId(idReserva);

        if(reserva == null){throw new IllegalArgumentException("Nenhuma reserva encontrada com esse id");}
        return reserva;
    }

    private void validarDataCheckIn(Reserva reserva, LocalDate dataCheckIn){
        if(dataCheckIn == null ){
            throw new IllegalArgumentException("Data precisa ser preenchida");
        }

        if(dataCheckIn.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data de chekin não pode ser anterior a hoje.");
        }

        if(!dataCheckIn.isBefore(reserva.getDataCheckOut())){
            throw new IllegalArgumentException("A data de check-in deve ser anterior à data de check-out.");
        }
    }

    private void validarDataCheckOut(Reserva reserva, LocalDate dataCheckOut){
        if(dataCheckOut == null ){
            throw new IllegalArgumentException("Data precisa ser preenchida");
        }
        if (reserva.getDataCheckIn() == null) {
            throw new IllegalStateException("Não é possível realizar o check-out sem realizar o check-in.");
        }

        if(!dataCheckOut.isAfter(reserva.getDataCheckIn())){
            throw new IllegalArgumentException("A data de check-out deve ser posterior a data de check-in.");
        }
    }
}
