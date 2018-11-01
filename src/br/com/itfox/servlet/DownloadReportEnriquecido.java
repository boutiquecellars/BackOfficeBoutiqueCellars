/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author belchiorpalma
 */
@WebServlet(name = "DownloadReportEnriquecido", urlPatterns = {"/DownloadReportEnriquecido"})
public class DownloadReportEnriquecido extends HttpServlet {

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
                            
                            String strFilename = request.getParameter("path");
                            if((strFilename==null) || (strFilename=="")){
                                strFilename="relatorio_enriquecimento.xls";
                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy");
                            String date = sdf.format(new Date()); 
                            //System.out.println(date); //15/10/2013
                            String path = request.getServletContext().getRealPath("/")+strFilename;
                            String filename=strFilename;//"relatorio_enriquecimento"+date+".xls";
                               try{
                                ServletOutputStream out = response.getOutputStream();
                                FileInputStream in = new FileInputStream(path);

                                response.setContentType("application/vnd.ms-excel");
                                response.addHeader("content-disposition",
                                        "attachment; filename=" + filename);

                                int octet;
                                while((octet = in.read()) != -1)
                                    out.write(octet);

                                in.close();
                                out.close();
                               }catch(Exception e){
                                
                                e.printStackTrace();
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
