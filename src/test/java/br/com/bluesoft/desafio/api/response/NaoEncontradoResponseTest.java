package br.com.bluesoft.desafio.api.response;

import org.junit.Test;

import br.com.bluesoft.desafio.test.AbstractTest;

public class NaoEncontradoResponseTest extends AbstractTest {

    @Test
    public void verificaMensagem() {
	RecursoNaoEncontradoResponse naoEncontradoResponse = new RecursoNaoEncontradoResponse("mensagem1");
	assertEquals("mensagem1",naoEncontradoResponse.getMensagem());
    }
}
