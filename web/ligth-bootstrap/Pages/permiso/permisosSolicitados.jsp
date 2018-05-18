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
        <title>Permisos Solicitados - Salesland Colombia</title>
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
                                        <h4 class="card-title">Permisos solicitados</h4>
                                        <p class="card-category">Este es el listado de los permisos solicitados por tu equipo de trabajo.</p>
                                    </div>
                                    <div class="card-body table-full-width" id="tablaModificada">                                        
                                        <div class="toolbar" id="toolbar">                                            
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <table id="bootstrap-table" data-toolbar="#toolbar" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th class="text-center">Motivo</th>
                                                    <th class="text-center">Descripción</th>
                                                    <th class="text-center">Desde</th>
                                                    <th class="text-center">Hasta</th>
                                                    <th class="text-center">Empleado</th>
                                                    <th class="text-center">Estado</th>
                                                    <th class="text-center">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoPermisos">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>                                
                            </div>                            
                        </div>                                                                                                            
                    </div>
                </div>
                
                <!-- MODAL PARA VER LOS PERMISOS SOLICITADOS -->
                <div class="modal fade modal-primary" id="modalEditarPermiso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">                                                        
                            <div class="modal-body">
                                <h5 class="category text-center">ACTUALIZA LA INFORMACIÓN DEL PERMISO</h5>
                                <div class="col-md-12 mr-auto ml-auto">

                                    <input type="hidden" id="idPermiso">
                                    <div class="row">
                                        <small class="card-category"><strong>Motivo: &nbsp;</strong></small>
                                        <p id="lblMotivo"></p>                                                       
                                    </div>


                                    <div class="row">
                                        <small class="card-category"><strong>Descripción: &nbsp;</strong></small>
                                        <p id="lblDescripcionPermiso"></p>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-6">                                                    
                                            <div class="row">
                                                <small class="card-category"><strong>Desde: &nbsp;</strong></small>
                                                <p id="lblDesdePermiso"></p>
                                            </div>                                                                                                        
                                        </div>  
                                        <div class="col-md-6">                                                    
                                            <div class="row">
                                                <small class="card-category"><strong>Hasta: &nbsp;</strong></small>
                                                <p id="lblHastaPermiso"></p>
                                            </div>                                                                                                        
                                        </div>
                                    </div>    

                                    <div class="row">
                                        <small class="card-category"><strong>Empleado: &nbsp;</strong></small>
                                        <p id="lblEmpleadoPermiso"></p>
                                    </div>


                                    <fieldset>
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="card-category"><strong>Estado *</strong></small>
                                                <select class="form-control" id="cmbEstadoPermiso" name="cmbEstadoPermiso">
                                                    <option value="">Selecciona el estado del permiso</option>
                                                    <option value="Aceptado">Aprobar</option>
                                                    <option value="Denegado">Rechazar</option>
                                                </select>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>   
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-simple" data-dismiss="modal">Cerrar</button>
                                <button class="btn btn-blue_corp btn-fill pull-right permisoEstado">Enviar</button>
                            </div>                            
                        </div>
                    </div>
                </div>
                <!--  End Modal -->
                
                
                <!-- INCLUDE FOOTER -->
                <%@include  file="../includes/footer.jsp" %>
            </div>
        </div>  
        <%@include file="../includes/jsInclude.jsp" %>    
        <script>
            
            $(document).ready(function (){         
                $("#empleadoItemNav").addClass("show");
                $("#permisosSolicitados").addClass("active");
                $("#tituloPagina").text("Permisos");                            
                
                listarPermisosSolicitados();
                
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