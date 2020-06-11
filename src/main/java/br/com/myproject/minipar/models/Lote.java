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
	public String toString() {
		return "Lote [id=" + id + ", dataCriacao=" + dataCriacao + ", valor=" + valor + "]";
	}	
}
