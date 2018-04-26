/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.dao.DispositivoDAO;
import br.com.telemetriaAmonia.dao.ModuloGSMDAO;
import br.com.telemetriaAmonia.dao.RegistroConcentracaoAmoniaDAO;
import br.com.telemetriaAmonia.dao.UsuarioDAO;
import br.com.telemetriaAmonia.model.Dispositivo;
import br.com.telemetriaAmonia.model.ModuloGSM;
import br.com.telemetriaAmonia.model.Usuario;
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
@ManagedBean(name = "dispositivoMB")
@SessionScoped
public class DispositivoMB extends AbstratoMB implements Serializable {

    private Dispositivo disp;
    private List<Dispositivo> disps;
    private List<Dispositivo> dispsCliente;
    private List<Usuario> usus;
    private List<ModuloGSM> gsms;
    private DispositivoDAO dispDAO;
    private boolean addNewDispositivo;
    private RegistroConcentracaoAmoniaDAO regAmoniaDAO;
    private UsuarioDAO usuDAO;
    private ModuloGSMDAO gsmDAO;
    
    public Dispositivo getDisp() {
        if (this.disp == null) {
            this.disp = new Dispositivo();
        }
        return disp;
    }

    public DispositivoDAO getDispDAO() {
        if (this.dispDAO == null) {
            this.dispDAO = new DispositivoDAO();
        }
        return dispDAO;
    }

    public RegistroConcentracaoAmoniaDAO getRegAmoniaDAO() {
        if (this.regAmoniaDAO == null) {
            this.regAmoniaDAO = new RegistroConcentracaoAmoniaDAO();
        }
        return regAmoniaDAO;
    }

    public UsuarioDAO getUsuDAO() {
        if (this.usuDAO == null) {
            this.usuDAO = new UsuarioDAO();
        }
        return usuDAO;
    }

    public ModuloGSMDAO getGsmDAO() {
        if (this.gsmDAO == null) {
            this.gsmDAO = new ModuloGSMDAO();
        }
        return gsmDAO;
    }

    public void setDisp(Dispositivo dis) {
        this.disp = dis;
    }

    public void adicionar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getDisp().setCriado(ti);
            getDisp().setConexao(null);
            getDisp().setModificado(null);
            getDispDAO().salvarDisp(disp);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Dispositivo criado com sucesso!");
            resetDisp();
            loadDisps();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos criar o Dispositivo. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }

    public void alterar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getDisp().setModificado(ti);
            getDispDAO().atualizarDisp(disp);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Dispositivo alterado com sucesso!");
            resetDisp();
            loadDisps();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos alterar o Dispositivo. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }

    public void salvar() {
        try {
            if (addNewDispositivo) {
                adicionar();
            } else {
                alterar();
            }
        } finally {
            getAllDisps();
        }
    }

    public void excluir() {
        try {
            List var = getRegAmoniaDAO().listarRegistrosByDisp(disp.getId_dis());
            if (!var.isEmpty()) {
                MantemDialogoAberto();
                MensagemDeErroParaUsuario("Impossível excluir o Dispositivo " + disp.getNome_dis() + ". Existem registros de NH³ relacionados a ele!");
            } else {
                getDispDAO().excluirDisp(disp);
                FechaDialogo();
                MensagemDeInformacaoParaUsuario("Dispositivo excluido com sucesso!");
                resetDisp();
                loadDisps();
            }
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos excluir o Dispositivo. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }

    public List<Dispositivo> getAllDisps() {
        if (disps == null) {
            loadDisps();
        }
        return disps;
    }

    public List<Dispositivo> getDispositivosDoCliente(int idUsu) {
        if (dispsCliente == null) {
            dispsCliente = getDispDAO().listarDispositivosByCliente(idUsu);
        }
        return dispsCliente;
    }

    public List<Usuario> getUsuariosTipoClientes() {
        if (usus == null) {
            usus = getUsuDAO().listarUsusByNivel(2);
        }
        return usus;
    }

    public List<ModuloGSM> getModulosGSM() {
        if (gsms == null) {
            gsms = getGsmDAO().listarGSMs();
        }
        return gsms;
    }

    public int totalDisps() {
        return disps.size();
    }

    public boolean isAddNewDisp() {
        return addNewDispositivo;
    }

    public void setAddNewDisp(boolean addNewDisp) {
        this.addNewDispositivo = addNewDisp;
    }

    private void loadDisps() {
        disps = getDispDAO().listarDispositivos();
    }

    public void resetDisp() {
        disp = new Dispositivo();
    }
}
