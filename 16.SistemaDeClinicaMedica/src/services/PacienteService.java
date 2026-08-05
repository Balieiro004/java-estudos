package services;

import entities.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacienteService {

    private List<Paciente> pacientes = new ArrayList<>();

    public PacienteService() { carregarPacientesMock();}

    public List<Paciente> listarPacientes() {
        return Collections.unmodifiableList(pacientes);
    }

    public Paciente cadastrarPaciente(String nome, String cpf, String convenio) {

        validarNome(nome);
        validarCPF(cpf);
        validarConvenio(convenio);
        validarSePacienteExiste(cpf);

        Paciente paciente = new Paciente(nome, cpf, convenio);
        pacientes.add(paciente);
        return paciente;
    }

    public Paciente buscarPacientePorId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    public void ecluirPaciente(int id) {
        Paciente paciente = buscarPacientePorId(id);

        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        pacientes.remove(paciente);
    }

    private void validarNome(String nome){
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome precisa ser preenchido.");
        }
    }

    private void validarCPF(String cpf){
        if(cpf == null || cpf.isEmpty()){
            throw new IllegalArgumentException("CPF precisa ser preenchido.");
        }

        if(cpf.length() != 11){
            throw new IllegalArgumentException("CPF precisa contar somente 11 caracteres.");
        }
    }

    private void validarConvenio(String convenio){
        if(convenio == null || convenio.isEmpty()){
            throw new IllegalArgumentException("Convenio precisa ser preenchido.");
        }
    }

    private void validarSePacienteExiste(String cpf){
        Paciente paciente = buscarPacientePorCpf(cpf);

        if(paciente != null){
            throw new IllegalArgumentException("Ja existe um paciente com esse CPF.");
        }
    }

    private Paciente buscarPacientePorCpf(String cpf){
        for(Paciente paciente : pacientes){
            if (paciente.getCpf().equals(cpf)){
                return paciente;
            }
        }
        return null;
    }

    private void carregarPacientesMock() {
        cadastrarPaciente(
                "João Silva",
                "12345678901",
                "Unimed"
        );

        cadastrarPaciente(
                "Maria Oliveira",
                "23456789012",
                "Bradesco Saúde"
        );

        cadastrarPaciente(
                "Pedro Santos",
                "34567890123",
                "SulAmérica"
        );

        cadastrarPaciente(
                "Ana Costa",
                "45678901234",
                "Amil"
        );

        cadastrarPaciente(
                "Carlos Pereira",
                "56789012345",
                "NotreDame Intermédica"
        );

        cadastrarPaciente(
                "Fernanda Lima",
                "67890123456",
                "Particular"
        );
    }
}
