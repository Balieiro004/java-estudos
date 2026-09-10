package system;

import services.LivroService;
import services.UsuarioService;

public class SistemaDeBibliotevaV3 {

    private final LivroService livroService;

    private final UsuarioService usuarioService;

    public SistemaDeBibliotevaV3() {
        this.livroService = new LivroService();
        this.usuarioService = new UsuarioService();
    }

    public LivroService getLivroService() {return livroService;}

    public UsuarioService getUsuarioService() {return usuarioService;}
}
