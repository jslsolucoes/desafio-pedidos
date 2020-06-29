package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.bo.Cotacao;

public interface CotacaoServiceClassificador {

    public Cotacao melhorCotacao(Produto produto, Integer quantidade) throws CotacaoServiceException;

}
