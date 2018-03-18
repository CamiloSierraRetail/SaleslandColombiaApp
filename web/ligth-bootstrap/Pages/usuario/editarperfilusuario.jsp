<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Inicio - SaleslandColombia</title>
    </head>

    <body>
        <div class="wrapper">
            <%@include file="../includes/navLateral.jsp" %>
            <div class="main-panel">
                <!-- Navbar -->
                <%@include file="../includes/navSuperior.jsp" %>
                <!-- End Navbar -->
                <div class="content">
                    <div class="container-fluid">
                        <div class="section-image" data-image="../../assets/img/bg5.jpg" ;>
                            <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-8 col-sm-6">
                                        <form class="form" method="post" action="" id="frmEditarPerfil">
                                            <div class="card ">
                                                <div class="card-header ">
                                                    <div class="card-header">
                                                        <h4 class="card-title">Editar Perfil</h4>
                                                    </div>
                                                </div>
                                                <div class="card-body ">
                                                    <div class="row">
                                                        <div class="col-md-6 pr-1">
                                                            <div class="form-group">
                                                                <label>Nombres</label>
                                                                <input type="text" class="form-control" id="txtNombre" value="<%=objUsuario.getNombre()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 pl-1">
                                                            <div class="form-group">
                                                                <label for="exampleInputEmail1">Apellidos</label>
                                                                <input type="text" class="form-control" id="txtApellido" value="<%=objUsuario.getApellido()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6 pr-1">
                                                            <div class="form-group">
                                                                <label>Direccion</label>
                                                                <input type="text" class="form-control" id="txtDireccion" value="<%=objUsuario.getDireccion()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 pl-1">
                                                            <div class="form-group">
                                                                <label>Telefono</label>
                                                                <input type="text" class="form-control" id="txtTelefono" value="<%=objUsuario.getTelefono()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label>Email</label>
                                                                <input type="text" class="form-control" id="txtEmail" value="<%=objUsuario.getEmail()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 pr-1">
                                                            <div class="form-group">
                                                                <label>Celular</label>
                                                                <input type="text" class="form-control" id="txtCelular" value="<%=objUsuario.getCelular()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 px-1">
                                                            <div class="form-group">
                                                                <label>Genero</label>
                                                                <select id="cmbGenero" class="form-control">
                                                                    <%if (objUsuario.getGenero().equals("Masculino")) {%>
                                                                        <option value="Masculino">Masculino</option>
                                                                        <option value="Femenino">Femenino</option>
                                                                    <%}else{%>
                                                                        <option value="Femenino">Femenino</option>
                                                                        <option value="Masculino">Masculino</option>
                                                                    <%}%>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 pl-1">
                                                            <div class="form-group">
                                                                <label>Fecha Nacimiento</label>
                                                                <input type="text" id="datetimepicker" class="form-control datepicker" value="<%=objUsuario.getFechaNacimiento()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label>Foto</label>
                                                                <input type="file" class="form-control" value="<%=objUsuario.getFoto()%>"></input>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-info btn-fill pull-right">Actualizar Perfil</button>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="card card-user">
                                            <div class="card-header no-padding">
                                                <div class="card-image">
                                                    <img src="../../assets/img/saleslandFondoOscuro.png" alt="...">
                                                </div>
                                            </div>
                                            <div class="card-body ">
                                                <div class="author">
                                                    <a href="#">
                                                        <img class="avatar border-gray" src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>" alt="...">
                                                        <h5 class="card-title">Tania Keatley</h5>
                                                    </a>
                                                    <p class="card-description">
                                                        michael24
                                                    </p>
                                                </div>
                                                <p class="card-description text-center">
                                                    Hey there! As you can see,
                                                    <br> it is already looking great.
                                                </p>
                                            </div>
                                            <div class="card-footer ">
                                                <hr>
                                                <div class="button-container text-center">
                                                    <button href="#" class="btn btn-simple btn-link btn-icon">
                                                        <i class="fa fa-facebook-square"></i>
                                                    </button>
                                                    <button href="#" class="btn btn-simple btn-link btn-icon">
                                                        <i class="fa fa-twitter"></i>
                                                    </button>
                                                    <button href="#" class="btn btn-simple btn-link btn-icon">
                                                        <i class="fa fa-google-plus-square"></i>
                                                    </button>
                                                </div>
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
        </div>
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            $(document).ready(function(){
               $("#tituloPagina").text("Editar Perfil"); 
            });
        </script>
    </body>
</html>