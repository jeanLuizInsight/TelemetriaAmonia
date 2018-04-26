/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.converter;

import br.com.telemetriaAmonia.dao.DispositivoDAO;
import br.com.telemetriaAmonia.model.Dispositivo;
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
@FacesConverter(value="converteDispositivo", forClass=br.com.telemetriaAmonia.model.Dispositivo.class)
public class DispositivoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.out.println("getAsObject = "+ string);
        DispositivoDAO disDAO = new DispositivoDAO();
        int disId;
        try {
            disId = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um dispositivo no select Dispositivo!", "Selecione um dispositivo no select Dispositivo!"));
        }
        return disDAO.getDispositivo(disId);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("getAsString = "+ o);
        if(o == null) {
            return "";
        }
        Dispositivo dis = (Dispositivo) o;
        return String.valueOf(dis.getId_dis());
    }
    
}
