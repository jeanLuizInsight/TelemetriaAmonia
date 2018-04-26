/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jean Luiz
 */
@Entity(name="modulo_gsm")
@NamedQuery(name="modulogsm.findAllGsms", query="FROM modulo_gsm")
public class ModuloGSM implements Serializable {
    public static final String FIND_ALL_GSMS = "modulogsm.findAllGsms";
    
    @Id
    private int id_gsm;
    private String nome_gsm;
    private int csq;    
    private String credito;    
    private Timestamp criado;
    private Timestamp modificado;

    public ModuloGSM() {
    }

    public ModuloGSM(int id_gsm, String nome_gsm, int csq, String credito, Timestamp criado, Timestamp modificado) {
        this.id_gsm = id_gsm;
        this.nome_gsm = nome_gsm;
        this.csq = csq;
        this.credito = credito;
        this.criado = criado;
        this.modificado = modificado;
    }    
    
    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public Timestamp getCriado() {
        return criado;
    }

    public void setCriado(Timestamp criado) {
        this.criado = criado;
    }

    public int getCsq() {
        return csq;
    }

    public void setCsq(int csq) {
        this.csq = csq;
    }

    public int getId_gsm() {
        return id_gsm;
    }

    public void setId_gsm(int id_gsm) {
        this.id_gsm = id_gsm;
    }

    public Timestamp getModificado() {
        return modificado;
    }

    public void setModificado(Timestamp modificado) {
        this.modificado = modificado;
    }

    public String getNome_gsm() {
        return nome_gsm;
    }

    public void setNome_gsm(String nome_gsm) {
        this.nome_gsm = nome_gsm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModuloGSM other = (ModuloGSM) obj;
        if (this.id_gsm != other.id_gsm) {
            return false;
        }
        if ((this.nome_gsm == null) ? (other.nome_gsm != null) : !this.nome_gsm.equals(other.nome_gsm)) {
            return false;
        }
        if (this.csq != other.csq) {
            return false;
        }
        if ((this.credito == null) ? (other.credito != null) : !this.credito.equals(other.credito)) {
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
        int hash = 3;
        hash = 61 * hash + this.id_gsm;
        hash = 61 * hash + (this.nome_gsm != null ? this.nome_gsm.hashCode() : 0);
        hash = 61 * hash + this.csq;
        hash = 61 * hash + (this.credito != null ? this.credito.hashCode() : 0);
        hash = 61 * hash + (this.criado != null ? this.criado.hashCode() : 0);
        hash = 61 * hash + (this.modificado != null ? this.modificado.hashCode() : 0);
        return hash;
    }
    
    
}
