import util.DadosPessoais;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        var pessoa = new DadosPessoais("Anderson", 73.4f, 1.80f);

        var pessoa2 = new DadosPessoais();

        System.out.println("Informe os dados");
        System.out.print("Nome: ");
        pessoa2.setNome(sc.next());
        System.out.print("Altura: ");
        pessoa2.setAltura(sc.nextFloat());
        System.out.print("Peso: ");
        pessoa2.setPeso(sc.nextFloat());


        System.out.println("=================");
        System.out.printf("IMC pessoa: %.2f%n", pessoa.calculoIMC());
        System.out.println("Classificação: " + pessoa.classificacaoIMC());

        System.out.println("=================");
        System.out.printf("IMC pessoa: %.2f%n", pessoa2.calculoIMC());
        System.out.println("Classificação: " + pessoa2.classificacaoIMC());
    }
}