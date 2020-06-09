package br.com.myproject.minipar.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Remessa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;
	
	private BigDecimal valor;
	
	@OneToMany(mappedBy = "remessa")
	private List<Lote<?>> lotes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Lote<?>> getLotes() {
		return lotes;
	}

	public void addLotes(List<Lote<?>> lotes) {
		this.lotes = lotes;
		this.valor = somaValor(lotes);
	}
	
	public BigDecimal somaValor(List<Lote<?>> lotes) {
		BigDecimal valorTotal = new BigDecimal(0);
		
		for (Lote<?> lote : lotes) {
			
			BigDecimal valor = lote.getValor();
			valorTotal.add(valor);
		}
		
		return valorTotal;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getValor() {
		return valor;
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
		Remessa other = (Remessa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Remessa [id=" + id + ", dataCriacao=" + dataCriacao + ", valor=" + valor + "]";
	}		
}
