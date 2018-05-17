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
        <title>Reporte Usuario - Salesland Colombia</title>
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
                                    <form class="form-horizontal" action="PdfUsuario.jsp" target="_blank" method="POST" novalidate="novalidate" id="FrmReporteUsuarios" name="FrmReporteUsuarios">
                                        <div class="card-header">
                                            <h4 class="card-title">Reporte Usuarios</h4>
                                            <p class="card-category">Tenga en cuenta diligenciar correctamente los datos , para generar el reporte correctamente</p>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-body col-md-10 mr-auto ml-auto">
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Documento*</label>                                                        
                                                          <input type="text" id="cmbUsuarios" name="txtparametro" class="form-control" placeholder="Dijite el Documento" >                                           
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">   
                                                        <label class="control-label">Desde*</label>
                                                        <input type="text" data-date-format="YYYY/MM/DD" id="datetimepicker" name ="txtfechainicial" class="form-control datepicker" placeholder="Selecciona la fecha"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="row">
                                                        <label class="control-label">Hasta*</label>
                                                        <input type="text" id="txfechafinal" data-date-format="YYYY/MM/DD" name ="txtfechafinal" class="form-control datepicker" placeholder="Selecciona la fecha"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer text-right card-body col-md-10 mr-auto ml-auto">                                            
                                            <input type="submit" class="btn btn-blue_corp btn-fill pull-right" id="btn-submit" value="Generar PDF" />
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
                                                  
                $("#ExamplesReportes").addClass("show");
                $("#reporteUsuarios").addClass('active');
                $("#tituloPagina").text("REPORTES");

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