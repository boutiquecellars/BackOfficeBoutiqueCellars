/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.Member;
import br.com.itfox.business.BusinessDelegate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author belchiorpalma
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userid = request.getParameter("email");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession(true);
        
        if(userid!=null && !userid.isEmpty() && userid!="" && pwd!=null && !pwd.isEmpty() && pwd!= ""){
            Member m = new Member();
            m.setEmail(userid);
            m.setPass(pwd);

            m = new BusinessDelegate().login(m);
            if (m!=null && m.getMemberId()>0) {
                session.setAttribute("userid", m.getMemberId());
                session.setAttribute("username", m.getName());
                session.setAttribute("member", m);
                //out.println("welcome " + userid);
                //out.println("<a href='logout.jsp'>Log out</a>");
                if(m.getPermission()!=null && !m.getPermission().isEmpty() && m.getPermission().equalsIgnoreCase("admin")){
                    response.sendRedirect("index.jsp");
                }else{
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?id=null");
            }
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
