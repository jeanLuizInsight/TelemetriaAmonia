/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.Usuario;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public interface InterfaceUsuario {
    public Usuario getUsuario(int idUsu);
    public Usuario getUsuarioByUsuarioLogin(String usuario);
    public void salvarUsu(Usuario usu);
    public void atualizarUsu(Usuario usu);
    public void excluirUsu(Usuario usu);
    public List<Usuario> listarUsus();
    public List<Usuario> listarUsusByNivel(int nivel);
    public Usuario loginEhValido(String usuario, String senha);
}
