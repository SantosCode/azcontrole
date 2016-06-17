package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.CompraDAO;
import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.dao.SapatilhaDAO;
import br.com.nfsconsultoria.azcontrole.domain.Compra;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
import br.com.nfsconsultoria.azcontrole.domain.Sapatilha;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * @author luissantos
 */
@ManagedBean
@SessionScoped
public class CompraBean {

    private Compra compra;
    private List<Compra> compras;
    private List<Fornecedor> fornecedores;
    private List<Sapatilha> sapatilhas;

    public CompraBean() {
        CompraDAO compraDAO = new CompraDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        SapatilhaDAO sapatilhaDAO = new SapatilhaDAO();

        this.compras = compraDAO.listar();
        this.fornecedores = fornecedorDAO.listarLazy("fornecedor");
        this.sapatilhas = sapatilhaDAO.listarLazy("sapatilha");
    }

    public Compra getCompra() {
        return this.compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Compra> getCompras() {
        return this.compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Fornecedor> getFornecedores() {
        return this.fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Sapatilha> getSapatilhas() {
        return this.sapatilhas;
    }

    public void setSapatilhas(List<Sapatilha> sapatilhas) {
        this.sapatilhas = sapatilhas;
    }

    public void listar() {
        try {
            CompraDAO compraDAO = new CompraDAO();
            this.compras = compraDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar compras");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.compra = new Compra();
    }

    public void salvar() {
        try {
            CompraDAO compraDAO = new CompraDAO();
            compraDAO.merge(compra);
            Messages.addGlobalInfo("Compra salva com sucesso");
            listar();
            novo();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar salvar compra");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            compra = (Compra) evento.getComponent().getAttributes().get("compraSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar selecionar compra");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            CompraDAO compraDAO = new CompraDAO();
            compra = (Compra) evento.getComponent().getAttributes().get("compraSelecionada");
            compraDAO.excluir(compra);
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir compra");
            erro.printStackTrace();
        }
    }
}
