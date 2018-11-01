/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.EmailMkt;
import br.com.itfox.beans.Product;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author belchiorpalma
 */
public class ManagerEmailMkt extends HttpServlet {

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
        //String sPrice = request.getParameter("price");
        String description = request.getParameter("description");
        String metaTitle = request.getParameter("meta_tag_title");
        String metaDescription = request.getParameter("meta_tag_description");
        String metKeywords = request.getParameter("meta_tag_keywords");
        String metaUrl = request.getParameter("meta_tag_url");
       
        String emailMktId = request.getParameter("id");
        String operation = request.getParameter("operation");
        if(name!=null && !name.isEmpty() && name!="" ){
            EmailMkt e = new EmailMkt();
            e.setName(name);
            
            if(emailMktId !=null && !emailMktId.isEmpty() && emailMktId!=""){
                try{
                e.setEmailMktId(Integer.parseInt(emailMktId));
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            
            // convert blob to String
            e.setDescription(Utils.stringToBlob(description));
            
            e.setMetaTagDescription(metaDescription);
            e.setMetaTagTitle(metaTitle);
            e.setMetaTagKeywords(metKeywords);
            e.setMetaTagUrl(metaUrl);
            int result = 0;
            if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("insert")){
                result= new BusinessDelegate().insertEmailMkt(e);
            }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("update")){
                result = new BusinessDelegate().updateEmailMkt(e);
            }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){
                result = new BusinessDelegate().deleteEmailMkt(e);
            }
            
            if (result>0) {
                //session.setAttribute("userid", m.getEmail());
                //out.println("welcome " + userid);
                //out.println("<a href='logout.jsp'>Log out</a>");
                response.sendRedirect("ecommerce_email_mkt.jsp?msg=success");
            } else {
                response.sendRedirect("ecommerce_email_mkt.jsp?msg=error");
            }
        }else{
            response.sendRedirect("ecommerce_email_mkt.jsp?msg=null");
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
