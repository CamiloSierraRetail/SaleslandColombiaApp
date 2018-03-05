<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Editar Canal - SaleslandColombia</title>
        
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
                        <div class="row">
                            <div class="col-md-10 mr-auto ml-auto">
                                <div class="card">
                                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmEditarCanal" name="frmEditarCanal">
                                        <div class="card-header">
                                            <h4 class="card-title">Ingrese los datos</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-body col-md-10 mr-auto ml-auto">
                                                <fieldset>  
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Estado *</label>
                                                            <div class="col-md-12 mr-auto ml-auto">
                                                                <select id="cmbEditarEstadoCanal" name="EditarEstadocanal" class="selectpicker" data-title="Seleccionar Estado" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                    <option value="Activo">Activo</option>
                                                                    <option value="Inactivo">Inactivo</option>                                                                                                                                
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Nombre del canal *</label>
                                                            <input id="txtEditarNombreCanal" name="EditarNombreCanal" type="text" class="form-control">                                                        
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Descripción *</label>
                                                            <textarea id="txtEditarDescripcionCanal" name="EditarDescripcionCanal" class="form-control textArea"></textarea>                                                       
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>  
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Sector *</label>
                                                            <div class="col-md-12 mr-auto ml-auto">
                                                                <select id="cmbSector" name="EditarSectorCanal" class="selectpicker" data-title="Seleccionar Sector" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                                                                                                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </div>
                                        <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">
                                            <button type="submit" class="btn btn-info btn-fill pull-right">Confirmar Cambios</button>
                                            <div class="clearfix"></div>
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
        cargarSectores();
        verDatosCanal();
    </script>
</html>
