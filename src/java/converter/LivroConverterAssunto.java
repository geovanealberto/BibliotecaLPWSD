/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Modelo.TbAssunto;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author geova
 */
@FacesConverter("LivroConverterAssunto")
@ManagedBean
public class LivroConverterAssunto implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (TbAssunto) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TbAssunto) {
            TbAssunto pergunta = (TbAssunto) value;
            if (pergunta != null && pergunta instanceof TbAssunto && pergunta.getIdtbAssunto()!= null) {
                uic.getAttributes().put(pergunta.getIdtbAssunto().toString(), pergunta);
                return pergunta.getIdtbAssunto().toString();
            }
        }
        return "";
    }
   
}
