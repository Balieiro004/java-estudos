import system.SistemaBanco;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SistemaBanco sistemaBanco = new SistemaBanco();

        Menu menu = new Menu(sistemaBanco, sc);


        menu.iniciar();

        sc.close();
    }
}