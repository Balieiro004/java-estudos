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

    public void pendente(){this.status = StatusPedido.PENDENTE;}

    public void pago(){this.status = StatusPedido.PAGO;}

    public void enviado(){this.status = StatusPedido.ENVIADO;}

    public void finalizado(){this.status = StatusPedido.FINALIZADO;}

    public void cancelado(){this.status = StatusPedido.CANCELADO;}

    public boolean isFinalizado() {return status == StatusPedido.FINALIZADO;}

    public boolean isCancelado() {return status == StatusPedido.CANCELADO;}

    public boolean isPendente() {return status == StatusPedido.PENDENTE;}

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("========Pedido========\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("").append(cliente).append("\n");
        sb.append("Data: ").append(data).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Itens:\n");

        itens.forEach(item -> sb.append(item.toString()).append("\n"));

        return sb.toString();
    }
}
