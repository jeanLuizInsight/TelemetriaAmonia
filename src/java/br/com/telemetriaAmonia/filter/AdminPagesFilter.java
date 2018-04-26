/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.filter;

import br.com.telemetriaAmonia.model.Usuario;
import java.io.IOException;
 import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jean Luiz
 */
public class AdminPagesFilter extends AbstractFilter implements Filter {
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Usuario user = (Usuario) req.getSession(true).getAttribute("usuario");
        if(user == null) {
            user = new Usuario();
        }
        if (!(user.getNivel() == 1)) {
            accessDenied(request, response, req);
            return;
        }
 
        chain.doFilter(request, response);
    }
 
    @Override
    public void init(FilterConfig arg0) throws ServletException {
 
    }
}
