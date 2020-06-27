package br.com.bluesoft.desafio.adapters.repo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.bluesoft.desafio.ports.domain.Cnpj;
import br.com.bluesoft.desafio.ports.domain.Fornecedor;
import br.com.bluesoft.desafio.ports.domain.Id;
import br.com.bluesoft.desafio.ports.domain.RazaoSocial;

@SuppressWarnings("serial")
@Entity
@Table(name = "fornecedor")
@SequenceGenerator(name = "fornecedor_sq", initialValue = 1, allocationSize = 1, sequenceName = "fornecedor_sq")
public class FornecedorImpl implements Fornecedor {

    @javax.persistence.Id
    @GeneratedValue(generator = "fornecedor_sq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    public FornecedorImpl() {

    }

    public FornecedorImpl(Long id) {
	this(id, null, null);
    }

    public FornecedorImpl(String cnpj, String razaoSocial) {
	this(null, cnpj, razaoSocial);
    }

    public FornecedorImpl(Long id, String cnpj, String razaoSocial) {
	this.id = id;
	this.cnpj = cnpj;
	this.razaoSocial = razaoSocial;
    }

    @Override
    public Id getId() {
	return new Id(id);
    }

    @Override
    public Cnpj getCnpj() {
	return new Cnpj(cnpj);
    }

    @Override
    public RazaoSocial getRazaoSocial() {
	return new RazaoSocial(razaoSocial);
    }

}
