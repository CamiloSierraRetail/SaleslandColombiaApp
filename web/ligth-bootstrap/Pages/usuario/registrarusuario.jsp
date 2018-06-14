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
        <title>Inicio - SaleslandColombia</title>           
    </head>
    <body>
        <div class="wrapper">
            
            <!-- Include Nav Lateral  -->
            <%@include file="../includes/navLateral.jsp" %>
            
            <div class="main-panel">
                <!-- Include Nav Superior -->
                <%@include file="../includes/navSuperior.jsp" %>
                <!-- Include div Ingresos -->
                <%@include file="../includes/divIngresos.jsp" %>
                
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">  
                                <div class="col-md-11 ml-auto mr-auto">
                                    <form id="frmRegistrarUsuario" novalidate="novalidate">
                                        <div class="card card-wizard">
                                            <div class="card-header ">
                                                <h3 class="card-title text-center">Registro de usuario</h3>
                                                <p class="card-category text-center">Para continuar con el proceso de registro te agradecemos seguir cada uno de estos pasos en el orden establecido</p>
                                            </div>
                                            <div class="card-body ">
                                                <ul class="nav nav-tabs nav-pills">
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link active" href="#tab1" data-toggle="tab" role="tab" aria-controls="tab1" aria-selected="true">Información Personal</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab2" data-toggle="tab" role="tab" aria-controls="tab2" aria-selected="true">Información del empleado</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab3" data-toggle="tab" role="tab" aria-controls="tab3" aria-selected="true">Asignar cargo</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                        <p class="text-center">Por favor ingresa los datos del usuario</p>
                                                        
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Tipo de Documento *</strong></label>                                    
                                                                    <select id="cmbTipoDocumentoUsuario" name="TipoDocumentoUsuario" class="selectpicker form-control" data-title="Tipo de Documento" data-menu-style="dropdown-blue">
                                                                        <option value="T.I">Tarjeta de indentidad (T.I)</option>
                                                                        <option value="C.C">Cedula de ciudadanía (C.C)</option> 
                                                                        <option value="C.E">Cedula de extranjería (C.E)</option> 
                                                                        <option value="OTRO">Otro</option>
                                                                    </select>
                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Documento *</strong></label>
                                                                    <input class="form-control" type="text" id="txtDocumentoUsuario" name="DocumentoUsuario" placeholder="ej: 1057621548">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Nombres *</strong></label>
                                                                    <input class="form-control" type="text" id="txtNombreUsuario" name="NombreUsuario" placeholder="ej: Brandon Felipe">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Apellidos *</strong></label>
                                                                    <input class="form-control" type="text" id="txtApellidoUsuario" name="ApellidoUsuario" placeholder="ej: Perez Sandoval">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-10">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Email *</strong></label>
                                                                    <input class="form-control" type="text" id="txtEmailUsuario" name="EmailUsuario" placeholder="ej: usuario@salesland.net">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Contraseña *</strong></label>
                                                                    <input class="form-control" type="password" id="txtContraseniaUsuario" name="ContraseniaUsuario" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Confirmar Contraseña *</strong></label>
                                                                    <input class="form-control" type="password" id="txtConfirmarContraseniaUsuario" name="ConfirmarContraseniaUsuario" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                        <h5 class="text-center">Por favor no desistas ya casi lo logras</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Direccion *</strong></label>
                                                                    <input class="form-control" type="text" id="txtDireccionUsuario" name="DireccionUsuario" placeholder="ej: carrera 16A # 85-64 Bogotá">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Genero *</strong></label>
                                                                    <select id="cmbGeneroUsuario" name="GeneroUsuario" class="selectpicker"  data-title="Selecciona el genero" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="Masculino">Masculino</option>
                                                                        <option value="Femenino">Femenino</option>
                                                                        <option value="Otro">Otro</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Celular *</strong></label>
                                                                    <input class="form-control" type="tel" id="txtCelularUsuario" name="CelularUsuario" placeholder="ej: 315 8921655">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Telefono *</strong></label>
                                                                    <input class="form-control" type="tel" id="txtTelefonoUSuario" name="TelefonoUsuario" placeholder="ej: 7435596">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Fecha de nacimiento *</strong></label>
                                                                    <div class="form-group">
                                                                        <input id="datetimepicker" type="text" class="form-control datepicker" placeholder="Selecciona la fecha de nacimiento" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Imagen de perfil</strong></label>
                                                                    <div class="form-group">

                                                                        <input type="file" name="file" id="fileImagenUsuario" class="input-file form-control">
                                                                        <label for="fileImagenUsuario" class="btn btn-outline btn-wd js-labelFile form-control">
                                                                            <i class="icon fa fa-check" ></i>
                                                                            <span class="js-fileName" id="fileImagenUsuarioNombre">Selecciona el archivo (PNG, JPG y JPEG)</span>
                                                                        </label>                                                                              
                                                                    </div>                                                                                                                                                   
                                                                </div>
                                                            </div>
                                                            <div class="col-md-10">
                                                                <div class="form-group">
                                                                    <label class="control-label"><strong>Horario *</strong></label>
                                                                    <select id="cmbHorarioUsuario" name="HorarioUsuario" class="selectpicker"  data-title="Selecciona el horario" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="A">De 8:00 A.M a 6:00 P.M</option>
                                                                        <option value="B">De 7:00 A.M a 5:00 P.M</option>                                                                        
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab3" role="tabpanel">
                                                        <h5 class="text-center">ahora selecciona el cargo y dale FINALIZAR.</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-12">
                                                                <div class="card bootstrap-table">
                                                                    <div class="card-body table-full-width">
                                                                        <div class="toolbar">
                                                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                                                        </div>
                                                                        <table id="bootstrap-table" class="table">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Select</th>
                                                                                    <th>Nombre</th>
                                                                                    <th>Descripción</th>
                                                                                    <th>Sector</th> 
                                                                                    <th>Tipo</th> 
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody id="listadoCargosSectores">
                                                                                
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>                                                                                                                            
                                                            </div>
                                                        </div>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-footer text-center">
                                                <button type="button" class="btn btn-default btn-wd btn-back pull-left">Volver</button>
                                                <button type="button" class="btn btn-blue_corp btn-wd btn-next pull-right">Siguiente</button>
                                                <button type="submit" class="btn btn-blue_corp btn-wd btn-finish pull-right" style="display: none;">Finalizar</button>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                    </form>                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- INCLUDE FOOTER -->
                <%@include  file="../includes/footer.jsp" %>
            </div>
            <%@include file="../includes/jsInclude.jsp" %>  
            <script>                
                $(document).ready(function (){ 
                    $("#empleadoItemNav").addClass("show");
                    $("#registrarEmpleadosNav").addClass('active');
                    $("#tituloPagina").text("Empleados");
                    cargarCargos();
                    InicializarFormularioRegistro();                    
                                        
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
