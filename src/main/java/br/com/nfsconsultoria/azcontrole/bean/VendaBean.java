/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.ClienteDAO;
import br.com.nfsconsultoria.azcontrole.dao.ProdutoDAO;
import br.com.nfsconsultoria.azcontrole.dao.VendaDAO;
import br.com.nfsconsultoria.azcontrole.dao.VendedorDAO;
import br.com.nfsconsultoria.azcontrole.domain.Cliente;
import br.com.nfsconsultoria.azcontrole.domain.Produto;
import br.com.nfsconsultoria.azcontrole.domain.Venda;
import br.com.nfsconsultoria.azcontrole.domain.Vendedor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author luis
 */
@ManagedBean
@SessionScoped
public class VendaBean {
    
    private Venda venda;
    private List<Venda> vendas;
    private List<Produto> produtos;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public VendaBean() {
        
        VendaDAO vendaDAO = new VendaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendedorDAO vendedorDAO = new VendedorDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        this.vendas = vendaDAO.listar();
        this.produtos = produtoDAO.listarLazy("produto");
        this.vendedores = vendedorDAO.listarLazy("vendedor");
        this.clientes = clienteDAO.listarLazy("cliente");
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void listar(){
        try {
            VendaDAO vendaDAO = new VendaDAO();
            this.vendas = vendaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar listar vendas");
            erro.printStackTrace();
        }
    }
    
    public void novo(){
        venda = new Venda();
    }
    
    public void salvar(){
        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.merge(venda);
            listar();
            novo();
            Messages.addGlobalInfo("Venda salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar salvar venda");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento){
        try {
            venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar selecionar venda");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento){
        try {
            VendaDAO vendaDAO = new VendaDAO();
            venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionada");
            vendaDAO.excluir(venda);
            listar();
            Messages.addGlobalInfo("Venda excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar excluir venda");
            erro.printStackTrace();
        }
    }
}
