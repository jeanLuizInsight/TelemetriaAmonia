/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.dao.DispositivoDAO;
import br.com.telemetriaAmonia.dao.ModuloGSMDAO;
import br.com.telemetriaAmonia.model.ModuloGSM;
import br.com.telemetriaAmonia.servidor.BuscarDataSistema;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jean Luiz
 */
@ManagedBean(name = "moduloGSMMB")
@SessionScoped
public class ModuloGSMMB extends AbstratoMB implements Serializable {
    private ModuloGSM gsm;
    private List<ModuloGSM> gsms;
    private ModuloGSMDAO gsmDAO;
    private DispositivoDAO disDAO;
    private boolean addNewGsm;
    
    public ModuloGSM getGSM() {
        if(this.gsm == null) 
            this.gsm = new ModuloGSM();
        return gsm;        
    }
    
    public ModuloGSMDAO getGSMDAO() {
        if(this.gsmDAO == null)
            this.gsmDAO = new ModuloGSMDAO();
        return gsmDAO;
    }
    
    public DispositivoDAO getDisDAO() {
        if(this.disDAO == null)
            this.disDAO = new DispositivoDAO();
        return disDAO;
    }
    
    public void setGSM(ModuloGSM gsm) {
        this.gsm = gsm;
    }
    
    public void adicionar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getGSM().setCriado(ti);
            getGSM().setModificado(null);
            getGSMDAO().salvarGSM(gsm);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Módulo GSM criado com sucesso!");
            resetGSM();
            loadGSMs();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos criar o Módulo GSM. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }
    
    public void alterar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getGSM().setModificado(ti);
            getGSMDAO().atualizarGSM(gsm);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Módulo GSM alterado com sucesso!");
            resetGSM();
            loadGSMs();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos alterar o Módulo GSM. Tente novamente mais tarde!");
            e.printStackTrace();
        }        
    }
    
    public void salvar() {
         try {   
            if (addNewGsm) {                 
                adicionar();
            } else {
                alterar();
            }        
        } finally {
            getAllGSMs();
        }
    }
    
    public void excluir() {
        try {
            List var = getDisDAO().listarDispositivosByGSM(gsm.getId_gsm()); 
            if(!var.isEmpty()) {
                MantemDialogoAberto();
                MensagemDeErroParaUsuario("Impossível excluir o módulo GSM "+gsm.getNome_gsm()+". Existem dispositivos relacionados a ele!");
            } else {
                getGSMDAO().excluirGSM(gsm);
                FechaDialogo();
                MensagemDeInformacaoParaUsuario("Módulo GSM excluido com sucesso!");
                resetGSM();
                loadGSMs();
            }
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos excluir o Módulo GSM. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }
    
    public List<ModuloGSM> getAllGSMs() {
        if(gsms == null) {
            loadGSMs();
        }
        return gsms;
    }
    public int totalGSMs() {
        return gsms.size();
    }
    
    public boolean isAddNewGSM() {
        return addNewGsm;
    }
   
    public void setAddNewGSM(boolean addNewGsm) {
        this.addNewGsm = addNewGsm;          
    }
    
    private void loadGSMs() {
        gsms = getGSMDAO().listarGSMs();
    }
    
    public void resetGSM() {
        gsm = new ModuloGSM();
    }
}
