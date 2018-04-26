/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.util.JSFMessageUtil;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jean Luiz
 */
public class AbstratoMB {
    //definido para o sistema saber se mantem dialogo aberto (troca de mensagens com usuário)
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
    
    public AbstratoMB() {
        super();
    }
    //envia uma mensagem de erro ao usuario (em um display)
    protected void MensagemDeErroParaUsuario(String msg) {
        JSFMessageUtil mensagem = new JSFMessageUtil();
        mensagem.sendErrorMessageToUser(msg);
    }
    //envia mensagem de informação ao usuário
    protected void MensagemDeInformacaoParaUsuario(String msg) {
        JSFMessageUtil mensagem = new JSFMessageUtil();
        mensagem.sendInfoMessageToUser(msg);
    }
    //fecha dialogo
    protected void FechaDialogo() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
    //mantem dialogo aberto
    protected void MantemDialogoAberto() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
    //retorna o request context instancia para solicitação corrente na web
    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }
}
