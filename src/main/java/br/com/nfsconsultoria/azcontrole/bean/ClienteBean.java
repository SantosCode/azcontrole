package br.com.nfsconsultoria.azcontrole.bean;

import br.com.nfsconsultoria.azcontrole.dao.ClienteDAO;
import br.com.nfsconsultoria.azcontrole.domain.Cliente;
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
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar salvar cliente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar selecionar cliente");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
            clienteDAO.excluir(cliente);
            listar();
            Messages.addGlobalInfo("Cliente excluido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar excluir cliente");
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
