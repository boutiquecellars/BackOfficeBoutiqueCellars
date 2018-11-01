/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.test;

import br.com.itfox.beans.CollectionColumns;
import br.com.itfox.beans.Colors;
import br.com.itfox.beans.Member;
import br.com.itfox.beans.Product;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.config.Preferences;
import br.com.itfox.servlet.DashboardServlet;
import br.com.itfox.utils.Utils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author belchiorpalma
 */
public class TestBusiness {
    public void testSelectChassiClienteMontadora(String ini, String fim, String seg){
        
        BusinessDelegate bd = new BusinessDelegate();
        JSONArray json = new JSONArray();
        try {
                     ini = (Utils.dateFormat(ini));
                     fim = (Utils.dateFormat(fim));
                     List<CollectionColumns> listColumns= bd.selectChassiClienteMontadora(ini, fim, seg,"222","222,270");
                     int i=0;
                     for(CollectionColumns c: listColumns){
                        JSONObject jsono = new JSONObject();
                        jsono.put("total", Integer.parseInt(c.getColumns()[0]));
                        jsono.put("descricao", (c.getColumns()[1]));
                        jsono.put("marca", (c.getColumns()[2]));
                        jsono.put("rows_pf", Integer.parseInt(c.getColumns()[3]));
                        jsono.put("rows_pj", Integer.parseInt(c.getColumns()[4]));
                        jsono.put("pf", Integer.parseInt(c.getColumns()[5]));
                        jsono.put("pj", Integer.parseInt(c.getColumns()[6]));
                        
                        jsono.put("color", new Colors().getColors()[i]);
                      //  jsono.put("ini", Utils.dateFormatPt(ini));
                      //  jsono.put("fim", Utils.dateFormatPt(fim));
                        json.put(jsono);
                        i++;
                     }
                     
                 }catch(JSONException ex){
                     Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }finally {
                   System.out.println(json.toString());
                   
                }
    }
    public static void main(String[] args){
        //TestBusiness test = new TestBusiness();
       // test.testSelectChassiClienteMontadora("01/11/2015", "07/11/2015", "3");
       // System.out.println(Utils.toHexString(Preferences.SECURITY_KEY.getBytes()));
       // System.out.println(Utils.fromHexString("7777772e6974666f782e636f6d2e62722e73656375726974792e6b65792d32"));
        
          BusinessDelegate bd = new BusinessDelegate();
          List<Member> list = bd.selectMembers();
          System.out.println("list:"+list.size());
          
          Utils u = new Utils();
          List<Product> l = bd.selectProducts();
          for(Product p: l){
              u.convertBase64StringToImage(Utils.blobToString(p.getPic1()),p.getName().replaceAll(" ", "_").toLowerCase()+".png");
          }
          
    }
}
