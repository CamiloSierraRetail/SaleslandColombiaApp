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
                        <div class="row">
                            <div class="col-lg-3 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center icon-warning">
                                                    <i class="nc-icon nc-chart text-warning"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Number</p>
                                                    <h4 class="card-title">150GB</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer ">
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-refresh"></i> Update Now
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
                                                    <i class="nc-icon nc-light-3 text-success"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Revenue</p>
                                                    <h4 class="card-title">$ 1,345</h4>
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
                                                    <i class="nc-icon nc-vector text-danger"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Errors</p>
                                                    <h4 class="card-title">23</h4>
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
                                                    <i class="nc-icon nc-favourite-28 text-primary"></i>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Followers</p>
                                                    <h4 class="card-title">+45K</h4>
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
                                                <p class="card-category">Ingreso y salida del usuario en esta semana</p>
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
    </body>
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        $(document).ready(function (){
            preferencias();
            inicializarChartIngreso();
            
        });
    </script>
    <script>
        function preferencias(){
            
            /*var dataPreferences = {
                series: [
                    [25, 30, 20, 25]
                ]
            };

            var optionsPreferences = {
                donut: true,
                donutWidth: 40,
                startAngle: 0,
                total: 100,
                showLabel: false,
                axisX: {
                    showGrid: false,
                    offset: 0
                },
                height: 245
            };

            Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);

            Chartist.Pie('#chartPreferences', {
                labels: ['62%', '32%', '6%'],
                series: [62, 32, 6]
            });*/
            
            
            var data = {
                series: [62, 32, 6]
            };

            var sum = function(a, b) { return a + b };

            new Chartist.Pie('#chartPreferences', data, {
                labelInterpolationFnc: function(value) {
                  return Math.round(value / data.series.reduce(sum) * 100) + '%';
                }
            });
            
        }
        function inicializarChartIngreso(){
            
            var data = {
                labels: ['Lun', 'Mar', 'Mier', 'Jue', 'Vie'],
                series: [
                    [24, 20, 10, 9, 8],
                    [4, 2, 2, 18, 4]
                ]
            };
            
            var options = {
                
                seriesBarDistance:10,
                axisX: {
                    showGrid: false
                },
                height: 245
                //height: "284px"
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
        
    </script>
</html>
