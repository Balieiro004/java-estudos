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
}
