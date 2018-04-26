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
@Entity(name="usuario")
@NamedQueries({
    @NamedQuery(name="usuario.findUserByUsuario", query="FROM usuario WHERE usuario = :usuario"),
    @NamedQuery(name="usuario.findUserByTipo", query="FROM usuario WHERE nivel = :nivel"),
    @NamedQuery(name="usuario.findAllUsers", query="FROM usuario")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_USUARIO_BY_USUARIO = "usuario.findUserByUsuario";  //busca usuário por usuario
    //para query buscar usuario pelo seu tipo
    public static final String FIND_USUARIO_BY_TIPO = "usuario.findUserByTipo";
    public static final String FIND_ALL_USUARIOS = "usuario.findAllUsers";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_usu;    
    private String nome_usu;       
    @Column(name="usuario", unique=true)
    private String usuario;       
    private String senha;
    private String confirmarSenha;      
    private String email;   
    private String telefone;
    private String endereco;
    private String cpf;     
    private int nivel;    //1 admin, 2 cliente user
    private boolean ativo;  
    private Timestamp criado;
    private Timestamp modificado;

    public Usuario() {
    }

    public Usuario(int id_usu, String nome_usu, String usuario, String senha, String confirmarSenha, String email, String telefone, String endereco, String cpf, int nivel, boolean ativo, Timestamp criado, Timestamp modificado) {
        this.id_usu = id_usu;
        this.nome_usu = nome_usu;
        this.usuario = usuario;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nivel = nivel;
        this.ativo = ativo;
        this.criado = criado;
        this.modificado = modificado;
    }    
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Timestamp getCriado() {
        return criado;
    }

    public void setCriado(Timestamp criado) {
        this.criado = criado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public Timestamp getModificado() {
        return modificado;
    }    

    public void setModificado(Timestamp modificado) {
        this.modificado = modificado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNome_usu() {
        return nome_usu;
    }

    public void setNome_usu(String nome_usu) {
        this.nome_usu = nome_usu;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id_usu != other.id_usu) {
            return false;
        }
        if ((this.nome_usu == null) ? (other.nome_usu != null) : !this.nome_usu.equals(other.nome_usu)) {
            return false;
        }
        if ((this.usuario == null) ? (other.usuario != null) : !this.usuario.equals(other.usuario)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if ((this.confirmarSenha == null) ? (other.confirmarSenha != null) : !this.confirmarSenha.equals(other.confirmarSenha)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.telefone == null) ? (other.telefone != null) : !this.telefone.equals(other.telefone)) {
            return false;
        }
        if ((this.endereco == null) ? (other.endereco != null) : !this.endereco.equals(other.endereco)) {
            return false;
        }
        if ((this.cpf == null) ? (other.cpf != null) : !this.cpf.equals(other.cpf)) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        if (this.ativo != other.ativo) {
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
        hash = 53 * hash + this.id_usu;
        hash = 53 * hash + (this.nome_usu != null ? this.nome_usu.hashCode() : 0);
        hash = 53 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        hash = 53 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 53 * hash + (this.confirmarSenha != null ? this.confirmarSenha.hashCode() : 0);
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 53 * hash + (this.telefone != null ? this.telefone.hashCode() : 0);
        hash = 53 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 53 * hash + (this.cpf != null ? this.cpf.hashCode() : 0);
        hash = 53 * hash + this.nivel;
        hash = 53 * hash + (this.ativo ? 1 : 0);
        hash = 53 * hash + (this.criado != null ? this.criado.hashCode() : 0);
        hash = 53 * hash + (this.modificado != null ? this.modificado.hashCode() : 0);
        return hash;
    }
    
    
}
