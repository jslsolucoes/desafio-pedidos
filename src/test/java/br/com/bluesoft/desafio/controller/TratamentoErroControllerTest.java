package br.com.bluesoft.desafio.controller;

import org.junit.Test;

import br.com.bluesoft.desafio.api.controller.TratatamentoErroController;
import br.com.bluesoft.desafio.api.response.ErroInternoServidorResponse;
import br.com.bluesoft.desafio.junit.AbstractTest;

public class TratamentoErroControllerTest extends AbstractTest {

    @Test
    public void verificaMensagemDeErroInterno() {
	TratatamentoErroController tratatamentoErroController = new TratatamentoErroController();
	ErroInternoServidorResponse erroInternoServidorResponse = tratatamentoErroController
		.trataErroInterno(new Exception("exception1"));
	assertNotNull(erroInternoServidorResponse);
	assertEquals("exception1", erroInternoServidorResponse.getMensagem());
    }
}
