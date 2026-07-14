package services;

import entities.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public ProdutoService() {carregarProdutosMock();}

    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public Produto cadastrarProduto(String nome, double preco, int estoque){

        if(nome == null || nome.isBlank()){throw new IllegalArgumentException("Nome é obrigatório.");}

        if(preco <= 0){throw new IllegalArgumentException("Valor não pode ser negativo ou zero.");}

        if(estoque <= 0){throw new IllegalArgumentException("Estoque não pode ser negativo ou zero.");}

        Produto produto = new Produto(nome, preco, estoque);
        produtos.add(produto);
        return produto;
    }

    public Produto buscarProdutoPorId(int id){
        for(Produto produto: produtos){
            if(produto.getId() == id){return produto;}
        }
        return null;
    }

    public void deletarProdutoPorId(int id){
        Produto produto = buscarProdutoPorId(id);

        if(produto == null){
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        produtos.remove(produto);
    }

    private void carregarProdutosMock() {
        cadastrarProduto("Notebook Dell Inspiron", 3899.90, 10);
        cadastrarProduto("Mouse Logitech G203", 149.90, 30);
        cadastrarProduto("Teclado Mecânico Redragon", 279.90, 20);
        cadastrarProduto("Monitor LG 24 Polegadas", 899.90, 15);
        cadastrarProduto("Headset HyperX Cloud Stinger", 249.90, 25);
        cadastrarProduto("SSD Kingston 1TB", 459.90, 18);
    }
}
