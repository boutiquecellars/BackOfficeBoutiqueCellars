/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.servlet;

import br.com.itfox.beans.CollectionColumns;
import br.com.itfox.beans.Colors;
import br.com.itfox.business.BDBoletim;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.business.BusinessDelegateBoletim;
import br.com.itfox.business.BusinessService;
import br.com.itfox.config.Preferences;
import br.com.itfox.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
public class DashboardBoletim extends HttpServlet {
    private float total=0;
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
        BusinessDelegate bd = new BusinessDelegate();
        BusinessDelegateBoletim bdBoletim = new BusinessDelegateBoletim();
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
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
         // tratando o subsegmento
         if(subSeg!=null && !subSeg.isEmpty() && subSeg.equalsIgnoreCase("todos")){
             List<CollectionColumns> listColumns = new ArrayList<CollectionColumns>();
             listColumns= bd.selectSubSegmentos();
             String subSegmento = "";
             for(CollectionColumns c: listColumns){
                subSegmento += "'"+c.getColumns()[0]+"',";
            }
             if(subSegmento!=null && !subSegmento.isEmpty() && subSegmento.length()>1){
                subSegmento = subSegmento.substring(0, subSegmento.length()-1);
                subSeg=subSegmento;
             }else{
                subSeg="'"+"todos"+"'";
             }
         }
        int count=0;           
        if (request.getParameter("clientesAByMarca") != null && !request.getParameter("clientesAByMarca").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.clienteAByMarca(ini, fim, seg,areaOper);
                    // List<CollectionColumns> clearList= new ArrayList<CollectionColumns>();
                    // List<CollectionColumns> otherList= new ArrayList<CollectionColumns>();
                    // List<CollectionColumns> totalList= new ArrayList<CollectionColumns>();
                    // List<List<CollectionColumns>> fullList= new ArrayList<List<CollectionColumns>>(); 
                    // fullList = this.combineValues(listColumns);
                     
                     //clearList = (List<CollectionColumns>) fullList.get(0);
                     //otherList = (List<CollectionColumns>) fullList.get(1);
                     //totalList = (List<CollectionColumns>) fullList.get(2);
                     /*
                     String outros = "<b>OBSERVAÇÃO: OUTROS</b> = ";
                     for(CollectionColumns c: otherList){
                         outros+= c.getColumns()[1]+": "+Integer.parseInt(c.getColumns()[0])+" - ";
                     }
                     outros = outros.substring(0, outros.length()-3);
                     if(otherList.size()==0){
                         outros="";
                     }
                     int i=0;
                     for(CollectionColumns c: clearList){
                        JSONObject jsono = new JSONObject();
                        // formato decimal americano
                        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                        df.setMaximumFractionDigits(2);
                        // variavel a receber os valores
                        String mkt = "";
                        if(c.getColumns()[2].length()>2){
                           BigDecimal percent = new BigDecimal(c.getColumns()[2]);
                            mkt = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }
                        String tot = "";
                        // formato decimal americano
                        df = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt", "BR"));
                        df.setMaximumFractionDigits(2);
                        tot = String.valueOf(Utils.formatDecimal(new BigDecimal(totalList.get(0).getColumns()[0])));
                        jsono.put("label", c.getColumns()[1]);
                        jsono.put("i", i);
                        jsono.put("data", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("mkt",  mkt );
                        jsono.put("color", new Colors().getColors()[i]);
                        jsono.put("ini", Utils.dateFormatPt(ini));
                        jsono.put("fim", Utils.dateFormatPt(fim));
                        jsono.put("total",tot);
                        jsono.put("other", outros);
                        json.put(jsono);
                        i++;
                     }*/
                     BigDecimal total_geral = new BigDecimal(bdBoletim.clienteA(ini, fim, seg,areaOperChassi));//sum(listColumns,1);
                     BigDecimal total = sum(listColumns,1);
                     List<CollectionColumns> clientList = bd.selectChassiCliente(ini, fim, seg, areaOper);
                     if(clientList.size()>0){
                         count = Integer.parseInt(clientList.get(0).getColumns()[1]);
                     }
                     //count = bd.selectCountChassi(ini, fim, seg,areaOperChassi);
                     BigDecimal total_perc = total_geral.divide(new BigDecimal(count),4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                     
                     for(CollectionColumns c: listColumns){
                         JSONObject jsono = new JSONObject();
                         jsono.put("marca", c.getColumns()[0]);
                         jsono.put("valor", c.getColumns()[1]);
                         
                        // DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         
                         //df.setMaximumFractionDigits(2);
                       //  df.setMaximumFractionDigits(2);
                       //  df.setMinimumFractionDigits(2);
                        // df.setMinimumIntegerDigits(2);
                        // df.setRoundingMode(RoundingMode.HALF_UP);
                         String mkt= "";
                         if(c.getColumns()[1].length()>=1){
                           BigDecimal percent = new BigDecimal(c.getColumns()[1]);
                           
                           percent = percent.divide(total,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           mkt = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }
                         jsono.put("perc", mkt+"%");
                         jsono.put("totals", total_geral);
                         jsono.put("totals_perc", String.valueOf(Utils.formatDecimal(total_perc))+"%");
                         json.put(jsono);
                     }
                     /*
                     JSONObject jsono = new JSONObject();
                         jsono.put("ini",ini);
                         jsono.put("fim", fim);
                         jsono.put("seg", seg);
                         jsono.put("subseg", subSeg);
                         jsono.put("regional", regional);
                         jsono.put("area", areaOper);
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
        }else if (request.getParameter("clientesBByMarca") != null && !request.getParameter("clientesBByMarca").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.clienteBByMarca(ini, fim, seg,areaOper);
                    
                     
                     BigDecimal total_geral = new BigDecimal(bdBoletim.clienteB(ini, fim, seg,areaOperChassi));//sum(listColumns,1);
                     BigDecimal total = sum(listColumns,1);
                     List<CollectionColumns> clientList = bd.selectChassiCliente(ini, fim, seg, areaOper);
                     if(clientList.size()>0){
                         count = Integer.parseInt(clientList.get(0).getColumns()[1]);
                     }
                     //count = bd.selectCountChassi(ini, fim, seg,areaOperChassi);
                     BigDecimal total_perc = total_geral.divide(new BigDecimal(count),4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                     
                     for(CollectionColumns c: listColumns){
                         JSONObject jsono = new JSONObject();
                         jsono.put("marca", c.getColumns()[0]);
                         jsono.put("valor", c.getColumns()[1]);
                         
                         //DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         // df.setMaximumFractionDigits(2);
                         //df.setMinimumFractionDigits(2);
                         //df.setMinimumIntegerDigits(2);
                         //df.setRoundingMode(RoundingMode.HALF_UP);
                         String mkt= "";
                         if(c.getColumns()[1].length()>=1){
                           BigDecimal percent = new BigDecimal(c.getColumns()[1]);
                           
                           percent = percent.divide(total,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           mkt = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }
                         jsono.put("perc", mkt+"%");
                         jsono.put("totals", total_geral);
                         jsono.put("totals_perc", String.valueOf(Utils.formatDecimal(total_perc))+"%");
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
        }else if (request.getParameter("clientesCByMarca") != null && !request.getParameter("clientesCByMarca").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.clienteCByMarca(ini, fim, seg,areaOper);
                    
                     
                     BigDecimal total_geral = new BigDecimal(bdBoletim.clienteC(ini, fim, seg,areaOperChassi));//sum(listColumns,1);
                     BigDecimal total = sum(listColumns,1);
                     List<CollectionColumns> clientList = bd.selectChassiCliente(ini, fim, seg, areaOper);
                     if(clientList.size()>0){
                         count = Integer.parseInt(clientList.get(0).getColumns()[1]);
                     }
                     //count = bd.selectCountChassi(ini, fim, seg,areaOperChassi);
                     BigDecimal total_perc = total_geral.divide(new BigDecimal(count),4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                     
                     for(CollectionColumns c: listColumns){
                         JSONObject jsono = new JSONObject();
                         jsono.put("marca", c.getColumns()[0]);
                         jsono.put("valor", c.getColumns()[1]);
                         
                         //DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         // df.setMaximumFractionDigits(2);
                         //df.setMinimumFractionDigits(2);
                        // df.setMinimumIntegerDigits(2);
                        // df.setRoundingMode(RoundingMode.HALF_UP);
                         String mkt= "";
                         if(c.getColumns()[1].length()>=1){
                           BigDecimal percent = new BigDecimal(c.getColumns()[1]);
                           
                           percent = percent.divide(total,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           mkt = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }
                         jsono.put("perc", mkt+"%");
                         jsono.put("totals", total_geral);
                         jsono.put("totals_perc", String.valueOf(Utils.formatDecimal(total_perc))+"%");
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
        }else if (request.getParameter("clientesABCByGama") != null && !request.getParameter("clientesABCByGama").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.clienteABCByGama(ini, fim, seg,areaOper);
                     
                     BigDecimal total_leves = sum(listColumns,1);
                     BigDecimal total_medios = sum(listColumns,2);
                     BigDecimal total_semipesados = sum(listColumns,3);
                     BigDecimal total_pesados = sum(listColumns,4);
                     //count = bd.selectCountChassi(ini, fim, seg,areaOperChassi);
                     //BigDecimal total_perc = total.divide(new BigDecimal(count),4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                     /*
                     JSONObject jsono = new JSONObject();
                     jsono.put("segmento", seg);
                     jsono.put("leves", total_leves);
                     jsono.put("medios", total_medios);
                     jsono.put("semipesados", total_semipesados);
                     jsono.put("pesados", total_pesados);
                     json.put(jsono);
                     */
                     
                     for(CollectionColumns c: listColumns){
                         JSONObject jsono = new JSONObject();
                         jsono.put("clientes", c.getColumns()[0]);
                         jsono.put("leves", c.getColumns()[1]);
                         jsono.put("medios", c.getColumns()[2]);
                         jsono.put("semipesados", c.getColumns()[3]);
                         jsono.put("pesados", c.getColumns()[4]);
                        
                         //DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         //df.setMaximumFractionDigits(2);
                         //df.setMinimumFractionDigits(2);
                         //df.setMinimumIntegerDigits(2);
                         //df.setRoundingMode(RoundingMode.HALF_UP);
                         String leves_= "";
                         String medios_="";
                         String pesados_="";
                         String semipesados_="";
                         if(c.getColumns()[1].length()>=1 && !c.getColumns()[1].equalsIgnoreCase("0")){
                           BigDecimal percent = new BigDecimal(c.getColumns()[1]);
                           percent = percent.divide(total_leves,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           leves_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }else{
                           leves_ = "00.00";  
                         }
                        if(c.getColumns()[2].length()>=1 && !c.getColumns()[2].equalsIgnoreCase("0")){
                           BigDecimal percent = new BigDecimal(c.getColumns()[2]);
                           percent = percent.divide(total_medios,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           medios_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }else{
                           medios_ = "00.00";  
                         }
                        if(c.getColumns()[3].length()>=1 && !c.getColumns()[3].equalsIgnoreCase("0")){
                           BigDecimal percent = new BigDecimal(c.getColumns()[3]);
                           percent = percent.divide(total_semipesados,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           semipesados_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        } else{
                           semipesados_ = "00.00";  
                         }
                        if(c.getColumns()[4].length()>=1 && !c.getColumns()[4].equalsIgnoreCase("0")){
                           BigDecimal percent = new BigDecimal(c.getColumns()[4]);
                           percent = percent.divide(total_pesados,4, RoundingMode.CEILING).multiply(new BigDecimal(100));
                           pesados_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                        }else{
                           pesados_ = "00.00";  
                         }
                         float[] floatTotal = {Float.parseFloat(c.getColumns()[1]),Float.parseFloat(c.getColumns()[2]),Float.parseFloat(c.getColumns()[3]),Float.parseFloat(c.getColumns()[4])};
                         
                         jsono.put("leves_", leves_);
                         jsono.put("medios_", medios_);
                         jsono.put("semipesados_", semipesados_);
                         jsono.put("pesados_", pesados_);
                         jsono.put("total", sum(floatTotal));
                         //jsono.put("totals_perc", String.valueOf(Utils.formatDecimal(total_perc))+"%");
                         
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
        }else if (request.getParameter("novosClientes") != null && !request.getParameter("novosClientes").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.novosClientes(ini, fim, seg,areaOper);
                     
                     for(CollectionColumns c: listColumns){
                         DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         df.setMaximumFractionDigits(2);
                         df.setMinimumFractionDigits(2);
                         df.setMinimumIntegerDigits(2);
                         df.setRoundingMode(RoundingMode.HALF_UP);
                         
                        // String leves_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                         
                         JSONObject jsono = new JSONObject();
                         jsono.put("cnpj", c.getColumns()[0]);
                         jsono.put("nome", c.getColumns()[1]);
                         jsono.put("ford", c.getColumns()[2]);
                         jsono.put("mercedes", c.getColumns()[3]);
                         jsono.put("scania", c.getColumns()[4]);
                         jsono.put("volvo", c.getColumns()[5]);
                         jsono.put("vw", c.getColumns()[6]);
                         jsono.put("iveco", c.getColumns()[7]);
                         jsono.put("renault", c.getColumns()[8]);
                         jsono.put("fiat", c.getColumns()[9]);
                         jsono.put("outros", c.getColumns()[10]);
                         jsono.put("total", c.getColumns()[11]);
                         // adding to json
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
        }else if (request.getParameter("novosClientes6Meses") != null && !request.getParameter("novosClientes6Meses").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.novosClientes6Meses(ini, fim, seg,areaOper);
                     
                     for(CollectionColumns c: listColumns){
                         DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         df.setMaximumFractionDigits(2);
                         df.setMinimumFractionDigits(2);
                         df.setMinimumIntegerDigits(2);
                         df.setRoundingMode(RoundingMode.HALF_UP);
                         
                        // String leves_ = String.valueOf(Utils.formatDecimal(percent));//String.valueOf(other[i]);
                         
                         JSONObject jsono = new JSONObject();
                         jsono.put("cnpj", c.getColumns()[0]);
                         jsono.put("nome", c.getColumns()[1]);
                         jsono.put("ford", c.getColumns()[2]);
                         jsono.put("mercedes", c.getColumns()[3]);
                         jsono.put("scania", c.getColumns()[4]);
                         jsono.put("volvo", c.getColumns()[5]);
                         jsono.put("vw", c.getColumns()[6]);
                         jsono.put("iveco", c.getColumns()[7]);
                         jsono.put("renault", c.getColumns()[8]);
                         jsono.put("fiat", c.getColumns()[9]);
                         jsono.put("outros", c.getColumns()[10]);
                         jsono.put("total", c.getColumns()[11]);
                         // adding to json
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
        }else if (request.getParameter("atividadeEconomica") != null && !request.getParameter("atividadeEconomica").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.atividadeEconomica(ini, fim, seg,areaOper);
                     
                     for(CollectionColumns c: listColumns){
                         //DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                         //df.setMaximumFractionDigits(2);
                         //df.setMinimumFractionDigits(2);
                         //df.setMinimumIntegerDigits(2);
                         //df.setRoundingMode(RoundingMode.HALF_UP);
                         
                         BigDecimal val = new BigDecimal(c.getColumns()[12]);
                         String mktshare_= c.getColumns()[12];
                         try{
                            mktshare_ = String.valueOf(Utils.formatDecimal(val));//String.valueOf(other[i]);
                         }catch(Exception ex){
                             br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName()+"***"+val+"***",ex.getMessage());
                         }
                         
                         JSONObject jsono = new JSONObject();
                         jsono.put("atividade", c.getColumns()[0]);
                         jsono.put("ford", c.getColumns()[1]);
                         jsono.put("mercedes", c.getColumns()[2]);
                         jsono.put("scania", c.getColumns()[3]);
                         jsono.put("volvo", c.getColumns()[4]);
                         jsono.put("vw", c.getColumns()[5]);
                         jsono.put("iveco", c.getColumns()[6]);
                         jsono.put("renault", c.getColumns()[7]);
                         jsono.put("fiat", c.getColumns()[8]);
                         jsono.put("outros", c.getColumns()[9]);
                         jsono.put("total", c.getColumns()[10]);
                         jsono.put("clientes", c.getColumns()[11]);
                         jsono.put("mktshare", mktshare_+"%");
                         // adding to json
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
        }else if (request.getParameter("concentracaoCliente") != null && !request.getParameter("concentracaoCliente").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.concentracaoCliente(ini, fim, seg,areaOper);
                     
                     for(CollectionColumns c: listColumns){
                         
                         
                         //BigDecimal val = new BigDecimal(c.getColumns()[12]);
                         //String mktshare_= c.getColumns()[12];
                         //try{
                         //   mktshare_ = String.valueOf(Utils.formatDecimal(val));//String.valueOf(other[i]);
                         //}catch(Exception ex){
                         //    br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName()+"***"+val+"***",ex.getMessage());
                         //}
                         
                         JSONObject jsono = new JSONObject();
                         jsono.put("marca", c.getColumns()[0]);
                         jsono.put("sp", c.getColumns()[1]);
                         jsono.put("curitiba", c.getColumns()[2]);
                         jsono.put("bh", c.getColumns()[3]);
                         jsono.put("recife", c.getColumns()[4]);
                         
                         // adding to json
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
        }else if (request.getParameter("penetracaoCliente") != null && !request.getParameter("penetracaoCliente").isEmpty()) {
             if(ini != null && !ini.isEmpty() && fim!=null & !fim.isEmpty() && seg !=null && !seg.isEmpty()){
                 try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bdBoletim.penetracaoClientes(ini, fim, seg, subSeg,areaOper);
                     
                     for(CollectionColumns c: listColumns){
                         
                         
                         JSONObject jsono = new JSONObject();
                         jsono.put("clientes", c.getColumns()[0]);
                         jsono.put("penetracao", c.getColumns()[1]);
                         jsono.put("ini", ini);
                         jsono.put("fim", fim);
                         jsono.put("seg", seg);
                         jsono.put("subseg", subSeg);
                         
                         // adding to json
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
        }
        
        
    }
    public List<List<CollectionColumns>> combineValues(List<CollectionColumns> listColumns){
        float totals=0;
        float percent=0.0f;
        float limit=Preferences.COMBINE_LIMIT;
        float other=0;
        List<CollectionColumns> clearList= new ArrayList<CollectionColumns>(); 
        List<CollectionColumns> otherList= new ArrayList<CollectionColumns>(); 
        List<CollectionColumns> totalList= new ArrayList<CollectionColumns>(); 
        List<List<CollectionColumns>> fullList= new ArrayList<List<CollectionColumns>>(); 
        //acumulando o valor total das colunas
        for(CollectionColumns c:listColumns){
            totals+=Integer.parseInt(c.getColumns()[0]);
        }
        // percorrendo as colunas para verificar o percentual
        for(int row=0;row < listColumns.size(); row++){
            percent = Float.parseFloat(listColumns.get(row).getColumns()[0])/totals;
            if(percent<limit){
                 other+=Integer.parseInt(listColumns.get(row).getColumns()[0]);
                 otherList.add(listColumns.get(row));

            }else{
                
                listColumns.get(row).setColumns(new String[]{listColumns.get(row).getColumns()[0],listColumns.get(row).getColumns()[1],String.valueOf(percent*100)});
                clearList.add(listColumns.get(row));
            }
        }

        if(other>0){
            CollectionColumns col = new CollectionColumns(new String[]{String.valueOf((int)(other)),"OUTROS",String.valueOf((other/totals)*100)});
            clearList.add(col);
        }
        
        fullList.add(clearList);
        fullList.add(otherList);
        
        CollectionColumns col = new CollectionColumns(new String[]{String.valueOf(totals)});
        totalList.add(col);
        fullList.add(totalList);
        //total = totals;
        return fullList;
    }
    
    public List<List<CollectionColumns>> combineValuesLastColumn(List<CollectionColumns> listColumns){
        total=0;
        float percent=0.0f;
        float limit=Preferences.COMBINE_LIMIT;
        //BigDecimal[] other = new BigDecimal[100];
        float[] arrayTotal = new float[15];
       // float arrTotal = 0.0f;
       
        BigDecimal sum = BigDecimal.ZERO;
        
        List<CollectionColumns> clearList   = new ArrayList<CollectionColumns>(); 
        List<CollectionColumns> otherList   = new ArrayList<CollectionColumns>(); 
        List<List<CollectionColumns>> fullList= new ArrayList<List<CollectionColumns>>(); 
        // inicializando o vetor com zero
        //Arrays.fill(other, BigDecimal.ZERO);
        Arrays.fill(arrayTotal, 0.0f);
        
        for(int row=0;row < listColumns.size(); row++){
            //percent = Float.parseFloat(listColumns.get(row).getColumns()[0])/total;
            int lastColumn = listColumns.get(row).getColumns().length-1;
            // initializing array other
            percent = Float.parseFloat(listColumns.get(row).getColumns()[lastColumn]);
            BigDecimal perc = new BigDecimal(percent/100);
            BigDecimal lim = new BigDecimal(limit);
            
            if(lim.compareTo(perc)>=0){
                
                 for(int i=2;i<=lastColumn;i++){
                     if(i%2==0){
                        // zerando a variavel soma
                        sum = BigDecimal.ZERO;
                        // recebendo o valor atual do vetor
                        sum = new BigDecimal(arrayTotal[i]);
                        // recebendo o valor atual da linha
                        float x = Float.parseFloat(listColumns.get(row).getColumns()[i]);
                        // efetuando a soma
                        sum = sum.add(new BigDecimal(x));
                        arrayTotal[i] = sum.floatValue();
                     
                     }
                 }
                if(otherList!=null){
                    otherList.add(listColumns.get(row));
                }
            }else{
                clearList.add(listColumns.get(row));
            }
        }
        
        if(arrayTotal.length>0){
           //total = arrayTotal[10];
           // CollectionColumns col = new CollectionColumns(new String[]{String.valueOf((int)(other)),"OUTROS",String.format("%.2f", ((other/total)*100))});
            String[] array = new String[listColumns.get(0).getColumns().length];
            CollectionColumns cols = new CollectionColumns(array);
            
            for(int i=0;i<cols.getColumns().length;i++){
                if(i==0){
                    cols.getColumns()[0]="OUTROS";
                }
                if(i>0 && i%2==0){
                   // DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
                   // df.setMaximumFractionDigits(2);
                    cols.getColumns()[i] = String.valueOf(Utils.formatDecimal(arrayTotal[i]));//String.valueOf(other[i]);
                }
            }
            // adding others to last row
            clearList.add(cols);
        }
        
        fullList.add(clearList);
        fullList.add(otherList);
        return fullList;
    }
    // método mágico para soma de Big Decimal
    public BigDecimal sum(float[] floats) {
        BigDecimal sum = BigDecimal.ZERO;

        for (float aFloat : floats) {
            sum = sum.add(new BigDecimal(aFloat));
        }

        return sum;
    }
    public BigDecimal sum(List<CollectionColumns> listColumns, int column){
        BigDecimal sum = BigDecimal.ZERO;
        for(CollectionColumns c: listColumns){
            sum = sum.add(new BigDecimal(c.getColumns()[column]));
        }
         return sum;
    }
 /***
  *  Utilizado com topEmplacamentoSubSegmento
  **/   
public List<List<CollectionColumns>> groupTopValues(List<CollectionColumns> listColumns){
        total=0;
        float percent=0.0f;
        float limit=Preferences.COMBINE_LIMIT;
        //BigDecimal[] other = new BigDecimal[100];
        float[] arrayTotal = new float[1000];
       // float arrTotal = 0.0f;
       
        BigDecimal sum = BigDecimal.ZERO;
        
        List<CollectionColumns> clearList   = new ArrayList<CollectionColumns>(); 
        List<CollectionColumns> otherList   = new ArrayList<CollectionColumns>(); 
        List<List<CollectionColumns>> fullList= new ArrayList<List<CollectionColumns>>(); 
        // inicializando o vetor com zero
        //Arrays.fill(other, BigDecimal.ZERO);
        Arrays.fill(arrayTotal, 0.0f);
        
        // separando outros dos tops
        for(int row=0;row < listColumns.size(); row++){
            if(row>Preferences.BOLETIM_TOP_LIMIT-1){
                otherList.add(listColumns.get(row));
            }else{
                clearList.add(listColumns.get(row));
            }
            arrayTotal[row]=Float.parseFloat(listColumns.get(row).getColumns()[2]);
        }
        
        // calculando o total
        sum = sum(arrayTotal);
       // sum = sum.multiply(new BigDecimal("100"));
        
        // calculando o percentual da lista limpa
        for(int row=0;row<clearList.size();row++){
            BigDecimal x = new BigDecimal(clearList.get(row).getColumns()[2]);
            //DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
            //df.setMaximumFractionDigits(2);
            clearList.get(row).getColumns()[3]= Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))).toString();
        }
        
        Arrays.fill(arrayTotal, 0.0f);
        // calculando o percentual da lista de outros
        for(int row=0;row<otherList.size();row++){
            BigDecimal x = new BigDecimal(otherList.get(row).getColumns()[2]);
           // DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
           // df.setMaximumFractionDigits(2);
            otherList.get(row).getColumns()[3]= Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))).toString();
            arrayTotal[row]=Float.parseFloat(otherList.get(row).getColumns()[2]);
        }
        
        // adicionando outros a lista apenas de valores
        BigDecimal x =  sum(arrayTotal);
        String[] array = new String[4];
        CollectionColumns cols = new CollectionColumns(array);
        cols.getColumns()[0]="OUTROS";
        cols.getColumns()[1]="OUTROS";
       // DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
       // df.setMaximumFractionDigits(2);
        cols.getColumns()[2] = String.valueOf(Utils.formatDecimal(x));//String.valueOf(other[i]);
        cols.getColumns()[3] = (Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")))).toString();
        // adding others to last row
        clearList.add(cols);
        
        
        fullList.add(clearList);
        fullList.add(otherList);
        return fullList;
    }  
   /*** LIMITAR PELO FILTRO DOS TOPS **/
public List<List<CollectionColumns>> groupTopValuesSimple(List<CollectionColumns> listColumns){
        total=0;
        float percent=0.0f;
        float limit=Preferences.COMBINE_LIMIT;
        //BigDecimal[] other = new BigDecimal[100];
        float[] arrayTotal = new float[1000];
       // float arrTotal = 0.0f;
       
        BigDecimal sum = BigDecimal.ZERO;
        
        List<CollectionColumns> clearList   = new ArrayList<CollectionColumns>(); 
        List<CollectionColumns> otherList   = new ArrayList<CollectionColumns>(); 
        List<List<CollectionColumns>> fullList= new ArrayList<List<CollectionColumns>>(); 
        // inicializando o vetor com zero
        //Arrays.fill(other, BigDecimal.ZERO);
        Arrays.fill(arrayTotal, 0.0f);
        
        // separando outros dos tops
        for(int row=0;row < listColumns.size(); row++){
            if(row>Preferences.BOLETIM_TOP_LIMIT-1){
                otherList.add(listColumns.get(row));
            }else{
                clearList.add(listColumns.get(row));
            }
            arrayTotal[row]=Float.parseFloat(listColumns.get(row).getColumns()[2]);
        }
        
        // calculando o total
        sum = sum(arrayTotal);
       // sum = sum.multiply(new BigDecimal("100"));
        /*
        // calculando o percentual da lista limpa
        for(int row=0;row<clearList.size();row++){
            BigDecimal x = new BigDecimal(clearList.get(row).getColumns()[2]);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
            df.setMaximumFractionDigits(2);
            clearList.get(row).getColumns()[3]= Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))).toString();
        }
        
        Arrays.fill(arrayTotal, 0.0f);
        // calculando o percentual da lista de outros
        for(int row=0;row<otherList.size();row++){
            BigDecimal x = new BigDecimal(otherList.get(row).getColumns()[2]);
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
            df.setMaximumFractionDigits(2);
            otherList.get(row).getColumns()[3]= Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))).toString();
            arrayTotal[row]=Float.parseFloat(otherList.get(row).getColumns()[2]);
        }
        
        // adicionando outros a lista apenas de valores
        BigDecimal x =  sum(arrayTotal);
        String[] array = new String[4];
        CollectionColumns cols = new CollectionColumns(array);
        cols.getColumns()[0]="OUTROS";
        cols.getColumns()[1]="OUTROS";
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
        df.setMaximumFractionDigits(2);
        cols.getColumns()[2] = String.valueOf(Utils.formatDecimal(x));//String.valueOf(other[i]);
        cols.getColumns()[3] = (Utils.formatDecimal(x.divide(sum,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")))).toString();
        // adding others to last row
        clearList.add(cols);
        */
        
        fullList.add(clearList);
        fullList.add(otherList);
        return fullList;
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
