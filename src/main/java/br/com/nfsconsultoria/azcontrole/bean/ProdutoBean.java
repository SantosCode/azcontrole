package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.dao.ProdutoDAO;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
import br.com.nfsconsultoria.azcontrole.domain.Produto;
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
public class ProdutoBean {

    private Produto produto;
    private List<Produto> produtos;
    private List<Fornecedor> fornecedores;

    public ProdutoBean() {
        ProdutoDAO proDao = new ProdutoDAO();
        FornecedorDAO forDAO = new FornecedorDAO();
        this.produtos = proDao.listar();
        this.fornecedores = forDAO.listarLazy("fornecedor");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }


    public void listar() {
        try {
            ProdutoDAO saDao = new ProdutoDAO();
            this.produtos = saDao.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar sapatilhas");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.produto = new Produto();
    }

    public void salvar() {
        try {
            ProdutoDAO saDao = new ProdutoDAO();
            saDao.merge(produto);
            listar();
            novo();
            Messages.addGlobalInfo("Produto salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar salvar produto");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar selecionar produto");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            ProdutoDAO saDao = new ProdutoDAO();
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
            saDao.excluir(produto);
            listar();
            Messages.addGlobalInfo("Produto excluido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar excluir produto");
            erro.printStackTrace();
        }
    }
}
