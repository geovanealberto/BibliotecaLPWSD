/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Modelo.TbLivro;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author alunoces
 */
@FacesConverter("ExemplarConverterLivro")
@ManagedBean
public class ExemplarConverterLivro implements Converter {
@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (TbLivro) uic.getAttributes().get(value);
        }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TbLivro) {
            TbLivro pergunta = (TbLivro) value;
            if (pergunta != null && pergunta instanceof TbLivro && pergunta.getIdtbLivro()!= null) {
                uic.getAttributes().put(pergunta.getIdtbLivro().toString(), pergunta);
                return pergunta.getIdtbLivro().toString();
            }
        }
        return "";
    }
    
}
