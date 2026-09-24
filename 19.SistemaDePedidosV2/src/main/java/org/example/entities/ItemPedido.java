package org.example.entities;

import org.example.enums.StatusProduto;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {

        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if(produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        if(produto.getStatus() == StatusProduto.INATIVO) {
            throw new IllegalArgumentException("Não é possível adicionar um produto inativo ao pedido.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularSubtotal(){
        return produto.getPreco() * quantidade;
    }
}
