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
        
        <title>Canales - SaleslandColombia</title>       
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
                                        <h4 class="card-title">Nuestros canales</h4>
                                        <p class="card-category">En este listado se muestran todos los canales registrados en la empresa.</p>
                                    </div>
                                    <div class="card-body table-full-width" id="tablaModificada">                                        
                                        <div class="toolbar" id="toolbar">
                                            <button class="btn btn-outline btn-round" data-toggle="modal" data-target="#modalRegistrarCanal">                                                
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
                                                    <th>Sector</th>
                                                    <th class="text-right">Estado</th>
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoCanales">
                                                
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
                <!-- MODAL PARA REGISTRAR UN CANAL -->
                <div class="modal fade modal-primary" id="modalRegistrarCanal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-center">
                                <div class="modal-profile">
                                    <i class="nc-icon nc-puzzle-10"></i>
                                </div>
                            </div>
                            <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarCanal" name="frmRegistrarCanal">
                                <div class="modal-body text-center">
                                    <h5 class="category">INGRESA LOS DATOS DEL CANAL</h5>
                                    <div class="col-md-12 mr-auto ml-auto">
                                        <fieldset>  
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Sector *</strong></small>                                                            
                                                    <select id="cmbSector" name="Sector" class="form-control" required="true">
                                                        <option>Selecciona el sector</option>
                                                    </select>                                                            
                                                </div>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Nombre del canal *</strong></small>
                                                    <input id="txtNombreCanal" name="NombreCanal" type="text" class="form-control">                                                        
                                                </div>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Descripción *</strong></small>
                                                    <textarea id="txtDescripcionCanal" name="DescripcionCanal" class="form-control textArea"></textarea>                                                       
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>   
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-link btn-simple" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-info btn-fill pull-right">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--  End Modal -->
                
                <!-- MODAL PARA EDITAR EL SECTOR -->
                <div class="modal fade modal-primary" id="ModalEditarCanal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-center">
                                <div class="modal-profile">
                                    <i class="nc-icon nc-puzzle-10"></i>
                                </div>
                            </div>
                            <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmEditarCanal" name="frmEditarSector">
                                <div class="modal-body text-center">
                                    <h5 class="category">ACTUALIZA LOS DATOS DEL CANAL</h5>
                                    <div class="col-md-12 mr-auto ml-auto">
                                        <input id="idCanal" style="display: none">
                                        
                                        <fieldset>  
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Estado *</strong></small>                                                            
                                                    <select id="cmbEditarEstadoCanal" name="EditarEstadocanal" class="form-control">
                                                        <option value="Activo">Activo</option>
                                                        <option value="Inactivo">Inactivo</option>                                                                                                                                
                                                    </select>                                                            
                                                </div>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Nombre del canal *</strong></small>
                                                    <input id="txtEditarNombreCanal" name="EditarNombreCanal" type="text" class="form-control">                                                        
                                                </div>
                                            </div>
                                        </fieldset>                                        
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label"><strong>Descripción *</strong></small>
                                                    <textarea id="txtEditarDescripcionCanal" name="EditarDescripcionCanal" class="form-control textArea"></textarea>                                                       
                                                </div>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="control-label" id="lblSector"><strong>Sector *</strong></small>                                                            
                                                    <select id="cmbEditarSector" name="EditarSectorCanal" class="form-control">

                                                    </select>                                                           
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>   
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-link btn-simple" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-info btn-fill pull-right">Guardar cambios</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--  End Modal -->
                
            </div>            
        </div>
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            $(document).ready(function (){
               
                listarCanales();
                $("#tituloPagina").text("CANALES");
                $("#canalesItemNav").addClass('active');
                $("#empresaItemNav").addClass("show");
                cargarSectores();

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