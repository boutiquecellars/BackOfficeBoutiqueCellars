/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.Member;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author belchiorpalma
 */
public class Register extends HttpServlet {

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
        String name = request.getParameter("name");
        String userid = request.getParameter("email");
        String pwd = request.getParameter("password");
        String terms = request.getParameter("terms");
        String permission = request.getParameter("permission");
        String data = request.getParameter("date");
        String operation = request.getParameter("operation");
        String memberId = request.getParameter("member_id");
        Date date = new Date();
        HttpSession session = request.getSession(true);
        
        if(data!=null && !data.isEmpty() && data!=""){
           //data= Utils.dateFormat(data);
           date = Utils.dateFormatStringToDate(data);
        }
        if(userid!=null && !userid.isEmpty() && userid!="" && pwd!=null && !pwd.isEmpty() && pwd!= ""){
            Member m = new Member();
            m.setName(name);
            m.setEmail(userid);
            m.setPass(pwd);
            m.setTerms(terms);
            m.setPermission(permission);
            m.setDate(date);
            if(memberId !=null && !memberId.isEmpty() && memberId!=""){
                try{
                m.setMemberId(Integer.parseInt(memberId));
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            int result = 0;
            if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("insert")){
                result= new BusinessDelegate().insertMember(m);
            }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("update")){
                result = new BusinessDelegate().updateMember(m);
            }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){
                result = new BusinessDelegate().deleteMember(m);
            }
            
            if (result>0) {
                //session.setAttribute("userid", m.getEmail());
                //out.println("welcome " + userid);
                //out.println("<a href='logout.jsp'>Log out</a>");
                response.sendRedirect("members.jsp");
            } else {
                response.sendRedirect("members.jsp");
            }
        }else{
            response.sendRedirect("members.jsp");
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
