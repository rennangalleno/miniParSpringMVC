package br.com.myproject.minipar.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cartao extends Recebivel{
	
	@ManyToOne
	@JoinColumn(name="bandeira_id")
	private Bandeira bandeira;

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	@Override
	public String toString() {
		return "Cartao [bandeira=" + bandeira + "]";
	}
}
