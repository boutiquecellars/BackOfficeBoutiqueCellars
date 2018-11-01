<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="br.com.itfox.beans.Member"%>
<%@page import="br.com.itfox.utils.Utils"%>
<%@page import="br.com.itfox.beans.CollectionColumns"%>
<%@page import="br.com.itfox.beans.MemberAreaOper"%>
<%@page import="java.util.List"%>
<%@page import="br.com.itfox.business.BusinessDelegate"%>
<%@page import="br.com.itfox.config.Preferences"%>
<jsp:include page="incLogin.jsp" />
<%
int memberId=0;
Member m = new Member();
if(session.getAttribute("userid")!=null){
    memberId = (int) session.getAttribute("userid");
    m = (Member) session.getAttribute("member");
}else{
    // New location to be redirected
%>
<jsp:forward page="login.jsp" /> 
<%
   String site = new String("login.jsp");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
}
if(memberId==0){
    response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
    <title><%=Preferences.title%></title>

    <link href="css/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" media="all">
    
     <link href="css/plugins/chartist/chartist.min.css" rel="stylesheet" media="all">

    <link href="css/animate.css" rel="stylesheet" media="all">
    <link href="css/style.css" rel="stylesheet" media="all">
    <!-- date picker -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" media="all">
    <!-- responsive lightbox & dialog script -->
    <link rel="stylesheet" href="css/plugins/magnific-popup/magnific-popup.css" media="all">
    <!-- responsive tables -->
    
    <link rel="stylesheet" href="js/plugins/jquery-responsive-tables-master/jquery-responsiveTables.css" />
    
    <link rel="stylesheet" href="js/plugins/jquery-responsive-tables-master/itfoxResponsiveTable.css" />
    
    <!-- itfox css -->
    <link rel="stylesheet" href="css/itfoxBoletim.css" />
    <link rel="stylesheet" href="css/itfoxFilters.css" />
    <style >
        /*** media screen ***/
        @media screen and (min-width: 1025px) and (max-width: 1260px) {
            .baixar{
                display:none;
            }
        }
        /*** <-//media screen ***/
        /*** media screen ***/
        @media screen and (min-width: 810px) and (max-width: 1024px) {
            .filter ul li input{
                height: 30px;
                width: 64px;
                font-size: 10px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
            .form-control{
                width: 74px;
                font-size: 10px;
                padding: 6px 12px;
            }
            .baixar{
                display:none;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
        }
        /*** <-//media screen ***/
        /*** media screen ***/
        @media screen and (min-width: 768px) and (max-width: 809px) {
            h1{
                font-size:1.7em;
                margin: 0 0 0 -2em;
            }
            .filter ul li input{
                height: 30px;
                width: 64px;
                font-size: 10px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
            .form-control{
                width: 74px;
                font-size: 10px;
                padding: 6px 12px;
            }
            .baixar{
                display:none;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
             /**** Tabela ***/
            .table-responsive {
                font-size:9px !important;
            }
            .txt-left{
                 display: block;
                overflow: show;
                text-align: left;
                width: 100%;
            }
            .table > thead > tr > th{
                font-size: 10px;
            }
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
                padding:2px;
            }
        }
        /*** <-//media screen ***/
        /*** media screen ***/
        @media screen and (min-width: 569px) and (max-width: 767px) {
            .filter ul li input{
                height: 30px;
                width: 64px;
                font-size: 10px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
            .form-control{
                width: 74px;
                font-size: 10px;
                padding: 6px 12px;
            }
            .baixar{
                display:none;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
             /**** Tabela ***/
            .table-responsive {
                font-size:8px !important;
            }
            .txt-left{
                 display: block;
                overflow: hidden;
                text-align: left;
                width: 130px;
            }
            .table > thead > tr > th{
                font-size: 8px;
            }
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
                padding:2px;
            }
            h1{
                font-size:1.7em;
                margin: 0 0 0 -2em;
            }
            li.fieldset>fieldset:nth-of-type(5)>legend{
                text-indent:-9999px;
                line-height: 0; /* Collapse the original line */
            }
            li.fieldset>fieldset:nth-of-type(5)>legend:after{
                content:"Área Oper.";
                text-indent: 0;
                display: block;
                line-height: initial; /* New content takes up original line height */
            }
        }
        @media screen and (min-width: 500px) and (max-width: 568px) {
            .filter ul li input{
                height: 30px;
                width: 50px;
                font-size: 8px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
            .form-control{
                width: 74px;
                font-size: 10px;
                padding: 6px 12px;
            }
            .baixar{
                display:none;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
             /**** Tabela ***/
            .table-responsive {
                font-size:7px !important;
            }
            .txt-left{
                 display: block;
                overflow: hidden;
                text-align: left;
                width: 130px;
            }
            .table > thead > tr > th{
                font-size: 8px;
            }
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
                padding:2px;
            }
            h1{
                font-size:1.7em;
                margin: 0 0 0 -2em;
            }
            li.fieldset>fieldset:nth-of-type(5)>legend, li.fieldset>fieldset:nth-of-type(3)>legend{
                text-indent:-9999px;
                line-height: 0; /* Collapse the original line */
            }
            li.fieldset>fieldset:nth-of-type(5)>legend:after{
                content:"Área";
                text-indent: 0;
                display: block;
                line-height: initial; /* New content takes up original line height */
            }
            li.fieldset>fieldset:nth-of-type(3)>legend:after{
                content:"Seg.";
                text-indent: 0;
                display: block;
                line-height: initial; /* New content takes up original line height */
            }
            
            /*li.fieldset>fieldset:nth-of-type(5)>legend{display:none}*/
        }
        /*** <-//media screen ***/
         /*** media screen ***/
        @media screen and (min-width: 380px) and (max-width: 499px) {
            /*
            .filter ul li input{
                height: 30px;
                width: 64px;
                font-size: 10px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
            
            .form-control{
                width: 74px;
                font-size: 10px;
                padding: 6px 12px;
            }
            */
            .baixar{
                display:none;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
            /*
            .filter ul li fieldset {
                 padding: 12px 6px 0;
            }*/
            /**** Gráficos ****/
            .flot-chart {
                display: block;
                height: 300px;
            }
            .flot-chart-pie-content {
                height: 300px;
                margin: auto;
                width: 300px;
            }
            div#flot-bar-chart.flot-chart-content{ 
                margin-left: 63px !important;
            
            }
             #flot-pie-chart2 .pieLabel>div{
                width: 100px;
            }
            /**** Tabela ***/
            .table-responsive {
                font-size:6px !important;
            }
            .txt-left{
                 display: block;
                overflow: hidden;
                text-align: left;
                width: 110px;
            }
            .table > thead > tr > th{
                font-size: 8px;
            }
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
                padding:2px;
            }
        }
        /*** <-//media screen ***/
        /*** media screen ***/
        @media screen and (min-width: 300px) and (max-width: 379px) {
            /*
            .form-control{
                width: 250px;
                font-size: 10px;
                padding: 6px 12px;
            }*/
            
            
            .baixar{
                display:inline;
            }
            a.popup-with-move-anim{
                width:100%;
                margin:0;
                padding: 17px 0px 0 10px !important;
            }
            .filter {
                height:auto;
                float:left;
                overflow: hidden;
            }
            .filter ul li{
                margin:0;
                padding:0;
            }
            /*
            .filter ul li a {
               background-color: white;
                border: medium none;
                height: 30px;
                margin: 20px 0 0 0px;
                padding: 6px 0 0 !important;
                text-align: center;
                width: 250px;
            }*/
            
            
            /*** gráficos ***/
            .flot-chart {
                display: block;
                height: 300px;
            }
            .flot-chart-pie-content {
                height: 300px;
                margin: auto 0 auto -30px;
                width: 300px;
            }
            div#flot-bar-chart.flot-chart-content{ 
                margin-left: 3px !important;
            
            }
            #flot-pie-chart2 .pieLabel>div{
                width: 100px;
            }
            /**** Tabela ***/
            .table-responsive {
                font-size:10px;
            }
            .txt-left{
                 display: block;
                overflow: hidden;
                text-align: left;
                width: 100%;
                padding-left: 3% !important; 
                color:white;
                background-color:#383838;
            }
            .table > thead > tr > th{
                font-size: 8px;
            }
            .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
                padding:2px;
            }
            /*** css trick for table ***/
            /* Force table to not be like tables anymore */
            table #topClienteChassiMontadora, #topClienteChassiMontadora thead ,#topClienteChassiMontadora tbody ,
            #topClienteChassiMontadora th, #topClienteChassiMontadora td, #topClienteChassiMontadora tr { 
                    display: block; 
            }

            /* Hide table headers (but not display: none;, for accessibility) */
            #topClienteChassiMontadora thead tr { 
                    position: absolute;
                    top: -9999px;
                    left: -9999px;
            }

            #topClienteChassiMontadora tr { border: 1px solid #ccc; }

            #topClienteChassiMontadora td { 
                    /* Behave  like a "row" */
                    border: none;
                    border-bottom: 1px solid #eee; 
                    position: relative;
                    padding-left: 0%; 
            }

            #topClienteChassiMontadora td:before { 
                    /* Now like a table header */
                    position: absolute;
                    /* Top/left values mimic padding */
                    top: 6px;
                    left: 6px;
                    width: 45%; 
                    padding-right: 10px; 
                    white-space: nowrap;
                    text-align:right;
            }

            /*
            Label the data
            */
            #topClienteChassiMontadora.van td:nth-of-type(1):before { content: ""; }
            #topClienteChassiMontadora.van td:nth-of-type(2):before { content: "RENAULT:"; }
            #topClienteChassiMontadora.van td:nth-of-type(3):before { content: "FIAT:"; }
            #topClienteChassiMontadora.van td:nth-of-type(4):before { content: "MBENZ:"; }
            #topClienteChassiMontadora.van td:nth-of-type(5):before { content: "IVECO:"; }
            #topClienteChassiMontadora.van td:nth-of-type(6):before { content: "PEUGEOT:"; }
            #topClienteChassiMontadora.van td:nth-of-type(7):before { content: "CITROEN:"; }
            #topClienteChassiMontadora.van td:nth-of-type(8):before { content: "FORD:"; }
            #topClienteChassiMontadora.van td:nth-of-type(9):before { content: "OUTROS:"; }
            #topClienteChassiMontadora.van td:nth-of-type(10):before { content: "TOTAL:"; }
            
            #topClienteChassiMontadora.cam td:nth-of-type(1):before { content: ""; }
            #topClienteChassiMontadora.cam td:nth-of-type(2):before { content: "VW:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(3):before { content: "MBENZ:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(4):before { content: "FORD:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(5):before { content: "VOLVO:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(6):before { content: "IVECO:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(7):before { content: "SCANIA:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(8):before { content: "DAF:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(9):before { content: "OUTROS:"; }
            #topClienteChassiMontadora.cam td:nth-of-type(10):before { content: "TOTAL:"; }
            
            #topClienteChassiMontadora.bus td:nth-of-type(1):before { content: ""; }
            #topClienteChassiMontadora.bus td:nth-of-type(2):before { content: "MBENZ:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(3):before { content: "VW:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(4):before { content: "AGRALE:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(5):before { content: "IVECO:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(6):before { content: "VOLVO:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(7):before { content: "SCANIA:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(8):before { content: "INTER:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(9):before { content: "OUTROS:"; }
            #topClienteChassiMontadora.bus td:nth-of-type(10):before { content: "TOTAL:"; }
            
            /*** // css trick for table ***/
           
        }
        /*** <-//media screen ***/
        
        /*** grafico de pizza ***/
        .flot-chart-pie-content{
            width: 200px;
            height: 200px;
            margin: auto;
        }
        .flot-chart{
            display: block;
            height: 200px;
        }
        
       /******/
        table, .table-responsive, .responsive{
            overflow-x:hidden!important;
        }
        .table-module{
            width: 18%;
            float: left;
            margin-right: 2%;
        }
        .table-module-6cols{
            width: 100%;
            float: left;
            margin-right: 0.1%;
        }
        #ini, #fim{
            background-color:white;
        }
        .none{
            display:none;
        }
        .menu-invisible{
            margin:0 !important;
            float:left;
            background-color: #FFFFFF;
            min-height: 540px;
        }
        .filter ul li a,.filter ul li a:hover{
            padding: 40px 9px 0 9px;
        }
        .flot-bar-chart{
            height:200px;
        }
        #flot-bar-chart1,#flot-bar-chart2{
            width:83%;
            height:200px;
            float:right;
            margin-right:-12px;
            /*margin: 0 -20px 10px 93px !important;*/
        }
         #flot-bar-chart3,#flot-bar-chart4{
            width:90%;
            height:200px;
            float:right;
            margin-right:-17px;
            /*margin: 0 -20px 10px 93px !important;*/
        }
        .inputText{
            text-align:center;
        }
        
        
        .txt-left{
            text-align: left;
        }
        .txt-center{
            text-align: center;
        }
        .txt-fill{
            color:#5576a0;
            font-weight: bold;
        }
        .widget .flot-chart{
            height: 110px;
        }
        div#sparkline6.flot-chart-content{
            width:210px;
            height: 210px;
            position: relative;
            margin: -50px auto 0px auto;
        }
        .flot-chart-table{
            height: 150px;
            width:100% !important;
        }
        div#flot-bar-chart.flot-chart-content{
            padding: 0px;
            margin-left: 93px;
            position: relative;
            width: 90% !important;
        }
        th{width:1%;text-align: center;}
        .flot-char-content-table{
            padding-left: 25px;
            padding-right: 0px;
            text-align: center;
            font-size: 10px;
            letter-spacing: -1px;
        }
        #sparkline6 span div{
            padding-left:0px!important;
            padding-right:0px!important;
        }
	/** widgets */
        .widget-one, .widget-two, .widget-tree{
            height:230px;
        }
	.widget-one{
		background-color: #5576a0;
		color: #ffffff !important;
	}
	.widget-two{
		background-color: #60b6e2;
		color: #ffffff !important;
	}
	.widget-tree{
		background-color: #afbed0;
		color: #ffffff !important;
	}
        /** forms **/
        .form-control{
                width: 100px;
                font-size: 10px;
                padding: 6px 12px;
            }
	/** bar colors */
	.ct-series-a .ct-bar{
		stroke: #60b6e2;
	}
	.ct-series-b .ct-bar{
		stroke: orange;
	}
	.ct-series-a .ct-slice-pie,  .ct-series-a .ct-area{
		stroke: #60b6e2;
		fill: #60b6e2;
	}
        .ct-label{
            color: rgba(0, 0, 0, 1);
        }
	.ct-legend {
            position: relative;
            z-index: 10;
        }

        li {
            position: relative;
            padding-left: 0px;
            margin-bottom: 3px;
        }

        li:before {
            width: 12px;
            height: 12px;
            position: absolute;
            left: 0;
            content: '';
            border: 3px solid transparent;
            border-radius: 2px;
        }

        li.inactive:before {
            background: transparent;
        }

        &.ct-legend-inside {
            position: absolute;
            top: 0;
            right: 0;
        }
   <%=""+
    "@for $i from 0 to length($ct-series-colors) {"+
    "  .ct-series-#{$i}:before {"+
    "        background-color: nth($ct-series-colors, $i + 1);"+
    "        border-color: nth($ct-series-colors, $i + 1);"+
    "    	}"+
    "		}"+
    "	}"%>
	
	.ct-tooltip {
    position: absolute;
    display: inline-block;
    min-width: 5em;
    padding: 8px 10px;
    background: #383838;
    color: #fff;
    text-align: center;
    pointer-events: none;
    z-index: 100;
    transition: opacity .2s linear;

    // Arrow
    &:before {
        position: absolute;
        bottom: -14px;
        left: 50%;
        border: solid transparent;
        content: ' ';
        height: 0;
        width: 0;
        pointer-events: none;
        border-color: rgba(251, 249, 228, 0);
        border-top-color: #383838;
        border-width: 7px;
        margin-left: -8px;
    }

    &.hide {
        display: block;
        opacity: 0;
        visibility: hidden;
    }
}
	</style>

</head>

<body class="fixed-sidebar">

<div id="wrapper">

   <%
   String paramPrint = request.getParameter("print");
   String printStyle = "";
   if(paramPrint==null){
   %>
    <jsp:include page="incNavSide.jsp">
        <jsp:param name="page" value="boletim" />
    </jsp:include>
    <%
   }else{
       printStyle="margin:0;";
   }
    %>

    <div id="page-wrapper" class="gray-bg" style="<%=printStyle%>">
        <div class="row border-bottom">
             <jsp:include page="incNavbarStaticTop.jsp" />
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <%
                    if(paramPrint==null){
                    %>
                    <div class="text-center m-t-lg  logo-assobens-relatorio">
                    <%
                    }else{
                    %>    
                       <div class="text-center m-t-lg  logo-quest-relatorio">
                    <%
                        }
                     %> 
                    
                        <h1>
                            Análises Adicionais
                            
                        </h1>
                        <small id="small">
                           
                           <br/><br/><br/><br/>
                        </small>
                    </div>
                    <!-- dialog itself, mfp-hide class is required to make dialog hidden -->
                    <div id="small-dialog" class="zoom-anim-dialog mfp-hide">
                        <h1 id="progress-report-title">Gerando Arquivo de Clientes, Aguarde!</h1>
                        <p id="progress-report">Lista de arquivos para enriquecer.</p>
                    </div>
                </div>
            </div>
            <!-- filter -->
            <div class="row">
                <div class="col-lg-12">
                    <div id="date-update" style="float:right">
                            
                    </div>
                </div>
                
                <div class="col-lg-12">
                    <div class="filter">
                      <ul>
                        <li class="fieldset">
                            
                            <fieldset>
                                <legend>Período</legend>
                                <span>de</span>
                        	<label class="user">
                                    <input type="text" id="ini" name="ini" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Inicial"> 
                            </label> 
                            <span>até</span>
                            <label class="pass"> 
                            	<input type="text" id="fim" name="fim" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Final"> 
                            </label>
                            </fieldset>
                            <fieldset>
                                <legend>Gama</legend>
                            <label class="user">
                                <select id="sub_seg" name="sub_seg" class="form-control m-b" title="Selecione uma Gama" onchange="selectSegGamma()">
                                	
                                        <%
                                        String areaOper = request.getParameter("area_oper");
                                        String subSeg   = request.getParameter("sub_seg");
                                        String ini      = request.getParameter("ini");
                                        String fim      = request.getParameter("fim");
                                        String seg      = request.getParameter("seg");
                                        String regional = request.getParameter("regional");
                                        
                                        String start="",finish="";
                                        
                                        if(ini==null || ini.length()==0){
                                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Calendar cal = Calendar.getInstance();
                                            // add -1 month to current month
                                            cal.add(Calendar.MONTH, -1);
                                            // set DATE to 1, so first date of previous month
                                           // cal.set(Calendar.DATE, 1);
                                            int month = cal.get(Calendar.MONTH)+1;
                                            int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                                            int year = cal.get(Calendar.YEAR);
                                            
                                            finish = year+"-"+String.valueOf(month)+"-"+String.valueOf(lastDayOfMonth);
                                            String startYear = finish.substring(0, 4);
                                            String startMonth = finish.substring(5, 7);
                                            start = year+"-"+String.valueOf(month)+"-"+"01";
                                            //start = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
                                            
                                        }else{
                                            //start = ini;
                                            //finish = fim;
                                            start = (Utils.dateFormat(ini));
                                            finish = (Utils.dateFormat(fim));
                                            
                                        }
                                        
                                        //ini = (Utils.dateFormat(ini));
                                        //fim = (Utils.dateFormat(fim));
                                        
                                        if(subSeg==null){
                                            subSeg="todos";
                                        }
                                        List<CollectionColumns> subsegmentos = new BusinessDelegate().selectSubSegmentos(start,finish);
                                        if(subsegmentos.size()==0){
                                            %>
                                            <option value="todos">Ñ há dados p/ o período</option>
                                            <%
                                        }else{
                                            %>
                                            <option value="todos">TODOS</option>
                                            <%
                                        }
                                        
                                        for(CollectionColumns s: subsegmentos){
                                        %>
                                        <option value="<%=s.getColumns()[0]%>" <% if(subSeg!=null && !subSeg.isEmpty() && subSeg.length()>0){if(s.getColumns()[0].equalsIgnoreCase(subSeg)){out.print("selected=\"selected\"");}else{out.print("");}} %> ><%=s.getColumns()[1]%></option>
                                        <%
                                        }
                                        %>
                                </select>
                            </label>
                            </fieldset>
                            <fieldset>
                                <legend>Segmento</legend>
                            <label class="user">
                            	<select id="segmento" name="segmento" class="form-control m-b" title="Selecione um Segmento">
                                	
                                        <%
                                       // String areaOper = request.getParameter("area_oper");
                                        
                                       // String ini      = request.getParameter("ini");
                                       // String fim      = request.getParameter("fim");
                                        
                                        
                                        if(seg==null){
                                            seg="todos";
                                        }
                                        List<CollectionColumns> segmentos = new BusinessDelegate().selectSegmentos(start,finish);
                                        if(segmentos.size()==0){
                                            %>
                                            <option value="todos">Ñ há dados p/ o período</option>
                                            <%
                                        }else{
                                            %>
                                            <option value="todos">TODOS</option>
                                            <%
                                        }
                                        
                                        for(CollectionColumns s: segmentos){
                                        %>
                                        <option value="<%=s.getColumns()[0]%>" <% if(seg!=null && !seg.isEmpty() && seg.length()>0){if(s.getColumns()[0].equalsIgnoreCase(seg)){out.print("selected=\"selected\"");}else{out.print("");}} %> ><%=s.getColumns()[1]%></option>
                                        <%
                                        }
                                        %>
                                </select>
                            </label>
                            </fieldset>
                            <fieldset>
                                <legend>Regional</legend>
                            <label class="user">
                                <select id="regional" name="regional" class="form-control m-b" title="Selecione uma Regional" onchange="checkAreaOper()">
                                	 <option value="todos">TODOS</option>
                                        <%
                                        
                                        
                                        //ini = (Utils.dateFormat(ini));
                                        //fim = (Utils.dateFormat(fim));
                                        
                                        if(regional==null){
                                            regional="";
                                        }
                                        List<CollectionColumns> regionais = new BusinessDelegate().selectRegional();
                                        for(CollectionColumns s: regionais){
                                        %>
                                        <option value="<%=s.getColumns()[0]%>" <% if(regional!=null && !regional.isEmpty() && regional.length()>0){if(s.getColumns()[0].equalsIgnoreCase(regional)){out.print("selected=\"selected\"");}else{out.print("");}} %> ><%=s.getColumns()[0]%></option>
                                        <%
                                        }
                                        %>
                                </select>
                            </label>
                            </fieldset>
                            <fieldset>
                                <legend>Área Operacional</legend>    
                            <label class="user">
                            	<select id="area_oper" name="area_oper" class="form-control m-b" title="Selecione uma Área Operacional">
                                    <option value="todos">TODOS</option>
                                        <%
                                        
                                        if(areaOper!=null && !areaOper.isEmpty() && areaOper.length()>0){
                                        
                                        }
                                        List<MemberAreaOper> areas = new BusinessDelegate().selectMemberAreaOper(memberId);
                                        for(MemberAreaOper a: areas){
                                        %>
                                        <option value="<%=a.getAreaoper().getArea_operacional()%>" <% if(areaOper!=null && !areaOper.isEmpty() && areaOper.length()>0){if(a.getAreaoper().getArea_operacional().equalsIgnoreCase(areaOper)){out.print("selected=\"selected\"");}else{out.print("");}} %> ><%=a.getAreaoper().getDescricao()%></option>
                                        <%
                                        }
                                        %>
                                        
                                </select>
                            </label>
                            
                            </fieldset>
                            
                            </li>
                            <li><input type="submit" class="buttonSubmit cor27 btn btn-primary block  m-b" value="OK" onClick="dashboard();"> 
                            </li>
                        <!-- <li><a>marca</a></li> -->
                        <!--
                        <li id="li-lista-cliente"><a onclick="generateReportEnriquecimento()"  class="popup-with-move-anim"  href="#small-dialog"><i class="fa fa-file-excel-o"></i>&nbsp;&nbsp;<span class="baixar">Lista</span></a></li>
                        -->
                        <li id="li-pdf"><a onclick="downloadPDF()"  class="popup-with-move-anim"  href="#small-dialog"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;<span class="baixar">PDF</span></a></li>
                        <!--
                        <li><a onclick="downloadPNG()"  class="popup-with-move-anim"  href="#small-dialog"><i class="fa fa-file-image-o"></i>&nbsp;&nbsp;<span class="baixar">PNG</span></a></li>
                        -->
                        <!--
                        <li><a>PDF</a></li>-->
                      </ul>
                    </div>
                </div>
            </div>
            <!-- // filter-->                            
            <!-- widgets -->
            
            <!--// widgets -->
            
           
            
            
            <!-- other graphics -->
            <div class="row graph" id="row1">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Clientes ABC x MARCA - Quantidade de Clientes por Classificação ABC</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            
                            
                            <div id="cliente-a"></div>
                            
                            <div id="cliente-b"></div>
                            
                            <div id="cliente-c"></div>
                            
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            <!-- other graphics -->
            <div class="row graph" id="row2a">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Participação de Classificação de Clientes ABC x Gama</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            
                            
                            <div id="cliente-abc"></div>
                            <div id="cliente-abc-footer">
                                <br/>
                            </div>
                            
                            
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            <!-- other graphics -->
            <div class="row graph" id="row2">
                <div class="col-lg-6" id="r2-box-leves">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Participação de Classificação de Clientes ABC - Leves</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r2-leves"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6" id="r2-box-medios">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Participação de Classificação de Clientes ABC -  Médios</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r2-medios"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-6" id="r2-box-semipesados">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Participação de Classificação de Clientes ABC - Semipesados</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r2-semipesados"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6" id="r2-box-pesados">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Participação de Classificação de Clientes ABC - Pesados</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r2-pesados"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            
            <!-- other graphics -->
            <div class="row graph" id="row3">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>NOVOS CLIENTES - Empresas que Não compravam Caminhoes desde 2014 e Compraram no período</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="novos-clientes"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            
             <!-- other graphics -->
            <div class="row graph" id="row4">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>NOVOS CLIENTES 6 Meses - Empresas com data de Abertura de até 6 meses e Compraram no período</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="novos-clientes-6meses"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            
            <!-- other graphics -->
            <div class="row graph" id="row5">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>MARKET SHARE POR ATIVIDADE</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="atividade-economica"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            <!-- other graphics -->
            <div class="row graph" id="row6">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Concentração de Clientes - Contagem de clientes por REGIONAL x MARCA</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="row6-concentracao-cliente"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // other -->
            <!-- other graphics -->
            <div class="row graph" id="row7">
                <div class="col-lg-4" id="r7-total-box">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Penetração de Clientes pela IVECO - TOTAL</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r7-total"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4" id="r7-leves-box">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Penetração de Clientes pela IVECO - LEVES</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r7-leves"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4" id="r7-medios-box">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Penetração de Clientes pela IVECO - MÉDIOS</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r7-medios"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4" id="r7-semipesados-box">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Penetração de Clientes pela IVECO - SEMIPESADOS</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r7-semipesados"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4" id="r7-pesados-box">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Penetração de Clientes pela IVECO - PESADOS</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="flot-chart">
                                <div class="flot-chart-pie-content" id="r7-pesados"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                 
                
                
            </div>
            <!-- // other -->
            
        </div>
        <div class="footer">
            <div class="pull-right">
                <%=Preferences.copyrightRight%>
            </div>
            <div>
                <%=Preferences.copyright%> <%=Preferences.copyrightYear%>
            </div>
        </div>

    </div>
</div>
<!-- ITFOX scripts -->
<script src="js/default.js"></script>
<script src="js/componentTable.js"></script>
<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- datepicker -->
<!--
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
-->
<script src="js/plugins/jquery-ui-1.11-2.4/jquery-ui.js"></script>
<!-- Chartist -->
 <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
 <!--
 <script src="js/plugins/chartist/chartist.min.js"></script> -->
 <script src="js/plugins/chartist/chartist-plugin-legend.js"></script>
 <script src="js/plugins/chartist/chartist-plugin-pointlabels.js"></script>
 <script src="js/plugins/chartist/chartist-plugin-tooltip.js"></script>
 <!-- Sparkline -->
 <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>
 
 <!-- Peity -->
<script src="js/plugins/peity/jquery.peity.min.js"></script>
<script src="js/demo/peity-demo.js"></script>


<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>

<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>

<!--
<script src="js/plugins/flot/jquery.flot.tooltip.0.8.5.js"></script>
-->
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/plugins/flot/jquery.flot.time.js"></script>


<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.js"></script>

<!-- Flot demo data -->
<!--
<script src="js/demo/flot-demo.js"></script>
-->
<!-- Magnific Popup - Lightbox & dialog modal -->
<script src="js/plugins/magnific-popup/magnific-popup.1.0.0.js"></script>
<!-- Jquery responsive tables -->
<script type="text/javascript" src="js/plugins/jquery-responsive-tables-master/jquery-responsiveTables.js"></script>
<script type="text/javascript" src="js/plugins/jquery-responsive-tables-master/jquery.responsiveText.js"></script>

 <script>
        $(document).ready(function() {
            
            
		
            /*
            var sparklineCharts = function(){
                
                 $("#sparkline6").sparkline([5, 3], {
                     type: 'pie',
                     height: '111',
                     sliceColors: ['#5576a0', '#F5F5F5']
                 });

                
            };

            var sparkResize;

            $(window).resize(function(e) {
                clearTimeout(sparkResize);
                sparkResize = setTimeout(sparklineCharts, 500);
            });

            sparklineCharts();
        */
        <%
        Calendar cal = Calendar.getInstance();
        cal.setTime(m.getDate());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        %>
			// formatando o date picker for inicial date 
			 $( "#ini" ).datepicker({
				    changeMonth: true,
				    changeYear: true,
                                    dateFormat: 'dd/mm/yy',
                                    minDate: new Date(<%=year%>,<%=month-1%>,<%=day%>),
                                    maxDate:1,
                                    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                                    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
                                    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                                    nextText: 'Próximo',
                                    prevText: 'Anterior',
                                    // setting time in the current month, max by the current date
                                    onSelect: function( selectedDate ) {
                                        // executando os segmentos
                                        selectGamma();
                                    }
				});
			 $( "#ini" ).datepicker( "option", "dateFormat", "dd/mm/yy" );	
                         
                         var dateEnd = new Date();
                        dateEnd.setDate(1);
                        dateEnd.setMonth(dateEnd.getMonth()-1);
                        var lastDayOfMonthEnd = new Date(dateEnd.getFullYear(), dateEnd.getMonth()+1, 0).getDate();
                            dateEnd.setDate(lastDayOfMonthEnd);
			// $.datepicker.formatDate('dd/mm/yy', date);
			 $( "#fim" ).datepicker({
				    changeMonth: true,
				    changeYear: true,
                                    dateFormat: 'dd/mm/yy',
                                    minDate: new Date(<%=year%>,<%=month-1%>,<%=day%>),
                                    maxDate:dateEnd,
                                    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                                    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
                                    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                                    nextText: 'Próximo',
                                    prevText: 'Anterior',
                                    // setting time in the current month, max by the current date
                                    onSelect: function( selectedDate ) {
                                        // executando os segmentos
                                        selectGamma();
                                    }
				});
			$( "#fim" ).datepicker( "option", "dateFormat", "dd/mm/yy" );	
                        // data atual
                        var date = new Date();
                        date.setDate(1);
                        date.setMonth(date.getMonth()-1);
                        <%
                            if(ini!=null && !ini.isEmpty() && ini.length()>0){
                             %>
                               date = $.datepicker.parseDate("dd/mm/yy", "<%=ini%>");      
                               $( "#ini" ).val($.datepicker.formatDate('dd/mm/yy', date));      
                            <%
                            }else{
                            %>
                            $( "#ini" ).val($.datepicker.formatDate('dd/mm/yy', date));
                            <%
                            }
                        %>
                        // fim
                         <%
                            if(fim!=null && !fim.isEmpty() && fim.length()>0){
                             %>
                               date = $.datepicker.parseDate("dd/mm/yy", "<%=fim%>");      
                               $( "#fim" ).val($.datepicker.formatDate('dd/mm/yy', date));      
                            <%
                            }else{
                               
                            %>
                            var today = date;
                            var lastDayOfMonth = new Date(today.getFullYear(), today.getMonth()+1, 0).getDate();
                            date.setDate(lastDayOfMonth);
                            $( "#fim" ).val($.datepicker.formatDate('dd/mm/yy', date)); 
                            <%
                            }
                        %>
                            
			// exibindo os segmentos
			//selectSeg();
                        // inicializando o dashboard
                        dashboard();
			
			// exibindo lightbox
			$('.popup-with-zoom-anim').magnificPopup({
				type: 'inline',
		
				fixedContentPos: false,
				fixedBgPos: true,
		
				overflowY: 'auto',
		
				closeBtnInside: true,
				preloader: false,
				
				midClick: true,
				removalDelay: 300,
				mainClass: 'my-mfp-zoom-in'
			});
		
			$('.popup-with-move-anim').magnificPopup({
				type: 'inline',
		
				fixedContentPos: false,
				fixedBgPos: true,
		
				overflowY: 'auto',
		
				closeBtnInside: true,
				preloader: false,
				
				midClick: true,
				removalDelay: 300,
				mainClass: 'my-mfp-slide-bottom'
			});			
                        
        
             });
                
                
                function dateUpdate(){
                    var url = "DashboardServlet?dateUpdate=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
                    
                    $.getJSON(url, function (result) {
                                    
					if (result && result.length) {
						var dateUpd = [];
						  $.each( result, function( key, val ) {
							  dateUpd.push(val.dateUpdate);
						  });
						 
                                                    $("#date-update").html("Dados atualizados até: "+dateUpd); 
                                                    
					}else{
                                            //console.log("dados atualizados error");
                                            $("#date-update").html(""); 
                                        }
				});
                }
		function dashboard(){
                    
                   // console.log("hello");
			//countChassi();
                        
			//plotPieDistSeg();
			//plotPieDistMarca();
			//plotHorizontalBarRankMunic();
			//plotHorizontalBarRankModel();
			//plotVerticalBar();
                        //plotVerticalBar1();
                        //plotVerticalBar2();
                        //plotVerticalBar3();
                        //plotVerticalBar4();
                        //plotVerticalBar5();
                        //plotHorizontalBar2a();
                        //plotHorizontalBar2b();
                        //plotPiePerfilCliente();
                        //selectTopSegmento();
                        //selectTopSegmentoNacional();
                        //selecRankingRegionalAreaOperacional();
                        dateUpdate();
                        hideRows();
                        showRows();
                        clienteABCByMarca();
                        clienteABCByGama();
                        getNovosClientes();
                        getNovosClientes6Meses();
                        getAtividadeEconomica();
                        getConcentracaoCliente();
                        getPenetracaoClientes();
                        
                        // responsive tables
                        setTimeout(function(){
                                   // responsiveTables();
                                }, 2000); 
                        
                        getIndex();
		}
                
                 function getIndex(){
                     var url = "index.jsp?dateUpdate=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
                     $('#index').attr('href',url);
                    // alert($('#boletim').attr('href'));
                          
                }
                //--> function plot Pie Cliente ABC X Gama - leves
		function plotPie(pieId, data){
                                //console.log(data+"-"+data.length);
				//var url = "DashboardServlet?distMarca=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
                                $(pieId).html("<div class=\"pre-loader\">&nbsp;</div>");
				//$.getJSON(url, function (data) {
					if (data && data.length) {
						var plotObj = $.plot($(pieId), data, {
							series: {
								pie: {
									
                                                                        show: true,
                                                                        radius: 2/3,
                                                                        innerRadius: 0.0,
                                                                         combine: {
                                                                            color: '#999',
                                                                            threshold: 0.02
                                                                        },
                                                                        label: {
										show:true,
										radius:1,
										formatter: labelFormatter,
										threshold: 0.02,
										background: {
										  opacity: 1,
										  color: 'transparent'
										}
									}
								}
							},
							legend: {
								show: false
							
							},
							grid: {
								hoverable: true
							},
							tooltip: false,
							tooltipOpts: {
                                                                content: function(label,x,y){
                                                                    return y+" | %p.0%, "+label;
                                                                },
								/*content: "%p.0%, %s, n=%n",*/ // show percentages, rounding to 2 decimal places
								shifts: {
									x: 20,
									y: 0
								},
								defaultTheme: false
							}
						});
						  
						
					}else{
                                         $(pieId).html("Não há dados.");
                                        }
				//});
				
		}
		//<-- function plot Pie plotPieR2Leves
                function clienteABCByMarca(){
                  //  var dataHead=["CAMINHÕES","2606", "21,2%"];
                  //  var dataBody=[
                  //                  [{value:"Mercedes Benz - XYZ",title:"TITULO",type:"string"}, {value:"19.500",title:"total",type:"currency"}, {value:"9%",title:"market share",type:"percentual"}],
                  //                  [{value:"Mercedes Benz - XYZ",title:"TITULO",type:"string"}, {value:"19.500",title:"total",type:"currency"}, {value:"9%",title:"market share",type:"percentual"}],
                   //                 [{value:"Mercedes Benz - XYZ",title:"TITULO",type:"string"}, {value:"19.500",title:"total",type:"currency"}, {value:"9%",title:"market share",type:"percentual"}]
                   //              ];
                   // var dataFoot=[
                   //                 [{value:"Total no Segmento",title:"TITULO",type:"string"}, {value:"19.500",title:"total",type:"currency"}, {value:"100%",title:"market share",type:"percentual"}]
                   //              ];
                   // renderTable("cliente-a", "color1", "9", "9", "", "CLIENTES A", dataHead, dataBody, dataFoot);
                   // renderTable("cliente-b", "color2", "9", "9", "", "CLIENTES B", dataHead, dataBody, dataFoot);
                   // renderTable("cliente-c", "color3", "9", "9", "", "CLIENTES C", dataHead, dataBody, dataFoot);
                    
                    //console.log(dataBody);
                    getClienteA();
                    getClienteB();
                    getClienteC();
                    /*
                    setTimeout(function(){
                                    responsiveTables();
                                }, 2000);
                    */
                    
                }
                
                function clienteABCByGama(){
                   // clienteABCByGamaCharts();            
                    clienteABCByGamaTable();
                }
                function clienteABCByGamaCharts(){
                    var dataLeves = [];
                    var dataMedios = [];
                    plotPie("#r2-leves", dataLeves);
                    plotPie("#r2-medios", dataMedios);
                }
                
                function clienteABCByGamaTable(){
                    getClienteABCByGama();
                    
                }
                function hideRows(){
                    $('#row1').hide();
                    $('#row2').hide();
                    $('#row2a').hide();
                    $('#row3').hide();
                    $('#row4').hide();
                    $('#row5').hide();
                    $('#row6').hide();
                    $('#row7').hide();
                    /*$('#row3').hide();
                    $('#row4').hide();
                    $('#row5').hide();
                    $('#row6').hide();
                    $('#row7').hide();
                    $('#row8').hide();
                    $('#row9').hide();
                    $('#row10').hide();*/
                }
                function showRows(){
                     $('#row1').show();
                    // $('#row2').show(); // PIE CHARTS - NOT TO USE
                     $('#row2a').show();
                     $('#row3').show();
                     $('#row4').show();
                     $('#row5').show();
                     $('#row6').show();
                     $('#row7').show();
                }
                //--> function responsive tables
                function responsiveTables(){
                    $('.responsive').not('table').responsiveText();
                    $('table.responsive').responsiveTables();
                }
                //<-- function responsive tables
               
                //--> function plot getClienteA 
		function getClienteA(){
				var url = "DashboardBoletim?clientesAByMarca=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#cliente-a").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                          
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.marca,title:"MARCA:     ",type:"string"}, {value:val.valor,title:"TOTAL",type:"currency"}, {value:val.perc,title:"MARKET SHARE",type:"percent"}];
                                                        seriesData.push(eachRow);
                                                        total = val.totals;
                                                        total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["TOTAL",total, total_perc];
                                              var caption = "CLIENTES A = Clientes que compraram >10 veículos";
                                              renderTable("cliente-a", "color1", "9", "12", caption, "CLIENTES A", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#cliente-a").html("CLIENTES A - NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getClienteA 
                //--> function plot getClienteB 
		function getClienteB(){
				var url = "DashboardBoletim?clientesBByMarca=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#cliente-b").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.marca,title:"MARCA:     ",type:"string"}, {value:val.valor,title:"TOTAL",type:"currency"}, {value:val.perc,title:"MARKET SHARE",type:"percent"}];
                                                        seriesData.push(eachRow);
                                                        total = val.totals;
                                                        total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["TOTAL",total, total_perc];  
                                              var caption = "CLIENTES B = Clientes que compraram entre 3 e 10 veículos";
                                              renderTable("cliente-b", "color2", "9", "12", caption, "CLIENTES B", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#cliente-b").html("CLIENTES B - NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getClienteB 
                
                //--> function plot getClienteC
		function getClienteC(){
				var url = "DashboardBoletim?clientesCByMarca=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#cliente-c").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.marca,title:"MARCA:     ",type:"string"}, {value:val.valor,title:"TOTAL",type:"currency"}, {value:val.perc,title:"MARKET SHARE",type:"percent"}];
                                                        seriesData.push(eachRow);
                                                        total = val.totals;
                                                        total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["TOTAL",total, total_perc];  
                                              var caption = "CLIENTES C = Clientes que compraram até <3 veículos";
                                              renderTable("cliente-c", "color3", "9", "12", caption, "CLIENTES C", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#cliente-c").html("CLIENTES C - NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getClienteC 
                
                //--> function plot getClienteABCbyGama 
		function getClienteABCByGama(){
				var url = "DashboardBoletim?clientesABCByGama=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var seriesLeves = [];
                                var seriesMedios = [];
                                var seriesSemipesados = [];
                                var seriesPesados = [];
                                var colors = ["lightsteelblue","lightskyblue","lightcoral"];
                                var total = 0;
                                var total_perc = 0;
                                /***** PIE CHARTS ********
                                 * NOT TO USE
                                 
                                $("#r2-leves").html("<div class=\"pre-loader\">&nbsp;</div>");
                                $("#r2-medios").html("<div class=\"pre-loader\">&nbsp;</div>");
                                $("#r2-semipesados").html("<div class=\"pre-loader\">&nbsp;</div>");
                                $("#r2-pesados").html("<div class=\"pre-loader\">&nbsp;</div>");
                                */
                                $("#cliente-abc").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                          
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.clientes,title:"CLIENTE:     ",type:"string"}, {value:val.leves,title:"LEVES",type:"currency"}, {value:val.leves_+"%",title:"MARKET SHARE LEVES",type:"percent"}, {value:val.medios,title:"MEDIOS",type:"currency"}, {value:val.medios_+"%",title:"MARKET SHARE MEDIOS",type:"percent"},{value:val.semipesados,title:"SEMIPESADOS",type:"currency"}, {value:val.semipesados_+"%",title:"MARKET SHARE SEMIPESADOS",type:"percent"},{value:val.pesados,title:"PESADOS",type:"currency"}, {value:val.pesados_+"%",title:"MARKET SHARE PESADOS",type:"percent"},{value:val.total,title:"TOTAL",type:"percent"}];
                                                        seriesData.push(eachRow);
                                                        total = val.totals;
                                                        total_perc = val.totals_perc;
                                                        seriesLeves.push({label:val.clientes,data:val.leves, color: colors[key]});
                                                        seriesMedios.push({label:val.clientes,data:val.medios, color: colors[key]});
                                                        seriesSemipesados.push({label:val.clientes,data:val.semipesados, color: colors[key]});
                                                        seriesPesados.push({label:val.clientes,data:val.pesados, color: colors[key]});
                                                  });
                                              var dataHead=["CLIENTES","LEVES", "%","MEDIOS", "%","SEMIPESADOS", "%","PESADOS", "%", "TOTAL"];  
                                              renderTable("cliente-abc", "color1", "9", "12", "", "CLIENTES ABCxGAMA", dataHead, seriesData, null);  
                                              // plot pie charts
                                              //console.log(seriesLeves);
                                              //$("#r2-leves").html("<div class=\"pre-loader\">&nbsp;</div>");
                                              /****** PLOT PIE CHARTS *******
                                               * NOT TO USE
                                              $("#r2-box-leves").show();
                                              $("#r2-box-medios").show();
                                              $("#r2-box-semipesados").show();
                                              $("#r2-box-pesados").show();
                                              var sum=0;
                                              // leves
                                              for(i=0;i<seriesLeves.length;i++){
                                                  sum+=seriesLeves[i].data;
                                              }
                                              if(sum==0){$("#r2-box-leves").hide();}
                                              // medios
                                              sum=0;
                                              for(i=0;i<seriesMedios.length;i++){
                                                  sum+=seriesMedios[i].data;
                                              }
                                              if(sum==0){$("#r2-box-medios").hide();}
                                              // semipesados
                                              sum=0;
                                              for(i=0;i<seriesSemipesados.length;i++){
                                                  sum+=seriesSemipesados[i].data;
                                              }
                                              if(sum==0){$("#r2-box-semipesados").hide();}
                                              // pesados
                                              sum=0
                                              for(i=0;i<seriesPesados.length;i++){
                                                  sum+=seriesPesados[i].data;
                                              }
                                              if(sum==0){$("#r2-box-pesados").hide();}
                                              // plotanto os graficos
                                              plotPie("#r2-leves",seriesLeves);
                                              plotPie("#r2-medios",seriesMedios);
                                              plotPie("#r2-semipesados",seriesSemipesados);
                                              plotPie("#r2-pesados",seriesPesados);
                                              */
                                        }else{
                                            // não ha dados
                                            var msg= "NÃO HÁ DADOS";
                                             $("#cliente-abc").html(msg); 
                                             /*
                                             $("#r2-leves").html(msg);
                                             $("#r2-medios").html(msg);
                                             $("#r2-semipesados").html(msg);
                                             $("#r2-pesados").html(msg);
                                             */
                                        }
                                    }); 
		}
		//<-- function plot getClienteABCbyGama 
                
                //--> function plot getNovosClientes
		function getNovosClientes(){
				var url = "DashboardBoletim?novosClientes=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#novos-clientes").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.cnpj,title:"CNPJ:     ",type:"string"}, {value:val.nome,title:"RAZÃO SOCIAL ",type:"currency"}, {value:val.ford,title:"FORD",type:"currency"}, {value:val.mercedes,title:"MERCEDES BENZ",type:"currency"}, {value:val.scania,title:"SCANIA",type:"currency"}, {value:val.volvo,title:"VOLVO",type:"currency"}, {value:val.vw,title:"VW",type:"currency"}, {value:val.iveco,title:"IVECO",type:"currency"}, {value:val.renault,title:"RENAULT",type:"currency"}, {value:val.fiat,title:"FIAT",type:"currency"}, {value:val.outros,title:"OUTROS",type:"currency"}, {value:val.total,title:"TOTAL",type:"currency"}];
                                                        seriesData.push(eachRow);
                                                       // total = val.totals;
                                                       // total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["CNPJ/CPF","RAZÃO SOCIAL", "FORD", "M.BENZ","SCANIA","VOLVO", "VW","IVECO", "RENAULT", "FIAT", "OUTROS","TOTAL"];  
                                              renderTable("novos-clientes", "color1", "7", "12", "", "NOVOS CLIENTES", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#novos-clientes").html("NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getNovosClientes 
                
                //--> function plot getNovosClientes6Meses
		function getNovosClientes6Meses(){
				var url = "DashboardBoletim?novosClientes6Meses=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#novos-clientes-6meses").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.cnpj,title:"CNPJ:     ",type:"string"}, {value:val.nome,title:"RAZÃO SOCIAL ",type:"currency"}, {value:val.ford,title:"FORD",type:"currency"}, {value:val.mercedes,title:"MERCEDES BENZ",type:"currency"}, {value:val.scania,title:"SCANIA",type:"currency"}, {value:val.volvo,title:"VOLVO",type:"currency"}, {value:val.vw,title:"VW",type:"currency"}, {value:val.iveco,title:"IVECO",type:"currency"}, {value:val.renault,title:"RENAULT",type:"currency"}, {value:val.fiat,title:"FIAT",type:"currency"}, {value:val.outros,title:"OUTROS",type:"currency"}, {value:val.total,title:"TOTAL",type:"currency"}];
                                                        seriesData.push(eachRow);
                                                       // total = val.totals;
                                                       // total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["CNPJ/CPF","RAZÃO SOCIAL", "FORD", "M.BENZ","SCANIA","VOLVO", "VW","IVECO", "RENAULT", "FIAT", "OUTROS","TOTAL"];  
                                              renderTable("novos-clientes-6meses", "color1", "9", "12", "", "NOVOS CLIENTES", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#novos-clientes-6meses").html("NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getNovosClientes6Meses 
                
                //--> function plot getAtividadeEconomica
		function getAtividadeEconomica(){
				var url = "DashboardBoletim?atividadeEconomica=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#atividade-economica").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.atividade,title:"ATIVIDADE ECONÔMICA: ",type:"string"}, {value:val.ford,title:"FORD",type:"currency"}, {value:val.mercedes,title:"MERCEDES BENZ",type:"currency"}, {value:val.scania,title:"SCANIA",type:"currency"}, {value:val.volvo,title:"VOLVO",type:"currency"}, {value:val.vw,title:"VW",type:"currency"}, {value:val.iveco,title:"IVECO",type:"currency"}, {value:val.renault,title:"RENAULT",type:"currency"}, {value:val.fiat,title:"FIAT",type:"currency"}, {value:val.outros,title:"OUTROS",type:"currency"}, {value:val.total,title:"TOTAL",type:"currency"}, {value:val.mktshare,title:"MKT SHARE",type:"currency"}];
                                                        seriesData.push(eachRow);
                                                       // total = val.totals;
                                                       // total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["ATIVIDADE ECONÔMICA", "FORD", "M.BENZ","SCANIA","VOLVO", "VW","IVECO", "RENAULT", "FIAT", "OUTROS","TOTAL", "MKT.SHARE"];  
                                              renderTable("atividade-economica", "color1", "9", "12", "", "ATIVIDADE ECONÔMICA", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#atividade-economica").html("NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getAtividadeEconomica 
                
                //--> function plot getConcentracaoCliente
		function getConcentracaoCliente(){
				var url = "DashboardBoletim?concentracaoCliente=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var total = 0;
                                var total_perc = 0;
                                $("#row6-concentracao-cliente").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        var eachRow = [{value:val.marca,title:"MARCA: ",type:"string"}, {value:val.sp+"%",title:"SÃO PAULO",type:"currency"}, {value:val.curitiba+"%",title:"CURITIBA",type:"currency"}, {value:val.bh+"%",title:"BELO HORIZONTE",type:"currency"}, {value:val.recife+"%",title:"RECIFE",type:"currency"}];
                                                        seriesData.push(eachRow);
                                                       // total = val.totals;
                                                       // total_perc = val.totals_perc;
                                                  });
                                              var dataHead=["MARCA", "SÃO PAULO", "CURITIBA","BELO HORIZONTE","RECIFE"];  
                                              renderTable("row6-concentracao-cliente", "color1", "9", "12", "", "MarketShare de volume de Clientes por Regional", dataHead, seriesData, null);  
                                        }else{
                                            // não ha dados
                                             $("#row6-concentracao-cliente").html("NÃO HÁ DADOS");   
                                        }
                                    }); 
		}
		//<-- function plot getConcentracaoCliente 
                function getPenetracaoClientes(){
                    var subSeg = $('#sub_seg').val();
                    if(subSeg=="todos"){
                        $("#r7-box-leves").hide();
                        $("#r7-box-medios").hide();
                        $("#r7-box-semipesados").hide();
                        $("#r7-box-pesados").hide();
                        $("#r7-box-total").hide();
                        getPenetracaoCliente("r7-leves","6");
                        getPenetracaoCliente("r7-medios","2");
                        getPenetracaoCliente("r7-semipesados","3");
                        getPenetracaoCliente("r7-pesados","1");
                        getPenetracaoCliente("r7-total","todos");
                    }else{
                        $("#r7-leves-box").hide();
                        $("#r7-medios-box").hide();
                        $("#r7-semipesados-box").hide();
                        $("#r7-pesados-box").hide();
                        $("#r7-box-total").hide();
                         if(subSeg=="6") getPenetracaoCliente("r7-leves",subSeg);
                         if(subSeg=="2") getPenetracaoCliente("r7-medios",subSeg);
                         if(subSeg=="3") getPenetracaoCliente("r7-semipesados",subSeg);
                         if(subSeg=="1") getPenetracaoCliente("r7-pesados",subSeg);
                    }/*
                    else if(subSeg=="6"){
                        // leves
                        $("#r7-box-leves").show();
                        $("#r7-box-medios").hide();
                        $("#r7-box-semipesados").hide();
                        $("#r7-box-pesados").hide();
                        getPenetracaoCliente("r7-leves","6");
                    }else if(subSeg=="2"){
                        // medios
                        $("#r7-box-leves").hide();
                        $("#r7-box-medios").show();
                        $("#r7-box-semipesados").hide();
                        $("#r7-box-pesados").hide();
                        getPenetracaoCliente("r7-medios","2");
                    }else if(subSeg=="3"){
                        // semipesados
                        $("#r7-box-leves").hide();
                        $("#r7-box-medios").hide();
                        $("#r7-box-semipesados").show();
                        $("#r7-box-pesados").hide();
                        getPenetracaoCliente("r7-semipesados","3");
                    }else if(subSeg=="1"){
                        // pesados
                        $("#r7-leves-box").hide();
                        $("#r7-medios-box").hide();
                        $("#r7-semipesados-box").hide();
                        $("#r7-pesados-box").show();
                        getPenetracaoCliente("r7-pesados","1");
                    }
                    */
                }
                //--> function plot getPenetracaoCliente
		function getPenetracaoCliente(plotID, subSeg){
				//var url = "DashboardBoletim?penetracaoCliente=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val());
				var seriesLabel=[];
				var seriesDataTotal=[];
                                var seriesData=[];
                                var series = [];
                                var colors = ["lightsteelblue","lightcoral","lightskyblue"];
                                $("#"+plotID).html("<div class=\"pre-loader\">&nbsp;</div>");
                                var url = "DashboardBoletim?penetracaoCliente=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+subSeg+"&regional="+encodeURIComponent($('#regional').val());
				// leves
                                $.getJSON(url, function (data) {
					if (data && data.length) {
						$.each( data, function( key, val ) {
                                                        series.push({label:'Não Penetrados',data:val.clientes, color: colors[key]});
                                                        series.push({label:'Penetrados',data:val.penetracao, color: colors[1]});
                                                       
                                                  });
                                             var sum=0;
                                              // leves
                                              for(var i=0;i<series.length;i++){
                                                  if(series[i].data===undefined){
                                                      series[i].data=0;
                                                  }
                                                 // console.log("*"+series[i].data+"*");
                                                  sum+=parseInt(series[i].data);
                                              }
                                              //console.log("#"+plotID+"---TOTAL:"+sum);
                                              if(sum==0){
                                                  $("#"+plotID).hide();
                                                  $("#"+plotID+"-box").hide();
                                              }else{
                                                  $("#"+plotID+"-box").show();
                                                  $("#"+plotID).show();
                                                  //console.log(series);
                                                   plotPie("#"+plotID,series);
                                                   series=null;
                                                }
                                              
                                            
                                        }else{
                                            // não ha dados
                                            var msg= "NÃO HÁ DADOS";
                                            // $("#row7-penetracao-leves").html(msg); 
                                             $("#"+plotID).html(msg);
                                        }
                                    }); 
		}
		//<-- function plot getPenetracaoCliente 
                
		//<-- function plot Vertical Bar 1
		function componentTable(id, config, url){
                    var strTitle= config.title;
                    var strColor = config.color;
                    var strSubSegment = config.subSegment;
                    var strUrl = url+"&sub_seg="+strSubSegment;
                    
                    var strTable=""; 
                                                strTable+="<div class=\"table-responsive table-module\">"+
                                                    "<table class=\"table table-striped responsive responsive-table "+strColor+"\" id=\"topClienteChassiMontadora\" data-min=\"7\" data-max=\"8\" >"+
                                                        "<caption></caption>"+
                                                        "<thead>"+
                                                            "<tr><th colspan=\"3\" scope=\"colgroup\">"+strTitle+"</th></tr>"+
                                                            "<tr>"+
                                                                "<th scope=\"col\" style=\"width: 50%;\">MODELO</th>"+
                                                                "<th scope=\"col\" style=\"width: 25%;\">TOTAL</th>"+
                                                                "<th scope=\"col\" style=\"width: 25%;\">%</th>"+
                                                            "</tr>"+    
                                                        "</thead>"+
                                                        "<tbody>";
                                                // executando o webservice
                                                var strRow="";
                                                var totals="";
                                                /*
                                                $.getJSON(strUrl, function (data) {
                                                    if (data && data.length) {

                                                            $.each( data, function( key, val ) {
                                                                totals = val.totals;
                                                                strRow+="<tr>"+
                                                                    "<td scope=\"row\">"+val.modelo+"</td>"+
                                                                    "<td data-title=\"TOTAL\" data-type=\"currency\">"+val.total+"</td>"+
                                                                    "<td data-title=\"MARKET SHARE %\" data-type=\"percentual\">"+val.total_perc+"%</td>"+
                                                                "</tr>";
                                                                           
                                                                    
                                                            });
                                                           
                                                            strTable+=strRow;
                                                           strTable+="</tbody>"+
                                                       "<tfoot>"+
                                                            "<tr  scope=\"row\">"+
                                                                "<td>Total no Segmento</td>"+
                                                                "<td  data-title=\"TOTAL\">"+totals+"</td>"+
                                                                "<td  data-title=\"MARKET SHARE %\">100%</td>"+
                                                            "</tr>"+
                                                       "</tfoot>"+
                                                    "</table>"+
                                                "</div>";   
                                                    $("#"+id).html($("#"+id).html()+strTable);
                                                    responsiveTables();
                                                    }
                                                });*/
                                                $.ajax({ 
                                                    url: strUrl, 
                                                    dataType: 'json', 
                                                    async: false, 
                                                    success: function(data){ 
                                                        //Proceso de los datos recibidos
                                                        if (data && data.length) {
                                                                $.each( data, function( key, val ) {
                                                                    totals = val.totals;
                                                                    strRow+="<tr>"+
                                                                        "<td scope=\"row\">"+val.modelo+"</td>"+
                                                                        "<td data-title=\"TOTAL\" data-type=\"currency\">"+val.total+"</td>"+
                                                                        "<td data-title=\"MARKET SHARE %\" data-type=\"percentual\">"+val.total_perc+"%</td>"+
                                                                    "</tr>";


                                                                });

                                                                strTable+=strRow;
                                                               strTable+="</tbody>"+
                                                           "<tfoot>"+
                                                                "<tr  scope=\"row\">"+
                                                                    "<td>Total no Segmento</td>"+
                                                                    "<td  data-title=\"TOTAL\">"+totals+"</td>"+
                                                                    "<td  data-title=\"MARKET SHARE %\">100%</td>"+
                                                                "</tr>"+
                                                           "</tfoot>"+
                                                        "</table>"+
                                                    "</div>";   
                                                        $("#"+id).html($("#"+id).html()+strTable);
                                                      //  responsiveTables();
                                                        }
                                                    } 
                                                });
                                               // console.log(strRow);             
                                                
                    
                }
                //<-- function component Table 2 colunas
		function componentTable2Columns(id, config, url){
                    var strTitle= config.title;
                    var strColor = config.color;
                    var strSubSegment = config.subSegment;
                    var strUrl = url+"&sub_seg="+strSubSegment;
                    
                    var strTable=""; 
                                                strTable+="<div class=\"table-responsive table-module\">"+
                                                    "<table class=\"table table-striped responsive responsive-table "+strColor+"\" id=\"topClienteChassiMontadora\" data-min=\"7\" data-max=\"8\" >"+
                                                        "<caption></caption>"+
                                                        "<thead>"+
                                                            "<tr><th colspan=\"3\" scope=\"colgroup\">"+strTitle+"</th></tr>"+
                                                            "<tr>"+
                                                                "<th colspan=\"2\" scope=\"col\" style=\"width: 70%;\">ÁREA OPERACIONAL</th>"+
                                                                "<th scope=\"col\" style=\"width: 25%;\">%</th>"+
                                                            "</tr>"+    
                                                        "</thead>"+
                                                        "<tbody>";
                                                // executando o webservice
                                                var strRow="";
                                                var totals="";
                                               
                                                $.ajax({ 
                                                    url: strUrl, 
                                                    dataType: 'json', 
                                                    async: false, 
                                                    success: function(data){ 
                                                        //Proceso de los datos recibidos
                                                        if (data && data.length) {
                                                               var posicao = "";
                                                                $.each( data, function( key, val ) {
                                                                    posicao = val.pos;
                                                                    totals = val.totals;
                                                                    strRow+="<tr>"+
                                                                        "<td colspan=\"2\" scope=\"row\">"+val.modelo+"</td>"+
                                                                        //"<td data-title=\"TOTAL\" data-type=\"currency\">"+val.total+"</td>"+
                                                                        "<td data-title=\"MARKET SHARE %\" data-type=\"percentual\">"+val.total_perc+"%</td>"+
                                                                    "</tr>";


                                                                });

                                                                strTable+=strRow;
                                                               strTable+="</tbody>"+
                                                           "<tfoot>"+
                                                                "<tr  scope=\"row\">"+
                                                                    "<td colspan=\"2\">Posição da AOP no Segmento</td>"+
                                                                   // "<td  data-title=\"TOTAL\">"+totals+"</td>"+
                                                                    "<td  data-title=\"MARKET SHARE %\">"+posicao+"</td>"+
                                                                "</tr>"+
                                                           "</tfoot>"+
                                                        "</table>"+
                                                    "</div>";   
                                                        $("#"+id).html($("#"+id).html()+strTable);
                                                       // responsiveTables();
                                                        }
                                                    } 
                                                });
                                               // console.log(strRow);             
                                                
                    
                }
		
                
		//--> function select Segmento
		function selectSeg(){
			
				var url = "DashboardServlet?selectSegPeriod=true&ini="+$('#ini').val()+"&fim="+$('#fim').val();
				var items =[];
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                 items.push("<option value=\"todos\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.segmento+"\" >"+val.descricao+"</option>");
						  });
						  $("#segmento").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#segmento").html("<option value=\""+00+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
				
		}
		//<-- function select Segmento
                //--> function select Gamma
		function selectGamma(){
			
				var url = "DashboardServlet?selectGamma=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&sub_seg="+$('#sub_seg').val();
				var items =[];
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                items.push("<option value=\"todos\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.subseg+"\" >"+val.subsegmento+"</option>");
						  });
						  $("#sub_seg").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#sub_seg").html("<option value=\""+"todos"+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
                                
                          selectSegGamma();      
				
		}
		//<-- function select Gamma
                //--> function select Gamma
		function selectSegGamma(){
			
				var url = "DashboardServlet?selectSegPeriodGamma=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&sub_seg="+$('#sub_seg').val();
				var items =[];
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                items.push("<option value=\"todos\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.segmento+"\" >"+val.descricao+"</option>");
						  });
						  $("#segmento").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#segmento").html("<option value=\""+00+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
                                
                          selectRegional();      
				
		}
		//<-- function select Gamma
                //--> function select selectAreaOperRegional
		function selectAreaOperRegional(){
			
				var url = "DashboardServlet?selectAreaOperRegional=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val())+"&trash=true";
				var items =[];
                                console.log(url);
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                 items.push("<option value=\""+"todos"+"\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.area_operacional+"\" >"+val.descricao+"</option>");
						  });
						  $("#area_oper").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#area_oper").html("<option value=\""+00+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
				
		}
		//<-- function select selectAreaOperRegional
                //--> function select selectAreaOper
		function selectAreaOper(){
			
				var url = "DashboardServlet?selectAreaOper=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val())+"&trash=true";
				var items =[];
                                //console.log(url);
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                 items.push("<option value=\""+"Todos"+"\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.area_operacional+"\" >"+val.descricao+"</option>");
						  });
						  $("#area_oper").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#area_oper").html("<option value=\""+00+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
				
		}
		//<-- function select selectAreaOper
                
                //--> function select selectRegional
		function selectRegional(){
			
				var url = "DashboardServlet?selectRegionalPeriod=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val())+"&trash=true";
				var items =[];
                               // console.log(url);
				$.getJSON(url, function (data) {
					if (data && data.length) {
                                                 items.push("<option value=\""+"todos"+"\" >"+"TODOS"+"</option>");
						 $.each( data, function( key, val ) {
							  items.push("<option value=\""+val.regiao_mbb+"\" >"+val.regiao_mbb+"</option>");
						  });
						  $("#regional").html(items);
                                                // executando o dashboard  
						//dashboard();
					}else{
                                            // não há dados para o perído
                                            $("#regional").html("<option value=\""+00+"\" >"+"Ñ há dados p/ o período."+"</option>");
                                        }
				});
                            
                            
                              checkAreaOper();
				
		}
		//<-- function select selectRegional
                
                function checkAreaOper(){
                    if($('#regional').val()=="todos"){
                                setTimeout(function(){
                                    selectAreaOper(); 
                                }, 1000); 
                            }else{
                                setTimeout(function(){
                                    selectAreaOperRegional(); 
                                }, 1000); 
                            }
                }
		function labelFormatter(label, series) {
			// series is the series object for the label
			// show object property
			//console.log(Object.getOwnPropertyNames(series).sort());
			// Logging property names and values using Array.forEach
			//Object.getOwnPropertyNames(series).forEach(function(val, idx, array) {
			//  console.log(val + ' -> ' + series[val]);
			//});
			var str = "";
				str =  series.data;
			var res = str[0][1];
			//series.color
			
			return "<div style='min-width: 60px; display:block; font-size:8pt;font-weight:bold; text-align:center; padding:2px; color: black"+";'>" + label + "<br/>" + res+" | "+series.percent.toFixed(0)+  "%</div>";
			
		}
                
                 //--> function downloadPdf
              function downloadPDF(){
                    //var url = "DownloadBoletimPdf?&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&key="+"<%=Utils.toHexString((Preferences.SECURITY_KEY+memberId).getBytes())%>"+"&report=pdf";
                    var url =   "DownloadBoletimPdf?ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val()+"&area_oper="+$('#area_oper').val()+"&sub_seg="+$('#sub_seg').val()+"&regional="+encodeURIComponent($('#regional').val())+"&key="+"<%=Utils.toHexString((Preferences.SECURITY_KEY+memberId).getBytes())%>"+"&report=pdf"+"&trash=true";
                    $("#progress-report-title").html("Geração de Arquivo PDF");  
                    $("#progress-report").html("Aguarde, processando...");     
                    console.log("starting..."+url);
                    document.location.href = url;
                    $("#progress-report").html("Quando finalizado, o arquivo estará disponível na área de download do seu navegador.");    
              }
              //<-- function downloadPdf
              
    </script>


</body>

</html>

