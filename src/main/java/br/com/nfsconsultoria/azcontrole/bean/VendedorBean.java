package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.VendedorDAO;
import br.com.nfsconsultoria.azcontrole.domain.Vendedor;
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
public class VendedorBean {

	private Vendedor vendedor;
	private List<Vendedor> vendedores;
	
	public VendedorBean() {
		VendedorDAO vendedorDAO = new VendedorDAO();
		this.vendedores = vendedorDAO.listar();
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedores() {
		return this.vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	
	public void listar() {
		try {
			VendedorDAO vendedorDAO = new VendedorDAO();
			this.vendedores = vendedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar listar vendedor");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		this.vendedor = new Vendedor();
	}
	
	public void salvar() {
		try {
			VendedorDAO vendedorDAO = new VendedorDAO();
			vendedorDAO.merge(vendedor);
			Messages.addGlobalInfo("Vendedor salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar salvar vendedor");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		vendedor = (Vendedor) evento.getComponent().getAttributes().get("vendedorSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o ero " +erro.getMessage()+ " ao tentar selecionar vendedor");
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			VendedorDAO vendedorDAO = new VendedorDAO();
			vendedor = (Vendedor) evento.getComponent().getAttributes().get("vendedorSelecionado");
			vendedorDAO.excluir(vendedor);
			Messages.addGlobalError("Vendedor ecluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar excluir vendedor");
			erro.printStackTrace();
		}
	}
}
