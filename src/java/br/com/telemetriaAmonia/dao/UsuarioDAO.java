/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.Usuario;
import br.com.telemetriaAmonia.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


/**
 *
 * @author Jean Luiz
 */
public class UsuarioDAO implements InterfaceUsuario {

    @Override
    public Usuario getUsuario(int idUsu) { //ok
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Usuario usu = (Usuario) s.load(Usuario.class, idUsu); //não esta funcionando
        s.beginTransaction().commit();
        //s.close();
        return usu;
    }

    @Override
    public void salvarUsu(Usuario usu) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(usu);
        s.beginTransaction().commit();
    }

    @Override
    public void atualizarUsu(Usuario usu) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(usu);
        s.beginTransaction().commit();
    }

    @Override
    public void excluirUsu(Usuario usu) {  //ok lembrar de lançar excessões (causa: chave estrangeira)
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(usu);
        s.beginTransaction().commit();
    }

    @Override
    public List<Usuario> listarUsus() { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List usuarios = (List) s.getNamedQuery(Usuario.FIND_ALL_USUARIOS).list();
        s.beginTransaction().commit();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsusByNivel(int nivel) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List usuarios = (List) s.getNamedQuery(Usuario.FIND_USUARIO_BY_TIPO).setInteger("nivel", nivel).list();
        //List usuarios = (List) s.createQuery("FROM usuario WHERE nivel = :nivel").setInteger("nivel", nivel).list();
        s.beginTransaction().commit();
        return usuarios;
    }

    @Override
    public Usuario getUsuarioByUsuarioLogin(String usuario) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Usuario usu = (Usuario) s.getNamedQuery(Usuario.FIND_USUARIO_BY_USUARIO).setString("usuario", usuario).uniqueResult();
        s.beginTransaction().commit();
        return usu; //ok
    }

    @Override
    public Usuario loginEhValido(String usuario, String senha) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        //inicia a transação hibernate
        s.beginTransaction();
        //busca o usuário pelo seu Login(USUARIO)
        Usuario usu = getUsuarioByUsuarioLogin(usuario);
        //verifica se existe e se a senha bate
        if(usu == null || !usu.getSenha().equals(senha)) {
            return null;
        }
        return usu;
    }
}
