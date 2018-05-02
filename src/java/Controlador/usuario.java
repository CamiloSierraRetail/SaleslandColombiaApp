package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Cargo;
import Modelo.Sector;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;


public class usuario extends HttpServlet {

   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
            
                //Usar y crear cada caso para cada una de las acciones que se vayan a realizar
                case "registrar":
                    registrarUsuario(request, response);
                    break;
                case "cargartablaregistro":
                    cargarTablaRegistro(request, response);
                    break;
                case "IniciarSesion":
                    iniciarSesion(request, response);
                    break;
                case "CerrarSesion":
                    cerrarSesion(request, response);
                    break;
                case "listarUsuarios":
                    listarUSuarios(request, response);
                    break;
                case "verificarEmail":
                    verificarEmail(request, response);
                    break;
                case "actualizarPerfil":
                    actualizarPerfil(request, response);
                    break;
                case "verusuariostabla":
                    listarUsuariosTablas(request, response);
                    break;
            }
            
        }
        
    }
    protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            HibernateUtil.inicializarSesion();
            String TipoDocumento = request.getParameter("TipoDocumentoUsuario");//Check
            String Documento = request.getParameter("DocumentoUsuario");//Check
            String Nombre = request.getParameter("NombreUsuario");//Check
            String Apellido = request.getParameter("ApellidoUsuario");//Check
            String Email = request.getParameter("EmailUsuario");//Check
            String Contrasenia = request.getParameter("ContraseniaUsuario");//Check
            String Direccion = request.getParameter("DireccionUsuario");//Check
            String Genero = request.getParameter("GeneroUsuario");//Check
            String Celular = request.getParameter("CelularUsuario");//Check
            String Telefono = request.getParameter("TelefonoUsuario");//Check
            String FechaNacimiento = request.getParameter("FechaNacimientoUsuario");//Check* convertir a date y agregar al objeto
            String ImagenPerfil = request.getParameter("ImagenPerfilUsuario");//Check
            String idCargo = request.getParameter("CargoUsuario");
            String Horario = request.getParameter("Horario");
            
            System.out.println("-------------> !!!!!!!!!!!!!!!!!!!! -->   " +ImagenPerfil);
            String imagenPerfil = "";
            if (ImagenPerfil.equals("")) {
                
                if (Genero.equals("Masculino")) {
                    imagenPerfil = "hombreDefaultImageProfile.png";
                }else if (Genero.equals("Femenino")) {
                    imagenPerfil = "mujerDefaultImageProfile.png";
                }else if (Genero.equals("Otro")){
                    imagenPerfil = "otroDefaultImageProfile.png";
                
                }
            }else{
            
                imagenPerfil = ImagenPerfil;
            
            }
            
            
            String Fecha[] = FechaNacimiento.split("/");
            String newFecha = Fecha[1]+"-"+Fecha[0]+"-"+Fecha[2];
            //////// CONVIERTE LA FECHA DEL FORMULARIO A DATE ////////////////////////
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            date = df.parse(newFecha);
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

            //INICIALIZA LA SESION
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario WHERE Documento='"+Documento+"' OR Email='"+Email+"' OR Celular='"+Celular+"'");
            List<Usuario> listaUsuario = query.list();
            if (listaUsuario.size() > 0) {
                response.getWriter().write("226");
                
            }else{
                Cargo objCargo = new Cargo();
                objCargo.setIdCargo(Integer.parseInt(idCargo));

                Usuario objUsuario = new Usuario(TipoDocumento, Documento, Nombre, Apellido, Direccion, Telefono, Celular, Genero, Email, date, Contrasenia, imagenPerfil, "Activo", Horario, objCargo);

                sesion.beginTransaction();
                sesion.save(objUsuario);
                sesion.getTransaction().commit();                
                response.getWriter().write("200");
                
            }
            sesion.close();
            HibernateUtil.closeSessionFactory();
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
    protected void cargarTablaRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            HibernateUtil.inicializarSesion();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query =  sesion.createQuery("FROM Sector WHERE Estado='Activo'");
            List<Sector> listaSector = query.list();
            
            for(Sector sector : listaSector){
                            
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Sector="+sector.getIdSector()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_Cargo : listaSector_Cargo){
                    
                    response.getWriter().write(buscarCargo(sector_Cargo.getCargo().getIdCargo(), sector_Cargo.getSector().getNombreSector()));
                
                }
                
                Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Sector="+sector.getIdSector()+"");
                List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                for(Canal_Cargo canal_Cargo : listaCanal_Cargo){
                    
                    response.getWriter().write(buscarCargo(canal_Cargo.getCargo().getIdCargo(), canal_Cargo.getCanal().getSector().getNombreSector()));
                
                }
                                
                Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Sector="+sector.getIdSector()+"");
                List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                for(Area_Cargo area_Cargo : listaArea_Cargo){
                    
                    response.getWriter().write(buscarCargo(area_Cargo.getCargo().getIdCargo(), area_Cargo.getArea().getCanal().getSector().getNombreSector()));
                
                }
                
            }
            
            sesion.close();
            HibernateUtil.closeSessionFactory();
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    }
    private String buscarCargo(int idCargo, String sector){
        
        String resultadoBusqueda = "", tipo = "";
        
        try{            
            HibernateUtil.inicializarSesion();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            
            Query queryCargo = sesion.createQuery("FROM Cargo WHERE idCargo="+idCargo+" AND Estado='Activo' ");
            List<Cargo>listaCargo = queryCargo.list();
            for(Cargo cargo : listaCargo){

                if (cargo.getTipo().equals("Empleado")) {

                    tipo="Usuario";
                }else{

                    tipo="Administrador";
                }
                resultadoBusqueda = "<tr>"
                                          + "<td>"
                                                +"<div class='form-check form-check-radio'>"
                                                    + "<label class='form-check-label'>"
                                                        + "<input class='form-check-input' type='radio' name='CargoUsuario' id='rbtnCargoUsuario' value='"+cargo.getIdCargo()+"'>"
                                                        + "<span class='form-check-sign'></span>"                                                            
                                                    + "</label>"
                                                + "</div>"
                                          + "</td>"
                                          + "<td>"+cargo.getNombreCargo()+"</td>"
                                          + "<td>"+cargo.getDescripcion()+"</td>"
                                          + "<td>"+sector+"</td>"  
                                          + "<td>"+tipo+"</td>"
                                     + "</tr>";

            }
            HibernateUtil.closeSessionFactory();
            sesion.close();
        }catch(Exception ex){
        
            System.err.println(ex);
        
        }
    
        return resultadoBusqueda;
    }
    
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HibernateUtil.inicializarSesion();
        
        try{
            String Usuario = request.getParameter("Usuario");
            String Contrasenia = request.getParameter("Contrasenia");            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario WHERE ((Email='"+Usuario+"' OR Documento='"+Usuario+"') AND Contrasenia='"+Contrasenia+"')");
            List<Usuario> listaUsuario = query.list();
            if (listaUsuario.size() == 1) {
                
                for(Usuario usuario : listaUsuario){
                
                    if (usuario.getEstado().equals("Activo")) {
                        if (usuario.getCargo().getTipo().equals("Empleado")) {
                        
                        response.getWriter().write("Empleado");
                        
                        }else{

                            response.getWriter().write("Administrador");

                        }

                        Usuario objUsuario = new Usuario(usuario.getTipoDocumento(), usuario.getDocumento(), usuario.getNombre(), usuario.getApellido(), usuario.getDireccion(), usuario.getTelefono(), usuario.getCelular(), usuario.getGenero(), usuario.getEmail(), usuario.getFechaNacimiento(), "", usuario.getFoto(), "Activo", usuario.getHorario() , usuario.getCargo());
                        objUsuario.setIdUsuario(usuario.getIdUsuario());
                        request.getSession().setAttribute("UsuarioIngresado", objUsuario);
                        
                    }else if (usuario.getEstado().equals("Inactivo")){
                    
                        response.getWriter().write("403");
                        
                    }
                                        
                }
                
            }else{            

                response.getWriter().write("404");
            }
         
            sesion.close();
        }catch(Exception e){
            System.err.println(e);
             response.getWriter().write("500");
         }
        HibernateUtil.closeSessionFactory();
    }
    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
        
            HttpSession logout = request.getSession();            
            logout.removeAttribute("UsuarioIngresado");
            logout.invalidate();
            response.getWriter().write("200");
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
    protected void listarUSuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            HibernateUtil.inicializarSesion();
            int countRows = 1;
            List<Usuario> listaUsuario = null;            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    listaUsuario = buscarUsuario(sector_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                    
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector="+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                        
                            listaUsuario.addAll(buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal="+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){
                        
                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area = "+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){
                            
                                System.out.println("***************************************   area arae arae    ------>    " + listaArea_Cargo.size() + "          -------------------------   " + area_cargo.getCargo().getNombreCargo() + " /////////////////////    " + canal.getNombreCanal());
                                
                                listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                            }
                        
                        }
                    
                    }
                    
                }
                
                for(Usuario usuario : listaUsuario){
            
                    response.getWriter().write("<tr>"
                                                  + "<td class='text-center'>"+countRows+"</td>"
                                                  + "<td>"+usuario.getDocumento()+"</td>"
                                                  + "<td>"+usuario.getNombre()+" "+usuario.getApellido()+"</td>"
                                                  + "<td>"+usuario.getCelular()+"</td>"
                                                  + "<td>"+usuario.getEmail()+"</td>"
                                                  + "<td class='text-right'>"+usuario.getEstado()

                                                  + "</td>"
                                                  + "<td class='td-actions text-right'>"
                                                    + "<a href='#' onclick='VerUsuariosTabla("+usuario.getIdUsuario()+")' data-toggle='modal' data-target='#modalVerUsuario' rel='tooltip' title='' class='btn btn-success btn-link btn-xs' data-original-title='Ver Usuario'>"
                                                        + "<i class='fa fa-eye'></i>"
                                                    + "</a>"   
                                                    + "<a href='/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/editarsector.jsp?_' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                        + "<i class='fa fa-edit'></i>"
                                                    + "</a>"
                                                    + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                                        + "<i class='fa fa-times'></i>"
                                                    + "</a>"
                                                  + "</td>"
                                             + "</tr>");
                    countRows++;

                }
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
                Query queryCanalCargo1 = sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objUsuario.getCargo().getIdCargo()+"");
                List<Canal_Cargo> listaCanal_Cargo1 = queryCanalCargo1.list();
                for(Canal_Cargo canal_cargo1 : listaCanal_Cargo1){
                
                    Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                    
                    for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                        
                        if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getTipo().equals("JefeCanal")) {
                            
                            ///////////////////////////// ELIMINAR ESTE IF, OPTIMIZAR CODE /////////////////////////////
                            System.out.println("jejefesote xd alv");
                            
                        }else{
                        
                            List<Usuario> listaUsuariosBuscados = buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                        
                            if (listaUsuario == null) {

                                listaUsuario = listaUsuariosBuscados;

                            }else{
                                listaUsuario.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    System.out.println("AREA AREA AREa aREA AREA arEAR arEA ARAEARARAERAEARAEARAEAREARAEARAE          " + listaArea.size());
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));

                        }

                    }
                    
                }
                
                for(Usuario usuario : listaUsuario){
            
                    response.getWriter().write("<tr>"
                                                  + "<td class='text-center'>"+countRows+"</td>"
                                                  + "<td>"+usuario.getDocumento()+"</td>"
                                                  + "<td>"+usuario.getNombre()+" "+usuario.getApellido()+"</td>"
                                                  + "<td>"+usuario.getCelular()+"</td>"
                                                  + "<td>"+usuario.getEmail()+"</td>"
                                                  + "<td class='text-right'>"+usuario.getEstado()

                                                  + "</td>"
                                                  + "<td class='td-actions text-right'>"
                                                    + "<a href='#' onclick='VerUsuariosTabla("+usuario.getIdUsuario()+")' data-toggle='modal' data-target='#modalVerUsuario' rel='tooltip' title='' class='btn btn-success btn-link btn-xs' data-original-title='Ver Usuario'>"
                                                        + "<i class='fa fa-eye'></i>"
                                                    + "</a>"   
                                                    + "<a href='/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/editarsector.jsp?_' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                        + "<i class='fa fa-edit'></i>"
                                                    + "</a>"
                                                    + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                                        + "<i class='fa fa-times'></i>"
                                                    + "</a>"
                                                  + "</td>"
                                             + "</tr>");
                    countRows++;

                }
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
            
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                        
                        
                        List<Usuario> listaUsuariosBuscados = buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                            
                        if (listaUsuario == null) {

                            listaUsuario = listaUsuariosBuscados;

                        }else{
                            listaUsuario.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }
                
                for(Usuario usuario : listaUsuario){
            
                    response.getWriter().write("<tr>"
                                                  + "<td class='text-center'>"+countRows+"</td>"
                                                  + "<td>"+usuario.getDocumento()+"</td>"
                                                  + "<td>"+usuario.getNombre()+" "+usuario.getApellido()+"</td>"
                                                  + "<td>"+usuario.getCelular()+"</td>"
                                                  + "<td>"+usuario.getEmail()+"</td>"
                                                  + "<td class='text-right'>"+usuario.getEstado()

                                                  + "</td>"
                                                  + "<td class='td-actions text-right'>"
                                                    + "<a href='#' onclick='VerUsuariosTabla("+usuario.getIdUsuario()+")' data-toggle='modal' data-target='#modalVerUsuario' rel='tooltip' title='' class='btn btn-success btn-link btn-xs' data-original-title='Ver Usuario'>"
                                                        + "<i class='fa fa-eye'></i>"
                                                    + "</a>"   
                                                    + "<a href='/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/editarsector.jsp?_' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                        + "<i class='fa fa-edit'></i>"
                                                    + "</a>"
                                                    + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                                        + "<i class='fa fa-times'></i>"
                                                    + "</a>"
                                                  + "</td>"
                                             + "</tr>");
                    countRows++;

                }
                
            }
            
            sesion.close();
            HibernateUtil.closeSessionFactory();
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
    //////// METODO PARA BUSCAR A LOS USUARIOS QUE SE VAN A LISTAR /////////
    private List<Usuario> buscarUsuario(int cargo, int usuarioEnSesion){
    
        List<Usuario> listaUsuarios = null;
        try{
            HibernateUtil.inicializarSesion();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query queryUsuario = sesion.createQuery("FROM Usuario WHERE Cargo="+cargo+" AND idUsuario != "+usuarioEnSesion+"");            
            listaUsuarios = queryUsuario.list();
            sesion.close();
            HibernateUtil.closeSessionFactory();
        }catch(HibernateException ex){
        
            System.err.println(ex);
        }
        return listaUsuarios;
    }
    
    protected void verificarEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HibernateUtil.inicializarSesion();
            String email = request.getParameter("email");
            String idusuario = request.getParameter("idusuario");
            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q  = s.createQuery("FROM Usuario WHERE Email ='"+email+"'");
            Query q2 = s.createQuery("FROM Usuario WHERE IdUsuario = '"+idusuario+"' AND Email = '"+email+"'");
            if(q.list().size() > 0 && q2.list().isEmpty()){
                response.getWriter().write("0");
            }else{
                response.getWriter().write("1");
            }
            
            s.close();
            HibernateUtil.closeSessionFactory();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    protected void actualizarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HibernateUtil.inicializarSesion();
            String idusuario = request.getParameter("idUsuario");
            String nombre = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            String genero = request.getParameter("genero");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String foto = request.getParameter("foto");
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Query q = s.createSQLQuery("UPDATE usuario SET Nombre = '"+nombre+"', Apellido = '"+apellidos+"', Direccion = '"+direccion+"', Telefono = '"+telefono+"', Email = '"+email+"', Celular = '"+celular+"', Genero = '"+genero+"', FechaNacimiento = '"+fechaNacimiento+"', Foto = '"+foto+"' WHERE idUsuario = '"+idusuario+"'");
            q.executeUpdate();
            s.getTransaction().commit();
            //Actualizar Sesion
            Usuario objUsuario = (Usuario) s.createQuery("FROM Usuario WHERE IdUsuario = '"+idusuario+"'").uniqueResult();
            
            request.getSession().setAttribute("UsuarioIngresado", objUsuario);
            response.getWriter().write("1");
            s.close();
            HibernateUtil.closeSessionFactory();
        }catch(Exception ex){
            System.out.println(ex);
            response.getWriter().write("0");
        }
    }
    
    protected void listarUsuariosTablas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            HibernateUtil.inicializarSesion();
            String rolUsuario = "";
            String idUsuario = request.getParameter("IdUsuario");
        
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Usuario objUsuario = (Usuario) sesion.createQuery("FROM Usuario WHERE IdUsuario="+idUsuario+"").uniqueResult();
            
            String nombre[] = objUsuario.getNombre().split(" ");
            String apellido[] = objUsuario.getApellido().split(" ");
            
            if(objUsuario.getCargo().getTipo().equals("Empleado")){
                rolUsuario = "(Empleado)";
            }else{
                rolUsuario = "(Administrador)";
            }
            
            JSONArray usuarioJson = new JSONArray();
            usuarioJson.add(objUsuario.getIdUsuario());
            usuarioJson.add(objUsuario.getFoto());
            usuarioJson.add(objUsuario.getDocumento());
            usuarioJson.add(nombre[0] + " " + apellido[0]);
            usuarioJson.add(rolUsuario);
            usuarioJson.add(objUsuario.getCargo().getNombreCargo() + ": " + objUsuario.getCargo().getDescripcion());
            
            response.getWriter().write(usuarioJson.toJSONString());
        
            sesion.close();
            HibernateUtil.closeSessionFactory();
        }catch(HibernateException ex){
        
            System.err.println(ex);
            response.getWriter().write("500");
        
        }
        
    
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
