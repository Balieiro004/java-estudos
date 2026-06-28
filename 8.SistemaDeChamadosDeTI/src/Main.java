import entities.Chamado;
import entities.Colaborador;
import entities.SistemaChamados;
import entities.Tecnico;
import entities.enuns.Especialidade;

public class Main {
    public static void main(String[] args) {

        SistemaChamados sistemaChamados = new SistemaChamados();


        System.out.println(sistemaChamados.cadastrarColaborador("João", "joao@email.com"));
        System.out.println(sistemaChamados.cadastrarColaborador("Maria", "Maria@email.com"));
        System.out.println(sistemaChamados.cadastrarTecnico("Carol", "Carol@email.com", Especialidade.INFRAESTRUTURA));
        System.out.println(sistemaChamados.cadastrarTecnico("Anderson", "Anderson@email.com", Especialidade.SISTEMAS));


        Colaborador colaborador = sistemaChamados.buscarColaboradorPorId(1);
        Tecnico tecnico =  sistemaChamados.buscarTecnicoPorId(3);
        sistemaChamados.cadastrarChamado("Teste", "Teste para Abertura",colaborador, tecnico);

        var listaColaboradores = sistemaChamados.getColaboradores();
        var listaTecnicos = sistemaChamados.getTecnicos();
        var listaChamados = sistemaChamados.getChamados();

        System.out.println("Lista de colaboradores:");
        for(Colaborador listaColaboradorAtualizada : listaColaboradores){
            System.out.println(listaColaboradorAtualizada.toString());
        }
        System.out.println("==========");

        System.out.println("Lista de tecnicos:");
        for(Tecnico listaTecnicoAtualizada : listaTecnicos){
            System.out.println(listaTecnicoAtualizada.toString());
        }

        System.out.println("==========");
        System.out.println("\n\n\n\n Lista de Chamados:");
        for(Chamado listaChamadoAtualizada : listaChamados){
            System.out.println(listaChamadoAtualizada.toString());
        }
    }
}