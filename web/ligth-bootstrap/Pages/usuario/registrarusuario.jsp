<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Inicio - SaleslandColombia</title>           
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
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-10 ml-auto mr-auto">
                                    <form id="frmRegistrarUsuario" method="" action="" novalidate="novalidate">
                                        <div class="card card-wizard">
                                            <div class="card-header ">
                                                <h3 class="card-title text-center">Registro de usuario</h3>
                                                <p class="card-category text-center">Split a complicated flow in multiple steps</p>
                                            </div>
                                            <div class="card-body ">
                                                <ul class="nav nav-tabs nav-pills">
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link active" href="#tab1" data-toggle="tab" role="tab" aria-controls="tab1" aria-selected="true">First Tab</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab2" data-toggle="tab" role="tab" aria-controls="tab2" aria-selected="true">Second Tab</a>
                                                    </li>
                                                    <li class="nav-item" style="width: 33.3333%;">
                                                        <a class="nav-link" href="#tab3" data-toggle="tab" role="tab" aria-controls="tab3" aria-selected="true">Third Tab</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                        <p class="text-center">Por favor ingresa los datos del usuario</p>
                                                        
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Tipo de Documento <star>*</star></label>                                    
                                                                    <select id="cmbTipoDocumentoUsuario" name="TipoDocumentoUsuario" class="selectpicker" data-title="Seleccionar Estado" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="T.I">Tarjeta de indentidad (T.I)</option>
                                                                        <option value="C.C">Cedula de ciudadanía (C.C)</option> 
                                                                        <option value="C.E">Cedula de extranjería (C.E)</option> 
                                                                        <option value="NIT">Nit</option> 
                                                                        <option value="PASAPORTE">Pasaporte</option> 
                                                                        <option value="RUC">Reg. unico de contribuyentes (RUC)</option> 
                                                                        <option value="OTRO">Otro</option>
                                                                    </select>
                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Documento <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtDocumentoUsuario" name="DocumentoUsuario" placeholder="ej: Perez Perez">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Nombres <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtNombreUsuario" name="NombreUsuario" placeholder="ej: Brandon Felipe">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Apellidos <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtApellidoUsuario" name="ApellidoUsuario" placeholder="ej: Perez Perez">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-10">
                                                                <div class="form-group">
                                                                    <label class="control-label">Email
                                                                        <star>*</star>
                                                                    </label>
                                                                    <input class="form-control" type="text" id="txtEmailUsuario" name="EmailUsuario" email="true" placeholder="ej: usuario@salesland.net">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Contraseña <star>*</star></label>
                                                                    <input class="form-control" type="password" id="txtContraseniaUsuario" name="ContraseniaUsuario" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Confirmar Contraseña<star>*</star></label>
                                                                    <input class="form-control" type="password" id="txtConfirmarContraseniaUsuario" name="ConfirmarContraseniaUsuario" placeholder="ej: ****************">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                        <h5 class="text-center">sigue ingresando los datos, ya falta poco (._.)</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Direccion <star>*</star></label>
                                                                    <input class="form-control" type="text" id="txtDireccionUsuario" name="DireccionUsuario" placeholder="ej: carrera 16A # 85-64 Bogotá">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Genero<star>*</star></label>
                                                                    <select id="cmbGeneroUsuario" name="GeneroUsuario" class="selectpicker"  data-title="Selecciona el genero" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                        <option value="Masculino">Masculino</option>
                                                                        <option value="Femenino">Femenino</option>
                                                                        <option value="Otro">Otro</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Celular <star>*</star></label>
                                                                    <input class="form-control" type="tel" id="txtCelularUsuario" name="CelularUsuario" placeholder="ej: 315 8921655">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <label class="control-label">Telefono <star>*</star></label>
                                                                    <input class="form-control" type="tel" id="txtTelefonoUSuario" name="TelefonoUsuario" placeholder="ej: 7435596">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-5 ">
                                                                <div class="form-group">
                                                                    <label class="control-label">Fecha de nacimiento <star>*</star></label>
                                                                    <div class="form-group">
                                                                        <input id="datetimepicker" type="text" class="form-control datepicker" placeholder="Selecciona la fecha de nacimiento" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-5">
                                                                <div class="form-group">
                                                                    <!--<div class="photo">
                                                                        <img src="../../assets/img/default-avatar.png">
                                                                        <input type="file" id="wizard-picture">
                                                                    </div>-->
                                                                    
                                                                    
                                                                   
                                                                    
                                                                    <!--<div class="user">
                                                                        <div class="photo">
                                                                            <img src="../../assets/img/default-avatar.png" style ="border-radius: 50%; height: 80px;width: 80px;">
                                                                            <input type="file" id="wizard-picture">
                                                                        </div>
                                                                    </div>-->
                                                                    
                                                                    <label class="control-label">Imagen de perfil <star>*</star></label>
                                                                    <input class="form-control" type="file" id="fileImagenUsuario" name="ImagenUsuario" placeholder="ej: 7435596">
                                                                    
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="tab3" role="tabpanel">
                                                        <h5 class="text-center">solo faltan algunos campos (>_<)</h5>
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-12">



                                                                <div class="card bootstrap-table">
                                                                    <div class="card-body table-full-width">
                                                                        <div class="toolbar">
                                                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                                                        </div>
                                                                        <table id="bootstrap-table" class="table">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Select</th>
                                                                                    <th>Nombre</th>
                                                                                    <th>Descripción</th>
                                                                                    <th>Sector</th> 
                                                                                    <th>Tipo</th> 
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody id="listadoCargosSectores">
                                                                                
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>                                                                                                                            
                                                            </div>
                                                        </div>                                                        
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-footer text-center">
                                                <button type="button" class="btn btn-default btn-wd btn-back pull-left">Volver</button>
                                                <button type="button" class="btn btn-info btn-wd btn-next pull-right">Siguiente</button>
                                                <button type="submit" class="btn btn-info btn-wd btn-finish pull-right" style="display: none;">Finalizar</button>
                                                <div class="clearfix"></div>
                                            </div>
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
        $(document).ready(function (){            
            cargarCargosSectores();
            InicializarFormularioRegistro();
        });
    </script>
    <script type="text/javascript">
        function putaTabla(){
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
            showRefresh: false,
            search: true,
            showToggle: false,
            showColumns: false,
            pagination: true,
            searchAlign: 'left',
            pageSize: 4,
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
        }
</script>    
</html>
