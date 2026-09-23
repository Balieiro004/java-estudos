package system;

import services.EmprestimoService;
import services.LivroService;
import services.UsuarioService;

public class SistemaDeBibliotevaV3 {

    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public SistemaDeBibliotevaV3() {
        this.livroService = new LivroService();
        this.usuarioService = new UsuarioService();
        this.emprestimoService = new EmprestimoService(usuarioService, livroService);
    }

    public LivroService getLivroService() {return livroService;}

    public UsuarioService getUsuarioService() {return usuarioService;}

    public EmprestimoService getEmprestimoService() {return emprestimoService;}
}
