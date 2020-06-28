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

import br.com.bluesoft.desafio.model.Produto.Builder;

@SuppressWarnings("serial")
@Entity
@Table(name = "fornecedor")
@SequenceGenerator(name = "fornecedor_sq", initialValue = 1, allocationSize = 1, sequenceName = "fornecedor_sq")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(generator = "fornecedor_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @NotNull
    public Long id;

    @NotNull
    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @NotNull
    @Column(name = "razao_social")
    private String razaoSocial;

    public Fornecedor() {

    }

    public Fornecedor(Long id, String cnpj, String razaoSocial) {
	this.id = id;
	this.cnpj = cnpj;
	this.razaoSocial = razaoSocial;
    }

    public Long getId() {
	return id;
    }

    public String getCnpj() {
	return cnpj;
    }

    public Fornecedor setCnpj(String cnpj) {
	this.cnpj = cnpj;
	return this;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public Fornecedor setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
	return this;
    }
    
    public static class Builder {

	private String cnpj;
	private String razaoSocial;

	private Builder() {

	}

	public static Builder novoBuilder() {
	    return new Builder();
	}

	public Builder comCnpj(String cnpj) {
	    this.cnpj = cnpj;
	    return this;
	}
	
	public Builder comRazaoSocial(String razaoSocial) {
	    this.razaoSocial = razaoSocial;
	    return this;
	}

	public Fornecedor constroi() {
	    return new Fornecedor(null, cnpj, razaoSocial);
	}
    }

}
