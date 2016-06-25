package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author luissantos
 */
@Entity
public class Cliente extends GenericDomain implements Serializable {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, unique = true, length = 15)
    private String cel;

    @Column(length = 45)
    private String email;

    @Column(length = 120)
    private String rua;

    @Column(length = 80)
    private String bairro;

    @Column(length = 80)
    private String cidade;
    
    @Column(length = 2)
    private String dia_niver;
    
    @Column(length = 15)
    private String mes_niver;
    
    @OneToMany(mappedBy = "cliente", targetEntity = Produto.class, 
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Produto> produtos;
    
    @OneToOne
    private Vendedor vendedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDia_niver() {
        return dia_niver;
    }

    public void setDia_niver(String dia_niver) {
        this.dia_niver = dia_niver;
    }

    public String getMes_niver() {
        return mes_niver;
    }

    public void setMes_niver(String mes_niver) {
        this.mes_niver = mes_niver;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
