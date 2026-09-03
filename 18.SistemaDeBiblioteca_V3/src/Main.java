import entities.Livro;
import services.LivroService;

public class Main {
    public static void main(String[] args) {


        LivroService livroService = new LivroService();

        livroService.cadastrarLivro("abc", "Euu", "123", 2026);
        livroService.cadastrarLivro("Senhor dos aneis", "Euu", "123", 2020);
        System.out.println(livroService.listarLivros());

        System.out.println("====");

        System.out.println(livroService.buscarLivroPorId(1));
    }
}