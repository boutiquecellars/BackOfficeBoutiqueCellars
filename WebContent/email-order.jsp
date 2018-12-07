<%-- 
    Document   : testMail
    Created on : 21/10/2016, 07:20:53
    Author     : belchiorpalma
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"                                                                        
    pageEncoding="ISO-8859-1"%>                                                                                                             
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">                                      
<%@page import="java.util.Properties"%>                                                                                                     
<%@page import="javax.mail.Session"%>                                                                                                       
<%@page import="javax.mail.Authenticator"%>                                                                                                 
<%@page import="javax.mail.PasswordAuthentication"%>                                                                                        
<%@page import="javax.mail.Message"%>                                                                                                       
<%@page import="javax.mail.internet.MimeMessage"%>                                                                                          
<%@page import="javax.mail.internet.InternetAddress"%>                                                                                      
<%@page import="javax.mail.Transport"%>          

<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<html>                                                                                                                                      
<head>                                                                                                                                      
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">                                                                    
<title>Email Order</title>                                                                                                                   
</head>                                                                                                                                     
<body>                                                                                                                                      
<%                                                                                                                                          
String smtpServer = null;                                                                                                                   
String smtpPort = null;                                                                                                                     
final String authAddress = "belchiorpalma@gmail.com";//request.getParameter("auth_add");                                                                                
final String authPassword = "xp2002b5";//request.getParameter("auth_pass");                                                                              
String subject = null;                                                                                                                      
String email = null;                                                                                                                        
String message = null;                                                                                                                      
String send = request.getParameter("send");                                                                                                 
String siteName=request.getServerName();                                                                                                    
siteName=siteName.replaceAll("www.","");                                                                                                    

if(send!=null){
        smtpServer = request.getParameter("smtp_server");
        smtpPort = request.getParameter("smtp_port");    
        subject = request.getParameter("subject");       
        email = request.getParameter("email");           
        message = request.getParameter("message");  
        
        if(smtpServer==null || smtpServer.equalsIgnoreCase("")){
            smtpServer="smtp.gmail.com";
            smtpPort = "587";
            subject="Boutique Cellars - Order Confirmed! =)";
            
        }
        try{                                             
            Properties props = new Properties();     
            props.put("mail.smtp.host", smtpServer); 
            props.put("mail.smtp.port", smtpPort);   
            props.put("mail.smtp.auth", "true");  
            //props.put("mail.smtps.socketFactory.fallback", "false");
            //props.put("mail.smtps.socketFactory.class","utils.DummySSLSocketFactory");
            props.put("mail.smtps.quitwait", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
                                                          
            // create some properties and get the default Session
            Session sessionMail = Session.getInstance(props, new Authenticator() {
                 public PasswordAuthentication getPasswordAuthentication() {      
                         return new PasswordAuthentication(authAddress, authPassword);
                 }                                                                    
                });                                                                   
                                                                                      
            //sessionMail.setDebug(true);

            // create a message
            MimeMessage msg = new MimeMessage(sessionMail);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(authAddress);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            
            String address = "belchiorpalma@me.com";
            InternetAddress[] iAdressArray = InternetAddress.parse(address);
            msg.setRecipients(Message.RecipientType.CC, iAdressArray);
           // String emailTemplateJSP = "/jsp/propertyMgmt/propertylandingpageEmail.jsp"; 
           // RequestDispatcher rd = servlet.getServletContext().getRequestDispatcher(emailTemplateJSP); 
           // rd.include(request, response); 


            // Optional : You can also set your custom headers in the Email if you Want
            msg.addHeader("site", siteName);

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport.send(msg);
           // String redirectURL = "https://boutiquecellars.com/email_marketing_sended.jsp";
           // response.sendRedirect(redirectURL);
        }catch(Exception e){
                e.printStackTrace(response.getWriter());
        }
}
%>
OK
</body>
</html> 
