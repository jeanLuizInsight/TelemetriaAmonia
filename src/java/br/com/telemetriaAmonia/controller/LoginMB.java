/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.controller;

import br.com.telemetriaAmonia.dao.UsuarioDAO;
import br.com.telemetriaAmonia.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jean Luiz
 */
@RequestScoped   //verificar
@ManagedBean
public class LoginMB extends AbstratoMB implements Serializable {
    //injetando UsuarioMB
    @ManagedProperty(value = UsuarioMB.INJECTION_NAME)
    
    private UsuarioMB usuarioMB;
    private String usuario;
    private String senha;
    
    //
    public String getUsuario() {
        return usuario;
    }
    //
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    //
    public String getSenha() {
        return senha;
    }
    //
    public void setSenha(String senha) {
        this.senha = senha;
    }
    //método responsável por fazer a verificação de login do usuario
    public String Login() {
        UsuarioDAO usuDAO = new UsuarioDAO();        //instancia objeto DAO
        Usuario usu = usuDAO.loginEhValido(usuario, senha); //chama o método verificar o login retornando o usuario em caso de sucesso
        if(usu != null) {
            usuarioMB.setUsu(usu);
            FacesContext context = FacesContext.getCurrentInstance();  //verificar
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().setAttribute("usuario", usu);
            if(usu.getNivel() == 1)
                return "../Protected/Admin/principalAdmin.xhtml?faces-redirect=true";           //verificar endereçoo
            else if(usu.getNivel() == 2)
                return "../Protected/Cliente/registrosNH3Listar.xhtml?faces-redirect=true";
        }
        MensagemDeErroParaUsuario("Usuário ou Senha inválidos. Favor verificar!");
        return null;
    }
    //seter obrigatório ao injetar o maneged bean usuário
    public void setUsuarioMB(UsuarioMB usuMB) {
        this.usuarioMB = usuMB;
    }
}
