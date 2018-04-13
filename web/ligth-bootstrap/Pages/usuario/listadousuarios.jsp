<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                     
        <!-- MODAL PARA CREAR UN SECTOR -->
        <div class="modal fade modal-primary" id="modalVerUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
            
            
    </body>        
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        
        $(document).ready(function (){
            
            $("#empleadoItemNav").addClass("show");
            $("#listadoEmpleadosNav").addClass('active');
            $("#tituloPagina").text("EMPLEADOS");
            listarUsuarios();
            
            localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
            localStorage.name = $(".spanName").val();
            var fullDate = new Date();
            var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
            var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
            websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
            
        });
                
    </script>        
</html>
