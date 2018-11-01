/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet.filter;

/**
 *
 * @author belchiorpalma
 */
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LogFilter implements Filter {
 
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response =  (HttpServletResponse) res;
         
        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();
        String url = request.getRequestURI();
        String last = url.substring(url.lastIndexOf('/') + 1);
         
        //Log the IP address and current timestamp.
       // System.out.println("IP "+ipAddress + ", Time "+ new Date().toString());
        
        HttpSession session = request.getSession(false);// don't create if it doesn't exist
        if(session != null && !session.isNew()) {
            
                chain.doFilter(req, res);
            
        } else {
            if(last.contains("login.jsp")){
                chain.doFilter(req, res);
            }else{
                chain.doFilter(req, res);
                //response.sendRedirect("login.jsp");
            }
        }
       
        
        
        //br.com.itfox.utils.Logger.getLogger("Page: "+last+ ", IP "+ipAddress + ", Time "+ new Date().toString());
        //chain.doFilter(req, res);
    }
    public void init(FilterConfig config) throws ServletException {
         
        //Get init parameter
        String testParam = config.getInitParameter("test-param");
         
        //Print the init parameter
        System.out.println("Test Param: " + testParam);
    }
    public void destroy() {
        //add code to release any resource
    }
}
