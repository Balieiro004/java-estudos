package org.example;

public class Produto {

    private String nome;
    private double preco;
    private int estoque;
    private StatusProduto status;

    public Produto(String nome, double preco, int estoque) {
        validarNomeProduto(nome);
        validarPrecoProduto(preco);
        validarEstoque(estoque);
        this.preco = preco;
        this.nome = nome;
        this.estoque = estoque;
        ativar();
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void baixarEstoque(int quantidade) {

        validarQuantidade(quantidade);
        if(this.status == StatusProduto.INATIVO) {
            throw  new IllegalStateException("Não é possível baixar o estoque de um produto inativo.");
        }

        if (quantidade > estoque) {
            throw new IllegalArgumentException("Quantidade maior que o estoque disponível.");
        }
        estoque -= quantidade;
    }

    public void reporEstoque(int quantidade) {
        validarQuantidade(quantidade);
        estoque += quantidade;
    }

    public void ativar(){
        if(this.status == StatusProduto.ATIVO){
            throw new IllegalStateException("O produto já está ativo");
        }
        this.status = StatusProduto.ATIVO;
    }

    public void desativar() {
        if(this.status == StatusProduto.INATIVO){
            throw new IllegalStateException("O produto ja está inativo.");
        }
        this.status = StatusProduto.INATIVO;
    }

    public StatusProduto getStatus() {
        return status;
    }

    public double calcularValor(int quantidade) {
        validarQuantidade(quantidade);
        return preco * quantidade;
    }

    private void validarNomeProduto(String nome){
        if(nome.isBlank()){
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
    }
    private void validarPrecoProduto(double preco){
        if(preco <= 0){
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
    }

    private void validarEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }
}
