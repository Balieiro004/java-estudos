package services;

import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmprestimoService {

    private UsuarioService usuarioService;
    private LivroService livroService;

    public EmprestimoService(UsuarioService usuarioService, LivroService livroService) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;

        carregarEmprestimosMock();
    }

    private List<Emprestimo> emprestimos = new ArrayList<>();

    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    public Emprestimo criarEmprestimo(int idUsuario, int idLivro, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao){

        Usuario usuario = validarUsuario(idUsuario);

        Livro livro = validarLivro(idLivro);

        validarDatas(dataEmprestimo, dataPrevistaDevolucao);

        if(!livro.isDisponivel()){throw new IllegalArgumentException("Livro indiponível.");}

        if (usuario.possuiEmprestimoAtrasado()) {throw new IllegalArgumentException("Usuário possui empréstimos em atraso.");}

        livro.emprestar();

        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataPrevistaDevolucao);
        usuario.adicionarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public Emprestimo devolverLivro(int idEmprestimo, LocalDate dataDevolucao){

        Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
        if(emprestimo == null){
            throw new IllegalArgumentException("Empréstimo não encontrado.");
        }

        if (dataDevolucao == null) {
            throw new IllegalArgumentException("Data de devolução é obrigatória.");
        }

        if (emprestimo.getDataDevolucao() != null) {
            throw new IllegalArgumentException("Este livro já foi devolvido.");
        }

        if (dataDevolucao.isBefore(emprestimo.getDataEmprestimo())) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data do empréstimo.");
        }

        emprestimo.devolver(dataDevolucao);
        emprestimo.getLivro().devolver();
        return emprestimo;
    }

    private Usuario validarUsuario(int idUsuario){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        if(usuario == null){
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        return usuario;
    }

    private Livro validarLivro(int idLivro){
        Livro livro = livroService.buscarLivroPorId(idLivro);
        if(livro == null){
            throw new IllegalArgumentException("Livro não encontrado");
        }
        return livro;
    }

    private void validarDatas(LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao){
        if(dataEmprestimo == null){
            throw new IllegalArgumentException("Data é obrigatória");
        }

        if (dataEmprestimo.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data do empréstimo não pode ser anterior à data de hoje.");
        }

        if (!dataPrevistaDevolucao.isAfter(dataEmprestimo)) {
            throw new IllegalArgumentException("A data prevista de devolução deve ser posterior à data do empréstimo.");
        }
    }

    private Emprestimo buscarEmprestimoPorId(int idEmprestimo) {
        for(Emprestimo emprestimo : emprestimos) {
            if(emprestimo.getId() == idEmprestimo){
                return emprestimo;
            }
        }
        return null;
    }

    public Emprestimo renovarEmprestimo(int idEmprestimo, LocalDate novaDataPrevistaDevolucao){
        Emprestimo  emprestimo = buscarEmprestimoPorId(idEmprestimo);

        if(emprestimo == null){
            throw new IllegalArgumentException("Emprestimo não encontrado");
        }

        if(emprestimo.getDataDevolucao() != null) {
            throw new IllegalArgumentException("Este empréstimo ja foi encerrado.");
        }

        emprestimo.renovar(novaDataPrevistaDevolucao);
        return emprestimo;
    }

    public void carregarEmprestimosMock() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        criarEmprestimo(
                1,
                1,
                LocalDate.parse("25/08/2026", formatter),
                LocalDate.parse("30/08/2026", formatter)
        );

        criarEmprestimo(
                2,
                2,
                LocalDate.parse("30/08/2026", formatter),
                LocalDate.parse("26/09/2026", formatter)
        );

        criarEmprestimo(
                3,
                3,
                LocalDate.parse("20/08/2026", formatter),
                LocalDate.parse("27/08/2026", formatter)
        );

        criarEmprestimo(
                4,
                4,
                LocalDate.parse("21/08/2026", formatter),
                LocalDate.parse("28/08/2026", formatter)
        );

        criarEmprestimo(
                5,
                5,
                LocalDate.parse("22/08/2026", formatter),
                LocalDate.parse("29/08/2026", formatter)
        );

        criarEmprestimo(
                6,
                6,
                LocalDate.parse("23/08/2026", formatter),
                LocalDate.parse("30/08/2026", formatter)
        );
    }

}
