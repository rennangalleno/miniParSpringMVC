package br.com.myproject.minipar.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cartao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Bandeira bandeira;
	
	@ManyToOne
	private TipoRecebivel tipoRecebivel;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private SituacaoRecebivel situacaoRecebivel;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao; 
	
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	private BigDecimal valor;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public TipoRecebivel getTipoRecebivel() {
		return tipoRecebivel;
	}

	public void setTipoRecebivel(TipoRecebivel tipoRecebivel) {
		this.tipoRecebivel = tipoRecebivel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SituacaoRecebivel getSituacaoRecebivel() {
		return situacaoRecebivel;
	}

	public void setSituacaoRecebivel(SituacaoRecebivel situacaoRecebivel) {
		this.situacaoRecebivel = situacaoRecebivel;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
//	@Override
//	public String toString() {
//		return this.id+" "+this.bandeira+" "+this.tipoRecebivel+" "+this.cliente.getNome()+" "+this.situacaoRecebivel
//		+" "+this.dataCriacao+" "+this.dataVencimento+" "+this.valor;
//	}
	
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
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
