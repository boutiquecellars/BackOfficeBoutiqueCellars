<%-- 
    Document   : logout
    Created on : 05/12/2015, 20:37:20
    Author     : belchiorpalma
--%>
<%
session.setAttribute("userid", null);
session.setAttribute("member", null);
session.setAttribute("username", null);
session.invalidate();
response.sendRedirect("index.jsp");
//request.getRequestDispatcher("index.jsp").forward(request, response);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
