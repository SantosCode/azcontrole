package br.com.nfsconsultoria.azcontrole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author luissantos
 *
 */
@Entity
public class Sapatilha extends GenericDomain {

	@Column(nullable = false, unique = true, length = 45)
	private String ref;

	@Column(nullable = false, length = 2)
	private Integer numero;

	@Column(length = 45)
	private String modelo;

	@Column(columnDefinition = "LONGBLOB")
	private Byte[] foto;

	public String getRef() {
		return this.ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

}
