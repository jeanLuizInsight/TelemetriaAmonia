/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.converter;

import br.com.telemetriaAmonia.dao.ModuloGSMDAO;
import br.com.telemetriaAmonia.model.ModuloGSM;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jean Luiz
 */
@FacesConverter(value="converteGSM", forClass = br.com.telemetriaAmonia.model.ModuloGSM.class)
public class ModuloGSMConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.out.println("getAsObject = "+ string);
        ModuloGSMDAO gsmDAO = new ModuloGSMDAO();
        int gsmId;
        try {
            gsmId = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um módulo GSM no select GSM!", "Selecione um módulo GSM no select GSM!"));
        }
        return gsmDAO.getGSM(gsmId);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("getAsString = "+ o);
        if(o == null) {
            return "";
        }
        ModuloGSM gsm = (ModuloGSM) o;
        return String.valueOf(gsm.getId_gsm());
    }
    
}
