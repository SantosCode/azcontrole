package br.com.nfsconsultoria.azcontrole.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
