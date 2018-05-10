package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class permiso extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
                            
                case "registrarPermiso":
                    registrarPermiso(request, response);
                    break;
                case "cargarJefes":
                    
                    break;
            }
            
        }
        
    }

    protected void registrarPermiso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
        try{
            
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            String motivo = request.getParameter("Motivo");
            String descripcion = request.getParameter("Descripcion");
            String inicio = request.getParameter("Inicio");
            String fin = request.getParameter("Fin");
            String archivo = request.getParameter("Archivo");
            
            
            String InicioCompmplete [] = inicio.split(" ");
            String FinCompmplete [] = fin.split(" ");
            
            
            System.out.println("sdsdjsjsjsjsjs al archivo es ---->         "+archivo+ "  FECHA DE INICIO ----->     " + inicio+ "        " + fin);
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            
            
            if (objUsuario.getCargo().equals("Director")) {
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal")) {
                
            }else if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                
            }else if (objUsuario.getCargo().getTipo().equals("")) {
                
            }
            
            
            
            
            //Permiso objPermiso = new Permiso(motivo, descripcion, inicio, InicioCompmplete[1], FechaFin, FinCompmplete[1], archivo, motivo, UsuarioEnvia, UsuarioRecibe)
        
        }catch(Exception ex){
            System.err.println(ex);
            response.getWriter().write("500");
        }
    }
    protected void cargarJefes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {            
        try{
        
            
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();                                    
            
            
            
            
        }catch(Exception ex){
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
