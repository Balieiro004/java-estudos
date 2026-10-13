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

}
