/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jean Luiz
 */
public class AbstractFilter {
    public AbstractFilter() {
        super();
    }
    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/Pages/Public/login.xhtml");
        rd.forward(request, response);
    } 
    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/Pages/Public/acessoNegado.xhtml");
        rd.forward(request, response);
    }
}
