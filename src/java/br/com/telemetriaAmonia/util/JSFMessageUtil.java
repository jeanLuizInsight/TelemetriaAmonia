/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jean Luiz
 */
public class JSFMessageUtil {
    //envia mensagem de informação
    public void sendInfoMessageToUser(String message) {
            FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
            addMessageToJsfContext(facesMessage);
    }
    //envia mensagem de erro
    public void sendErrorMessageToUser(String message) {
            FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
            addMessageToJsfContext(facesMessage);
    }
    //cria mensagem
    private FacesMessage createMessage(FacesMessage.Severity severity, String mensagemErro) {
            return new FacesMessage(severity, mensagemErro, mensagemErro);
    }
    //adiciona mensagem no contexto JSF
    private void addMessageToJsfContext(FacesMessage facesMessage) {
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
