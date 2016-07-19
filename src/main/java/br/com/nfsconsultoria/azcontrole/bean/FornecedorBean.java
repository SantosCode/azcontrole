package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.FornecedorDAO;
import br.com.nfsconsultoria.azcontrole.domain.Fornecedor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
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

    public void listar() {
        try {
            FornecedorDAO fDao = new FornecedorDAO();
            this.fornecedores = fDao.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar fornecedor");
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
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar salvar fornecedor");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar selecionar fornecedor");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            FornecedorDAO fDao = new FornecedorDAO();
            fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecioando");
            fDao.excluir(fornecedor);
            listar();
            Messages.addGlobalInfo("Fornecedor excluido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar excluir fornecedor");
            erro.printStackTrace();
        }
    }
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        //Rectangle size = new Rectangle(1900, 600);
        pdf.addAuthor("Luis Carlos Santos");
        pdf.addTitle("Dentistas Cadastrados");
        pdf.addCreator("NFS Consultoria");
        pdf.addSubject("Dentistas Cadastrados");
        pdf.setPageSize(PageSize.A4.rotate());
        pdf.setMarginMirroring(true);

        pdf.open();

        Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);

        Paragraph p = new Paragraph("Relatório de Dentistas", catFont);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(20);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "banner.png";

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
