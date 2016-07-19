package br.com.nfsconsultoria.azcontrole.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author luissantos
 *
 */
@SuppressWarnings("serial")
@Entity
public class Vendedor extends GenericDomain implements Serializable {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(length = 15)
    private String cel;

    @Column(nullable = false, length = 10, unique = true)
    private String login;

    @Column(nullable = false, length = 10)
    private String senha;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel() {
        return this.cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
