package br.com.itfox.beans;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    //package mail;

import br.com.itfox.business.BusinessDelegate;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeUtility;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

    /**
     *
     * @author BelchiorPalma http://commons.apache.org/proper/commons-email/userguide.html
     * https://support.google.com/mail/answer/78775?hl=pt-BR
     */
   

public class SendHtmlFormatedEmail {
     private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
     private String htmlHead = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                                "<head>\n" +
                                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n" +
                                "<title>Inscrição no Congresso</title>\n" +
                                "</head>\n" +
                                "\n" +
                                "<body>";
     private String bodyProfissional = new BusinessDelegate().getMensagem(new BigDecimal(62)).getMensagem();
             
                                    
     
     private String bodyServidor = new BusinessDelegate().getMensagem(new BigDecimal(63)).getMensagem();
     
     private String bodyEstudante = new BusinessDelegate().getMensagem(new BigDecimal(65)).getMensagem();
     
     private String bodyMagistrado = new BusinessDelegate().getMensagem(new BigDecimal(64)).getMensagem();
     public static void main(String[] args){
         try {
             //new SendHtmlFormatedEmail().sendingHtml();
             String assunto = "";
                    Mensagem msg = new Mensagem();
                     // localizando a mensagem
                    msg = new BusinessDelegate().getMensagem(new BigDecimal(61));
                    assunto = msg.getAssunto();
            new SendHtmlFormatedEmail().sendingHtml("belchiorpalma@gmail.com", "Belchior", assunto, 2) ;
         } catch (Exception ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
         }
     }   
        
     public  void sendingHtml(){
         try {
             // Create the email message
             HtmlEmail email = new HtmlEmail();
             email.setHostName("mail.congressotrt15.com.br");
             email.setSmtpPort(587);
             email.setAuthenticator(new DefaultAuthenticator("congresso@congressotrt15.com.br", "admtrt15xx"));
             email.setSSLOnConnect(false);
             //email.setTLS(true);
             email.setFrom("congresso@congressotrt15.com.br");
             email.setSubject("TestMail");
             email.addTo("belchiorpalma@gmail.com", "Belchior Palma");
             //email.setFrom("belchiorpalma@me.com", "Me");
             email.setSubject("Test email with inline image");
             email.setSubject(MimeUtility.encodeText("Test email with inline image", "UTF-8", "B"));
             
             // embed the image and get the content id
             URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
             String cid = email.embed(url, "Apache logo");
             
             // set the html message
             email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
             
             // set the alternative message
             email.setTextMsg("Your email client does not support HTML messages");
             
             // send the email
             email.send();
         } catch (EmailException ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
         } catch (MalformedURLException ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
         }
           
     } 
     public  void sendingHtml(String destinatario, String nome, String assunto, URL linkBoleto ) {
         try {
             
             // Create the email message
             HtmlEmail email = new HtmlEmail();
             email.setHostName("mail.congressotrt15.com.br");
             email.setSmtpPort(587);
             email.setAuthenticator(new DefaultAuthenticator("congresso@congressotrt15.com.br", "admtrt15xx"));
             email.setSSLOnConnect(false);
             //email.setTLS(true);
             email.setFrom("congresso@congressotrt15.com.br");
             email.setSubject("TestMail");
             email.addTo(destinatario, nome);
             //email.setFrom("belchiorpalma@me.com", "Me");
             email.setSubject(assunto);
             email.setSubject(MimeUtility.encodeText(assunto, "UTF-8", "B"));
             
             // embed the image and get the content id
             URL url = linkBoleto;//new URL(linkBoleto);
             String cid = email.embed(url, new BusinessDelegate().getMensagem(new BigDecimal(61)).getAssunto());
             
             // localizando a mensagem
             //Mensagem msg = new Mensagem();
             //msg = new BusinessDelegate().getMensagem(mensagemId);
             
             // set the html message
             //email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
             String body = "";
             body+=this.htmlHead;
             body+=(this.bodyProfissional);
             body+=("Fa&ccedil;a Download do Boleto para pagamento: - <a href="+url+" target=\"_blank\">Clique Aqui para baixar o Boleto.</a>");
             body+="<img src=\"cid:"+cid+"\">";
             body+=("</body></html>");
             //email.setContent(body,CONTENT_TYPE);
             email.setHtmlMsg(body);
             //email.setHeaders(null);
             //email.setHtmlMsg(body);
             // set the alternative message
             email.setTextMsg("Your email client does not support HTML messages");
             
             // send the email
             email.send();
         } catch (EmailException ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
            
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(SendHtmlFormatedEmail.class.getName()).log(Level.SEVERE, null, ex);
         } 
     }
         
         /**
     *
     * @param destinatario
     * @param nome
     * @param assunto
     * @throws IOException
     */
     
    public  void sendingHtml(String destinatario, String nome, String assunto, int tipoId) throws IOException  {
         try {
             
             // Create the email message
             HtmlEmail email = new HtmlEmail();
             email.setHostName("mail.congressotrt15.com.br");
             email.setSmtpPort(587);
             email.setAuthenticator(new DefaultAuthenticator("congresso@congressotrt15.com.br", "admtrt15xx"));
             email.setSSLOnConnect(false);
             //email.setTLS(true);
             email.setFrom("congresso@congressotrt15.com.br");
             //email.setSubject("TestMail");
             email.addTo(destinatario, nome);
             //email.setFrom("belchiorpalma@me.com", "Me");
             email.setSubject(assunto);
             email.setSubject(MimeUtility.encodeText(assunto, "UTF-8", "B"));
             
             
             // embed the image and get the content id
            // URL url = linkBoleto;//new URL(linkBoleto);
             //String cid = email.embed(url, "Congresso TRT 15");
             
             // set the html message
             //email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
             String body = "";
             body+=this.htmlHead;
             if(tipoId==1){
                 body+=(this.bodyProfissional);
             }
             else if(tipoId==2){
                 body+=(this.bodyServidor);
             }else if(tipoId==3){
                 body+=(this.bodyMagistrado);
             }else if(tipoId==4){
                 body+=(this.bodyEstudante);
             }else{
                 body+="Congresso TRT";
             }
             
            // body+=("Faça Download do Boleto para pagamento: - <a href="+url+" target=\"_blank\">Clique Aqui para baixar o Boleto.</a>");
             //body+="<img src=\"cid:"+cid+"\">";
             body+=("</body></html>");
             //email.setContent(body,CONTENT_TYPE);
             email.setHtmlMsg(body);
             //email.setHeaders(CONTENT_TYPE);
             //email.setHtmlMsg(body);
             // set the alternative message
             email.setTextMsg("Your email client does not support HTML messages");
             
             // send the email
             email.send();
         } catch (EmailException ex) {
            // utils.Logger.getLogger("Erro ao enviar Email. "+tipoId+" - "+ex.toString(),tipoId);
            // utils.Logger.getLoggerPessoaFisica("Erro ao enviar email - sending html:"+ex.getMessage());
         } 
           
     } 

 }

   

