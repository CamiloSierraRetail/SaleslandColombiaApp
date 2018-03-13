package Controlador;

import Modelo.Cargo;
import Modelo.Ingreso;
import Modelo.Sector;
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
import org.hibernate.Query;
import org.hibernate.Session;


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
                
            }
            
        }
        
    }
    protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
        
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
                    
                    imagenPerfil = "hombreDefaultImagePorfile.png";
                }else if (Genero.equals("Femenino")) {
                    imagenPerfil = "mujerDefaultImagePorfile.png";
                }else if (Genero.equals("Otro")){
                    imagenPerfil = "otroDefaultImagePorfile.png";
                
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
                sesion.close();
                response.getWriter().write("200");
                
            }
            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
    protected void cargarTablaRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            String tipo = "";
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query =  sesion.createQuery("FROM Sector WHERE Estado='Activo'");
            List<Sector> listaSector = query.list();
            
            for(Sector sector : listaSector){
            
                Query queryCargo = sesion.createQuery("FROM Cargo WHERE Sector='"+sector.getIdSector()+"' AND Estado='Activo' ");
                List<Cargo>listaCargo = queryCargo.list();
                for(Cargo cargo : listaCargo){
                    
                    if (cargo.getTipo().equals("Empleado")) {
                        
                        tipo="Usuario";
                    }else{
                        
                        tipo="Administrador";
                    }
                    response.getWriter().write("<tr>"
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
                                              + "<td>"+sector.getNombreSector()+"</td>"  
                                              + "<td>"+tipo+"</td>"
                                         + "</tr>");
                
                }
                
            }
            
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    }
    protected void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
        
            String Usuario = request.getParameter("Usuario");
            String Contrasenia = request.getParameter("Contrasenia");
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario WHERE Email='"+Usuario+"' OR Documento='"+Usuario+"' AND Contrasenia='"+Contrasenia+"' AND Estado='Activo'");
            List<Usuario> listaUsuario = query.list();
            if (listaUsuario.size() > 0) {
                
                for(Usuario usuario : listaUsuario){
                
                    if (usuario.getCargo().getTipo().equals("Empleado")) {
                        
                        response.getWriter().write("Empleado");
                        
                    }else{
                    
                        response.getWriter().write("Administrador");
                        
                    }
                    
                    Usuario objUsuario = new Usuario(usuario.getTipoDocumento(), usuario.getDocumento(), usuario.getNombre(), usuario.getApellido(), usuario.getDireccion(), usuario.getTelefono(), usuario.getCelular(), usuario.getGenero(), usuario.getEmail(), usuario.getFechaNacimiento(), "", usuario.getFoto(), "Activo", usuario.getHorario() , usuario.getCargo());
                    request.getSession().setAttribute("UsuarioIngresado", objUsuario);                    
                }
                
            }else{            
                response.getWriter().write("404");
            }
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    
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
            int countRows = 1;
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario");
            List<Usuario>listaUsuario = query.list();
            for(Usuario usuario : listaUsuario){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+usuario.getDocumento()+"</td>"
                                              + "<td>"+usuario.getNombre()+" "+usuario.getApellido()+"</td>"
                                              + "<td>"+usuario.getCelular()+"</td>"
                                              + "<td>"+usuario.getEmail()+"</td>"
                                              + "<td class='text-right'>"+usuario.getEstado()
                                                      
                                                   /*+ "<div class='row'>"
                                                      + "<div class='col-md-12'>"
                                                        + "<input type='checkbox' checked='' data-toggle='switch' data-on-color='info' data-off-color='info'>"
                                                        + "<span class='toggle'></span>"
                                                      + "</div>"
                                                   + "</div>"*/
                                              + "</td>"
                                              + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-info btn-link btn-xs' data-original-title='Ver Sector'>"
                                                    + "<i class='fa fa-user'></i>"
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
            
        }catch(Exception e){
        
            System.err.println(e);
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
