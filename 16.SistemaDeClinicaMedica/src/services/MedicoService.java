package services;

import entities.Medico;
import enums.Especialidades;
import utils.ValidacaoUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicoService {


    private List<Medico>  medicos = new ArrayList<>();

    public MedicoService() {carregarMedicosMock();}

    public List<Medico> listarMedicos() {return Collections.unmodifiableList(medicos);}

    public Medico cadastrarMedico(String nome, String cpf, String crm, Especialidades especialidade){

        ValidacaoUtil.validarNome(nome);
        ValidacaoUtil.validarCPF(cpf);
        validarCrm(crm);
        validarEspecialidade(especialidade);
        validarSeMedicoExiste(cpf);

        Medico medico = new Medico(nome,cpf,crm,especialidade);
        medicos.add(medico);
        return medico;
    }

    private Medico buscarMedicoPorCpf(String cpf){
        for(Medico medico : medicos){
            if(medico.getCpf().equals(cpf)){
                return medico;
            }
        }
        return null;
    }
    public Medico buscarMedicoPorId(int id){
        for(Medico medico : medicos){
            if (id==medico.getId()){
                return medico;
            }
        }
        return null;
    }

    public void excluirMedicoPorId(int id){
        Medico  medico = buscarMedicoPorId(id);

        if(medico == null){
            throw new IllegalArgumentException("Médico não encontrado com esse ID");
        }
        medicos.remove(medico);
    }

    private  void validarEspecialidade(Especialidades especialidade){
        if(especialidade == null){
            throw new IllegalArgumentException("Especialidade precisa ser preenchido.");
        }
    }

    private  void validarCrm(String crm){
        if(crm == null || crm.isEmpty()){
            throw new IllegalArgumentException("Crm precisa ser preenchido.");
        }
    }

    private void validarSeMedicoExiste(String cpf){
        Medico medico = buscarMedicoPorCpf(cpf);

        if(medico != null){
            throw new IllegalArgumentException("Já existe um medico com esse CPF.");
        }
    }

    public void carregarMedicosMock() {

        cadastrarMedico(
                "Carlos Oliveira",
                "12345678901",
                "CRM-SP-123456",
                Especialidades.CARDIOLOGIA
        );

        cadastrarMedico(
                "Mariana Santos",
                "23456789012",
                "CRM-SP-234567",
                Especialidades.PEDIATRIA
        );

        cadastrarMedico(
                "João Pereira",
                "34567890123",
                "CRM-SP-345678",
                Especialidades.ORTOPEDIA
        );

        cadastrarMedico(
                "Ana Costa",
                "45678901234",
                "CRM-SP-456789",
                Especialidades.DERMATOLOGIA
        );

        cadastrarMedico(
                "Pedro Almeida",
                "56789012345",
                "CRM-SP-567890",
                Especialidades.NEUROLOGIA
        );

        cadastrarMedico(
                "Fernanda Lima",
                "67890123456",
                "CRM-SP-678901",
                Especialidades.GINECOLOGIA
        );
    }
}
