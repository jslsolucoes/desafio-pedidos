package br.com.bluesoft.desafio.api;

import org.junit.Test;

import br.com.bluesoft.desafio.test.AbstractTest;

public class RecursoNaoEncontradoExceptionTest extends AbstractTest {

    @Test
    public void verificaMensagem() {
	RecursoNaoEncontradoException recursoNaoEncontradoException = new RecursoNaoEncontradoException("mensagem1");
	assertEquals("mensagem1",recursoNaoEncontradoException.getMessage());
    }
}
