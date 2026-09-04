import entities.Livro;
import services.LivroService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {


        LivroService livroService = new LivroService();

        livroService.cadastrarLivro("abc", "Euu", "123", 2026);
        livroService.cadastrarLivro("Senhor dos aneis", "Euu", "123", 2020);

        Optional<Livro> resultado = livroService.buscarLivroPorId(1);

        if (resultado.isPresent()) {
            System.out.println(resultado);
        }

        resultado.ifPresent(livro -> System.out.println(livro));

        Livro livro = livroService.buscarLivroPorId(999).
                orElseThrow(() -> new IllegalArgumentException("Livro não encontrado."));

        System.out.println(livro);

    }
}