<%-- 
    Document   : incNavSideMenu
    Created on : 06/12/2015, 00:00:23
    Author     : belchiorpalma
--%>
<%
    String navPage = request.getParameter("page");
    if(navPage==null || navPage.isEmpty()){
        navPage="";
    }
%>
<nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                <!--
                    <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                             </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="#">Logout</a></li>
                            </ul>
                    </div>
                    -->
                    <div class="logo-element">
                        
                    </div>
                </li>
                <li class="<%if(navPage.equalsIgnoreCase("index")==true){out.print("active");}%>">
                    <a href="index.jsp"><i class="fa fa-bar-chart"></i> <span class="nav-label">Relatórios</span></a>
                </li>
                <li class="<%if(navPage.equalsIgnoreCase("boletim")==true){out.print("active");}%>">
                    <a href="boletim.jsp" id="boletim"><i class="fa fa-line-chart"></i> <span class="nav-label">Análises Adicionais</span></a>
                </li>
                <!--
                <li>
                    <a href="importar.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Importar Arquivos</span></a>
                </li>
                <li>
                    <a href="enriquecer.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Gerar Arquivos Para Enriquecer</span></a>
                </li>
                -->
                <li>
                    <a href="search-cnpj.jsp"><i class="fa fa-search"></i> <span class="nav-label">CNPJ Específico</span></a>
                </li>
                <li>
                    <a href="logout.jsp"><i class="fa fa-sign-out"></i> <span class="nav-label">Logout</span></a>
                </li>
                
            </ul>

        </div>
    </nav>
