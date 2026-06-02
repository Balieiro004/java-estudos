import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Valor para saque R$ ");
        int saque = sc.nextInt();

        int notas100 = saque / 100;
        int resto = saque % 100;
        System.out.println(notas100 + " notas de 100");

        int notas50 = resto / 50;
        resto = resto % 50;
        System.out.println(notas50 + " notas de 50");

        int notas20 = resto / 20;
        resto = resto % 20;
        System.out.println(notas20  + " notas de 20");

        int notas10 = resto / 10;
        resto = resto % 10;
        System.out.println(notas10  + " notas de 10");

        int notas5 = resto / 5;
        resto = resto % 5;
        System.out.println(notas5  + " notas de 5");

        int notas1 = resto;
        resto = resto % 1;
        System.out.println(notas1  + " notas de 1");
    }
}