import entities.Livro;

public class Main {
    public static void main(String[] args) {


        Livro livro = new Livro(
                "O Senhor dos Anéis",
                "J.R.R. Tolkien",
                "978-0000000000",
                1954
        );

        System.out.println(livro);

        livro.emprestado();

        System.out.println("=========");
        System.out.println(livro);

        livro.disponivel();
        livro.disponivel();

    }
}