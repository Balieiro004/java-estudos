import system.SistemaHotel;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaHotel sistemaHotel = new SistemaHotel();

        Menu menu = new Menu(sistemaHotel, sc);

        menu.iniciar();
        sc.close();
    }
}