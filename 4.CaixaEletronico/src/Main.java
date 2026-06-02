import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Valor para saque R$ ");
        int saque = sc.nextInt();

        int notas100 = saque / 100;
        int resto = saque % 100;
        imprimirNotas(notas100, 100);

        int notas50 = resto / 50;
        resto = resto % 50;
        imprimirNotas(notas50, 50);

        int notas20 = resto / 20;
        resto = resto % 20;
        imprimirNotas(notas20, 20);

        int notas10 = resto / 10;
        resto = resto % 10;
        imprimirNotas(notas10, 10);

        int notas5 = resto / 5;
        resto = resto % 5;
        imprimirNotas(notas5, 5);

        int notas1 = resto;
        imprimirNotas(notas1, 1);
    }

    public static void imprimirNotas(int quantidade, int valorNota){
        if(quantidade <= 0){
            return;
        }
        if(quantidade == 1)
            System.out.println("1 nota de " +valorNota);
        else
            System.out.println(quantidade + " notas de " + valorNota);
    }
}