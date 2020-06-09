package br.com.myproject.minipar.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lote <T extends Recebivel> {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao; 
	
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name="remessa_id")
	private Remessa remessa;
	
	@Transient
	private List<T> recebiveis = new ArrayList<>();
	
	
	public List<T> getRecebiveis() {
		return recebiveis;
	}

	public void addRecebiveis(List<T> recebiveis) {
		this.recebiveis = recebiveis;	
		somaValor(recebiveis);
	}
	
	public void somaValor(List<T> t) {
		
		for (T recebivel : t) {
			System.out.println("Valor Recebivel: "+recebivel.getValor());
			this.valor.add(recebivel.getValor());			
		}
	}
		
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
	public Remessa getRemessa() {
		return remessa;
	}

	public void setRemessa(Remessa remessa) {
		this.remessa = remessa;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
		Lote<?> other = (Lote<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lote [id=" + id + ", dataCriacao=" + dataCriacao + ", valor=" + valor + "]";
	}	
}
