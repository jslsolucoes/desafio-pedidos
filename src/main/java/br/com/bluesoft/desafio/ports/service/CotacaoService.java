package br.com.bluesoft.desafio.ports.service;

import java.util.List;

import br.com.bluesoft.desafio.ports.domain.Cotacao;
import br.com.bluesoft.desafio.ports.domain.Produto;

public interface CotacaoService {

    public List<Cotacao> realizaCotacoesParaProduto(Produto produto) throws CotacaoServiceException;

}
