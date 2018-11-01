<%
   
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
        response.sendRedirect("login.jsp");
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%} else {
        //int memberId=0;
        //String userId = "";
        //userId = (String) session.getAttribute("userid").toString();
        //memberId = Integer.parseInt(userId);
        
    }
%>
