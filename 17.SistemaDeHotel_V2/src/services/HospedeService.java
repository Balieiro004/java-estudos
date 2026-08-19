package services;

import entities.Hospede;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HospedeService {

    private List<Hospede> hospedes = new ArrayList<>();

    public HospedeService() {carregarHospedesMock();}

    public List<Hospede> listarHospedes() {return Collections.unmodifiableList(hospedes);}

    public Hospede cadastrarHospede(String nome,String cpf, String telefone, String email){

        validarNome(nome);
        validarCpf(cpf);
        validarTelefone(telefone);
        validarEmail(email);

        Hospede hospede = new Hospede(nome, cpf, telefone, email);
        hospedes.add(hospede);
        return hospede;
    }

    public Hospede buscarHospedePorId(int id){
        for (Hospede hospede : hospedes) {
            if (hospede.getId() == id){
                return hospede;
            }
        }
        return null;
    }

    public void excluirHospede(int id){
        Hospede hospede = buscarHospedePorId(id);
        if (hospede == null){
            throw new IllegalArgumentException("Nenhum hospede encontrado");
        }
        hospedes.remove(hospede);
    }

    private void validarNome(String nome) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome precisa ser preenchido.");
        }

        if(nome.length() < 3){
            throw new IllegalArgumentException("Nome precisa ter no minimo 3 caracteres.");
        }
    }

    private void validarCpf(String cpf) {
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF precisa ser preenchido.");
        }
        if(cpf.length() != 11){
            throw new IllegalArgumentException("CPF precisa ter 11 caracteres.");
        }

        if(buscarHospepdePorCpf(cpf) != null){
            throw new IllegalArgumentException("Já existe um hóspde com esse CPF.");
        }

    }
    private void validarTelefone(String telefone) {
        if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("Telefone precisa ser preenchido.");
        }
    }
    private void validarEmail(String email) {
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email precisa ser preenchido.");
        }

        if(buscarHospedePorEmail(email) != null){
            throw new IllegalArgumentException("Já existe um hospede com esse email.");
        }
    }

    private Hospede buscarHospedePorEmail(String email){
        for (Hospede hospede : hospedes) {
            if (hospede.getEmail().equals(email)) {
                return hospede;
            }
        }
        return null;
    }

    private Hospede buscarHospepdePorCpf(String cpf){
        for (Hospede hospede : hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }

    private void carregarHospedesMock() {

        cadastrarHospede(
                "João Silva",
                "12345678901",
                "11999990001",
                "joao.silva@email.com"
        );

        cadastrarHospede(
                "Maria Oliveira",
                "23456789012",
                "11999990002",
                "maria.oliveira@email.com"
        );

        cadastrarHospede(
                "Pedro Santos",
                "34567890123",
                "11999990003",
                "pedro.santos@email.com"
        );

        cadastrarHospede(
                "Ana Costa",
                "45678901234",
                "11999990004",
                "ana.costa@email.com"
        );

        cadastrarHospede(
                "Carlos Pereira",
                "56789012345",
                "11999990005",
                "carlos.pereira@email.com"
        );

        cadastrarHospede(
                "Fernanda Lima",
                "67890123456",
                "11999990006",
                "fernanda.lima@email.com"
        );
    }
}
