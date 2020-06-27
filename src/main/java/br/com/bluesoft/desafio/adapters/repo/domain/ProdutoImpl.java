package br.com.bluesoft.desafio.adapters.repo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bluesoft.desafio.ports.domain.Gtin;
import br.com.bluesoft.desafio.ports.domain.Produto;

@SuppressWarnings("serial")
@Entity
@Table(name = "produto")
public class ProdutoImpl implements Produto {

    @Id
    @Column(name = "gtin")
    private String gtin;

    @Column(name = "nome")
    private String nome;
    
    public ProdutoImpl() {
	
    }
    
    public ProdutoImpl(String gtin) {
	this(gtin, null);
    }

    public ProdutoImpl(String gtin, String nome) {
	this.gtin = gtin;
	this.nome = nome;
    }

    @Override
    public Gtin getId() {
	return new Gtin(gtin);
    }

    @Override
    public String getNome() {
	return nome;
    }

}
