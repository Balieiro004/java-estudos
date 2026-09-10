import system.SistemaDeBibliotevaV3;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaDeBibliotevaV3 sistema = new SistemaDeBibliotevaV3();

        Menu menu = new Menu(sistema, sc);

        menu.iniciar();
        sc.close();
    }
}