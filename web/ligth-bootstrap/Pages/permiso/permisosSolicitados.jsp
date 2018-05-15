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
        <title>Permisos Solicitados - Salesland Colombia</title>
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
                                    <div class="card-header">
                                        <h4 class="card-title">Permisos solicitados</h4>
                                        <p class="card-category">Este es el listado de los permisos solicitados por tu equipo de trabajo.</p>
                                    </div>
                                    <div class="card-body table-full-width" id="tablaModificada">                                        
                                        <div class="toolbar" id="toolbar">                                            
                                            <!--        Here you can write extra buttons/actions for the toolbar              -->
                                        </div>
                                        <table id="bootstrap-table" data-toolbar="#toolbar" class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th class="text-center">Motivo</th>
                                                    <th class="text-center">Descripción</th>
                                                    <th class="text-center">Desde</th>
                                                    <th class="text-center">Hasta</th>
                                                    <th class="text-center">Empleado</th>
                                                    <th class="text-center">Estado</th>
                                                    <th class="text-center">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody id="listadoPermisos">

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
        <%@include file="../includes/jsInclude.jsp" %>    
        <script>
            
            $(document).ready(function (){         
                $("#empleadoItemNav").addClass("show");
                $("#permisosSolicitados").addClass("active");
                $("#tituloPagina").text("Permisos");                            
                
                listarPermisosSolicitados();
                
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