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

    public String cadastrarEquipamento(String nome, String patrimonio, String serviceTag) {

        if(nome == null || nome.isBlank()){
            return "Nome de equipamento invalido";
        }
        if (patrimonio == null || patrimonio.isBlank()){
            return "Patrimonio invalido";
        }
        if (serviceTag == null || serviceTag.isBlank()){
            return "Service Tag invalida";
        }
        equipamentos.add(new Equipamento(nome, patrimonio, serviceTag));
        return "Equipamento cadastrado com sucesso!";
    }

    public String cadastrarColaborador(String nome, String email) {
        if(nome == null || nome.isBlank()){
            return "Nome de colaborador invalido";
        }
        if (email == null || email.isBlank()){
            return "Email do colaborador invalido";
        }
        colaboradores.add(new Colaborador(nome, email));
        return "Colaborador cadastrado com sucesso!";
    }

    public String emprestarEquipamento(int idColaborador, int idEquipamento) {

        Colaborador colaboradorEncontrado = buscarColaboradorPorId(idColaborador);

        if(colaboradorEncontrado == null) return "Colaborador inexistente";

        Equipamento equipamentoEncontrada = buscarEquipamentoPorId(idEquipamento);

        if(equipamentoEncontrada == null) return "Equipamento não encontrado";

        if (equipamentoEncontrada.isStatus())return "Equipamento ja emprestado";

        equipamentoEncontrada.emprestarEquipamento(colaboradorEncontrado);
        return "Equipamento emprestado com sucesso!" + colaboradorEncontrado.getNome();

    }

    public String devolverEquipamento(int idColaborador, int idEquipamento) {
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
