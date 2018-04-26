/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.Dispositivo;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public interface InterfaceDispositivo {
    public Dispositivo getDispositivo(int idDis);
    public List<Dispositivo> listarDispositivosByCliente(int idUsu);
    public List<Dispositivo> listarDispositivosByGSM(int idGSM);
    public void salvarDisp(Dispositivo dis);
    public void atualizarDisp(Dispositivo dis);
    public void excluirDisp(Dispositivo dis);
    public List<Dispositivo> listarDispositivos();
}
