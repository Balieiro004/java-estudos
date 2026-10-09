package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        Cliente cliente = new Cliente("Anderson", "anderson@email.com");

        assertEquals("Anderson", cliente.getNome());
    }

    @Test
    void naoDeveCriarClienteComNomeVazio(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("", "anderson@email.com");
        });
    }

    @Test
    void naoDeveCriarClienteComNomeNulo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "anderson@email.com");
        });
    }

    @Test
    void naoDeveCriarClienteComNomeApenasComEspacos(){
        assertThrows(IllegalArgumentException.class, () -> {
           new Cliente("  ", "anderson@email.com");
        });
    }

    @Test
    void deveCriarClienteComEmail(){
        Cliente cliente = new Cliente("Anderson", "anderson@email.com");

        assertEquals("anderson@email.com", cliente.getEmail());
    }

    @Test
    void naoDeveCriarClienteComEmailNulo(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Anderson", null);
        });
    }

    @Test
    void naoDeveCriarClienteComEmailVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
           new Cliente("Anderson", "");
        });
    }

    @Test
    void naoDeveCriarClienteComEmailApenasComEspacos(){
        assertThrows(IllegalArgumentException.class, () -> {
           new Cliente("Anderson", "  ");
        });
    }


    @Test
    void deveManterNomeEEmailDoCliente(){

        Cliente cliente = new Cliente("Anderson", "anderson@email.com");

        assertEquals("Anderson", cliente.getNome());
        assertEquals("anderson@email.com", cliente.getEmail());
    }

    @Test
    void deveRetornarNomeEEmailCorretamente(){

        Cliente cliente = new Cliente("Anderson", "anderson@email.com");

        assertEquals("Anderson", cliente.getNome());
        assertEquals("anderson@email.com", cliente.getEmail());
    }
}
