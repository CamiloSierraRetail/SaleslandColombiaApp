<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>Ver Perfil | SaleslandColombia</title>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="../includes/navLateral.jsp" %>
            <div class="main-panel">
                <!-- Navbar -->
                <%@include file="../includes/navSuperior.jsp" %>
                <!-- End Navbar -->
                <div class="content">
                    <div class="container-fluid">
                        <div class="section-image" data-image="../../assets/img/bg5.jpg">
                            <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
                            <div class="container">
                                
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="../includes/footer.jsp" %>
            </div>
        </div>
            <%@include file="../includes/jsInclude.jsp" %>
        <script>
            $(document).ready(function(){    
                $("#tituloPagina").text("Ver Perfil"); 
            });
        </script>
    </body>
</html>
