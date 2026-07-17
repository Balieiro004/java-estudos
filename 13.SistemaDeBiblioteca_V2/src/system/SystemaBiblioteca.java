package system;

import services.AutorService;
import services.LivroService;
import services.UsuarioService;

public class SystemaBiblioteca {

    private final LivroService livroService;
    private final AutorService autorService;
    private final UsuarioService usuarioService;

    public SystemaBiblioteca() {
        this.usuarioService = new UsuarioService();
        this.autorService = new AutorService();
        this.livroService = new LivroService(autorService);
    }

    public LivroService getLivroService() {return livroService;}

    public AutorService getAutorService() {return autorService;}

    public UsuarioService getUsuarioService() {return usuarioService;}
}
