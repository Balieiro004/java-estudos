package util;

public class DadosPessoais {

    private String nome;
    private float peso;
    private float altura;

    public DadosPessoais() {
    }

    public DadosPessoais(String nome, float peso, float altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float calculoIMC() {
        return this.peso / (this.altura * this.altura);
    }

    public String classificacaoIMC(){
        float imc = this.calculoIMC();
        if(imc < 18.5){
            return "Abaixo do peso";
        }
        else if(imc <= 24.9){
            return "Peso normal";
        }
        else if(imc <= 29.9){
            return "Sobrepeso";
        }
        else if(imc <= 39.9){
            return "Obesidade";
        }
        else{
            return "Obesidade grave";
        }
    }
}
