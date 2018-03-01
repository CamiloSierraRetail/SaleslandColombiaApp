package Controlador;

import Modelo.Sector;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;


public class sector extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
            
                //Usar y crear cada caso para cada una de las acciones que se vayan a realizar
                case "registrar":
                    
                    registrarsector(request, response);
                    
                    break;
                case "versectores":
                    versectores(request, response);
                    break;
                
            }
            
        }
        
    }
    
    protected void registrarsector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            
            String nombreSector = request.getParameter("NombreSector");
            String descripcionSector = request.getParameter("DescripcionSector");
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Sector objSector = new Sector(nombreSector, descripcionSector, "Activo");
            sesion.beginTransaction();
            sesion.save(objSector);
            sesion.getTransaction().commit();
            sesion.close();
            response.getWriter().write("200");
        
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
        
        
    }

    protected void versectores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
        try{
        
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Sector");
            
            List<Sector> listaEquipo = query.list();
            System.out.println(listaEquipo);
            response.getWriter().write("sdd");
        
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
