<%@page import="Modelo.Usuario"%>
<%  
    
    Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

try{

    if(objUsuario.getIdUsuario() == 0){
        response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/sesionBloqueada.jsp");
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
                        <div class="photo">
                            <img src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>" alt=""/>
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
                                <p>Inicio (Empleado)</p>
                            </a>
                        </li>                                
                        <!-- Div xdxdxdxddxddxdxdxdxdx -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#Examples">
                                <i class="material-icons">date_range</i>
                                <p>
                                    Permisos
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
                                    <li class="nav-item " id="canalesItemNav">
                                        <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp">
                                            <span class="sidebar-mini">C</span>
                                            <span class="sidebar-normal">Canales</span>
                                        </a>
                                    </li>
                                    <li class="nav-item " id="areasItemNav">
                                        <a class="nav-link" href="../area/listararea.jsp">
                                            <span class="sidebar-mini">A</span>
                                            <span class="sidebar-normal">Areas</span>
                                        </a>
                                    </li>                                
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="../empleado/indexempleado.jsp">
                                <i class="material-icons">picture_in_picture</i>
                                <p>Publicaciones</p>
                            </a>
                        </li>                                                  
                    </ul>
                </div>        
            </div>

        <%}else{%>
        <!-- ///////////////////////////////////////////////// ADMINISTRADOR //////////////////////////////////////////////// -->

            <div class="sidebar" data-color="corp" data-image="../../assets/img/defaultImage.jpg">
                <!--
                    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

                    Tip 2: you can also add an image using data-image tag
                -->
                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/administrador/indexadministrador.jsp" class="simple-text logo-mini" style="margin-right: -3px;">
                            SL
                        </a>
                        <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/administrador/indexadministrador.jsp" class="simple-text logo-normal">
                            <img style="height: 30px; white-space: 100px;" src="../../assets/img/LOGO_SALESLAND_NEGATIVO.png">
                        </a>
                    </div>
                    <div class="user">
                        <div class="photo">
                            <img src="../../assets/img/imagenesDePerfil/<%=objUsuario.getFoto()%>"/>
                        </div>
                        <div class="info">
                            <a data-toggle="collapse" href="#opcionesUsuarioNav" class="collapsed">
                                <span><%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>
                                    <b class="caret"></b>
                                </span>
                            </a>
                            <div class="collapse" id="opcionesUsuarioNav">
                                <ul class="nav">
                                    <li class="nav-item" id="editarPerfilUsuario">
                                        <a class="nav-link profile-dropdown" href="../usuario/editarperfilusuario.jsp">
                                            <span class="sidebar-mini">EP</span>
                                            <span class="sidebar-normal">Editar Perfil</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="">
                                        <a class="nav-link profile-dropdown" href="#">
                                            <span class="sidebar-mini">AJ</span>
                                            <span class="sidebar-normal">Ajustes</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <ul class="nav">
                        <li class="nav-item " id="home">
                            <a class="nav-link" href="../administrador/indexadministrador.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Inicio (Administrador)</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#empresaItemNav">
                                <i class="material-icons">location_city</i>
                                <p>
                                    Empresa
                                    <b class="caret"></b>
                                </p>
                            </a>
                            <div class="collapse " id="empresaItemNav">
                                <ul class="nav">
                                    <li class="nav-item" id="sectoresItemNav">
                                        <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp">
                                            <span class="sidebar-mini">S</span>
                                            <span class="sidebar-normal">Sectores</span>
                                        </a>
                                    </li>
                                    <li class="nav-item " id="canalesItemNav">
                                        <a class="nav-link" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp">
                                            <span class="sidebar-mini">C</span>
                                            <span class="sidebar-normal">Canales</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="areasItemNav">
                                        <a class="nav-link" href="../area/listararea.jsp">
                                            <span class="sidebar-mini">A</span>
                                            <span class="sidebar-normal">Areas</span>
                                        </a>
                                    </li>                                
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#empleadoItemNav">
                                <i class="material-icons">supervisor_account</i>
                                <p>
                                    Empleados
                                    <b class="caret"></b>
                                </p>
                            </a>
                            <div class="collapse " id="empleadoItemNav">
                                <ul class="nav">
                                    <li class="nav-item" id="listadoEmpleadosNav">
                                        <a class="nav-link" href="../usuario/listadousuarios.jsp">
                                            <span class="sidebar-mini">L</span>
                                            <span class="sidebar-normal">Listado</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="registrarEmpleadosNav">
                                        <a class="nav-link" href="../usuario/registrarusuario.jsp">
                                            <span class="sidebar-mini">R</span>
                                            <span class="sidebar-normal">Registrar</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="monitorearEmpleadosNav">
                                        <a class="nav-link" href="../empleado/monitorearempleados.jsp">
                                            <span class="sidebar-mini">M</span>
                                            <span class="sidebar-normal">Monitorear</span>
                                        </a>
                                    </li>                                
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ExamplesReportes">
                                <i class="material-icons">insert_chart</i>
                                <p>
                                    Reportes
                                    <b class="caret"></b>
                                </p>
                            </a>
                            <div class="collapse " id="ExamplesReportes">
                                <ul class="nav">
                                    <li class="nav-item ">
                                        <a class="nav-link" href="../cargo/listarcargo.jsp">
                                            <span class="sidebar-mini">U</span>
                                            <span class="sidebar-normal">Usuarios</span>
                                        </a>
                                    </li>
                                    <li class="nav-item ">
                                        <a class="nav-link" href="../usuario/registrarcargo.jsp">
                                            <span class="sidebar-mini">S</span>
                                            <span class="sidebar-normal">Sectores</span>
                                        </a>
                                    </li>                                                                     
                                </ul>
                            </div>
                        </li>                        
                        <li class="nav-item" id="cargosEmpleadosNav">
                            <a class="nav-link"  href="../cargo/listarcargo.jsp">
                                <i class="material-icons">work</i>
                                <p>Cargos</p>
                            </a>                                                        
                        </li>
                    </ul>
                </div>        
            </div>
                                    
            <input type="hidden" id="txtIdUsuario" value="<%=objUsuario.getIdUsuario()%>">
            <input type="hidden" id="imgPerfilNavLateral" value="<%=objUsuario.getFoto()%>">                                                   
            <input type="hidden" class="spanName" value="<%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>">
                                            
      <%}%>

  <%}    
}catch(Exception ex){
    response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp");
}%>        