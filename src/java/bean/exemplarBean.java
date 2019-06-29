/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Modelo.TbExemplar;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import dao.ExemplarDAO;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
public class exemplarBean {

    TbExemplar exemplar = new TbExemplar();

    List exemplares = new ArrayList();

    //construtor
    public exemplarBean() {
        exemplares = new ExemplarDAO().buscarTodas();
        exemplar = new TbExemplar();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new ExemplarDAO().persistir(exemplar);
        exemplares = new ExemplarDAO().buscarTodas();
        exemplar = new TbExemplar();
    }

    public void exclude(ActionEvent actionEvent) {
        new ExemplarDAO().remover(exemplar);
        exemplares = new ExemplarDAO().buscarTodas();
        exemplar = new TbExemplar();
    }

    //getters and setters
    public TbExemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(TbExemplar exemplar) {
        this.exemplar = exemplar;
    }

    public List getExemplares() {
        return exemplares;
    }

    public void setExemplares(List exemplares) {
        this.exemplares = exemplares;
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
