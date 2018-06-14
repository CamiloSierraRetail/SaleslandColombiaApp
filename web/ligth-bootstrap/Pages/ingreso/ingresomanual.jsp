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
        <title>Ingreso Manual - Salesland Colombia</title>
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
                            <div class="col-md-10 mr-auto ml-auto">
                                <div class="card">                                                
                                    <form class="form-horizontal" novalidate="novalidate" id="frmIngresoManual" name="frmIngresoManual">
                                        <div class="card-header">
                                            <h4 class="card-title">INGRESO MANUAL</h4>
                                            <p class="card-category">Digita los datos del ingreso</p>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-body col-md-10 mr-auto ml-auto">
                                                
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">DOCUMENTO *</label>
                                                            <input id="txtDocumentoIngreso" name="DocumentoIngreso" class="form-control">                                                   
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <label class="control-label">HORA *</label>
                                                            <!--<input type="text" pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}">-->
                                                            <!--<input type="time" id="lol" class="timepicker">-->
                                                            
                                                            <input id="datetimepicker" name="HoraIngreso" type="text" class="form-control timepicker">
                                                            
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </div>
                                        <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">                                            
                                            <button type="submit" class="btn btn-blue_corp btn-fill pull-right">Confirmar</button>
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
        <%@include file="../includes/jsInclude.jsp" %>        
        <script>
            
            $(document).ready(function (){
                localStorage.imgPerfil = $("#imgPerfilNavLateral").val();
                localStorage.name = $(".spanName").val();
                $("#ingresoManual").addClass("active");
                $("#tituloPagina").text("Ingreso manual");              
              
                loadWeeklyData($("#txtIdUsuario").val());                
                
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