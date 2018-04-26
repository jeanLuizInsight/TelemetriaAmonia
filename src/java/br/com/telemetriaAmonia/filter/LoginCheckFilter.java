/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.telemetriaAmonia.filter;

import br.com.telemetriaAmonia.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

/**
 *
 * @author Jean Luiz
 */
public class LoginCheckFilter extends AbstractFilter implements Filter {
    private static List<String> allowedURIs;
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        if(allowedURIs == null){
            allowedURIs = new ArrayList<String>();
            allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
        }
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        if (session.isNew()) {
            doLogin(request, response, req);
            return;
        }
 
        Usuario user = (Usuario) session.getAttribute("usuario");
 
        if (user == null && !allowedURIs.contains(req.getRequestURI())) {
            System.out.println(req.getRequestURI());
            doLogin(request, response, req);
            return;
        }
 
        chain.doFilter(request, response);
    }
}
