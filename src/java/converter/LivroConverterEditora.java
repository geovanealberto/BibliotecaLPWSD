/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Modelo.TbEditora;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author geova
 */
@FacesConverter("LivroConverterEditora")
@ManagedBean
public class LivroConverterEditora implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (TbEditora) uic.getAttributes().get(value);
        }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TbEditora) {
            TbEditora pergunta = (TbEditora) value;
            if (pergunta != null && pergunta instanceof TbEditora && pergunta.getIdtbEditora()!= null) {
                uic.getAttributes().put(pergunta.getIdtbEditora().toString(), pergunta);
                return pergunta.getIdtbEditora().toString();
            }
        }
        return "";
    }
   
}
