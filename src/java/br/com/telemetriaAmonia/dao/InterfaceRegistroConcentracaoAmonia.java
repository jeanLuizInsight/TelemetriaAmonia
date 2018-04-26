/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.RegistroConcentracaoAmonia;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public interface InterfaceRegistroConcentracaoAmonia {
    public void salvarRegistro(RegistroConcentracaoAmonia reg);
    public void atualizarRegistro(RegistroConcentracaoAmonia reg);
    public void excluirRegistro(RegistroConcentracaoAmonia reg);
    public List<RegistroConcentracaoAmonia> listarRegistros();
    public List<RegistroConcentracaoAmonia> listarRegistrosByDisp(int idDis);
    public List<RegistroConcentracaoAmonia> listarRegistrosByCliente(int idUsu);
    public RegistroConcentracaoAmonia getUltimoRegistroByDisp(int idDis);
}
