<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Listado de Areas SaleslandColombia</title>       
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
                            <div class="col-md-12 mr-auto ml-auto">
                                 <div class="card bootstrap-table">
                                 <div class="card-header">
                                        <h4 class="card-title">Nuestras Areas</h4>
                                        <p class="card-category">En este listado se muestran todas las areas registradas en la empresa.</p>
                                    </div>                                                   
                                     <div class="toolbar" id="toolbar">
                                            <a href="registrararea.jsp" class="btn btn-outline btn-round">                                                
                                                Nuevo
                                                <span class="btn-label">
                                                    <i class="fa fa-plus"></i>
                                                </span>
                                            </a>
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <table id="bootstrap-table" data-toolbar="#toolbar" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th>Nombre</th>
                                                    <th>Descripción</th>
                                                    <th>Canal</th>
                                                    <th class="text-right">Estado</th>
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoArea">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>                                                                                                            
                    </div>
                </div>
            </div>
            <!-- INCLUDE FOOTER -->
            <%@include  file="../includes/footer.jsp" %>
        </div>
    </body>        
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        listarArea();
         $("#tituloPagina").text("Empresa-Areas");
    </script>
</html>
