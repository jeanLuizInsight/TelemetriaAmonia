/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author Jean Luiz
 */
@Entity(name="dispositivo")
@NamedQueries({
    @NamedQuery(name="dispositivo.findAllDisp", query="FROM dispositivo"),
    @NamedQuery(name="dispositivo.findDispByCliente", query="FROM dispositivo WHERE id_usu = :idUsu"),
    @NamedQuery(name="dispositivo.findDispByGsm", query="FROM dispositivo WHERE id_gsm = :idGSM")
})
public class Dispositivo implements Serializable {
    //query buscar dispositivos por usuário
    public static final String FIND_DISP_BY_CLIENTE = "dispositivo.findDispByCliente";
    public static final String FIND_ALL_DISP = "dispositivo.findAllDisp";
    public static final String FIND_DISP_BY_GSM = "dispositivo.findDispByGsm";
    
    @Id
    private int id_dis;    
    
    @ManyToOne
    @JoinColumn(name="id_usu")
    private Usuario id_usu;    
    
    @ManyToOne
    @JoinColumn(name="id_gsm")
    private ModuloGSM id_gsm;    
    
    private String nome_dis;
    private Timestamp conexao;
    private Timestamp criado;
    private Timestamp modificado;

    public Dispositivo() {
    }

    public Dispositivo(int id_dis, Usuario id_usu, ModuloGSM id_gsm, String nome_dis, Timestamp conexao, Timestamp criado, Timestamp modificado) {
        this.id_dis = id_dis;
        this.id_usu = id_usu;
        this.id_gsm = id_gsm;
        this.nome_dis = nome_dis;
        this.conexao = conexao;
        this.criado = criado;
        this.modificado = modificado;
    }   
    
    public Timestamp getConexao() {
        return conexao;
    }

    public void setConexao(Timestamp conexao) {
        this.conexao = conexao;
    }

    public Timestamp getCriado() {
        return criado;
    }

    public void setCriado(Timestamp criado) {
        this.criado = criado;
    }

    public int getId_dis() {
        return id_dis;
    }

    public void setId_dis(int id_dis) {
        this.id_dis = id_dis;
    }

    public ModuloGSM getId_gsm() {
        return id_gsm;
    }

    public void setId_gsm(ModuloGSM id_gsm) {
        this.id_gsm = id_gsm;
    }

    public Usuario getId_usu() {
        return id_usu;
    }

    public void setId_usu(Usuario id_usu) {
        this.id_usu = id_usu;
    }

    public Timestamp getModificado() {
        return modificado;
    }

    public void setModificado(Timestamp modificado) {
        this.modificado = modificado;
    }

    public String getNome_dis() {
        return nome_dis;
    }

    public void setNome_dis(String nome_dis) {
        this.nome_dis = nome_dis;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dispositivo other = (Dispositivo) obj;
        if (this.id_dis != other.id_dis) {
            return false;
        }
        if (this.id_usu != other.id_usu && (this.id_usu == null || !this.id_usu.equals(other.id_usu))) {
            return false;
        }
        if (this.id_gsm != other.id_gsm && (this.id_gsm == null || !this.id_gsm.equals(other.id_gsm))) {
            return false;
        }
        if ((this.nome_dis == null) ? (other.nome_dis != null) : !this.nome_dis.equals(other.nome_dis)) {
            return false;
        }
        if (this.conexao != other.conexao && (this.conexao == null || !this.conexao.equals(other.conexao))) {
            return false;
        }
        if (this.criado != other.criado && (this.criado == null || !this.criado.equals(other.criado))) {
            return false;
        }
        if (this.modificado != other.modificado && (this.modificado == null || !this.modificado.equals(other.modificado))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id_dis;
        hash = 23 * hash + (this.id_usu != null ? this.id_usu.hashCode() : 0);
        hash = 23 * hash + (this.id_gsm != null ? this.id_gsm.hashCode() : 0);
        hash = 23 * hash + (this.nome_dis != null ? this.nome_dis.hashCode() : 0);
        hash = 23 * hash + (this.conexao != null ? this.conexao.hashCode() : 0);
        hash = 23 * hash + (this.criado != null ? this.criado.hashCode() : 0);
        hash = 23 * hash + (this.modificado != null ? this.modificado.hashCode() : 0);
        return hash;
    }
    
    
}
