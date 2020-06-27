package br.com.bluesoft.desafio.ports.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Id implements Serializable {

    private final Long id;

    public Id(final Long id) {
	this.id = id;
    }

    public Long getValue() {
	return id;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Id other = (Id) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
