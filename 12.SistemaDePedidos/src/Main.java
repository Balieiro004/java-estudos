import system.SistemaPedidos;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SistemaPedidos sistemaPedidos = new SistemaPedidos();

        Menu menu = new Menu(sistemaPedidos, sc);

        menu.iniciar();
        sc.close();
    }
}