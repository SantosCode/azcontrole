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
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.omnifaces.util.Messages;

/**
 *
 * @author luis
 */
@ManagedBean
@SessionScoped
public class VendaBean {
    
    private Venda venda;
    private Collection<Venda> vendas;
    private Collection<Produto> produtos;
    private Collection<Vendedor> vendedores;
    private Collection<Cliente> clientes;

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

    public Collection<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(Collection<Venda> vendas) {
        this.vendas = vendas;
    }

    public Collection<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Collection<Produto> produtos) {
        this.produtos = produtos;
    }

    public Collection<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(Collection<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
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
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.A4);
        pdf.addAuthor("Luis Carlos Santos");
        pdf.addTitle("Acordo Cadastrados");
        pdf.addCreator("NFS Consultoria");
        pdf.addSubject("Acordo Cadastrados");
        pdf.open();

        Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);

        Paragraph p = new Paragraph("Relatório de Acordos", catFont);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(20);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images"
                + File.separator + "banner.png";

        pdf.add(Image.getInstance(logo));
        pdf.add(p);
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(CellStyle.ALIGN_JUSTIFY);
        cellStyle.setFont(font);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }
}
