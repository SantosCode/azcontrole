package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
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
public class FornecedorBean {

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	
	public FornecedorBean() {
		FornecedorDAO fDAO = new FornecedorDAO();
		this.fornecedores = fDAO.listar();
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return this.fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public void listar(){
		try {
			FornecedorDAO fDao = new FornecedorDAO();
			this.fornecedores = fDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar listar fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		this.fornecedor = new Fornecedor();
	}
	
	public void salvar() {
		try {
			FornecedorDAO fDao = new FornecedorDAO();
			fDao.merge(fornecedor);
			Messages.addGlobalInfo("Fornecedor Salvo com sucesso");
			listar();
			novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar salvar fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar selecionar fornecedor");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			FornecedorDAO fDao = new FornecedorDAO();
			fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecioando");
			fDao.excluir(fornecedor);
			Messages.addGlobalInfo("Fornecedor excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " +erro.getMessage()+ " ao tentar excluir fornecedor");
			erro.printStackTrace();
		}
	}
}
