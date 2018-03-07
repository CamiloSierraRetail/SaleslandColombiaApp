<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <%@include file="../../Pages/includes/cssInclude.jsp" %>
    <title>Inicio de sesion - SaleslandColombia</title>
    
    <style>   
        /*/////////////// NO BORRAR, FUENTES PARA EL PRELOADER /////////////////////*/
        @import url(https://fonts.googleapis.com/css?family=Montserrat+Alternates:400,700);
        @import url(https://fonts.googleapis.com/css?family=Eczar);
    </style>
    
</head>

<body>
    <div class="wrapper wrapper-full-page">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
            <div class="container">
                <div class="navbar-wrapper">
                    <a class="navbar-brand" href="#pablo"><strong>SALESLAND COLOMBIA</strong></a>
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse justify-content-end" id="navbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a href="../dashboard.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">dashboard</i>Dashboard                                
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="register.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">person_add</i>Register
                            </a>
                        </li>
                        <li class="nav-item  active ">
                            <a href="login.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">fingerprint</i>Login
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="lock.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">lock</i>Lock
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="full-page  section-image" data-color="black" data-image="../../assets/img/SpaceBackground.jpg">
            <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
            <div class="content">
                <div class="container">
                    
                    <div class="col-md-4 ml-auto mr-auto">
                        <form id="frmIniciarSesion" class="form" method="" action="" novalidate="novalidate">
                            <div class="card card-login card-hidden">
                                <div class="card-header ">
                                    <h3 class="header text-center">Inicio de sesión</h3>
                                </div>
                                <div class="card-body ">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label>Email o documento</label>
                                            <input id="txtUsuarioSesion" name="UsuarioSesion" placeholder="Email o número de documento" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Contraseña</label>
                                            <input id="txtContraseniaSesion" name="ContraseniaSesion" type="password" placeholder="Contraseña" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" value="" checked>
                                                    <span class="form-check-sign"></span>
                                                    Subscribe to newsletter
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer ml-auto mr-auto">
                                    <button type="submit" class="btn btn-warning btn-wd">Login</button>
                                </div>
                            </div>
                        </form>
                    </div>                    
                    <section id="container">
                        <div id="loader">
                            <div class="rinkin_coffee">
                                <span class="rinkin">
                                    alesland
                                </span>
                            </div>
                            <span class="slogan">
                                #HastaLaLunaNoParamos
                            </span>
                            <div class="rectangle outlines">
                                <span>S</span>
                            </div>
                            <div class="rectangle fill">
                                <span>S</span>
                            </div>
                        </div>
                    </section>
                    
                </div>
            </div>
        </div>
        <%@include file="../../Pages/includes/footer.jsp" %>
    </div>    
</body>
<%@include file="../../Pages/includes/jsInclude.jsp" %>
<script>
    $(document).ready(function() {
        demo.checkFullPageBackgroundImage();

        setTimeout(function() {
            // after 1000 ms we add the class animated to the login/register card
            $('.card').removeClass('card-hidden');
            $("#container").hide();
        }, 4500);
    });
</script>
</html>