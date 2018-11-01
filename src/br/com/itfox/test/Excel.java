/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.test;

import br.com.itfox.beans.Gic;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.generator.GeneratorObjectCollection;
import br.com.itfox.servlet.DashboardServlet;
import br.com.itfox.utils.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


//import org.apache.log4j.Level;
import static org.apache.log4j.Level.OFF;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 *
 * @author belchiorpalma
 */
public class Excel {
    
    public void gerarExcel(){
                    String ini="01/04/2016";
                    String fim="22/06/2016";
                    String seg="'21','22','23'";
                    String areaOper="'47','24','23','29','4','18','5','48','10','31','43','35','36','7','33','45','3','32','9','39','13','38','16','44','30','15','2','17','12','6','42','41','34','40','1','19','14','26','22','51','46','49','27','25','8','50','52','28','11','20','37','21'";
                    BusinessDelegate bd = new BusinessDelegate();
                    String path = "/Users/belchiorpalma/Desktop/template/";
                    String pathTemplate="/Users/belchiorpalma/NetBeansProjects/Quest_Iveco/src/br/com/itfox/generator/";
                    InputStream is = null;
                    try {
                        is = new FileInputStream(pathTemplate+"TemplateGic.xlsx");
                    } catch (FileNotFoundException ex) {
                       // Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                    /*
                    try{
                        is = GeneratorObjectCollection.class.getResourceAsStream(pathTemplate+"TemplateGic.xlsx");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }*/
                    //try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream(pathTemplate+"TemplateGic.xlsx")) 
                    // {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
                            String date = sdf.format(new Date()); 
                            
                            try (OutputStream os = new FileOutputStream(path+"relatorio_enriquecimento_"+date+".xls")) 
                            {
                                Context context = new Context();
                                //Context context = transformer.createInitialContext();
                                //context.getConfig().setIgnoreSourceCellStyle(false);
                                //context.getConfig().setIsFormulaProcessingRequired(true);
                                ini = (Utils.dateFormat(ini));
                                fim = (Utils.dateFormat(fim));
                                
                                List<Gic> gics = bd.selectGic(ini, fim, seg, areaOper);
                                
                               
                               org.apache.log4j.Logger.getRootLogger().setLevel(OFF);
                                
                               // System.out.println(org.apache.log4j.Logger.getRootLogger().isDebugEnabled());
                                //org.apache.log4j.Logger.getRootLogger().setLevel(OFF);
                                //System.out.println(org.apache.log4j.Logger.getRootLogger().isDebugEnabled());
                                //Logger barlogger = Logger.getLogger(org.jxls.transform.poi.PoiTransformer.class);
                                //barlogger.setLevel(OFF);
                                //System.out.println(barlogger.isDebugEnabled());
                                if(gics.size()>0){
                                    System.out.println("..");
                                    context.putVar("gics", gics);
                                    // log4j.rootLogger=OFF
                                    try{
                                    JxlsHelper.getInstance().processTemplate(is, os, context);
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                   
                                    System.out.println(gics.get(0).getC_nomeproprietario()+"-"+is);
                                }else{
                                    System.out.println("não há dados.");
                                }
                               // System.out.println("tamanho da lista:"+gics.size());
                                
                                // imprimindo o json
                                // writer.write(json.toString());
                                // writer.close();
                                // exibindo o arquivo
                                //try to flush memory
                               // gics=null;
                               // context=null;
                               // System.gc();
                            }catch(Exception e){
                                 //br.com.itfox.utils.Logger.getLogger(e, DashboardServlet.class.getName(),e.getMessage());
                                //System.out.println("e"+e);
                            }

                   //     }catch(Exception e){
                   //          System.err.println(e);
                   //             
                    //    }
                     
    }            
    
    public void gerarExcel(boolean simple){
                    try {
                        String ini="01/01/2015";
                        String fim="22/06/2016";
                        String seg="'21','22','23'";
                        String areaOper="'47','24','23','29','4','18','5','48','10','31','43','35','36','7','33','45','3','32','9','39','13','38','16','44','30','15','2','17','12','6','42','41','34','40','1','19','14','26','22','51','46','49','27','25','8','50','52','28','11','20','37','21'";
                        BusinessDelegate bd = new BusinessDelegate();
                        String path = "/Users/belchiorpalma/Desktop/template/";
                        String pathTemplate="/Users/belchiorpalma/NetBeansProjects/Quest_Iveco/src/br/com/itfox/generator/";
                        InputStream is = null;
                        try {
                            is = new FileInputStream(pathTemplate+"TemplateGic.xlsx");
                        } catch (FileNotFoundException ex) {
                           // Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }
                        //try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream(pathTemplate+"TemplateGic.xlsx"))
                        // {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
                        String date = sdf.format(new Date());
                        
                        Workbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
                        Sheet sh = wb.createSheet();
                        
                        ini = (Utils.dateFormat(ini));
                        fim = (Utils.dateFormat(fim));
                                
                        List<Gic> gics = bd.selectGic(ini, fim, seg, areaOper);
                        int i=0;
                        for(Gic g: gics){
                             Row row = sh.createRow(i);
                             for (int cellnum = 0; cellnum < 153; cellnum++) {
                                 Cell cell = row.createCell(cellnum);
                                 cell.setCellValue(g.getC_nomeproprietario());
                             }
                             i++;
                        }
                        /*
                        for (int rownum = 0; rownum < 1000000; rownum++) {
                            Row row = sh.createRow(rownum);
                            for (int cellnum = 0; cellnum < 2; cellnum++) {
                                Cell cell = row.createCell(cellnum);
                                String address = new CellReference(cell).formatAsString();
                                cell.setCellValue(address);
                            }
                        }*/
                        
                        FileOutputStream out;
                        try {
                            out = new FileOutputStream(path+"object_collection_output.xlsx");
                            wb.write(out);
                            out.close();
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                        }
                        
                    } catch (IOException ex) {
                       // Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
    }
                        
    public static void main(String args[]){
        Excel   e = new Excel();
                e.gerarExcel(true);
        //e.gerarExcel(true);
    }                 
                 
    
}
