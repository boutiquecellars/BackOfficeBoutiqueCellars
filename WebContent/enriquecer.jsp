<%@page import="br.com.itfox.beans.CollectionColumns"%>
<%@page import="java.util.List"%>
<%@page import="br.com.itfox.business.BusinessDelegate"%>
<%@page import="java.util.Calendar"%>
<%@page import="br.com.itfox.config.Preferences"%>
<%@include file="incProfileAdmin.jsp" %>
<%
Member m = new Member();
if(session.getAttribute("userid")!=null){
    m = (Member) session.getAttribute("member");
}
%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><%=Preferences.title%></title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    
     <link href="css/plugins/chartist/chartist.min.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- date picker -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <!-- custom pagination -->
<!--
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/s/dt/jszip-2.5.0,pdfmake-0.1.18,dt-1.10.10,af-2.1.0,b-1.1.0,b-colvis-1.1.0,b-flash-1.1.0,b-html5-1.1.0,b-print-1.1.0,cr-1.3.0,fc-3.2.0,fh-3.1.0,kt-2.1.0,r-2.0.0,rr-1.1.0,sc-1.4.0,se-1.1.0/datatables.min.css"/>
 -->


    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://www.datatables.net/release-datatables/extensions/TableTools/css/dataTables.tableTools.css">
    <!-- responsive lightbox & dialog script -->
    <link rel="stylesheet" href="css/plugins/magnific-popup/magnific-popup.css">
    
    <style>
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
            width:200px;
            height: 200px;
            position: absolute;
            margin-top: -40px;
            margin-left: 27px;
        }
        .flot-chart-table{
            height: 150px;
        }
        div#flot-bar-chart.flot-chart-content{
            padding: 0px;
            margin-left: 93px;
            position: relative;
            width: 90%;
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
	.widget-one{
		background-color: #5576a0;
		color: #ffffff;
	}
	.widget-two{
		background-color: #60b6e2;
		color: #ffffff;
	}
	.widget-tree{
		background-color: #afbed0;
		color: #ffffff;
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
	
		.ct-legend {
    position: relative;
    z-index: 10;

    li {
        position: relative;
        padding-left: 23px;
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

    <%="@for $i from 0 to length($ct-series-colors) {"%>
        <%=".ct-series-#{$i}:before {"%>
            <%="background-color: nth($ct-series-colors, $i + 1);"%>
            <%="border-color: nth($ct-series-colors, $i + 1);"%>
        	<%="}"%>
		<%="}"%>
	<%="}"%>
	
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

    <jsp:include page="incNavSide.jsp" >
     <jsp:param name="page" value="enriquecer" />
    </jsp:include>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
             <jsp:include page="incNavbarStaticTop.jsp" />
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="text-center m-t-lg">
                        <h1>
                            <%=Preferences.h1%>
                        </h1>
                        <small >
                           Gerar arquivos para enriquecer.
                           <br/><br/><br/><br/>
                        </small>
                    </div>
                    <!-- dialog itself, mfp-hide class is required to make dialog hidden -->
                    <div id="small-dialog" class="zoom-anim-dialog mfp-hide">
                        <h1>Arquivos para Enriquecer</h1>
                        <p id="progress-report">Lista de arquivos para enriquecer.</p>
                    </div>
                </div>
            </div>
            <!-- filter -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="filter">
                      <ul>
                        <li  class="fieldset">
                            <fieldset>
                                <legend>PerÌodo</legend>
                                <span>de</span>
                        	<label class="user">
                                    <input type="text" id="ini" name="ini" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Inicial"> 
                            </label> 
                            <span>atÈ</span>
                            <label class="pass"> 
                            	<input type="text" id="fim" name="fim" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Final"> 
                            </label>
                            </fieldset>
                            <!--
                            <fieldset><span>de</span>
                        	<label class="user">
                        		<input type="text" id="ini" name="ini" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Inicial"> 
                            </label> 
                            <span>atÈ</span>
                            <label class="pass"> 
                            	<input type="text" id="fim" name="fim" class="inputText cor9" readonly="readonly"  contenteditable="false" placeholder="dd/mm/aaaa" title="Insira a Data Final"> 
                            </label> 
                            -->
                            
                            <fieldset>
                                <legend>Segmento</legend>
                            <label class="user">
                            	<select id="segmento" name="segmento" class="form-control m-b" title="Selecione um Segmento">
                                	<option value="todos">Todos os Segmentos</option>
                                        <%
                                        String areaOper = request.getParameter("area_oper");
                                        String seg      = request.getParameter("seg");
                                        String ini      = request.getParameter("ini");
                                        String fim      = request.getParameter("fim");
                                        
                                        if(seg==null){
                                            seg="3";
                                        }
                                        List<CollectionColumns> segmentos = new BusinessDelegate().selectSegmentos();
                                        for(CollectionColumns s: segmentos){
                                        %>
                                        <option value="<%=s.getColumns()[0]%>" <% if(seg!=null && !seg.isEmpty() && seg.length()>0){if(s.getColumns()[0].equalsIgnoreCase(seg)){out.print("selected=\"selected\"");}else{out.print("");}} %> ><%=s.getColumns()[1]%></option>
                                        <%
                                        }
                                        %>
                                </select>
                            </label>
                            </fieldset>
                            
                            <!--
                            <fieldset>
                                <legend>Segmento</legend>
                                <label class="user">
                                    <select id="segmento" name="segmento"  class="form-control m-b" title="Selecione um Segmento">
                                            <option value="todos">Todos os Segmentos</option>
                                        <option value="3">Caminhıes</option>
                                        <option value="4">Onibus</option>
                                        <option value="2">Vans</option>
                                    </select>
                                </label>
                            </fieldset>
                            -->
                            <!--
                            <label class="user">
                            	<select id="area" name="area" class="inputText cor9" title="Selecione uma √°rea operacional">
                                	<option value="Todas">√Årea Operacional</option>
                                </select>
                            </label>
                            -->
                            <!--
                             </fieldset>
                            -->
                          </li>
                        <!-- <li><a>marca</a></li> -->
                        <li class="fieldset">
                        	<fieldset>
                                    <legend>Exibir na Tela</legend>
                        		<input type="submit" class="btn btn-primary block full-width m-b" value="Visualizar" onClick="dashboard();">
                            </fieldset>
                        </li>
                        <li class="fieldset">
                        	<fieldset>
                                    <legend>Download</legend>
                        		<input type="submit" class="btn btn-primary block full-width m-b popup-with-move-anim" value="Baixar arquivos para enriquecer" onClick="downloadEnriquecer();"  href="#small-dialog">
                            </fieldset>
                        </li>
                        <!--
                         ***** use this for responsive modal example ******
                        <li>
                        	<a class="popup-with-zoom-anim" href="#small-dialog">Open zoom</a><br>
							<a class="popup-with-move-anim" href="#small-dialog">Open fade</a>
                        </li>
                       -->
                      </ul>
                    </div>
                </div>
            </div>
            <!-- widgets -->
            
            <!--// widgets -->
            
            <!-- table -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Arquivos para enriquecer </h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#">Config option 1</a>
                                    </li>
                                    <li><a href="#">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <!--
                            <div class="row">
                                <div class="col-sm-5 m-b-xs"><select class="input-sm form-control input-s-sm inline">
                                    <option value="0">Option 1</option>
                                    <option value="1">Option 2</option>
                                    <option value="2">Option 3</option>
                                    <option value="3">Option 4</option>
                                </select>
                                </div>
                                <div class="col-sm-4 m-b-xs">
                                    <div data-toggle="buttons" class="btn-group">
                                        <label class="btn btn-sm btn-white"> <input type="radio" id="option1" name="options"> Day </label>
                                        <label class="btn btn-sm btn-white active"> <input type="radio" id="option2" name="options"> Week </label>
                                        <label class="btn btn-sm btn-white"> <input type="radio" id="option3" name="options"> Month </label>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="input-group"><input type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> Go!</button> </span></div>
                                </div>
                            </div>
                            -->
                            <div class="table-responsive">
                                <table id="dados" class="table table-striped">
                                    <thead>
                                    <tr id="enriquecerHead">
                                        
                                    </tr>
                                    </thead>
                                    <tbody id="enriquecerBody">
                                    
                                    
                                    </tbody>
                                </table>
                                <div class="info"></div>
                                <table id="example" class="display" width="100%">
                                	<thead>
                                        <tr>
                                            <th>Data</th>
                                            <th>Chassi</th>
                                            <th>Placa</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Data</th>
                                            <th>Chassi</th>
                                            <th>Placa</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
            
            <!--// table -->
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

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- datepicker -->
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

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
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/plugins/flot/jquery.flot.time.js"></script>


<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.js"></script>

<!-- Custom pagination -->
<!-- 
http://datatables.net/
-->
<!--
<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
-->
<!-- Table Tools -->
<!--
<script src="https://www.datatables.net/release-datatables/media/js/jquery.js"></script> -->

<script src="https://www.datatables.net/release-datatables/media/js/jquery.dataTables.js"></script>

<script src="https://www.datatables.net/release-datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>

<!--
<script type="text/javascript" src="https://cdn.datatables.net/s/dt/jszip-2.5.0,pdfmake-0.1.18,dt-1.10.10,af-2.1.0,b-1.1.0,b-colvis-1.1.0,b-flash-1.1.0,b-html5-1.1.0,b-print-1.1.0,cr-1.3.0,fc-3.2.0,fh-3.1.0,kt-2.1.0,r-2.0.0,rr-1.1.0,sc-1.4.0,se-1.1.0/datatables.min.js"></script>
-->
<!-- Magnific Popup - Lightbox & dialog modal -->
<script src="js/plugins/magnific-popup/magnific-popup.1.0.0.js"></script>
<!-- http://dimsemenov.com/plugins/magnific-popup/ -->
 <script>
        $(document).ready(function() {
            
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
                                    minDate: new Date(<%=year%>,<%=month%>,<%=day%>),
                                    maxDate:1,
                                    dayNames: ['Domingo','Segunda','TerÁa','Quarta','Quinta','Sexta','S·bado'],
                                    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S·b','Dom'],
                                    monthNames: ['Janeiro','Fevereiro','MarÁo','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                                    nextText: 'PrÛximo',
                                    prevText: 'Anterior'
				});
			 $( "#ini" ).datepicker( "option", "dateFormat", "dd/mm/yy" );	
			 
			 $( "#fim" ).datepicker({
				    changeMonth: true,
				    changeYear: true,
                                    dateFormat: 'dd/mm/yy',
                                    minDate: new Date(<%=year%>,<%=month%>,<%=day%>),
                                    maxDate:1,
                                    dayNames: ['Domingo','Segunda','TerÁa','Quarta','Quinta','Sexta','S·bado'],
                                    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S·b','Dom'],
                                    monthNames: ['Janeiro','Fevereiro','MarÁo','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                                    nextText: 'PrÛximo',
                                    prevText: 'Anterior'
				});
			$( "#fim" ).datepicker( "option", "dateFormat", "dd/mm/yy" );	
                        // data atual
			var date = new Date();
			date.setDate(1);
			$( "#ini" ).val($.datepicker.formatDate('dd/mm/yy', date));
			$( "#fim" ).val($.datepicker.formatDate('dd/mm/yy', new Date()));    
			// exibindo os segmentos
			//selectSeg();
                        $('#example').hide();
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
			 
                
                //--> function plot Pie Perfil Cliente
		
                //<-- function plot Pie Perfil Cliente
                
		function dashboard(){
	         selectTopCliente();
                        
		}
                function downloadEnriquecer(){
                    $("#progress-report").html("Aguarde, processando...");     
                    var url = "DashboardServlet?downloadEnriquecerArquivos=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val();
		//console.log(url);
				$.getJSON(url, function (result) {
                                    
					if (result && result.length) {
						var total = [];
						  $.each( result, function( key, val ) {
							  
							  total.push(val.gics);
						  });
						 
                                                 $("#progress-report").html("Total de Arquivos para Enriquecer:"+total); 
                                                 if(eval(total)>0){
                                                     document.location.href ="DownloadReportEnriquecer";
                                                 }
                                                 //alert(total);
						 // $("#periodoIni").html(periodoIni);
						 // $("#periodoFim").html(periodoFim);
					}
				});
                }
                function generateReportEnriquecimento(){
                $("#progress-report").html("Aguarde, processando...");     
		var url = "DashboardServlet?selectGic=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val();
		//console.log(url);
				$.getJSON(url, function (result) {
                                    
					if (result && result.length) {
						var total = [];
						  $.each( result, function( key, val ) {
							  
							  total.push(val.gics);
						  });
						 
                                                 $("#progress-report").html("Total Enriquecido:"+total); 
                                                 if(eval(total)>0){
                                                     document.location.href ="DownloadReportEnriquecido";
                                                 }
                                                 //alert(total);
						 // $("#periodoIni").html(periodoIni);
						 // $("#periodoFim").html(periodoFim);
					}
				});
		
                }
                function returnStyle(val){
                   /*
                    if(eval(val)>0){
                        return "txt-fill";
                    }else{
                        return "";
                    }*/
                   return "";
                        
                }
                //--> selectTopCliente
                function selectTopCliente(){
                    var url = "DashboardServlet?selectEnriquecerArquivos=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val();
                    var seriesLabel=[];
                    var seriesDataTotal=[];
                    var seriesDataClient=[];
                    var seriesDataAvg=[];
                    var tableHeadCaminhoes="<th class=\"txt-left\">Nome Proprietario</th><th>VW</th><th>MBENZ</th><th>FORD</th><th>VOLVO</th><th>IVECO</th><th>SCANIA</th><th>OUTROS</th><th>TOTAL</th>";
                    var tableHeadVans="<th class=\"txt-left\">Nome Proprietario</th><th>RENAULT</th><th>FIAT</th><th>MBENZ</th><th>IVECO</th><th>PEUGEOT</th><th>CITROEN</th><th>OUTROS</th><th>TOTAL</th>";
                    var tableHeadOnibus="<th class=\"txt-left\">Nome Proprietario</th><th>MBENZ</th><th>VW</th><th>AGRALE</th><th>IVECO</th><th>VOLVO</th><th>SCANIA</th><th>OUTROS</th><th>TOTAL</th>";
                    var tableHead="<th class=\"txt-left\">Data</th><th>Chassi</th><th>Placa</th>";
                    var col1="";
                    var tableRowTotal="<tr><td><img src=\"img/icon-chassis.png\" width=\"63px\" height=\"16px\"></td>";
                    var tableRowClient="<tr><td><img src=\"img/icon-clientes.png\" width=\"63px\" height=\"16px\"></td>";
                    var tableRowAvg="<tr><td><img src=\"img/icon-media.png\" width=\"63px\" height=\"16px\"></td>";
                    
                   /*
                                
				$.getJSON(url, function (result) {
					if (result && result.length) {
						// percorrendo o array json
						$.each( result, function( key, val ) {
							//seriesLabel.push([val.i,""]);
							//seriesDataTotal.push([val.i,val.total]);
                                                        //seriesDataClient.push([val.i,(val.pf+val.pj)]);
                                                        //seriesDataAvg.push([val.i,(val.total/(val.pf+val.pj)).toFixed(2)]);
                                                        // add heads to columns
                                                       // tableHead+="<th>"+val.C_NOMEPROPRIETARIO+"</th>";
                                                        col1+="<tr>";
                                                        col1+="     <td class=\"txt-left\">"+val.col0+"</td>";
                                                        col1+="     <td class=\"txt-center "+returnStyle(val.col1)+"\">"+val.col1+"</td>";
                                                        col1+="     <td class=\"txt-center "+returnStyle(val.col2)+"\">"+val.col2+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col3)+"\">"+val.col3+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col4)+"\">"+val.col4+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col5)+"\">"+val.col5+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col6)+"\">"+val.col6+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col7)+"\">"+val.col7+"</td>";
                                                        //col1+="     <td class=\"txt-center "+returnStyle(val.col8)+"\">"+val.col8+"</td>";
                                                        col1+="</tr>";
                                                        //tableRowTotal+="<td>"+val.VW+"</td>";
                                                        //tableRowClient+="<td>"+(val.pf+val.pj)+"</td>";
                                                        //tableRowAvg+="<td>"+((val.total/(val.pf+val.pj)).toFixed(2))+"</td>";
						});
                                                
                                                //tableRowTotal+="</tr>";
                                                //tableRowClient+="</tr>";
                                                //tableRowAvg+="</tr>";
                                               // ---> exibindo os dados na tela
											   // $("#enriquecerHead").html(tableHead);
                                               // $("#enriquecerBody").html(col1);
												var dataSet = {"data":result}
												//console.log(data);
												//paginatorTables();
                                                //$("#flot-bar-chart-data").html(tableRowTotal+tableRowClient+tableRowAvg);
						 // $("#periodoIni").html(periodoIni);
						 // $("#periodoFim").html(periodoFim);
					}
				});
				*/
				paginatorTables();
		}
                //<-- selectTopCliente
		
		function paginatorTables(){
			if(typeof table != "undefined"){
				table.destroy();
			}
                        $('#example').show();
			 table = $('#example').DataTable( {
                              "language": {
                                    "url": 'http://cdn.datatables.net/plug-ins/1.10.10/i18n/Portuguese-Brasil.json'
                                },
				"ajax": {
					"url": "DashboardServlet?selectEnriquecerArquivos=true&ini="+$('#ini').val()+"&fim="+$('#fim').val()+"&seg="+$('#segmento').val(),
					"dataSrc": ""
				},
				"columns": [
					{ "data": "col0" },
					{ "data": "col1" },
					{ "data": "col2" }
					]
			} );
             // table tools
		       //table = $('#example').DataTable();
			//var tt = new $.fn.dataTable.TableTools( table );
                        //$( tt.fnContainer() ).insertAfter('div.info');
                        
                        
                         //document.location.href ="DownloadReportEnriquecer";
                                                 
		}
		
    </script>


</body>

</html>
