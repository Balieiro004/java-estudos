package entities;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private List<Colaborador> colaboradores = new ArrayList<>();
    private List<Equipamento> equipamentos = new ArrayList<>();

    public Estoque() {
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public String cadastrarEquipamento(Equipamento equipamento) {

        if(equipamento.getNome() == null || equipamento.getNome().isBlank()){
            return "Nome de equipamento invalido";
        }
        if (equipamento.getPatrimonio() == null || equipamento.getPatrimonio().isBlank()){
            return "Patrimonio invalido";
        }
        if (equipamento.getServiceTag() == null || equipamento.getServiceTag().isBlank()){
            return "Service Tag invalida";
        }
        equipamentos.add(equipamento);
        return "Equipamento cadastrado com sucesso!";
    }

    public String cadastrarColaborador(Colaborador colaborador) {
        if(colaborador.getNome() == null || colaborador.getNome().isBlank()){
            return "Nome de colaborador invalido";
        }
        if (colaborador.getEmail() == null || colaborador.getEmail().isBlank()){
            return "Email do colaborador invalido";
        }
        colaboradores.add(colaborador);
        return "Colaborador cadastrado com sucesso!";
    }

    public String emprestarEquipamento(int idEquipamento, int idColaborador) {

        Colaborador colaboradorEncontrado = buscarColaboradorPorId(idColaborador);

        if(colaboradorEncontrado == null) return "Colaborador inexistente";

        Equipamento equipamentoEncontrada = buscarEquipamentoPorId(idEquipamento);

        if(equipamentoEncontrada == null) return "Equipamento não encontrado";

        if (equipamentoEncontrada.isStatus())return "Livro ja emprestado";

        equipamentoEncontrada.emprestarEquipamento(colaboradorEncontrado);
        return "Equipamento emprestado com sucesso!" + colaboradorEncontrado.getNome();

    }

    public String devolverEquipamento(int idEquipamento, int idColaborador) {
        Colaborador colaboradorEncontrado = buscarColaboradorPorId(idColaborador);

        if(colaboradorEncontrado == null) return "Colaborador inexistente";

        Equipamento equipamentoEncontrada = buscarEquipamentoPorId(idEquipamento);

        if(equipamentoEncontrada == null) return "Equipamento não encontrado";

        if(!equipamentoEncontrada.isStatus())return "Equipamento não está emprestado";

        if(equipamentoEncontrada.getColaboradorComEquipamento().getId() != idColaborador)return "Esse colaborador não está com o equipamento";

        equipamentoEncontrada.devolverEquipamento();
        return "Equipamento devolvido com sucesso!" + colaboradorEncontrado.getNome();
    }

    public List<Equipamento> listarEquipamentosEmprestados(){

        List<Equipamento> equipamentosEmprestados = new ArrayList<>();

        for(Equipamento equipamento : equipamentos){
            if(equipamento.isStatus()) equipamentosEmprestados.add(equipamento);
        }
        return equipamentosEmprestados;
    }
    private Colaborador buscarColaboradorPorId(int idColaborador) {
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getId() == idColaborador) return colaborador;
        }
        return null;
    }

    private Equipamento buscarEquipamentoPorId(int idEquipamento) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == idEquipamento) return equipamento;
        }
        return null;
    }
}
