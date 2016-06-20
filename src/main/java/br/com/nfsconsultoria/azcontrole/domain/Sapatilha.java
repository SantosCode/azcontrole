package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author luissantos
 *
 */
@Entity
public class Sapatilha extends GenericDomain implements Serializable {

    @Column(nullable = false, unique = true, length = 45)
    private String ref;
    
    @Column(nullable = false, length = 2)
    private Integer numero;

    @Column(length = 45)
    private String modelo;
    
    @Column(nullable = false, length = 8)
    private Double valor_compra;
    
    @Column(length = 8)
    private Double valor_venda;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data_compra;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date data_venda;
    
    @Column
    private Boolean troca;
    
    @Column(columnDefinition = "LONGBLOB")
    private Byte[] foto;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;
    
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Vendedor vendedor;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public Double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(Double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
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
    
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
