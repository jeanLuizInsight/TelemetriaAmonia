/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.converter;

import br.com.telemetriaAmonia.dao.UsuarioDAO;
import br.com.telemetriaAmonia.model.Usuario;
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
@FacesConverter(value="converteUsuario", forClass = br.com.telemetriaAmonia.model.Usuario.class)
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.out.println("getAsObject = "+ string);
        UsuarioDAO usuDAo = new UsuarioDAO();
        int usuId;
        if(string == null || string.equals("")) {
            return null;
        }
        try {
            usuId = Integer.parseInt(string);
            return usuDAo.getUsuario(usuId);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível aplicar a conversão de item com valor["+string+"] no componente ["+uic.getId()+"]!", "Não foi possível aplicar a conversão de item com valor["+string+"] no componente ["+uic.getId()+"]!!"));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("getAsString = "+ o);
        if(o == null) {
            return "";
        }
        Usuario usu = (Usuario) o;
        return String.valueOf(usu.getId_usu());
    }    
}
