import entities.Colaborador;
import entities.Pessoa;
import entities.Tecnico;
import entities.enuns.Especialidade;

public class Main {
    public static void main(String[] args) {

        Colaborador colaborador = new Colaborador("Joao", "joao@email.com");

        System.out.println(colaborador);

        System.out.println("======================");

        Tecnico tecnico = new Tecnico("Maria", "Maria@email.com", Especialidade.INFRAESTRUTURA);

        System.out.println(tecnico);
    }
}