package entities;

import enums.StatusPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contador = 0;
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private StatusPedido status;
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

    public Pedido(Cliente cliente, LocalDate data, StatusPedido status) {
        contador++;
        this.id=contador;
        this.cliente = cliente;
        this.data = data;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item){
        itens.add(item);
    }

    public double calcularTotal(){
        double total = 0;

        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }
}
