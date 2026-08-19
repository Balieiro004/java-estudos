package entities;

import enums.StatusQuarto;
import enums.TipoQuarto;

public class Quarto {

    private static int contador = 0;
    private int id;
    private int numero;
    private int capacidade;
    private TipoQuarto tipoQuarto;
    private StatusQuarto statusQuarto;

    public Quarto(int numero, int capacidade, TipoQuarto tipoQuarto) {
        contador++;
        this.id = contador;
        this.numero = numero;
        this.capacidade = capacidade;
        this.tipoQuarto = tipoQuarto;
        disponivel();
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public void disponivel(){

        validarSeQuartoEstaDisponivel();
        this.statusQuarto = StatusQuarto.DISPONIVEL;
    }
    public void reservado(){
        validarSeQuartoPodeSerReservado();

        this.statusQuarto = StatusQuarto.RESERVADO;
    }
    public void ocupado(){
        validarSeQuartoPodeSerOcupado();

        this.statusQuarto = StatusQuarto.OCUPADO;
    }
    public void manutencao(){
        validarSeQuartoPodeEntrarEmManutencao();

        this.statusQuarto = StatusQuarto.MANUTENCAO;
    }

    public StatusQuarto getStatusQuarto() {return statusQuarto;}


    private void validarSeQuartoEstaDisponivel(){
        if(statusQuarto == StatusQuarto.DISPONIVEL){
            throw new IllegalStateException("O quarto já está disponivel.");
        }
    }

    private void validarSeQuartoPodeSerReservado(){
        if (statusQuarto != StatusQuarto.DISPONIVEL) {
            throw new IllegalStateException("Somente quartos disponíveis podem ser reservados.");
        }
    }

    private void validarSeQuartoPodeSerOcupado() {

        if (statusQuarto != StatusQuarto.RESERVADO) {
            throw new IllegalStateException("Somente quartos reservados podem ser ocupados.");
        }
    }

    private void validarSeQuartoPodeEntrarEmManutencao() {

        if (statusQuarto == StatusQuarto.OCUPADO) {
            throw new IllegalStateException("Quartos ocupados não podem entrar em manutenção.");
        }
    }

    @Override
    public String toString() {
        return "========Quarto========" +
                "\nId: " + id +
                "\nNumero: " + numero +
                "\nCapacidade: " + capacidade +
                "\nTipo Quarto: " + tipoQuarto +
                "\nStatus Quarto: " + statusQuarto;
    }
}
