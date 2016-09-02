package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author luissantos
 *
 */
@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain implements Serializable {
    
    @Column(nullable = false, length = 45)
    private String refe;
    
    @Column(length = 45)
    private String tipo;
    
    @Column(nullable = false, length = 2)
    private Integer numero;

    @Column(length = 45)
    private String modelo;
    
    @Column
    private Boolean troca;
    
    @Column(columnDefinition = "LONGBLOB")
    private Byte[] foto;
    
    @ManyToOne
    @JoinColumn
    private Lote lote;

    public String getRefe() {
        return refe;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getTroca() {
        return troca;
    }

    public void setTroca(Boolean troca) {
        this.troca = troca;
    }

    public Byte[] getFoto() {
        return foto;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
}
