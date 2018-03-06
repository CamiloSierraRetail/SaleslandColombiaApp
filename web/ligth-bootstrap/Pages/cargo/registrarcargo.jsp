<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Registrar Cargos - SaleslandColombia</title>

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
                                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarCargo" name="frmRegistrarCargo">
                                        <div class="card-header">
                                            <h4 class="card-title">Ingrese los datos</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-body col-md-10 mr-auto ml-auto">
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Nombre del Cargo *</label>
                                                            <input id="txtNombreCargo" name="NombreCargo" type="text" class="form-control">                                                        
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Descripción *</label>
                                                            <textarea id="txtDescripcionCargo" name="DescripcionCargo" class="form-control textArea"></textarea>                                                       
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">Salario *</label>
                                                            <input id="txtSalario"type="number" value="781.242" min="781.242" class="form-control currency" name="Salario" ></input>                                                       
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Selecciona tipo de Usuario*</label>
                                                        <select name="opciones" id="select" class="selectpicker" data-title="Seleccionar Tipo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" onchange="habilitar(this.value);">                                                      
                                                            <option value='segundo'>Director</option>
                                                            <option value='tercero'>Jefe Canal</option>
                                                            <option value='cuarto'>Coordinador Canal</option>
                                                            <option value='segundo'>Jefe Area</option>
                                                            <option value='5'>Empleado </option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div id="mielemento">
                                                    Contenido del elemento...
                                                </div>
                                                <div class="form-group">                                                
                                                    <div class="row">
                                                        <label class="control-label">Sector*</label>
                                                        <select name="Sector" id="segundo" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >
                                                            <option value=''>seleccion 1</option>
                                                            <option value=''>seleccion 2</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Canal*</label>
                                                        <select name="Canal" id="tercero" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >
                                                            <option value=''>seleccion 1</option>
                                                            <option value=''>seleccion 2</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Area*</label>
                                                        <select name="Area" id="cuarto" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >
                                                            <option value=''>seleccion 1</option>
                                                            <option value=''>seleccion 2</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                

                                            </div>

                                        </div>
                                        <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">
                                            <button type="submit" class="btn btn-info btn-fill pull-right">Confirmar</button>
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
    <script type="text/javascript" src="jquery-1.4.2.min.js"></script>


    <%@include file="../includes/jsInclude.jsp" %>
</html>
