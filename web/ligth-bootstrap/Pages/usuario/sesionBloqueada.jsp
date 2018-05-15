<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesión bloqueada - SaleslandColombia</title>
        <%@include file="../includes/cssInclude.jsp" %>
    </head>
    <body>
        <div class="wrapper wrapper-full-page">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
                <div class="container">
                    <div class="navbar-wrapper">
                        <a href="#" class="navbar-brand"><img style="height: 30px; white-space: 100px;" src="../../assets/img/LOGO_SALESLAND_NEGATIVO.png"></a>
                        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-bar burger-lines"></span>
                            <span class="navbar-toggler-bar burger-lines"></span>
                            <span class="navbar-toggler-bar burger-lines"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse justify-content-end" id="navbar">
                        <ul class="navbar-nav">
                            <li class="nav-item ">
                                <a href="login.jsp" class="nav-link">
                                    <i class="nc-icon nc-mobile"></i> Iniciar Sesión
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a href="#" class="nav-link">
                                    <i class="nc-icon nc-key-25"></i> Bloqueo
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->
            <div class="full-page lock-page" data-color="" data-image="../../assets/img/defaultImage.jpg">
                <!--   you can change the color of the filter page using: data-color="blue | azure | green | orange | red | purple" -->
                <div class="content">
                    <div class="container">
                        <div class="col-md-4 ml-auto mr-auto">
                            <div class="card card-lock text-center card-plain">
                                <form method="post" id="frmUnlock">
                                    <div class="card-header ">
                                        <div class="author">
                                            <img class="avatar" id="imgLock" src="" alt="...">
                                        </div>
                                    </div>
                                    <div class="card-body ">
                                        <h4 class="card-title" id="nameLock"></h4>
                                        <div class="form-group">
                                            <input type="hidden" id="txtUserUnlock">    
                                            <input type="password" placeholder="Contraseña" id="passwordUnlock" name="passwordUnlock" class="form-control">
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <button type="submit" class="btn btn-blue_corp btn-round">Desbloquear</button>
                                    </div>
                                </form>
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
                $.notify({
                    icon: "nc-icon nc-fav-remove",
                    message: "Tú sesión ha sido bloqueada por inactividad, por favor ingresa tu contraseña para volver a ingresar al sistema."
                },{
                    type: 'danger',
                    timer: 2500,
                    placement: {
                        from: 'bottom',
                        align: 'right'
                    }
                });
                
                $("#passwordUnlock").focus();
                
                demo.checkFullPageBackgroundImage();
                $("#txtUserUnlock").val(localStorage.docEmail);
                $('#imgLock').attr('src','/SaleslandColombiaApp/ligth-bootstrap/assets/img/imagenesDePerfil/'+localStorage.imgPerfil+'');
                $("#nameLock").append(localStorage.name);
                setTimeout(function() {
                    $('.card').removeClass('card-hidden');
                }, 700);
            });
        </script>
    </body>
</html>
