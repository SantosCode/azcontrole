package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.ClienteDAO;
import br.com.nfsconsultoria.azcontrole.dao.SapatilhaDAO;
import br.com.nfsconsultoria.azcontrole.dao.VendaDAO;
import br.com.nfsconsultoria.azcontrole.dao.VendedorDAO;
import br.com.nfsconsultoria.azcontrole.domain.Cliente;
import br.com.nfsconsultoria.azcontrole.domain.Sapatilha;
import br.com.nfsconsultoria.azcontrole.domain.Venda;
import br.com.nfsconsultoria.azcontrole.domain.Vendedor;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by luis on 16/06/16.
 */
@ManagedBean
@SessionScoped
public class VendaBean {

    private Venda venda;
    private List<Venda> vendas;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Sapatilha> sapatilhas;

    public VendaBean() {
        VendaDAO vendaDAO = new VendaDAO();
        VendedorDAO vendedorDAO = new VendedorDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        SapatilhaDAO sapatilhaDAO = new SapatilhaDAO();

        this.vendas = vendaDAO.listar();
        this.vendedores = vendedorDAO.listarLazy("vendedor");
        this.clientes = clienteDAO.listarLazy("cliente");
        this.sapatilhas = sapatilhaDAO.listarLazy("sapatilha");
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

    public List<Sapatilha> getSapatilhas() {
        return sapatilhas;
    }

    public void setSapatilhas(List<Sapatilha> sapatilhas) {
        this.sapatilhas = sapatilhas;
    }

    public void listar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();
            this.vendas = vendaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar vendas");
        }


    }

    public void novo() {
        this.venda = new Venda();
    }

    public void salvar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.merge(venda);
            Messages.addGlobalInfo("Venda salva com sucesso");
            listar();
            novo();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar salvar venda");
            erro.printStackTrace();
        }

    }

    public void editar(ActionEvent evento) {
        try {
            venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar selecionar venda");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            VendaDAO vendaDAO = new VendaDAO();
            venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionada");
            vendaDAO.excluir(venda);
            listar();
            Messages.addGlobalInfo("Venda excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir venda");
            erro.printStackTrace();
        }
    }
}
