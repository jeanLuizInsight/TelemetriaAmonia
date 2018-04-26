/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.Dispositivo;
import br.com.telemetriaAmonia.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Jean Luiz
 */
public class DispositivoDAO implements InterfaceDispositivo {

    @Override
    public Dispositivo getDispositivo(int idDis) { //ok
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Dispositivo disp = (Dispositivo) s.load(Dispositivo.class, idDis); //não esta funcionando
        s.beginTransaction().commit();
        //s.close();
        return disp;
    }

    @Override
    public List<Dispositivo> listarDispositivosByCliente(int idUsu) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List dispositivos = (List) s.getNamedQuery(Dispositivo.FIND_DISP_BY_CLIENTE).setInteger("idUsu", idUsu).list();
        //List usuarios = (List) s.createQuery("FROM usuario WHERE nivel = :nivel").setInteger("nivel", nivel).list();
        s.beginTransaction().commit();
        return dispositivos;
    }

    @Override
    public void salvarDisp(Dispositivo dis) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(dis);
        s.beginTransaction().commit();
    }

    @Override
    public void atualizarDisp(Dispositivo dis) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(dis);
        s.beginTransaction().commit();
    }

    @Override
    public void excluirDisp(Dispositivo dis) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(dis);
        s.beginTransaction().commit();
    }

    @Override
    public List<Dispositivo> listarDispositivos() { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List dispositivos = (List) s.getNamedQuery(Dispositivo.FIND_ALL_DISP).list();
        s.beginTransaction().commit();
        return dispositivos;
    }

    @Override
    public List<Dispositivo> listarDispositivosByGSM(int idGSM) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List dispositivos = (List) s.getNamedQuery(Dispositivo.FIND_DISP_BY_GSM).setInteger("idGSM", idGSM).list();
        //List usuarios = (List) s.createQuery("FROM usuario WHERE nivel = :nivel").setInteger("nivel", nivel).list();
        s.beginTransaction().commit();
        return dispositivos;
    }
}
