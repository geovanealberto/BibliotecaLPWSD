/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Modelo.TbExemplar;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author alunoces
 */
@FacesConverter("EmprestimoConverterExemplar")    
@ManagedBean
public class EmprestimoConverterExemplar implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent uic, String value) {
         if (value != null && !value.isEmpty()) {
            return (TbExemplar) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uic, Object value) {
        if (value instanceof TbExemplar) {
            TbExemplar pergunta = (TbExemplar) value;
            if (pergunta != null && pergunta instanceof TbExemplar && pergunta.getIdtbExemplar()!= null) {
                uic.getAttributes().put(pergunta.getIdtbExemplar().toString(), pergunta);
                return pergunta.getIdtbExemplar().toString();
            }
        }
        return "";
    }
    
}
