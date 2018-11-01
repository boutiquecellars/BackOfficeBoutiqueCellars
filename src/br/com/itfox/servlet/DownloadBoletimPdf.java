/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.Member;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.config.Preferences;
import br.com.itfox.utils.OsCheck;
import br.com.itfox.utils.Utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author belchiorpalma
 */
public class DownloadBoletimPdf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response){
        String path = request.getServletContext().getRealPath("/")+"/js/phantomjs/quest_boletim.pdf";
                            String filename="Quest.pdf";
                               try{
                                ServletOutputStream out = response.getOutputStream();
                                FileInputStream in = new FileInputStream(path);

                                response.setContentType("application/pdf");
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Runtime rt = Runtime.getRuntime();
        String ini = "";
                   ini = request.getParameter("ini");
        String fim="";
                   fim = request.getParameter("fim");
        String seg="";
                    seg = request.getParameter("seg");
        String subSeg="";
                    subSeg = request.getParameter("sub_seg");    
        String regional = "";
                    regional = request.getParameter("regional");            
        String areaOper = "";
                    areaOper = request.getParameter("area_oper");
        String key="";
                    key=request.getParameter("key");
        String report="";
                    report = request.getParameter("report");
        int memberId=0;            
        String script="";
        String scriptPdf="";
        String scriptPdfWindows="";
        /*
        try {
        response.getWriter().println("user email is there?: " + "fuck");
        } catch (IOException e) {
            // handle your error here

        }
        br.com.itfox.utils.Logger.getLogger("downloadPDF");
        */
        
        try {
            //response.getWriter().println("user email is there?: " );
            try{
                if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty() && areaOper !=null && !areaOper.isEmpty() && key !=null && !key.isEmpty() && report !=null && !report.isEmpty()){
                  
                    //ini = (Utils.dateFormat(ini));
                    //fim = (Utils.dateFormat(fim));
                    String p = Utils.fromHexString(key); 
                    p = p.substring(Preferences.SECURITY_KEY.length());
                    //response.getWriter().println(p);
                    memberId=Integer.parseInt(p);
                    Member m = new BusinessDelegate().selectMembers(memberId);
                    //
                    
                    String user="email="+m.getEmail()+"&password="+m.getPass()+" ";
                    //response.getWriter().println(user);
                    
                    String param="&area_oper="+areaOper+"&ini="+ini+"&fim="+fim+"&seg="+seg+"&sub_seg="+subSeg+"&regional="+Utils.encodeURI(regional)+"&print=true";
                    script = ""+request.getServletContext().getRealPath("/")+"js/phantomjs/questBoletimPdf.js "+ user+param;
                    scriptPdf = ""+request.getServletContext().getRealPath("/")+"js/phantomjs/questBoletimPdf.js "+ user+param;
                    
                    
                    
                    String mac = "/opt/local/bin/phantomjs ";
                    String windows = "c:\\bin\\phantomjs.exe ";
                    String linux="/usr/lib/phantomjs/phantomjs ";
                    String os = "";
                    OsCheck.OSType ostype=OsCheck.getOperatingSystemType();
                    switch (ostype) {
                        case Windows: 
                                    os=windows;
                                    script = "\""+request.getServletContext().getRealPath("\\")+"\\js\\phantomjs\\questBoletimPdf.js\" "+ user+param;
                                    scriptPdf = "\""+request.getServletContext().getRealPath("\\")+"\\js\\phantomjs\\questBoletimPdf_windows.js\" "+ user+param;
                                    break;
                            
                        case MacOS: 
                                    os=mac;
                                    break;
                        case Linux: 
                                    os=linux;
                                    break;
                        case Other: 
                                    os=mac;
                                    break;
                    }
                    if(report.equalsIgnoreCase("pdf")){
                        script = scriptPdf;
                    }
                    
                    try{
                        Process process = Runtime.getRuntime().exec(os+script);
                        int exitStatus = process.waitFor();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader (process.getInputStream()));

                        br.com.itfox.utils.Logger.getLogger( os+script);    

                        String currentLine=null;
                        StringBuilder stringBuilder = new StringBuilder(exitStatus==0?"SUCCESS:":"ERROR:");
                        currentLine= bufferedReader.readLine();
                        while(currentLine !=null)
                        {
                            stringBuilder.append(currentLine);
                            currentLine = bufferedReader.readLine();
                        }
                        if(stringBuilder.toString().contains("success")){
                            if(report.equalsIgnoreCase("pdf")){
                            this.downloadPDF(request, response);
                        }else{
                           // this.downloadPNG(request, response);
                            }
                        }else{
                        //System.out.println(stringBuilder.toString());
                        response.getWriter().println(script);
                        response.getWriter().println(scriptPdf);
                        response.getWriter().println(stringBuilder.toString());
                        }
                    }catch(Exception ex){
                        br.com.itfox.utils.Logger.getLogger(ex, "Erro na execução do phantom", os+script);    
                    }
                    
                }else{
                    response.getWriter().println("Parametros nulos");
                }
                
                
            }catch(Exception ex){
                // get the error details
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                
                String details=sw.toString(); // stack trace as a string
                
                response.getWriter().println(script);
                response.getWriter().println(scriptPdf);
                response.getWriter().println("Error: "+details );
                ex.printStackTrace();
            }
        } catch (IOException e) {
            // handle your error here
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            String details=sw.toString(); // stack trace as a string
            response.getWriter().println(script);
            response.getWriter().println(scriptPdf);
            response.getWriter().println("IOException Error: "+details );
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
