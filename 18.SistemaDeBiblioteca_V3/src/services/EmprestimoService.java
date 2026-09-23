package services;

import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;
import enums.StatusEmprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmprestimoService {

    private UsuarioService usuarioService;
    private LivroService livroService;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public EmprestimoService(UsuarioService usuarioService,  LivroService livroService) {
        this.usuarioService = usuarioService;
        this.livroService = livroService;
        carregarEmprestimosMock();
    }

    public List<Emprestimo> listarEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    public Emprestimo cadastrarEmprestimo(int idUsuario, int idLivro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista){
        Usuario usuario = validarSeUsuarioExiste(idUsuario);
        Livro livro = validarSeLivroExiste(idLivro);
        validarDataEmprestimo(dataEmprestimo);
        validarDataDevolucaoPrevista(dataEmprestimo, dataDevolucaoPrevista);
        mudarLivroParaEmprestado(livro);

        Emprestimo emprestimo = new Emprestimo(usuario,livro,dataEmprestimo,dataDevolucaoPrevista);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public Optional<Emprestimo> buscarEmprestimoPorId(int idEmprestimo){
        return emprestimos.stream().filter(emprestimo ->  emprestimo.getId() == idEmprestimo).findFirst();
    }

    public void devolverEmprestimo(int idEmprestimo){
       Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo)
               .orElseThrow(() -> new IllegalArgumentException("Emprésimo não encontrado."));

        emprestimo.devolvido();
       emprestimo.getLivro().disponivel();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimos.stream().filter(emprestimo -> emprestimo.getStatusEmprestimo().equals(StatusEmprestimo.ATIVO)).toList();
    }

    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimos.stream().filter(emprestimo -> emprestimo.getStatusEmprestimo().equals(StatusEmprestimo.ATRASADO)).toList();
    }

    public List<Emprestimo> listarEmprestimosPorUsuario(int idUsuario) {
        return emprestimos.stream().filter(emprestimo -> emprestimo.getUsuario().getId() == idUsuario).toList();
    }

    private Usuario validarSeUsuarioExiste(int idUsuario){
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        return usuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private Livro validarSeLivroExiste(int idLivro){
        Optional<Livro> livro = livroService.buscarLivroPorId(idLivro);
        return livro.orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    private void validarDataEmprestimo(LocalDate dataEmprestimo){
        if(dataEmprestimo.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data não pode ser anterior a data de Hoje.");
        }
    }

    private void validarDataDevolucaoPrevista(LocalDate dataEmprestimo,LocalDate dataDevolucaoPrevista) {
        if (dataDevolucaoPrevista.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data do empréstimo.");
        }
    }

    private void mudarLivroParaEmprestado(Livro livro){
        livro.emprestado();
    }

    public void carregarEmprestimosMock() {

        // Usuário 1
        cadastrarEmprestimo(
                1,
                1,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 8)
        );

        cadastrarEmprestimo(
                1,
                2,
                LocalDate.of(2026, 10, 2),
                LocalDate.of(2026, 10, 9)
        );

        cadastrarEmprestimo(
                1,
                3,
                LocalDate.of(2026, 10, 5),
                LocalDate.of(2026, 10, 12)
        );

        // Usuário 2
        cadastrarEmprestimo(
                2,
                4,
                LocalDate.of(2026, 10, 3),
                LocalDate.of(2026, 10, 10)
        );

        cadastrarEmprestimo(
                2,
                5,
                LocalDate.of(2026, 10, 6),
                LocalDate.of(2026, 10, 13)
        );

        // Usuário 3
        cadastrarEmprestimo(
                3,
                6,
                LocalDate.of(2026, 10, 5),
                LocalDate.of(2026, 10, 12)
        );

        // Usuário 4
        cadastrarEmprestimo(
                4,
                7,
                LocalDate.of(2026, 10, 8),
                LocalDate.of(2026, 10, 15)
        );

        // Usuário 5
        cadastrarEmprestimo(
                5,
                8,
                LocalDate.of(2026, 10, 10),
                LocalDate.of(2026, 10, 17)
        );

        // Usuário 6
        cadastrarEmprestimo(
                6,
                9,
                LocalDate.of(2026, 10, 12),
                LocalDate.of(2026, 10, 19)
        );

        // Usuário 1 novamente
        cadastrarEmprestimo(
                1,
                10,
                LocalDate.of(2026, 10, 15),
                LocalDate.of(2026, 10, 22)
        );
    }

}
