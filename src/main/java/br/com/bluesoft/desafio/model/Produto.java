package br.com.bluesoft.desafio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.bluesoft.desafio.api.dto.PedidoDto.Builder;

@SuppressWarnings("serial")
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "produto_sq", initialValue = 1, allocationSize = 1, sequenceName = "produto_sq")
public class Produto implements Serializable {

    

    @Id
    @GeneratedValue(generator = "produto_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @NotNull
    public Long id;

    @NotNull
    @Column(name = "gtin", unique = true)
    private String gtin;

    @Column(name = "nome")
    @NotNull
    private String nome;

    public Produto() {

    }

    public Produto(Long id, String gtin, String nome) {
	this.id = id;
	this.gtin = gtin;
	this.nome = nome;
    }

    public Long getId() {
	return id;
    }

    public String getGtin() {
	return gtin;
    }

    public Produto setGtin(String gtin) {
	this.gtin = gtin;
	return this;
    }

    public String getNome() {
	return nome;
    }

    public Produto setNome(String nome) {
	this.nome = nome;
	return this;
    }
    
    public static class Builder {
	
	private String gtin;
	
	private Builder() {

	}

	public static Builder novoBuilder() {
	    return new Builder();
	}
	
	public Builder comGtin(String gtin) {
	    this.gtin = gtin;
	    return this;
	}
	
	public Produto constroi() {
	    return new Produto(null, gtin, null);
	}
    }

}
