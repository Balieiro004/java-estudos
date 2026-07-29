import system.SistemaAcademia;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaAcademia sistemaAcademia = new SistemaAcademia();
        Menu menu = new Menu(sistemaAcademia, sc);

        menu.iniciar();
        sc.close();
    }
}