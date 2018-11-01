/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.AreaOper;
import br.com.itfox.beans.CollectionColumns;
import br.com.itfox.beans.Colors;
import br.com.itfox.beans.Gic;
import br.com.itfox.beans.MemberAreaOper;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.business.BusinessService;
import br.com.itfox.generator.GeneratorObjectCollection;
import br.com.itfox.utils.Utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.log4j.Level.OFF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 *
 * @author belchiorpalma
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
    public String getMemberAreaOper(HttpServletRequest request, BusinessDelegate bd){
        int memberId = (int) request.getSession().getAttribute("userid");
        List<MemberAreaOper> areas = bd.selectMemberAreaOper(memberId);
        StringBuilder strAreas= new StringBuilder();
        for(MemberAreaOper m:areas){
            strAreas.append("'"+m.getAreaoper().getArea_operacional()+"',");
        }
        return strAreas.substring(0, strAreas.length()-1).toString();
    }*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BusinessDelegate bd = new BusinessDelegate();
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        JSONObject jsonobj = new JSONObject();
        String ini = "";
                   ini = request.getParameter("ini");
        String fim="";
                   fim = request.getParameter("fim");
        String seg="";
                    seg = request.getParameter("seg");
        String subSeg="";
                    subSeg = request.getParameter("sub_seg");            
        String areaOper = "";
                    areaOper = request.getParameter("area_oper");
        String regional = "";
                    regional = request.getParameter("regional");
        String areaOperChassi="";     
        
         // se a regional for passada, entao localizar a area operacional da regional
         if(regional !=null && !regional.isEmpty() && regional.equalsIgnoreCase("todos") && areaOper != null && !areaOper.isEmpty() && areaOper.equalsIgnoreCase("todos")){
                areaOper        = BusinessService.getMemberAreaOperSeparated(request, bd);
                areaOperChassi  = areaOper;
         }else if(regional!=null && !regional.isEmpty() && regional.length()>2 && areaOper != null && !areaOper.isEmpty() && areaOper.equalsIgnoreCase("todos")){
                areaOper        = BusinessService.getMemberAreaOperSeparated(request, bd,regional);
                areaOperChassi  = areaOper;
                br.com.itfox.utils.Logger.getLogger("regional"+areaOper);
         }else{
             areaOperChassi=areaOper;
         }
         
         // localizando o segmento
         if(seg!=null && !seg.isEmpty() && seg.equalsIgnoreCase("todos")){
             String start = (Utils.dateFormat(ini));
             String finish = (Utils.dateFormat(fim));
             List<CollectionColumns> listColumns = new ArrayList<CollectionColumns>();
             if(subSeg!=null && !subSeg.isEmpty() && subSeg.equalsIgnoreCase("todos")){
                listColumns= bd.selectSegmentos(start,finish);
             }else if(subSeg!=null && !subSeg.isEmpty() && subSeg.length()>0){
                listColumns= bd.selectSegmentos(start,finish,subSeg);
             }else{
                listColumns= bd.selectSegmentos(start,finish); 
             }
             String segmento = "";
             for(CollectionColumns c: listColumns){
                segmento += "'"+c.getColumns()[0]+"',";
            }
             if(segmento!=null && !segmento.isEmpty() && segmento.length()>1){
                segmento = segmento.substring(0, segmento.length()-1);
                seg=segmento;
             }else{
                seg="'"+"todos"+"'";
             }
             
         }
        /* 
        if(areaOper != null && !areaOper.isEmpty() && areaOper.equalsIgnoreCase("todos")){
            // se for todas as áreas operacionais
            areaOper        = BusinessService.getMemberAreaOperSeparated(request, bd);
            areaOperChassi  = BusinessService.getMemberAreaOperSeparated(request, bd);
            
           
            
        }else{
            areaOperChassi=areaOper;
        }*/
        int count=0;           
        if (request.getParameter("countChassi") != null && !request.getParameter("countChassi").isEmpty()) {
            
            if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                    
                     
                     count = bd.selectCountChassi(ini, fim, seg,areaOperChassi);
                     JSONObject jsono = new JSONObject();
                     jsono.put("countChassi", count);
                     jsono.put("ini", Utils.dateFormatPt(ini));
                     jsono.put("fim", Utils.dateFormatPt(fim));
                     json.put(jsono);
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
            }
        }else if (request.getParameter("distSeg") != null && !request.getParameter("distSeg").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectDistribuicaoSeg(ini, fim, seg,areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("label", c.getColumns()[2]);
                        jsono.put("data", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
        }else if (request.getParameter("distMarca") != null && !request.getParameter("distMarca").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectDistribuicaoMarca(ini, fim, seg,areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("label", c.getColumns()[1]);
                        jsono.put("data", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
        }else if (request.getParameter("rankMunic") != null && !request.getParameter("rankMunic").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectRankMunic(ini, fim, seg,areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("label", c.getColumns()[1]);
                        jsono.put("data", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
        }else if (request.getParameter("rankModel") != null && !request.getParameter("rankModel").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectRankModel(ini, fim, seg, areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("label", c.getColumns()[1]);
                        jsono.put("data", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
        }else if (request.getParameter("selectSeg") != null && !request.getParameter("selectSeg").isEmpty()) {
             
                 try {
                     List<CollectionColumns> listColumns= bd.selectSegmentos();
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("segmento", c.getColumns()[0]);
                        jsono.put("descricao", c.getColumns()[1]);
                       
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectSegPeriod") != null && !request.getParameter("selectSegPeriod").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectSegmentos(ini,fim);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("segmento", c.getColumns()[0]);
                        jsono.put("descricao", c.getColumns()[1]);
                       
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectSegPeriodGamma") != null && !request.getParameter("selectSegPeriodGamma").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns = new ArrayList<CollectionColumns>();
                     if(subSeg!=null && !subSeg.isEmpty() && subSeg.equalsIgnoreCase("todos")){
                         listColumns= bd.selectSegmentos(ini,fim);
                     }else{
                         listColumns= bd.selectSegmentos(ini,fim,subSeg);
                     }
                     
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("segmento", c.getColumns()[0]);
                        jsono.put("descricao", c.getColumns()[1]);
                       
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectGamma") != null && !request.getParameter("selectGamma").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectSubSegmentos(ini,fim);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("subseg", c.getColumns()[0]);
                        jsono.put("subsegmento", c.getColumns()[1]);
                       
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectRegional") != null && !request.getParameter("selectRegional").isEmpty()) {
             
                 try {
                    // ini = (Utils.dateFormat(ini));
                    // fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectRegional();
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("regiao_mbb", c.getColumns()[0]);
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectRegionalPeriod") != null && !request.getParameter("selectRegionalPeriod").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectRegional(ini,fim);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("regiao_mbb", c.getColumns()[0]);
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectAreaOperRegional") != null && !request.getParameter("selectAreaOperRegional").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<AreaOper> listColumns= bd.selectAreaOper(ini, fim, regional);
                     int i=0;
                     for(AreaOper a: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("area_operacional", a.getArea_operacional());
                        jsono.put("descricao", a.getDescricao());
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectAreaOper") != null && !request.getParameter("selectAreaOper").isEmpty()) {
             
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<AreaOper> listColumns= bd.selectAreaOper();
                     int i=0;
                     for(AreaOper a: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("area_operacional", a.getArea_operacional());
                        jsono.put("descricao", a.getDescricao());
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             
        }else if (request.getParameter("selectChassiCliente") != null && !request.getParameter("selectChassiCliente").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectChassiCliente(ini, fim, seg,areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("chassis", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("clientes", Integer.parseInt(c.getColumns()[1]));
                        /*
                        jsono.put("pf", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("pj", Integer.parseInt(c.getColumns()[1]));
                        jsono.put("total", Integer.parseInt(c.getColumns()[2]));
                        */
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("selectPerfilCliente") != null && !request.getParameter("selectPerfilCliente").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectPerfilCliente(ini, fim, seg,areaOper);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        //jsono.put("chassis", Integer.parseInt(c.getColumns()[0]));
                        //jsono.put("clientes", Integer.parseInt(c.getColumns()[1]));
                        
                        jsono.put("pf", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("pj", Integer.parseInt(c.getColumns()[1]));
                        jsono.put("total", Integer.parseInt(c.getColumns()[2]));
                        
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }
        else if (request.getParameter("selectChassiClienteMontadora") != null && !request.getParameter("selectChassiClienteMontadora").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     if(request.getParameter("area_oper").equalsIgnoreCase("todos")){
                         areaOper = BusinessService.getMemberAreaOper(request, bd);
                     }
                     br.com.itfox.utils.Logger.getLogger("ini:"+ini+"|fim:"+fim+"|seg:"+seg+"|areaOper:"+areaOper+"|areaOperChassi:"+areaOperChassi);
                     
                     List<CollectionColumns> listColumns= bd.selectChassiClienteMontadora(ini, fim, seg,areaOper,areaOperChassi);
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("total", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("descricao", (c.getColumns()[1]));
                        jsono.put("marca", (c.getColumns()[2]));
                        jsono.put("clientes", (c.getColumns()[3]));
                       // jsono.put("rows_pf", Integer.parseInt(c.getColumns()[3]));
                       // jsono.put("rows_pj", Integer.parseInt(c.getColumns()[4]));
                       // jsono.put("pf", Integer.parseInt(c.getColumns()[5]));
                       // jsono.put("pj", Integer.parseInt(c.getColumns()[6]));
                        jsono.put("i",i);
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                     try {
                         JSONObject jsono = new JSONObject();
                         jsono.put("erro", "ops, que coisa feia servidor. (=");
                         json.put(jsono);
                          br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                         Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (JSONException ex1) {
                          br.com.itfox.utils.Logger.getLogger(ex1, DashboardServlet.class.getName(),ex1.getMessage());
                         Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex1);
                     }
                 }catch(Exception ex){
                     try {
                         JSONObject jsono = new JSONObject();
                         jsono.put("erro", "ops, que coisa feia servidor. (=");
                         json.put(jsono);
                         Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                          br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     } catch (JSONException ex1) {
                          br.com.itfox.utils.Logger.getLogger(ex1, DashboardServlet.class.getName(),ex1.getMessage());
                         Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex1);
                     }
                 }
                 finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("selectGic") != null && !request.getParameter("selectGic").isEmpty()) {
             
                 try {
                     try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream("TemplateGic.xlsx")) 
                     {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
                            String date = sdf.format(new Date()); 
                            
                            try (OutputStream os = new FileOutputStream(request.getServletContext().getRealPath("/")+"relatorio_enriquecimento_"+date+".xls")) 
                            {
                                Context context = new Context();
                                //BusinessDelegate bd = new BusinessDelegate();
                                ini = (Utils.dateFormat(ini));
                                fim = (Utils.dateFormat(fim));
                                List<Gic> gics = bd.selectGic(ini, fim, seg, areaOper);
                                //org.apache.log4j.Logger.getRootLogger().setLevel(OFF);
                                context.putVar("gics", gics);
                                JxlsHelper.getInstance().processTemplate(is, os, context);
                               // System.out.println("tamanho da lista:"+gics.size());
                                JSONObject jsono = new JSONObject();
                                jsono.put("gics", gics.size());
                                jsono.put("path","relatorio_enriquecimento_"+date+".xls");
                                jsono.put("ini",ini);
                                jsono.put("fim",fim);
                                jsono.put("seg",seg);
                                json.put(jsono);
                                // imprimindo o json
                                // writer.write(json.toString());
                                // writer.close();
                                // exibindo o arquivo
                                //try to flush memory
                                //gics=null;
                                //context=null;
                                //System.gc();
                            }catch(Exception e){
                                 br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage());
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }

                        }catch(Exception e){
                             br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage());
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }
                     
                        
                        
                     
                 }catch(JSONException ex){
                     
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
        }else if (request.getParameter("selectGicChassi") != null && !request.getParameter("selectGicChassi").isEmpty()) {
             
                 try{
                     String keyword = request.getParameter("keyword");
                     if(keyword!=null && keyword.length()>2){
                        
                        //strAreas.substring(0, strAreas.length()-1);
                        List<Gic> gics = bd.selectGicChassi(keyword,BusinessService.getMemberAreaOperSeparated(request, bd));   
                        for(Gic g: gics){
                           JSONObject jsono = new JSONObject();
                           //String data = Utils.dateFormatPt();
                           
                           jsono.put("Data", g.getData_completa()==null?"": Utils.dateFormatPt2(g.getData_completa()));
                           jsono.put("Chassi", g.getChassi()==null? "":g.getChassi());
                           jsono.put("Modelo",g.getModelo()==null?"":g.getModelo());
                           jsono.put("Marca",g.getMarca_completa()==null?"":g.getMarca_completa());
                          // jsono.put("Data",g.getData_completa()==null?"":g.getData_completa());
                           jsono.put("Segmento",g.getSegmento()==null?"":g.getSegmento());
                           jsono.put("SubSegmento",g.getSubsegmento()==null?"":g.getSubsegmento());
                           jsono.put("Placa", g.getPlaca()==null?"":g.getPlaca());
                           jsono.put("Concessionario",g.getRazao_social()==null?"":g.getRazao_social());// está faltando
                           jsono.put("Cidade",g.getC_no_cidade()==null?"":g.getC_no_cidade());
                           jsono.put("AnoFabricacao",g.getAnofabricacao()==null?"":g.getAnofabricacao());
                           jsono.put("AnoModelo",g.getAnomodelo()==null?"":g.getAnomodelo());
                           jsono.put("CPF",g.getC_cpfcnpjproprietario()==null?"":Utils.formatCpfCnpj(g.getC_cpfcnpjproprietario()));
                           jsono.put("Tipo",g.getC_tipocnpjproprietario()==null?"":g.getC_tipocnpjproprietario());
                           jsono.put("Nome", g.getC_nomeproprietario()==null?"":g.getC_nomeproprietario());
                           jsono.put("Endereco",g.getC_no_logr()==null?"":g.getC_no_logr());
                           jsono.put("Numero",g.getC_nu_logr()==null?"":g.getC_nu_logr());
                           jsono.put("Complemento",g.getC_no_compl()==null? "":g.getC_no_compl());
                           jsono.put("Bairro",g.getC_no_bairro()==null?"":g.getC_no_bairro());
                           jsono.put("Cidade",g.getC_no_cidade()==null?"":g.getC_no_cidade());
                           jsono.put("Estado",g.getC_sg_estado()==null?"":g.getC_sg_estado());
                           jsono.put("Cep",g.getC_nu_cep()==null?"":Utils.formatCpfCnpj(g.getC_nu_cep()));
                           jsono.put("DDD1",g.getC_ddd1()==null? "":Utils.formatTelephone(g.getC_ddd1()));
                           jsono.put("Telefone1",g.getC_telefone1()==null?"":Utils.formatTelephone(g.getC_telefone1()));
                           jsono.put("DDD2",g.getC_ddd2()==null? "":Utils.formatTelephone(g.getC_ddd2()));
                           jsono.put("Telefone2",g.getC_telefone2()==null?"":Utils.formatTelephone(g.getC_telefone2()));
                           jsono.put("DDD3",g.getC_ddd3()==null?"":Utils.formatTelephone(g.getC_ddd3()));
                           jsono.put("Telefone3",g.getC_telefone3()==null?"":Utils.formatTelephone(g.getC_telefone3()));
                           jsono.put("DDD4",g.getC_ddd4()==null?"":Utils.formatTelephone(g.getC_ddd4()));
                           jsono.put("Telefone4",g.getC_telefone4()==null? "":Utils.formatTelephone(g.getC_telefone4()));
                           jsono.put("DDD5",g.getC_ddd1()==null? "":Utils.formatTelephone(g.getC_ddd1()));
                           jsono.put("Telefone5",g.getC_telefone5()==null?"": Utils.formatTelephone(g.getC_telefone5()));
                           jsono.put("Email1",g.getC_email()==null?"": g.getC_email());
                           jsono.put("Atividade",g.getAtividade_economica_secundaria()==null?"": g.getAtividade_economica_secundaria());
                           jsono.put("i","x"); 
                           //jsono.put("COLOR", new Colors().getColors()[i]);
                         //  jsono.put("ini", Utils.dateFormatPt(ini));
                         //  jsono.put("fim", Utils.dateFormatPt(fim));
                           json.put(jsono);

                        }   
                     }
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                    
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
        }else if (request.getParameter("selectGicChassiBatch") != null && !request.getParameter("selectGicChassiBatch").isEmpty()) {
             
                 try{
                     String keyword = request.getParameter("keyword");
                     //if(keyword!=null && keyword.length()>2){
                        
                        //strAreas.substring(0, strAreas.length()-1);
                        List<Gic> gics = bd.selectGicChassiBatch(keyword,BusinessService.getMemberAreaOperSeparated(request, bd));   
                        for(Gic g: gics){
                           JSONObject jsono = new JSONObject();
                           jsono.put("Data", g.getData_completa());
                           jsono.put("Chassi", g.getChassi());
                           jsono.put("Placa", g.getPlaca());
                           jsono.put("Nome", g.getC_nomeproprietario()==null?"":g.getC_nomeproprietario());
                           jsono.put("i",""); 
                           //jsono.put("COLOR", new Colors().getColors()[i]);
                         //  jsono.put("ini", Utils.dateFormatPt(ini));
                         //  jsono.put("fim", Utils.dateFormatPt(fim));
                           json.put(jsono);

                        }   
                     //}
                     
                 }catch(JSONException ex){
                      br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                    
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
        }
        else if (request.getParameter("selectTopCliente") != null && !request.getParameter("selectTopCliente").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     String segment = request.getParameter("seg");
                     if(segment!=null && !segment.isEmpty() && segment.equalsIgnoreCase("todos")){
                        listColumns=bd.selectTopClientesTodos(ini, fim, seg, areaOper);
                        int i=0;
                        for(CollectionColumns c: listColumns){
                           JSONObject jsono = new JSONObject();
                           jsono.put("col0", c.getColumns()[0]);
                           jsono.put("col1", Integer.parseInt(c.getColumns()[1]));
                           jsono.put("col2", Integer.parseInt(c.getColumns()[2]));
                           jsono.put("col3", Integer.parseInt(c.getColumns()[3]));
                           jsono.put("col4", Integer.parseInt(c.getColumns()[4]));
                           jsono.put("col5", Integer.parseInt(c.getColumns()[5]));
                           jsono.put("col6", Integer.parseInt(c.getColumns()[6]));
                           jsono.put("col7", Integer.parseInt(c.getColumns()[7]));
                           jsono.put("col8", Integer.parseInt(c.getColumns()[8]));
                           jsono.put("col9", Integer.parseInt(c.getColumns()[9]));
                           jsono.put("col10", Integer.parseInt(c.getColumns()[10]));
                           jsono.put("col11", Integer.parseInt(c.getColumns()[11]));
                           jsono.put("col12", Integer.parseInt(c.getColumns()[12]));
                           //jsono.put("COLOR", new Colors().getColors()[i]);
                         //  jsono.put("ini", Utils.dateFormatPt(ini));
                         //  jsono.put("fim", Utils.dateFormatPt(fim));
                           json.put(jsono);
                           i++;
                        }
                     }else{
                        listColumns = bd.selectSegmentos(seg);
                        String tipo_veic = listColumns.get(0).getColumns()[4];
                        if(Integer.parseInt(tipo_veic)==2){
                           listColumns=bd.selectTopClientesVans(ini, fim, seg, areaOper);
                        }else if(Integer.parseInt(tipo_veic)==3){
                           listColumns=bd.selectTopClientesCaminhoes(ini, fim, seg, areaOper);
                        }else if(Integer.parseInt(tipo_veic)==4){
                           listColumns=bd.selectTopClientesOnibus(ini, fim, seg, areaOper);
                        }
                        int i=0;
                        for(CollectionColumns c: listColumns){
                           JSONObject jsono = new JSONObject();
                           jsono.put("col0", c.getColumns()[0]);
                           jsono.put("col1", Integer.parseInt(c.getColumns()[1]));
                           jsono.put("col2", Integer.parseInt(c.getColumns()[2]));
                           jsono.put("col3", Integer.parseInt(c.getColumns()[3]));
                           jsono.put("col4", Integer.parseInt(c.getColumns()[4]));
                           jsono.put("col5", Integer.parseInt(c.getColumns()[5]));
                           jsono.put("col6", Integer.parseInt(c.getColumns()[6]));
                           jsono.put("col7", Integer.parseInt(c.getColumns()[7]));
                           jsono.put("col8", Integer.parseInt(c.getColumns()[8]));
                           jsono.put("col9", Integer.parseInt(c.getColumns()[9]));
                           //jsono.put("COLOR", new Colors().getColors()[i]);
                         //  jsono.put("ini", Utils.dateFormatPt(ini));
                         //  jsono.put("fim", Utils.dateFormatPt(fim));
                           json.put(jsono);
                           i++;
                        }
                     }
                     
                     /*
                        JSONObject jsono = new JSONObject();
                        jsono.put("seg", seg);
                        jsono.put("area",areaOper);
                        jsono.put("size",listColumns.size());
                        jsono.put("ini", Utils.dateFormatPt(ini));
                        jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                     */
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("searchCnpjVinculado") != null && !request.getParameter("searchCnpjVinculado").isEmpty()) {
                 String keyword = request.getParameter("keyword");
                 if(keyword != null && !keyword.isEmpty() ){
                 try {
                     
                     List<CollectionColumns> listColumns= null;
                     
                     {
                        
                        listColumns = bd.searchCnpjVinculado(keyword);
                        
                        for(CollectionColumns c: listColumns){
                           String cpfcnpj = c.getColumns()[1];
                                  cpfcnpj = Utils.formatCpfCnpj(cpfcnpj);
                           JSONObject jsono = new JSONObject();
                           jsono.put("nome", c.getColumns()[0]);
                           jsono.put("cpfcnpj", cpfcnpj);
                           jsono.put("cpf", c.getColumns()[1]);
                           jsono.put("municipio", c.getColumns()[2]);
                           jsono.put("estado",c.getColumns()[3]);
                           jsono.put("totals",listColumns.size());
                           jsono.put("keyword",Utils.formatCpfCnpj(keyword));
                           json.put(jsono);
                           
                        }
                     }
                     
                            
                     
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("searchCnpjByYear") != null && !request.getParameter("searchCnpjByYear").isEmpty()) {
                 String keyword = request.getParameter("keyword");
                 if(keyword != null && !keyword.isEmpty() ){
                 try {
                     
                     List<CollectionColumns> listColumns= null;
                     {
                        
                        listColumns = bd.searchCnpjByYear(keyword);
                        int i=0;
                        float totals=0, s2014=0, s2015=0, s2016=0;
                        for(CollectionColumns c: listColumns){
                            totals+=Float.parseFloat(c.getColumns()[7]);
                            s2014+=Float.parseFloat(c.getColumns()[1]);
                            s2015+=Float.parseFloat(c.getColumns()[3]);
                            s2016+=Float.parseFloat(c.getColumns()[5]);
                        }
                        for(CollectionColumns c: listColumns){
                           JSONObject jsono = new JSONObject();
                           jsono.put("marca", c.getColumns()[0]);
                           jsono.put("s2014", Float.parseFloat(c.getColumns()[1]));
                           jsono.put("t2014", Float.parseFloat(c.getColumns()[2]));
                           jsono.put("s2015", Float.parseFloat(c.getColumns()[3]));
                           jsono.put("t2015", Float.parseFloat(c.getColumns()[4]));
                           jsono.put("s2016", Float.parseFloat(c.getColumns()[5]));
                           jsono.put("t2016", Float.parseFloat(c.getColumns()[6]));
                           jsono.put("total", Float.parseFloat(c.getColumns()[7]));
                           jsono.put("ini", Integer.parseInt("2014"));
                           jsono.put("fim", Integer.parseInt("2016"));
                           jsono.put("totals", totals);
                           jsono.put("other", "<b> TOTAL 2014: "+s2014+" - TOTAL 2015: "+s2015+" - TOTAL 2016:"+s2016+"</b>");
                           json.put(jsono);
                           i++;
                        }
                     }
                     
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("searchCnpjByLastYear") != null && !request.getParameter("searchCnpjByLastYear").isEmpty()) {
                 String keyword = request.getParameter("keyword");
                 if(keyword != null && !keyword.isEmpty() ){
                 try {
                    // ini = (Utils.dateFormat(ini));
                    // fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     {
                        
                        listColumns = bd.searchCnpjByLastYear(keyword);
                        float totals=0, sjan, sfev, smar, sabr,smai, sjun, sjul, sago, sset, sout, snov, sdez=sjan=sfev=smar=sabr=smai=sjun=sjul=sago=sset=sout=snov=0;
                        for(CollectionColumns c: listColumns){
                            totals+=Float.parseFloat(c.getColumns()[13]);
                            sjan+=Float.parseFloat(c.getColumns()[1]);
                            sfev+=Float.parseFloat(c.getColumns()[2]);
                            smar+=Float.parseFloat(c.getColumns()[3]);
                            sabr+=Float.parseFloat(c.getColumns()[4]);
                            smai+=Float.parseFloat(c.getColumns()[5]);
                            sjun+=Float.parseFloat(c.getColumns()[6]);
                            sjul+=Float.parseFloat(c.getColumns()[7]);
                            sago+=Float.parseFloat(c.getColumns()[8]);
                            sset+=Float.parseFloat(c.getColumns()[9]);
                            sout+=Float.parseFloat(c.getColumns()[10]);
                            snov+=Float.parseFloat(c.getColumns()[11]);
                            sdez+=Float.parseFloat(c.getColumns()[12]);
                            
                        }
                        int i=0;
                       // DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                       // df.setMaximumFractionDigits(2);
                        for(CollectionColumns c: listColumns){
                           JSONObject jsono = new JSONObject();
                           jsono.put("marca", c.getColumns()[0]);
                           jsono.put("jan", Float.parseFloat(c.getColumns()[1]));
                           jsono.put("tjan", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[1])/sjan*100)));
                           jsono.put("sjan", sjan);
                           jsono.put("fev", Float.parseFloat(c.getColumns()[2]));
                           jsono.put("tfev",String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[2])/sfev*100)));
                           jsono.put("sfev", sfev);
                           jsono.put("mar", Float.parseFloat(c.getColumns()[3]));
                           jsono.put("tmar",String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[3])/smar*100)));
                           jsono.put("smar", smar);
                           jsono.put("abr", Float.parseFloat(c.getColumns()[4]));
                           jsono.put("tabr",String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[4])/sabr*100)));
                           jsono.put("sabr", sabr);
                           jsono.put("mai", Float.parseFloat(c.getColumns()[5]));
                           jsono.put("tmai",String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[5])/smai*100)));
                           jsono.put("smai", smai);
                           jsono.put("jun", Float.parseFloat(c.getColumns()[6]));
                           jsono.put("tjun", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[6])/sjun*100)));
                           jsono.put("sjun", sjun);
                           jsono.put("jul", Float.parseFloat(c.getColumns()[7]));
                           jsono.put("tjul", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[7])/sjul*100)));
                           jsono.put("sjul", sjul);
                           jsono.put("ago", Float.parseFloat(c.getColumns()[8]));
                           jsono.put("tago", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[8])/sago*100)));
                           jsono.put("sago", sago);
                           jsono.put("set", Float.parseFloat(c.getColumns()[9]));
                           jsono.put("tset", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[9])/sset*100)));
                           jsono.put("sset", sset);
                           jsono.put("out", Float.parseFloat(c.getColumns()[10]));
                           jsono.put("tout", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[10])/sout*100)));
                           jsono.put("sout", sout);
                           jsono.put("nov", Float.parseFloat(c.getColumns()[11]));
                           jsono.put("tnov", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[11])/snov*100)));
                           jsono.put("snov", snov);
                           jsono.put("dez", Float.parseFloat(c.getColumns()[12]));
                           jsono.put("tdez", String.valueOf(Utils.formatDecimal(Float.parseFloat(c.getColumns()[12])/sdez*100)));
                           jsono.put("sdez", sdez);
                           jsono.put("total", Integer.parseInt(c.getColumns()[13]));
                           jsono.put("ini", "MAIO/2016");
                           jsono.put("fim", "JUN/2015");
                           jsono.put("totals", totals);
                           jsono.put("other", "<b>TOTAL JAN:"+sjan + " TOTAL FEV:"+sfev+" TOTAL MAR:"+smar+" TOTAL ABR:"+sabr+" TOTAL MAI:"+ smai+" TOTAL JUN:"+sjun+" TOTAL JUL:"+sjul+" TOTAL AGO:"+ sago+" TOTAL SET: "+ sset+ " TOTAL OUT:"+ sout+" TOTAL NOV:"+snov+" TOTAL DEZ:"+sdez+"</b>");
                           json.put(jsono);
                           i++;
                        }
                     }
                     
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("searchNomeProprietario") != null && !request.getParameter("searchNomeProprietario").isEmpty()) {
                 String keyword = request.getParameter("keyword");
                 if(keyword != null && !keyword.isEmpty() ){
                 try {
                     
                     List<CollectionColumns> listColumns= null;
                     
                     {
                        
                        listColumns = bd.searchNomeProprietario(keyword);
                        
                        
                        for(CollectionColumns c: listColumns){
                           String cpfcnpj = c.getColumns()[1];
                                  cpfcnpj = Utils.formatCpfCnpj(cpfcnpj);
                           JSONObject jsono = new JSONObject();
                           String a = "<a href=\"search-cnpj.jsp?keyword="+c.getColumns()[1]+"\">"+c.getColumns()[0]+"</a>";
                           jsono.put("nome", a);
                           jsono.put("cpfcnpj", cpfcnpj);
                           jsono.put("cpf", c.getColumns()[1]);
                           jsono.put("municipio", c.getColumns()[2]);
                           jsono.put("estado",c.getColumns()[3]);
                           jsono.put("totals",listColumns.size());
                           jsono.put("keyword",keyword);
                           
                           json.put(jsono);
                           
                            
                        }
                     }
                     
                            
                     
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                     try {
                         // adding data
                         jsonobj.put("data", json);
                     } catch (JSONException ex) {
                         Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    writer.write(jsonobj.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("clickToView") != null && !request.getParameter("clickToView").isEmpty()) {
                 String keyword = request.getParameter("keyword");
                 if(keyword != null && !keyword.isEmpty() ){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     {
                        
                        listColumns = bd.clickToView(ini, fim, seg, areaOper, keyword);
                        
                        
                        for(CollectionColumns c: listColumns){
                           JSONObject jsono = new JSONObject();
                           jsono.put("chassi", c.getColumns()[0]);
                           jsono.put("data", c.getColumns()[1]);
                           jsono.put("marca", c.getColumns()[2]);
                           jsono.put("modelo", c.getColumns()[3]);
                           jsono.put("segmento", c.getColumns()[4]);
                           jsono.put("subsegmento", c.getColumns()[5]);
                           jsono.put("placa", c.getColumns()[6]);
                           jsono.put("municipio", c.getColumns()[7]);
                           jsono.put("estado", c.getColumns()[8]);
                           jsono.put("dealer_aop", c.getColumns()[9]);
                           jsono.put("tipo_veic", c.getColumns()[10]);
                           json.put(jsono);
                           
                        }
                     }
                     
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("selectSegmentoTipoVeic") != null && !request.getParameter("selectSegmentoTipoVeic").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     listColumns = bd.selectSegmentos(seg);
                     if(listColumns.size()>0){
                        seg = listColumns.get(0).getColumns()[4];
                        JSONObject jsono = new JSONObject();
                        jsono.put("tipo_veic", seg);
                        jsono.put("seg", listColumns.get(0).getColumns()[0]);
                        json.put(jsono);
                     }else{
                     
                        JSONObject jsono = new JSONObject();
                        jsono.put("tipo_veic", seg);
                        jsono.put("seg", seg);
                        json.put(jsono);
                     }   
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("selectEnriquecerArquivos") != null && !request.getParameter("selectEnriquecerArquivos").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     listColumns=bd.selectEnriquecerArquivos(ini, fim, seg);
                     
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("col0", c.getColumns()[0]);
                        jsono.put("col1", (c.getColumns()[1]));
                        jsono.put("col2", (c.getColumns()[2]));
                        
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                     /*
                     //  excel generate
                     try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream("TemplateEnriquecer.xlsx")) 
                     {
                            try (OutputStream os = new FileOutputStream(request.getServletContext().getRealPath("/")+"relatorio_enriquecer.xls")) 
                            {
                                Context context = new Context();
                                //BusinessDelegate bd = new BusinessDelegate();
                                //ini = (Utils.dateFormat(ini));
                                //fim = (Utils.dateFormat(fim));
                                List<Gic> gics = new ArrayList<Gic>();
                                for(CollectionColumns c: listColumns){
                                    Gic g = new Gic();
                                    g.setChassi(c.getColumns()[1]);
                                    g.setData_completa(c.getColumns()[0]);
                                    g.setPlaca(c.getColumns()[2]);
                                    gics.add(g);
                                }
                                context.putVar("gics", gics);
                                JxlsHelper.getInstance().processTemplate(is, os, context);
                               // System.out.println("tamanho da lista:"+gics.size());
                               // JSONObject jsono = new JSONObject();
                               // jsono.put("gics", gics.size());
                               // jsono.put("path",request.getServletContext().getRealPath("/"));
                               // jsono.put("ini",ini);
                               // jsono.put("fim",fim);
                               // jsono.put("seg",seg);
                               // json.put(jsono);
                                // imprimindo o json
                                // writer.write(json.toString());
                                // writer.close();
                                // exibindo o arquivo
                                
                            }catch(Exception e){
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }

                        }catch(Exception e){
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }
                     */
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("downloadEnriquecerArquivos") != null && !request.getParameter("downloadEnriquecerArquivos").isEmpty()) {
             
                 if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= null;
                     listColumns=bd.selectEnriquecerArquivos(ini, fim, seg);
                     
                     int i=0;
                     /*
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("col0", c.getColumns()[0]);
                        jsono.put("col1", (c.getColumns()[1]));
                        jsono.put("col2", (c.getColumns()[2]));
                        
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     */
                     
                     //  excel generate
                     try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream("TemplateEnriquecer.xlsx")) 
                     {
                            try (OutputStream os = new FileOutputStream(request.getServletContext().getRealPath("/")+"relatorio_enriquecer.xls")) 
                            {
                                Context context = new Context();
                                //BusinessDelegate bd = new BusinessDelegate();
                                //ini = (Utils.dateFormat(ini));
                                //fim = (Utils.dateFormat(fim));
                                List<Gic> gics = new ArrayList<Gic>();
                                for(CollectionColumns c: listColumns){
                                    Gic g = new Gic();
                                    g.setChassi(c.getColumns()[1]);
                                    g.setData_completa(c.getColumns()[0]);
                                    g.setPlaca(c.getColumns()[2]);
                                    gics.add(g);
                                }
                                context.putVar("gics", gics);
                                JxlsHelper.getInstance().processTemplate(is, os, context);
                               // System.out.println("tamanho da lista:"+gics.size());
                                JSONObject jsono = new JSONObject();
                                jsono.put("gics", gics.size());
                                jsono.put("path",request.getServletContext().getRealPath("/"));
                                jsono.put("ini",ini);
                                jsono.put("fim",fim);
                                jsono.put("seg",seg);
                                json.put(jsono);
                                // imprimindo o json
                                // writer.write(json.toString());
                                // writer.close();
                                // exibindo o arquivo
                                
                            }catch(Exception e){
                                br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage()); 
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }

                        }catch(Exception e){
                                br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage()); 
                                JSONObject jsono = new JSONObject();
                                jsono.put("Erro", e.getMessage());
                                json.put(jsono);
                            }
                     
                 }catch(JSONException ex){
                     br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage()); 
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                    writer.write(json.toString());
                    writer.close();
                }
             }
             
        }else if (request.getParameter("dateUpdate") != null && !request.getParameter("dateUpdate").isEmpty()) {
             
                 try {
                                //BusinessDelegate bd = new BusinessDelegate();
                                ini = (Utils.dateFormat(ini));
                                fim = (Utils.dateFormat(fim));
                                String dateUpdate = bd.dateUpdate(ini, fim, seg, areaOper);
                               
                               // System.out.println("tamanho da lista:"+gics.size());
                                JSONObject jsono = new JSONObject();
                                jsono.put("dateUpdate", dateUpdate);
                                jsono.put("ini",ini);
                                jsono.put("fim",fim);
                                jsono.put("seg",seg);
                                jsono.put("area",areaOper);
                                json.put(jsono);
                                // imprimindo o json
                                // writer.write(json.toString());
                                // writer.close();
                                // exibindo o arquivo
                                
                           

                    }catch(JSONException ex){
                        br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName(),ex.getMessage());
                        Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }catch(Exception e){
                             br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage());
                             
                    }
                    finally {
                        writer.write(json.toString());
                        writer.close();
                    }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
