
package Controlador;

import Modelo.Area;
import Modelo.Canal;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.json.simple.JSONArray;


public class area extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
                     
                switch (url[3]){
            
                //Usar y crear cada caso para cada una de las acciones que se vayan a realizar
                case "registrararea":
                    registrarArea(request, response);
                    break;
                    
                case "verarea":
                     verArea(request, response);
                    break;
                    
                case "cargardatosarea":
                    cargaDatosArea(request, response);
                    break;
                case "editararea":
                    editarArea(request, response);
                    break;
                case "cargarcomboarea":
                    cargarcomboarea(request, response);
                    break;
                    
                case "cargarareasdependientes":
                    cargarAreasDependientes(request, response);
                    break;
             
            }
            
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

    private void registrarArea(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        
        
        
        try{            
            
            String CanalArea = request.getParameter("CanalArea");
            String NombreArea = request.getParameter("NombreArea");
            String DescripcionArea = request.getParameter("descripcionArea");
            
            Canal objCanal = new Canal();
            objCanal.setIdCanal(Integer.parseInt(CanalArea));
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Area objArea = new Area(NombreArea, DescripcionArea, "Activo", objCanal);
            sesion.beginTransaction();
            sesion.save(objArea);
            sesion.getTransaction().commit();            
            response.getWriter().write("200");
            
            sesion.close();
            objSessionFactory.close();
        }catch(Exception e){   
            System.err.println(e);
            response.getWriter().write("500");
        }        
    }
    private void verArea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try{            
            
            int countRows = 1;
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Area");
            List<Area> listaArea = query.list();
            response.setCharacterEncoding("UTF-8");
            for(Area area : listaArea){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+area.getNombreArea()+"</td>"
                                              + "<td>"+area.getDescripcion()+"</td>"
                                              + "<td>"+area.getCanal().getNombreCanal()+"</td>"
                                              + "<td class='text-right'>"+area.getEstado()+ "</td>"
                                              + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-link btn-xs' data-original-title='Ver Sector'>"
                                                    + "<i class='fa fa-eye blue-corp'></i>"
                                                + "</a>"   
                                                + "<button onclick='verDatosArea("+area.getIdArea()+")' data-toggle='modal' data-target='#ModalEditarArea' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                    + "<i class='fa fa-edit gray-corp'></i>"
                                                + "</button>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                                    + "<i class='fa fa-bar-chart orange-corp'></i>"
                                                + "</a>"
                                              + "</td>"
                                         + "</tr>");
                countRows++;
                
            }
            sesion.close(); 
            objSessionFactory.close();
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    
        
    }

    private void cargaDatosArea(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
        try{
            
            String idArea = request.getParameter("idArea");            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Area WHERE idArea="+idArea+"");
            JSONArray  canalJson = new JSONArray();
            List<Area> listaArea = query.list();
            for(Area area : listaArea){
            
                canalJson.add(area.getIdArea());
                canalJson.add(area.getNombreArea());
                canalJson.add(area.getDescripcion());
                canalJson.add(area.getEstado());
                canalJson.add(area.getCanal().getIdCanal());
                canalJson.add(area.getCanal().getNombreCanal());
                
            }
            
            sesion.close();  
            objSessionFactory.close();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(canalJson.toJSONString());
            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
       
    }

    private void editarArea(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    
        try{
            
            String idArea = request.getParameter("IdArea");
            String Estado = request.getParameter("EstadoArea");
            String Nombre = request.getParameter("NombreArea");
            String Descripcion = request.getParameter("DescripcionArea");
            String Canal = request.getParameter("CanalArea");
            
            Canal objCanal = new Canal();
            objCanal.setIdCanal(Integer.parseInt(Canal));
            Area objArea = new Area(Nombre, Descripcion, Estado, objCanal);
            objArea.setIdArea(Integer.parseInt(idArea));
            objCanal.setIdCanal(Integer.parseInt(Canal));
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            sesion.beginTransaction();
            sesion.update(objArea);
            sesion.getTransaction().commit();
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write("200");
            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    }

    private void cargarcomboarea(HttpServletRequest request, HttpServletResponse response)     
            throws ServletException, IOException {
    
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            Query query = sesion.createQuery("FROM Area WHERE Estado='Activo'");
            JSONArray  areaJson = new JSONArray();

            List<Area> listaArea = query.list();
            for(Area area : listaArea){
            
                areaJson.add(area.getIdArea());
                areaJson.add(area.getNombreArea());            
            }
            sesion.close();   
            objSessionFactory.close();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(areaJson.toJSONString());    
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        
        }
        
    }
    private void cargarAreasDependientes(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        try{            
            String idCanal = request.getParameter("idCanal");
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Area WHERE Canal="+idCanal+"");
            List<Area> ListaArea = query.list();
            JSONArray areaJson = new JSONArray();
            
            for(Area area : ListaArea){
            
                areaJson.add(area.getIdArea());
                areaJson.add(area.getNombreArea());
            }
            sesion.close();
            objSessionFactory.close();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(areaJson.toJSONString());
        }catch(Exception e){
            response.getWriter().write("500");
            System.err.println("e");
        }
    }

}
