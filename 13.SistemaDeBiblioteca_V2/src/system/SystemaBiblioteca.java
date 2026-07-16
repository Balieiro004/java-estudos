package system;

import services.AutorService;
import services.LivroService;

public class SystemaBiblioteca {

    private final LivroService livroService;
    private final AutorService autorService;

    public SystemaBiblioteca() {
        this.autorService = new AutorService();
        this.livroService = new LivroService(autorService);
    }

    public LivroService getLivroService() {
        return livroService;
    }

    public AutorService getAutorService() {
        return autorService;
    }
}
