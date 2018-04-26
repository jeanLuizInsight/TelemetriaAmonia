/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.ModuloGSM;
import br.com.telemetriaAmonia.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Jean Luiz
 */
public class ModuloGSMDAO implements InterfaceModuloGSM {

    @Override
    public ModuloGSM getGSM(int idGSM) { //ok
        Session s = HibernateUtil.getSessionFactory().openSession(); //verificar open session se não vai dar problema mais tarde            
        ModuloGSM gsm;
        try {
            s.beginTransaction();
            gsm = (ModuloGSM) s.load(ModuloGSM.class, idGSM); 
            s.beginTransaction().commit();
            return gsm;
        } catch (Exception e) {
            System.out.println("Erro ao buscar Módulo GSM: "+e.getMessage());
            return null;           
        } 
    }

    @Override
    public void salvarGSM(ModuloGSM gsm) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(gsm);
        s.beginTransaction().commit();
    }

    @Override
    public void atualizarGSM(ModuloGSM gsm) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.update(gsm);
        s.beginTransaction().commit();
    }

    @Override
    public void excluirGSM(ModuloGSM gsm) { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(gsm);
        s.beginTransaction().commit();
    }

    @Override
    public List<ModuloGSM> listarGSMs() { //ok
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List gsms = (List) s.getNamedQuery(ModuloGSM.FIND_ALL_GSMS).list();
        s.beginTransaction().commit();
        return gsms;
    }
}
