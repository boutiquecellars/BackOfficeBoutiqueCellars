<%-- 
    Document   : incNavSide
    Created on : 07/12/2015, 00:56:38
    Author     : belchiorpalma
--%>
<%@page import="br.com.itfox.beans.Member"%>
<%
if(session.getAttribute("member")!=null){
    Member m = (Member) session.getAttribute("member");
    if(m.getPermission()!=null && !m.getPermission().isEmpty() && m.getPermission().equalsIgnoreCase("admin")){
        %>
        <jsp:include page="incNavSideMenuAdmin.jsp">
            <jsp:param name="page" value="${param.page}" />
        </jsp:include>
        <%
    }else{
        %>
        <jsp:include page="incNavSideMenu.jsp" >
            <jsp:param name="page" value="${param.page}" />
        </jsp:include>
        <%
    }
}
%>