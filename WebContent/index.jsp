<%@page import="java.util.Locale"%>
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
    <link href="css/fonts.css" rel="stylesheet" media="all">
    
    <!--
     <link href="css/plugins/chartist/chartist.min.css" rel="stylesheet" media="all">
    -->
     <link href="js/plugins/chartist-plugin/chartist.min.css" rel="stylesheet" media="all">

    <link href="css/animate.css" rel="stylesheet" media="all">
    <link href="css/style.css" rel="stylesheet" media="all">
    <!-- date picker -->
    
    <link href="js/plugins/jquery-ui-themes-1.11-2.4/themes/smoothness/jquery-ui.css" rel="stylesheet" media="all">
    <!--
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" media="all">
    -->
    <!-- responsive lightbox & dialog script -->
    <link rel="stylesheet" href="css/plugins/magnific-popup/magnific-popup.css" media="all">
     <!-- itfox css -->
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
        }
        /*** <-//media screen ***/
        /*** media screen ***/
        @media screen and (min-width: 768px) and (max-width: 809px) {
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
            }*/
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
                font-size:11px;
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
        @media screen and (min-width: 500px) and (max-width: 767px) {
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
             /**** Tabela ***/
            .table-responsive {
                font-size:10px;
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
                font-size:10px;
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
            .filter ul li input{
                height: 30px;
                width: 94px;
                font-size: 10px;
                font-weight: 100;
                margin: 0 0px 0 0px;
                padding: 0px;  
            }
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
                margin: 20px 0 0 10px;
                padding: 6px 0 0 !important;
                text-align: center;
                width: 250px;
            }
            .filter ul li fieldset {
                 padding: 12px 6px 0;
            }
            input.buttonSubmit{
                width:250px !important;
                margin: 0 10px 0 -10px !important;
                padding: 0 !important;
            }
            */
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
        .modal-dialog{
            width:50%;
            height:80%;
            background-color:#FFFFFF;
            overflow:scroll;
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
            height:300px;
        }
        .inputText{
            text-align:center;
        }
        /*
        .buttonSubmit{
            width:40px !important;
            margin: 0 0 10px 10px !important;
        }*/
        .txt-left{
            text-align: left;
        }
        .txt-center{
            text-align: center;
        }
        .txt-fill{
            color:#5576a0;;
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
    
    <!--
    <div class="modal fade" tabindex="-1" role="dialog" id="myModal2" aria-hidden="true" >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">PRINCIPAL COMPRADOR</h4>
            </div>
            <div class="modal-body" id="modal-body">
              <p>- y&hellip;</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
    </div>
    -->
    
    <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-sm">
            <div class="modal-content animated flipInY">
                <div class="modal-header">
                    <h4 class="modal-title">LISTA DE VEÍCULOS DOS PRINCIPAIS COMPRADORES</h4>
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Fechar</span></button>
                    
                </div>
               
                <div class="modal-body"  id="modal-body">
                    <h4 class="modal-title"></h4>
                    <small class="font-bold">Processando ...</small>
                    <div class="spiner-example">
                        <div class="sk-spinner sk-spinner-wave">
                            <div class="sk-rect1"></div>
                            <div class="sk-rect2"></div>
                            <div class="sk-rect3"></div>
                            <div class="sk-rect4"></div>
                            <div class="sk-rect5"></div>
                        </div>
                    </div>
                </div> 
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">Fechar</button>
                    <!--
                   <button type="button" class="btn btn-primary">Continuar</button> 
                    -->
                </div>
                
            </div>
        </div>
    </div>
   

<div id="wrapper">
     <%
   String paramPrint = request.getParameter("print");
   String printStyle = "";
   if(paramPrint==null){
   %>
    <jsp:include page="incNavSide.jsp">
        <jsp:param name="page" value="index" />
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
                    <div class="text-center m-t-lg">
                        <h1>
                            BOUTIQUE CELLARS WINE SELECTION
                            
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
 <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
-->

 <script src="js/plugins/jquery-ui-1.11-2.4/jquery-ui.js"></script>

 <!--
<script src="js/plugins/jquery-ui/jquery-ui.js"></script>
 -->
<!-- Chartist -->
<!--
 <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script> 
-->
 <script src="js/plugins/chartist-plugin/chartist.min.js"></script>
<!--
 <script src="js/plugins/chartist/chartist.min.js"></script>
-->
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
<script src="js/demo/flot-demo.js"></script>

<!-- Magnific Popup - Lightbox & dialog modal -->
<script src="js/plugins/magnific-popup/magnific-popup.1.0.0.js"></script>




</body>

</html>

