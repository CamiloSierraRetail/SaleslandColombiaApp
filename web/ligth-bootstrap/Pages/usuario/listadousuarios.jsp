<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>        
        <title>Listado de usuarios - SaleslandColombia</title>       
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
                                
                                <div class="card bootstrap-table">
                                    <div class="card-body table-full-width">
                                        <div class="toolbar">
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <table id="bootstrap-table" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th>Documento</th>
                                                    <th>Nombre</th>
                                                    <th>Celular</th>
                                                    <th>Email</th>
                                                    <th class="text-right">Estado</th>
                                                    <th class="text-right">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoUsuario">

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
    </body>        
    <%@include file="../includes/jsInclude.jsp" %>
    <script>
        
        $(document).ready(function (){
            
            $("#empleadoItemNav").addClass("show");
            $("#listadoEmpleadosNav").addClass('active');
            $("#tituloPagina").text("EMPLEADOS");
            listarUsuarios(); 
            
            localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
            localStorage.name = $(".spanName").val();
            var fullDate = new Date();
            var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
            var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
            websocket.send("CargarUsuarios-"+<%=objUsuario.getIdUsuario()%>+"-"+currentDate);
            
        });
                
    </script>        
</html>
