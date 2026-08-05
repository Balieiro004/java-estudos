import system.SistemaClinicaMedica;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaClinicaMedica sistemaClinicaMedica = new SistemaClinicaMedica();

        Menu menu = new Menu(sistemaClinicaMedica, sc);
        menu.iniciar();

        sc.close();
    }
}