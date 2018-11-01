/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.Client;
import br.com.itfox.beans.EmailMkt;
import br.com.itfox.beans.EmailMktCart;
import br.com.itfox.beans.Product;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author belchiorpalma
 */
public class ManagerEmailMktCart extends HttpServlet {

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
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        String strEmailMktId = request.getParameter("email_mkt_id");
        String strClientId = request.getParameter("client_id");
        String strProductId = request.getParameter("product_id");
        String strQuantity = request.getParameter("quantity");
        String strStatus = request.getParameter("status");
        String strType = request.getParameter("type");
        //String strPrice = request.getParameter("price");
        //String strTotal = request.getParameter("total");
        float price=0.f;
        float total=0.f;
        
        String operation = request.getParameter("operation");
        if(strEmailMktId!=null && !strEmailMktId.isEmpty() && strEmailMktId!="" ){
            EmailMkt e = new EmailMkt();
            Client c = new Client();
            Product p = new Product();
            EmailMktCart cart = new EmailMktCart();
            // email_mkt_id
            if(strEmailMktId !=null && !strEmailMktId.isEmpty() && strEmailMktId!=""){
                try{
                e.setEmailMktId(Integer.parseInt(strEmailMktId));
                cart.setEmailMkt(e);
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            // quantity
            if(strQuantity !=null && !strQuantity.isEmpty() && strQuantity!=""){
                try{
                cart.setQuantity(Float.parseFloat(strQuantity));
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            // client_id
            if(strClientId !=null && !strClientId.isEmpty() && strClientId!=""){
                try{
                c.setClientId(Integer.parseInt(strClientId));
                cart.setClient(c);
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            // product_id
            if(strProductId !=null && !strProductId.isEmpty() && strProductId!=""){
                try{
                p.setProductId(Integer.parseInt(strProductId));
                Product prod = new Product();
                prod = new BusinessDelegate().selectProduct(p.getProductId());
                price = prod.getPrice();
                total = prod.getPrice()*cart.getQuantity();
                cart.setPrice(price);
                cart.setTotal(total);
                p.setPrice(price);
                cart.setProduct(p);
                }catch(Exception ex){
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    br.com.itfox.utils.Logger.getLogger(ex);
                }
            }
            int result = 0;
            // se for tipo json estamos fazendo direto pelo site
            if(strType !=null && !strType.isEmpty() && strType!="" && strType.equalsIgnoreCase("json")){
                EmailMktCart emailCart = new BusinessDelegate().selectEmailMktCart(cart);
                if(emailCart!=null && emailCart.getEmailMkt()!=null && emailCart.getEmailMkt().getEmailMktId()>0){
                    // operacao update
                     result = new BusinessDelegate().updateEmailMktCart(cart);
                }else{
                    result= new BusinessDelegate().insertEmailMktCart(cart);
                }
                try{
                    cart = new BusinessDelegate().selectEmailMktCartSummary(cart);
                    JSONObject jsono = new JSONObject();
                    jsono.put("total", cart.getTotal()+"*");
                    json.put(jsono);
                }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
            }else{
                // se nao for tipo json
                if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("insert")){
                    result= new BusinessDelegate().insertEmailMktCart(cart);
                }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("update")){
                    result = new BusinessDelegate().updateEmailMktCart(cart);
                }else if(operation !=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){
                    result = new BusinessDelegate().deleteEmailMktCart(cart);
                }
            }
            if (result>0) {
                //session.setAttribute("userid", m.getEmail());
                //out.println("welcome " + userid);
                //out.println("<a href='logout.jsp'>Log out</a>");
                response.sendRedirect("ecommerce_email_mkt_cart.jsp?msg=success");
            } else {
                response.sendRedirect("ecommerce_email_mkt_cart.jsp?msg=error");
            }
        }else{
            response.sendRedirect("ecommerce_email_mkt_cart.jsp?msg=null&email_mkt_id="+strEmailMktId);
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
