package entities;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemPedido(Produto produto, int quantidade, double precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double calcularSubtotal(){
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido" +
                "\nProduto: " + produto.getNome() +
                "\nQuantidade: " + quantidade +
                "\nPreco Unitario: " + precoUnitario;
    }
}
