package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.ClienteDAO;
import br.com.nfsconsultoria.azcontrole.domain.Cliente;
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
public class ClienteBean {

	private Cliente cliente;
	private List<Cliente> clientes;

	public ClienteBean() {
		ClienteDAO clienteDAO = new ClienteDAO();
		this.clientes = clienteDAO.listar();
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			this.clientes = clienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		this.cliente = new Cliente();
	}
	
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			listar();
			novo();
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar salvar cliente");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar selecionar cliente");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			clienteDAO.excluir(cliente);
			Messages.addGlobalInfo("Cliente excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar excluir cliente");
			erro.printStackTrace();
		}
	}
}
