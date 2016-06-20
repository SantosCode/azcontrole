package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author luissantos
 *
 */
@Entity
public class Fornecedor extends GenericDomain implements Serializable {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(length = 15)
    private String tel;

    @Column(length = 120)
    private String rua;

    @Column(length = 80)
    private String bairro;

    @Column(length = 80)
    private String cidade;

    @Column(length = 9)
    private String cep;
    
    @OneToMany(mappedBy = "fornecedor", targetEntity = Sapatilha.class, 
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sapatilha> sapatilhas;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Sapatilha> getSapatilhas() {
        return sapatilhas;
    }

    public void setSapatilhas(List<Sapatilha> sapatilhas) {
        this.sapatilhas = sapatilhas;
    }
}
