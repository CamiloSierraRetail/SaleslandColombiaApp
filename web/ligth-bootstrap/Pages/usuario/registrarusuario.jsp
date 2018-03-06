<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                
                <div class="content">
                    <div class="container-fluid">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-10 ml-auto mr-auto">
                                    <form id="frmRegistrarUsuario" method="" action="" novalidate="novalidate">
                                        <div class="card card-wizard">
                                            <div class="card-header ">
                                                <h3 class="card-title text-center">Registro de usuario</h3>
                                                <p class="card-category text-center">Split a complicated flow in multiple steps</p>
                                            </div>
                                            <div class="card-body ">
                                                <ul class="nav nav-tabs nav-pills">
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link active" href="#tab1" data-toggle="tab" role="tab" aria-controls="tab1" aria-selected="true">First Tab</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab2" data-toggle="tab" role="tab" aria-controls="tab2" aria-selected="true">Second Tab</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab3" data-toggle="tab" role="tab" aria-controls="tab3" aria-selected="true">Third Tab</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                        <p class="text-center">Por favor ingresa los datos del usuario</p>
                                                        
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Tipo de Documento <star>*</star></label>
                                                                    
                                                                    <select id="cmbTipoDocumentoUsuario" name="TipoDocumentoUsuario" class="selectpicker" data-title="Seleccionar Estado" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="T.I">Tarjeta de indentidad (T.I)</option>
                                                                        <option value="C.C">Cedula de ciudadanía (C.C)</option> 
                                                                        <option value="C.E">Cedula de extranjería (C.E)</option> 
                                                                        <option value="NIT">Nit</option> 
                                                                        <option value="PASAPORTE">Pasaporte</option> 
                                                                        <option value="RUC">Reg. unico de contribuyentes (RUC)</option> 
                                                                        <option value="OTRO">Otro</option>
                                                                    </select>
                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Documento <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtDocumentoUsuario" name="DocumentoUsuario" required="true" placeholder="ej: Perez Perez">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Nombres <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtNombreUsuario" name="NombreUsuario" placeholder="ej: Brandon Felipe">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Apellidos <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtApellidoUsuario" name="ApellidoUsuario" required="true" placeholder="ej: Perez Perez">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-10">
                                                                <div class="form-group">
                                                                    <label class="control-label">Email
                                                                        <star>*</star>
                                                                    </label>
                                                                    <input class="form-control" type="text" id="txtEmailUsuario" name="EmailUsuario" email="true" placeholder="ej: usuario@salesland.net">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Contraseña <star>*</star></label>
                                                                    <input class="form-control" type="password" id="txtContraseniaUsuario" name="ContraseniaUsuario" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Confirmar Contraseña<star>*</star></label>
                                                                    <input class="form-control" type="password" id="txtConfirmarContraseniaUsuario" name="ConfirmarContraseniaUsuario" required="true" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                        <h5 class="text-center">sigue ingresando los datos, ya falta poco (._.)</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Direccion <star>*</star></label>
                                                                    <input class="form-control" type="password" name="first_name" placeholder="ej: carrera 16A # 85-64 Bogotá">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Genero<star>*</star></label>
                                                                    <select name="cities" class="selectpicker" data-title="Selecciona el genero" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
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
                                                                    <label class="control-label">Celular <star>*</star></label>
                                                                    <input class="form-control" type="text" name="first_name" placeholder="ej: 315 8921655">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Telefono <star>*</star></label>
                                                                    <input class="form-control" type="text" name="last_name" required="true" placeholder="ej: 7435596">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Fecha de nacimiento <star>*</star></label>
                                                                    <input type="text" class="form-control datepicker" placeholder="Selecciona la fecha de nacimiento">                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <!--<div class="photo">
                                                                        <img src="../../assets/img/default-avatar.png">
                                                                        <input type="file" id="wizard-picture">
                                                                    </div>-->
                                                                    
                                                                    
                                                                   
                                                                    
                                                                    <!--<div class="user">
                                                                        <div class="photo">
                                                                            <img src="../../assets/img/default-avatar.png" style ="border-radius: 50%; height: 80px;width: 80px;">
                                                                            <input type="file" id="wizard-picture">
                                                                        </div>
                                                                    </div>-->
                                                                    
                                                                    <label class="control-label">Imagen de perfil <star>*</star></label>
                                                                    <input class="form-control" type="file" name="last_name" required="true" placeholder="ej: 7435596">
                                                                    
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab3" role="tabpanel">
                                                        <h5 class="text-center">solo faltan algunos campos (>_<)</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5">                                                            
                                                                <div class="form-group">
                                                                    <label class="control-label">Sector<star>*</star></label>
                                                                    <select name="cities" class="selectpicker" data-title="Selecciona el sector" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="id">Sector 1</option>
                                                                        <option value="ms">Sector 2</option>
                                                                        <option value="ca">Sector 3</option>
                                                                    </select>
                                                                </div>                                                            
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Canal<star>*</star></label>
                                                                    <select name="cities" class="selectpicker" data-title="Selecciona el canal" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="Masculino">Canal 1</option>
                                                                        <option value="Femenino">Canal 2</option>
                                                                        <option value="Otro">Canal 3</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5">                                                            
                                                                <div class="form-group">
                                                                    <label class="control-label">Area<star>*</star></label>
                                                                    <select name="cities" class="selectpicker" data-title="Selecciona el area" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="id">Area 1</option>
                                                                        <option value="ms">Area 2</option>
                                                                        <option value="ca">Area 3</option>
                                                                    </select>
                                                                </div>                                                            
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Cargo<star>*</star></label>
                                                                    <select name="cities" class="selectpicker" data-title="Selecciona el cargo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="Masculino">Cargo 1</option>
                                                                        <option value="Femenino">Cargo 2</option>
                                                                        <option value="Otro">Cargo 3</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-footer text-center">
                                                <button type="button" class="btn btn-default btn-wd btn-back pull-left">Volver</button>
                                                <button type="button" class="btn btn-info btn-wd btn-next pull-right">Siguiente</button>
                                                <button type="button" class="btn btn-info btn-wd btn-finish pull-right" onclick="onFinishWizard()" style="display: none;">Finalizar</button>
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
        </div>
    </body>
    <%@include file="../includes/jsInclude.jsp" %>    
    <script>
        $(document).ready(function (){

           InicializarFormularioRegistro(); 
        });
    </script>
</html>
