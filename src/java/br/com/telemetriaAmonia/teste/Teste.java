/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.teste;

import br.com.telemetriaAmonia.dao.*;
import br.com.telemetriaAmonia.model.Dispositivo;
import br.com.telemetriaAmonia.model.ModuloGSM;
import br.com.telemetriaAmonia.model.RegistroConcentracaoAmonia;
import br.com.telemetriaAmonia.model.Usuario;
import br.com.telemetriaAmonia.util.HibernateUtil;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jean Luiz
 */
public class Teste {
    public static void main(String[]args) {
        //Dispositivo dis = new DispositivoDAO().getDispositivo(1);
        //RegistroConcentracaoAmonia reg = new RegistroConcentracaoAmonia();
        List<Dispositivo> usu = new DispositivoDAO().listarDispositivos();
        /*
        reg.setId_dis(dis);
        String var1 = JOptionPane.showInputDialog("Data");
        Date dt = Date.valueOf(var1);
        reg.setData_reg(dt);
        String var2 = JOptionPane.showInputDialog("Data");
        Time t = Time.valueOf(var2);
        reg.setHora_reg(t);
        reg.setConc_medida_reg(20);
        reg.setConc_menor(20);
        reg.setConc_maior(20);        
        Timestamp ti = Timestamp.valueOf("2014-09-09 15:40:32");
        reg.setData_hora_cma(ti);
        Timestamp ti2 = Timestamp.valueOf("2014-09-09 15:40:32");
        reg.setData_hora_cme(ti2); 
        * 
        */
        
        InterfaceModuloGSM regui = new ModuloGSMDAO();
        //List<RegistroConcentracaoAmonia> reg2 = regui.listarRegistrosByCliente(2);
        //for(RegistroConcentracaoAmonia re : reg2) {
        //    JOptionPane.showMessageDialog(null, "Dados registros: "+re.getId_dis().getId_dis()+' '+re.getData_reg()+' '+re.getHora_reg());
        //}
        //ModuloGSM reg2 = regui.getGSM(1);
        for(Dispositivo u : usu){
            JOptionPane.showMessageDialog(null, "Registro: "+u.getNome_dis());
        }
        //JOptionPane.showMessageDialog(null, "Registro: "+usu.);
        
    }
    
}
