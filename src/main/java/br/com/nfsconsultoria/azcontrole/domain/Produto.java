package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author luissantos
 *
 */
@Entity
public class Produto extends GenericDomain implements Serializable {
    
    @Column(nullable = false)
    private String lote;

    @Column(nullable = false, length = 45)
    private String ref;
    
    @Column(length = 45)
    private String tipo;
    
    @Column(nullable = false, length = 2)
    private Integer numero;

    @Column(length = 45)
    private String modelo;
    
    @Column(nullable = false, length = 8)
    private Double valor_compra;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data_compra;
   
    @Column
    private Boolean troca;
    
    @Column(columnDefinition = "LONGBLOB")
    private Byte[] foto;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public Double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(Double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
