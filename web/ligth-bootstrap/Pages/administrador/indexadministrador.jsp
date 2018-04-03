<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
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
        <title>Inicio | SaleslandColombia</title>       
    </head>
    <body>
        <div id="dimScreen">
            
        </div>
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
                                            <!-- CODIGO REDUNDANTE, VER USO O BORRAR -->
                                            <div class="col-7">
                                                <div class="numbers">
                                                    <p class="card-category">Ingresos</p>
                                                    <h4 class="card-title" id="numeroIngresos">0</h4>
                                                    <input type="hidden" id="txtIdUsuario" value="<%=objUsuario.getIdUsuario()%>">
                                                    <input type="hidden" id="imgPerfilNavLateral" value="<%=objUsuario.getFoto()%>">
                                                    <%
                                                        String nombreUSuario[] = objUsuario.getNombre().split(" ");
                                                        String apellidoUsuario[] = objUsuario.getApellido().split(" ");
                                                    %>
                                                    <input type="hidden" class="spanName" value="<%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>">
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
                                    <div class="col-md-12">
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
                                                    <i class="fa fa-circle text-info"></i> Ingreso
                                                    <i class="fa fa-circle text-danger"></i> Salida
                                                </div>
                                                <hr>
                                                <div class="stats">
                                                    <i class="fa fa-check"></i>Datos actualizados desde la ultima salida y el ultimo ingreso.
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
                cargarPromedio();
                loadWeeklyData($("#txtIdUsuario").val());
            });
        </script>
        
        <script>
            
            /////// HACER LA CONDICION PARA VER SI EL SOCKET YA ESTA INICIALIZADO Y EL USUARIO YA ESTA EN LA SALA //////////
            
            var websocket = new WebSocket("ws://localhost:8080/SaleslandColombiaApp/ingresoSala");
            
            function on_Open(){
                
                websocket.onopen = function (){
                
                    alert("on open");
                };
                
            }            
            
            //function on_Message(message){
                
                websocket.onmessage = function (message){
                    console.log("llega el mensaje     " + message.data);
                    alert("on message  ------------>   " + message.data);
                    
                    var dt = JSON.parse(message.data);
                    
                    
                    if (dt[0] == "muchos") {
                        
                        var contador = -1;
                        $("#usuariosIngresados").html("");
                      
                        
                        for (var i = 0, max = dt.length; i < max; i++) {
                            
                            console.log(dt[i]);
                           
                            //var nombre = dt[i-4].split("a");
                            
                            if (contador == 5) {
                                alert("si");
                                
                                var nombre = dt[i-4].split(" ");
                                var apellido = dt[i-3].split(" ");
                                var entrada = dt[i-1].split(" ");
                                var horaEntrada = entrada[2].split(":");
                                var salida = dt[i].split(" ");
                                var horaSalida = salida[2].split(":");
                                alert("asdasdassd salida ------>        "+dt[i]);
                                $("#usuariosIngresados").append("<li class='nav-item'>"
                                                                    +"<div class='ctli'>"
                                                                        +"<div class='row'>"
                                                                            +"<div class='col-md-3'>"
                                                                                +"<a href='#'>"
                                                                                    +"<img src='../../assets/img/imagenesDePerfil/"+dt[i-2]+"' alt=''/> "
                                                                                +"</a>"
                                                                            +"</div>"
                                                                            +"<div class='col-md-9'>"
                                                                                +"<p style='padding: 0; margin:0;'>"+nombre[0]+" "+apellido[0]+"</p>"
                                                                                +"<p style='font-size:11px; margin:0; padding: 0;'>"+entrada[0]+" "+horaEntrada[0]+":"+horaEntrada[1]+" - "+salida[0]+" "+horaSalida[0]+":"+horaSalida[1]+"</p>"
                                                                            +"</div>"
                                                                        +"</div>"
                                                                    +"</div>"
                                                               +"</li>"
                                                                );
                              
                                contador = 0;
                            }else{
                                
                                alert("no");
                                contador++;
                            }
                            
                        } 
                        
                        $.each(dt, function (index, value) {
                            
                            
                            // Will stop running after "three"                            
                          });
                        
                        
    
                    } 
                    
                    console.log(dt);

                };
            //}
                        
            window.onload = function (){
                
                //on_Open();
                var fullDate = new Date();
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
                //alert("mierda");
                websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
                //mostrarEmpleados();
            };
            
        </script>
    </body>
</html>
<%        }
    }  
    catch(NullPointerException ex){
        response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
    }
%>
