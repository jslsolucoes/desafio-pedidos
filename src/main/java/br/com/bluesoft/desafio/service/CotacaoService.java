package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.impl.model.Cotacao;

public interface CotacaoService {

    public List<Cotacao> realizaCotacoesParaProduto(Produto produto) throws CotacaoServiceException;

}