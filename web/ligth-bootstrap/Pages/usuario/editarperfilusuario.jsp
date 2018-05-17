<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try{      
        if(session.getAttribute("UsuarioIngresado").equals("") || session.getAttribute("UsuarioIngresado").equals(null)){
            response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
        }
        else{
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Editar Perfil - Salesland Colombia</title>
    </head>

    <body>
        <div class="wrapper">
            <%@include file="../includes/navLateral.jsp" %>
            <div class="main-panel">
                <!-- Navbar -->
                <%@include file="../includes/navSuperior.jsp" %>
                <!-- End Navbar -->
                <!-- Include div Ingresos -->
                <%@include file="../includes/divIngresos.jsp" %>
                <div class="content">
                    <div class="container-fluid">
                        <div class="section-image" data-image="../../assets/img/bg5.jpg">
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
                                                                <input type="text" class="form-control" id="txtNombre" name="txtNombre" value="<%=objUsuario.getNombre()%>">
                                                                <input type="hidden" id="txtIdUsuario" value="<%=objUsuario.getIdUsuario()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 pl-1">
                                                            <div class="form-group">
                                                                <label for="exampleInputEmail1">Apellidos</label>
                                                                <input type="text" class="form-control" id="txtApellido" name="txtApellido" value="<%=objUsuario.getApellido()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6 pr-1">
                                                            <div class="form-group">
                                                                <label>Direccion</label>
                                                                <input type="text" class="form-control" id="txtDireccion" name="txtDireccion" value="<%=objUsuario.getDireccion()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 pl-1">
                                                            <div class="form-group">
                                                                <label>Telefono</label>
                                                                <input type="text" class="form-control" id="txtTelefono" name="txtTelefono" value="<%=objUsuario.getTelefono()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label>Email</label>
                                                                <input type="email" class="form-control" id="txtEmail" name="txtEmail" value="<%=objUsuario.getEmail()%>">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 pr-1">
                                                            <div class="form-group">
                                                                <label>Celular</label>
                                                                <input type="text" class="form-control" id="txtCelular" name="txtCelular" value="<%=objUsuario.getCelular()%>">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 px-1">
                                                            <div class="form-group">
                                                                <label>Genero</label>
                                                                <select id="cmbGenero" name="cmbGenero" class="form-control">
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
                                                                <input type="hidden" id="dtData" value="<%=objUsuario.getFechaNacimiento()%>">
                                                                <input type="text" id="datetimepicker" class="form-control datepicker dtFechaNacimiento" name="dtFechaNacimiento">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 pr-1">
                                                            <div class="form-group">
                                                                <label>Foto</label> 
                                                                <label class="btn btn-info btn-fill form-control" for="txtFotoFile">Selecciona un archivo</label>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8 pl-1">
                                                            <div class="form-group">
                                                                <label>Archivo</label>
                                                                <input type="text" class="form-control" id="txtFoto" name="txtFoto" disabled="" value="<%=objUsuario.getFoto()%>">
                                                                <input type="file" id="txtFotoFile" hidden>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Actualizar Perfil</button>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="card card-user">
                                            <div class="card-header no-padding">
                                                <div class="card-image">
                                                    <img src="/SaleslandColombiaApp/ligth-bootstrap/assets/img/saleslandFondoOscuro.png" alt="...">
                                                </div>
                                            </div>
                                            <div class="card-body ">
                                                <div class="author">
                                                    <a href="#">
                                                        <%String nombreUSuario[] = objUsuario.getNombre().split(" ");
                                                         String apellidoUsuario[] = objUsuario.getApellido().split(" ");%>
                                                        <img class="avatar border-gray" src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>" alt="...">
                                                        <h5 class="card-title"><%=nombreUSuario[0] +" " +apellidoUsuario[0]%></h5>
                                                    </a>
                                                    <p class="card-description">
                                                        <%if(objUsuario.getCargo().getTipo().equals("Empleado")){%>
                                                            (Empleado)
                                                        <%}else{%>
                                                            (Administrador)
                                                        <%}%>
                                                    </p>
                                                </div>
                                                <p class="card-description text-center">
                                                    <%=objUsuario.getCargo().getNombreCargo()%> : <%=objUsuario.getCargo().getDescripcion()%>
                                                </p>
                                            </div>
                                            <div class="card-footer ">
                                                <hr>
                                                <div class="button-container text-center">
                                                    
                                                    <form action='/SaleslandColombiaApp/ligth-bootstrap/Pages/reportes/PdfMiReporte.jsp' target='_blank' method='POST'>
                                                        <input name='txtparametro' type='hidden' value="<%=objUsuario.getIdUsuario()%>">
                                                        <button class='btn gray-corp btn-link btn-xs' type='submit' rel='tooltip' title='' data-original-title='Mi Reporte' > 
                                                            <i class='fa fa-bar-chart gray-corp'></i>
                                                        </button>
                                                    </form>
                                                    
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
                $("#opcionesUsuarioNav").addClass("show");
                $("#editarPerfilUsuario").addClass('active');                
                $(".dtFechaNacimiento").val(moment($("#dtData").val()).format('DD/MM/YYYY'));
                $("#tituloPagina").text("EDITAR PERFIL"); 
                
                localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
                localStorage.name = $(".spanName").val();

                var fullDate = new Date();
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
                websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
            });
        </script>
    </body>
</html>
<%        }
    }  
    catch(NullPointerException ex){
        response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
    }
%>