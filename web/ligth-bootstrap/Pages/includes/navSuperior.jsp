<!-- Navbar -->
    <nav class="navbar navbar-expand-lg ">
        <div class="container-fluid">
            <div class="navbar-wrapper">
                <div class="navbar-minimize">
                    <button id="minimizeSidebar" class="btn btn-warning btn-fill btn-round btn-icon d-none d-lg-block">
                        <i class="fa fa-ellipsis-v visible-on-sidebar-regular"></i>
                        <i class="fa fa-navicon visible-on-sidebar-mini"></i>
                    </button>
                </div>
                <p class="navbar-brand" id="tituloPagina"></p>
            </div>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar burger-lines"></span>
                <span class="navbar-toggler-bar burger-lines"></span>
                <span class="navbar-toggler-bar burger-lines"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link btnSideUsers" rel="tooltip" data-original-title="Ingreso de Personal" href="#" aria-haspopup="true" aria-expanded="false">
                            <i class="material-icons">perm_identity</i>
                            <a href="#"class="d-lg-none btnSideUsers">Empleados</a>
                        </a>
                    </li>
                    <!-- CODIGO PARA LAS NOTIFICACIONES, NO BORRAR
                    <li class="dropdown nav-item">
                        <a href="#" rel="tooltip" data-original-title="Notificaciones" class="dropdown-toggle nav-link" data-toggle="dropdown">
                            <i class="material-icons">notifications_none</i>
                            <span class="notification">5</span>
                            <span class="d-lg-none">Notificaciones</span>
                        </a>
                        <ul class="dropdown-menu">
                            <a class="dropdown-item" href="#">Notification 1</a>
                            <a class="dropdown-item" href="#">Notification 2</a>
                            <a class="dropdown-item" href="#">Notification 3</a>
                            <a class="dropdown-item" href="#">Notification 4</a>
                            <a class="dropdown-item" href="#">Notification 5</a>
                        </ul>
                    </li>-->
                    <li class="nav-item">
                        <a class="nav-link cerrarSesion" rel="tooltip" data-original-title="Cerrar Sesión" href="#" aria-haspopup="true" aria-expanded="false">
                            <span class="d-lg-none">Cerrar Sesión</span>
                            <i class="material-icons">power_settings_new</i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->