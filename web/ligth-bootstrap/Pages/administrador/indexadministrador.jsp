<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Inicio | SaleslandColombia</title>
        
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
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-planet text-warning"></i>
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
                                            <i class="fa fa-refresh"></i> Actualizado ahora
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
                                                    <i class="nc-icon nc-spaceship text-success"></i>
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
                                            <i class="fa fa-calendar-o"></i> Last day
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
                                                    <i class="nc-icon nc-fav-remove text-danger"></i>
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
                                            <i class="fa fa-clock-o"></i> In the last hour
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
                                                    <i class="nc-icon nc-alien-33 text-primary"></i>
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
                                            <i class="fa fa-refresh"></i> Update now
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 mr-auto ml-auto">
                                <div class="row">
                                    <div class="col-md-12 pull-left">
                                        <div class="card">
                                            <div class="card-header ">
                                                <h4 class="card-title">Esta semana</h4>
                                                <p class="card-category">En esta grafica se muestra la hora tu entrada y salida en lo que ha corrido de la semana.</p>
                                            </div>
                                            <div class="card-body ">
                                                <div id="chartActivity" class="ct-chart"></div>
                                            </div>
                                            <div class="card-footer ">
                                                <div class="legend">
                                                    <i class="fa fa-circle text-info"></i> Ingreso
                                                    <i class="fa fa-circle text-danger"></i> Salida
                                                </div>
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-check"></i>Información de datos certificada (>_<)
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card ">
                                            <div class="card-header ">
                                                <h4 class="card-title">Mis ingresos</h4>
                                                <p class="card-category">En este grafico se muestra el porcentaje de entrada del usuario</p>
                                            </div>
                                            <div class="card-body ">
                                                <div id=chartPreferences class="ct-chart ct-perfect-fourth"></div>
                                            </div>
                                            <div class="card-footer ">
                                                <div class="legend">
                                                    <i class="fa fa-circle text-info"></i> Open
                                                    <i class="fa fa-circle text-danger"></i> Bounce
                                                    <i class="fa fa-circle text-warning"></i> Unsubscribe
                                                </div>
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-clock-o"></i> Campaign sent 2 days ago
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
                $("#tituloPagina").text("Inicio");
                
                cargarPromedio();
                preferencias();
                inicializarChartIngreso();

            });
        </script>
        <script>
            function preferencias(){
                
                var data = {
                    series: [62, 32, 6, 32, 32]
                };

                var sum = function(a, b) { return a + b };

                new Chartist.Pie('#chartPreferences', data, {
                    labelInterpolationFnc: function(value) {
                      return Math.round(value / data.series.reduce(sum) * 100) + '%';
                    }
                });

            }
            function inicializarChartIngreso(){

                var fullDate = new Date();
                var weekday = new Array(7);
                weekday[0] = "Domingo";
                weekday[1] = "Lunes";
                weekday[2] = "Martes";
                weekday[3] = "Miercoles";
                weekday[4] = "Jueves";
                weekday[5] = "Viernes";
                weekday[6] = "Sabado";
                var n = weekday[fullDate.getDay()];

                //convert month to 2 digits
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
                
                $.post("/SaleslandColombiaApp/ingreso/chartSemana",{Fecha:currentDate, Dia:n},function (responseText){
                    
                    if (responseText == "500") {
                        
                    }else{
                        
                        var dt = JSON.parse(responseText);   
                        
                        var data = {
                        labels: ['Lun', 'Mar', 'Mier', 'Jue', 'Vie'],
                        series: [

                                [dt[0], dt[2], dt[4], dt[6], dt[8]],
                                [dt[1], dt[3], dt[5], dt[7], dt[9]]

                            ]
                        };

                        var options = {
                            seriesBarDistance:10,
                            axisX: {
                                showGrid: false
                            },
                            high: 24,
                            height: "264px"
                        };

                        var responsiveOptions = [
                            ['screen and (max-width: 640px)', {
                                seriesBarDistance: 10,
                                axisX: {
                                    labelInterpolationFnc: function(value) {
                                        return value[0];
                                    }
                                }
                            }]
                        ];

                    var chartActivity = Chartist.Bar('#chartActivity', data, options, responsiveOptions);
                                
                    }
                    
        
                });
            
                

            }

        </script>
    </body>
</html>
