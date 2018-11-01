/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function retornaUltimoDiaDoMes(qualquerObjetoDate){
        return (new Date(qualquerObjetoDate.getFullYear(), qualquerObjetoDate.getMonth() + 1, 0) ).getDate();
   }   
   function daysInMonth(month,year) {
	var dd = new Date(year, month, 0);
	return dd.getDate();
}
    $(document).ready(function() {
            
           
                    //alert("hu");   
                            
			// exibindo os segmentos
			//selectSeg();
                        // inicializando o dashboard
                      //  $('#myModal2').modal('show');
                       // loadingDashboard();
                    	
						
                        
        
             });
                
                function loadingDashboard(){
                    //$.isLoading({ text: "Loading" });
                    setTimeout(function(){
                        dashboardSearch();
                      }, 1000);
                }
                
		function dashboardSearch(){
                    //responsiveTables();
                    //var areaOper = $('#area_oper').val();
                    //var seg = $('#segmento').val();
                   // console.log("hello");
			//countChassi();
                     //   printInformation();
			//plotPieDistSeg();
			//plotPieDistMarca();
			//plotHorizontalBarRankMunic();
			//plotHorizontalBarRankModel();
			//plotVerticalBar();
                      //  plotVerticalBar1();
                       // plotVerticalBar2();
                       // plotVerticalBar3();
                       // plotVerticalBar4();
                       // plotVerticalBar5();
                        plotSearchCnpjByYear();
                        plotSearchCnpjByLastYear();
                        searchCnpjVinculado();
                       // plotHorizontalBar2b();
                        //plotPiePerfilCliente();
                        
                       // selectTopSegmentoNacional();
                        
                        //selecRankingNacionalAreaOperacional();
                        
                        // segmento
                        
                       // hideLoadingDashboard();
		}
                function hideLoadingDashboard(){
                    $('#myModal2').modal('hide');
                    // concertando o erro de SVG para o PhantomJS
                    //fixSVG();
                }
                //-->> function get inicio para periodo acumulado
                function getDataIniAcumulado(){
                    var ini =  $("#ini").val();
                    var d = ini.substring(0,2);
                    var m = ini.substring(3,5);
                    var y = ini.substring(6,10);
                    var dataSelecionada = "01/01/"+y;//(new Date(y, 1 , 1 )).getDate();
                    return dataSelecionada;
                }
                //--> function responsive tables
                function responsiveTables(){
                    $('.responsive').not('table').responsiveText();
                    $('table.responsive').responsiveTables();
                }
                //<-- function responsive tables
                //--> function printInformation
                function printInformation(){
                    var seg = $("#segmento").val();
                    var ini = $("#ini").val();
                    var fim = $("#fim").val();
                    var areaOper = $('#area_oper option:selected').text();
                    if(areaOper=="TODOS"){
                        areaOper="NACIONAL";
                    }
                     $("#principaisEmplacados").html(areaOper);
                     $("#topSegmentoNacionalCaption").html(" Período "+ini+" à "+fim);
                     $("#analiseEmplacamentosMes").html(" - "+areaOper);
                     //$("#analiseEmplacamentosMesCaption").html(" Acumulado "+ini+" à "+fim+"<br/>");
                     
                    // $("#topRankingRegionalAreaOperacionalCaption").html(" Período "+ini+" à "+fim+"<br/>");
                    // $("#topRankingNacionalAreaOperacionalCaption").html(" Período "+ini+" à "+fim+"<br/>");
                     //$("#topSegmentoCaption").html(" Período "+ini+" à "+fim+"<br/>");
                     
                }
               
                
                //--> function plot Horizontal Bar - 2a
		function plotSearchCnpjByYear(){
			
				var url = "DashboardServlet?searchCnpjByYear=true&keyword="+$('#keyword').val();
				var seriesLabel=[];
				var seriesData=[];
                                var tableHead="<th style=\"min-width:69px; \"></th><th style=\"min-width:67px; \">2014</th><th style=\"min-width:67px;\">2015</th><th style=\"min-width:67px;\">2016</th><th>TOTAL</th>";
                               
                                
                                var tableBody="";
                                var other="";
                                var acuIni="";
                                var acuFim="";
                                var total = "";
                               
                               
                                //var tableRowTotal="<tr><td><img src=\"img/icon-chassis.png\" width=\"63px\" height=\"16px\"></td>";
                                //var tableRowClient="<tr><td><img src=\"img/icon-clientes.png\" width=\"63px\" height=\"16px\"></td>";
				//var tableRowAvg="<tr><td><img src=\"img/icon-media.png\" width=\"63px\" height=\"16px\"></td>";
                                
				$("#flot-bar-chart-2a").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						//seriesData = [];
						$.each( data, function( key, val ) {
							seriesLabel.push(val.label);
                                                       
                                                             var dataSerie = [parseFloat(val.t2014), parseFloat(val.t2015), parseFloat(val.t2016)];
                                                             tableBody+="<tr>"+
                                                                    "<td style=\"text-align:left;font-weight:bold;\">"+"<svg class=\"svg-legend\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">"+
                                                                                "<rect class=\"svg-legend-style svg-legend-color-"+key+"\"/>"+
                                                                              "</svg>"+
                                                                    val.marca+"</td>"+
                                                                    "<td>"+parseFloat(val.s2014).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.s2015).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.s2016).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.total).toFixed(0)+"</td>"+
                                                                    "</tr>";
                                                            // aumentando a altura da div
                                                            $("#flot-layer-2a").css("height","200px");
                                                        
                                                        seriesData.push(dataSerie);
                                                        other = "";//val.other;
                                                        acuIni = val.ini;
                                                        acuFim = val.fim;
                                                        total = val.totals;
                                                        // add heads to columns
                                                          
                                                       // tableHead+="<th style=\"width:63px;\">"+val.label+"</th>";
                                                       // tableRowTotal+="<td>"+val.leves+"</td>";
                                                       // tableRowClient+="<td>"+(val.leves)+"</td>";
                                                       // tableRowAvg+="<td>"+((val.leves/(val.leves)).toFixed(2))+"</td>";
						});
                                               // console.log("teste grafico");
                                               // console.log(seriesData);
                                              //  tableRowTotal+="</tr>";
                                              //  tableRowClient+="</tr>";
                                               // tableRowAvg+="</tr>";
                                               var arrayLabels = null;
                                               
                                                    $("#flot-bar-chart-ticks-2a").html(tableHead);
                                                    arrayLabels = ['2014', '2015', '2016'];
                                                
                                                $("#flot-bar-chart-data-2a").html("");
                                                $("#flot-bar-chart-data-2a").html(tableBody);
                                                $("#flot-bar-chart2a-total").html("Período:"+acuIni+" à "+acuFim+"<br/>"+"Total: "+total+"</br>");
                                                $("#flot-bar-chart2a-other").html(other);
                                               // console.log("date:"+gd(2012, 1, 1));
                                                
						
						//require('chartist-plugin-legend');
						$("#flot-bar-chart-2a").html("");
						var chart = new Chartist.Bar('#flot-bar-chart-2a', {
                                                                labels: arrayLabels,
                                                                series: seriesData
                                                              }, {
                                                                stackBars: true,
                                                                horizontalBars: true,
                                                                seriesBarDistance: 20,
                                                                low: 0,
                                                                high: 100,
                                                                reverseData: false,
                                                                axisX:{
                                                                    labelInterpolationFnc: function(value) {
                                                                    return (value) + '%';
                                                                  }
                                                                },
                                                                axisY: {
                                                                  offset: 100
                                                                  
                                                                }
                                                              }).on('draw', function(data) {
                                                                if(data.type === 'bar') {
                                                                  data.element.attr({
                                                                    style: 'stroke-width: 30px'
                                                                  });
                                                                }
                                                              });
						/* adjust chart point*/
						//chartPoint(chart);	  
						
					}else{
                                                //$("#flot-layer-2a").html("");
                                                $("#flot-bar-chart-2a").html("");
                                                $("#flot-bar-chart-data-2a").html("");
                                                $("#flot-bar-chart-ticks-2a").html("Não há dados para o período.");
                                                $("#flot-bar-chart2a-total").html("Período:"+$('#ini').val()+" à "+$('#fim').val()+"<br/>"+"Total: "+"0"+"</br>");
                                                $("#flot-bar-chart2a-other").html("");
                                        }// end if
				});
				
		}
		//<-- function plot Horizontal Bar - 2a 
                
                //--> function plot Horizontal Bar - 2b
		function plotSearchCnpjByLastYear(){
			
				var url = "DashboardServlet?searchCnpjByLastYear=true&keyword="+$('#keyword').val();
				var seriesLabel=[];
				var seriesData=[];
                                var tableHead="<th style=\"min-width:99px; \"></th>";//"<th style=\"min-width:27px; \">Jun/2015</th><th style=\"min-width:27px;\">Jul/2015</th><th style=\"min-width:27px;\">Ago/2015</th><th style=\"min-width:27px;\">Set/2015</th><th style=\"min-width:27px;\">Out/2015</th><th style=\"min-width:27px;\">Nov/2015</th><th style=\"min-width:27px;\">Dez/2015</th><th style=\"min-width:27px;\">Jan/2016</th><th style=\"min-width:27px;\">Fev/2016</th><th style=\"min-width:27px;\">Mar/2016</th><th style=\"min-width:27px;\">Abr/2016</th><th style=\"min-width:27px;\">Mai/2016</th><th>TOTAL</th>";
                                var currentDate = new Date();
                                var monthNames = ["JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"];
                                var currentMonth = new Date().getMonth() -0;
                                var currentYear = new Date().getFullYear();
                                //var lastMonths =monthNames.slice(currentMonth).concat(monthNames.slice(0, currentMonth));
                                
                                var monthThisYear = monthNames.slice(0, currentMonth);
                                var monthLastYear = monthNames.slice(currentMonth);
                                // meses atual
                                for (var i=0; i<monthThisYear.length;i++) {
                                    monthThisYear[i]+="/"+currentYear;
                                }
                                // meses do ano anterior
                                for (var i=0; i<monthLastYear.length;i++) {
                                    monthLastYear[i]+="/"+(currentYear-1);
                                }
                                var lastMonths =monthLastYear.concat(monthThisYear);
                                // listando os meses corretos na legenda
                                // meses corretos
                                for (var i=0; i<lastMonths.length;i++) {
                                    tableHead+="<th style=\"min-width:27px; \">"+lastMonths[i]+"</th>";
                                }
                                tableHead+="<th>TOTAL</th>";
                                
                                var tableBody="";
                                var other="";
                                var acuIni="";
                                var acuFim="";
                                var total = "";
                               
                               
                                //var tableRowTotal="<tr><td><img src=\"img/icon-chassis.png\" width=\"63px\" height=\"16px\"></td>";
                                //var tableRowClient="<tr><td><img src=\"img/icon-clientes.png\" width=\"63px\" height=\"16px\"></td>";
				//var tableRowAvg="<tr><td><img src=\"img/icon-media.png\" width=\"63px\" height=\"16px\"></td>";
                                
				$("#flot-bar-chart-2b").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						//seriesData = [];
						$.each( data, function( key, val ) {
							seriesLabel.push(val.label);
                                                            var monthValues = [val.jan,val.fev,val.mar,val.abr,val.mai,val.jun,val.jul,val.ago,val.set,val.out,val.nov,val.dez];
                                                            var monthValues = monthValues.slice(currentMonth).concat(monthValues.slice(0, currentMonth));
                                                            var monthSumValues = [val.sjan,val.sfev,val.smar,val.sabr,val.smai,val.sjun,val.sjul,val.sago,val.sset,val.sout,val.snov,val.sdez];
                                                            var monthSumValues = monthSumValues.slice(currentMonth).concat(monthSumValues.slice(0, currentMonth));
                                                            var dataSerie = [parseFloat(val.smai), parseFloat(val.sabr), parseFloat(val.smar),parseFloat(val.sfev),parseFloat(val.sjan),parseFloat(val.sdez),parseFloat(val.snov),parseFloat(val.sout),parseFloat(val.sset),parseFloat(val.sago),parseFloat(val.sjul),parseFloat(val.sjun)];
                                                             tableBody+="<tr>"+
                                                                    "<td style=\"text-align:left;font-weight:bold;\">"+"<svg class=\"svg-legend\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">"+
                                                                                "<rect class=\"svg-legend-style svg-legend-color-"+key+"\"/>"+
                                                                              "</svg>"+
                                                                    val.marca+"</td>";
                                                                    for (var i=0; i<monthValues.length;i++) {
                                                                    tableBody+="<td>"+parseFloat(monthValues[i]).toFixed(0)+"</td>";
                                                                    }
                                                                    /*
                                                                    "<td>"+parseFloat(monthValues[0]).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(monthValues[0]).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(monthValues[0]).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.out).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.nov).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.dez).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.jan).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.fev).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.mar).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.abr).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.mai).toFixed(0)+"</td>"+*/
                                                                    tableBody+="<td>"+parseFloat(val.total).toFixed(0)+"</td>"+
                                                                    "</tr>";
                                                            // aumentando a altura da div
                                                            $("#flot-layer-2b").css("height","200px");
                                                        if(key==0){
                                                        seriesData.push(dataSerie);
                                                      }
                                                      if(key == data.length-1){
                                                          tableBody+="<tr style=\"background-color:#ddd\">"+
                                                                    "<td style=\"text-align:left;font-weight:bold;\">"+
                                                                    "TOTAL"+"</td>";
                                                                    for (var i=0; i<monthValues.length;i++) {
                                                                    tableBody+="<td>"+parseFloat(monthSumValues[i]).toFixed(0)+"</td>";
                                                                    }
                                                                    /*
                                                                    "<td>"+parseFloat(val.sjun).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sjul).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sago).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sset).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sout).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.snov).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sdez).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sjan).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sfev).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.smar).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.sabr).toFixed(0)+"</td>"+
                                                                    "<td>"+parseFloat(val.smai).toFixed(0)+"</td>"+*/
                                                                    tableBody+="<td>"+parseFloat(val.totals).toFixed(0)+"</td>"+
                                                                    "</tr>";
                                                      }
                                                        other = "";//val.other;
                                                        acuIni = lastMonths[0];
                                                        acuFim = lastMonths[11];
                                                        total = val.totals;
                                                        // add heads to columns
                                                          
                                                       // tableHead+="<th style=\"width:63px;\">"+val.label+"</th>";
                                                       // tableRowTotal+="<td>"+val.leves+"</td>";
                                                       // tableRowClient+="<td>"+(val.leves)+"</td>";
                                                       // tableRowAvg+="<td>"+((val.leves/(val.leves)).toFixed(2))+"</td>";
						});
                                               // console.log("teste grafico");
                                               // console.log(seriesData);
                                              //  tableRowTotal+="</tr>";
                                              //  tableRowClient+="</tr>";
                                               // tableRowAvg+="</tr>";
                                               var arrayLabels = null;
                                               
                                                    $("#flot-bar-chart-ticks-2b").html(tableHead);
                                                    arrayLabels = ['Mai/2016', 'Abr/2016', 'Mar/2016', 'Fev/2016', 'Jan/2016', 'Dez/2015', 'Nov/2015', 'Out/2015', 'Set/2015', 'Ago/2015', 'Jul/2015', 'Jun/2015'];
                                                
                                                $("#flot-bar-chart-data-2b").html("");
                                                $("#flot-bar-chart-data-2b").html(tableBody);
                                                $("#flot-bar-chart2b-total").html("Período: "+acuIni+" à "+acuFim+"<br/>"+"Total: "+total+"</br>");
                                                $("#flot-bar-chart2b-other").html(other);
                                               // console.log("date:"+gd(2012, 1, 1));
                                                
						
						//require('chartist-plugin-legend');
						$("#flot-bar-chart-2b").html("");
						var chart = new Chartist.Bar('#flot-bar-chart-2b', {
                                                                labels: arrayLabels,
                                                                series: seriesData
                                                              }, {
                                                                stackBars: false,
                                                                horizontalBars: false,
                                                                seriesBarDistance: 20,
                                                                low: 0,
                                                                high: 100,
                                                                reverseData: true,
                                                                axisX:{
                                                                    labelInterpolationFnc: function(value) {
                                                                    return (value) + '';
                                                                  }
                                                                },
                                                                axisY: {
                                                                  offset: 100
                                                                  
                                                                }
                                                              }).on('draw', function(data) {
                                                                if(data.type === 'bar') {
                                                                  data.element.attr({
                                                                    style: 'stroke-width: 30px'
                                                                  });
                                                                }
                                                              });
						/* adjust chart point*/
						//chartPoint(chart);	  
						
					}else{
                                                //$("#flot-layer-2a").html("");
                                                $("#flot-bar-chart-2b").html("");
                                                $("#flot-bar-chart-data-2b").html("");
                                                $("#flot-bar-chart-ticks-2b").html("Não há dados para o período.");
                                                $("#flot-bar-chart2b-total").html("");
                                                //$("#flot-bar-chart2b-total").html("Período:"+$('#ini').val()+" à "+$('#fim').val()+"<br/>"+"Total: "+"0"+"</br>");
                                                $("#flot-bar-chart2b-other").html("");
                                        }// end if
				});
				
		}
		//<-- function plot Horizontal Bar - 2a 
                
                //--> function search Cnpj Vinculado
		function searchCnpjVinculado(){
			var url = "DashboardServlet?searchCnpjVinculado=true&keyword="+$('#keyword').val();
				
                                var tableBody="";
                                var keyword="";
                                var total = "";
                                
				$("#dado_vinculado").html("<div class=\"pre-loader\">&nbsp;</div>");
				$.getJSON(url, function (data) {
					if (data && data.length) {
						//seriesData = [];
						$.each( data, function( key, val ) {
							     tableBody+="<div class=\"over-blue\" onclick=\"searchCnpj('"+val.cpf+"')\"><h5 class=\"font-bold m-xs\">"+
                                                                        val.cpfcnpj+ " - "+ val.nome+
                                                                        "</h5>"+
                                                                        "<small class=\"m-xs\">"+val.municipio+" - "+val.estado+"</small>"+
                                                                        "</div>";
                                                       
                                                        keyword = val.keyword;
                                                        total = val.totals;
						});
                                                $("#result_vinculado").show();
                                                $("#dado_vinculado").html(tableBody);
                                                $("#total_vinculado").html(total);
                                                $("#cnpj_keyword").html(keyword);
                                                if(total==1){
                                                    $("#registro_vinculado").html("CNPJ vinculado encontrado");
                                                }else if(total>1){
                                                    $("#registro_vinculado").html("CNPJ's vinculados encontrados");
                                                }
                                                
					}else{
                                                //$("#flot-layer-2a").html("");
                                                var cellvalue = $('#keyword').val();
                                               // $("#cnpj_keyword").unmask();
                                                var tamanho = cellvalue.length;
                                                if(tamanho == 11){
                                                    //$("#cnpj_keyword").mask("999.999.99-99");
                                                   // cellvalue = $.inputmask.format(cellvalue, { mask: "999.999.99-99"});
                                                   cellvalue = cpfCnpj(cellvalue,'F');
                                                } else if(tamanho == 14){
                                                    //$("#cnpj_keyword").mask("99.999.999/9999-99");
                                                    //cellvalue= $.inputmask.format(cellvalue, { mask: "99.999.999/9999-99"});
                                                    //cellvalue = $("<input>").attr("type", "hidden").val(cellvalue).inputmask("99.999.999/9999-99");
                                                    cellvalue = cpfCnpj(cellvalue,'J');
                                                }
                                                $("#cnpj_keyword").html(cellvalue);
                                                $("#total_vinculado").html("0");
                                                $("#dado_vinculado").html("<h4 class=\"font-bold m-xs\">"+""+"</h4>");
                                                $("#result_vinculado").hide();
                                                $("#registro_vinculado").html("Não existe nenhum CPF/CNPJ's vinculado.");
                                        }// end if
				});
				
		}
		//<-- function searchCnpjVinculado
                
                
		
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
			
			
			return "<div style='min-width: 60px; display:block; font-size:8pt; text-align:center; padding:2px; color:"+series.color+";'>" + label + "<br/>" + res+" | "+series.percent.toFixed(0)+  "%</div>";
			
		}
                function searchCnpj(objKey){
                    window.location.href="search-cnpj.jsp?keyword="+objKey;
                }
                
function cpfCnpj(v,label){

    //CNPJ
    if (label == 'J'){
        return formataCampo(v, '00.000.000/0000-00', event);
    }
    //CPF
    if (label == 'F'){
        return formataCampo(v, '000.000.000-00', event);
    }

}
//formata de forma generica os campos
function formataCampo(campo, Mascara, evento) { 
    var boleanoMascara; 

    var Digitato = evento.keyCode;
    var exp = "/\-|\.|\/|\(|\)| /g";
    //campoSoNumeros = campo.value.toString().replace( exp, "" ); 
    var campoSoNumeros = campo.toString().replace( exp, "" ); 

    var posicaoCampo = 0;     
    var NovoValorCampo="";
    var TamanhoMascara = campoSoNumeros.length; 

    if (Digitato != 8) { // backspace 
        for(var i=0; i<= TamanhoMascara; i++) { 
            boleanoMascara  = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
                                || (Mascara.charAt(i) == "/")) 
            boleanoMascara  = boleanoMascara || ((Mascara.charAt(i) == "(") 
                                || (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " ")) 
            if (boleanoMascara) { 
                NovoValorCampo += Mascara.charAt(i); 
                  TamanhoMascara++;
            }else { 
                NovoValorCampo += campoSoNumeros.charAt(posicaoCampo); 
                posicaoCampo++; 
              }            
          }     
        //campo.value = NovoValorCampo;
        campo = NovoValorCampo;
          return campo; 
    }else { 
        return campo; 
    }
}