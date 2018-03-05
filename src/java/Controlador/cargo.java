package Controlador;

import Modelo.Cargo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;


public class cargo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
           String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
            
                //USAR Y CREAR CADA CASO SEGUN CORRESPONDA .
                case "registrar":
                    
                    registrarcargo(request, response);
                    
                    break;
                case "vercargos":
                    vercargos(request, response);
                    break;
                    
                case "cargardatossector":
                    cargardatossector(request, response, url[4]);
                    break;
                
            }           
        }          
    }
     private void registrarcargo(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
        
        try{
            
//            String nombreCargo = request.getParameter("NombreCargo");
//            String descripcionCargo = request.getParameter("DescripcionCargo");
//            String salario = request.getParameter("Salario");
//            Session sesion = HibernateUtil.getSessionFactory().openSession();
//            Cargo objSector = new Cargo(nombreCargo, descripcionCargo, salario);
//            sesion.beginTransaction();
//            sesion.save(objSector);
//            sesion.getTransaction().commit();
//            sesion.close();
//            response.getWriter().write("200");
        
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
        
        
    }
      private void vercargos(HttpServletRequest request, HttpServletResponse response) 
       
             throws ServletException, IOException {
        
        try{
        
            int countRows = 1;
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Cargo");                        
            List<Cargo> listaCargo = query.list();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            for(Cargo cargo : listaCargo){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+cargo.getNombreCargo()+"</td>"
                                              + "<td>"+cargo.getDescripcion()+"</td>"
                                              + "<td>"+cargo.getSalario()+ "</td>"                                    
                                            
                                              + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-info btn-link btn-xs' data-original-title='View Profile'>"
                                                    + "<i class='fa fa-user'></i>"
                                                + "</a>"   
                                                + "<a href='/SaleslandColombiaApp/sector/cargardatossector/"+cargo.getIdCargo()+"' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Edit Profile'>"
                                                    + "<i class='fa fa-edit'></i>"
                                                + "</a>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Remove'>"
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

    private void cargardatossector(HttpServletRequest request, HttpServletResponse response, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
