/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class Lote extends GenericDomain implements Serializable{
    
    @Column(nullable = false, length = 45)
    private String numero;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data_compra;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
