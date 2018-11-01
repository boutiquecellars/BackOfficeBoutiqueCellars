<%@page import="br.com.itfox.config.Preferences"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><%=Preferences.title%></title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/login/style.css" rel="stylesheet">

</head>

<body class="white-bg  bg-login" id="login">

    <div class="loginColumns animated fadeInDown" style="padding-top:20px;">
        <div class="row" >

            <div class="col-md-6">
               <!--
                <h2 class="font-bold">Seja bem vindo a QUEST Inteligência</h2>

                <p>
                    Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                </p>

                <p>
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
                </p>

                <p>
                    When an unknown printer took a galley of type and scrambled it to make a type specimen book.
                </p>

                <p>
                    <small>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</small>
                </p>
               -->

            </div>
            <div class="col-md-6">
                <div class="ibox-content" style="padding-top:0px">
                    <form class="m-t" role="form" action="Login">
                        <div class="form-group">
                            <input name="email" type="email" class="form-control" placeholder="User" required="">
                        </div>
                        <div class="form-group">
                            <input name="password" type="password" class="form-control" placeholder="Password" required="">
                        </div>
                        <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

                        <a href="forgot_password.jsp">
                            <small>Forgot Password?</small>
                        </a>
                        <!--
                        <p class="text-muted text-center">
                            <small>Não possui uma conta?</small>
                        </p>
                        <a class="btn btn-sm btn-white btn-block" href="register.jsp">Criar uma conta</a>
                        -->
                    </form>
                    <p class="m-t">
                        <small><%=Preferences.copyright%> <%=Preferences.copyrightYear%></small>
                    </p>
                </div>
            </div>
        </div>
        <!--
        <hr/>
        -->
        <div class="row">
            <!--
            <div class="col-md-6">
                 <%=Preferences.copyright%></div>
            <div class="col-md-6 text-right">
               <small> <%=Preferences.copyrightYear%></small>
            </div>
            -->
        </div>
    </div>

</body>

</html>
