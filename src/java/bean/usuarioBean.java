/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Modelo.TbUsuario;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author tassio
 */
@ManagedBean
@ViewScoped
public class usuarioBean {

    TbUsuario usuario = new TbUsuario();

    List usuarios = new ArrayList();

    //construtor
    public usuarioBean() {
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new TbUsuario();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new UsuarioDAO().persistir(usuario);
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new TbUsuario();
    }

    public void exclude(ActionEvent actionEvent) {
        new UsuarioDAO().remover(usuario);
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new TbUsuario();
    }
    
    
    public void login() throws IOException {
        TbUsuario usuarioLogado = new UsuarioDAO().login(this.usuario);
        
        if (usuarioLogado != null)
        {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            session.setAttribute("usuario", usuarioLogado);
            
            response.sendRedirect("/LPWSD");
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login ou senha incorretos!",
							"Favor inserir usuário e senha novamente!"));
        }
    }
    
    public void logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        session.invalidate();
        response.sendRedirect("/LPWSD/faces/login.xhtml");
    }
    //getters and setters
    public TbUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TbUsuario usuario) {
        this.usuario = usuario;
    }

    public List getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

    }

}
