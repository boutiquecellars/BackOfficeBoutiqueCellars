<%@page import="br.com.itfox.beans.Member"%>
<%@page import="br.com.itfox.beans.MemberAreaOper"%>
<%@page import="br.com.itfox.beans.AreaOper"%>
<%@page import="br.com.itfox.business.BusinessDelegate"%>
<%@page import="java.util.List"%>
<%@page import="br.com.itfox.config.Preferences"%>
<%@include file="incProfileAdmin.jsp" %>
<%
    String profile="admin";
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
      //  response.sendRedirect("login.jsp");
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%} else { 
        int memberId = Integer.parseInt(session.getAttribute("userid").toString());
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
        
        .wrapper-content{
            padding: 0px 0px 40px;
        }
        .page-heading{
            border-top: 0;
            padding: 0px 10px 20px 10px;
            margin-bottom:20px;
        }
        .gray-bg{
            background-color: #f3f3f4;
        }
        .light-bg{
            background-color:#ffffff;
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
     <jsp:param name="page" value="members" />
    </jsp:include>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <jsp:include page="incNavbarStaticTop.jsp" />
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            
            <div class="row">
                <div class="col-lg-12">
                    <!--
                    <div class="text-center m-t-lg">
                        <h1>
                            <%=Preferences.h1%>
                        </h1>
                        <small >
                           Buscar Chassi.
                           <br/><br/><br/><br/>
                        </small>
                    </div>
                    -->
                    <!-- dialog itself, mfp-hide class is required to make dialog hidden -->
                    <div id="small-dialog" class="zoom-anim-dialog mfp-hide">
                        <h1>Arquivos para Enriquecer</h1>
                        <p id="progress-report">Lista de arquivos para enriquecer.</p>
                        <form id="formMember" name="formMember" action="" method="">
                            <input type="hidden" name="memberId">
                            <input type="hidden" name="name">
                            <input type="hidden" name="email">
                        </form>
                    </div>
                </div>
            </div>
            
            <!-- filter -->
           
            <!-- widgets -->
            
            <!--// widgets -->
            
            <!-- table -->
            
            <div class="row wrapper border-bottom light-bg page-heading">
                <div class="col-lg-10">
                    <h2> <input type="hidden" name="memberId" id="memberId" value="<%=session.getAttribute("userid")%>">List of Users </h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.jsp">Home</a>
                        </li>
                        <li class="active">
                            <strong>List of Users</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">
                    <br/><br/>
                        <a href="register.jsp"><i class="fa fa-user-plus"></i> <span class="nav-label">Create Users</span></a>
                </div>
            </div>

        <div class="wrapper wrapper-content  animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Users</h3>
                            <p class="small"><i class="fa fa-hand-o-up"></i> Select users</p>

                            <div class="input-group">
                                <input type="text" id="keyword-area" placeholder="Add new task. " class="input input-sm form-control">
                                <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-search"></i> Search</button>
                                </span>
                            </div>
                            

                            <ul id="area_disponivel" class="sortable-list connectList agile-list">
                                <%
                                List<Member> members = new BusinessDelegate().selectMembers();
                                for(Member m: members){
                                %>
                                <li class="<% if(m.getPermission().equalsIgnoreCase("admin")){out.print("warning-element");}else{ out.print("info-element");} %>" id="<%=m.getMemberId()%>">
                                    <%=m.getName()%>
                                    <div class="agile-detail">
                                        <!--
                                        <a href="#" class="pull-right btn btn-xs btn-white click_area" onclick="cadastrarAreaOperacional('<%=m.getMemberId()%>','<%=m.getName()%>','<%=m.getEmail()%>')">&#8594;</a>
                                        -->
                                        <!--<a href="#" class="pull-right btn btn-xs btn-white">ID <%=m.getMemberId()%></a> -->
                                        <a href="register.jsp?id=<%=m.getMemberId()%>" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-edit"></i> Edit</a>
                                        <a href="register.jsp?id=<%=m.getMemberId()%>&operation=delete" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-remove"></i> Delete</a>
                                        
                                        <i class="fa fa-clock-o"></i> <%=m.getEmail()%>
                                    </div>
                                </li>
                                <%
                                }
                                %>
                               
                            </ul>
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
 <script>
        $(document).ready(function() {
            
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
			 
            function cadastrarAreaOperacional(memberId,memberName,memberEmail){
                        document.formMember.action= ("registerArea.jsp");
                        document.formMember.method= ("POST");
                        document.formMember.memberId.value= (memberId);
                        document.formMember.name.value= (memberName);
                        document.formMember.email.value= (memberEmail);
                        document.formMember.submit();
            }
            
                
                
                
                
		
    </script>
    <script>
        // fonte: https://kilianvalkhof.com/2010/javascript/how-to-build-a-fast-simple-list-filter-with-jquery/
        // Copyright (c) 2010 Kilian Valkhof

        // Permission is hereby granted, free of charge, to any person
        // obtaining a copy of this software and associated documentation
        // files (the "Software"), to deal in the Software without
        // restriction, including without limitation the rights to use,
        // copy, modify, merge, publish, distribute, sublicense, and/or sell
        // copies of the Software, and to permit persons to whom the
        // Software is furnished to do so, subject to the following
        // conditions:

        // The above copyright notice and this permission notice shall be
        // included in all copies or substantial portions of the Software.

        // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
        // EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
        // OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
        // NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
        // HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
        // WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
        // FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
        // OTHER DEALINGS IN THE SOFTWARE.



        (function ($) {
          // custom css expression for a case-insensitive contains()
          jQuery.expr[':'].Contains = function(a,i,m){
              return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
          };


          function listFilter(header, list) { // header is any element, list is an unordered list
            // create and add the filter form to the header
            //var form = $("<form>").attr({"class":"filterform","action":"#"}),
            //    input = $("<input>").attr({"class":"filterinput","type":"text"});
            //    $(form).append(input).appendTo(header);
            
            input = $(header);
            $(input)
              .change( function () {
                var filter = $(this).val();
                if(filter) {
                  // this finds all links in a list that contain the input,
                  // and hide the ones not containing the input while showing the ones that do
                  $(list).find("div:not(:Contains(" + filter + "))").parent().slideUp();
                  $(list).find("div:Contains(" + filter + ")").parent().slideDown();
                } else {
                  $(list).find("li").slideDown();
                }
                return false;
              })
            .keyup( function () {
                // fire the above change event after every letter
                $(this).change();
            });
          }


          //ondomready
          $(function () {
            listFilter($("#keyword-area"), $("#area_disponivel"));
          });
        }(jQuery));
    </script>

</body>

</html>
<%
    }
%>
