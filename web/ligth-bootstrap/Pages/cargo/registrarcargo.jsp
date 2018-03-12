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
                                    <form class="form-horizontal" action="" method="" novalidate="novalidate" id="frmRegistrarCargos" name="frmRegistrarCargo">
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
                                                        <select name="opciones" id="cmbTipo" class="selectpicker" onChange="pagoOnChange(this)" data-title="Seleccionar Tipo" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                      
                                                        <option value="director">Director</option>                                                       
                                                        <option value="jefecanal">Jefe Canal</option>
                                                        <option value="coordinador">Coordinador</option>                                                       
                                                        <option VALUE="jefearea">Jefe Area</option>
                                                        <option VALUE="empleado">Empleado</option>
                                                        </select>
                                                    </div>
                                                </div>                                                                                                             
                                                <div id="nsector" style="display:none;">
                                                     <div class="form-group">                                                
                                                    <div class="row">
                                                        <label class="control-label">Sector*</label>
                                                        <select name="Sector" id="cmbSector" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >                                                           
                                                        </select>
                                                    </div>
                                                </div>
                                                </div>
                                                <div id="ncanal" style="display:none;">
                                                     <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Canal*</label>
                                                        <select name="Canal" id="cmbCanal" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >
                                                           
                                                        </select>
                                                    </div>
                                                </div>
                                                </div>
                                                <div id="narea" style="display:none;">
                                                     <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Area*</label>
                                                        <select name="Area" id="cmbArea" class="selectpicker" data-title="Seleccionar" data-style="btn-default btn-outline" data-menu-style="dropdown-blue" >
                                                           
                                                        </select>
                                                    </div>
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
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        
          $(document).ready(function (){
            cargarSectores();        
            cargarCanal();
            cargarAreas();
        });
        </script>
    <script>
       
        function pagoOnChange(sel) {
            if (sel.value == "director") {
                divC = document.getElementById("nsector");
                divC.style.display = "";

                divC = document.getElementById("ncanal");
                divC.style.display = "none";

                divT = document.getElementById("narea");
                divT.style.display = "none";

            } else if (sel.value == "jefecanal") {
                divC = document.getElementById("nsector");
                divC.style.display = "";

                divC = document.getElementById("ncanal");
                divC.style.display = "";

                divT = document.getElementById("narea");
                divT.style.display = "none";
                
            }else if (sel.value == "coordinador") {
                divC = document.getElementById("nsector");
                divC.style.display = "none";

                divC = document.getElementById("ncanal");
                divC.style.display = "";

                divT = document.getElementById("narea");
                divT.style.display = "none";
                
            }else if (sel.value == "jefearea") {
                divC = document.getElementById("nsector");
                divC.style.display = "";

                divC = document.getElementById("ncanal");
                divC.style.display = "";

                divT = document.getElementById("narea");
                divT.style.display = "";
                
            } else if (sel.value == "empleado") {
                divC = document.getElementById("nsector");
                divC.style.display = "";

                divC = document.getElementById("ncanal");
                divC.style.display = "";

                divT = document.getElementById("narea");
                divT.style.display = "";
                
            }else{

                divC = document.getElementById("nsector");
                divC.style.display = "none";

                divC = document.getElementById("ncanal");
                divC.style.display = "none";

                divT = document.getElementById("narea");
                divT.style.display = "none";
            }
            ;


        }
    </script>
    <script type="text/javascrpt">
       $(document).ready(function (){
          $('#cmbSector').change(function (){
             SeleccionandoCombo(this,'idCanal') 
          }); 
        });
        function SeleccionandoCombo(combo1, combo2){
            combo2 = document.getElementById(combo2);
            LimpiarCombo(combo2);
            if(combo1.options [combo1,SelectedIndex].value 1= ""){
            combo2.disable = true;
            }
        }
        
        
    </script>

    
</html>
