<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Editar Area- SaleslandColombia</title>
    </head>
    <body>
        <div class="wrapper">  
            <!-- INCLUEDE NAV LATERAL -->
            <%@include file="../includes/navLateral.jsp" %>
            <div class="main-panel">
                <!-- INCLUEDE NAV SUPERIOR -->
                <%@include file="../includes/navSuperior.jsp" %>
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-10 mr-auto ml-auto">
                                <div class="card">
                                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmEditarArea" name="frmEditarArea">
                                        <div class="card-header">
                                            <h4 class="card-title">Actualizar Areas</h4>
                                            <p class="card-category">Ingrese los cambios, por favor diligenciar correctamente</p>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-body col-md-10 mr-auto ml-auto">
                                                <fieldset>  
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Estado*</label>
                                                            <div class="col-md-12 mr-auto ml-auto">
                                                                <select id="cmbEditarEstadoArea" name="EditarEstadoarea" class="selectpicker" data-title="Seleccionar Estado" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
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
                                                            <label class="control-label">Nombre del Area *</label>
                                                            <input id="txtEditarNombreArea" name="EditarNombreArea" type="text" class="form-control">                                                        
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Descripción *</label>
                                                            <textarea id="txtEditarDescripcionArea" name="EditarDescripcionArea" class="form-control textArea"></textarea>                                                       
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>  
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Canal *</label>
                                                            <div class="col-md-12 mr-auto ml-auto">
                                                                <select id="cmbCanal" name="EditarCanal" class="selectpicker" data-title="Seleccionar Canal" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">                                                                                                                                                                                               
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </div>
                                        <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">
                                            <a href="listarareas.jsp" class="btn btn-fill pull-left">Volver</a>
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
    <!-- INCLUDE JAVA SCRIPT -->
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        cargarCanal();
        verDatosArea();      
    </script>
     <script>$("#tituloPagina").text("Empresa-Areas");</script>
</html>