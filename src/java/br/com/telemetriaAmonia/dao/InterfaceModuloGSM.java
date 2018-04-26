/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.dao;

import br.com.telemetriaAmonia.model.ModuloGSM;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public interface InterfaceModuloGSM {
    public ModuloGSM getGSM(int idGSM);
    public void salvarGSM(ModuloGSM gsm);
    public void atualizarGSM(ModuloGSM gsm);
    public void excluirGSM(ModuloGSM gsm);
    public List<ModuloGSM> listarGSMs();
}
