/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.utils;

import br.com.itfox.beans.Log;
import br.com.itfox.business.BusinessDelegate;
import br.com.itfox.business.DBase;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author belchiorpalma
 */
public  class Logger {
    public static void getLogger(Exception ex, String name, String description){
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            
            String error=sw.toString(); // stack trace as a string
            BusinessDelegate bd = new BusinessDelegate();
            String strContent = error;
            byte[] byteContent = strContent.getBytes();
            Connection conn = new DBase(true).getConnection();
            Blob blob = conn.createBlob();//Where connection is the connection to db object.
            blob.setBytes(1, byteContent);
            Log l = new Log(blob, name, description);
            conn.close();
            bd.insertLog(l);
        } catch (SQLException ex1) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex1);
        }
       
    }
    
    public static void getLogger(Exception ex){
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            
            String error=sw.toString(); // stack trace as a string
            BusinessDelegate bd = new BusinessDelegate();
            String strContent = error;
            byte[] byteContent = strContent.getBytes();
            Connection conn = new DBase(true).getConnection();
            Blob blob = conn.createBlob();//Where connection is the connection to db object.
            blob.setBytes(1, byteContent);
            Log l = new Log(blob, "", "");
            conn.close();
            bd.insertLog(l);
        } catch (SQLException ex1) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex1);
        }
       
    }
    
    public static void getLogger(String ex){
        try {
            //StringWriter sw = new StringWriter();
            //PrintWriter pw = new PrintWriter(sw);
            //ex.printStackTrace(pw);
            
            String error=ex; // stack trace as a string
            BusinessDelegate bd = new BusinessDelegate();
            String strContent = error;
            byte[] byteContent = strContent.getBytes();
            Connection conn = new DBase(true).getConnection();
            Blob blob = conn.createBlob();//Where connection is the connection to db object.
            blob.setBytes(1, byteContent);
            Log l = new Log(blob, "", "");
            conn.close();
            bd.insertLog(l);
        } catch (SQLException ex1) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex1);
        }
       
    }
    
}
