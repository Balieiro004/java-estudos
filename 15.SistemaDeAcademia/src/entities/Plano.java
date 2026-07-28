package entities;

public class Plano {

    private static int contador;
    private int id;
    private String nome;
    private int duracaoMeses;
    private double valorMensal;

    public Plano(String nome, int duracaoMeses, double valorMensal) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.duracaoMeses = duracaoMeses;
        this.valorMensal = valorMensal;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        return "========Plano========" +
                "\nId: " + id +
                "\nNome: " + nome +
                "\nDuracao Meses: " + duracaoMeses +
                "\nValor Mensal: R$ " + valorMensal;
    }
}
