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
        <title>Listado de usuarios - SaleslandColombia</title>       
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
                                        <h4 class="card-title">Mis empleados</h4>
                                        <p class="card-category">Este es el listado de empleados que tienes a cargo.</p>
                                    </div>
                                    <div class="card-body table-full-width">
                                        <div class="toolbar" id="toolbar">
                                            <a href="registrarusuario.jsp" class="btn btn-outline btn-round">                                                
                                                Nuevo
                                                <span class="btn-label">
                                                    <i class="fa fa-plus"></i>
                                                </span>
                                            </a>
                                        </div>
                                        <table id="bootstrap-table" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th>Documento</th>
                                                    <th>Nombre</th>
                                                    <th>Celular</th>
                                                    <th>Email</th>
                                                    <th class="text-right">Estado</th>
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoUsuario">

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
                     
        <!-- MODAL PARA VER EL USUARIO -->
        <div class="modal fade modal-primary" id="modalVerUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">                                  
                    <div class="col-md-9 mr-auto ml-auto">
                        <div class="card card-user">
                            <div class="card-header no-padding">
                                <div class="card-image">
                                    <img src="../../assets/img/saleslandFondoOscuro.png" alt="...">
                                </div>
                            </div>
                            <div class="card-body ">
                                <div class="author">
                                    <a href="#">
                                        <img class="avatar border-gray" id="imgPerfilUsuario" alt="...">
                                        <h5 class="card-title" id="lblNombreApellido"></h5>
                                    </a>
                                    <p class="card-description" id="lblRolEmpleado">
                                        
                                    </p>
                                </div>
                                <p class="card-description text-center" id="lblDescripcionEmpleado">
                                    
                                </p>
                            </div>
                            <div class="card-footer ">
                                <hr>
                                <div class="button-container text-center">                                    
                                    <a href="#" class="btn btn-success btn-link btn-xs" rel="tooltip" data-original-title="Descargar Reporte" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">file_download</i>
                                    </a>
                                    <a href="#" class="btn btn-info btn-link btn-icon" rel="tooltip" data-original-title="Ver estadisticas" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">trending_up</i>
                                    </a>
                                    <button href="#" class="btn btn-warning btn-link btn-icon" rel="tooltip" data-original-title="Editar Perfil Del Usuario" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">mode_edit</i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>              
            </div>
        </div>
        <!--  End Modal -->
        
        
        <!-- MODAL PARA EDITAR LOS DATOS DEL USUARIO -->
        <div class="modal fade modal-primary" id="ModalEditarUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" novalidate="novalidate" id="frmEditarDatosUsuarios" name="frmEditarDatosUsuarios">
                        <div class="modal-body ">
                            <h5 class="category text-center">ACTUALIZA LOS DATOS DEL USUARIO</h5>
                            <div class="col-md-12">
                                <input id="idEditarUsuario" type="hidden" style="display: none">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <small class="control-label"><strong>Nombres</strong></small>
                                            <input class="form-control" type="text" id="txtNombreUsuario" name="NombreUsuario" disabled>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <small class="control-label"><strong>Apellidos</strong></small>
                                            <input class="form-control" type="text" id="txtApellidoUsuario" name="ApellidoUsuario" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <small class="control-label"><strong>Horario *</strong></small>
                                            <select id="cmbHorarioUsuario" name="HorarioUsuario" class="form-control"  data-title="Selecciona el horario" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                <option value="A">De 8:00 A.M a 6:00 P.M</option>
                                                <option value="B">De 7:00 A.M a 5:00 P.M</option>                                                                        
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <small class="control-label"><strong>Estado *</strong></small>
                                            <select id="cmbEstadoUsuario" name="EstadoUsuario" class="form-control"  data-title="SelecorarioUsuariociona el horario" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                <option value="Activo">Activo</option>
                                                <option value="Inactivo">Inactivo</option>                                                                        
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <small class="control-label"><strong>Cargo *</strong></small>
                                            <select id="cmbCargoUsuario" name="CargoUsuario" class="form-control">

                                            </select>
                                        </div>
                                    </div>                                     
                                </div>                                                                               
                            </div>   
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-simple" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Guardar cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--  End Modal -->  
        <%@include file="../includes/jsInclude.jsp" %>
        <script>

            $(document).ready(function (){

                $("#empleadoItemNav").addClass("show");
                $("#listadoEmpleadosNav").addClass('active');
                $("#tituloPagina").text("EMPLEADOS");
                listarUsuarios();

                cargarComboCargos();
                
        
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