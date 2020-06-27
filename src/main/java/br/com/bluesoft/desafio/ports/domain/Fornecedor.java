package br.com.bluesoft.desafio.ports.domain;

public interface Fornecedor extends Entity<Id> {

    public Cnpj getCnpj();

    public RazaoSocial getRazaoSocial();

}
