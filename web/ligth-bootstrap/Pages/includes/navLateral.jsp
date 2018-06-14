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
            <div class="sidebar" data-color="corp" data-image="../../assets/img/defaultImage.jpg">
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
                                    <li class="nav-item" id="editarPerfilUsuario">
                                        <a class="nav-link profile-dropdown" href="../usuario/editarperfilusuario.jsp">
                                            <span class="sidebar-mini">EP</span>
                                            <span class="sidebar-normal">Editar Perfil</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <ul class="nav">
                        <li class="nav-item" id="inicioItem">
                            <a class="nav-link" href="../empleado/indexempleado.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Inicio</p>
                            </a>
                        </li>
                        <li class="nav-item" id="misPermisos">
                            <a class="nav-link" href="../permiso/mispermisos.jsp">
                                <i class="material-icons">date_range</i>
                                <p>Permisos</p>
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
                                    <%if((!objUsuario.getCargo().getTipo().equals("Director")) || (!objUsuario.getCargo().getTipo().equals("Recepcion"))){%>
                                    
                                        <li class="nav-item" id="misPermisos">
                                            <a class="nav-link profile-dropdown" href="../permiso/mispermisos.jsp">
                                                <span class="sidebar-mini">PR</span>
                                                <span class="sidebar-normal">Mis Permisos</span>
                                            </a>
                                        </li>
                                    
                                    <%} %>
                                    
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
                                    <%
                                        if (!objUsuario.getCargo().getTipo().equals("Recepcion")) {%>
                                            
                                        <li class="nav-item" id="permisosSolicitados">
                                            <a class="nav-link" href="../permiso/permisosSolicitados.jsp">
                                                <span class="sidebar-mini">P</span>
                                                <span class="sidebar-normal">Permisos</span>
                                            </a>
                                        </li>
                                        
                                    <%}%>                                    
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
                                    <%
                                        if (objUsuario.getCargo().getTipo().equals("Director") || objUsuario.getCargo().getTipo().equals("Recepcion")) {%>
                                                 
                                        <li class="nav-item" id="reporteSectores">
                                            <a class="nav-link" href="../usuario/../reportes/ReporteSector.jsp">
                                                <span class="sidebar-mini">S</span>
                                                <span class="sidebar-normal">Sectores</span>
                                            </a>
                                        </li>
                                    
                                    <%}if(objUsuario.getCargo().getTipo().equals("Director") || objUsuario.getCargo().getTipo().equals("Recepcion") || objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")){%>
                                    
                                        <li class="nav-item" id="reporteCanales">
                                            <a class="nav-link" href="../usuario/../reportes/ReporteCanal.jsp">
                                                <span class="sidebar-mini">C</span>
                                                <span class="sidebar-normal">Canales</span>
                                            </a>
                                        </li>
                                    
                                    <%}%>
                                    
                                    <li class="nav-item" id="reporteAreas">
                                        <a class="nav-link" href="../usuario/../reportes/ReporteArea.jsp">
                                            <span class="sidebar-mini">A</span>
                                            <span class="sidebar-normal">Areas</span>
                                        </a>
                                    </li>
                                    <li class="nav-item" id="reporteUsuarios">
                                        <a class="nav-link" href="../reportes/ReporteUsuario.jsp">
                                            <span class="sidebar-mini">U</span>
                                            <span class="sidebar-normal">Usuarios</span>
                                        </a>
                                    </li>
                                    
                                    <%
                                        if (objUsuario.getCargo().getTipo().equals("Recepcion")) {%>
                                                
                                            <li class="nav-item">
                                                <a class="nav-link" href="/SaleslandColombiaApp/excelservlet">
                                                    <span class="sidebar-mini">R</span>
                                                    <span class="sidebar-normal">Reporte General <small>(EXCEL)</small></span>
                                                </a>
                                            </li>

                                    <%}%>
                                     
                                </ul>
                            </div>
                        </li>      
                        <li class="nav-item" id="ingresoManual">
                            <a class="nav-link"  href="../ingreso/ingresomanual.jsp">
                                <i class="material-icons">transfer_within_a_station</i>
                                <p>Ingreso Manual</p>
                            </a>                                                        
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
                                            
      <%}%>
      
        <input type="hidden" id="txtIdUsuario" value="<%=objUsuario.getIdUsuario()%>">
        <input type="hidden" id="imgPerfilNavLateral" value="<%=objUsuario.getFoto()%>">                                                   
        <input type="hidden" class="spanName" value="<%= nombreUSuario[0] +" "+ apellidoUsuario[0]%>">

  <%}    
}catch(Exception ex){
    response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp");
}%>        