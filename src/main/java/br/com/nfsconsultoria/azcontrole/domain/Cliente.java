package br.com.nfsconsultoria.azcontrole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author luissantos
 *
 */
@Entity
public class Cliente extends GenericDomain {

	@Column(nullable = false, length = 45)
	private String nome;

	@Column(unique = true, length = 15)
	private String cel;

	@Column(length = 120)
	private String rua;

	@Column(length = 80)
	private String bairro;

	@Column(length = 80)
	private String cidade;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCel() {
		return this.cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
