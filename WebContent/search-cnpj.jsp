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
     <jsp:param name="page" value="search-cnpj" />
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
                           Buscar CNPJ.
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
                        <li><fieldset><span>CNPJ</span>
                        	<label class="user">
                                    <input type="text" id="keyword" name="keyword" class="inputText cor9" placeholder="Digite o CNPJ" title="Insira o CNPJ" value="<% if(request.getParameter("keyword")!=null){out.print(request.getParameter("keyword"));}%>" style="width:100%"> 
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
                        		<input type="submit" class="btn btn-primary block full-width m-b" value="Buscar CNPJ" onClick="dashboard();">
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
            
            <!--// widgets -->
            <!-- result master detail -->
            <div class="row">
                
                <div class="col-lg-12">

                    <div class="ibox product-detail">
                        <div class="ibox-content" style="padding-bottom:0">

                            <div class="row">
                                <div class="col-md-5" style="display:none">

                                    <!--    
                                    <div class="product-images slick-initialized slick-slider" role="toolbar"><button type="button" data-role="none" class="slick-prev slick-arrow" aria-label="Previous" role="button" style="display: block;">Previous</button>
                                        <div aria-live="polite" class="slick-list draggable">
                                            <div class="slick-track" role="listbox" style="opacity: 1; width: 2135px; transform: translate3d(-427px, 0px, 0px);"><div class="slick-slide slick-cloned" data-slick-index="-1" aria-hidden="true" tabindex="-1" style="width: 427px;">
                                                <div class="image-imitation">
                                                    [IMAGE 3]
                                                </div>
                                                </div><div class="slick-slide slick-current slick-active" data-slick-index="0" aria-hidden="false" tabindex="0" role="option" aria-describedby="slick-slide00" style="width: 427px;">
                                                    <div class="image-imitation">
                                                        [IMAGE 1]
                                                    </div>
                                                </div><div class="slick-slide" data-slick-index="1" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide01" style="width: 427px;">
                                                    <div class="image-imitation">
                                                        [IMAGE 2]
                                                    </div>
                                                </div><div class="slick-slide" data-slick-index="2" aria-hidden="true" tabindex="-1" role="option" aria-describedby="slick-slide02" style="width: 427px;">
                                                    <div class="image-imitation">
                                                        [IMAGE 3]
                                                    </div>
                                                </div>
                                                <div class="slick-slide slick-cloned" data-slick-index="3" aria-hidden="true" tabindex="-1" style="width: 427px;">
                                                    <div class="image-imitation">
                                                        [IMAGE 1]
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" data-role="none" class="slick-next slick-arrow" aria-label="Next" role="button" style="display: block;">Next</button><ul class="slick-dots" role="tablist" style="display: block;"><li class="slick-active" aria-hidden="false" role="presentation" aria-selected="true" aria-controls="navigation00" id="slick-slide00"><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">1</button></li><li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation01" id="slick-slide01"><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">2</button></li><li aria-hidden="true" role="presentation" aria-selected="false" aria-controls="navigation02" id="slick-slide02"><button type="button" data-role="none" role="button" aria-required="false" tabindex="0">3</button></li></ul>
                                    </div>
                                    -->
                                    <div class="widget widget-one no-padding" style="height:420px">
                                        <div class="p-m">
                                            <h1 class="m-xs">147</h1>

                                            <h3 class="font-bold no-margins">
                                               Registros encontrados
                                            </h3>
                                            <small>Para todo o per�odo.</small>
                                        </div>
                                        <div class="flot-chart">
                                            <div class="flot-chart-content" id="flot-chart1" style="padding: 0px; position: relative;">
                                                <canvas class="flot-base" width="1194" height="200" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 597px; height: 100px;"></canvas>
                                                <canvas class="flot-overlay" width="1194" height="200" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 597px; height: 100px;"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-7" id="result">

                                    <h2 class="font-bold m-b-xs" id="nome">
                                        MINIST�RIO DA SA�DE
                                    </h2>
                                    <small> <span id="logradouro">ESP DOS MINISTERIOS BL G,</span> <span id="numero">11.</span> <span id="complemento">ANEXO A SL 420 A</span><br/><span id="bairro">ASA SUL</span> - <span id="cep">70.310-500</span> -  <span id="cidade">BRAS�LIA</span> - <span id="uf">DF</span></small>
                                    <div class="m-t-md" style="">
                                        <h2 class="product-main-price" ><span id="tel1">(61) 3701-1720</span> <small class="text-muted"><span id="email1">comercial@saude.gov.br</span></small> </h2>
                                        <h2 class="product-main-price" ><span id="tel2">(61) 3701-1720</span> <small class="text-muted"><span id="email2"></span></small> </h2>
                                        <h2 class="product-main-price" ><span id="tel3">(61) 3701-1720</span> <small class="text-muted"><span id="email3"></span></small> </h2>
                                    </div>
                                    
                                    <hr>

                                    <h4>Dados cadastrais</h4>

                                    <div class="small text-muted">
                                        TIPO: <span id="tipo">JUR�DICA</span> <br/>
                                        CNPJ: <span id="cnpj">00394544000851</span><br/>
                                        ATIVIDADE ECON�MICA: <span id="ativ"></span><br/>

                                        <br>
                                        <br>
                                       
                                    </div>
                                    <!--
                                    <dl class="small m-t-md">
                                        <dt>Description lists</dt>
                                        <dd>A description list is perfect for defining terms.</dd>
                                        <dt>Euismod</dt>
                                        <dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
                                        <dd>Donec id elit non mi porta gravida at eget metus.</dd>
                                        <dt>Malesuada porta</dt>
                                        <dd>Etiam porta sem malesuada magna mollis euismod.</dd>
                                    </dl>
                                    
                                    <hr>
                                    -->
<!--
                                    <div>
                                        <div class="btn-group">
                                            
                                            <button class="btn btn-primary btn-sm" onclick="location.href='tel:6137011720'" id="ligar"><i class="fa fa-phone"></i> Ligar para contato </button>
                                            
                                            <button class="btn btn-white btn-sm" onclick="location.href='mailto:comercial@saude.gov.br?subject=Quest'" id="email-contato"><i class="fa fa-envelope"></i> Falar com contato </button>
                                            
                                        </div>
                                    </div>
-->


                                </div>
                                <div class="col-md-7" id="noresult"></div>
                                
                                <div class="col-md-5" id="box_vinculado">
                                    <div class="widget widget-one no-padding" style="min-height:130px">
                                        <div class="p-m">
                                            <h1 class="m-xs" id="total_vinculado">0</h1>
                                            <h3 class="font-bold no-margins" id="registro_vinculado">
                                               CNPJ(s) vinculado encontrado(s)
                                            </h3>
                                            <small>Para o CNPJ: <span id="cnpj_keyword"></span></small>
                                        </div>
                                        <div class="p-m" id="result_vinculado" >
                                            <h3 class="m-xs">CNPJ's Vinculados <small style="color:white">(Matriz e/ou Filial)</small><br/><br/></h3>
                                           
                                            <div id="dado_vinculado">
                                                <!--
                                                <h4 class="font-bold m-xs">
                                                    <span id="cnpj_vinculado">01.956.134/0001-43</span> - <span id="nome_viculado">NILO TOZZO E CIA LTDA</span>
                                                </h4>
                                                <small class="m-xs"><span id="cidade_vinculado">CORDILHEIRA ALTA</span> - <span id="estado_vinculado">SC</span></small>
                                                -->
                                            </div>
                                            
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--
                        <div class="ibox-footer">
                            <span class="pull-right">
                                Full stock - <i class="fa fa-clock-o"></i> 14.04.2016 10:04 pm
                            </span>
                            The generated Lorem Ipsum is therefore always free
                        </div>
                        -->
                    </div>

                </div>
            </div>
            <!-- // result master detail -->
           <!-- horizontal bars -->
           <div class="row graph" id="grapha">
                <div class="col-lg-12">
                	<div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>AN�LISE POR ANO </h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                               
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content" id="flot-bar-chart2a-content">
                            <div id="flot-bar-chart2a-total"></div>
                            <div class="flot-chart flot-bar-chart" id="flot-layer-2a" >
                                <div class="flot-chart-content" id="flot-bar-chart-2a"></div>
                            </div>
                            
                            <div class="flot-chart flot-chart-table" style="height:150px">
                                <div class="flot-chart-content flot-char-content-table" >
                                     <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr id="flot-bar-chart-ticks-2a">
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody id="flot-bar-chart-data-2a">
                                                <tr>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div id="flot-bar-chart2a-other" class="left">Outros</div>
                                </div>
                            </div>
                            
                           
                    </div>
                    </div>
                </div>
            </div>
           <div class="row graph" id="graphb">
            	<div class="col-lg-12">
                	<div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>AN�LISE POR �LTIMOS 12 MESES </h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                               
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content" id="flot-bar-chart2b-content">
                            <div id="flot-bar-chart2b-total"></div>
                            <div class="flot-chart flot-bar-chart" id="flot-layer-2b" style="display:none" >
                                <div class="flot-chart-content" id="flot-bar-chart-2b"></div>
                            </div>
                           <div class="flot-chart flot-chart-table">
                                <div class="flot-chart-content flot-char-content-table" >
                                     <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr id="flot-bar-chart-ticks-2b">
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody id="flot-bar-chart-data-2b">
                                                <tr>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div id="flot-bar-chart2b-other" class="left">Outros</div>
                                </div>
                            </div> 
                           
                    </div>
                    </div>
                </div>
                
            </div>
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
			/*
                       if(typeof table != "undefined"){
				table.destroy();
			}
                        $('#example').show();
			 table = $('#example').DataTable( {
				"ajax": {
					"url": "DashboardServlet?selectGicChassi=true&keyword="+$('#keyword').val(),
					"dataSrc": ""
				},
				"columns": [
					{ "data": "Data" },
					{ "data": "Chassi" },
                                        { "data": "Modelo" },
                                        { "data": "Marca" },
					{ "data": "Placa" },
                                        { "data": "Nome" }
					]
			} );
                        */
                       if(typeof table != "undefined"){
				table.destroy();
			}
                        var url= "DashboardServlet?selectGicChassi=true&keyword="+$('#keyword').val();
                        var tbl="";
                       // $("#example").html("<tr>"+"<td>Chassi</td>"+"<td></td>"+"</tr>");
                       // $('#example').show();
                        //$(".info").html("<div class=\"pre-loader\">&nbsp;</div>");
                        $.getJSON(url, function (result) {
					if (result && result.length) {
                                                var nome="";
                                                var logradouro = "";
                                                var numero = "";
                                                var complemento = "";
                                                var bairro = "";
                                                var cidade = "";
                                                var cep = "";
                                                var uf = "";
                                                var tel1 = "";
                                                var tel2 = "";
                                                var tel3 = "";
                                                var email = "";
                                                var tipo = "";
                                                var cnpj = "";
                                                var atividade = "";
						// percorrendo o array json
						$.each( result, function( key, val ) {
                                                    nome = val.Nome;
                                                    logradouro = val.Endereco;
                                                    numero = val.Numero;
                                                    complemento = val.Complemento;
                                                    cidade = val.Cidade;
                                                    bairro = val.Bairro;
                                                    uf= val.Estado;
                                                    cep = val.Cep;
                                                    tel1 = ""+val.DDD1+" "+ val.Telefone1;
                                                    tel2 = ""+val.DDD2+" "+ val.Telefone2;
                                                    tel3 = ""+val.DDD3+" "+ val.Telefone3;
                                                    email = val.Email1;
                                                    atividade = val.Atividade;
                                                    tipo = val.Tipo;
                                                    cnpj = val.CPF;
                                                    tbl="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Chassi</td>"+"<td  class=\"inputData\">"+val.Chassi+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Modelo</td>"+"<td class=\"inputData\">"+val.Modelo+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Marca</td>"+"<td class=\"inputData\">"+val.Marca+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Data Emplacamento</td>"+"<td class=\"inputData\">"+val.Data+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Segmento</td>"+"<td class=\"inputData\">"+val.Segmento+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">SubSegmento</td>"+"<td class=\"inputData\">"+val.SubSegmento+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Concession�rio</td>"+"<td class=\"inputData\">"+val.Concessionario+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Cidade</td>"+"<td class=\"inputData\">"+val.Cidade+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Ano Fabrica��o</td>"+"<td class=\"inputData\">"+val.AnoFabricacao+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputRed\">"+"<td class=\"inputLegend\">Ano Modelo</td>"+"<td class=\"inputData\">"+val.AnoModelo+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">CPF ou CNPJ do Propriet�rio</td>"+"<td class=\"inputData\">"+val.CPF+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Tipo de Pessoa</td>"+"<td class=\"inputData\">"+val.Tipo+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Nome do Propriet�rio</td>"+"<td class=\"inputData\">"+val.Nome+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Endere�o</td>"+"<td class=\"inputData\">"+val.Endereco+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">N�mero</td>"+"<td class=\"inputData\">"+val.Numero+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Complemento</td>"+"<td class=\"inputData\">"+val.Complemento+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Bairro</td>"+"<td class=\"inputData\">"+val.Bairro+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Cidade</td>"+"<td class=\"inputData\">"+val.Cidade+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Estado</td>"+"<td class=\"inputData\">"+val.Estado+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">CEP</td>"+"<td class=\"inputData\">"+val.Cep+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">DDD1</td>"+"<td class=\"inputData\">"+val.DDD1+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Telefone1</td>"+"<td class=\"inputData\">"+val.Telefone1+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">DDD2</td>"+"<td class=\"inputData\">"+val.DDD2+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Telefone2</td>"+"<td class=\"inputData\">"+val.Telefone2+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">DDD3</td>"+"<td class=\"inputData\">"+val.DDD3+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Telefone3</td>"+"<td class=\"inputData\">"+val.Telefone3+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">DDD4</td>"+"<td class=\"inputData\">"+val.DDD4+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Telefone4</td>"+"<td class=\"inputData\">"+val.Telefone4+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">DDD5</td>"+"<td class=\"inputData\">"+val.DDD5+"</td>"+"</tr>";
                                                    tbl+="<tr class=\"inputGray\">"+"<td class=\"inputLegend\">Telefone5</td>"+"<td class=\"inputData\">"+val.Telefone5+"</td>"+"</tr>";
                                                });
                                                
                                                
                                                
                                                $("#nome").html(nome);
                                                $("#logradouro").html(logradouro+",");
                                                $("#numero").html(numero+". ");
                                                $("#complemento").html(complemento);
                                                $("#cep").html(cep);
                                                $("#cidade").html(cidade);
                                                $("#bairro").html(cidade);
                                                $("#uf").html(uf);
                                                $("#tel1").html(tel1);
                                                $("#tel2").html(tel2);
                                                $("#tel3").html(tel3);
                                                $("#ativ").html(atividade);
                                                $("#email1").html(email);
                                                $("#tipo").html(tipo);
                                                $("#cnpj").html(cnpj);
                                                
                                                
                                                //$("#ligar").click(function(){ "location.href='"+"tel:"+tel1+"';" });
                                                $('#email-contato').removeAttr('onclick');
                                                $('#email-contato').attr('onClick', "enviarEmail('"+email+"')");
                                                //$("#email-contato").click(function(){ "location.href='"+"mailto:"+email+"?subject=Quest Inteligencia';" });
                                                
                                                //$(".info").html("");
                                               // console.log(tbl);
                                                $("#result").show();
                                                $("#noresult").hide();
                                                $('#grapha').show();
                                                $('#graphb').show();
                                                $('#box_vinculado').show();
                                                dashboardSearch();  
                                            }else{
                                               // $(".info").html("");
                                                $("#result").hide();
                                                $('#grapha').hide();
                                                $('#graphb').hide();
                                                $("#noresult").html("Nenhum CPF/CNPJ encontrado.");
                                                $('#box_vinculado').hide();
                                            }
                                        });
             // table tools
		       //table = $('#example').DataTable();
			//var tt = new $.fn.dataTable.TableTools( table );
                        //$( tt.fnContainer() ).insertAfter('div.info');
                        
                        
                         //document.location.href ="DownloadReportEnriquecer";
                                                 
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
                                    "url": 'http://cdn.datatables.net/plug-ins/1.10.10/i18n/Portuguese-Brasil.json'
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
