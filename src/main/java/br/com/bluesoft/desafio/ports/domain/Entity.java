package br.com.bluesoft.desafio.ports.domain;

import java.io.Serializable;

public interface Entity<T extends Serializable> extends Serializable {

    public T getId();
}
