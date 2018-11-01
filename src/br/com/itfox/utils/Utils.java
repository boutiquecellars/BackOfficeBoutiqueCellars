package br.com.itfox.utils;

import br.com.itfox.business.DBase;
import br.com.itfox.config.Preferences;
import br.com.itfox.servlet.DashboardServlet;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.time.DateUtils;
//import org.apache.commons.lang.time.DateUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author belchiorpalma
 */
public class Utils {
    public static String dateFormat(String date_s){
        String dateFormated="";
        try {
            //String date_s = "2011-01-18 00:00:00.0";
            
            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yy");
            Date date = dt.parse(date_s);
            
            // *** same for the format String below
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            dateFormated=dt1.format(date);
            //System.out.println(dateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateFormated;
    }
    
    public static Date dateFormatStringToDate(String date_s){
        String dateFormated="";
        Date date  = new Date();
        try {
            //String date_s = "2011-01-18 00:00:00.0";
            
            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yy");
            date = dt.parse(date_s);
            
            // *** same for the format String below
            //SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            //dateFormated=dt1.format(date);
            //System.out.println(dateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public static Timestamp dateToTimestamp(Date utilDate){
        long millis = DateUtils.truncate(utilDate, Calendar.MILLISECOND).getTime();
        java.sql.Timestamp sq = new java.sql.Timestamp(millis );
        return sq;
    }
    
    public static String dateFormatPt(String date_s){
        String dateFormated="";
        try {
            //String date_s = "2011-01-18 00:00:00.0";
            
            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(date_s);
            
            // *** same for the format String below
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yy");
            dateFormated=dt1.format(date);
            //System.out.println(dateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateFormated;
    }
    
    public static String dateFormatPt2(String date_s){
        String dateFormated="";
        try {
            //String date_s = "2011-01-18 00:00:00.0";
            
            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = dt.parse(date_s);
            
            // *** same for the format String below
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yy");
            dateFormated=dt1.format(date);
            //System.out.println(dateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateFormated;
    }
    
    public static String dateFormat_dd_MM_yyyy(String date_s){
        String dateFormated="";
        try {
            //String date_s = "2011-01-18 00:00:00.0";
            
            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(date_s);
            
            // *** same for the format String below
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            dateFormated=dt1.format(date);
            //System.out.println(dateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateFormated;
    }
    
    public static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }

    public static String fromHexString(String hex) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2) {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }
    public static String formatCpfCnpj(String value) {
        String maskCpf = "###.###.###-##";
        String maskCnpj = "##.###.###/####-##";
        String maskCep = "##.###-###";
        String mask = "";
        if(value==null){
            return "";
        }if(value.length()==14){
            mask = maskCnpj;
        }else if(value.length()==11){
            mask = maskCpf;
        }else if(value.length()==8){
            mask = maskCep;
        }
        try{
        MaskFormatter formatter = new MaskFormatter(mask);
        JFormattedTextField textField = new JFormattedTextField();
        formatter.install(textField);
        textField.setText(value);
        value = textField.getText();
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }
    public static String formatTelephone(String value) {
        String maskTel = "####-####";
        String maskCel = "#####-####";
        String maskDDD = "(##) ";
        String mask = "";
        if(value==null){
            return "";
        }if(value.length()==9){
            mask = maskCel;
        }else if(value.length()==8){
            mask = maskTel;
        }else if(value.length()==2){
            mask = maskDDD;
        }
        
        try{
        MaskFormatter formatter = new MaskFormatter(mask);
        JFormattedTextField textField = new JFormattedTextField();
        formatter.install(textField);
        textField.setText(value);
        value = textField.getText();
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }
    
    public static String formatDecimal(BigDecimal val){
        String s = "";
        try{
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
        df.setMaximumFractionDigits(Preferences.MAXIMUM_FRACTION_DIGITS);
        df.setMinimumFractionDigits(Preferences.MINIMUM_FRACTION_DIGITS);
        df.setMinimumIntegerDigits(Preferences.MIMIMUM_INTEGER_DIGITS);
        df.setRoundingMode(Preferences.ROUNDING_MODE);
        s = String.valueOf(df.format(val));
        }catch(Exception ex){
             br.com.itfox.utils.Logger.getLogger(ex, Utils.class.getName()+"***"+val+"***",ex.getMessage());
        }
        return s;
        
    }
    public static String formatDecimal(float val){
        String s = "";
        try{
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("en", "EN"));
        df.setMaximumFractionDigits(Preferences.MAXIMUM_FRACTION_DIGITS);
        df.setMinimumFractionDigits(Preferences.MINIMUM_FRACTION_DIGITS);
        df.setMinimumIntegerDigits(Preferences.MIMIMUM_INTEGER_DIGITS);
        df.setRoundingMode(Preferences.ROUNDING_MODE);
        s = String.valueOf(df.format(val));
        }catch(Exception ex){
             br.com.itfox.utils.Logger.getLogger(ex, Utils.class.getName()+"***"+val+"***",ex.getMessage());
        }
        return s;
        
    }
    
     public static String encodeURIComponent(String url) {
         String result = "";
        try {
            result= URLEncoder.encode(url, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
            //the slow option:
            //new ScriptEngineManager().getEngineByName("JavaScript")...etc
        } catch (UnsupportedEncodingException e) {
            //never
            e.printStackTrace();
        }
        return result;
    }

    public static String encodeURI(String url) {
            return encodeURIComponent(url)
                .replaceAll("%3A", ":")
                .replaceAll("%2F", "/")
                .replaceAll("%3F", "?")
                .replaceAll("%3D", "=")
                .replaceAll("%26", "&");
    }
    
    public static Blob stringToBlob(String s){
        try {
            if(s!=null && !s.isEmpty() && s!=""){
            Connection conn = new DBase(true).getConnection();
            Blob myBlob = conn.createBlob();
            myBlob.setBytes(1, s.getBytes());
            conn.close();
            return myBlob;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public static String blobToString(Blob blob){
        try {
            if(blob!=null){
            byte[] bdata = blob.getBytes(1, (int) blob.length());
            String text = new String(bdata);
            text = text.replaceAll("\r\n", "<br>");
            return text;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void convertBase64StringToImage(String sourceData, String filename){
        try {
            //String sourceData = "data:image/png;base64,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";
            
            // tokenize the data
            if(sourceData!=null && sourceData.length()>1){
            String[] parts = sourceData.split(",");
            String imageString = parts[1];
            
            // create a buffered image
            BufferedImage image = null;
            byte[] imageByte;
            
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
            
            // write the image to a file
            File outputfile = new File(filename);
            ImageIO.write(image, "png", outputfile);
            }
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int parseInt(String s){
        int i=0;
        try{
                if(s!=null && !s.isEmpty() && s!="" && s!=" " && s.length()>0 && !s.equalsIgnoreCase("null") && !s.equalsIgnoreCase("undefined")){
                    i=Integer.parseInt(s);
                }
            }catch(Exception ex){
                 br.com.itfox.utils.Logger.getLogger(ex, DashboardServlet.class.getName()+" -- Parse Int Exception",ex.getMessage());
                 Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
        return i;
    }
}
