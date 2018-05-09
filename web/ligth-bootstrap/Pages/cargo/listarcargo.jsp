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
        <title>Cargos - Salesland Colombia</title>       
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
                            <div class="col-md-12 mr-auto ml-auto">
                                <div class="card bootstrap-table">
                                <div class="card-header">
                                    <h4 class="card-title">Nuestros Cargos</h4>
                                    <p class="card-category">En este listado se muestran todos los cargos registrados en la empresa.</p>
                                </div>
                                    <div class="modal-body text-center" id="tablaModificada">
                                        <div class="toolbar" id="toolbar">
                                            <button class="btn btn-outline btn-round" data-toggle="modal" data-target="#modalRegistrarCargo">                                                
                                                Nuevo
                                                <span class="btn-label">
                                                    <i class="fa fa-plus"></i>
                                                </span>
                                            </button>
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <table id="bootstrap-table" data-toolbar="#toolbar" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th>Nombre</th>
                                                    <th>Descripción</th>                                                  
                                                    <th>Tipo</th>
                                                    <th>Sector</th>
                                                    <th>Canal</th>
                                                    <th>Area</th>
                                                    <th>Estado</th>                                                
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoCargos">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>                                                                                                            
                </div>
                <!-- INCLUDE FOOTER -->
                <%@include  file="../includes/footer.jsp" %>
            </div>
        </div>
        <!-- MODAL PARA CREAR UN CARGO -->
        <div class="modal fade modal-primary" id="modalRegistrarCargo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarCargos" name="frmRegistrarCargo">
                        <div class="modal-body text-center">
                            <h5 class="category">INGRESA LOS DATOS DEL CARGO</h5>
                            <div class="col-md-12 mr-auto ml-auto">
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Nombre del Cargo *</strong></small>
                                            <input id="txtNombreCargo" name="NombreCargo" type="text" class="form-control">                                                        
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Descripción *</strong></small>
                                            <textarea id="txtDescripcionCargo" name="DescripcionCargo" class="form-control textArea"></textarea>                                                       
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Selecciona tipo de Usuario *</strong></small>
                                            <select name="opciones" id="cmbTipo" class="form-control" data-title="Seleccionar Tipo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                      
                                            <option>Seleccione el tipo de usuario</option>
                                            <option value="Director">Director</option>                                                       
                                            <option value="JefeCanal">Jefe Canal</option>
                                            <option value="CoordinadorCanal">Coordinador</option>                                                       
                                            <option value="JefeArea">Jefe Area</option>
                                            <option value="Empleado">Empleado</option>
                                            </select>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="nsector">
                                        <div class="form-group">                                                
                                            <div class="row">
                                                <small class="control-label"><strong>Sector *</strong></small>
                                                <select name="Sector" id="cmbSector" class="form-control" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                           
                                                    <option>Seleccione el sector</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="ncanal">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Canal *</strong></small>
                                                <select name="Canal" id="cmbCanal" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione el canal</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="narea">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Area *</strong></small>
                                                <select name="Area" id="cmbArea" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione el area</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>   
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-simple" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--  End Modal -->
        
        
        <!--________________________________________   MODAL PARA Editar UN CARGO _____________________________________________-->
        <div class="modal fade modal-primary" id="modalEditarCargo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmEditarCargo" name="frmEditarCargo">
                        <div class="modal-body text-center">
                            <h5 class="category">Actualiza los datos del cargo</h5>
                            <div class="col-md-12 mr-auto ml-auto">
                                <input id="idCargo" style="display: none;">
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Nombre del Cargo *</strong></small>
                                            <input id="txtEditarNombreCargo" name="EditarNombreCargo" type="text" class="form-control">                                                        
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Descripción *</strong></small>
                                            <textarea id="txtEditarDescripcionCargo" name="EditarDescripcionCargo" class="form-control textArea"></textarea>                                                       
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Estado *</strong></small>
                                            <select name="EditarEstado" id="cmbEditarEstadoCargo" class="form-control">                                                      
                                                <option value="Activo">Activo</option>                                                       
                                                <option value="Inactivo">Inactivo</option>                                            
                                            </select>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Selecciona tipo de Usuario *</strong></small>
                                            <select name="EditarOpciones" id="cmbEditarTipo" class="form-control" data-title="Seleccionar Tipo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                      
                                                <option value="Director">Director</option>                                                       
                                                <option value="JefeCanal">Jefe Canal</option>
                                                <option value="CoordinadorCanal">Coordinador</option>                                                       
                                                <option value="JefeArea">Jefe Area</option>
                                                <option value="Empleado">Empleado</option>
                                            </select>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="nEditarSector">
                                        <div class="form-group">                                                
                                            <div class="row">
                                                <small class="control-label"><strong>Sector *</strong></small>
                                                <select name="EditarSector" id="cmbEditarSector" class="form-control" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                           
                                                    <option>Seleccione el sector</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="nEditarCanal">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Canal *</strong></small>
                                                <select name="EditarCanal" id="cmbEditarCanal" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione el canal</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="nEditarArea">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Area *</strong></small>
                                                <select name="EditarArea" id="cmbEditarArea" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione el area</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-simple" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--  End Modal -->
        
        
        
        
        
        
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            $(document).ready(function (){               
                listarCargos();
                $("#nsector").hide();
                $("#ncanal").hide();
                $("#narea").hide();           
                $("#cargosEmpleadosNav").addClass('active');
                $("#tituloPagina").text("Cargos");

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