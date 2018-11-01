<%@page import="br.com.itfox.beans.EmailMktCart"%>
<%@page import="br.com.itfox.beans.Client"%>
<%@page import="br.com.itfox.beans.Product"%>
<%@page import="java.util.List"%>
<%@page import="br.com.itfox.utils.Utils"%>
<%@page import="br.com.itfox.beans.EmailMkt"%>
<%@page import="br.com.itfox.business.BusinessDelegate"%>
<%@page import="br.com.itfox.config.Preferences"%>
<!DOCTYPE html>
<html id="html">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> <%=Preferences.title%></title>
<!--
    <style>
        
        /*
        style
        **/.gray-bg{background-color: #f3f3f4;}
        .agile-list li:hover{cursor: pointer;background: #fff;}
        .agile-list li{background: #FAFAFB;border: 1px solid #e7eaec;margin: 0px 0 10px 0;padding: 10px;border-radius: 2px;font-size:12px;list-style:none;}
        .ibox-content{background-color: #ffffff;color: inherit;padding: 15px 20px 20px 20px;border-color: #e7eaec;border-image: none;border-style: solid solid none;border-width: 0px 0px;}
        .full-width{width: 100% !important;}
        h2{font-size: 24px;}
        h3{font-size: 16px;}
        .agile-detail{font-size: 12px;}
        /***/
        body{font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 14px;line-height: 1.42857143;}
        .emailMktBox{max-width: 1024px;margin: 0 auto;padding: 30px 20px 20px 20px;}
        .form-control{margin-top: 10px;width: 98%;height: 34px;padding: 6px 12px;background-color: #fff;border: 1px solid #ccc;border-radius: 4px;-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s}
        .header-marron{background-color: #ebd9c0;min-width: 300px;min-height: 50px;margin:10px -20px 10px -20px;padding: 20px 20px 40px 40px;font-family: Georgia;font-size:14px;color:#898989;font-weight: bold;text-align: center;}
        .logo-boutique{text-align: center;margin:20px;background: url(http://boutiquecellars.com/img/logo.png) no-repeat scroll 0 20px;width:227px;height:37px;text-indent: -9999px;margin: 40px auto 0 auto;padding: 40px;}
        .productImage{width:100px;height: 100px;margin: 10px;float:left;background: url(http://boutiquecellars.com/img/wine-img.jpg) no-repeat scroll top left;background-size: cover;}
        .emailMktImage{width:100px;height: 100px;margin: 10px;float:left;background: url(http://boutiquecellars.com/img/wine-img.jpg) no-repeat scroll top left;background-size: cover;}
        button{height:35px;}
        .emailmkt-element h2{}
        a{text-decoration: none;color:#333 !important}
        li.emailmkt-element{border-left: 3px solid #B3A9A9;}
        ul#area_disponivel.sortable-list.connectList.agile-list{
            padding:0;
        }
        .row{float:left;}
         /*** media screen ***/
        @media screen and (min-width: 320px) and (max-width: 414px) {
            .header-marron {
                background-color: #ebd9c0;
                min-width: 103%;
                min-height: 50px;
                margin: 10px -20px 10px -20px;
                padding: 15px;
                font-family: Georgia;
                font-size: 14px;
                color: #898989;
                /* font-weight: bold; */
                text-align: center;
            }

            .emailMktBox {
                max-width: 1024px;
                margin: 0 auto;
                padding: 10PX;
                BACKGROUND-COLOR: WHITE;
            }

            .ibox-content {
                background-color: #ffffff;
                color: inherit;
                /* padding: 10px; */
                border-color: #e7eaec;
                border-image: none;
                border-style: solid solid none;
                border-width: 0px 0px;
            }

            H2 {
                font-size: 18px;
            }

            h3 {
                font-size: 12px;
            }
        }
    </style>
-->
</head>

<body class="gray-bg" style="background-color: #f3f3f4;font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;font-size: 14px;line-height: 1.42857143;">

    <div class="emailMktBox animated fadeInDown" style="max-width: 1024px;margin: 0 auto;padding: 0px;background-color: white;">
        <div class="row" style="float:left;">

            <div class="col-md-12">
                <div class="ibox-content" style="float:left;background-color: #ffffff;color: inherit;padding: 15px 20px 20px 20px;border-color: #e7eaec;border-image: none;border-style: solid solid none;border-width: 0px 0px;">

                    <h2 class="font-bold logo-boutique" style="font-size: 24px;text-align: center;margin:20px;background: url(http://boutiquecellars.com/img/logo.png) no-repeat scroll 50px 0px;width:227px;height:37px;text-indent: -9999px;margin: 40px auto 0 auto;padding: 40px;"></h2>
                    <%
                                
                                String strEmailMktId = request.getParameter("email_mkt_id");
                                String strClientId = request.getParameter("client_id");
                                String name="";
                                String email="";
                                String address="";
                                
                                int clientId=0;
                                int emailMktId=0;
                                try{
                                    if(strEmailMktId!=null && strEmailMktId.length()>0){
                                        //productId=Integer.parseInt(id);
                                        emailMktId = Integer.parseInt(strEmailMktId);
                                        clientId=Integer.parseInt(strClientId);
                                        Client client = new BusinessDelegate().selectClient(clientId);
                                        if(client!=null){
                                            name=client.getName();
                                            email=client.getEmail();
                                            
                                        }
                                        
                                    }
                                }catch(Exception ex){
                                    br.com.itfox.utils.Logger.getLogger(ex, "Register", "Erro ao converter identificador do usuário");
                                }
                                EmailMktCart cart = new EmailMktCart();
                                EmailMkt emailMkt = new EmailMkt();
                                Client cli = new Client();
                                // set id's
                                emailMkt.setEmailMktId(emailMktId);
                                cli.setClientId(clientId);
                                // set objects
                                cart.setEmailMkt(emailMkt);
                                cart.setClient(cli);
                                // set Cart
                               
                                
                                List<EmailMktCart> listCarts = new BusinessDelegate().selectEmailMktCartFromClient(cart);
                                
                                
                                
                                
                                
                                %>

                                <p class="header-marron" style="background-color: #ebd9c0;min-width: 300px;min-height: 50px;margin:10px -20px 10px -20px;padding: 20px 20px 40px 40px;font-family: Georgia;font-size:14px;color:#898989;font-weight: bold;text-align: center;">
                       Thanks! =)
                       <br/><br/>
                       Your order will be sent to you in a shortest time. 
                        
                    </p>
                   

                    <div class="row" style="float:left;">

                        <div class="col-lg-12">
                            <form class="m-t" role="form" action="#" name="emailMkt">
                                
                                <div class="ibox">
                                    <div class="ibox-content" style="float:left;background-color: #ffffff;color: inherit;padding: 15px 20px 20px 20px;border-color: #e7eaec;border-image: none;border-style: solid solid none;border-width: 0px 0px;">
                                        <h3 style="font-size: 16px;">Details</h3>
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Name" name="name" id="name" required="" value="<%=name%>" style="margin-top: 10px;width: 98%;height: 34px;padding: 6px 12px;background-color: #fff;border: 1px solid #ccc;border-radius: 4px;-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s">
                                            <input type="email" class="form-control" placeholder="Email address" name="email" id="email" required="" value="<%=email%>" style="margin-top: 10px;width: 98%;height: 34px;padding: 6px 12px;background-color: #fff;border: 1px solid #ccc;border-radius: 4px;-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s">
                                            <!--
                                            <input type="text" class="form-control" placeholder="Address" required="" name="address" value="<%=address%>">
                                           -->
                                           <!-- hidden fields -->
                                            <input type="hidden" name="message" id="message"/>
                                            <input type="hidden" name="send" id="send" value="send"/>
                                            <input type="hidden" name="client_id" id="client" value="<%=clientId%>"/>
                                            <input type="hidden" name="email_mkt_id" id="id" value="<%=emailMktId%>"
                                        </div>
                                        
                                        
                                        
                                        
                                        <h3><br/>Your Order</h3>
                                        
                                        
                                        


                                        <ul id="area_disponivel" class="sortable-list connectList agile-list" style=" padding:0;">
                                            <%
                                            
                                            for(EmailMktCart emailCart: listCarts){
                                            %>
                                            <li class="info-element product-element" style="float:left;width:98%;border-left: 3px solid #B3A9A9;background: #FAFAFB;border: 1px solid #e7eaec;margin: 0px 0 10px 0;padding: 10px;border-radius: 2px;font-size:12px;list-style:none;">
                                                <div class="col-lg-2" style="float:left;min-width: 310px;min-height: 400px; padding: 10px">
                                                    <img src="http://boutiquecellars.com/img/wine/boutique_cellars_<%=emailCart.getProduct().getName().replaceAll(" ", "_").toLowerCase()+".png"%>" class="productImage" title="" style="width:300px;height: 400px;margin: 10px auto 10px auto;float:none;background: url(http://boutiquecellars.com/img/wine-img.jpg) no-repeat scroll top left;background-size: cover;border: 1px solid #e7eaec;">
                                                </div>
                                                <div class="col-lg-10" style="float:left;min-width: 300px;min-height: 100px;">
                                                    <h2><%=emailCart.getProduct().getName()%><br/><small>Quantity: <%=emailCart.getQuantity()%> - Price: AU$ <%=emailCart.getPrice()%></small> <br/><small>Total: AU$ <%=emailCart.getTotal()%></small></h2>

                                                    
                                                </div>
                                            </li>
                                            <%
                                            }
                                            %>

                                        </ul>
                                            

                                            
                                         <div id="result" style="width:100%;height:100px;float:left;">
                                              
                                              <div style="float: left;font-size: 26px;margin-top: 10px;background-color: #ebd9c0;width: 100%;text-align: right;margin: 0;font-family: Georgia;color: #898989;font-weight: bold;line-height: 70px;border-left: 3px solid #B3A9A9;" id="subTotal">
                                                AU$   
                                                <%
                                                cart = new BusinessDelegate().selectEmailMktCartSummary(cart);
                                                out.print(cart.getTotal());
                                                %>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              </div>
                                          </div>  

                                            
                                    </div>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row" style="float:left;margin:10px 0 10px 0">
            <div class="col-md-6" style="float:left">
                <%=Preferences.copyright%>
            </div>
            <div class="col-md-6 text-right" style="float:right">
               <small><%=Preferences.copyrightYear%></small>
            </div>
        </div>
    </div>
            <!-- Mainly scripts -->
<script src="http://boutiquecellars.com/js/jquery-2.1.1.js"></script>

<!-- Custom and plugin javascript -->

            
            <script>
                function sendEmail(){
                    var elems = document.getElementsByName("emailMkt")[0].getElementsByTagName("input");

                    for(var i = 0; i < elems.length; i++) {
                        // set attribute to property value
                        elems[i].setAttribute("value", elems[i].value);
                    }
                    
                    
                    //document.getElementById("btn_submit").style.display="none";
                    //document.getElementById("clientId").style.display="none";
                   // $("#message").val($("html").html());
                    document.forms[0].action="email-test.jsp";
                    document.forms[0].message.value="<!DOCTYPE html><html>"+document.getElementById("html").innerHTML+"</html>";
                    document.forms[0].method="post";
                    document.forms[0].submit();
                }
                function sendEmailAjax(){
                    
                    var elems = document.getElementsByName("emailMkt")[0].getElementsByTagName("input");
                    for(var i = 0; i < elems.length; i++) {
                        // set attribute to property value
                        elems[i].setAttribute("value", elems[i].value);
                    }
                    document.forms[0].message.value="<!DOCTYPE html><html>"+document.getElementById("html").innerHTML+"</html>";
                    var name=$("#name").val();
                    var email=$("#email").val();
                    var message=$("#message").val();
                    var send="send";
                    
                    //console.log(url);
                    $.post( "email-order.jsp", { name: name, email: email, send:send, message: message })
                        .done(function( data ) {
                            console.log( "Data Loaded: " + data );
                        })
                        .fail(function() {
                            console.log( "error" );
                        })
                        .always(function() {
                            console.log( "finished" );
                        });
                }
                $( document ).ready(function() {
                    sendEmailAjax();
                   
                });
                
            </script>
</body>

</html>
