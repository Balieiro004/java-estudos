package org.example.services;

import org.example.entities.Cliente;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

    @Test
    void deveCadastrarCliente() {

        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        assertEquals(1, service.listar().size());
    }

    @Test
    void naoDevePermitirAlterarListaDeClientesDiretamente(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        assertThrows(UnsupportedOperationException.class, () -> {
            service.listar().clear();
        });

        assertEquals(1, service.listar().size());
    }

    @Test
    void deveBuscarClientePorEmail(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        Optional<Cliente> resultado = service.buscarPorEmail("anderson@email.com");

        assertTrue(resultado.isPresent());
        assertEquals("Anderson", resultado.get().getNome());
        assertEquals("anderson@email.com",  resultado.get().getEmail());
    }

    @Test
    void deveRetornarOptionalVazioQuandoClienteNaoForEncontrado(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        Optional<Cliente> resultado = service.buscarPorEmail("outro@email.com");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveBuscarClienteCorretoQuandoExistiremVariosClientes(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");
        service.cadastrar("Carlos", "carlos@email.com");

        Optional<Cliente> resultado = service.buscarPorEmail("carlos@email.com");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNome());
        assertEquals("carlos@email.com", resultado.get().getEmail());
    }

    @Test
    void naoDeveCadastrarClienteComEmailDuplicado(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
           service.cadastrar("carlos", "anderson@email.com");
        });

        assertEquals(1, service.listar().size());
    }

    @Test
    void naoDeveCadastrarClientesComEmailDiferenciandoMaiusculasEMinusculas(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar("Carlos", "ANDERSON@email.com");
        });

        assertEquals(1, service.listar().size());
    }

    @Test
    void deveRetornarOptionalVazioQuandoBuscarPorEmailNulo(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        Optional<Cliente> resultado = service.buscarPorEmail(null);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRemoverClientePorEmail(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        service.removerClientePorEmail("anderson@email.com");

        assertEquals(0, service.listar().size());
    }

    @Test
    void naoDeveRemoverClienteInexistente(){
        ClienteService service = new ClienteService();

        assertThrows(IllegalArgumentException.class, () -> {
           service.removerClientePorEmail("inexistente@email.com");
        });

        assertEquals(0, service.listar().size());
    }


    @Test
    void deveRemoverSomenteClienteComEmailInformado() {
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");
        service.cadastrar("Carlos", "carlos@email.com");

        service.removerClientePorEmail("anderson@email.com");

        assertEquals(1, service.listar().size());
        assertEquals("Carlos", service.listar().get(0).getNome());
    }

    @Test
    void naoDeveRemoverClienteQuandoEmailForNulo(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.removerClientePorEmail(null);
        });

        assertEquals(1, service.listar().size());
    }

    @Test
    void deveAlterarNomeDoCliente(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        service.alterarNome("anderson@email.com", "Anderson Balieiro");

        assertEquals("Anderson Balieiro", service.buscarPorEmail("anderson@email.com").get().getNome());
    }

    @Test
    void naoDeveAlterarNomeDeClienteInexistente(){
        ClienteService service = new ClienteService();


        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarNome("inexistente@email.com", "Carlos");
        });

        assertEquals(0, service.listar().size());
    }

    @Test
    void deveAlterarEmailDoCliente(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");

        service.alterarEmail("anderson@email.com", "anderson.novo@email.com");

        Optional<Cliente> cliente = service.buscarPorEmail("anderson.novo@email.com");

        assertEquals("anderson.novo@email.com", cliente.get().getEmail());
    }

    @Test
    void naoDeveAlterarEmailParaEmailJaCadastrado(){
        ClienteService service = new ClienteService();

        service.cadastrar("Anderson", "anderson@email.com");
        service.cadastrar("Carlos", "carlos@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarEmail("anderson@email.com", "carlos@email.com");
        });

        assertTrue(service.buscarPorEmail("anderson@email.com").isPresent());
        assertTrue(service.buscarPorEmail("carlos@email.com").isPresent());
    }

    @Test
    void naoDeveAlterarEmailDeClienteInexistente(){
        ClienteService service = new ClienteService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarEmail("inexistente@email.com", "novo@email.com");
        });

        assertTrue(service.listar().isEmpty());

    }

    @Test
    void naoDeveAlterarEmailParaVazio(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarEmail("anderson@email.com", "");
        });

        assertEquals("anderson@email.com", service.buscarPorEmail("anderson@email.com").get().getEmail());
    }

    @Test
    void naoDeveAlterarEmailParaNulo(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarEmail("anderson@email.com", null);
        });

        assertEquals("anderson@email.com", service.buscarPorEmail("anderson@email.com").get().getEmail());
    }

    @Test
    void naoDeveAlterarEmailParaApenasEspacos(){
        ClienteService service = new ClienteService();

        service.cadastrar("anderson", "anderson@email.com");

        assertThrows(IllegalArgumentException.class, () -> {
            service.alterarEmail("anderson@email.com", "     ");
        });

        assertEquals("anderson@email.com", service.buscarPorEmail("anderson@email.com").get().getEmail());
    }


}
