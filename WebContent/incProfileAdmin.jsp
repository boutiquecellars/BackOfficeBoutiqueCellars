<%-- 
    Document   : incProfile
    Created on : 07/12/2015, 01:20:10
    Author     : belchiorpalma
--%>
<%@page import="br.com.itfox.beans.Member"%>
<%
if(session.getAttribute("member")!=null){
    Member m = (Member) session.getAttribute("member");
    if(m.getPermission()!=null && !m.getPermission().isEmpty() && m.getPermission().equalsIgnoreCase("admin")){
        // não faz nada
       // response.sendRedirect("enriquecer.jsp");
    }else{
        // não é admin
        response.sendRedirect("login.jsp");
        
        %>
        You are not autorized in<br/>
        <a href="login.jsp">Please Login</a>
        <%
    }
}else{
    response.sendRedirect("login.jsp");
    %>
    You are not autorized in<br/>
        <a href="login.jsp">Please Login</a>
        <%
}
%>
