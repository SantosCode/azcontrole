/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luis
 */
@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain implements Serializable {

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data_venda;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<Produto> produtos;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;
    
    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public Collection<Produto> getProdutos() {
        return produtos;
    }
    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
