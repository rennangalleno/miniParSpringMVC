package br.com.myproject.minipar.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Recebivel {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private TipoRecebivel tipoRecebivel;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="situacao_id")
	private SituacaoRecebivel situacaoRecebivel;
	
	@ManyToOne
	@JoinColumn(name="lote_id")
	private Lote<?> lote;
	
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao; 
	
	@Column
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@Column
	private BigDecimal valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Lote<?> getLote() {
		return lote;
	}

	public void setLote(Lote<?> lote) {
		this.lote = lote;
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

	@Override
	public String toString() {
		return "Recebivel [id=" + id + ", tipoRecebivel=" + tipoRecebivel + ", cliente=" + cliente
				+ ", situacaoRecebivel=" + situacaoRecebivel + ", lote=" + lote + ", dataCriacao=" + dataCriacao
				+ ", dataVencimento=" + dataVencimento + ", valor=" + valor + "]";
	}
}
