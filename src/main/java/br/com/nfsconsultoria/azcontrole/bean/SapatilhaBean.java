package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.dao.SapatilhaDAO;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
import br.com.nfsconsultoria.azcontrole.domain.Sapatilha;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * @author luissantos
 *
 */
@ManagedBean
@SessionScoped
public class SapatilhaBean {

    private Sapatilha sapatilha;
    private List<Sapatilha> sapatilhas;
    private List<Fornecedor> fornecedores;

    public SapatilhaBean() {
        SapatilhaDAO saDao = new SapatilhaDAO();
        FornecedorDAO forDAO = new FornecedorDAO();
        this.sapatilhas = saDao.listar();
        this.fornecedores = forDAO.listarLazy("fornecedor");
    }

    public Sapatilha getSapatilha() {
        return this.sapatilha;
    }

    public void setSapatilha(Sapatilha sapatilha) {
        this.sapatilha = sapatilha;
    }

    public List<Sapatilha> getSapatilhas() {
        return this.sapatilhas;
    }

    public void setSapatilhas(List<Sapatilha> sapatilhas) {
        this.sapatilhas = sapatilhas;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void listar() {
        try {
            SapatilhaDAO saDao = new SapatilhaDAO();
            this.sapatilhas = saDao.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar sapatilhas");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.sapatilha = new Sapatilha();
    }

    public void salvar() {
        try {
            SapatilhaDAO saDao = new SapatilhaDAO();
            saDao.merge(sapatilha);
            listar();
            novo();
            Messages.addGlobalInfo("Sapatilha salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar salvar sapatilha");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            sapatilha = (Sapatilha) evento.getComponent().getAttributes().get("sapatilhaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar selecionar sapatilha");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            SapatilhaDAO saDao = new SapatilhaDAO();
            sapatilha = (Sapatilha) evento.getComponent().getAttributes().get("sapatilhaSelecionada");
            saDao.excluir(sapatilha);
            listar();
            Messages.addGlobalInfo("Sapatilha excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar excluir sapatilha");
            erro.printStackTrace();
        }
    }
}
