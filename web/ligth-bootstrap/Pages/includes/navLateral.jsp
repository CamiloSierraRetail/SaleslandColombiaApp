    <%@page import="Modelo.Usuario"%>
    <%
        Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
        
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
        
        try{
        
            if(session.getAttribute("UsuarioIngresado").equals(null)){
                response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp");
            }else{
    
                String nombreUSuario[] = objUsuario.getNombre().split(" ");
                String apellidoUsuario[] = objUsuario.getApellido().split(" ");
                
                if (objUsuario.getCargo().getTipo().equals("Empleado")) {%>
                
                    <div class="sidebar" data-color="corp" data-image="../../assets/img/navLateral.jpg">
                        <!--
                            Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                            Tip 2: you can also add an image using data-image tag
                        -->
                        <div class="sidebar-wrapper">
                            <div class="logo">
                                <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/empleado/indexempleado.jsp" class="simple-text logo-mini">
                                    SL
                                </a>
                                <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/empleado/indexempleado.jsp" class="simple-text logo-normal">
                                    <strong>Sales</strong>land
                                </a>
                            </div>
                            <div class="user">
                                <div class="photo" style="height: 45px;width: 45px;">
                                    <img src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>" />
                                </div>
                                <div class="info ">
                                    <a data-toggle="collapse" href="#collapseExample" class="collapsed">
                                        <span><%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>
                                            <b class="caret"></b>
                                        </span>
                                    </a><br>
                                    <div class="collapse" id="collapseExample">
                                        <ul class="nav">
                                            <li>
                                                <a class="profile-dropdown" href="#pablo">
                                                    <span class="sidebar-mini">MP</span>
                                                    <span class="sidebar-normal">Mi Perfil</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="profile-dropdown" href="../usuario/editarperfilusuario.jsp">
                                                    <span class="sidebar-mini">EP</span>
                                                    <span class="sidebar-normal">Editar Perfil</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="profile-dropdown" href="#pablo">
                                                    <span class="sidebar-mini">A</span>
                                                    <span class="sidebar-normal">Ajustes</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <ul class="nav">
                                <li class="nav-item ">
                                    <a class="nav-link" href="../empleado/indexempleado.jsp">
                                        <i class="material-icons">dashboard</i>
                                        <p>Dashboard (Empleado)</p>
                                    </a>
                                </li>
                                <!-- Div xdxdxdxddxddxdxdxdxdx -->
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="collapse" href="#Examples">
                                        <i class="material-icons">card_travel</i>
                                        <p>
                                            Empresa
                                            <b class="caret"></b>
                                        </p>
                                    </a>
                                    <div class="collapse " id="Examples">
                                        <ul class="nav">
                                            <li class="nav-item ">
                                                <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp">
                                                    <span class="sidebar-mini">S</span>
                                                    <span class="sidebar-normal">Sectores</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp">
                                                    <span class="sidebar-mini">C</span>
                                                    <span class="sidebar-normal">Canales</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="../area/listararea.jsp">
                                                    <span class="sidebar-mini">A</span>
                                                    <span class="sidebar-normal">Areas</span>
                                                </a>
                                            </li>                                
                                        </ul>
                                    </div>
                                </li>


                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="collapse" href="#componentsExamples">
                                        <i class="material-icons">card_travel</i>
                                        <p>
                                            Components
                                            <b class="caret"></b>
                                        </p>
                                    </a>
                                    <div class="collapse " id="componentsExamples">
                                        <ul class="nav">
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/buttons.html">
                                                    <span class="sidebar-mini">B</span>
                                                    <span class="sidebar-normal">Buttons</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/grid.html">
                                                    <span class="sidebar-mini">GS</span>
                                                    <span class="sidebar-normal">Grid System</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/panels.html">
                                                    <span class="sidebar-mini">P</span>
                                                    <span class="sidebar-normal">Panels</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/sweet-alert.html">
                                                    <span class="sidebar-mini">SA</span>
                                                    <span class="sidebar-normal">Sweet Alert</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/notifications.html">
                                                    <span class="sidebar-mini">N</span>
                                                    <span class="sidebar-normal">Notifications</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/icons.html">
                                                    <span class="sidebar-mini">I</span>
                                                    <span class="sidebar-normal">Icons</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/typography.html">
                                                    <span class="sidebar-mini">T</span>
                                                    <span class="sidebar-normal">Typography</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </li>                    
                            </ul>
                        </div>        
                    </div>
                
                <%}else{%>
                
                    <div class="sidebar" data-color="corp" data-image="../../assets/img/navLateral.jpg">
                        <!--
                            Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                            Tip 2: you can also add an image using data-image tag
                        -->
                        <div class="sidebar-wrapper">
                            <div class="logo">
                                <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/administrador/indexadministrador.jsp" class="simple-text logo-mini">
                                    SL
                                </a>
                                <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/administrador/indexadministrador.jsp" class="simple-text logo-normal">
                                    <strong>Sales</strong>land
                                </a>
                            </div>
                            <div class="user">
                                <div class="photo">
                                    <img src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>"/>
                                </div>
                                <div class="info ">
                                    <a data-toggle="collapse" href="#collapseExample" class="collapsed">
                                        <span><%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>
                                            <b class="caret"></b>
                                        </span>
                                    </a>
                                    <div class="collapse" id="collapseExample">
                                        <ul class="nav">
                                            <li>
                                                <a class="profile-dropdown" href="#pablo">
                                                    <span class="sidebar-mini">MP</span>
                                                    <span class="sidebar-normal">Mi Perfil</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="profile-dropdown" href="#pablo">
                                                    <span class="sidebar-mini">EP</span>
                                                    <span class="sidebar-normal">Editar Perfil</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a class="profile-dropdown" href="#pablo">
                                                    <span class="sidebar-mini">A</span>
                                                    <span class="sidebar-normal">Ajustes</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <ul class="nav">
                                <li class="nav-item ">
                                    <a class="nav-link" href="./dashboard.html">
                                        <i class="material-icons">dashboard</i>
                                        <p>Dashboard (Administrador)</p>
                                    </a>
                                </li>
                                <!-- Div xdxdxdxddxddxdxdxdxdx -->
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="collapse" href="#Examples">
                                        <i class="material-icons">card_travel</i>
                                        <p>
                                            Empresa
                                            <b class="caret"></b>
                                        </p>
                                    </a>
                                    <div class="collapse " id="Examples">
                                        <ul class="nav">
                                            <li class="nav-item ">
                                                <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp">
                                                    <span class="sidebar-mini">S</span>
                                                    <span class="sidebar-normal">Sectores</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp">
                                                    <span class="sidebar-mini">C</span>
                                                    <span class="sidebar-normal">Canales</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/grid.html">
                                                    <span class="sidebar-mini">A</span>
                                                    <span class="sidebar-normal">Areas</span>
                                                </a>
                                            </li>                                
                                        </ul>
                                    </div>
                                </li>


                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="collapse" href="#componentsExamples">
                                        <i class="material-icons">card_travel</i>
                                        <p>
                                            Components
                                            <b class="caret"></b>
                                        </p>
                                    </a>
                                    <div class="collapse " id="componentsExamples">
                                        <ul class="nav">
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/buttons.html">
                                                    <span class="sidebar-mini">B</span>
                                                    <span class="sidebar-normal">Buttons</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/grid.html">
                                                    <span class="sidebar-mini">GS</span>
                                                    <span class="sidebar-normal">Grid System</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/panels.html">
                                                    <span class="sidebar-mini">P</span>
                                                    <span class="sidebar-normal">Panels</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/sweet-alert.html">
                                                    <span class="sidebar-mini">SA</span>
                                                    <span class="sidebar-normal">Sweet Alert</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/notifications.html">
                                                    <span class="sidebar-mini">N</span>
                                                    <span class="sidebar-normal">Notifications</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/icons.html">
                                                    <span class="sidebar-mini">I</span>
                                                    <span class="sidebar-normal">Icons</span>
                                                </a>
                                            </li>
                                            <li class="nav-item ">
                                                <a class="nav-link" href="./components/typography.html">
                                                    <span class="sidebar-mini">T</span>
                                                    <span class="sidebar-normal">Typography</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </li>                    
                            </ul>
                        </div>        
                    </div>
                
              <%}%>
      
          <%}    
        }catch(Exception e){
        
            System.err.println(e);
            response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp");
        }%>        