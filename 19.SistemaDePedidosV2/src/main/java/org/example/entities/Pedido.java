package org.example.entities;

import org.example.enums.StatusPedido;

import java.util.ArrayList;
import java.util.Collections;
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
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(ItemPedido item){

        if(item == null){
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }

        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("Não é possível adicionar itens a um pedido cancelado.");
        }

        if (status == StatusPedido.PAGO){
            throw new IllegalStateException("Não é possível adicionar itens a um pedido pago");
        }

        if (status == StatusPedido.ENVIADO){
            throw new IllegalStateException("Não é possível adicionar itens a um pedido enviado");
        }

        item.getProduto().baixarEstoque(item.getQuantidade());
        itens.add(item);
    }

    public void cancelar(){
        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("O pedido já está cancelado.");
        }

        if(status == StatusPedido.PAGO){
            throw new IllegalStateException("Pedido ja foi pago.");
        }
        if(status == StatusPedido.ENVIADO){
            throw new IllegalStateException("Pedido ja Enviado.");
        }

        for (ItemPedido item : itens) {
            item.getProduto().reporEstoque(item.getQuantidade());
        }

        status = StatusPedido.CANCELADO;
    }

    public void pagar(){
        if(status == StatusPedido.PAGO){
            throw new IllegalStateException("Pedido já está como pago.");
        }

        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("Não é possível pagar um pedido cancelado.");
        }

        if(status == StatusPedido.ENVIADO){
            throw new IllegalStateException("Não é possível pagar um pedido que já foi enviado.");
        }
        status = StatusPedido.PAGO;
    }

    public void enviar() {
        if(status != StatusPedido.PAGO){
            throw new IllegalStateException("O pedido ainda não foi pago.");
        }
        status = StatusPedido.ENVIADO;
    }

    public double calcularTotal(){
        double total = 0;

        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void removerItem(ItemPedido item){

        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
        if (status == StatusPedido.PAGO){
            throw new IllegalStateException("Não é possível remover itens de um pedido pago.");
        }
        if(status == StatusPedido.ENVIADO){
            throw new IllegalStateException("Não é possível remover itens de um pedido enviado.");
        }
        if(status == StatusPedido.CANCELADO){
            throw new IllegalStateException("Não é possível remover itens de um pedido cancelado.");
        }

        if(!itens.contains(item)){
            throw new IllegalArgumentException("Este item não está na lista");
        }
        item.getProduto().reporEstoque(item.getQuantidade());
        itens.remove(item);
    }
}
