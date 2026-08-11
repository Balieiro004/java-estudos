package services;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConsultaService {

    private PacienteService pacienteService;
    private MedicoService medicoService;
    private List<Consulta> consultas = new ArrayList<>();


    public ConsultaService(PacienteService pacienteService, MedicoService medicoService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        carregarConsultasMock();
    }

    public List<Consulta> listarConsultas() {return Collections.unmodifiableList(consultas);}

    public Consulta criarConsulta(int idPaciente, int idMedico, LocalDate dataConsulta, LocalTime horaConsulta) {

        Paciente paciente = validarSePacienteExiste(idPaciente);
        Medico medico = validarSeMedicoExiste(idMedico);

        validarDataEHoraDaConsulta(dataConsulta, horaConsulta);

        validarHorarioMedico(idMedico, dataConsulta, horaConsulta, -1);
        validarHorarioPaciente(idPaciente, dataConsulta, horaConsulta, -1);

        Consulta consulta = new Consulta(paciente, medico, dataConsulta, horaConsulta);
        consultas.add(consulta);
        return consulta;
    }

    public Consulta reagendarConsulta(int idConsulta,LocalDate novaData, LocalTime novaHora){
        Consulta consulta = buscarConsultaPorId(idConsulta);

        if(consulta == null){
            throw new IllegalArgumentException("Consulta não encontrada");
        }

        validarDataEHoraDaConsulta(novaData, novaHora);

        int idPaciente = consulta.getPaciente().getId();
        int idMedico = consulta.getMedico().getId();

        validarHorarioMedico(idMedico, novaData, novaHora, idConsulta);
        validarHorarioPaciente(idPaciente, novaData, novaHora, idConsulta);

        consulta.setDataConsulta(novaData);
        consulta.setHoraConsulta(novaHora);

        return consulta;
    }

    public List<Consulta> consultarAgendaMedico(int idMedico, LocalDate data){
        validarSeMedicoExiste(idMedico);

        List<Consulta> agenda = new ArrayList<>();

        for(Consulta consulta : consultas){
            if(consulta.getMedico().getId() == idMedico && consulta.getDataConsulta().equals(data)){
                agenda.add(consulta);
            }
        }

        /*Organiza as consulta por horário*/
        agenda.sort(Comparator.comparing(Consulta::getDataConsulta));

        return agenda;
    }

    public List<Consulta> consultarAgendaPaciente(int idPaciente){
        validarSePacienteExiste(idPaciente);

        List<Consulta> agenda = new ArrayList<>();

        for(Consulta consulta : consultas){
            if(consulta.getPaciente().getId() == idPaciente){
                agenda.add(consulta);
            }
        }
        agenda.sort(Comparator.comparing(Consulta::getDataConsulta).thenComparing(Consulta::getHoraConsulta));
        return agenda;
    }

    public Consulta buscarConsultaPorId(int idConsulta){
        for(Consulta consulta : consultas){
            if(consulta.getId() == idConsulta){
                return consulta;
            }
        }
        return null;
    }

    private Paciente validarSePacienteExiste(int idPaciente) {
        Paciente paciente = pacienteService.buscarPacientePorId(idPaciente);

        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado!");
        }
        return paciente;
    }

    private Medico validarSeMedicoExiste(int idMedico) {
        Medico medico = medicoService.buscarMedicoPorId(idMedico);
        if (medico == null) {
            throw new IllegalArgumentException("Médico não encontrado!");
        }
        return medico;
    }

    private void validarDataEHoraDaConsulta(LocalDate dataConsulta, LocalTime horaConsulta) {

        if (dataConsulta == null) {
            throw new IllegalArgumentException("A data da consulta precisa ser preenchido!");
        }
        if (horaConsulta == null) {
            throw new IllegalArgumentException("O horário da consulta precisa ser preenchido!");
        }

        if (dataConsulta.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da consulta não pode ser anterior à data atual!");
        }
    }

    private void validarHorarioMedico(int idMedico, LocalDate dataConsulta, LocalTime horaConsulta, int idConsultaIgnorar){

        for (Consulta consulta : consultas) {
            if (consulta.getId() == idConsultaIgnorar){
                continue;
            }
            if(consulta.getMedico().getId() == idMedico && consulta.getDataConsulta().equals(dataConsulta) && consulta.getHoraConsulta().equals(horaConsulta)){

                throw new IllegalArgumentException("O médico ja possui uma consulta nesse dia e horário!");
            }
        }
    }

    private void validarHorarioPaciente(int idPaciente, LocalDate dataConsulta, LocalTime horaConsulta, int idConsultaIgnorar){
        for (Consulta consulta : consultas) {
            if(consulta.getId() == idConsultaIgnorar){
                continue;
            }

            if (consulta.getPaciente().getId() == idPaciente
                    && consulta.getDataConsulta().equals(dataConsulta)
                    && consulta.getHoraConsulta().equals(horaConsulta)) {
                throw new IllegalArgumentException("O Paciente ja possui consulta nesse dia e horário!");
            }
        }
    }

    private void carregarConsultasMock() {

        LocalDate hoje = LocalDate.now();

        criarConsulta(
                1,
                7,
                hoje.plusDays(1),
                LocalTime.of(9, 0)
        );

        criarConsulta(
                2,
                8,
                hoje.plusDays(1),
                LocalTime.of(10, 0)
        );

        criarConsulta(
                3,
                9,
                hoje.plusDays(1),
                LocalTime.of(14, 0)
        );

        criarConsulta(
                4,
                10,
                hoje.plusDays(1),
                LocalTime.of(15, 30)
        );

        criarConsulta(
                5,
                11,
                hoje.plusDays(2),
                LocalTime.of(8, 30)
        );

        criarConsulta(
                6,
                12,
                hoje.plusDays(2),
                LocalTime.of(10, 0)
        );

        criarConsulta(
                1,
                7,
                hoje.plusDays(2),
                LocalTime.of(14, 0)
        );

        criarConsulta(
                2,
                8,
                hoje.plusDays(3),
                LocalTime.of(9, 30)
        );

        criarConsulta(
                3,
                9,
                hoje.plusDays(3),
                LocalTime.of(11, 0)
        );

        criarConsulta(
                4,
                10,
                hoje.plusDays(4),
                LocalTime.of(13, 30)
        );

        criarConsulta(
                5,
                12,
                hoje.plusDays(5),
                LocalTime.of(16, 0)
        );
    }
}
