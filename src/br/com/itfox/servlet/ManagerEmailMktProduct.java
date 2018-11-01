/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.MemberAreaOper;
import br.com.itfox.beans.Product;
import br.com.itfox.business.BusinessDelegate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author belchiorpalma
 */
public class ManagerEmailMktProduct extends HttpServlet {

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
        String strProductId  = request.getParameter("product_id");
        String strEmailMktId  = request.getParameter("email_mkt_id");
        String operation    = request.getParameter("operation");
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        
        HttpSession session = request.getSession(true);
        
        if(strProductId!=null && !strProductId.isEmpty() && strProductId!="" && strEmailMktId!=null && !strEmailMktId.isEmpty() && strEmailMktId!= "" && operation!=null && !operation.isEmpty() && operation!= ""){
            int productId = Integer.parseInt(strProductId);
            int emailMktId = Integer.parseInt(strEmailMktId);
            int result=0;
             
            try{
                JSONObject jsono = new JSONObject();
                switch(operation){
                    case "insert":
                        result = new BusinessDelegate().insertEmailMktProduct(emailMktId, productId);
                        jsono.put("insert", result);
                        jsono.put("productId", productId);
                        jsono.put("emailMktId", emailMktId);
                        json.put(jsono); 
                        break;
                    case "delete":
                        result = new BusinessDelegate().deleteEmailMktProduct(emailMktId, productId);
                        jsono.put("delete", result);
                        jsono.put("productId", productId);
                        jsono.put("emailMktId", emailMktId);
                        json.put(jsono); 
                        break;
                    case "select":
                        List<Product> products = new BusinessDelegate().selectEmailMktProduct(emailMktId);
                        for(Product p: products){
                            jsono.put("product_id", p.getProductId());
                            jsono.put("name", p.getName());
                            jsono.put("descricao", p.getMetaTagDescription());
                            json.put(jsono);
                     }
                        break;
                    default:
                        break;
                }
            }catch(JSONException e){
                e.printStackTrace();
            }finally {
                    writer.write(json.toString());
                    writer.close();
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
