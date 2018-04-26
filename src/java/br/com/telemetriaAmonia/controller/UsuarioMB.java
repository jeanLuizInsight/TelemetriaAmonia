/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.dao.DispositivoDAO;
import br.com.telemetriaAmonia.dao.InterfaceUsuario;
import br.com.telemetriaAmonia.dao.UsuarioDAO;
import br.com.telemetriaAmonia.model.Usuario;
import br.com.telemetriaAmonia.servidor.BuscarDataSistema;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jean Luiz
 */
@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends AbstratoMB implements Serializable {
    public static final String INJECTION_NAME = "#{usuarioMB}"; //utilizada em LoginMB (pois vou injetar este maneged bean em LoginMB)
    private Usuario usu;
    private List<Usuario> usuClientes;
    private List<Usuario> usuAdmins;
    private List<Usuario> usuarios;
    private UsuarioDAO usuDAO;
    private DispositivoDAO disDAO;
    private boolean addNewReg;
    
    public Usuario getUsu() {
        if(this.usu == null)
            this.usu = new Usuario();
        return usu;
    }
    
    public UsuarioDAO getUsuDao() {
        if(usuDAO == null) {
            usuDAO = new UsuarioDAO();
        }
        return usuDAO;
    }
    
    public DispositivoDAO getDisDao() {
        if(disDAO == null)
            disDAO = new DispositivoDAO();
        return disDAO;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }    
    
    public void adicionar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getUsu().setCriado(ti);
            getUsu().setModificado(null);
            getUsuDao().salvarUsu(usu);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Usuário criado com sucesso!");
            resetUsuario();
            loadUsuarios();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos criar o Usuário. Tente novamente mais tarde!");
            e.printStackTrace();
        }
    }
    
    public void alterar() {
        try {
            String buscaData = new BuscarDataSistema().getDataSistema();
            Timestamp ti = Timestamp.valueOf(buscaData);
            getUsu().setModificado(ti);
            getUsuDao().atualizarUsu(usu);
            FechaDialogo();
            MensagemDeInformacaoParaUsuario("Usuário alterado com sucesso!");
            resetUsuario();
            loadUsuarios();
        } catch (Exception e) {
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos alterar o Usuário. Tente novamente mais tarde!");
            e.printStackTrace();
        }        
    }
    
    public void salvar() {
         try {   
            if (addNewReg) {                 
                adicionar();
            } else {
                alterar();
            }        
        } finally {
            getAllUsuarios();
        }
    }
    
    public void excluir() {
        try {
            List var = getDisDao().listarDispositivosByCliente(usu.getId_usu());
            if(!var.isEmpty()) {
                MantemDialogoAberto();
                MensagemDeErroParaUsuario("Impossível excluir o usuário "+usu.getNome_usu()+". Existem dispositivos relacionados a ele!");
            } else {
                getUsuDao().excluirUsu(usu);
                FechaDialogo();
                MensagemDeInformacaoParaUsuario("Usuário excluido com sucesso!");
                resetUsuario();
                loadUsuarios();
            }
        } catch (Exception e) {            
            MantemDialogoAberto();
            MensagemDeErroParaUsuario("Ops, não conseguimos excluir o Usuário. Tente novamente mais tarde!");
            e.printStackTrace();
            
        }
    }
    //método que lista todos os usuários
    public List<Usuario> getAllUsuarios() {
        if(usuarios == null) {
            loadUsuarios();
        }
        return usuarios;
    }
    public int totalUsuarios() {
        return usuarios.size();
    }
    //método que lista apenas os usuários do tipo CLIENTE
    public List<Usuario> getUsuariosClientes() {
        usuClientes = getUsuDao().listarUsusByNivel(2);
        return usuClientes;
    }
    //método que lista apenas os usuários do tipo ADMIN
    public List<Usuario> getUsuariosAdmins() {
        usuAdmins = getUsuDao().listarUsusByNivel(1);
        return usuAdmins;
    }
    //método que lista os usuários pelo tipo em parametro
    public List<Usuario> getUsuariosTipo(int tipo) {
        usuarios = getUsuDao().listarUsusByNivel(tipo);
        return usuarios;
    }
    //verifica se é pra add novo usuário
    public boolean isAddNewReg() {
        return addNewReg;
    }
    //seta bool pra adicionar novo usuário
    public void setAddNewReg(boolean addNewReg) {
        this.addNewReg = addNewReg;          
    }
    //carregar usuários
    private void loadUsuarios() {
        usuarios = getUsuDao().listarUsus();
    }
    //instanciar um novo objeto usuario
    public void resetUsuario() {
        usu = new Usuario();
    }
    
    //metodo chamado ao realizar logout
    public String Logout() {
        getRequest().getSession().invalidate(); //torna sessão inválida
        return "/Pages/Public/login.xhtml?faces-redirect=true";
    }
    //busca requisição (pedido) no servidor http
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
