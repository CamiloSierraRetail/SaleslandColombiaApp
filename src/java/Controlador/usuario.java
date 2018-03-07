package Controlador;

import Modelo.Cargo;
import Modelo.Sector;
import Modelo.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            
            //INICIALIZA LA SESION
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario WHERE Documento='"+Documento+"' OR Email='"+Email+"'");
            List<Usuario> listaUsuario = query.list();
            if (listaUsuario.size() > 0) {
                response.getWriter().write("226");
                
            }else{
                Cargo objCargo = new Cargo();
                objCargo.setIdCargo(Integer.parseInt(idCargo));

                Usuario objUsuario = new Usuario(TipoDocumento, Documento, Nombre, Apellido, Direccion, Telefono, Celular, Genero, Email, new Date(), Contrasenia, ImagenPerfil, "Activo", objCargo);

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
            Query query = sesion.createQuery("FROM Usuario WHERE Email='"+Usuario+"' OR Documento='"+Usuario+"' AND Contrasenia='"+Contrasenia+"'");
            List<Usuario> listaUsuario = query.list();
            if (listaUsuario.size() > 0) {
                
                for(Usuario usuario : listaUsuario){
                
                    if (usuario.getCargo().getTipo().equals("Empleado")) {
                        
                        response.getWriter().write("Empleado");
                        
                    }else{
                    
                        response.getWriter().write("Administrador");
                        
                    }
                    
                    Usuario objUsuario = new Usuario(usuario.getTipoDocumento(), usuario.getDocumento(), usuario.getNombre(), usuario.getApellido(), usuario.getDireccion(), usuario.getTelefono(), usuario.getCelular(), usuario.getGenero(), usuario.getEmail(), usuario.getFechaNacimiento(), "", usuario.getFoto(), "Activo", usuario.getCargo());
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
