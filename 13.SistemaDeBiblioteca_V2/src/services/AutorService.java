package services;

import entities.Autor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutorService {

    private List<Autor> autores = new ArrayList<>();

    public AutorService() {carregarAutoresMock();}

    public List<Autor> getAutores() {
        return Collections.unmodifiableList(autores);
    }

    private void cadastrarAutor(String nome) {

        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        Autor autor = new Autor(nome);
        autores.add(autor);
    }

    protected Autor buscarAutorPorId(int idAutor){
        for(Autor autor : autores){
            return autor;
        }
        return null;
    }

    private void carregarAutoresMock() {
        cadastrarAutor("Machado de Assis");
        cadastrarAutor("J. R. R. Tolkien");
        cadastrarAutor("Stephen King");
        cadastrarAutor("Walter Isaacson");
        cadastrarAutor("Paul Deitel");
        cadastrarAutor("Yuval Noah Harari");
    }
}
