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
                                <div class="card bootstrap-table">                                                                                                                  
                                    <div class="card-header ">
                                        <h4 class="card-title">Table with Links</h4>
                                        <p class="card-category">Here is a subtitle for this table</p>
                                    </div>                                    
                                    <div class="col-md-12">
                                        <div class="card bootstrap-table">
                                            <div class="card-body table-full-width">
                                                <div class="toolbar">
                                                    <!--        Here you can write extra buttons/actions for the toolbar-->                                                    
                                                </div>
                                                <table id="bootstrap-table" class="table">
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
<<<<<<< HEAD
                                    </div>                                                                          
=======
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
>>>>>>> origin/master
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
    <script type="text/javascript">
        var $table = $('#bootstrap-table');

        function operateFormatter(value, row, index) {
            return [
                '<a rel="tooltip" title="View" class="btn btn-link btn-info table-action view" href="javascript:void(0)">',
                '<i class="fa fa-image"></i>',
                '</a>',
                '<a rel="tooltip" title="Edit" class="btn btn-link btn-warning table-action edit" href="javascript:void(0)">',
                '<i class="fa fa-edit"></i>',
                '</a>',
                '<a rel="tooltip" title="Remove" class="btn btn-link btn-danger table-action remove" href="javascript:void(0)">',
                '<i class="fa fa-remove"></i>',
                '</a>'
            ].join('');
        }

        $().ready(function() {
            window.operateEvents = {
                'click .view': function(e, value, row, index) {
                    info = JSON.stringify(row);

                    swal('You click view icon, row: ', info);
                    console.log(info);
                },
                'click .edit': function(e, value, row, index) {
                    info = JSON.stringify(row);

                    swal('You click edit icon, row: ', info);
                    console.log(info);
                },
                'click .remove': function(e, value, row, index) {
                    console.log(row);
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
                }
            };

            $table.bootstrapTable({
                toolbar: ".toolbar",
                clickToSelect: true,
                showRefresh: true,
                search: true,
                showToggle: true,
                showColumns: true,
                pagination: true,
                searchAlign: 'left',
                pageSize: 8,
                clickToSelect: false,
                pageList: [8, 10, 25, 50, 100],

                formatShowingRows: function(pageFrom, pageTo, totalRows) {
                    //do nothing here, we don't want to show the text "showing x of y from..."
                },
                formatRecordsPerPage: function(pageNumber) {
                    return pageNumber + " rows visible";
                },
                icons: {
                    refresh: 'fa fa-refresh',
                    toggle: 'fa fa-th-list',
                    columns: 'fa fa-columns',
                    detailOpen: 'fa fa-plus-circle',
                    detailClose: 'fa fa-minus-circle'
                }
            });

            //activate the tooltips after the data table is initialized
            $('[rel="tooltip"]').tooltip();

            $(window).resize(function() {
                $table.bootstrapTable('resetView');
            });


        });
    </script>
</html>
</html>
</html>
