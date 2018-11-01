/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.CollectionColumns;
import br.com.itfox.beans.Colors;
import br.com.itfox.beans.Member;
import br.com.itfox.beans.MemberAreaOper;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.utils.Utils;
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
public class RegisterMemberAreaOper extends HttpServlet {

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
        String strMemberId  = request.getParameter("member_id");
        String strAreaOper  = request.getParameter("area_oper");
        String operation    = request.getParameter("operation");
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        
        HttpSession session = request.getSession(true);
        
        if(strMemberId!=null && !strMemberId.isEmpty() && strMemberId!="" && strAreaOper!=null && !strAreaOper.isEmpty() && strAreaOper!= "" && operation!=null && !operation.isEmpty() && operation!= ""){
            int memberId = Integer.parseInt(strMemberId);
            int areaOper = Integer.parseInt(strAreaOper);
            int result=0;
             
            try{
                JSONObject jsono = new JSONObject();
                switch(operation){
                    case "insert":
                        result = new BusinessDelegate().insertMemberAreaOper(memberId, areaOper);
                        jsono.put("insert", result);
                        jsono.put("memberId", memberId);
                        jsono.put("areaOper", areaOper);
                        json.put(jsono); 
                        break;
                    case "delete":
                        result = new BusinessDelegate().deleteMemberAreaOper(memberId, areaOper);
                        jsono.put("delete", result);
                        jsono.put("memberId", memberId);
                        jsono.put("areaOper", areaOper);
                        json.put(jsono); 
                        break;
                    case "select":
                        List<MemberAreaOper> areas = new BusinessDelegate().selectMemberAreaOper(memberId);
                        for(MemberAreaOper m: areas){
                            jsono.put("member_id", m.getMember().getMemberId());
                            jsono.put("area_operacional", m.getAreaoper().getArea_operacional());
                            jsono.put("descricao", m.getAreaoper().getDescricao());
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
