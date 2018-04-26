/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.RegistroConcentracaoAmonia;
import br.com.telemetriaAmonia.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Jean Luiz
 */
public class RegistroConcentracaoAmoniaDAO implements InterfaceRegistroConcentracaoAmonia {

    @Override
    public void salvarRegistro(RegistroConcentracaoAmonia reg) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(reg);
        s.beginTransaction().commit();
    }

    @Override
    public void atualizarRegistro(RegistroConcentracaoAmonia reg) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(reg);
        s.beginTransaction().commit();
    }

    @Override
    public void excluirRegistro(RegistroConcentracaoAmonia reg) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(reg);
        s.beginTransaction().commit();
    }

    @Override
    public List<RegistroConcentracaoAmonia> listarRegistros() { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List registros = (List) s.getNamedQuery(RegistroConcentracaoAmonia.FIND_ALL_REGAMONIA).list();
        s.beginTransaction().commit();
        return registros;
    }

    @Override
    public List<RegistroConcentracaoAmonia> listarRegistrosByDisp(int idDis) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List registros = (List) s.getNamedQuery(RegistroConcentracaoAmonia.FIND_REGAMONIAALL_BY_DISPOSITIVO).setInteger("idDis", idDis).list();
        s.beginTransaction().commit();
        return registros;
    }

    @Override
    public List<RegistroConcentracaoAmonia> listarRegistrosByCliente(int idUsu) { 
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List registros = (List) s.getNamedQuery(RegistroConcentracaoAmonia.FIND_REGAMONIAALL_BY_CLIENTE).setInteger("idUsu", idUsu).list();
        s.beginTransaction().commit();
        return registros;
    }

    @Override
    public RegistroConcentracaoAmonia getUltimoRegistroByDisp(int idDis) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        RegistroConcentracaoAmonia reg = (RegistroConcentracaoAmonia) s.getNamedQuery(RegistroConcentracaoAmonia.FIND_REGAMONIAONE_BY_CLIENTE_BY_DATA).setInteger("idDis", idDis).setMaxResults(1).uniqueResult();
        s.beginTransaction().commit();
        return reg;
    }
    
}
