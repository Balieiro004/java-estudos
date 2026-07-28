package ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    Scanner sc;
    DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    public void iniciar(){

        System.out.println("========Iniciando Menu========");


    }
}
