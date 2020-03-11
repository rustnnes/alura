package app.models;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Lance extends Entity{

	private double valor;
	private Calendar data;
	@OneToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	@ManyToOne
	private Leilao leilao;

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Leilao getLeilao() {
		return leilao;
	}
	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}
	
	
}
