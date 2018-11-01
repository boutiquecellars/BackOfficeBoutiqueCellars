<%@page import="br.com.itfox.beans.EmailMkt"%>
<%@page import="br.com.itfox.utils.Utils"%>
<%@page import="br.com.itfox.beans.Product"%>
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
    
    <link href="css/plugins/cropper/cropper.min.css" rel="stylesheet">
    
    <style>
        .img-preview-sm, .img-preview img{
         width:94px !important;
         height:125px !important;
        }
        .btn-primary{
            background-color: #7e6036;
            color:white;
            margin-right: 10px;
        }
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
     <jsp:param name="page" value="ecommerce_email_mkt" />
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
                    <h2> <input type="hidden" name="memberId" id="memberId" value="<%=session.getAttribute("userid")%>">Create Email Marketing </h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.jsp">Home</a>
                        </li>
                        <li class="active">
                            <strong>Create Email Marketing</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>

        <div class="wrapper wrapper-content  animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Email Marketing</h3>
                            <p class="small"><i class="fa fa-hand-o-up"></i> Select Email Marketing</p>

                            <div class="input-group">
                                <input type="text" id="keyword-area" placeholder="Add new task. " class="input input-sm form-control">
                                <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-search"></i> Search</button>
                                </span>
                            </div>
                            
                            <%
                                String id = request.getParameter("id");
                                String operation = request.getParameter("operation");
                                int emailMktId = 0;
                                try{
                                    if(id!=null && id.length()>0){
                                        emailMktId=Integer.parseInt(id);
                                    }
                                }catch(Exception ex){
                                    br.com.itfox.utils.Logger.getLogger(ex, "Register", "Erro ao converter identificador do usuário");
                                }
                                EmailMkt e = new BusinessDelegate().selectEmailMkt(emailMktId);
                                
                                
                                
                                %>
                            <ul id="area_disponivel_email" class="sortable-list connectList agile-list">
                                <%
                                List<EmailMkt> emails = new BusinessDelegate().selectEmailMkts();
                                for(EmailMkt email: emails){
                                %>
                                <li class="info-element">
                                    <%=email.getName()%>
                                    <div class="agile-detail">
                                        <a href="email_marketing_send_light.jsp?id=<%=email.getEmailMktId()%>&operation=send" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-edit"></i> Send</a>
                                         <a href="ecommerce_email_mkt.jsp?id=<%=email.getEmailMktId()%>&operation=update" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-edit"></i> Edit</a>
                                        <a href="ecommerce_email_mkt.jsp?id=<%=email.getEmailMktId()%>&operation=delete" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-remove"></i> Delete</a>
                                        
                                        <i class="fa fa-clock-o"></i> <%=email.getMetaTagDescription() %>
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
             <div class="row">
                 <form method="POST" action="ManagerEmailMkt">
                <div class="col-lg-12">
                    <div class="tabs-container">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#tab-1"> Create Email Marketing</a></li>
                                 <% if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("update")){%>   
                                <li class=""><a data-toggle="tab" href="#tab-2"> Select Products</a></li>
                                <li class=""><a  href="email_marketing_send_light.jsp?id=<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getEmailMktId());}%>"> Preview Email Marketing</a></li>
                                <%
                                 }
                                %>
                                <!--
                                <li class=""><a data-toggle="tab" href="#tab-2"> Data</a></li>
                                <li class=""><a data-toggle="tab" href="#tab-3"> Discount</a></li>
                                <li class=""><a data-toggle="tab" href="#tab-4"> Images</a></li>
                                -->
                            </ul>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active">
                                    <div class="panel-body">

                                        <fieldset class="form-horizontal">
                                            <div class="form-group"><label class="col-sm-2 control-label">Title:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Product name" name="name" value="<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getName());}%>"></div>
                                            </div>
                                           
                                            <div class="form-group"><label class="col-sm-2 control-label">Description:</label>
                                                <div class="col-sm-10">
                                                    <div class="summernote">
                                                        <textarea  class="form-control" name="description"><%if(e!=null &&  e.getEmailMktId()>0){out.print(Utils.blobToString(e.getDescription()));}%></textarea>
                                                        

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Meta Tag Title:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="..." name="meta_tag_title" value="<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getMetaTagTitle());}%>" ></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Meta Tag Description:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Sheets containing Lorem" name="meta_tag_description" value="<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getMetaTagDescription());}%>"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Meta Tag Keywords:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Lorem, Ipsum, has, been" name="meta_tag_keywords" value="<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getMetaTagKeywords());}%>"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Meta Tag URL:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Lorem, Ipsum, has, been" name="meta_tag_url" value="<%if(e!=null &&  e.getEmailMktId()>0){out.print(e.getMetaTagUrl());}%>"></div>
                                            </div>
                                            
                                            
                                            <div class="form-group">
                                                <div class="right" style="float:right">
                                                    <div class="btn-group">
                                                        <input type="hidden" name="id" id="email_mkt_id" value="<%if(e!=null &&  e.getEmailMktId()>0 ){out.print(e.getEmailMktId());}%>" />
                                                         <input type="hidden" name="operation" id="operation" value="<%if(e!=null &&  e.getEmailMktId()>0 ){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("delete");}else{out.print("update");}}else{out.print("insert");}%>" />
                                    
                                                    <button type="submit" class="btn btn-primary block full-width m-b" style="<% if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("background-color:red;border-color:red;");}%>"><%if(e!=null &&  e.getEmailMktId()>0 ){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("Delete Email Marketing");}else{out.print("Update Email Marketing");}}else{out.print("Create New Email Marketing");}%></button>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                        </fieldset>

                                    </div>
                                </div>
                                <% if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("update")){
                                %>                    
                                <div id="tab-2" class="tab-pane">
                                    <div class="panel-body">
                                        Visualizar
                                        <div class="wrapper wrapper-content  animated fadeInRight">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="ibox">
                                                        <div class="ibox-content">
                                                            <h3>Products Available</h3>
                                                            <p class="small"><i class="fa fa-hand-o-up"></i> Drag and Drop Between Lists</p>

                                                            <div class="input-group">
                                                                <input type="text" id="keyword-area-product" placeholder="Add new task. " class="input input-sm form-control">
                                                                <span class="input-group-btn">
                                                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-search"></i> Search</button>
                                                                </span>
                                                            </div>


                                                            <ul id="area_disponivel" class="sortable-list connectList agile-list">
                                                                <%
                                                                List<Product> products = new BusinessDelegate().selectEmailMktProductDisponible(emailMktId);
                                                                for(Product p: products){
                                                                %>
                                                                <li class="warning-element" id="<%=p.getProductId()%>">

                                                                    <div class="agile-detail">
                                                                        <a href="#" class="pull-right btn btn-xs btn-white click_area">&#8594;</a>
                                                                        <a href="#" class="pull-right btn btn-xs btn-white">Produto: <%=p.getName()%></a>

                                                                        <i class="fa fa-clock-o"></i> <%=p.getMetaTagDescription()%>
                                                                    </div>
                                                                </li>
                                                                <%
                                                                }
                                                                %>

                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="ibox">
                                                        <div class="ibox-content">
                                                            <h3>E-Mail Marketing Products List</h3>
                                                            <p class="small"><i class="fa fa-hand-o-up"></i> Drag and Drop Between Lists</p>
                                                            <ul id="area_selecionada" class="sortable-list connectList agile-list">
                                                                <%

                                                                List<Product> productsSelected = new BusinessDelegate().selectEmailMktProduct(emailMktId);
                                                                for(Product p: productsSelected){
                                                                %>
                                                                <li class="success-element" id="<%=p.getProductId()%>">

                                                                    <div class="agile-detail">
                                                                        <a href="#" class="pull-right btn btn-xs btn-white click_area">&#8592;</a>
                                                                        <a href="#" class="pull-right btn btn-xs btn-white">Produto: <%=p.getName()%></a>

                                                                        <i class="fa fa-clock-o"></i> <%=p.getMetaTagDescription()%>
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
                                    </div>  
                                </div>
                                <%
                                }
                                %>
                                <!--
                                <div id="tab-2" class="tab-pane">
                                    <div class="panel-body">

                                        <fieldset class="form-horizontal">
                                            <div class="form-group"><label class="col-sm-2 control-label">ID:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="543"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Model:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="..."></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Location:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="location"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Tax Class:</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" >
                                                        <option>option 1</option>
                                                        <option>option 2</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Quantity:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Quantity"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Minimum quantity:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="2"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Sort order:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="0"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Status:</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" >
                                                        <option>option 1</option>
                                                        <option>option 2</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </fieldset>


                                    </div>
                                </div>
                                <div id="tab-3" class="tab-pane">
                                    <div class="panel-body">

                                        <div class="table-responsive">
                                            <table class="table table-stripped table-bordered">

                                                <thead>
                                                <tr>
                                                    <th>
                                                        Group
                                                    </th>
                                                    <th>
                                                        Quantity
                                                    </th>
                                                    <th>
                                                        Discount
                                                    </th>
                                                    <th style="width: 20%">
                                                        Date start
                                                    </th>
                                                    <th style="width: 20%">
                                                        Date end
                                                    </th>
                                                    <th>
                                                        Actions
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                            <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <select class="form-control" >
                                                            <option selected>Group 1</option>
                                                            <option>Group 2</option>
                                                            <option>Group 3</option>
                                                            <option>Group 4</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="10">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" placeholder="$10.00">
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="input-group date">
                                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" value="07/01/2014">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>

                                                </tbody>

                                            </table>
                                        </div>

                                    </div>
                                </div>
                                <div id="tab-4" class="tab-pane">
                                    <div class="panel-body">

                                        <div class="table-responsive">
                                            <table class="table table-bordered table-stripped">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        Image preview
                                                    </th>
                                                    <th>
                                                        Image url
                                                    </th>
                                                    <th>
                                                        Sort order
                                                    </th>
                                                    <th>
                                                        Actions
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/2s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image1.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="1">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/1s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image2.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="2">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/3s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image3.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="3">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/4s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image4.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="4">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/5s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image5.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="5">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/6s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image6.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="6">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <img src="img/gallery/7s.jpg">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" disabled value="http://mydomain.com/images/image7.png">
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" value="7">
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-white"><i class="fa fa-trash"></i> </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                                -->
                            </div>
                    </div>
                </div>
                </form>
            </div>
                    <!--            
                                <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title  back-change">
                        <h5>Image Adjust <small></small></h5>
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
                        <p>
                            Upload your wine picture
                        </p>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="image-crop">
                                    <img src="img/wine-img.jpg">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h4>Preview image</h4>
                                <div class="img-preview img-preview-sm"></div>
                                
                                <p>
                                    You can upload new image to crop container and easy download new cropped image.
                                </p>
                                <div class="btn-group">
                                    <label title="Upload image file" for="inputImage" class="btn btn-primary">
                                        <input type="file" accept="image/*" name="file" id="inputImage" class="hide">
                                        Upload new image
                                    </label>
                                    <label title="Donload image" id="download" class="btn btn-primary">Download</label>
                                </div>
                                
                                <div class="btn-group">
                                    <button class="btn btn-white" id="zoomIn" type="button">Zoom In</button>
                                    <button class="btn btn-white" id="zoomOut" type="button">Zoom Out</button>
                                    <button class="btn btn-white" id="rotateLeft" type="button">Rotate Left</button>
                                    <button class="btn btn-white" id="rotateRight" type="button">Rotate Right</button>
                                    <button class="btn btn-warning" id="setDrag" type="button">New crop</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
          -->                          

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

<!-- Image cropper -->
    <script src="js/plugins/cropper/cropper.min.js"></script>

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
            /*
            var $image = $(".image-crop > img")
            $($image).cropper({
                aspectRatio: 2/3,
                preview: ".img-preview",
                done: function(data) {
                    // Output the result data for cropping image.
                }
            });
            */
           
            var $image = $(".img-crop > img")
            $($image).cropper({
                aspectRatio: 2/3,
                preview: ".img-preview",
                done: function(data) {
                    // Output the result data for cropping image.
                }
            });
            if (window.FileReader) {
                $image.change(function() {
                    var fileReader = new FileReader(),
                            files = this.files,
                            file;

                    if (!files.length) {
                        return;
                    }

                    file = files[0];

                    if (/^image\/\w+$/.test(file.type)) {
                        fileReader.readAsDataURL(file);
                        fileReader.onload = function () {
                            $image.val("");
                            $image.cropper("reset", true).cropper("replace", this.result);
                        };
                    } else {
                        showMessage("Please choose an image file.");
                    }
                });
            } else {
                $image.addClass("hide");
            }

            $("#download").click(function() {
                //window.open($image.cropper("getDataURL"));
                window.open($image.cropper("getDataURL"));
                
            });
            /*
            $("#zoomIn").click(function() {
                $image.cropper("zoom", 0.1);
            });

            $("#zoomOut").click(function() {
                $image.cropper("zoom", -0.1);
            });

            $("#rotateLeft").click(function() {
                $image.cropper("rotate", 45);
            });

            $("#rotateRight").click(function() {
                $image.cropper("rotate", -45);
            });

            $("#setDrag").click(function() {
                $image.cropper("setDragMode", "crop");
            });

            $('#data_1 .input-group.date').datepicker({
                todayBtn: "linked",
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            });
            */
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
			 
            
                
                
                
                
		
    </script>
    
    <script>
        $(document).ready(function() {
            
			
                       
                       // drag and drop
                        $(".sortable-list").sortable({
                            connectWith: ".connectList",
                            stop: function(event, ui) {
                                updateLi(ui.item);
                            }
                            
                            
                        }).disableSelection().on("click", ".click_area", function() {
                            // First figure out which list the clicked element is NOT in...
                            var otherUL = $(".sortable-list").not($(this).closest("ul"));
                            var li = $(this).closest("li");

                            // Move the li to the other list. prependTo() can also be used instead of appendTo().
                            li.detach().appendTo(otherUL);

                            // Finally, switch the class on the li, and change the arrow's direction.
                            updateLi(li);
                        });

          
             });
			 
            function updateLi(li) {
                var clickArea = li.find(".click_area");
                // var myOrder = $(this).sortable('toArray').toString();
                     el = li.attr('id');
                     //areaFinal = li.closest("ul").attr("id");           
                     //console.log(el+"-"+areaFinal);
                if (li.closest("ul").is("#area_selecionada")) {
                    //li.removeClass("ui-state-default").addClass("ui-state-highlight");
                    li.attr('class', 'success-element');
                    clickArea.html('&#8592;');
                    // insert element in database
                    insertEmailMktProduct($('#email_mkt_id').val(),el);
                } else {
                    //li.removeClass("ui-state-highlight").addClass("ui-state-default");
                    li.attr('class', 'danger-element');
                    clickArea.html('&#8594;');
                    // delete element from database
                    deleteEmailMktProduct($('#email_mkt_id').val(),el);
                }    
            }    
              
            function insertEmailMktProduct(emailMktId, productId){
                    //console.log("insert:"+memberId+","+areaOper)
                    $("#progress-report").html("Aguarde, processando...");  
                    //var memberId = "";
                    //var areaOper="";
                    var url = "ManagerEmailMktProduct?operation=insert&product_id="+productId+"&email_mkt_id="+emailMktId;
		//console.log(url);
				$.getJSON(url, function (result) {
                                    
					if (result && result.length) {
						var r = [];
						  $.each( result, function( key, val ) {
							  r.push(val.insert);
						  });
						 console.log("insert result:"+r)
                                                 $("#progress-report").html("Status:"+r); 
                                                 //if(eval(result)>0){
                                                 //    document.location.href ="DownloadReportEnriquecer";
                                                 //}
                                                 //alert(total);
						 // $("#periodoIni").html(periodoIni);
						 // $("#periodoFim").html(periodoFim);
					}
				});
                }
                
                function deleteEmailMktProduct(emailMktId, productId){
                    //console.log("insert:"+memberId+","+areaOper)
                    $("#progress-report").html("Aguarde, processando...");  
                    //var memberId = "";
                    //var areaOper="";
                    var url = "ManagerEmailMktProduct?operation=delete&product_id="+productId+"&email_mkt_id="+emailMktId;
		//console.log(url);
				$.getJSON(url, function (result) {
                                    
					if (result && result.length) {
						var r = [];
						  $.each( result, function( key, val ) {
							  r.push(val.insert);
						  });
						 console.log("delete result:"+r)
                                                 $("#progress-report").html("Status:"+r); 
                                                 //if(eval(result)>0){
                                                 //    document.location.href ="DownloadReportEnriquecer";
                                                 //}
                                                 //alert(total);
						 // $("#periodoIni").html(periodoIni);
						 // $("#periodoFim").html(periodoFim);
					}
				});
                }
                
                
		
    </script>
     <script>
        // fonte: http://kilianvalkhof.com/2010/javascript/how-to-build-a-fast-simple-list-filter-with-jquery/
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
                  //console.log( $(list).find(":Contains(" + filter + ")"));
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
            listFilter($("#keyword-area-product"), $("#area_disponivel"));
           // listFilter($("#keyword-area"), $("#area_disponivel_email"));
          });
        }(jQuery));
    </script>

</body>

</html>
<%
    }
%>
