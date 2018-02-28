<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/cssInclude.jsp" %>
        <title>SaleslandColombia</title>
        
    </head>
    <body>
        <div class="wrapper">
            <div class="sidebar" data-color="green" data-image="../assets/img/sidebar-5.jpg">
                <!--
                    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                    Tip 2: you can also add an image using data-image tag
                -->
                <!-- Include Nav Lateral  -->
                <%@include file="../includes/navLateral.jsp" %>
            </div>
            <div class="main-panel">
                <!-- Include Nav Superior -->
                <%@include file="../includes/navSuperior.jsp" %>
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            
                            
                            
                                                                                
                        </div>
                    </div>
                </div>
                <!-- INCLUDE FOOTER -->
                <%@include  file="../includes/footer.jsp" %>
            </div>
        </div>
    </body>
    <%@include file="../includes/jsInclude.jsp" %>
</html>
