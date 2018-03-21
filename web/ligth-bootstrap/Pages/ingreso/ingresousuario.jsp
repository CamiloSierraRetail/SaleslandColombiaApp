<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Ingreso de personal - SaleslandColombia</title>
        <!--     Fonts and icons     -->

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" />
    </head>

    <body>
        <div class="wrapper wrapper-full-page">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
                <div class="container">
                    <div class="navbar-wrapper">
                        <a class="navbar-brand" href="login.jsp">SALESLAND COLOMBIA</a>
                        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-bar burger-lines"></span>
                            <span class="navbar-toggler-bar burger-lines"></span>
                            <span class="navbar-toggler-bar burger-lines"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse justify-content-end" id="navbar">
                        <ul class="navbar-nav">
                            <li class="nav-item ">
                                <a href="../usuario/login.jsp" class="nav-link">
                                    <i class="nc-icon nc-mobile"></i> Iniciar Sesión
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a href="#" class="nav-link">
                                    <i class="nc-icon nc-key-25"></i> Ingreso
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->
            <div class="full-page lock-page" data-color="black" data-image="../../assets/img/SpaceBackground.jpg">
                <!--   you can change the color of the filter page using: data-color="blue | azure | green | orange | red | purple" -->
                <div class="content">
                    <div class="container">
                        <div class="col-md-4 ml-auto mr-auto">
                            <div class="card card-lock text-center card-plain">
                                <div class="card-header ">
                                    <div class="author">
                                        <img class="avatar" src="../../assets/img/imagenesDePerfil/otroDefaultImageProfile.png" alt="...">
                                    </div>
                                </div>
                                <div class="card-body ">
                                    <h4 class="card-title">INGRESO DE PERSONAL</h4>
                                    <div class="form-group">
                                        <input id="txtUsuarioIngresoDisabled" name="UsuarioIngreso" disabled type="password" placeholder="Acerca tu carnet al lector" class="form-control">
                                        <div class="txtUsuarioIngresoContainer">
                                            <input type="password" id="txtUsuarioIngreso">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../includes/footer.jsp" %>
        </div>
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            $(document).ready(function() {
                demo.checkFullPageBackgroundImage();               
                
                setInterval(function(){ 
                    $("#txtUsuarioIngreso").focus(); 
                },800);

                setTimeout(function() {
                    // after 1000 ms we add the class animated to the login/register card
                    $('.card').removeClass('card-hidden');
                }, 700);
            });
            $("#txtUsuarioIngreso").change(function (){     
                $("#txtUsuarioIngresoDisabled").val($(this).val());
                ingreso();
            });
        </script>
    </body>
</html>