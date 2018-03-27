<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Listado de Cargos SaleslandColombia</title>       
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
                                    <h4 class="card-title">Nuestros Cargos</h4>
                                    <p class="card-category">En este listado se muestran todas los cargos registrados en la empresa.</p>
                                </div>
                                    <div class="modal-body text-center" id="tablaModificada">
                                        <div class="toolbar" id="toolbar">
                                            <button class="btn btn-outline btn-round" data-toggle="modal" data-target="#modalRegistrarCargo">                                                
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
                                                    <th>Tipo</th>
                                                    <th>Sector</th>
                                                    <th>Canal</th>
                                                    <th>Area</th>
                                                    <th>Estado</th>                                                
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoCargos">

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
        <div class="modal fade modal-primary" id="modalRegistrarCargo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header justify-content-center">
                        <div class="modal-profile">
                            <i class="nc-icon nc-puzzle-10"></i>
                        </div>
                    </div>
                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarCargos" name="frmRegistrarCargo">
                        <div class="modal-body text-center">
                            <h5 class="category">INGRESA LOS DATOS DEL CARGO</h5>
                            <div class="col-md-12 mr-auto ml-auto">
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Nombre del Cargo *</strong></small>
                                            <input id="txtNombreCargo" name="NombreCargo" type="text" class="form-control">                                                        
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Descripción *</strong></small>
                                            <textarea id="txtDescripcionCargo" name="DescripcionCargo" class="form-control textArea"></textarea>                                                       
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="form-group">
                                        <div class="row">
                                            <small class="control-label"><strong>Selecciona tipo de Usuario *</strong></small>
                                            <select name="opciones" id="cmbTipo" class="form-control" data-title="Seleccionar Tipo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                      
                                            <option>Seleccione</option>
                                            <option value="Director">Director</option>                                                       
                                            <option value="JefeCanal">Jefe Canal</option>
                                            <option value="CoordinadorCanal">Coordinador</option>                                                       
                                            <option value="JefeArea">Jefe Area</option>
                                            <option value="Empleado">Empleado</option>
                                            </select>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="nsector">
                                        <div class="form-group">                                                
                                            <div class="row">
                                                <small class="control-label"><strong>Sector *</strong></small>
                                                <select name="Sector" id="cmbSector" class="form-control" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                           
                                                    <option>Seleccione</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="ncanal">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Canal *</strong></small>
                                                <select name="Canal" id="cmbCanal" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div id="narea">
                                        <div class="form-group">
                                            <div class="row">
                                                <small class="control-label"><strong>Area *</strong></small>
                                                <select name="Area" id="cmbArea" class="form-control" data-title="Seleccionar">
                                                    <option>Seleccione</option>
                                                </select>
                                            </div>
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
        <%@include file="../includes/jsInclude.jsp" %>
        <script>
            listarCargos();
            $("#nsector").hide();
            $("#ncanal").hide();
            $("#narea").hide();

            $("#cmbTipo").change(function (){
               var tipo = $("#cmbTipo").val();
               if (tipo == "Director") {               
                   $("#nsector").show();  
                   $("#ncanal").hide();
                   $("#narea").hide();
                   cargarSectores();
               }else if (tipo == "JefeCanal" || tipo == "CoordinadorCanal" ) {                                                             
                   $("#nsector").show();
                   $("#ncanal").show();
                   $("#narea").hide();
                   cargarSectores();                   
               }else if (tipo == "Empleado" || tipo == "JefeArea"){

                   $("#nsector").show();
                   $("#ncanal").show();
                   $("#narea").show();
                   cargarSectores();
               }

            });
///////////////////////////  EVENTO DEL SECTOR /////////////////////////////////
            $("#cmbSector").change(function (){
                if ($("#cmbTipo").val() != "Director" ) { 
                    
                    cargarCanalesDependientes();
                
                }                
            });
            
///////////////////////////  EVENTO DEL CANAL /////////////////////////////////            
            $("#cmbCanal").change(function (){
                if ($("#cmbTipo").val() == "Empleado" || $("#cmbTipo").val() == "JefeArea" ) { 
                    
                    cargarAreasDependientes();
                
                }                
            });
            ///////////////////////////////// FUNCION PARA CARGAR LOS CANALES /////////////////////////////7
            function cargarCanalesDependientes(){
                var idsector = $("#cmbSector").val();                
                $.post("/SaleslandColombiaApp/canal/cargarcanalesdependientes",{idSector:idsector},function(responseText) {                
                    if (responseText == "500") {

                        swal("Ocurrio un error", "Lo sentimos, los datos de los canales no se lograron cargar, por favor intentalo mas tarde.", "error");

                    }else{
                        var selectCount = 0;
                        var contador = 0;
                        var dt = JSON.parse(responseText); 
                        $("#cmbCanal").html("");
                        for (var i = 0, max = dt.length; i < max; i++) {
                            
                            if (contador == 1) {
                                contador = 0;

                            }else{
                                
                                if (selectCount == 0) {
                                    $("#cmbCanal").append("<option>Seleccione</option>");
                                    selectCount++;
                                }
                                $("#cmbCanal").append("<option value='"+dt[i]+"'>"+dt[i+1]+"</option>");
                                contador++;
                            }
                        }

                    }

                });
            }
            ////////////7 FUNCION PARA CARGAR LAS AREAS //////////////////
            function cargarAreasDependientes (){
                var idCanal = $("#cmbCanal").val(); 
                $.post("/SaleslandColombiaApp/area/cargarareasdependientes",{idCanal:idCanal},function(responseText) {
                    if (responseText == "500") {

                        swal("Ocurrio un error", "Lo sentimos, los datos de los canales no se lograron cargar, por favor intentalo mas tarde.", "error");

                    }else{
                        var contador = 0;
                        var selectCount = 0;
                        var dt = JSON.parse(responseText); 
                        $("#cmbArea").html("");
                        for (var i = 0, max = dt.length; i < max; i++) {
                            
                            if (selectCount == 0) {
                                $("#cmbArea").append("<option>Seleccione</option>");
                                selectCount++;
                            }
                            
                            if (contador == 1) {
                                contador = 0;

                            }else{
                                $("#cmbArea").append("<option value='"+dt[i]+"'>"+dt[i+1]+"</option>");
                                contador++;
                            }
                        } 

                    }
    
                }); 
            }

            $("#tituloPagina").text("Cargos");
        </script>
    </body>        
</html>
