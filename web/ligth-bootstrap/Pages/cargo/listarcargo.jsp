<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        
        <title>Listado de Cargos - SaleslandColombia</title>       
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
                            <div class="col-md-12 mr-auto ml-auto">
                                <div class="card">
                                    <form class="form-horizontal" action="" novalidate="novalidate" id="frmRegistrarCargos" name="frmRegistrarCargos">                                                                                
                                        <div class="card-header ">
                                            <h4 class="card-title">Listado de Cargos</h4>
                                            <p class="card-category">Cargos que se encuentran actualmente en la empresa</p>
                                        </div>
                                        <div class="card-body table-full-width">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">#</th>
                                                        <th>Nombre</th>
                                                        <th>Descripción</th>
                                                        <th>Salario</th>
                                                        <th class="text-right">Acciones</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="listadoCargos">
                                                    <tr>
                                                        <td class="text-center">1</td>
                                                        <td>Cargando...</td>
                                                        <td>Cargando...</td>
                                                        <td>Cargando...</td>
                                              
                                                        
                                                        <td class="td-actions text-right">
                                                            <a href="#" rel="tooltip" title="" class="btn btn-info btn-link btn-xs" data-original-title="View Profile">
                                                                <i class="fa fa-user"></i>
                                                            </a>
                                                            <a href="#" rel="tooltip" title="" class="btn btn-success btn-link btn-xs" data-original-title="Edit Profile">
                                                                <i class="fa fa-edit"></i>
                                                            </a>
                                                            <a href="#" rel="tooltip" title="" class="btn btn-danger btn-link btn-xs" data-original-title="Remove">
                                                                <i class="fa fa-times"></i>
                                                            </a>
                                                        </td>
                                                    </tr>                                                                                                                                                       
                                                </tbody>
                                            </table>
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
    <script> listarCargos(); </script>
</html>
</html>
