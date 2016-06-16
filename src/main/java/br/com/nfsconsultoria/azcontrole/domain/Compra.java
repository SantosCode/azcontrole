package br.com.nfsconsultoria.azcontrole.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author luissantos
 *
 */
@Entity
public class Compra extends GenericDomain{

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(nullable = false)
	private List<Sapatilha> sapatilhas;
	
	@Column(nullable = false)
	private Double valor;
	
	@Temporal(TemporalType.DATE)
	@Column(unique = false)
	private Date data;

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Sapatilha> getSapatilhas() {
		return this.sapatilhas;
	}

	public void setSapatilhas(List<Sapatilha> sapatilhas) {
		this.sapatilhas = sapatilhas;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
