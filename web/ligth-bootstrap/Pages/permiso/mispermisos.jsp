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
        <title>Mis permisos - Salesland Colombia</title>
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
                                        <h4 class="card-title">Mis permisos</h4>
                                        <p class="card-category">En este listado de todos tus permisos solicitados.</p>
                                    </div>
                                    <div class="card-body table-full-width" id="tablaModificada">                                        
                                        <div class="toolbar" id="toolbar">
                                            <button class="btn btn-outline btn-round" data-toggle="modal" data-target="#modalRegistrarPermiso">                                                
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
                                                    <th class="text-center">Motivo</th>
                                                    <th class="text-center">Descripción</th>
                                                    <th class="text-center">Desde</th>
                                                    <th class="text-center">Hasta</th>
                                                    <th class="text-center">Jefe</th>
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
                <!-- MODAL PARA CREAR UN SECTOR -->
                <div class="modal fade modal-primary" id="modalRegistrarPermiso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-center">
                                <div class="modal-profile">
                                    <i class="nc-icon nc-satisfied"></i>
                                </div>
                            </div>
                            <form class="form-horizontal" novalidate="novalidate" id="frmSolicitarPermiso" name="frmSolicitarPermiso">
                                <div class="modal-body text-center">
                                    <h5 class="category">INGRESA LA INFORMACIÓN DEL PERMISO</h5>
                                    <div class="col-md-12 mr-auto ml-auto">
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="card-category"><strong>Motivo *</strong></small>
                                                    <input id="txtMotivoPermiso" name="MotivoPermiso" type="text" class="form-control">                                                        
                                                </div>
                                            </div>
                                        </fieldset>
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">
                                                    <small class="card-category"><strong>Descripción *</strong></small>
                                                    <textarea id="txtDescripcionPermiso" name="DescripcionPermiso" class="form-control textArea"></textarea>                                                       
                                                </div>
                                            </div>
                                        </fieldset>                                        
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <div class="row">
                                                             <small class="card-category"><strong>Desde *</strong></small>
                                                            <input id="datetimepicker" name="InicioPermiso" type="text" class="form-control datetimepicker" placeholder="Selecciona la fecha de inicio">
                                                        </div>                                                    
                                                    </div>  
                                                </div>  
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <div class="row">
                                                             <small class="card-category"><strong>Hasta *</strong></small>
                                                            <input id="tdFinPermiso" name="FinPermiso" type="text" class="form-control datetimepicker" placeholder="Selecciona la fecha de fin">
                                                        </div>                                                    
                                                    </div>  
                                                </div>
                                            </div>    
                                        </fieldset>                                                                                                                            
                                        <fieldset>
                                            <div class="form-group">
                                                <div class="row">                                                    
                                                    <small class="card-category"><strong>Archivo</strong></small>
                                                    <input type="file" name="file" id="file" class="input-file">
                                                    <label for="file" class="btn btn-tertiary js-labelFile" style="margin-top: 23px; margin-left: -50px; width: 450px;">
                                                      <i class="icon fa fa-check"></i>
                                                      <span class="js-fileName">Selecciona el archivo (PDF)</span>
                                                    </label>                                                                                                   
                                                </div>
                                            </div>
                                        </fieldset>                                        
                                    </div>   
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-simple" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Solicitar permiso</button>
                                </div>
                            </form>
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
            
            $(document).ready(function(){
                $("#opcionesUsuarioNav").addClass("show");
                $("#misPermisos").addClass('active');                                
                $("#tituloPagina").text("Mis permisos");                                                                 
                
                listarPermisos();
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