<%@page import="br.com.itfox.beans.Profile"%>
<%@page import="java.util.List"%>
<%@page import="br.com.itfox.business.BusinessDelegate"%>
<%@page import="br.com.itfox.config.Preferences"%>
<%@include file="incProfileAdmin.jsp" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><%=Preferences.title%></title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <!--
    <link href="css/login/style.css" rel="stylesheet">
    -->
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <!--
   <link href="css/plugins/slick/slick.css" rel="stylesheet">
    <link href="css/plugins/slick/slick-theme.css" rel="stylesheet">
    -->
    <style >
        ::-webkit-input-placeholder { color: #333333; opacity: 1 !important; }
        :-moz-placeholder { color: #333333;}
        ::-moz-placeholder {color: #333333;}
        :-ms-input-placeholder {color: #333333;}
        input[placeholder]::-webkit-input-placeholder{color:#999999;}
        button.btn-primary{
            background-color: #1ab394;
            border-color: #1ab394;
            color: #FFFFFF;
        }
        select#permission{color:#999999;}
    </style>
         

</head>

<body class="fixed-sidebar">
    <div id="wrapper">
    <%
   String paramPrint = request.getParameter("print");
   String printStyle = "";
   if(paramPrint==null){
   %>
    <jsp:include page="incNavSide.jsp">
        <jsp:param name="page" value="register" />
    </jsp:include>
    <%
   }else{
       printStyle="margin:0;";
   }
    %>
    <div id="page-wrapper" class="gray-bg" style="background-color:#f3f3f4;<%=printStyle%>">
        <div class="row border-bottom">
             <jsp:include page="incNavbarStaticTop.jsp" />
        </div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <%
                    if(paramPrint==null){
                    %>
                        <div class="text-center m-t-lg  logo-assobens-relatorio">
                    <%
                    }else{
                    %>    
                       <div class="text-center m-t-lg  logo-quest-relatorio">
                    <%
                        }
                     %> 
                    <!--
                        <h1>
                            Análises Adicionais
                            
                        </h1>-->

                        <div class="middle-box text-center loginscreen   animated fadeInDown">
                            <div>
                                <!--
                                <div>

                                    <h1 class="logo-name">Q</h1>

                                </div>-->
                                <h3>Register to Boutique Cellars</h3>
                                <p>Create account to see it in action.</p>
                                <%
                                String id = request.getParameter("id");
                                String operation = request.getParameter("operation");
                                int memberId = 0;
                                try{
                                    if(id!=null && id.length()>0){
                                        memberId=Integer.parseInt(id);
                                    }
                                }catch(Exception ex){
                                    br.com.itfox.utils.Logger.getLogger(ex, "Register", "Erro ao converter identificador do usuário");
                                }
                                Member m = new BusinessDelegate().selectMembers(memberId);
                                out.print(m.getEmail());
                                String data = "";
                                if(m!=null &&  m.getMemberId()>0){data = (br.com.itfox.utils.Utils.dateFormat_dd_MM_yyyy(m.getDate().toString()));}
                                %>
                                <form class="m-t" role="form" action="Register">
                                    <div class="form-group">
                                        <input name="name" type="text" class="form-control" placeholder="Name" required="" value="<%if(m!=null &&  m.getMemberId()>0){out.print(m.getName());}%>">
                                    </div>
                                    <div class="form-group">
                                        <input name="email" type="email" class="form-control" placeholder="Email" required="" value="<%if(m!=null &&  m.getMemberId()>0){out.print(m.getEmail());}%>">
                                    </div>
                                    <div class="form-group">
                                        <input name="password" type="password" class="form-control" placeholder="Password" required="" value="<%if(m!=null &&  m.getMemberId()>0){out.print(m.getPass());}%>">
                                    </div>
                                    <div class="form-group">
                                        <input name="date" id="date" type="text" class="form-control" placeholder="Date DD/MM/AA" required="" value="<%=data%>">
                                    </div>
                                    <div class="form-group">
                                        <select id="permission" name="permission" class="form-control m-b" title="Select a profile">
                                                <%
                                                    List<Profile> listProfile = new BusinessDelegate().selectProfile();
                                                    for(Profile p:listProfile){
                                                %>
                                                <option value="<%=p.getPermission()%>" <%if(m!=null &&  m.getMemberId()>0 && p.getPermission().equalsIgnoreCase(m.getPermission())){out.print("selected");}%> ><%=p.getProfile()%></option>
                                                       
                                                <%
                                                    }
                                                %>      
                                        </select>                
                                    </div>
                                    <!--
                                    <div class="form-group">
                                            <div class="checkbox i-checks"><label> <input name="terms" type="checkbox"><i></i> Concordo com os termos e a política de privacidade </label></div>
                                    </div>
                                    -->
                                    <input type="hidden" name="member_id" id="member_id" value="<%if(m!=null &&  m.getMemberId()>0 ){out.print(m.getMemberId());}%>" />
                                    <input type="hidden" name="operation" id="operation" value="<%if(m!=null &&  m.getMemberId()>0 ){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("delete");}else{out.print("update");}}else{out.print("insert");}%>" />
                                    <button type="submit" class="btn btn-primary block full-width m-b" style="<% if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("background-color:red;border-color:red;");}%>"><%if(m!=null &&  m.getMemberId()>0 ){if(operation!=null && !operation.isEmpty() && operation!="" && operation.equalsIgnoreCase("delete")){out.print("Delete user");}else{out.print("Update User");}}else{out.print("Create new user");}%></button>
                                    <!--
                                    <p class="text-muted text-center"><small>Já possui uma conta?</small></p>
                                    <a class="btn btn-sm btn-white btn-block" href="login.html">Login</a>
                                    -->
                                </form>
                                <p class="m-t"> <small> <%=Preferences.copyright%> <%=Preferences.copyrightYear%></small> </p>
                            </div>
                        </div>
                     
                       </div>
                   
                        <!--</div>-->
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

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- datepicker -->
     
    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <!-- Picker Calendar -->
    <script src="js/plugins/jquery-ui-1.11-2.4/jquery-ui.js"></script>
    
    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.js"></script>
    <!-- Custom local javascript -->
    <script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
            
            // formatando o date picker for inicial date 
			 $( "#date" ).datepicker({
				  changeMonth: true,
				  changeYear: true,
                                   dateFormat: 'dd/mm/yy',
                                    minDate: new Date(<%=2000%>,<%=0%>,<%=01%>),
                                    maxDate:1,
                                    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                                    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                                    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
                                    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                                    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                                    nextText: 'Próximo',
                                    prevText: 'Anterior'
				});
			 $( "#date" ).datepicker( "option", "dateFormat", "dd/mm/yy" );	
                         var data = $.datepicker.parseDate("dd/mm/yy", "<%=data%>" );   
                         $( "#date" ).val($.datepicker.formatDate('dd/mm/yy', data));      
        });
    </script>
</body>

</html>
