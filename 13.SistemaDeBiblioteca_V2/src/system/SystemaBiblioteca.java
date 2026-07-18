package system;

import services.AutorService;
import services.EmprestimoService;
import services.LivroService;
import services.UsuarioService;

public class SystemaBiblioteca {

    private final LivroService livroService;
    private final AutorService autorService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public SystemaBiblioteca() {
        this.usuarioService = new UsuarioService();
        this.autorService = new AutorService();
        this.livroService = new LivroService(autorService);
        this.emprestimoService = new EmprestimoService(usuarioService, livroService);
    }

    public LivroService getLivroService() {return livroService;}

    public AutorService getAutorService() {return autorService;}

    public UsuarioService getUsuarioService() {return usuarioService;}

    public EmprestimoService getEmprestimoService() {return emprestimoService;}
}
