package br.com.myproject.minipar.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SituacaoRecebivel {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String desSituacaoRecebivel;
	private Integer bolAtivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesSituacaoRecebivel() {
		return desSituacaoRecebivel;
	}

	public void setDesSituacaoRecebivel(String desSituacaoRecebivel) {
		this.desSituacaoRecebivel = desSituacaoRecebivel;
	}

	public Integer getBolAtivo() {
		return bolAtivo;
	}

	public void setBolAtivo(Integer bolAtivo) {
		this.bolAtivo = bolAtivo;
	}
	
	@Override
	public String toString() {
		return this.desSituacaoRecebivel;
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
		SituacaoRecebivel other = (SituacaoRecebivel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
