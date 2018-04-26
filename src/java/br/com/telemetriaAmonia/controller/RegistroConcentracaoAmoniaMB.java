/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.dao.DispositivoDAO;
import br.com.telemetriaAmonia.dao.RegistroConcentracaoAmoniaDAO;
import br.com.telemetriaAmonia.dao.UsuarioDAO;
import br.com.telemetriaAmonia.model.Dispositivo;
import br.com.telemetriaAmonia.model.RegistroConcentracaoAmonia;
import br.com.telemetriaAmonia.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItems;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jean Luiz
 */
@ManagedBean(name="registroConcentracaoAmoniaMB")
@SessionScoped
public class RegistroConcentracaoAmoniaMB extends AbstratoMB {
    private HtmlSelectOneMenu htmlSelectUsuarios;
    private HtmlSelectOneMenu htmlSelectDispositivos;
    private RegistroConcentracaoAmonia regNH3;
    private List<RegistroConcentracaoAmonia> registrosNH3;
    private RegistroConcentracaoAmoniaDAO registrosNH3DAO;
    private List<Usuario> ususCli;
    private Usuario usuSelecionado = null;
    private UsuarioDAO usuDAO;
    private List<Dispositivo> disps;
    private Dispositivo dispSelecionado = null;
    private DispositivoDAO disDAO;
    private Usuario usuLogado;
    private UsuarioMB usuMB;

    public RegistroConcentracaoAmoniaMB() {
        ususCli = new ArrayList<Usuario>();
        disps = new ArrayList<Dispositivo>();
    }    
    
    public RegistroConcentracaoAmonia getRegNH3() {
        if(this.regNH3 == null) {
            this.regNH3 = new RegistroConcentracaoAmonia();
        }
        return regNH3;
    }
    
    public RegistroConcentracaoAmoniaDAO getRegistrosNH3DAO() {
        if(this.registrosNH3DAO == null) {
            this.registrosNH3DAO = new RegistroConcentracaoAmoniaDAO();            
        }
        return registrosNH3DAO;
    }
    
    public Usuario getUsuSelecionado() {
        if(usuSelecionado == null)
            usuSelecionado = new Usuario();
        return usuSelecionado;
    }
    
    public UsuarioDAO getUsuDAO() {
        if(usuDAO == null)
            usuDAO = new UsuarioDAO();
        return usuDAO;
    }
    
    public Dispositivo getDispSelecionado() {
        if(dispSelecionado == null)
            dispSelecionado = new Dispositivo();
        return dispSelecionado;
    }
    
    public DispositivoDAO getDisDAO() {
        if(disDAO == null)
            disDAO = new DispositivoDAO();
        return disDAO;
    }
    
    public Usuario getUsuLogado() {
        FacesContext context = FacesContext.getCurrentInstance();  //verificar
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        usuLogado = (Usuario)request.getSession().getAttribute("usuario");
        System.out.println("Usuario: "+usuLogado.getNome_usu());       
        return usuLogado;
    }
    
    public void excluir() {
        try {
            getRegistrosNH3DAO().excluirRegistro(regNH3);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Registro de NH³ excluido com sucesso!");
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos excluir o registro. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }
    
    public List<RegistroConcentracaoAmonia> getAllRegistros() {
        registrosNH3 = getRegistrosNH3DAO().listarRegistros();
        return registrosNH3;
    }
    
    public List<RegistroConcentracaoAmonia> getRegistrosDoCliente() {
        if(usuSelecionado != null)
            registrosNH3 = getRegistrosNH3DAO().listarRegistrosByCliente(getUsuSelecionado().getId_usu());
        return registrosNH3;
    }
    
    public List<RegistroConcentracaoAmonia> getRegistrosDoDispositivo() {
        return registrosNH3;        
    }
    
    public int totalRegistros() {
        //return registrosNH3.size();
        return 0;
    }
    
    public List<Usuario> getUsuariosClientes() {
        ususCli.clear();
        //RequestContext.getCurrentInstance().update("frmTblAmonia");
        ususCli = getUsuDAO().listarUsusByNivel(2);        
        return ususCli;
    }    
    
    public List<Dispositivo> getDispositivosDoCliente() {
        if(getUsuSelecionado() != null)
            disps = getDisDAO().listarDispositivosByCliente(getUsuSelecionado().getId_usu());        
        return disps;
    }
    
    public List<Dispositivo> getDispositivoDoClienteLogado() {
        if(getUsuLogado() != null)
            disps = getDisDAO().listarDispositivosByCliente(getUsuLogado().getId_usu());
        return disps;
    }
    
    public void usuarioChangeListener(ValueChangeEvent event) {        
        usuSelecionado = (Usuario) event.getNewValue();
        registrosNH3 = null;
        if(usuSelecionado != null) {
            disps = getDisDAO().listarDispositivosByCliente(getUsuSelecionado().getId_usu());
        } else {
            disps = null;
        }
        refresh();
    }
    
    public void dispositivoChangeListener(ValueChangeEvent event) {
        
        dispSelecionado = (Dispositivo) event.getNewValue();
        if(dispSelecionado != null) {
            registrosNH3 = getRegistrosNH3DAO().listarRegistrosByDisp(getDispSelecionado().getId_dis());            
        } else {
            registrosNH3 = null;
        }      
        refresh();
    }
    
    public void editMyUsuariosList(AjaxBehaviorEvent event) {
        if(htmlSelectUsuarios == null)
            htmlSelectUsuarios = new SelectOneMenu();
        htmlSelectUsuarios.getChildren().clear();
        UISelectItems itens = new UISelectItems();
        itens.setValue(getUsuariosClientes());
        htmlSelectUsuarios.getChildren().add(itens);
    }
    
    public boolean habilitarDispositivos() {
        if(usuSelecionado == null)
            return false;
        return true;
    }
    
    public HtmlSelectOneMenu getHtmlSelectUsus() {
        editMyUsuariosList(null);
        return htmlSelectUsuarios;
    }
    
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse(); //Optional
    }
}
