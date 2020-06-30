package br.com.bluesoft.desafio.controller;

import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.desafio.api.RecursoNaoEncontradoException;
import br.com.bluesoft.desafio.api.controller.TratatamentoErroController;
import br.com.bluesoft.desafio.api.response.ErroInternoServidorResponse;
import br.com.bluesoft.desafio.api.response.RecursoNaoEncontradoResponse;
import br.com.bluesoft.desafio.junit.AbstractTest;

public class TratamentoErroControllerTest extends AbstractTest {

    private TratatamentoErroController tratatamentoErroController;

    @Before
    public void before() {
	tratatamentoErroController = new TratatamentoErroController();
    }

    @Test
    public void verificaMensagemDeErroInterno() {
	ErroInternoServidorResponse erroInternoServidorResponse = tratatamentoErroController
		.trataErroInterno(new Exception("exception1"));
	assertNotNull(erroInternoServidorResponse);
	assertEquals("exception1", erroInternoServidorResponse.getMensagem());
    }

    @Test
    public void verificaRecusoNaoEncontrado() {
	RecursoNaoEncontradoResponse recursoNaoEncontradoResponse = tratatamentoErroController
		.trataRecursoNaoEncontrado(new RecursoNaoEncontradoException("exception1"));
	assertNotNull(recursoNaoEncontradoResponse);
	assertEquals("exception1", recursoNaoEncontradoResponse.getMensagem());
    }
}
