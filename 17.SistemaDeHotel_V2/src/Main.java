import system.SistemaHotel_V2;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaHotel_V2 sistema = new SistemaHotel_V2();

        Menu menu = new Menu(sistema, sc);
        menu.iniciar();

        sc.close();

    }
}