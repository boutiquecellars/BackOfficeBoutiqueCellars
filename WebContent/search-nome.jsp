<%@page import="br.com.itfox.beans.Member"%>
<%@page import="br.com.itfox.config.Preferences"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><%=Preferences.title%></title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    
    
     <link href="css/plugins/chartist/chartist.min.css" rel="stylesheet">
    <link href="css/plugins/slick/slick.css" rel="stylesheet">
    <link href="css/plugins/slick/slick-theme.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/itfoxSearchCnpj.css" rel="stylesheet">
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
        /*** filtro ***/
        .filter{
            height:43px;
        }
        .filter ul li fieldset {
	/*padding:12px 20px 0 6px;*/
        margin:7px 10px 0px 0px;
        float:left;
        
}
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
            height: auto !important;
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

   <%=" @for $i from 0 to length($ct-series-colors) {"%>
        <%=".ct-series-#{$i}:before {"%>
        <%="    background-color: nth($ct-series-colors, $i + 1);"%>
        <%="    border-color: nth($ct-series-colors, $i + 1);"%>
        <%="	} "%>
	<%="	}"%>
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
     <jsp:param name="page" value="search-nome" />
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
                           Buscar por NOME.
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
                        <li><fieldset><span>NOME</span>
                        	<label class="user">
                                    <input type="text" id="keyword" name="keyword" class="inputText cor9" placeholder="Digite o NOME" title="Insira o NOME" value="<% if(request.getParameter("keyword")!=null){out.print(request.getParameter("keyword"));}%>" style="width:100%"> 
                            </label> 
                            
                            <!--
                            <label class="user">
                            	<select id="area" name="area" class="inputText cor9" title="Selecione uma área operacional">
                                	<option value="Todas">Área Operacional</option>
                                </select>
                            </label>
                            -->
                             </fieldset>
                          </li>
                        <!-- <li><a>marca</a></li> -->
                        <li>
                        	<fieldset>
                        		<input type="submit" class="btn btn-primary block full-width m-b" value="Buscar por NOME" onClick="dashboard();">
                            </fieldset>
                        </li>
                        <%
                        if(session.getAttribute("member")!=null){
                            Member m = (Member) session.getAttribute("member");
                            if(m.getPermission()!=null && !m.getPermission().isEmpty() && m.getPermission().equalsIgnoreCase("admin")){
                                %>
                               <!--
                                <li>
                                    <fieldset>
                                                <input type="submit" class="btn btn-primary block full-width m-b" value="Buscar Lote de CNPJ" onClick="batchDashboard();">
                                    </fieldset>
                                </li>
                               -->
                                <%
                            }else{
                                %>
                                
                                <%
                            }
                        }
                        %>
                        
                        
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
             <div class="row">
                 <div class="col-lg-12">
                     <table id="example" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>NOME</th>
                                <th>CPF/CNPJ</th>
                                
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>NOME</th>
                                <th>CPF/CNPJ</th>
                                
                            </tr>
                        </tfoot>
                    </table>
                 </div>
             </div>
            <!--// widgets -->
            <!-- result master detail -->
            
            <!-- // result master detail -->
           <!-- horizontal bars -->
           
           
            <!--// horizontal bars -->
           
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
https://datatables.net/
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
<!-- https://dimsemenov.com/plugins/magnific-popup/ -->

<!-- slick carousel-->
<script src="js/plugins/slick/slick.min.js"></script>

<!-- ITFOX Magic -->
<script type="text/javascript" charset="iso-8859-1" language="javascript"  src="js/searchCnpjCharts.js" ></script>
 <script>
        $(document).ready(function() {
                        
                        //$('.product-images').slick({
                        //    dots: true
                       // });
            
			// formatando o date picker for inicial date 
			   
			// exibindo os segmentos
			//selectSeg();
                        $('#result').hide();
                        $('#grapha').hide();
                        $('#graphb').hide();
                        $('#box_vinculado').hide();
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
                        
                        // evento on key
                        $('#keyword').on('input', function() {
                            dashboard();
                        });
                        
                        if( $('#keyword').val().length>=11){
                            dashboard();
                        }
                        
                        $('#example').hide();
        
             });
			 
                
                //--> function plot Pie Perfil Cliente
		
                //<-- function plot Pie Perfil Cliente
                
		function dashboard(){
	         //selectTopCliente();
                 //$("#result").hide();
                 paginatorTables();
                 //dashboardSearch();       
		}
                
                function batchDashboard(){
	         //selectTopCliente();
                 paginatorTablesBatch();
                        
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
                
		
		function paginatorTables(){
                        var url = "DashboardServlet?searchNomeProprietario=true&keyword="+$('#keyword').val();
                        
                        if(typeof table != "undefined"){
				table.destroy();
			}
                        $('#example').show();
			 table = $('#example').DataTable( {
                                "processing": false,
                                "serverSide": false,
                                "language": {
                                    "url": 'https://cdn.datatables.net/plug-ins/1.10.10/i18n/Portuguese-Brasil.json'
                                },
                                "ajax": url,
				"columns": [
					{ "data": "nome" },
					{ "data": "cpfcnpj" }
					]
			} );
			 
		       
                                                 
		}
                function enviarEmail(obj){
                    
                     window.location.href = "mailto:"+obj;
                }
                function paginatorTablesBatch(){
			if(typeof table != "undefined"){
				table.destroy();
			}
                        $('#example').show();
			 table = $('#example').DataTable( {
                             "language": {
                                    "url": 'https://cdn.datatables.net/plug-ins/1.10.10/i18n/Portuguese-Brasil.json'
                                },
				"ajax": {
					"url": "DashboardServlet?selectGicChassiBatch=true&keyword="+$('#keyword').val(),
					"dataSrc": ""
				},
				"columns": [
					{ "data": "Data" },
					{ "data": "Chassi" },
					{ "data": "Placa" },
                                        { "data": "Nome" }
					]
			} );
            
                                                 
		}
		
    </script>


</body>

</html>
