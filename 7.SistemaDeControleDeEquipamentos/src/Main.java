import entities.Colaborador;
import entities.Equipamento;
import entities.Estoque;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Estoque estoque = new Estoque();
        Colaborador colaborador = new Colaborador("Anderson", "anderson@email.com");
        Colaborador colaborador2 = new Colaborador("Anderson", "anderson@email.com");

        estoque.cadastrarColaborador(colaborador);
        estoque.cadastrarColaborador(colaborador2);
        System.out.println("-------");
        List<Colaborador> listaColaborador = estoque.getColaboradores();
        if(listaColaborador.isEmpty()){
            System.out.println("Lista vazia.");
        }
        for(Colaborador c : listaColaborador){
            System.out.println(c);
        }
        System.out.println("-------");

        Equipamento equipamento = new Equipamento();

        for (int i = 0; i < 5; i++) {
            equipamento = new Equipamento("Notebook", "001", "4SRTRB");
            System.out.println(estoque.cadastrarEquipamento(equipamento));
        }
        equipamento = new Equipamento();
        System.out.println(estoque.cadastrarEquipamento(equipamento));

        List <Equipamento> listaEquipamento = estoque.getEquipamentos();
        if(listaEquipamento.isEmpty()){
            System.out.println("Lista vazia.");
        }
        for(Equipamento e : listaEquipamento){
            System.out.println("----------------");
            System.out.println(e);
        }

        System.out.println("======================");

        System.out.println(estoque.emprestarEquipamento(1,1));
        System.out.println("======================");
        System.out.println("======================");

        System.out.println(estoque.devolverEquipamento(2,2));

        List<Equipamento> equipamentosEmprestados = estoque.listarEquipamentosEmprestados();
        if(equipamentosEmprestados.isEmpty()) System.out.println("Nenhum Equipamento Emprestado Encontrado.");
        else for(Equipamento equipamento1 : equipamentosEmprestados){
            System.out.println(equipamento1);
        }

    }
}