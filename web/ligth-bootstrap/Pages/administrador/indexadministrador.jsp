<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try{      
        if(session.getAttribute("UsuarioIngresado").equals("") || session.getAttribute("UsuarioIngresado").equals(null)){
            response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
        }
        else{
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Inicio - Salesland Colombia</title>
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
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-badge text-warning"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Ingresos</p>
                                                    <h4 class="card-title" id="numeroIngresos">0</h4>                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-clock-o"></i> <small id="ultimoIngreso"></small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-check-2 text-success"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Correctos</p>
                                                    <h4 class="card-title" id="ingresosCorrectos">0</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-clock-o"></i> <small id="ultimoIngresoCorrecto"></small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-simple-remove text-danger"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Erroneos</p>
                                                    <h4 class="card-title" id="ingresosErroneos">0</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-clock-o"></i> <small id="ultimoIngresoErroneo"></small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-time-alarm text-primary"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">A tiempo</p>
                                                    <h4 class="card-title" id="ingresosJusto">0</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-clock-o"></i> <small id="ultimoIngresoJusto"></small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 mr-auto ml-auto">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="card">
                                            <div class="card-header ">
                                                <h4 class="card-title">Esta semana</h4>
                                                <p class="card-category">En esta grafica se representa tu hora de entrada y salida cada dia a lo largo de la semana.</p>
                                            </div>
                                            <div class="card-body">
                                                <div class="ct-chart ct-octave">
                                                    
                                                </div>
                                            </div>
                                            <div class="card-footer ">
                                                <div class="legend">
                                                    <i class="fa fa-circle blue-corp"></i> Ingreso
                                                    <i class="fa fa-circle gray-corp"></i> Salida
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
                                                <h4 class="card-title">Promedio</h4>
                                                <p class="card-category">Este es el promedio de entrada y salida que llevas a lo largo de toda tu estancia en la empresa.</p>
                                            </div>
                                            <div class="card-body ">
                                                <div id="chartPromedio" class="ct-chart ct-perfect-fourth"></div>
                                            </div>
                                            <div class="card-footer ">
                                                <div class="legend">
                                                    <i class="fa fa-circle blue-corp"></i> Correctos
                                                    <i class="fa fa-circle gray-corp"></i> Erroneos
                                                    <i class="fa fa-circle orange-corp"></i> A tiempo
                                                </div>
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-clock-o"></i> Información actualizada justo ahora
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
            </div>
        </div>
        <%@include file="../includes/jsInclude.jsp" %>        
        <script>
            
            $(document).ready(function (){
                localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
                localStorage.name = $(".spanName").val();
                $("#home").addClass("active");
                $("#tituloPagina").text("Inicio");              
                /*cargarPromedio();
                loadWeeklyData($("#txtIdUsuario").val());*/
                
                var fullDate = new Date();
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
                websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
            });
        </script>
    </body>
</html> 
<%        }
    }  
    catch(NullPointerException ex){
        response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
    }
%>