package br.com.bluesoft.desafio.ports.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Gtin implements Serializable {

    private final String id;

    public Gtin(final String id) {
	this.id = id;
    }

    public String getValue() {
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
	Gtin other = (Gtin) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return id;
    }

}
