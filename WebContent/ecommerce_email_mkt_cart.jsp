<%@page import="br.com.itfox.beans.EmailMkt"%>
<%@page import="br.com.itfox.beans.EmailMktCart"%>
<%@page import="br.com.itfox.beans.Client"%>
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
     <link href="css/plugins/cropit/cropit.css" rel="stylesheet">
    <style>
        .panel-collapse{
            height:auto !important;
        }
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
     <jsp:param name="page" value="ecommerce_email_mkt_cart" />
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
                   
                </div>
            </div>
            
            <!-- filter -->
           
            <!-- widgets -->
            
            <!--// widgets -->
            
            <!-- table -->
            
            <div class="row wrapper border-bottom light-bg page-heading">
                <div class="col-sm-4">
                    <h2> <input type="hidden" name="memberId" id="memberId" value="<%=session.getAttribute("userid")%>">Create Order By Email Marketing </h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="index.jsp">Home</a>
                        </li>
                        <li class="active">
                            <strong>Create Order By Email Marketing</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-sm-8">
                    <div class="title-action">
                        <a href="ManagerPictures" class="btn btn-primary btn-sm">Sincronize Images</a>
                    </div>
                </div>    
                <div class="col-lg-2">

                </div>
            </div>

        <div class="wrapper wrapper-content  animated fadeInRight">
            <%
                                String id = request.getParameter("id");
                                String operation = request.getParameter("operation");
                                String strEmailMktId = request.getParameter("email_mkt_id");
                                String strClientId = request.getParameter("client_id");
                                String strProductId = request.getParameter("product_id");
                                int clientId=0;
                                int productId = 0;
                                int emailMktId=0;
                                try{
                                    if(strEmailMktId!=null && strEmailMktId.length()>0){
                                        //productId=Integer.parseInt(id);
                                        emailMktId = Integer.parseInt(strEmailMktId);
                                        clientId=Integer.parseInt(strClientId);
                                        productId=Integer.parseInt(strProductId);
                                        
                                    }
                                }catch(Exception ex){
                                    br.com.itfox.utils.Logger.getLogger(ex, "Register", "Erro ao converter identificador do usuário");
                                }
                                EmailMktCart cart = new EmailMktCart();
                                EmailMkt email = new EmailMkt();
                                Client cli = new Client();
                                Product prod = new Product();
                                
                                email.setEmailMktId(emailMktId);
                                cli.setClientId(clientId);
                                prod.setProductId(productId);
                                cart.setEmailMkt(email);
                                cart.setClient(cli);
                                cart.setProduct(prod);
                               
                                
                                cart = new BusinessDelegate().selectEmailMktCart(cart);
                               // List<EmailMktCart> listCart = new BusinessDelegate().selectEmailMktCart(cart);
                                
                                
                                
                                %>
            <div class="row">
                 <form method="POST" action="ManagerEmailMktCart" name="managerEmailMktCart">
                <div class="col-lg-12">
                     <div class="ibox">
                        <div class="ibox-content">
                    <div class="tabs-container">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#tab-1"> Create Email Marketing Order</a></li>
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
                                            <div class="form-group"><label class="col-sm-2 control-label">Email Marketing Name</label>
                                                <div class="col-sm-10">
                                                    <select name="email_mkt_id" id="email_mkt_id">
                                                     <%
                                                         List<EmailMkt> listEmailMkt = new BusinessDelegate().selectEmailMkts();
                                                         for(EmailMkt emailMkt: listEmailMkt){
                                                     %>
                                                     <option value="<%=emailMkt.getEmailMktId()%>" <% if(emailMkt.getEmailMktId()==emailMktId){out.print("selected");}%> ><%=emailMkt.getName()%></option>
                                                     <%
                                                         }
                                                     %>
                                                     </select>
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label"  >Client:</label>
                                                <div class="col-sm-10">
                                                    <select name="client_id" id="client_id" >
                                                     <%
                                                         List<Client> listClient = new BusinessDelegate().selectClients();
                                                         for(Client c: listClient){
                                                     %>
                                                     <option value="<%=c.getClientId()%>" <% if(c.getClientId()==clientId){out.print("selected");}%> ><%=c.getName()%></option>
                                                     <%
                                                         }
                                                     %>
                                                     </select>
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Product:</label>
                                                <div class="col-sm-10">
                                                    <select name="product_id" id="product_id" >
                                                     <%
                                                         List<Product> listProduct = new BusinessDelegate().selectProducts();
                                                         for(Product p: listProduct){
                                                     %>
                                                     <option value="<%=p.getProductId()%>" <% if(p.getProductId()==productId){out.print("selected");}%> ><%=p.getName()%></option>
                                                     <%
                                                         }
                                                     %>
                                                     </select>
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Quantity:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" placeholder="..." name="quantity" value="<%if(cart!=null && cart.getEmailMkt()!=null && cart.getEmailMkt().getEmailMktId()>0){out.print(cart.getQuantity());}%>" >
                                                    
                                                </div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Price:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" disabled="disabled" placeholder="Price of Product" name="price" value="<%if(cart!=null && cart.getEmailMkt()!=null && cart.getEmailMkt().getEmailMktId()>0){out.print(cart.getProduct().getPrice());}%>"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Total:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" disabled="disabled" placeholder="Total" name="total" value="<%if(cart!=null && cart.getEmailMkt()!=null && cart.getEmailMkt().getEmailMktId()>0){out.print(cart.getQuantity()*cart.getProduct().getPrice());}%>"></div>
                                            </div>
                                            <div class="form-group"><label class="col-sm-2 control-label">Status:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control" placeholder="Status" name="status" value="<%if(cart!=null && cart.getEmailMkt()!=null && cart.getEmailMkt().getEmailMktId()>0){out.print(cart.getStatus());}%>"></div>
                                            </div>
                                           
                                            <div class="form-group">
                                                <div class="right" style="float:right">
                                                    <div class="btn-group">
                                                        <input type="hidden" name="id" id="id" value="<%if(cart!=null && cart.getEmailMkt()!=null && cart.getEmailMkt().getEmailMktId()>0 ){out.print(cart.getEmailMkt().getEmailMktId());}%>" />
                                                         <input type="hidden" name="operation" id="operation" value="<%if(cart!=null && cart.getEmailMkt()!=null  &&  cart.getEmailMkt().getEmailMktId()>0){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("delete");}else{out.print("update");}}else{out.print("insert");}%>" />
                                    
                                                    <button type="submit" class="btn btn-primary block full-width m-b" style="<% if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("background-color:red;border-color:red;");}%>"><%if(cart!=null && cart.getEmailMkt()!=null  &&  cart.getEmailMkt().getEmailMktId()>0 ){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("Delete Item Order");}else{out.print("Update Item Order");}}else{out.print("Create New Item Order");}%></button>
                                                    </div>
                                                </div>
                                                    
                                            </div>
                                                    
                                            
                                        </fieldset>

                                    </div>
                                </div>
                                
                            </div>
                    </div>
                        </div>
                     </div>
                </div>
                </form>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Orders Grouped By Client</h3>
                            <p class="small"><i class="fa fa-hand-o-up"></i> Select Orders by Clients</p>

                            <div class="input-group">
                                <input type="text" id="keyword-area" placeholder="Add new task. " class="input input-sm form-control">
                                <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-search"></i> Search</button>
                                </span>
                            </div>
                            
                            
                            
                                
                            <div class="row">
                                        <div class="col-lg-12">
                                            <div class="wrapper wrapper-content animated fadeInRight">

                                                <div class="ibox-content m-b-sm border-bottom">
                                                    <div class="text-center p-lg">
                                                        <h2>If you don't find the pictures on page</h2>
                                                        <span>add your pictures by selecting </span>
                                                        <button title="Sincronize Images" class="btn btn-primary btn-sm" onclick="document.location.href=\"ManagerPictures\""><i class="fa fa-plus"></i> <span class="bold">Sincronize Images</span></button> button
                                                    </div>
                                                </div>
                                                <%
                                                List<EmailMktCart> listCarts = new BusinessDelegate().selectEmailMktCartGroupByClient();
                                                for(EmailMktCart emailCart: listCarts){
                                                %>
                                                <div class="faq-item">
                                                    <div class="row">
                                                        <div class="col-md-7">
                                                            <a data-toggle="collapse" href="#faq<%=emailCart.getClient().getClientId()%>" class="faq-question"><%=emailCart.getClient().getName()%></a>
                                                            <small>Number of Orders:  <strong><%=emailCart.getStatus()%></strong> - Sum of Orders: <i class="fa fa-dollar"></i> <%=emailCart.getTotal()%></small>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div id="faq<%=emailCart.getClient().getClientId()%>" class="panel-collapse collapse ">
                                                                <div class="faq-answer">
                                                                    
                                                                    <%
                                                                    EmailMktCart cartFromClient = new EmailMktCart();
                                                                    EmailMkt emkt = new EmailMkt();
                                                                    emkt.setEmailMktId(emailCart.getEmailMkt().getEmailMktId());
                                                                    Client client = new Client();
                                                                    client.setClientId(emailCart.getClient().getClientId());
                                                                    cartFromClient.setEmailMkt(emkt);
                                                                    cartFromClient.setClient(client);
                                                                    List<EmailMktCart> cartsFromClient = new BusinessDelegate().selectEmailMktCartFromClientSumary(cartFromClient);
                                                                    for(EmailMktCart eCart: cartsFromClient){
                                                                    %>
                                                                    <p>
                                                                      <a data-toggle="collapse" href="#faq-mkt<%=eCart.getEmailMkt().getEmailMktId()%><%=eCart.getClient().getClientId()%>" class="faq-question">  
                                                                          <%=eCart.getEmailMkt().getEmailMktId()%> - <%=eCart.getEmailMkt().getName()%>
                                                                      </a><br/>
                                                                      <small>Number of Orders:  <strong><%=eCart.getStatus()%></strong> - Sum of Orders: <i class="fa fa-dollar"></i> <%=eCart.getTotal()%></small>
                                                                        <div id="faq-mkt<%=eCart.getEmailMkt().getEmailMktId()%><%=eCart.getClient().getClientId()%>"  class="panel-collapse collapse ">
                                                                            <ul id="area_disponivel" class="sortable-list connectList agile-list">
                                                                                <%
                                                                                EmailMktCart cartFromClientSelected = new EmailMktCart();
                                                                                EmailMkt emktSelected = new EmailMkt();
                                                                                emktSelected.setEmailMktId(eCart.getEmailMkt().getEmailMktId());
                                                                                Client clientSelected = new Client();
                                                                                clientSelected.setClientId(eCart.getClient().getClientId());
                                                                                cartFromClientSelected.setEmailMkt(emktSelected);
                                                                                cartFromClientSelected.setClient(clientSelected);
                                                                                List<EmailMktCart> cartsSelected = new BusinessDelegate().selectEmailMktCartFromClient(cartFromClientSelected);
                                                                                for(EmailMktCart emailCartSelected: cartsSelected){
                                                                                %>
                                                                                <li class="info-element product-element">
                                                                                    <div class="col-lg-2">
                                                                                        <img src="https://boutiquecellars.com/img/wine/boutique_cellars_<%=emailCartSelected.getProduct().getName().replaceAll(" ", "_").toLowerCase()+".png"%>" class="productImage" title="Utils.blobToString(emailCart.getProduct().getPic1())">
                                                                                    </div>
                                                                                    <div class="col-lg-10">
                                                                                        <h2><%=emailCartSelected.getProduct().getName()%><br/><small>Quantity: <%=emailCartSelected.getQuantity()%> - Price: AU$ <%=emailCartSelected.getPrice()%></small> <br/><small>Total: AU$ <%=emailCartSelected.getTotal()%></small></h2>

                                                                                        <div class="agile-detail">
                                                                                             <a href="ecommerce_email_mkt_cart.jsp?email_mkt_id=<%=emailCartSelected.getEmailMkt().getEmailMktId()%>&client_id=<%=emailCartSelected.getClient().getClientId()%>&product_id=<%=emailCartSelected.getProduct().getProductId()%>" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-edit"></i> Edit</a>
                                                                                             <a href="ecommerce_email_mkt_cart.jsp?email_mkt_id=<%=emailCartSelected.getEmailMkt().getEmailMktId()%>&client_id=<%=emailCartSelected.getClient().getClientId()%>&product_id=<%=emailCartSelected.getProduct().getProductId()%>&operation=delete" class="pull-right btn btn-xs btn-white" style="margin:0 10px 0 10px"> <i class="fa fa-remove"></i> Delete</a>


                                                                                        </div>
                                                                                    </div>
                                                                                </li>
                                                                                <%
                                                                                }
                                                                                %>

                                                                            </ul>
                                                                            
                                                                        </div>
                                                                    </p>
                                                                    <%
                                                                    }
                                                                    %>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>            
                                                
                                                
                                                

                                            </div>
                                        </div>
                                    </div>    
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

<!-- Image cropper -->
    <script src="js/plugins/cropper/cropper.min.js"></script>
    <script src="js/plugins/cropit/jquery.cropit.js"></script>

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
           /*
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
            */
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
                        
                       
                       
                /*****
                 * 
                 * @param {type} memberId
                 * @param {type} memberName
                 * @param {type} memberEmail
                 * @returns {undefined}
                 * 
                 * Cropit
                 */       
                $('.image-editor').cropit();
                
                $('.cropit-image-input').change(function(){
                  //document.forms["managerProduct"].action="";
                  //document.forms["managerProduct"].submit()
                  // Move cropped image data to hidden input
                    var imageData = $('.image-editor').cropit('export');
                    $('.hidden-image-data').val(imageData);

                    // Print HTTP request params
                    //var formValue = $(this).serialize();
                    //$('#result-data').text(formValue);
                    
                    //document.forms[0].submit();

                    //console.log($('image-data').val());
                    // Prevent the form from actually submitting
                    return false;
               
                });
                
                $('form').submit(function() {
                   // console.log("hello");
                    // Move cropped image data to hidden input
                    var imageData = $('.image-editor').cropit('export');
                    $('.hidden-image-data').val(imageData);

                    // Print HTTP request params
                    //var formValue = document.forms["managerProduct"].image_data.value;//$(this).serialize();
                    //formValue=$(formValue).serialize();
                    //$('#result-data').text('<img src=\"+'+formValue+'\">');
                    //document.forms["managerProduct"].result2.value=formValue;
                    // Prevent the form from actually submitting
                    document.managerProduct.method="POST";
                    document.managerProduct.action="ManagerProduct";
                    document.managerProduct.submit();
                    return false;
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
