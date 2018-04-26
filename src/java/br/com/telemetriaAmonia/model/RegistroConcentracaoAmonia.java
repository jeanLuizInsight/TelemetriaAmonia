/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author Jean Luiz
 */
@Entity(name="registros_amonia")
@NamedQueries({
    @NamedQuery(name="registros_amonia.findRegAmoniaAllByDispositivo", query="FROM registros_amonia WHERE id_dis = :idDis ORDER BY data_reg DESC"),
    @NamedQuery(name="registros_amonia.findRegAmoniaAllByCliente", query="FROM registros_amonia ra INNER JOIN ra.id_dis as dispositivo INNER JOIN dispositivo.id_usu as usuario WHERE usuario.id_usu = :idUsu ORDER BY ra.data_reg DESC"),
    @NamedQuery(name="registros_amonia.findRegAmoniaOneByDispByData", query="FROM registros_amonia WHERE id_dis = :idDis ORDER BY id_reg_amonia DESC"),
    @NamedQuery(name="registros_amonia.findAllRegistros", query="FROM registros_amonia")
})
public class RegistroConcentracaoAmonia implements Serializable {
    public static final String FIND_REGAMONIAALL_BY_DISPOSITIVO = "registros_amonia.findRegAmoniaAllByDispositivo";
    public static final String FIND_REGAMONIAALL_BY_CLIENTE = "registros_amonia.findRegAmoniaAllByCliente";
    public static final String FIND_REGAMONIAONE_BY_CLIENTE_BY_DATA = "registros_amonia.findRegAmoniaOneByDispByData";
    public static final String FIND_ALL_REGAMONIA = "registros_amonia.findAllRegistros";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_reg_amonia;    
    @ManyToOne
    @JoinColumn(name = "id_dis")
    private Dispositivo id_dis;    
    private Date data_reg;  
    private Time hora_reg;   
    private int conc_medida_reg;   
    private int conc_menor;   
    private int conc_maior; 
    private Timestamp data_hora_cme;
    private Timestamp data_hora_cma;

    public RegistroConcentracaoAmonia() {
    }

    public RegistroConcentracaoAmonia(int id_reg_amonia, Dispositivo id_dis, Date data_reg, Time hora_reg, int conc_medida_reg, int conc_menor, int conc_maior, Timestamp data_hora_cme, Timestamp data_hora_cma) {
        this.id_reg_amonia = id_reg_amonia;
        this.id_dis = id_dis;
        this.data_reg = data_reg;
        this.hora_reg = hora_reg;
        this.conc_medida_reg = conc_medida_reg;
        this.conc_menor = conc_menor;
        this.conc_maior = conc_maior;
        this.data_hora_cme = data_hora_cme;
        this.data_hora_cma = data_hora_cma;
    }    
    
    public int getConc_maior() {
        return conc_maior;
    }

    public void setConc_maior(int conc_maior) {
        this.conc_maior = conc_maior;
    }

    public int getConc_medida_reg() {
        return conc_medida_reg;
    }

    public void setConc_medida_reg(int conc_medida_reg) {
        this.conc_medida_reg = conc_medida_reg;
    }

    public int getConc_menor() {
        return conc_menor;
    }

    public void setConc_menor(int conc_menor) {
        this.conc_menor = conc_menor;
    }

    public Timestamp getData_hora_cma() {
        return data_hora_cma;
    }

    public void setData_hora_cma(Timestamp data_hora_cma) {
        this.data_hora_cma = data_hora_cma;
    }

    public Timestamp getData_hora_cme() {
        return data_hora_cme;
    }

    public void setData_hora_cme(Timestamp data_hora_cme) {
        this.data_hora_cme = data_hora_cme;
    }

    public Date getData_reg() {
        return data_reg;
    }

    public void setData_reg(Date data_reg) {
        this.data_reg = data_reg;
    }

    public Time getHora_reg() {
        return hora_reg;
    }

    public void setHora_reg(Time hora_reg) {
        this.hora_reg = hora_reg;
    }

    public Dispositivo getId_dis() {
        return id_dis;
    }

    public void setId_dis(Dispositivo id_dis) {
        this.id_dis = id_dis;
    }

    public int getId_reg_amonia() {
        return id_reg_amonia;
    }

    public void setId_reg_amonia(int id_reg_amonia) {
        this.id_reg_amonia = id_reg_amonia;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroConcentracaoAmonia other = (RegistroConcentracaoAmonia) obj;
        if (this.id_reg_amonia != other.id_reg_amonia) {
            return false;
        }
        if (this.id_dis != other.id_dis && (this.id_dis == null || !this.id_dis.equals(other.id_dis))) {
            return false;
        }
        if (this.data_reg != other.data_reg && (this.data_reg == null || !this.data_reg.equals(other.data_reg))) {
            return false;
        }
        if (this.hora_reg != other.hora_reg && (this.hora_reg == null || !this.hora_reg.equals(other.hora_reg))) {
            return false;
        }
        if (this.conc_medida_reg != other.conc_medida_reg) {
            return false;
        }
        if (this.conc_menor != other.conc_menor) {
            return false;
        }
        if (this.conc_maior != other.conc_maior) {
            return false;
        }
        if (this.data_hora_cme != other.data_hora_cme && (this.data_hora_cme == null || !this.data_hora_cme.equals(other.data_hora_cme))) {
            return false;
        }
        if (this.data_hora_cma != other.data_hora_cma && (this.data_hora_cma == null || !this.data_hora_cma.equals(other.data_hora_cma))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_reg_amonia;
        hash = 97 * hash + (this.id_dis != null ? this.id_dis.hashCode() : 0);
        hash = 97 * hash + (this.data_reg != null ? this.data_reg.hashCode() : 0);
        hash = 97 * hash + (this.hora_reg != null ? this.hora_reg.hashCode() : 0);
        hash = 97 * hash + this.conc_medida_reg;
        hash = 97 * hash + this.conc_menor;
        hash = 97 * hash + this.conc_maior;
        hash = 97 * hash + (this.data_hora_cme != null ? this.data_hora_cme.hashCode() : 0);
        hash = 97 * hash + (this.data_hora_cma != null ? this.data_hora_cma.hashCode() : 0);
        return hash;
    }
    
    
}
