/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.business;

import br.com.itfox.beans.AreaOper;
import br.com.itfox.beans.Brand;
import br.com.itfox.beans.Category;
import br.com.itfox.beans.Client;
import br.com.itfox.beans.CollectionColumns;
import br.com.itfox.beans.EmailMkt;
import br.com.itfox.beans.EmailMktCart;
import br.com.itfox.beans.Gic;
import br.com.itfox.beans.Log;
import br.com.itfox.beans.LogFiles;
import br.com.itfox.beans.LogUpload;
import br.com.itfox.beans.Member;
import br.com.itfox.beans.MemberAreaOper;
import br.com.itfox.beans.Mensagem;
import br.com.itfox.beans.Product;
import br.com.itfox.beans.Profile;
import br.com.itfox.utils.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author belchiorpalma
 */
public class BusinessDelegate {
    
    public Mensagem getMensagem(BigDecimal mensagemId){
        Mensagem m = new Mensagem();
        try{
                Connection conn = new DBase(true).getConnection();
                String sqlRegrasDesconto = "SELECT MENSAGEM_ID, MENSAGEM, TAG, TIPO_ID, ASSUNTO FROM ADMTRT.MENSAGEM WHERE MENSAGEM_ID=?";
                PreparedStatement statement =
                conn.prepareStatement(sqlRegrasDesconto);
                statement.setBigDecimal(1, mensagemId);
                ResultSet  rs = statement.executeQuery();
                 while(rs.next()){
                     m.setMensagemId(rs.getBigDecimal("MENSAGEM_ID"));
                     m.setMensagem(rs.getString("MENSAGEM"));
                     m.setTag(rs.getString("TAG"));
                     m.setTipoId(rs.getBigDecimal("TIPO_ID"));
                     m.setAssunto(rs.getString("ASSUNTO"));


                }
                     conn.close();
            }catch(Exception ex){
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        return m;
    }
    public int insertEmailMktCart(EmailMktCart cart){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && cart!=null){
            try {
                String sql = "INSERT INTO email_mkt_cart (email_mkt_id, client_id, product_id, quantity, price, total, status) VALUES (?, ?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                

                ps.setInt(1, cart.getEmailMkt().getEmailMktId() );
                ps.setInt(2, cart.getClient().getClientId());
                ps.setInt(3, cart.getProduct().getProductId());
                ps.setFloat(4, cart.getQuantity());
                ps.setFloat(5, cart.getPrice());
                ps.setFloat(6, cart.getTotal());
                ps.setString(7, cart.getStatus());
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public int updateEmailMktCart(EmailMktCart cart){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && cart!=null){
            try {
                String sql = "UPDATE email_mkt_cart SET quantity=?, price=?, total=?, status=? WHERE email_mkt_id=? and client_id=? and product_id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                
                ps.setFloat(1, cart.getQuantity());
                ps.setFloat(2, cart.getPrice());
                ps.setFloat(3, cart.getTotal());
                ps.setString(4, cart.getStatus());
                ps.setInt(5, cart.getEmailMkt().getEmailMktId() );
                ps.setInt(6, cart.getClient().getClientId());
                ps.setInt(7, cart.getProduct().getProductId());
                
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteEmailMktCart(EmailMktCart cart){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && cart!=null){
            try {
                String sql = "DELETE FROM email_mkt_cart WHERE email_mkt_id=? and client_id=? and product_id=? limit 1";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setInt(1, cart.getEmailMkt().getEmailMktId() );
                ps.setInt(2, cart.getClient().getClientId());
                ps.setInt(3, cart.getProduct().getProductId());
                
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public List<EmailMktCart> selectEmailMktCart(){
        Connection conn = new DBase(true).getConnection();
        List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        int result=0;
        if(conn!=null){
            try {
                String sql = "SELECT cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, cart.TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price,e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    EmailMktCart cart = new EmailMktCart();
                    
                    // client
                    c.setClientId(rs.getInt("c.CLIENT_ID"));
                    c.setName(rs.getString("c.NAME"));
                    c.setEmail(rs.getString("c.EMAIL"));
                    // product
                    p.setProductId(rs.getInt("p.PRODUCT_ID"));
                    p.setName(rs.getString("p.NAME"));
                    p.setPrice(rs.getFloat("p.PRICE"));
                   // p.setDescription(rs.getBlob("p.DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // p.setPic1(rs.getBlob("p.PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("e.EMAIL_MKT_ID"));
                    e.setName(rs.getString("e.NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("STATUS"));
                    
                    listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCart;
    }
    public List<EmailMktCart> selectEmailMktCartGroupByClient(){
        Connection conn = new DBase(true).getConnection();
        List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        int result=0;
        if(conn!=null){
            try {
                String sql = "SELECT count( DISTINCT cart.email_mkt_id) AS count, cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, SUM(cart.TOTAL) AS TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price,e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id GROUP BY cart.client_id, cart.email_mkt_id ORDER BY c.name  ";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    EmailMktCart cart = new EmailMktCart();
                    
                    // client
                    c.setClientId(rs.getInt("c.CLIENT_ID"));
                    c.setName(rs.getString("c.NAME"));
                    c.setEmail(rs.getString("c.EMAIL"));
                    // product
                    p.setProductId(rs.getInt("p.PRODUCT_ID"));
                    p.setName(rs.getString("p.NAME"));
                    p.setPrice(rs.getFloat("p.PRICE"));
                   // p.setDescription(rs.getBlob("p.DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // p.setPic1(rs.getBlob("p.PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("e.EMAIL_MKT_ID"));
                    e.setName(rs.getString("e.NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("COUNT"));
                    
                    listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCart;
    }
    
    public List<EmailMktCart> selectEmailMktCartFromClientSumary(EmailMktCart emailMktCart){
        Connection conn = new DBase(true).getConnection();
        List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        int result=0;
        if(conn!=null){
            try {
                String sql = "SELECT count( DISTINCT cart.email_mkt_id) AS count, cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, SUM(cart.TOTAL) AS TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price,e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id and cart.email_mkt_id=? and cart.client_id=? GROUP BY e.EMAIL_MKT_ID, cart.CLIENT_ID";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktCart.getEmailMkt().getEmailMktId());
                ps.setInt(2, emailMktCart.getClient().getClientId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    EmailMktCart cart = new EmailMktCart();
                    
                    // client
                    c.setClientId(rs.getInt("c.CLIENT_ID"));
                    c.setName(rs.getString("c.NAME"));
                    c.setEmail(rs.getString("c.EMAIL"));
                    // product
                    p.setProductId(rs.getInt("p.PRODUCT_ID"));
                    p.setName(rs.getString("p.NAME"));
                    p.setPrice(rs.getFloat("p.PRICE"));
                   // p.setDescription(rs.getBlob("p.DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // p.setPic1(rs.getBlob("p.PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("e.EMAIL_MKT_ID"));
                    e.setName(rs.getString("e.NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("COUNT"));
                    
                    listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCart;
    }
    
    public List<EmailMktCart> selectEmailMktCartFromClient(EmailMktCart emailMktCart){
        Connection conn = new DBase(true).getConnection();
        List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        int result=0;
        if(conn!=null){
            try {
                String sql = "SELECT cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, cart.TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price,e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id and cart.email_mkt_id=? and cart.client_id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktCart.getEmailMkt().getEmailMktId());
                ps.setInt(2, emailMktCart.getClient().getClientId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    EmailMktCart cart = new EmailMktCart();
                    
                    // client
                    c.setClientId(rs.getInt("c.CLIENT_ID"));
                    c.setName(rs.getString("c.NAME"));
                    c.setEmail(rs.getString("c.EMAIL"));
                    // product
                    p.setProductId(rs.getInt("p.PRODUCT_ID"));
                    p.setName(rs.getString("p.NAME"));
                    p.setPrice(rs.getFloat("p.PRICE"));
                   // p.setDescription(rs.getBlob("p.DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // p.setPic1(rs.getBlob("p.PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("e.EMAIL_MKT_ID"));
                    e.setName(rs.getString("e.NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("STATUS"));
                    
                    listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCart;
    }
    
    public EmailMktCart selectEmailMktCart(EmailMktCart emailMktCart){
        Connection conn = new DBase(true).getConnection();
       // List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        EmailMktCart cart = new EmailMktCart();
        int result=0;
        if(conn!=null && emailMktCart!=null && emailMktCart.getEmailMkt()!=null && emailMktCart.getEmailMkt().getEmailMktId()>0){
            try {
                String sql = "SELECT cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, cart.TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price, e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id and cart.email_mkt_id=? and cart.client_id=? and cart.product_id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktCart.getEmailMkt().getEmailMktId());
                ps.setInt(2, emailMktCart.getClient().getClientId());
                ps.setInt(3, emailMktCart.getProduct().getProductId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    
                    
                    // client
                    c.setClientId(rs.getInt("CLIENT_ID"));
                    c.setName(rs.getString("NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    // product
                    //p.setProductId(rs.getInt("PRODUCT_ID"));
                    p.setName(rs.getString("NAME"));
                    p.setPrice(rs.getFloat("PRICE"));
                    //p.setDescription(rs.getBlob("DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    //p.setPic1(rs.getBlob("PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("EMAIL_MKT_ID"));
                    e.setName(rs.getString("NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("STATUS"));
                    
                    //listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cart;
    }
    
    public EmailMktCart selectEmailMktCartSummary(EmailMktCart emailMktCart){
        Connection conn = new DBase(true).getConnection();
       // List<EmailMktCart> listCart = new ArrayList<EmailMktCart>();
        EmailMktCart cart = new EmailMktCart();
        int result=0;
        if(conn!=null && emailMktCart!=null && emailMktCart.getEmailMkt()!=null && emailMktCart.getEmailMkt().getEmailMktId()>0){
            try {
                String sql = "SELECT cart.EMAIL_MKT_ID, cart.CLIENT_ID, cart.PRODUCT_ID, cart.QUANTITY, cart.PRICE, sum(cart.TOTAL) AS TOTAL, cart.STATUS,  c.client_id, c.name, c.email, p.product_id, p.name, p.price, e.email_mkt_id, e.name FROM email_mkt_cart cart, client c, product p, email_mkt e WHERE cart.client_id=c.client_id and cart.product_id=p.product_id and cart.email_mkt_id=e.email_mkt_id and cart.email_mkt_id=? and cart.client_id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktCart.getEmailMkt().getEmailMktId());
                ps.setInt(2, emailMktCart.getClient().getClientId());
               // ps.setInt(3, emailMktCart.getProduct().getProductId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    Product p = new Product();
                    EmailMkt e = new EmailMkt();
                    
                    
                    // client
                    c.setClientId(rs.getInt("CLIENT_ID"));
                    c.setName(rs.getString("NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    // product
                    //p.setProductId(rs.getInt("PRODUCT_ID"));
                    p.setName(rs.getString("NAME"));
                    p.setPrice(rs.getFloat("PRICE"));
                    //p.setDescription(rs.getBlob("DESCRIPTION"));
                    //p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    //p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    //p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    //p.setPic1(rs.getBlob("PIC1"));
                    //p.setPic2(rs.getBlob("PIC2"));
                    //p.setPic3(rs.getBlob("PIC3"));
                    //p.setPic4(rs.getBlob("PIC4"));
                    //p.setPic5(rs.getBlob("PIC5"));
                    
                    e.setEmailMktId(rs.getInt("EMAIL_MKT_ID"));
                    e.setName(rs.getString("NAME"));
                   // e.setDescription(rs.getBlob("DESCRIPTION"));
                   // e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                   // e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                   // e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                   // e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    cart.setEmailMkt(e);
                    cart.setClient(c);
                    cart.setProduct(p);
                    cart.setQuantity(rs.getFloat("QUANTITY"));
                    cart.setPrice(rs.getFloat("PRICE"));
                    cart.setTotal(rs.getFloat("TOTAL"));
                    cart.setStatus(rs.getString("STATUS"));
                    
                    //listCart.add(cart);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cart;
    }
    
    
    public int insertClient(Client c){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && c!=null){
            try {
                String sql = "INSERT INTO client (NAME, EMAIL) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                

                ps.setString(1, c.getName() );
                ps.setString(2, c.getEmail());
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int updateClient(Client c){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && c!=null){
            try {
                String sql = "UPDATE client SET NAME=?, EMAIL=? WHERE CLIENT_ID=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setString(1, c.getName() );
                ps.setString(2, c.getEmail());
                ps.setInt(3, c.getClientId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteClient(Client c){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && c!=null){
            try {
                String sql = "DELETE FROM client WHERE CLIENT_ID=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, c.getClientId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public List<Client> selectClients(){
        Connection conn = new DBase(true).getConnection();
        List<Client> clients = new ArrayList<Client>();
        
        if(conn!=null){
            try {
                String sql = "SELECT CLIENT_ID, NAME, EMAIL FROM client ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Client c = new Client();
                    c.setClientId(rs.getInt("CLIENT_ID"));
                    c.setName(rs.getString("NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    
                    
                    clients.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }
    
    public Client selectClient(int clientId){
        Connection conn = new DBase(true).getConnection();
         Client c = new Client();
        
        if(conn!=null){
            try {
                String sql = "SELECT CLIENT_ID, NAME, EMAIL FROM client WHERE CLIENT_ID=? ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, clientId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   
                    c.setClientId(rs.getInt("CLIENT_ID"));
                    c.setName(rs.getString("NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
    public int insertEmailMkt(EmailMkt e){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && e!=null){
            try {
                String sql = "INSERT INTO email_mkt (NAME, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, META_TAG_URL) VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                

                ps.setString(1, e.getName() );
                ps.setBlob(2, e.getDescription());
                ps.setString(3, e.getMetaTagTitle());
                ps.setString(4,e.getMetaTagDescription());
                ps.setString(5, e.getMetaTagKeywords());
                ps.setString(6, e.getMetaTagUrl());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int updateEmailMkt(EmailMkt e){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && e!=null){
            try {
                String sql = "UPDATE email_mkt SET NAME=?, DESCRIPTION=?, META_TAG_TITLE=?, META_TAG_DESCRIPTION=?, META_TAG_KEYWORDS=?, META_TAG_URL=? WHERE EMAIL_MKT_ID=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                 ps.setString(1, e.getName() );
                ps.setBlob(2, e.getDescription());
                ps.setString(3, e.getMetaTagTitle());
                ps.setString(4,e.getMetaTagDescription());
                ps.setString(5, e.getMetaTagKeywords());
                ps.setString(6, e.getMetaTagUrl());
                ps.setInt(7, e.getEmailMktId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteEmailMkt(EmailMkt e){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && e!=null){
            try {
                String sql = "DELETE FROM email_mkt WHERE email_mkt_id=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, e.getEmailMktId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public List<EmailMkt> selectEmailMkts(){
        Connection conn = new DBase(true).getConnection();
        List<EmailMkt> emailsMkts = new ArrayList<EmailMkt>();
        
        if(conn!=null){
            try {
                String sql = "SELECT EMAIL_MKT_ID, NAME, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, META_TAG_URL  FROM email_mkt ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    EmailMkt e = new EmailMkt();
                    e.setEmailMktId(rs.getInt("EMAIL_MKT_ID"));
                    e.setName(rs.getString("NAME"));
                    e.setDescription(rs.getBlob("DESCRIPTION"));
                    e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    
                    emailsMkts.add(e);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emailsMkts;
    }
    
    public EmailMkt selectEmailMkt(int emailMktId){
        Connection conn = new DBase(true).getConnection();
         EmailMkt e = new EmailMkt();
        
        if(conn!=null){
            try {
                String sql = "SELECT EMAIL_MKT_ID, NAME, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, META_TAG_URL  FROM email_mkt WHERE EMAIL_MKT_ID=? ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   
                   
                    e.setEmailMktId(rs.getInt("EMAIL_MKT_ID"));
                    e.setName(rs.getString("NAME"));
                    e.setDescription(rs.getBlob("DESCRIPTION"));
                    e.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    e.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    e.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    e.setMetaTagUrl(rs.getString("META_TAG_URL"));
                    
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return e;
    }
    
    // insert method for class LogUpload
    public int insertProduct(Product p){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && p!=null){
            try {
                String sql = "INSERT INTO product (NAME, PRICE, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, PIC1, PIC2, PIC3, PIC4, PIC5, brand, category_id, varietal, alcohool, region, year, mevushal) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                

                ps.setString(1, p.getName() );
                ps.setFloat(2, p.getPrice());
                ps.setBlob(3, p.getDescription());
                ps.setString(4, p.getMetaTagTitle());
                ps.setString(5,p.getMetaTagDescription());
                ps.setString(6, p.getMetaTagKeywords());
                ps.setBlob(7, p.getPic1());
                ps.setBlob(8, p.getPic2());
                ps.setBlob(9, p.getPic3());
                ps.setBlob(10, p.getPic4());
                ps.setBlob(11, p.getPic5());
                ps.setInt(12, p.getBrand());
                ps.setInt(13, p.getCategoryId());
                ps.setString(14, p.getVarietal());
                ps.setString(15, p.getAlcohol());
                ps.setString(16, p.getRegion());
                ps.setString(17, p.getYear());
                ps.setString(18, p.getMevushal());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int updateProduct(Product p){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && p!=null){
            try {
                String sql = "UPDATE product SET NAME=?, PRICE=?, DESCRIPTION=?, META_TAG_TITLE=?, META_TAG_DESCRIPTION=?, META_TAG_KEYWORDS=?, PIC1=?, PIC2=?, PIC3=?, PIC4=?, PIC5=?, BRAND=?, CATEGORY_ID=?, VARIETAL=?, ALCOHOOL=?, REGION=?, YEAR=?, MEVUSHAL=? WHERE PRODUCT_ID=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setString(1, p.getName() );
                ps.setFloat(2, p.getPrice());
                ps.setBlob(3, p.getDescription());
                ps.setString(4, p.getMetaTagTitle());
                ps.setString(5,p.getMetaTagDescription());
                ps.setString(6, p.getMetaTagKeywords());
                ps.setBlob(7, p.getPic1());
                ps.setBlob(8, p.getPic2());
                ps.setBlob(9, p.getPic3());
                ps.setBlob(10, p.getPic4());
                ps.setBlob(11, p.getPic5());
                ps.setInt(12, p.getBrand());
                ps.setInt(13, p.getCategoryId());
                ps.setString(14, p.getVarietal());
                ps.setString(15, p.getAlcohol());
                ps.setString(16, p.getRegion());
                ps.setString(17, p.getYear());
                ps.setString(18, p.getMevushal());
                ps.setInt(19, p.getProductId());
                result = ps.executeUpdate();
                System.out.println("atualizacao de produto:"+result);
                conn.close();
                
            } catch (SQLException ex) {
                System.out.println("Erro ao atualizar produto"+ex.getLocalizedMessage());
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteProduct(Product p){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && p!=null){
            try {
                String sql = "DELETE FROM product WHERE product_id=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, p.getProductId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public List<Product> selectProducts(){
        Connection conn = new DBase(true).getConnection();
        List<Product> products = new ArrayList<Product>();
        
        if(conn!=null){
            try {
                String sql = "SELECT PRODUCT_ID, NAME, PRICE, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, PIC1, PIC2, PIC3, PIC4, PIC5, BRAND, CATEGORY_ID, VARIETAL, ALCOHOOL, REGION, YEAR, MEVUSHAL  FROM product ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("PRODUCT_ID"));
                    p.setName(rs.getString("NAME"));
                    p.setPrice(rs.getFloat("PRICE"));
                    p.setDescription(rs.getBlob("DESCRIPTION"));
                    p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    p.setPic1(rs.getBlob("PIC1"));
                    p.setPic2(rs.getBlob("PIC2"));
                    p.setPic3(rs.getBlob("PIC3"));
                    p.setPic4(rs.getBlob("PIC4"));
                    p.setPic5(rs.getBlob("PIC5"));
                    p.setBrand(rs.getInt("BRAND"));
                    p.setCategoryId(rs.getInt("CATEGORY_ID"));
                    p.setVarietal(rs.getString("VARIETAL"));
                    p.setAlcohol(rs.getString("ALCOHOOL"));
                    p.setYear(rs.getString("YEAR"));
                    p.setMevushal(rs.getString("MEVUSHAL"));
                    p.setRegion(rs.getString("REGION"));
                    
                    products.add(p);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
    
    public List<Product> selectProductsLight(){
        Connection conn = new DBase(true).getConnection();
        List<Product> products = new ArrayList<Product>();
        
        if(conn!=null){
            try {
                String sql = "SELECT PRODUCT_ID, NAME, PRICE, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, BRAND, CATEGORY_ID, VARIETAL, ALCOHOOL, REGION, YEAR, MEVUSHAL  FROM product ORDER BY product_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("PRODUCT_ID"));
                    p.setName(rs.getString("NAME"));
                    p.setPrice(rs.getFloat("PRICE"));
                    p.setDescription(rs.getBlob("DESCRIPTION"));
                    p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    p.setBrand(rs.getInt("BRAND"));
                    p.setCategoryId(rs.getInt("CATEGORY_ID"));
                    p.setVarietal(rs.getString("VARIETAL"));
                    p.setAlcohol(rs.getString("ALCOHOOL"));
                    p.setYear(rs.getString("YEAR"));
                    p.setMevushal(rs.getString("MEVUSHAL"));
                    p.setRegion(rs.getString("REGION"));
                    
                   // p.setPic1(rs.getBlob("PIC1"));
                   // p.setPic2(rs.getBlob("PIC2"));
                   // p.setPic3(rs.getBlob("PIC3"));
                   // p.setPic4(rs.getBlob("PIC4"));
                   // p.setPic5(rs.getBlob("PIC5"));
                    
                    products.add(p);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
    
    public Product selectProduct(int productId){
        Connection conn = new DBase(true).getConnection();
        Product p = new Product();
        
        if(conn!=null){
            try {
                String sql = "SELECT PRODUCT_ID, NAME, PRICE, DESCRIPTION, META_TAG_TITLE, META_TAG_DESCRIPTION, META_TAG_KEYWORDS, PIC1, PIC2, PIC3, PIC4, PIC5, BRAND, CATEGORY_ID, VARIETAL, ALCOHOOL, REGION, YEAR, MEVUSHAL  FROM product WHERE PRODUCT_ID=? ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, productId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   
                    p.setProductId(rs.getInt("PRODUCT_ID"));
                    p.setName(rs.getString("NAME"));
                    p.setPrice(rs.getFloat("PRICE"));
                    p.setDescription(rs.getBlob("DESCRIPTION"));
                    p.setMetaTagTitle(rs.getString("META_TAG_TITLE"));
                    p.setMetaTagDescription(rs.getString("META_TAG_DESCRIPTION"));
                    p.setMetaTagKeywords(rs.getString("META_TAG_KEYWORDS"));
                    p.setPic1(rs.getBlob("PIC1"));
                    p.setPic2(rs.getBlob("PIC2"));
                    p.setPic3(rs.getBlob("PIC3"));
                    p.setPic4(rs.getBlob("PIC4"));
                    p.setPic5(rs.getBlob("PIC5"));
                    p.setBrand(rs.getInt("BRAND"));
                    p.setCategoryId(rs.getInt("CATEGORY_ID"));
                    p.setVarietal(rs.getString("VARIETAL"));
                    p.setAlcohol(rs.getString("ALCOHOOL"));
                    p.setYear(rs.getString("YEAR"));
                    p.setMevushal(rs.getString("MEVUSHAL"));
                    p.setRegion(rs.getString("REGION"));
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
    
    
    
    // insert method for class LogUpload
    public void insertLogUpload(LogUpload l){
        Connection conn = new DBase(true).getConnection();
        
        if(conn!=null && l!=null){
            try {
                String sql = "INSERT INTO logupload (FILENAME, PATH, DESCRIPTION, STATUS) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                Blob description = conn.createBlob();
                description.setBytes(1, l.getDescription().getBytes());

                ps.setString(1,l.getFilename() );
                ps.setString(2, l.getPath());
                ps.setBlob(3, description);
                ps.setString(4, l.getStatus());
                ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertLog(Log l){
        Connection conn = new DBase(true).getConnection();
        
        if(conn!=null && l!=null){
            try {
                String sql = "INSERT INTO log (LOG, NAME, DESCRIPTION) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setBlob(1,l.getLog());
                ps.setString(2, l.getName());
                ps.setString(3, l.getDescription());
                
                ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
               // br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // insert method for class LogUpload
    public int insertMember(Member m){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && m!=null){
            try {
                String sql = "INSERT INTO member (NAME, EMAIL, PASS, TERMS, PERMISSION, DATE) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1,m.getName() );
                ps.setString(2, m.getEmail());
                ps.setString(3, m.getPass());
                ps.setString(4, m.getTerms());
                ps.setString(5, m.getPermission());
                ps.setTimestamp(6, Utils.dateToTimestamp(m.getDate()));
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int updateMember(Member m){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && m!=null){
            try {
                String sql = "UPDATE member SET NAME=?, EMAIL=?, PASS=?, TERMS=?, PERMISSION=?, DATE=? WHERE MEMBER_ID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1,m.getName() );
                ps.setString(2, m.getEmail());
                ps.setString(3, m.getPass());
                ps.setString(4, m.getTerms());
                ps.setString(5, m.getPermission());
                ps.setTimestamp(6, Utils.dateToTimestamp(m.getDate()));
                ps.setInt(7, m.getMemberId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int deleteMember(Member m){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && m!=null){
            try {
                String sql = "DELETE FROM member WHERE MEMBER_ID=? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, m.getMemberId());
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public int insertEmailMktProduct(int emailMktId, int productId){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && emailMktId>0 && productId >0){
            try {
                String sql = "INSERT INTO email_mkt_product (EMAIL_MKT_ID,PRODUCT_ID) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setInt(1,emailMktId );
                ps.setInt(2,productId);
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public int deleteEmailMktProduct(int emailMktId, int productId){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && emailMktId>0 && productId >0){
            try {
                String sql = "DELETE FROM email_mkt_product WHERE EMAIL_MKT_ID=? AND PRODUCT_ID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setInt(1,emailMktId );
                ps.setInt(2,productId);
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    // insert method for class Member Area Oper
    public int insertMemberAreaOper(int memberId, int areaOperacional){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && memberId>0 && areaOperacional >0){
            try {
                String sql = "INSERT INTO MEMBER_AREAOPER (MEMBER_ID, AREA_OPERACIONAL) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setInt(1,memberId );
                ps.setInt(2,areaOperacional);
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public int deleteMemberAreaOper(int memberId, int areaOperacional){
        Connection conn = new DBase(true).getConnection();
        int result=0;
        if(conn!=null && memberId>0 && areaOperacional >0){
            try {
                String sql = "DELETE FROM MEMBER_AREAOPER WHERE MEMBER_ID=? AND AREA_OPERACIONAL=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setInt(1,memberId );
                ps.setInt(2,areaOperacional);
               
                result = ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public List<Product> selectEmailMktProductDisponible(int emailMktId){
        Connection conn = new DBase(true).getConnection();
        List<Product> products = new ArrayList<Product>();
        
        if(conn!=null){
            try {
                String sql = "SELECT PROD.PRODUCT_ID, PROD.NAME, PROD.meta_tag_description FROM product PROD\n" +
                            "LEFT OUTER JOIN \n" +
                            "(\n" +
                            "SELECT p.product_id, p.name, p.meta_tag_description FROM email_mkt_product email, product p WHERE email.product_id=p.product_id AND email.email_mkt_id=? ORDER BY p.name) AS product_area \n" +
                            "ON PROD.product_id = product_area.product_id \n" +
                            "WHERE product_area.product_id IS NULL";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    p.setMetaTagDescription(rs.getString("meta_tag_description"));
                    products.add(p);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
    public List<Product> selectEmailMktProduct(int emailMktId){
        Connection conn = new DBase(true).getConnection();
        List<Product> products = new ArrayList<Product>();
        
        if(conn!=null){
            try {
                String sql = "SELECT p.product_id, p.name, p.meta_tag_description, p.price, p.pic1 FROM email_mkt_product email, product p WHERE email.product_id=p.product_id AND email.email_mkt_id=? ORDER BY p.name";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    p.setMetaTagDescription(rs.getString("meta_tag_description"));
                    p.setPrice(emailMktId);
                    p.setPic1(rs.getBlob("pic1"));
                    products.add(p);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
    
    public List<Product> selectEmailMktProductLight(int emailMktId){
        Connection conn = new DBase(true).getConnection();
        List<Product> products = new ArrayList<Product>();
        
        if(conn!=null){
            try {
                String sql = "SELECT p.product_id, p.name, p.meta_tag_description, p.price, p.description FROM email_mkt_product email, product p WHERE email.product_id=p.product_id AND email.email_mkt_id=? ORDER BY p.name";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, emailMktId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getFloat("price"));
                    p.setMetaTagDescription(rs.getString("meta_tag_description"));
                    p.setDescription(rs.getBlob("description"));
                   // p.setPrice(emailMktId);
                   // p.setPic1(rs.getBlob("pic1"));
                    products.add(p);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return products;
    }
    
    public List<MemberAreaOper> selectMemberAreaOperDisponible(int memberId){
        Connection conn = new DBase(true).getConnection();
        List<MemberAreaOper> areas = new ArrayList<MemberAreaOper>();
        
        if(conn!=null){
            try {
                String sql = "SELECT AO.AREA_OPERACIONAL, AO.DESCRICAO FROM AREAOPER AO\n" +
                            "LEFT OUTER JOIN \n" +
                            "(\n" +
                            "SELECT MA.MEMBER_ID, MA.AREA_OPERACIONAL, A.DESCRICAO \n" +
                            "FROM MEMBER_AREAOPER MA, MEMBER M, AREAOPER A \n" +
                            "WHERE MA.MEMBER_ID=M.MEMBER_ID AND \n" +
                            "MA.AREA_OPERACIONAL=A.AREA_OPERACIONAL AND \n" +
                            "MA.MEMBER_ID=? \n" +
                            "ORDER BY A.DESCRICAO) AS MEMBER_AREA\n" +
                            "ON AO.AREA_OPERACIONAL = MEMBER_AREA.AREA_OPERACIONAL\n" +
                            "WHERE MEMBER_AREA.AREA_OPERACIONAL IS NULL";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    AreaOper a = new AreaOper();
                    a.setArea_operacional(rs.getString("AREA_OPERACIONAL"));
                    a.setDescricao(rs.getString("DESCRICAO"));
                    Member m = new Member();
                    m.setMemberId(memberId);
                    MemberAreaOper ma = new MemberAreaOper(m,a);
                    areas.add(ma);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return areas;
    }
    public List<MemberAreaOper> selectMemberAreaOper(int memberId){
        Connection conn = new DBase(true).getConnection();
        List<MemberAreaOper> areas = new ArrayList<MemberAreaOper>();
        
        if(conn!=null){
            try {
                String sql = "SELECT MA.MEMBER_ID, MA.AREA_OPERACIONAL, A.DESCRICAO FROM MEMBER_AREAOPER MA, MEMBER M, AREAOPER A WHERE MA.MEMBER_ID=M.MEMBER_ID AND MA.AREA_OPERACIONAL=A.AREA_OPERACIONAL AND MA.MEMBER_ID=? ORDER BY A.DESCRICAO";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    AreaOper a = new AreaOper();
                    a.setArea_operacional(rs.getString("AREA_OPERACIONAL"));
                    a.setDescricao(rs.getString("DESCRICAO"));
                    Member m = new Member();
                    m.setMemberId(rs.getInt("MEMBER_ID"));
                    MemberAreaOper ma = new MemberAreaOper(m,a);
                    areas.add(ma);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return areas;
    }
    public List<MemberAreaOper> selectMemberAreaOper(int memberId, String regional){
        Connection conn = new DBase(true).getConnection();
        List<MemberAreaOper> areas = new ArrayList<MemberAreaOper>();
        
        if(conn!=null){
            try {
                String sql = "SELECT MA.MEMBER_ID, MA.AREA_OPERACIONAL, A.DESCRICAO \n" +
                            "FROM MEMBER_AREAOPER MA, MEMBER M, AREAOPER A, MUNICIPIOS MU \n" +
                            "WHERE \n" +
                            "	MA.MEMBER_ID=M.MEMBER_ID \n" +
                            "    AND MU.AREA_OPERACIONAL = MA.AREA_OPERACIONAL \n" +
                            "	AND MA.AREA_OPERACIONAL=A.AREA_OPERACIONAL \n" +
                            "	AND MA.MEMBER_ID=? \n" +
                            "	AND MU.REGIAO_MBB=? \n" +
                            "GROUP BY MU.AREA_OPERACIONAL \n" +
                            "ORDER BY A.DESCRICAO";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ps.setString(2, regional);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    AreaOper a = new AreaOper();
                    a.setArea_operacional(rs.getString("AREA_OPERACIONAL"));
                    a.setDescricao(rs.getString("DESCRICAO"));
                    Member m = new Member();
                    m.setMemberId(rs.getInt("MEMBER_ID"));
                    MemberAreaOper ma = new MemberAreaOper(m,a);
                    areas.add(ma);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return areas;
    }
    public List<Member> selectMembers(){
        Connection conn = new DBase(true).getConnection();
        List<Member> members = new ArrayList<Member>();
        
        if(conn!=null){
            try {
                String sql = "SELECT MEMBER_ID, NAME, EMAIL, PASS, REGDATE, TERMS, PERMISSION FROM member ORDER BY NAME";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Member m = new Member();
                    m.setMemberId(rs.getInt("MEMBER_ID"));
                    m.setName(rs.getString("NAME"));
                    m.setEmail(rs.getString("EMAIL"));
                    m.setRegdate(rs.getDate("REGDATE"));
                    m.setTerms(rs.getString("TERMS"));
                    m.setPermission(rs.getString("PERMISSION"));
                    
                    members.add(m);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return members;
    }
    public Member selectMembers(int memberId){
        Connection conn = new DBase(true).getConnection();
        //List<Member> members = new ArrayList<Member>();
         Member m = new Member();
        if(conn!=null){
            try {
                String sql = "SELECT MEMBER_ID, NAME, EMAIL, PASS, REGDATE, TERMS, PERMISSION, DATE FROM member WHERE MEMBER_ID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   
                    m.setMemberId(rs.getInt("MEMBER_ID"));
                    m.setName(rs.getString("NAME"));
                    m.setEmail(rs.getString("EMAIL"));
                    m.setRegdate(rs.getDate("REGDATE"));
                    m.setTerms(rs.getString("TERMS"));
                    m.setPermission(rs.getString("PERMISSION"));
                    m.setPass(rs.getString("PASS"));
                    m.setDate(rs.getDate("DATE"));
                   // members.add(m);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return m;
    }
    
    // insert method for class LogUpload
    public Member login(Member m){
        Connection conn = new DBase(true).getConnection();
        Member member = new Member();
        
        if(conn!=null && m!=null){
            try {
                String sql = "SELECT MEMBER_ID, NAME,EMAIL, REGDATE, PERMISSION, DATE FROM member WHERE EMAIL=? AND PASS= ? LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, m.getEmail());
                ps.setString(2, m.getPass());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    member.setMemberId(rs.getInt("MEMBER_ID"));
                    member.setEmail(rs.getString("EMAIL"));
                    member.setName(rs.getString("NAME"));
                    member.setRegdate(rs.getTimestamp("REGDATE"));
                    member.setPermission(rs.getString("PERMISSION"));
                    member.setDate(rs.getTimestamp("DATE"));
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return member;
    }
    // profile
    public List<Profile> selectProfile(){
        Connection conn = new DBase(true).getConnection();
        List<Profile> listProfile = new ArrayList<Profile>();
        if(conn!=null){
            try {
                String sql = "SELECT PROFILE_ID, PROFILE, PERMISSION FROM profile ORDER BY PROFILE_ID";
                PreparedStatement ps = conn.prepareStatement(sql);
                //ps.setInt(1, profileId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Profile p = new Profile();
                    p.setProfileId(rs.getInt("PROFILE_ID"));
                    p.setProfile(rs.getString("PROFILE"));
                    p.setPermission(rs.getString("PERMISSION"));
                    listProfile.add(p);
                }
                conn.close();
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listProfile;
    }
    public Profile selectProfile(int profileId){
        Connection conn = new DBase(true).getConnection();
         Profile p = new Profile();
        if(conn!=null){
            try {
                String sql = "SELECT PROFILE_ID, PROFILE, PERMISSION FROM profile WHERE PROFILE_ID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, profileId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    p.setProfileId(rs.getInt("PROFILE_ID"));
                    p.setProfile(rs.getString("PROFILE"));
                    p.setPermission(rs.getString("PERMISSION"));
                }
                conn.close();
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
    // area operacional
     public List<AreaOper> selectAreaOper(){
        Connection conn = new DBase(true).getConnection();
        List<AreaOper> area = new ArrayList<AreaOper>();
        
        if(conn!=null){
            try {
                String sql = "SELECT AREA_OPERACIONAL, DESCRICAO FROM areaoper ORDER BY DESCRICAO";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    AreaOper a = new AreaOper();
                    a.setArea_operacional(rs.getString("AREA_OPERACIONAL"));
                    a.setDescricao(rs.getString("DESCRICAO"));
                    area.add(a);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return area;
    }
     public List<AreaOper> selectAreaOper(String ini, String fim, String regional){
        Connection conn = new DBase(true).getConnection();
        List<AreaOper> area = new ArrayList<AreaOper>();
        
        if(conn!=null){
            try {
                String sql = "select DEALER_AOP AS DESCRICAO, AREA_OPERACIONAL from gic where DATA_COMPLETA BETWEEN ? AND ? AND REGIAO_MBB = ? GROUP BY AREA_OPERACIONAL ORDER BY DEALER_AOP";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, regional);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    AreaOper a = new AreaOper();
                    a.setArea_operacional(rs.getString("AREA_OPERACIONAL"));
                    a.setDescricao(rs.getString("DESCRICAO"));
                    area.add(a);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return area;
    }
    // insert method for class LogUpload
    public LogUpload selectLogUpload(LogUpload l){
        Connection conn = new DBase(true).getConnection();
        
        if(conn!=null && l!=null){
            try {
                String sql = "SELECT LOGUPLOAD_ID, FILENAME, PATH, DESCRIPTION, STATUS, DATE FROM  logupload WHERE PATH=? ORDER BY LOGUPLOAD_ID DESC LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, l.getPath());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    java.sql.Blob ablob = rs.getBlob("DESCRIPTION");
                    String strDescription = new String(ablob.getBytes(1l, (int) ablob.length()));

                    LogUpload log = new LogUpload();
                    log.setLoguploadId(rs.getInt("LOGUPLOAD_ID"));
                    log.setFilename(rs.getString("FILENAME"));
                    log.setPath(rs.getString("PATH"));
                    log.setDescription(strDescription);
                    log.setStatus(rs.getString("STATUS"));
                    log.setDate(rs.getTimestamp("DATE"));
                    
                    return log;
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l;
    }
    
    // insert method for class LogFiles
    public void insertLogFiles(LogFiles l){
        Connection conn = new DBase(true).getConnection();
        
        if(conn!=null && l!=null){
            try {
                String sql = "INSERT INTO logfiles (NAME, ICON, SIZE, STATUS, STATUS_ICON, URL, THUMBNAIL_URL, DELETE_URL, DELETE_TYPE, JSON) VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1,l.getName());
                ps.setString(2, l.getIcon());
                ps.setString(3, l.getSize());
                ps.setString(4,l.getStatus());
                ps.setString(5, l.getSatusIcon());
                ps.setString(6, l.getUrl());
                ps.setString(7, l.getThumbnailURL());
                ps.setString(8, l.getDeteleURL());
                ps.setString(9, l.getDeleteType());
                ps.setString(10, l.getJson());
                ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteLogFiles(LogFiles l){
        Connection conn = new DBase(true).getConnection();
        
        if(conn!=null && l!=null){
            try {
                String sql = "DELETE FROM logfiles WHERE NAME = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1,l.getName());
                ps.executeUpdate();
                
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<LogFiles> selectLogFiles(){
        Connection conn = new DBase(true).getConnection();
        List<LogFiles> listLogFiles = new ArrayList<LogFiles>();
        if(conn!=null){
            try {
                String sql = "SELECT * FROM logfiles";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    LogFiles l = new LogFiles(rs.getString("NAME"), rs.getString("ICON"), rs.getString("SIZE"),
                                            rs.getString("STATUS"), rs.getString("STATUS_ICON"), rs.getString("URL"),
                                            rs.getString("THUMBNAIL_URL"), rs.getString("DELETE_URL"), rs.getString("DELETE_TYPE"), rs.getString("JSON"));
                    listLogFiles.add(l);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLogFiles;
    }
    
    public List<LogFiles> selectLogFilesSearch(String keyword){
        Connection conn = new DBase(true).getConnection();
        List<LogFiles> listLogFiles = new ArrayList<LogFiles>();
        if(conn!=null){
            try {
                String sql = "SELECT * FROM logfiles WHERE NAME LIKE ? ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "%"+keyword+"%");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
                    LogFiles l = new LogFiles(rs.getString("NAME"), rs.getString("ICON"), rs.getString("SIZE"),
                                            rs.getString("STATUS"), rs.getString("STATUS_ICON"), rs.getString("URL"),
                                            rs.getString("THUMBNAIL_URL"), rs.getString("DELETE_URL"), rs.getString("DELETE_TYPE"), rs.getString("JSON"));
                    listLogFiles.add(l);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLogFiles;
    }
    
    public int selectCountChassi(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        int count=0;
        if(conn!=null){
            try {
                String sql = "SELECT COUNT(*) AS COUNTCHASSI\n" +
"                            FROM EMPLACAMENTOS E, MODELOS M, SEGMENTO S,MUNICIPIOS MU, AREAOPER A, GIC G \n" +
"                            WHERE E.MODELO=M.MODELO AND\n" +
                            "G.CHASSI=E.CHASSI AND\n" +
                            "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "E.MUNICIPIO=MU.MUNICIPIO AND\n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND\n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND"+
"                            M.SEGMENTO=S.SEGMENTO AND\n" +
"                            E.DATA BETWEEN ? AND ? AND \n" +
"							S.SEGMENTO IN ("+seg+")";
                br.com.itfox.utils.Logger.getLogger("CountChassi:"+sql+"ini:"+ini+"fim:"+fim+"seg:"+seg);
                
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                
                
                while(rs.next()){
                    count=rs.getInt("COUNTCHASSI");
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }
    
    public List<CollectionColumns> selectDistribuicaoSeg(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT COUNT(SS.SUBSEGMENTO) AS NUMVEIC, S.SEGMENTO, SS.DESCRICAO \n " +
                            "FROM EMPLACAMENTOS E, MODELOS M, SEGMENTO S, SUBSEGMENTO SS, MUNICIPIOS MU, AREAOPER A, GIC G  \n " +
                            "WHERE E.MODELO=M.MODELO AND\n " +
                            "G.CHASSI=E.CHASSI AND\n" +
                            "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                            "M.SEGMENTO=S.SEGMENTO AND\n " +
                            "M.SUBSEGMENTO=SS.SUBSEGMENTO AND M.SEGMENTO=SS.SEGMENTO AND \n " +
                            "E.DATA BETWEEN ? AND ? AND\n " +
                            "S.SEGMENTO IN ("+seg+") \n " +
                            "GROUP BY SS.SUBSEGMENTO ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getString("NUMVEIC");
                    desc= rs.getString("DESCRICAO");
                    CollectionColumns c = new CollectionColumns(new String[]{count,"",desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectDistribuicaoMarca(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT COUNT(*) AS TOTAL, MA.DESCRICAO \n " +
                            "FROM EMPLACAMENTOS E, MODELOS M, MARCAS MA, SEGMENTO S, MUNICIPIOS MU, AREAOPER A, GIC G \n " +
                            "WHERE E.MODELO=M.MODELO AND\n " +
                            "G.CHASSI=E.CHASSI AND\n" +
                            "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                            "M.FABRICANTE=MA.MARCA AND\n " +
                            "M.SEGMENTO=S.SEGMENTO AND\n " +
                            "E.DATA BETWEEN ? AND ? AND\n "+
                            "S.SEGMENTO IN ("+seg+")   "+
                            "GROUP BY MA.MARCA";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setString(1, ini);
                ps.setString(2, fim);
                //ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getString("TOTAL");
                    desc= rs.getString("DESCRICAO");
                    CollectionColumns c = new CollectionColumns(new String[]{count,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectRankMunic(String ini, String fim, String seg,String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT COUNT(*) AS TOTAL, MU.NOME\n" +
                            "FROM EMPLACAMENTOS E, MODELOS M, MARCAS MA, SEGMENTO S, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                            "WHERE E.MODELO=M.MODELO AND \n" +
                            "G.CHASSI=E.CHASSI AND \n" +
                            "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "M.FABRICANTE=MA.MARCA AND\n" +
                            "M.SEGMENTO=S.SEGMENTO AND\n" +
                            "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                            "E.DATA BETWEEN ? AND ? AND\n" +
                            "S.SEGMENTO IN ("+seg+")  \n" +
                            "GROUP BY MU.NOME\n" +
                            "ORDER BY TOTAL DESC\n" +
                            "LIMIT 10";
                                            PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                //ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getString("TOTAL");
                    desc= rs.getString("NOME");
                    CollectionColumns c = new CollectionColumns(new String[]{count,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectRankModel(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT COUNT(*) AS TOTAL, M.DESCRICAO\n" +
                            "FROM EMPLACAMENTOS E, MODELOS M, MARCAS MA, SEGMENTO S, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                            "WHERE E.MODELO=M.MODELO AND \n" +
                            "G.CHASSI=E.CHASSI AND \n" +
                            "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "M.FABRICANTE=MA.MARCA AND\n" +
                            "M.SEGMENTO=S.SEGMENTO AND\n" +
                            "E.MUNICIPIO=MU.MUNICIPIO AND\n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                            "E.DATA BETWEEN ? AND ? AND\n" +
                            "S.SEGMENTO IN ("+seg+")  \n" +
                            "GROUP BY M.MODELO\n" +
                            "ORDER BY TOTAL DESC\n" +
                            "LIMIT 10";
                                            PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getString("TOTAL");
                    desc= rs.getString("DESCRICAO");
                    CollectionColumns c = new CollectionColumns(new String[]{count,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSegmentos(){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT SEGMENTO, DESCRICAO FROM SEGMENTO";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("SEGMENTO");
                    desc= rs.getString("DESCRICAO");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSegmentos(String ini, String fim){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "select segmento, seg from gic where data_completa between ? and ? group by segmento";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("SEG");
                    desc= rs.getString("SEGMENTO");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSegmentos(String ini, String fim, String subSegmento){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "select segmento, seg from gic where data_completa between ? and ? and subseg=? group by segmento";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, subSegmento);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("SEG");
                    desc= rs.getString("SEGMENTO");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectRegional(){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String reg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT REGIAO_MBB FROM municipios WHERE REGIAO_MBB IS NOT NULL GROUP BY REGIAO_MBB";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    reg=rs.getString("REGIAO_MBB");
                    CollectionColumns c = new CollectionColumns(new String[]{reg});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectRegional(String ini, String fim){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String reg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "SELECT REGIAO_MBB FROM GIC WHERE REGIAO_MBB IS NOT NULL AND DATA_COMPLETA BETWEEN ? AND ? GROUP BY REGIAO_MBB";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    reg=rs.getString("REGIAO_MBB");
                    CollectionColumns c = new CollectionColumns(new String[]{reg});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSubSegmentos(String ini, String fim){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "select subsegmento, subseg from gic where data_completa between ? and ? group by subsegmento";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("subSEG");
                    desc= rs.getString("subSEGMENTO");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSubSegmentos(){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        if(conn!=null){
            try {
                String sql = "select subsegmento, subseg from gic group by subsegmento";
                PreparedStatement ps = conn.prepareStatement(sql);
               // ps.setString(1, ini);
                //ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("subSEG");
                    desc= rs.getString("subSEGMENTO");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<Brand> selectBrands(){
        Connection conn = new DBase(true).getConnection();
        List<Brand> list = new ArrayList<Brand>();
        
        if(conn!=null){
            try {
                String sql = "select brand_id, brand from brand";
                PreparedStatement ps = conn.prepareStatement(sql);
               // ps.setString(1, ini);
                //ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Brand b = new Brand();
                    b.setBrandId(rs.getInt("brand_id"));
                    b.setBrand(rs.getString("brand"));
                    
                    list.add(b);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<Category> selectCategories(){
        Connection conn = new DBase(true).getConnection();
        List<Category> list = new ArrayList<Category>();
        
        if(conn!=null){
            try {
                String sql = "select category_id, category, category_tag, description, image1 from category";
                PreparedStatement ps = conn.prepareStatement(sql);
               // ps.setString(1, ini);
                //ps.setString(2, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Category c = new Category();
                    c.setCategoryId(rs.getInt("category_id"));
                    c.setCategory(rs.getString("category"));
                    c.setCategoryTag(rs.getString("category_tag"));
                    c.setDescription(rs.getString("description"));
                    c.setImage1(rs.getString("image1"));
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> selectSegmentos(String segmento){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String seg= "";
        String desc = "";
        String ref = "";
        String tipo = "";
        String idTipo = "";
        if(conn!=null){
            try {
                String sql = "SELECT SEGMENTO, DESCRICAO, REFERENCIA, TIPO_VEIC, ID_TIPO_VEIC FROM SEGMENTO WHERE SEGMENTO=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, segmento);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    seg=rs.getString("SEGMENTO");
                    desc= rs.getString("DESCRICAO");
                    ref = rs.getString("REFERENCIA");
                    tipo = rs.getString("TIPO_VEIC");
                    idTipo = rs.getString("ID_TIPO_VEIC");
                    CollectionColumns c = new CollectionColumns(new String[]{seg,desc, ref, tipo, idTipo});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
     public List<CollectionColumns> selectPerfilCliente(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String pf="";
        String pj = "";
        String total = "";
        String chassis="";
        String clientes="";
        if(conn!=null){
            try {
               
                String sql =  
                        "SELECT SUM(Q.FISICA) AS FISICA, SUM(Q.JURIDICA) AS JURIDICA FROM (\n" +
                        "	SELECT  \n" +
                        "		Count(Case When G.C_TIPOCNPJPROPRIETARIO = 'FISICA' Then G.C_TIPOCNPJPROPRIETARIO Else Null End) As FISICA,\n" +
                        "		Count(Case When G.C_TIPOCNPJPROPRIETARIO = 'JURIDICA' Then G.C_TIPOCNPJPROPRIETARIO Else Null End) As JURIDICA \n"+
                        "FROM EMPLACAMENTOS AS E, MODELOS AS M, SEGMENTO  AS S , MARCAS AS MA, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "WHERE \n" +
                        "G.CHASSI=E.CHASSI AND\n" +
                        "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                        "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                        "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                        "M.SEGMENTO=S.SEGMENTO AND\n" +
                        "M.MODELO=E.MODELO AND\n" +
                        "M.FABRICANTE=MA.MARCA AND\n" +
                        "S.SEGMENTO IN ("+seg+")  AND\n" +
                        "E.DATA BETWEEN ? AND ?\n" +
                        "GROUP BY G.C_TIPOCNPJPROPRIETARIO) AS Q";
                PreparedStatement ps = conn.prepareStatement(sql);
                // rows-pf
               // ps.setString(1, seg);
                ps.setString(1, ini);
                ps.setString(2, fim);
                /*
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, seg);
                ps.setString(2, ini);
                ps.setString(3, fim);
                ps.setString(4, seg);
                ps.setString(5, ini);
                ps.setString(6, fim);*/
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    //pf=rs.getString("PF");
                    //pj= rs.getString("PJ");
                    //total= rs.getString("TOTAL");
                    pf= rs.getString("FISICA");
                    pj= rs.getString("JURIDICA");
                    total = String.valueOf(Integer.parseInt(pf)+Integer.parseInt(pj));
                    CollectionColumns c = new CollectionColumns(new String[]{pf,pj,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
     
    public List<CollectionColumns> selectChassiCliente(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String pf="";
        String pj = "";
        String total = "";
        String chassis="";
        String clientes="";
        if(conn!=null){
            try {
               
                /** 
                 * String sql = "SELECT PF,PJ,(PF+PJ) AS TOTAL  FROM\n" +
                    "	(SELECT COUNT(PF) AS PF FROM\n" +
                    "		(SELECT COUNT(*) AS PF \n" +
                    "		FROM EMPLACAMENTOS E, PF_RETORNO PF,MODELOS M, SEGMENTO S, MUNICIPIOS MU, AREAOPER A \n" +
                    "		WHERE\n" +
                    "           E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                    "           MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                    "           A.AREA_OPERACIONAL IN ("+areaOper+") AND "+    
                    "		E.MODELO=M.MODELO AND\n" +
                    "		M.SEGMENTO=S.SEGMENTO AND\n" +
                    "		E.CHASSI = PF.CHASSI AND\n" +
                    "		S.SEGMENTO = ? AND \n" +
                    "		E.DATA BETWEEN ? AND ? \n" +
                    "		GROUP BY PF.CPFCNPJPROPRIETARIO) AS PF) AS PF,\n" +
                    "	 \n" +
                    "		(SELECT COUNT(PJ) AS PJ FROM\n" +
                    "		(SELECT COUNT(*) AS PJ \n" +
                    "		FROM EMPLACAMENTOS E, PJ_RETORNO PJ, MODELOS M, SEGMENTO S, MUNICIPIOS MU, AREAOPER A \n" +
                    "		WHERE\n" +
                    "           E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                    "           MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                    "           A.AREA_OPERACIONAL IN ("+areaOper+") AND "+  
                    "		E.MODELO=M.MODELO AND\n" +
                    "		M.SEGMENTO=S.SEGMENTO AND\n" +
                    "		E.CHASSI = PJ.CHASSI AND\n" +
                    "		S.SEGMENTO = ? AND\n" +
                    "		E.DATA BETWEEN ? AND ? \n" +
                    "		GROUP BY PJ.CPFCNPJPROPRIETARIO ) AS PJ)AS PJ ";
                    **/
                String sql = "";
                       /* sql = "SELECT SUM(Q.TOTAL) AS CHASSIS, SUM(Q.CLIENTES) AS CLIENTES FROM \n" +
                        "(SELECT COUNT(*) AS TOTAL,MA.DESCRICAO, MA.MARCA,COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) AS CLIENTES \n" +
                        "FROM EMPLACAMENTOS AS E, MODELOS AS M, SEGMENTO  AS S , MARCAS AS MA, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "WHERE \n" +
                        "G.CHASSI=E.CHASSI AND\n" +
                        "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                        "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                        "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                        "M.SEGMENTO=S.SEGMENTO AND\n" +
                        "M.MODELO=E.MODELO AND\n" +
                        "M.FABRICANTE=MA.MARCA AND\n" +
                        "S.SEGMENTO IN ("+seg+")  AND\n" +
                        "E.DATA BETWEEN ? AND ?\n" +
                        "GROUP BY MA.MARCA) AS Q";*/
                        
                sql = "SELECT SUM(Q.TOTAL) AS CHASSIS, SUM(Q.CLIENTES) AS CLIENTES FROM \n" +
                        "	(\n" +
                        "	SELECT COUNT(*) AS TOTAL, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) AS CLIENTES \n" +
                        "	FROM  GIC G \n" +
                        "	WHERE \n" +
                        "	G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n" +
                        "	  length(G.C_CPFCNPJPROPRIETARIO )>1 AND\n" +
                        "	G.AREA_OPERACIONAL IN ("+areaOper+") AND \n" +
                        "	G.SEG IN ("+seg+")  AND\n" +
                        "	G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                      //  "   -- GROUP BY G.MARCA_COMPLETA\n" +
                        ") AS Q";
                PreparedStatement ps = conn.prepareStatement(sql);
                // rows-pf
               // ps.setString(1, seg);
                ps.setString(1, ini);
                ps.setString(2, fim);
                /*
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, seg);
                ps.setString(2, ini);
                ps.setString(3, fim);
                ps.setString(4, seg);
                ps.setString(5, ini);
                ps.setString(6, fim);*/
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    //pf=rs.getString("PF");
                    //pj= rs.getString("PJ");
                    //total= rs.getString("TOTAL");
                    chassis= rs.getString("CHASSIS");
                    clientes= rs.getString("CLIENTES");
                    CollectionColumns c = new CollectionColumns(new String[]{chassis,clientes});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
     public List<CollectionColumns> selectChassiClienteMontadora(String ini, String fim, String seg, String areaOper, String areaOperSepareted){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String pf="";
        String pj = "";
        String desc="";
        String marca="";
        String rows_pf="";
        String rows_pj="";
        String total = "";
        String clientes="";
        if(conn!=null){
            try {
                /*
                String sql = "SELECT COUNT(*) AS TOTAL,MA.DESCRICAO, MA.MARCA, (\n" +
                       // "	-- SELECT COUNT(PF) AS PF FROM(\n" +
                        "		SELECT COUNT(*) AS PF \n" +
                        "		FROM EMPLACAMENTOS EE, PF_RETORNO PF,MODELOS EM, SEGMENTO ES, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "		WHERE\n" +
                        "               G.CHASSI=E.CHASSI AND\n" +
                        "               G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "               EE.MUNICIPIO=MU.MUNICIPIO AND\n" +
                        "               MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND\n" +
                        "		A.AREA_OPERACIONAL IN ("+areaOperSepareted+") AND"+
                        "		EE.MODELO=EM.MODELO AND\n" +
                        "		EM.SEGMENTO=ES.SEGMENTO AND\n" +
                        "		EE.CHASSI = PF.CHASSI AND\n" +
                        "		ES.SEGMENTO IN (?) AND\n" +
                        "		EM.FABRICANTE in(MA.MARCA)  AND\n" +
                        "		EE.DATA BETWEEN ? AND ? \n" +
                     //   "		-- GROUP BY PF.CPFCNPJPROPRIETARIO\n" +
                        "		LIMIT 1\n" +
                        "	 ) AS ROWS_PF,\n" +
                        "(\n" +
                    //    "	-- SELECT COUNT(PF) AS PF FROM(\n" +
                        "		SELECT COUNT(*) AS PJ \n" +
                        "		FROM EMPLACAMENTOS EE, PJ_RETORNO PJ,MODELOS EM, SEGMENTO ES, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "		WHERE\n" +
                        "               G.CHASSI=E.CHASSI AND\n" +
                        "               G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "               EE.MUNICIPIO=MU.MUNICIPIO AND\n" +
                        "               MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND\n" +
                        "		A.AREA_OPERACIONAL IN ("+areaOperSepareted+") AND"+
                        "		EE.MODELO=EM.MODELO AND\n" +
                        "		EM.SEGMENTO=ES.SEGMENTO AND\n" +
                        "		EE.CHASSI = PJ.CHASSI AND\n" +
                        "		ES.SEGMENTO IN (?) AND\n" +
                        "		EM.FABRICANTE in(MA.MARCA)  AND\n" +
                        "		EE.DATA BETWEEN ? AND ? \n" +
                      //  "		-- GROUP BY PF.CPFCNPJPROPRIETARIO\n" +
                        "		LIMIT 1\n" +
                        "	 ) AS ROWS_PJ\n" +
                        "	,(\n" +
                        "		SELECT PF_FABRICANTE(?,?,?,MA.MARCA,"+areaOper+")\n" +
                        "	 ) AS PF,(\n" +
                        "		SELECT PJ_FABRICANTE(?,?,?,MA.MARCA,"+areaOper+")\n" +
                        "	 ) AS PJ\n" +
                        "	 \n" +
                        "FROM EMPLACAMENTOS AS E, MODELOS AS M, SEGMENTO  AS S , MARCAS AS MA, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "WHERE \n" +
                        "G.CHASSI=E.CHASSI AND\n" +
                        "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                        "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                        "A.AREA_OPERACIONAL IN ("+areaOperSepareted+") AND "+
                        "M.SEGMENTO=S.SEGMENTO AND\n" +
                        "M.MODELO=E.MODELO AND\n" +
                        "M.FABRICANTE=MA.MARCA AND\n" +
                        "S.SEGMENTO = ? AND\n" +
                        "E.DATA BETWEEN ? AND ?\n" +
                        "GROUP BY MA.MARCA";
               
                        */
                String sql = "SELECT COUNT(*) AS TOTAL,MA.DESCRICAO, MA.MARCA,COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) AS CLIENTES \n" +
                        "FROM EMPLACAMENTOS AS E, MODELOS AS M, SEGMENTO  AS S , MARCAS AS MA, MUNICIPIOS MU, AREAOPER A, GIC G \n" +
                        "WHERE \n" +
                        "G.CHASSI=E.CHASSI AND\n" +
                        "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                        "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                        "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                        "A.AREA_OPERACIONAL IN ("+areaOperSepareted+") AND "+
                        "M.SEGMENTO=S.SEGMENTO AND\n" +
                        "M.MODELO=E.MODELO AND\n" +
                        "M.FABRICANTE=MA.MARCA AND\n" +
                        "S.SEGMENTO IN ("+seg+")  AND\n" +
                        "E.DATA BETWEEN ? AND ?\n" +
                        "GROUP BY MA.MARCA";
                PreparedStatement ps = conn.prepareStatement(sql);
                // rows-pf
               // ps.setString(1, seg);
                ps.setString(1, ini);
                ps.setString(2, fim);
                /*
                // rows-pj
                ps.setString(4, seg);
                ps.setString(5, ini);
                ps.setString(6, fim);
                // func unique-pf
                ps.setString(7, ini);
                ps.setString(8, fim);
                ps.setString(9, seg);
                // func unique-pj
                ps.setString(10, ini);
                ps.setString(11, fim);
                ps.setString(12, seg);
                // group marca
                ps.setString(13, seg);
                ps.setString(14, ini);
                ps.setString(15, fim);*/
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   // pf=rs.getString("PF");
                   // pj= rs.getString("PJ");
                    total= rs.getString("TOTAL");
                    desc= rs.getString("DESCRICAO");
                    marca= rs.getString("MARCA");
                    clientes= rs.getString("CLIENTES");
                   // rows_pf= rs.getString("ROWS_PF");
                   // rows_pj= rs.getString("ROWS_PJ");
                    CollectionColumns c = new CollectionColumns(new String[]{total, desc, marca, clientes});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
     public List<Gic> selectGic(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<Gic> list = new ArrayList<Gic>();
        String pf="";
        String pj = "";
        String total = "";
        /*
        switch(seg){
            case "2":
                seg="VANS";
                break;
            case "3":
                seg="CAMINHOES";
                break;
            case "4":
                seg="ONIBUS";
                break;
            default:
                seg="";
                break;
                
        }*/
        //seg = segToGic(seg);
        // br.com.itfox.utils.Logger.getLogger("segmento:"+seg);
        // System.out.println("segmento:"+seg);
        if(conn!=null){
            try {
                String sql = "SELECT * FROM GIC \n" +
                "WHERE DATA_COMPLETA BETWEEN ? AND ? AND SEG IN ("+seg+")  AND AREA_OPERACIONAL IN("+areaOper+") AND C_CPFCNPJPROPRIETARIO IS NOT NULL";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, seg);
                
                ResultSet rs = ps.executeQuery();
                int count=0;
                while(rs.next()){
                    int numCol=156+1;
                    String[] cols = new String[numCol+1];
                    for(int i=1;i<numCol;i++){
                        cols[i]=nullToString(rs.getString(i));
                    }
                    // convert date
                    String data = Utils.dateFormatPt(cols[2]);
                    //br.com.itfox.utils.Logger.getLogger(data);
                    Gic g = new Gic(cols[1],data,cols[3],cols[4],cols[5],cols[6],cols[7],cols[8],cols[9],cols[10],cols[11],cols[12],cols[13],cols[14],cols[15],cols[16],cols[17],cols[18],cols[19],cols[20],cols[21],cols[22],cols[23],cols[24],cols[25],cols[26],cols[27],cols[28],cols[29],cols[30],cols[31],cols[32],cols[33],cols[34],cols[35],cols[36],cols[37],cols[38],cols[39],cols[40],cols[41],cols[42],cols[43],cols[44],cols[45],cols[46],cols[47],cols[48],cols[49],cols[50],cols[51],cols[52],cols[53],cols[54],cols[55],cols[56],cols[57],cols[58],cols[59],cols[60],cols[61],cols[62],cols[63],cols[64],cols[65],cols[66],cols[67],cols[68],cols[69],cols[70],cols[71],cols[72],cols[73],cols[74],cols[75],cols[76],cols[77],cols[78],cols[79],cols[80],cols[81],cols[82],cols[83],cols[84],cols[85],cols[86],cols[87],cols[88],cols[89],cols[90],cols[91],cols[92],cols[93],cols[94],cols[95],cols[96],cols[97],cols[98],cols[99],cols[100],cols[101],cols[102],cols[103],cols[104],cols[105],cols[106],cols[107],cols[108],cols[109],cols[110],cols[111],cols[112],cols[113],cols[114],cols[115],cols[116],cols[117],cols[118],cols[119],cols[120],cols[121],cols[122],cols[123],cols[124],cols[125],cols[126],cols[127],cols[128],cols[129],cols[130],cols[131],cols[132],cols[133],cols[134],cols[135],cols[136],cols[137],cols[138],cols[139],cols[140],cols[141],cols[142],cols[143],cols[144],cols[145],cols[146],cols[147],cols[148],cols[149],cols[150],cols[151],cols[152],cols[153],cols[154],cols[155],cols[156],cols[157]);
                    //CollectionColumns c = new CollectionColumns(new String[]{pf,pj,total});
                    if(count==0){
                        g.setSite(g.getSite());
                        g.setRef_ini(Utils.dateFormatPt(ini));
                        g.setRef_fim(Utils.dateFormatPt(fim));
                        g.setRef_seg(seg);
                        g.setRef_aop(areaOper);
                    }
                    else{
                        g.setSite(nullToString(g.getSite()));
                        g.setRef_ini(nullToString(""));
                        g.setRef_fim(nullToString(""));
                        g.setRef_seg(nullToString(""));
                        g.setRef_aop(nullToString(""));
                    }
                    
                    list.add(g);
                    count++;
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public String nullToString(String x){
        if(x==null){
            x="";
        }else if(x.isEmpty()){
            x="";
        }else if(x.length()==0){
            x="";
        }
        return x;
    }
    public List<Gic> selectGicChassi(String chassi, String areaOperacional){
        Connection conn = new DBase(true).getConnection();
        List<Gic> list = new ArrayList<Gic>();
       
        
        if(conn!=null){
            try {
                String sql = "SELECT * FROM GIC \n" +
                "WHERE C_CPFCNPJPROPRIETARIO = ? ORDER BY DATA_COMPLETA DESC limit 1";// AND AREA_OPERACIONAL IN ("+areaOperacional+")";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,  chassi);
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int numCol=156+1;
                    String[] cols = new String[numCol+1];
                    for(int i=1;i<numCol;i++){
                        cols[i]=rs.getString(i);
                    }
                    Gic g = new Gic(cols[1],cols[2],cols[3],cols[4],cols[5],cols[6],cols[7],cols[8],cols[9],cols[10],cols[11],cols[12],cols[13],cols[14],cols[15],cols[16],cols[17],cols[18],cols[19],cols[20],cols[21],cols[22],cols[23],cols[24],cols[25],cols[26],cols[27],cols[28],cols[29],cols[30],cols[31],cols[32],cols[33],cols[34],cols[35],cols[36],cols[37],cols[38],cols[39],cols[40],cols[41],cols[42],cols[43],cols[44],cols[45],cols[46],cols[47],cols[48],cols[49],cols[50],cols[51],cols[52],cols[53],cols[54],cols[55],cols[56],cols[57],cols[58],cols[59],cols[60],cols[61],cols[62],cols[63],cols[64],cols[65],cols[66],cols[67],cols[68],cols[69],cols[70],cols[71],cols[72],cols[73],cols[74],cols[75],cols[76],cols[77],cols[78],cols[79],cols[80],cols[81],cols[82],cols[83],cols[84],cols[85],cols[86],cols[87],cols[88],cols[89],cols[90],cols[91],cols[92],cols[93],cols[94],cols[95],cols[96],cols[97],cols[98],cols[99],cols[100],cols[101],cols[102],cols[103],cols[104],cols[105],cols[106],cols[107],cols[108],cols[109],cols[110],cols[111],cols[112],cols[113],cols[114],cols[115],cols[116],cols[117],cols[118],cols[119],cols[120],cols[121],cols[122],cols[123],cols[124],cols[125],cols[126],cols[127],cols[128],cols[129],cols[130],cols[131],cols[132],cols[133],cols[134],cols[135],cols[136],cols[137],cols[138],cols[139],cols[140],cols[141],cols[142],cols[143],cols[144],cols[145],cols[146],cols[147],cols[148],cols[149],cols[150],cols[151],cols[152],cols[153],cols[154],cols[155],cols[156],cols[157]);
                    //CollectionColumns c = new CollectionColumns(new String[]{pf,pj,total});
                    list.add(g);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<Gic> selectGicChassiBatch(String chassi, String areaOperacional){
        Connection conn = new DBase(true).getConnection();
        List<Gic> list = new ArrayList<Gic>();
       
        
        if(conn!=null){
            try {
                String sql = "SELECT * FROM GIC G, SEARCH_CHASSI C WHERE \n" +
                "G.CHASSI=C.CHASSI  \n" +
                "AND G.AREA_OPERACIONAL IN ("+areaOperacional+")";
                PreparedStatement ps = conn.prepareStatement(sql);
                //ps.setString(1,  "%" + chassi + "%");
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int numCol=156+1;
                    String[] cols = new String[numCol+1];
                    for(int i=1;i<numCol;i++){
                        cols[i]=rs.getString(i);
                    }
                    Gic g = new Gic(cols[1],cols[2],cols[3],cols[4],cols[5],cols[6],cols[7],cols[8],cols[9],cols[10],cols[11],cols[12],cols[13],cols[14],cols[15],cols[16],cols[17],cols[18],cols[19],cols[20],cols[21],cols[22],cols[23],cols[24],cols[25],cols[26],cols[27],cols[28],cols[29],cols[30],cols[31],cols[32],cols[33],cols[34],cols[35],cols[36],cols[37],cols[38],cols[39],cols[40],cols[41],cols[42],cols[43],cols[44],cols[45],cols[46],cols[47],cols[48],cols[49],cols[50],cols[51],cols[52],cols[53],cols[54],cols[55],cols[56],cols[57],cols[58],cols[59],cols[60],cols[61],cols[62],cols[63],cols[64],cols[65],cols[66],cols[67],cols[68],cols[69],cols[70],cols[71],cols[72],cols[73],cols[74],cols[75],cols[76],cols[77],cols[78],cols[79],cols[80],cols[81],cols[82],cols[83],cols[84],cols[85],cols[86],cols[87],cols[88],cols[89],cols[90],cols[91],cols[92],cols[93],cols[94],cols[95],cols[96],cols[97],cols[98],cols[99],cols[100],cols[101],cols[102],cols[103],cols[104],cols[105],cols[106],cols[107],cols[108],cols[109],cols[110],cols[111],cols[112],cols[113],cols[114],cols[115],cols[116],cols[117],cols[118],cols[119],cols[120],cols[121],cols[122],cols[123],cols[124],cols[125],cols[126],cols[127],cols[128],cols[129],cols[130],cols[131],cols[132],cols[133],cols[134],cols[135],cols[136],cols[137],cols[138],cols[139],cols[140],cols[141],cols[142],cols[143],cols[144],cols[145],cols[146],cols[147],cols[148],cols[149],cols[150],cols[151],cols[152],cols[153], cols[154],cols[155],cols[156],cols[157]);
                    //CollectionColumns c = new CollectionColumns(new String[]{pf,pj,total});
                    list.add(g);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(), "selectGicChassiBatch");
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public String dateUpdate(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<Gic> list = new ArrayList<Gic>();
        
        String dateUpdate = "";
        
        seg = segToGic(seg);
         
        if(conn!=null){
            try {
                String sql = 
                        //"SELECT DATA_COMPLETA FROM GIC WHERE AREA_OPERACIONAL IN("+areaOper+") AND SEGMENTO  IN (?) AND C_CPFCNPJPROPRIETARIO IS NOT NULL ORDER BY DATA_COMPLETA DESC LIMIT 1";
                        "SELECT DATA_COMPLETA FROM GIC WHERE  C_CPFCNPJPROPRIETARIO IS NOT NULL ORDER BY DATA_COMPLETA DESC LIMIT 1";
                        
                PreparedStatement ps = conn.prepareStatement(sql);
                //ps.setString(1, ini);
                //ps.setString(2, fim);
               // ps.setString(1, seg);
                
                ResultSet rs = ps.executeQuery();
                int count=0;
                while(rs.next()){
                    String date = rs.getString("DATA_COMPLETA");
                    // convert date
                    dateUpdate = Utils.dateFormatPt(date);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dateUpdate;
    }
     
     public List<CollectionColumns> selectTopClientesCaminhoes(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String c_nomeproprietario="";
        String vw = "";
        String mbenz = "";
        String ford="";
        String volvo="";
        String iveco="";
        String scania="";
        String daf="";
        String outros="";
        String total="";
        if(conn!=null){
            //if(seg.equalsIgnoreCase("3")){
            try {
                String sql = "-- ============================ TOP CLIENTES PARA CAMINHOES  ============================ \n" +
                        "\n" +
                        "Select\n" +
                        "  gic.C_NOMEPROPRIETARIO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VW' Then marca_fixo.MARCA_AGRUP Else Null End) As VW,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'M.BENZ' Then marca_fixo.MARCA_AGRUP Else Null End) As MBENZ,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'FORD' Then marca_fixo.MARCA_AGRUP Else Null End) As FORD,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VOLVO' Then marca_fixo.MARCA_AGRUP Else Null End) As VOLVO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'IVECO' Then marca_fixo.MARCA_AGRUP Else Null End) As IVECO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'SCANIA' Then marca_fixo.MARCA_AGRUP Else Null End) As SCANIA,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'DAF' Then marca_fixo.MARCA_AGRUP Else Null End) As DAF,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'OUTROS' Then marca_fixo.MARCA_AGRUP Else Null End) As OUTROS,\n" +
                        "  Count(marca_fixo.MARCA_AGRUP) As TOTAL\n" +
                        "From\n" +
                        "  gic Inner Join\n" +
                        "  marca_fixo On gic.MARCA_COMPLETA = marca_fixo.DESCRICAO\n" +
                        "  join segmento ON gic.SEG = segmento.SEGMENTO \n" +
                        "Where\n" +
                        "  gic.DATA_COMPLETA Between ? And ? AND \n" +
                        "   gic.AREA_OPERACIONAL IN("+areaOper+") \n"+
                        "AND\n" +
                        "  segmento.TIPO_VEIC = 'CAMINHOES'\n" +
                        "   AND GIC.SEG in ("+seg+") \n"+
                        "   AND gic.C_CPFCNPJPROPRIETARIO is not NULL \n"+
                        "Group By\n" +
                        "  gic.C_NOMEPROPRIETARIO\n" +
                        "Order By\n" +
                        "  TOTAL Desc\n" +
                        "Limit 10 ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    c_nomeproprietario=rs.getString("C_NOMEPROPRIETARIO");
                    vw= rs.getString("VW");
                    mbenz= rs.getString("MBENZ");
                    ford= rs.getString("FORD");
                    volvo= rs.getString("VOLVO");
                    iveco= rs.getString("IVECO");
                    scania= rs.getString("SCANIA");
                    daf = rs.getString("DAF");
                    outros= rs.getString("OUTROS");
                    
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{c_nomeproprietario,vw, mbenz, ford, volvo, iveco, scania, daf, outros, total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
           // }
        }
        return list;
    }
     public List<CollectionColumns> selectTopClientesTodos(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String c_nomeproprietario="";
        String vw = "";
        String mbenz = "";
        String ford="";
        String volvo="";
        String iveco="";
        String scania="";
        String daf="";
        String outros="";
        String total="";
        String fiat="";
        String citroen = "";
        String renault = "";
        if(conn!=null){
           // if(seg.equalsIgnoreCase("3")){
            try {
                String sql = "-- ============================ TOP CLIENTES PARA TODOS  ============================ \n" +
                        "\n" +
                        "Select\n" +
                        "  gic.C_NOMEPROPRIETARIO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VW' Then marca_fixo.MARCA_AGRUP Else Null End) As VW,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'M.BENZ' Then marca_fixo.MARCA_AGRUP Else Null End) As MBENZ,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'FORD' Then marca_fixo.MARCA_AGRUP Else Null End) As FORD,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VOLVO' Then marca_fixo.MARCA_AGRUP Else Null End) As VOLVO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'IVECO' Then marca_fixo.MARCA_AGRUP Else Null End) As IVECO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'SCANIA' Then marca_fixo.MARCA_AGRUP Else Null End) As SCANIA,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'DAF' Then marca_fixo.MARCA_AGRUP Else Null End) As DAF,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'FIAT' Then marca_fixo.MARCA_AGRUP Else Null End) As FIAT,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'CITROEN' Then marca_fixo.MARCA_AGRUP Else Null End) As CITROEN,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'RENAULT' Then marca_fixo.MARCA_AGRUP Else Null End) As RENAULT,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'OUTROS' Then marca_fixo.MARCA_AGRUP Else Null End) As OUTROS,\n" +
                        "  Count(marca_fixo.MARCA_AGRUP) As TOTAL\n" +
                        "From\n" +
                        "  gic Inner Join\n" +
                        "  marca_fixo On gic.MARCA_COMPLETA = marca_fixo.DESCRICAO\n" +
                        "  join segmento ON gic.SEG = segmento.SEGMENTO \n" +
                        "Where\n" +
                        "  gic.DATA_COMPLETA Between ? And ? AND \n" +
                        "   gic.AREA_OPERACIONAL IN("+areaOper+") \n"+
                      //  "AND\n" + "  segmento.TIPO_VEIC = 'CAMINHOES'\n" +
                          "AND\n" + "  gic.SEG IN ("+seg+ ") \n" +
                        "   AND gic.C_CPFCNPJPROPRIETARIO is not NULL \n"+
                        "Group By\n" +
                        "  gic.C_NOMEPROPRIETARIO\n" +
                        "Order By\n" +
                        "  TOTAL Desc\n" +
                        "Limit 10 ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    c_nomeproprietario=rs.getString("C_NOMEPROPRIETARIO");
                    vw= rs.getString("VW");
                    mbenz= rs.getString("MBENZ");
                    ford= rs.getString("FORD");
                    volvo= rs.getString("VOLVO");
                    iveco= rs.getString("IVECO");
                    scania= rs.getString("SCANIA");
                    daf = rs.getString("DAF");
                    outros= rs.getString("OUTROS");
                    fiat = rs.getString("FIAT");
                    citroen = rs.getString("CITROEN");
                    renault = rs.getString("RENAULT");
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{c_nomeproprietario,vw, mbenz, ford, volvo, iveco, scania, daf, fiat, citroen, renault, outros, total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
           // }
        }
        return list;
    }
     public List<CollectionColumns> selectTopClientesVans(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String c_nomeproprietario="";
        String vw = "";
        String mbenz = "";
        String ford="";
        String volvo="";
        String iveco="";
        String scania="";
        String outros="";
        String total="";
        
        String renault, peugeot, fiat,citroen = "";
        if(conn!=null){
           // if(seg.equalsIgnoreCase("2")){
            try {
                String sql = "-- ============================ TOP CLIENTES PARA VANS  ============================ \n" +
                        "\n" +
                        "Select\n" +
                        "  gic.C_NOMEPROPRIETARIO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'RENAULT' Then marca_fixo.MARCA_AGRUP Else Null End) As RENAULT,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'FIAT' Then marca_fixo.MARCA_AGRUP Else Null End) As FIAT,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'M.BENZ' Then marca_fixo.MARCA_AGRUP Else Null End) As MBENZ,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'IVECO' Then marca_fixo.MARCA_AGRUP Else Null End) As IVECO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'PEUGEOT' Then marca_fixo.MARCA_AGRUP Else Null End) As PEUGEOT,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'CITROEN' Then marca_fixo.MARCA_AGRUP Else Null End) As CITROEN,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'FORD' Then marca_fixo.MARCA_AGRUP Else Null End) As FORD,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'OUTROS' Then marca_fixo.MARCA_AGRUP Else Null End) As OUTROS,\n" +
                        "  Count(marca_fixo.MARCA_AGRUP) As TOTAL\n" +
                        "From\n" +
                        "  gic Inner Join\n" +
                        "  marca_fixo On gic.MARCA_COMPLETA = marca_fixo.DESCRICAO \n" +
                        "  join segmento ON gic.SEG = segmento.SEGMENTO \n" +
                        "Where\n" +
                        "  gic.DATA_COMPLETA Between ? And ? AND \n" +
                        "   gic.AREA_OPERACIONAL IN("+areaOper+") \n"+
                        "AND\n" +
                        "  segmento.TIPO_VEIC = 'VANS' \n" +
                        "   AND GIC.SEG in ("+seg+") \n"+
                        "   AND gic.C_CPFCNPJPROPRIETARIO is not NULL \n"+
                        "Group By\n" +
                        "  gic.C_NOMEPROPRIETARIO\n" +
                        "Order By\n" +
                        "  TOTAL Desc \n"+"Limit 10 ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    c_nomeproprietario=rs.getString("C_NOMEPROPRIETARIO");
                    renault= rs.getString("RENAULT");
                    fiat= rs.getString("FIAT");
                    mbenz= rs.getString("MBENZ");
                    iveco= rs.getString("IVECO");
                    peugeot= rs.getString("PEUGEOT");
                    citroen= rs.getString("CITROEN");
                    outros= rs.getString("OUTROS");
                    ford = rs.getString("FORD");
                    
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{c_nomeproprietario,renault, fiat, mbenz, iveco, peugeot, citroen, ford, outros, total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
           // }
        }
        return list;
    }
     
     public List<CollectionColumns> selectTopClientesOnibus(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String c_nomeproprietario="";
        String vw = "";
        String mbenz = "";
        String agrale="";
        String volvo="";
        String iveco="";
        String scania="";
        String outros="";
        String total="";
        String inter="";
        if(conn!=null){
            //if(seg.equalsIgnoreCase("4")){
            try {
                String sql = 
                        "-- ============================ TOP CLIENTES PARA ONIBUS  ============================ \n" +
                        "Select\n" +
                        "  gic.C_NOMEPROPRIETARIO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'M.BENZ' Then marca_fixo.MARCA_AGRUP Else Null End) As MBENZ,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VW' Then marca_fixo.MARCA_AGRUP Else Null End) As VW,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'AGRAL' Then marca_fixo.MARCA_AGRUP Else Null End) As AGRALE,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'IVECO' Then marca_fixo.MARCA_AGRUP Else Null End) As IVECO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'VOLVO' Then marca_fixo.MARCA_AGRUP Else Null End) As VOLVO,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'SCANIA' Then marca_fixo.MARCA_AGRUP Else Null End) As SCANIA,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'INTER' Then marca_fixo.MARCA_AGRUP Else Null End) As INTER,\n" +
                        "  Count(Case When marca_fixo.MARCA_AGRUP = 'OUTROS' Then marca_fixo.MARCA_AGRUP Else Null End) As OUTROS,\n" +
                        "  Count(marca_fixo.MARCA_AGRUP) As TOTAL\n" +
                        "From\n" +
                        "  gic Inner Join\n" +
                        "  marca_fixo On gic.MARCA_COMPLETA = marca_fixo.DESCRICAO \n" +
                        "  join segmento ON gic.SEG = segmento.SEGMENTO \n" +
                        "Where\n" +
                        "  gic.DATA_COMPLETA Between ? And ? AND \n" +
                        "   gic.AREA_OPERACIONAL IN("+areaOper+") \n"+
                        "AND\n" +
                        "  segmento.TIPO_VEIC = 'ONIBUS'\n" +
                        "   AND GIC.SEG in ("+seg+") \n"+
                        "   AND gic.C_CPFCNPJPROPRIETARIO is not NULL \n"+
                        "Group By\n" +
                        "  gic.C_NOMEPROPRIETARIO\n" +
                        "Order By\n" +
                        "  TOTAL Desc\n" +
                        "Limit 10";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    c_nomeproprietario=rs.getString("C_NOMEPROPRIETARIO");
                    mbenz= rs.getString("MBENZ");
                    vw= rs.getString("VW");
                    agrale= rs.getString("AGRALE");
                    iveco= rs.getString("IVECO");
                    volvo= rs.getString("VOLVO");
                    scania= rs.getString("SCANIA");
                    outros= rs.getString("OUTROS");
                    inter = rs.getString("INTER");
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{c_nomeproprietario,mbenz, vw, agrale, iveco, volvo, scania, inter, outros, total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
           // }
        }
        return list;
    }
     
     public List<CollectionColumns> searchCnpjByYear(String cnpj){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String total="";
        String s2014="";
        String s2015="";
        String s2016="";
        String t2014,t2015,t2016=t2014=t2015="";
        String marca="";
        
        if(conn!=null){
            
            try {
                String sql = 
                        "SELECT MARCA, s2014, COALESCE(ROUND(s2014/t2014*100,2),0) AS t2014, s2015, COALESCE(ROUND(s2015/t2015*100,2),0) AS t2015, s2016,COALESCE(ROUND(s2016/t2016*100,2),0) AS t2016, TOTAL FROM (\n" +
                        "SELECT 	MARCA_COMPLETA AS MARCA, \n" +
                        "		COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2014' OR NULL),0) AS 's2014',\n" +
                        "		(SELECT COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2014' OR NULL),0) AS 't2014' FROM GIC WHERE C_CPFCNPJPROPRIETARIO = ?) AS 'T2014',\n" +
                        "		COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2015' OR NULL),0) AS 's2015',\n" +
                        "		(SELECT COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2015' OR NULL),0) AS 't2015' FROM GIC WHERE C_CPFCNPJPROPRIETARIO = ?) AS 'T2015',\n" +
                        "		COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2016' OR NULL),0) AS 's2016',\n" +
                        "		(SELECT COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2016' OR NULL),0) AS 't2016' FROM GIC WHERE C_CPFCNPJPROPRIETARIO = ?) AS 'T2016',\n" +
                        "		COALESCE(COUNT(YEAR(DATA_COMPLETA) = '2014' OR YEAR(DATA_COMPLETA) = '2015' OR YEAR(DATA_COMPLETA) = '2016' OR NULL),0) AS 'TOTAL'\n" +
                        "FROM GIC \n" +
                        "WHERE C_CPFCNPJPROPRIETARIO = ? \n" +
                        "GROUP BY MARCA_COMPLETA) AS TBL";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, cnpj);
                ps.setString(2, cnpj);
                ps.setString(3, cnpj);
                ps.setString(4, cnpj);
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA");
                    s2014= rs.getString("S2014");
                    s2015= rs.getString("S2015");
                    s2016= rs.getString("S2016");
                    t2014 = rs.getString("t2014");
                    t2015 = rs.getString("t2015");
                    t2016 = rs.getString("t2016");
                    total= rs.getString("TOTAL");
                    
                    CollectionColumns c = new CollectionColumns(new String[]{marca, s2014,t2014,s2015,t2015,s2016,t2016,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
     
     public List<CollectionColumns> searchCnpjVinculado(String cnpj){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String nome="";
        String cpfcnpj="";
        String municipio="";
        String estado="";
        
        //cnpj = ;
        
        if(conn!=null){
            
            try {
                String sql = 
                        "SELECT C_NOMEPROPRIETARIO,C_CPFCNPJPROPRIETARIO, MUNICIPIO, ESTADO \n" +
                        "FROM GIC \n" +
                        "WHERE C_CPFCNPJPROPRIETARIO LIKE ? \n" +
                        "AND C_CPFCNPJPROPRIETARIO <> ? \n" +
                        "GROUP BY C_CPFCNPJPROPRIETARIO \n" +
                        "LIMIT 1000";
                PreparedStatement ps = conn.prepareStatement(sql);
                String vinculado = cnpj.substring(0, 8);
                ps.setString(1, vinculado+"%");
                ps.setString(2, cnpj);
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    nome=rs.getString("C_NOMEPROPRIETARIO");
                    cpfcnpj= rs.getString("C_CPFCNPJPROPRIETARIO");
                    municipio= rs.getString("MUNICIPIO");
                    estado= rs.getString("ESTADO");
                    
                    
                    CollectionColumns c = new CollectionColumns(new String[]{nome, cpfcnpj,municipio, estado});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
     
     public List<CollectionColumns> searchCnpjByLastYear(String cnpj){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String total="";
        String jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez = jan=fev=mar=abr=mai=jun=jul=ago=set=out=nov="";
       
        String marca="";
        
        if(conn!=null){
            
            try {
                String sql = 
                        "SELECT 	MARCA_COMPLETA AS MARCA, \n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '1' OR NULL),0) AS 'JAN',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '2' OR NULL),0) AS 'FEV',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '3' OR NULL),0) AS 'MAR',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '4' OR NULL),0) AS 'ABR',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '5' OR NULL),0) AS 'MAI',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '6' OR NULL),0) AS 'JUN',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '7' OR NULL),0) AS 'JUL',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '8' OR NULL),0) AS 'AGO',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '9' OR NULL),0) AS 'SET',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '10' OR NULL),0) AS 'OUT',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '11' OR NULL),0) AS 'NOV',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '12' OR NULL),0) AS 'DEZ',\n" +
                        "		COALESCE(COUNT(MONTH(DATA_COMPLETA) = '1' OR MONTH(DATA_COMPLETA) = '2' OR MONTH(DATA_COMPLETA) = '3' OR MONTH(DATA_COMPLETA) = '4' OR MONTH(DATA_COMPLETA) = '5' OR MONTH(DATA_COMPLETA) = '6' OR MONTH(DATA_COMPLETA) = '7' OR MONTH(DATA_COMPLETA) = '8' OR MONTH(DATA_COMPLETA) = '9' OR MONTH(DATA_COMPLETA) = '10' OR MONTH(DATA_COMPLETA) = '11' OR MONTH(DATA_COMPLETA) = '12' OR NULL),0) AS 'TOTAL'\n" +
                        "FROM GIC \n" +
                        "WHERE C_CPFCNPJPROPRIETARIO = ?  \n" +
                        "AND DATA_COMPLETA > DATE_SUB(now() , INTERVAL 12 MONTH)\n" +
                        "GROUP BY  MARCA_COMPLETA";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, cnpj);
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA");
                    jan= rs.getString("JAN");
                    fev= rs.getString("FEV");
                    mar= rs.getString("MAR");
                    abr= rs.getString("ABR");
                    mai= rs.getString("MAI");
                    jun= rs.getString("JUN");
                    jul= rs.getString("JUL");
                    ago= rs.getString("AGO");
                    set= rs.getString("SET");
                    out= rs.getString("OUT");
                    nov= rs.getString("NOV");
                    dez= rs.getString("DEZ");
                    total= rs.getString("TOTAL");
                    
                    CollectionColumns c = new CollectionColumns(new String[]{marca,jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
     
     public List<CollectionColumns> searchNomeProprietario(String keyword){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String nome="";
        String cpfcnpj="";
        String municipio="";
        String estado="";
        
        //cnpj = ;
        
        if(conn!=null){
            
            try {
                String sql = 
                        "SELECT C_NOMEPROPRIETARIO,C_CPFCNPJPROPRIETARIO, MUNICIPIO, ESTADO \n" +
                        "FROM GIC \n" +
                        "WHERE C_NOMEPROPRIETARIO LIKE ? \n" +
                        "GROUP BY C_NOMEPROPRIETARIO \n"+
                        "LIMIT 1000";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, "%"+keyword+"%");
                
                
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    nome=rs.getString("C_NOMEPROPRIETARIO");
                    cpfcnpj= rs.getString("C_CPFCNPJPROPRIETARIO");
                    municipio= rs.getString("MUNICIPIO");
                    estado= rs.getString("ESTADO");
                    
                    
                    CollectionColumns c = new CollectionColumns(new String[]{nome, cpfcnpj,municipio, estado});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
     
     public List<CollectionColumns> selectEnriquecerArquivos(String ini, String fim, String seg){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String data_completa="";
        String chassi = "";
        String placa = "";
        String sql="SELECT DATA_COMPLETA,CHASSI,PLACA FROM GIC\n" +
                        "WHERE  C_CPFCNPJPROPRIETARIO IS NULL AND\n" +
                        "DATA_COMPLETA BETWEEN ? AND ?  AND \n" +
                        "SEGMENTO=? LIMIT 100000";
        
        if(seg.equalsIgnoreCase("todos")){
           sql="SELECT DATA_COMPLETA,CHASSI,PLACA FROM GIC\n" +
                        "WHERE  C_CPFCNPJPROPRIETARIO IS NULL AND\n" +
                        "DATA_COMPLETA BETWEEN ? AND ?  AND \n" +
                        "SEGMENTO IN("+segToGic(seg)+") LIMIT 100000";
        }else{
             seg = segToGic(seg);
        }
        br.com.itfox.utils.Logger.getLogger("SQL "+sql+" | seg:"+seg);
        /*
        switch(seg){
            case "2":
                    seg="LARGE VANS";
                    break;
            case "3":
                    seg="CAMINHOES";
                    break;
            case "4":
                    seg="ONIBUS";
                    break;
            case "5":
                    seg="MID SIZE VAN";
                    break;    
            default:
                    sql="SELECT DATA_COMPLETA,CHASSI,PLACA FROM GIC\n" +
                        "WHERE  C_CPFCNPJPROPRIETARIO IS NULL AND\n" +
                        "DATA_COMPLETA BETWEEN ? AND ?  AND \n" +
                        "SEGMENTO IN('VANS','CAMINHOES','ONIBUS') LIMIT 100000";
                    seg="1";
                    break;
        }*/
       
        if(conn!=null){
            
            try {
                
               
                PreparedStatement ps = conn.prepareStatement(sql);
                
                if(seg.equalsIgnoreCase("todos")){
                   ps.setString(1, ini);
                   ps.setString(2, fim); 
                }else{
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                }
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    data_completa=rs.getString("DATA_COMPLETA");
                    chassi= rs.getString("CHASSI");
                    placa= rs.getString("PLACA");
                    
                    CollectionColumns c = new CollectionColumns(new String[]{data_completa, chassi, placa});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return list;
    }
   
     public String segToGic(String seg){
         switch(seg){
            case "2":
                    seg="LARGE VANS";
                    break;
            case "3":
                    seg="CAMINHOES";
                    break;
            case "4":
                    seg="ONIBUS";
                    break;
            case "5":
                    seg="MIDE SIZE VAN";
                    break;
            default:
                    seg="'LARGE VANS','CAMINHOES','ONIBUS','MIDE SIZE VAN'";
                break;
        }
         return seg;
     }
     
     public List<CollectionColumns> clickToView(String ini, String fim, String seg, String areaOper, String nomeProprietario){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String chassi, data, marca, modelo, segmento, subsegmento, placa, municipio, dealer_aop, tipo_veic, estado;
        chassi=data=marca=modelo=segmento=subsegmento=placa=municipio=dealer_aop=tipo_veic=estado="";
        if(conn!=null){
            try {
                String sql = "SELECT CHASSI, DATA_COMPLETA, MARCA_COMPLETA, MODELO, SEGMENTO, SUBSEGMENTO, PLACA, MUNICIPIO, ESTADO, DEALER_AOP, TIPO_VEIC FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_NOMEPROPRIETARIO = ? \n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "ORDER BY G.MARCA_COMPLETA, G.MODELO ";
                            
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, nomeProprietario);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    chassi=rs.getString("CHASSI");
                    data=rs.getString("DATA_COMPLETA");
                    marca=rs.getString("MARCA_COMPLETA");
                    modelo=rs.getString("MODELO");
                    segmento=rs.getString("SEGMENTO");
                    subsegmento=rs.getString("SUBSEGMENTO");
                    placa=rs.getString("PLACA");
                    municipio=rs.getString("MUNICIPIO");
                    estado=rs.getString("ESTADO");
                    dealer_aop=rs.getString("DEALER_AOP");
                    tipo_veic=rs.getString("TIPO_VEIC");
                    
                    CollectionColumns c = new CollectionColumns(new String[]{chassi, data, marca, modelo, segmento, subsegmento, placa, municipio, estado, dealer_aop,tipo_veic });
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }  
}
