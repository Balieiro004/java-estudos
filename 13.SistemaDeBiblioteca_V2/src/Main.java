import system.SystemaBiblioteca;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SystemaBiblioteca sistemaBiblioteca = new SystemaBiblioteca();

        Menu menu = new Menu(sistemaBiblioteca, sc);

        menu.iniciar();

        sc.close();
    }
}