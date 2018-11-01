/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * tableid = table1
 * color = color1, color2....
 * fontMin= 10
 * fontMax = 10
 * cation = ""
 * title = Titulo da tabela
 * dataHead = ["MODELO","TOTAL", "%"]
 * dataBody = [["Mercedes Benz - XYZ", "TOTAL", "string"] ["19.500","TOTAL", "currency"] ["9%", "MARKET SHARE", "percentual"]]
 * dataBody = [{value:"Mercedes Benz - XYZ",title:"TITULO",type:"string"}, {value:"19.500",title:"total",type:"currency"}, {value:"9%",title:"market share",type:"percentual"}]
 */

function renderTable(tableid, color, fontMin, fontMax, caption, title, dataHead, dataBody, dataFoot){
    var htm="";
    //console.log(dataHead);
    //console.log(dataBody);
    htm+="<div class=\"table-responsive table-module\">"+
                                "<table class=\"table table-striped responsive responsive-table "+color+"\" id=\"table1\" data-min=\""+fontMin+"\" data-max=\""+fontMax+"\" >"+
                                    "<caption>"+caption+"</caption>"+
                                    "<thead>"+
                                        "<tr><th colspan=\""+dataHead.length+"\" scope=\"colgroup\">"+title+"</th></tr>"
                                        "<tr>";
                                               if(dataHead !== null){
                                                  //var rows = [];
                                                    for (var rowCount=0; rowCount < dataHead.length; rowCount++) {
                                                        if(rowCount==0){
                                                            htm+="<th scope=\"col\" data-type=\"string\"  \">"+dataHead[rowCount]+"</th>";
                                                        }else{
                                                            htm+="<th scope=\"col\" data-type=\"currency\" \">"+dataHead[rowCount]+"</th>";
                                                        }
                                                    }

                                               } 
                                            htm+=// "<th scope=\"col\">MODELO</th>"+
                                       "</tr>" +
                                    "</thead>"+
                             
                                    "<tbody>"+
                                        "<tr>";
                                            if(dataBody !== null){
                                                for (var rowCount=0; rowCount < dataBody.length; rowCount++) {
                                                        htm+="<tr>";
                                                        for(var colCount=0; colCount < dataBody[rowCount].length; colCount++){
                                                           // console.log(dataBody[rowCount][colCount]);
                                                           if(colCount==0){
                                                                htm+= "<td scope=\"row\" data-title=\""+dataBody[rowCount][colCount].title+"\" data-type=\""+dataBody[rowCount][colCount].type+"\">"+dataBody[rowCount][colCount].value+"</td>";
                                                            }else{
                                                                htm+= "<td  data-title=\""+dataBody[rowCount][colCount].title+"\" data-type=\""+dataBody[rowCount][colCount].type+"\">"+dataBody[rowCount][colCount].value+"</td>";
                                                        
                                                            }
                                                        }
                                                        htm+="</tr>";
                                                    }
                                            }
                                           // htm+="<td scope=\"row\">Mercedes Bens XSAZ</td>"+
                                           // "<td data-title=\"TOTAL\" data-type=\"currency\">10.500</td>"+
                                           // "<td data-title=\"MARKET SHARE %\" data-type=\"percentual\">9%</td>";
                                        htm+="</tr>"+
                                    "</tbody>"+
                                    "<tfoot>";
                                        if(dataFoot !== null){
                                                for (var rowCount=0; rowCount < dataFoot.length; rowCount++) {
                                                        htm+="<tr scope=\"row\">";
                                                        for(var colCount=0; colCount < dataFoot[rowCount].length; colCount++){
                                                           // console.log(dataBody[rowCount][colCount]);
                                                            htm+= "<td  data-title=\""+dataFoot[rowCount][colCount].title+"\" data-type=\""+dataFoot[rowCount][colCount].type+"\">"+dataFoot[rowCount][colCount].value+"</td>";
                                                        }
                                                        htm+="</tr>";
                                                    }
                                            }
                                        
                                    htm+="</tfoot>"+
                                "</table>"+
                            "</div>";
    $("#"+tableid).html(htm);                
    
}

