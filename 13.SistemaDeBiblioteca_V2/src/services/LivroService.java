package services;

import entities.Autor;
import entities.Livro;
import enums.CategoriaLivro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivroService {

    private List<Livro> livros = new ArrayList<>();
    private AutorService autorService;

    public LivroService(AutorService autorService) {
        this.autorService = autorService;
        carregarLivrosMock();
    }

    public List<Livro> getLivros() {
        return Collections.unmodifiableList(livros);
    }

    public  Livro cadastrarLivro(String titulo, String isbn, int idAutor, CategoriaLivro categoria, int anoPublicacao, int quantidadeExemplares){

        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("Titulo é obrigatório.");
        }
        if(isbn == null || isbn.isBlank()){
            throw new IllegalArgumentException("isbn é obrigatório.");
        }

        if (buscarLivroPorIsbn(isbn) != null) {
            throw new IllegalArgumentException("Já existe um livro com esse ISBN.");
        }

        if(categoria == null){
            throw new IllegalArgumentException("Categoria Obrigatório.");
        }

        if(quantidadeExemplares <= 0){
            throw new IllegalArgumentException("Quantidade insuficiente");
        }

        if (anoPublicacao < 1900 || anoPublicacao > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Ano de publicação inválido.");
        }

        Autor autor = autorService.buscarAutorPorId(idAutor);

        if(autor == null){
            throw new IllegalArgumentException("Autor inexistente.");
        }

        Livro livro = new Livro(titulo,isbn, autor, categoria, anoPublicacao, quantidadeExemplares);
        livros.add(livro);
        return livro;
    }

    public Livro buscarLivroPorId(int id){
        for(Livro livro : livros){
            if(livro.getId() == id){
                return livro;
            }
        }
        return null;
    }

    private Livro buscarLivroPorIsbn(String isbn){
        for(Livro livro : livros){
            if(livro.getIsbn().equalsIgnoreCase(isbn)){
                return livro;
            }
        }
        return null;
    }

    private void carregarLivrosMock() {
        cadastrarLivro(
                "Dom Casmurro",
                "9788535914849",
                1,
                CategoriaLivro.ROMANCE,
                1900,
                8
        );

        cadastrarLivro(
                "O Senhor dos Anéis",
                "9788595084759",
                2,
                CategoriaLivro.FICCAO,
                1954,
                5
        );

        cadastrarLivro(
                "It: A Coisa",
                "9788560280940",
                3,
                CategoriaLivro.TERROR,
                1986,
                6
        );

        cadastrarLivro(
                "Steve Jobs",
                "9788535919714",
                4,
                CategoriaLivro.BIOGRAFIA,
                2011,
                4
        );

        cadastrarLivro(
                "Java: Como Programar",
                "9788543004792",
                5,
                CategoriaLivro.PROGRAMACAO,
                2018,
                10
        );

        cadastrarLivro(
                "Sapiens: Uma Breve História da Humanidade",
                "9788525432186",
                6,
                CategoriaLivro.HISTORIA,
                2014,
                7
        );
    }
}
