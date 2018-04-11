<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        
        <title>Sectores - SaleslandColombia</title>       
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
                                        <h4 class="card-title">Nuestros sectores</h4>
                                        <p class="card-category">En este listado se muestran todos los sectores de la empresa.</p>
                                    </div>
                                    <div class="card-body table-full-width" id="tablaModificada">                                        
                                        <div class="toolbar" id="toolbar">
                                            <button class="btn btn-outline btn-round" data-toggle="modal" data-target="#modalRegistrarSector">                                                
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
                                                    <th class="text-right">Estado</th>
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoSectores">

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
        <!-- MODAL PARA CREAR UN SECTOR -->
        <div class="modal fade modal-primary" id="modalRegistrarSector" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarSector" name="frmRegistrarSector">
                        <div class="modal-body text-center">
                            <h5 class="category">INGRESA LOS DATOS DEL SECTOR</h5>
                            <div class="col-md-12 mr-auto ml-auto">
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="card-category"><strong>Nombre del sector *</strong></small>
                                            <input id="txtNombreSector" name="NombreSector" type="text" class="form-control">                                                        
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="card-category"><strong>Descripción *</strong></small>
                                            <textarea id="txtDescripcionSector" name="DescripcionSector" class="form-control textArea"></textarea>                                                       
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
        <div class="modal fade modal-primary" id="ModalEditarSector" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmEditarSector" name="frmEditarSector">
                        <div class="modal-body text-center">
                            <h5 class="category">ACTUALIZA LOS DATOS DEL SECTOR</h5>
                            <div class="col-md-12 mr-auto ml-auto">
                                <input id="idSector" type="password" style="display: none" >
                                <fieldset>  
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Estado *</strong></small>                                                            
                                            <select id="cmbEditarEstadoSector" name="EditarEstadoSector" class="form-control" data-title="Seleccionar Estado" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                <option value="Activo">Activo</option>
                                                <option value="Inactivo">Inactivo</option>                                                                                                                                
                                            </select>                                                          
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Nombre del sector *</strong></small>
                                            <input id="txtEditarNombreSector" name="EditarNombreSector" type="text" class="form-control">                                                        
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Descripción *</strong></small>
                                            <textarea id="txtEditarDescripcionSector" name="EditarDescripcionSector" class="form-control textArea"></textarea>                                                       
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
        
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            
            $(document).ready(function (){
                
                listarSectores();             
                $("#empresaItemNav").addClass("show");
                $("#sectoresItemNav").addClass('active');
                $("#tituloPagina").text("Sectores");

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
