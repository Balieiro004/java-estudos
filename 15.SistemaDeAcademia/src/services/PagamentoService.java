package services;

import entities.Matricula;
import entities.Pagamento;
import entities.Plano;
import enums.FormaPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PagamentoService {

    private MatriculaService matriculaService;

    public PagamentoService(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    private List<Pagamento> pagamentos = new ArrayList<>();

    public List<Pagamento> listPagamentos() {return Collections.unmodifiableList(pagamentos);}

    public Pagamento registrarPagamento(int idMatricula, LocalDate dataPagamento, double valorPagamento, FormaPagamento formaPagamento) {

        Matricula matricula = validarMatricula(idMatricula);
        validarDataPagamento(dataPagamento);
        validarPagamento(valorPagamento);
        validarFormaPagamento(formaPagamento);

        Pagamento pagamento = new Pagamento(matricula, dataPagamento, valorPagamento, formaPagamento);
        pagamentos.add(pagamento);
        return pagamento;
    }

    public Pagamento buscarPagamentoPorId(int id){
        for(Pagamento pagamento : pagamentos){
            if(pagamento.getId() == id){
                return pagamento;
            }
        }
        return null;
    }

    private Matricula validarMatricula(int idMatricula) {
        Matricula matricula = matriculaService.buscarMatriculaPorId(idMatricula);

        if (matricula == null) {
            throw new IllegalArgumentException("Matricula não encotrada.");
        }
        return matricula;
    }

    private void validarDataPagamento(LocalDate dataPagamento) {

        if(dataPagamento == null) {
            throw new IllegalArgumentException("Data não pode ser vazia");
        }
        if (dataPagamento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de pagamento não pode ser anterior a data de hoje.");
        }
    }

    private void validarPagamento(double valorPagamento) {
        if(valorPagamento <= 0) {
            throw new IllegalArgumentException("Valor não pode ser 0 ou negativo.");
        }
    }

    private void validarFormaPagamento(FormaPagamento formaPagamento) {
        if(formaPagamento == null) {
            throw new IllegalArgumentException("Forma de Pagamento inválida.");
        }
    }
}
