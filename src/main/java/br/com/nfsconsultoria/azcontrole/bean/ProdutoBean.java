package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.dao.ProdutoDAO;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
import br.com.nfsconsultoria.azcontrole.domain.Produto;
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
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

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
