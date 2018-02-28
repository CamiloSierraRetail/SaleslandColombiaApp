<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Registrar Sector - SaleslandColombia</title>
        
    </head>
    <body>
        <div class="wrapper">
            <div class="sidebar" data-color="green" data-image="../assets/img/sidebar-5.jpg">
                <!--
                    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                    Tip 2: you can also add an image using data-image tag
                -->
                <!-- Include Nav Lateral  -->
                <%@include file="../includes/navLateral.jsp" %>
            </div>
            <div class="main-panel">
                <!-- Include Nav Superior -->
                <%@include file="../includes/navSuperior.jsp" %>
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            
                            <div class="col-md-10 mr-auto ml-auto">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Ingrese los datos</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="card-body col-md-10 mr-auto ml-auto">
                                            <form class="form-horizontal">

                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Nombre del sector</label>
                                                            <input id="txtNombreSector" type="text" class="form-control">                                                        
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Descripción</label>
                                                            <textarea id="txtDescripcionSector" class="form-control textArea"></textarea>                                                       
                                                        </div>
                                                    </div>
                                                </fieldset>


                                            </form>
                                        </div>
                                    </div>
                                    <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">
                                        <button class="btn btn-info btn-fill pull-right registrarSector">Confirmar</button>
                                        <div class="clearfix"></div>
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
    </body>
    <%@include file="../includes/jsInclude.jsp" %>
</html>
