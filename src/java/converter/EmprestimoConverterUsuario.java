/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Modelo.TbUsuario;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author alunoces
 */
@FacesConverter("EmprestimoConverterUsuario")    
@ManagedBean
public class EmprestimoConverterUsuario implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent uic, String value) {
         if (value != null && !value.isEmpty()) {
            return (TbUsuario) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uic, Object value) {
        if (value instanceof TbUsuario) {
            TbUsuario pergunta = (TbUsuario) value;
            if (pergunta != null && pergunta instanceof TbUsuario && pergunta.getIdtbUsuario()!= null) {
                uic.getAttributes().put(pergunta.getIdtbUsuario().toString(), pergunta);
                return pergunta.getIdtbUsuario().toString();
            }
        }
        return "";
    }
    
}
