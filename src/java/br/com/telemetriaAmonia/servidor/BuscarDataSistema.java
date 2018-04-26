/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jean Luiz
 */
public class BuscarDataSistema {
    public String getDataSistema() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date);
    }
}
