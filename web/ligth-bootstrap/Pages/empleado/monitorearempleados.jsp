<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Monitorear Empleados - Salesland Colombia</title>
    </head>
    <body>
        <div class="wrapper">
            <!-- Include Nav Lateral  -->
            <%@include file="../includes/navLateral.jsp" %>
            <div class="main-panel">
                <!-- Include Nav Superior -->
                <%@include file="../includes/navSuperior.jsp" %>   
                <!-- Include div Ingresos -->
                <%@include file="../includes/divIngresos.jsp" %>
                
                <div class="content">
                    <div class="container-fluid">                        
                        <div class="row">
                            <div class="col-md-12 mr-auto ml-auto">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="card">
                                            <div class="card-header ">
                                                <h4 class="card-title">Promedio semanal</h4>
                                                <p class="card-category">Este es el promedio del ingreso del personal en cada uno de los dias de la semana.</p>
                                            </div>
                                            <div class="card-body">
                                                <div class="ct-chart ct-octave">
                                                    
                                                </div>
                                            </div>
                                            <div class="card-footer ">
                                                <div class="legend">
                                                    <i class="fa fa-circle text-info" rel="tooltip" data-original-title="Ingreso Horario A (08:00 - 18:00)" href="#" aria-haspopup="true" aria-expanded="false"></i><i class="fa fa-circle text-warning" rel="tooltip" data-original-title="Ingreso horario B (07:00 - 17:00)" href="#" aria-haspopup="true" aria-expanded="false"></i> Ingreso &nbsp &nbsp
                                                    <i class="fa fa-circle text-danger" rel="tooltip" data-original-title="Salida horario A (08:00 - 18:00)" href="#" aria-haspopup="true" aria-expanded="false"></i><i class="fa fa-circle text-primary" rel="tooltip" data-original-title="Salida horario B (07:00 - 17:00)" href="#" aria-haspopup="true" aria-expanded="false"></i> Salida
                                                </div>
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-check"></i>Datos actualizados desde la ultima salida y el ultimo ingreso.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="card ">
                                            <div class="card-header ">
                                                <h4 class="card-title">Informacion del personal</h4>
                                                <p class="card-category">Este es el promedio general de los empleados que tienes a cargo.</p>
                                            </div>
                                            <div class="card-body ">
                                                <div class="row">
                                                    <div class="table-responsive">
                                                        <table class="table table-hover">
                                                            
                                                            <tbody>

                                                                <tr class="cargarPromedioEntrada" style="cursor: pointer" data-toggle="modal" data-target="#modalPromedios">
                                                                    <td>
                                                                        <div class="flag">
                                                                            <i class="material-icons" style="color: #FFA534">brightness_5</i>
                                                                        </div>
                                                                    </td>
                                                                    <td>Promedio de Entrada</td>
                                                                    <td rel="tooltip" data-original-title="Promedio ingreso horario A" href="#" aria-haspopup="true" aria-expanded="false">
                                                                        <p class="text-muted" id="lblPromedioEntrada_A">0</p>
                                                                    </td>
                                                                    <td rel="tooltip" data-original-title="Promedio ingreso horario B" href="#" aria-haspopup="true" aria-expanded="false">
                                                                        <p class="text-muted" id="lblPromedioEntrada_B">0</p>
                                                                    </td>
                                                                </tr>

                                                                <tr class="cargarPromedioSalida" style="cursor: pointer" data-toggle="modal" data-target="#modalPromedios">
                                                                    <td>
                                                                        <div class="flag">
                                                                            <i class="material-icons" style="color: #006A84">brightness_3</i>
                                                                        </div>
                                                                    </td>
                                                                    <td>Promedio de Salida</td>
                                                                    <td rel="tooltip" data-original-title="Promedio salida horario A" href="#" aria-haspopup="true" aria-expanded="false">
                                                                        <p class="text-muted" id="lblPromedioSalida_A">0</p>
                                                                    </td>
                                                                    <td rel="tooltip" data-original-title="Promedio salida horario B" href="#" aria-haspopup="true" aria-expanded="false">
                                                                        <p class="text-muted" id="lblPromedioSalida_B">0</p>
                                                                    </td>
                                                                </tr>

                                                                <tr style="cursor: pointer" data-toggle="modal" data-target="#modalHorasTrabajadas">
                                                                    <td>
                                                                        <div class="flag">
                                                                            <i class="material-icons" style="color: #23CCEF">alarm</i>
                                                                        </div>
                                                                    </td>
                                                                    <td>Promedio de Horas Trabajadas</td>
                                                                    <td class="text-right">

                                                                    </td>
                                                                    <td class="text-right">
                                                                        <h4 class="card-title" id="lblPromedioHorasTrabajadas">0</h4>
                                                                    </td>
                                                                </tr>                                                            
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                            <div class="card-footer" style="margin-top: -40px;">
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-question"></i> Da click en cualquera de estos items para ver la informacion más detallada.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                                                                                            
                        </div>
                    </div>
                </div>
                <!-- INCLUDE FOOTER -->
                <%@include  file="../includes/footer.jsp" %>
                
                <!-- Mini Modal -->
                <div class="modal fade modal-primary" id="modalPromedios" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">                        
                        <div class="modal-content">                                                        
                            <div class="modal-body text-center">
                                
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card card-wizard">
                                            <div class="card-header ">
                                                <h4 class="card-title text-center" id="lblTituloPromedio">PROMEDIO DE ENTRADA</h4>                                                
                                            </div>
                                            <div class="card-body ">
                                                <ul class="nav nav-tabs nav-pills">
                                                    <li class="nav-item" style="width: 50%;">
                                                        <a class="nav-link active" id="linkTab1" href="#tab1" data-toggle="tab" role="tab" aria-controls="tab1" aria-selected="true">Horario A <small>(08:00 - 18:00)</small></a>
                                                    </li>
                                                    <li class="nav-item" style="width: 50%;">
                                                        <a class="nav-link" href="#tab2" id="linkTab2" data-toggle="tab" role="tab" aria-controls="tab2" aria-selected="true">Horario B <small>(07:00 - 17:00)</small></a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                        <p class="text-center text-muted card-category" id="lblDescripcionTabA">Este es el promedio de los trabajadores menos ranqueados dentro de tu equipo de trabajo.</p><br>
                                                        <div class="row" id="contentTabA">
                                                            <div class="col-md-7">
                                                                <div class="card">
                                                                    <div class="table-responsive">
                                                                        <table class="table table-hover">
                                                                            <tbody id="tblIngresoHA">


                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>                                                                
                                                            </div>                                                               
                                                            <div class="col-md-5">
                                                                <div class="card ">                                                                    
                                                                    <div class="card-body ">
                                                                        <div class="table-responsive">
                                                                            
                                                                            <!-- CODIGO PARA LA GRAFICA -->
                                                                            <div id="chartPromedioHorariosA" class="ct-chart ct-perfect-fourth">


                                                                            </div>                                                                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="card-footer" style="margin-top: -50px;">                                                
                                                                        <hr>
                                                                        <div class="legend">
                                                                            <i class="fa fa-circle text-info"></i> Temprano
                                                                            <i class="fa fa-circle text-danger"></i> Tarde
                                                                            <i class="fa fa-circle text-warning"></i> Justo
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                    
                                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                        <p class="text-center text-muted card-category" id="lblDescripcionTabB">aaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>                                                        
                                                        <div class="row" id="contentTabB">
                                                            <div class="col-md-7">
                                                                <div class="card">
                                                                    <div class="table-responsive">
                                                                        <table class="table table-hover">
                                                                            <tbody id="tblIngresoHB">


                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                                
                                                            </div>   
                                                                                                                        
                                                            <div class="col-md-5">
                                                                <div class="card ">                                                                    
                                                                    <div class="card-body ">
                                                                        <div class="table-responsive">
                                                                            
                                                                            <!-- CODIGO PARA LA GRAFICA -->
                                                                            <div id="chartPromedioHorariosB" class="ct-chart ct-perfect-fourth">


                                                                            </div>                                                                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="card-footer" style="margin-top: -50px;">                                                
                                                                        <hr>
                                                                        <div class="legend">
                                                                            <i class="fa fa-circle text-info"></i> Temprano
                                                                            <i class="fa fa-circle text-danger"></i> Tarde
                                                                            <i class="fa fa-circle text-warning"></i> Justo
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--  End Modal -->
                
                
                <!-- Mini Modal -->
                <div class="modal fade modal-mini modal-primary" id="modalHorasTrabajadas" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header justify-content-center">
                                <div class="modal-profile">
                                    <i class="material-icons" style="color: #23CCEF">alarm</i>
                                </div>
                            </div>
                            <div class="modal-body text-center">
                                <p>Modal horas trabajadas</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-link btn-simple">Back</button>
                                <button type="button" class="btn btn-link btn-simple" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--  End Modal -->
                
            </div>
        </div>
                
        <%@include file="../includes/jsInclude.jsp" %>        
        <script>

            $(document).ready(function (){
                localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
                localStorage.name = $(".spanName").val();
                $("#empleadoItemNav").addClass("show");
                $("#monitorearEmpleadosNav").addClass("active");
                $("#tituloPagina").text("MONITOREAR EMPLEADOS");    
                
                cargarChartPromedioDias();
                cargarPromedioEmpleados();
                
                var fullDate = new Date();
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
                websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
            });
        </script>
    </body>
</html>
