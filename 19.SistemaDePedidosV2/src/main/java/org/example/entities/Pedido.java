package org.example.entities;

import org.example.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private StatusPedido status;
    private List<ItemPedido> itens;

    public Pedido() {
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item){

        if(item == null){
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }

        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("Não é possível adicionar itens a um pedido cancelado.");
        }
        item.getProduto().baixarEstoque(item.getQuantidade());
        itens.add(item);
    }

    public void cancelar(){
        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("O pedido ja está canelado.");
        }
        status = StatusPedido.CANCELADO;
    }

    public double calcularTotal(){
        double total = 0;

        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }
}
