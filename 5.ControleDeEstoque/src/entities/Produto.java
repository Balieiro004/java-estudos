package entities;

public class Produto {

    private String nome;
    private int quantidade;

    public Produto() {
    }

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String adicionarEstoque(int quantidade){
        if(quantidade <= 0){
            return "Quantidade invalida";
        }
        this.quantidade += quantidade;
        return "Estoque atualizado com sucesso.";
    }

    public String removerEstoque(int quantidade){
        if(quantidade <= 0){
            return "Quantidade precisa ser maior que 0";
        }
        if(quantidade > this.quantidade){
            return "Estoque insuficiente";
        }
        this.quantidade -= quantidade;
        return  "Removido com sucesso";
    }

    public int consultarEstoque(){
        return quantidade;
    }

    public String exibirProduto(){
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ");
        sb.append(nome);
        sb.append("\nQuantidade: ");
        sb.append(quantidade);

        return sb.toString();
    }
}
