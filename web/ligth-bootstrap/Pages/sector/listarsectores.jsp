<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        
        <title>Listado de sectores - SaleslandColombia</title>       
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
                                    <form class="form-horizontal" action="" novalidate="novalidate" id="frmRegistrarSector" name="frmRegistrarSector">                                                                                
                                        <div class="card-header ">
                                            <h4 class="card-title">Table with Links</h4>
                                            <p class="card-category">Here is a subtitle for this table</p>
                                        </div>
                                        <div class="card-body table-full-width">
                                            <table class="table">
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
                                                    <tr>
                                                        <td class="text-center">1</td>
                                                        <td>Andrew Mike</td>
                                                        <td>Develop</td>
                                                        <td class="text-right">                                                                            
                                                            <div class="row">
                                                                <div class="col-md-12">                                                                                    
                                                                    <input type="checkbox" checked="" data-toggle="switch" data-on-color="info" data-off-color="info">
                                                                    <span class="toggle"></span>
                                                                </div>
                                                            </div>
                                                        </td>
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
                                                    <tr>
                                                        <td class="text-center">2</td>
                                                        <td>John Doe</td>
                                                        <td>Design</td>
                                                        <td class="text-right">€ 89,241</td>
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
                                                    <tr>
                                                        <td class="text-center">3</td>
                                                        <td>Alex Mike</td>
                                                        <td>Design</td>
                                                        <td class="text-right">€ 92,144</td>
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
                                                    <tr>
                                                        <td class="text-center">4</td>
                                                        <td>Mike Monday</td>
                                                        <td>Marketing</td>
                                                        <td class="text-right">€ 49,990</td>
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
                                                    <tr>
                                                        <td class="text-center">5</td>
                                                        <td>Paul Dickens</td>
                                                        <td>Communication</td>
                                                        <td class="text-right">€ 69,201</td>
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
    <script> listarSectores(); </script>
</html>
</html>
