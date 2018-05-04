package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Cargo;
import Modelo.Sector;
import com.google.gson.Gson;
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
                    
                case "cargardatossector":
                    cargardatossector(request, response);
                    break;
                case "editarsector":
                    editarsector(request, response);                    
                    break;
                case "cargarcombosector":
                    cargarcombosector(request, response);
                
            }
            
        }
        
    }
    
    protected void registrarsector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            String nombreSector = request.getParameter("NombreSector");
            String descripcionSector = request.getParameter("DescripcionSector");      
            Session sesion = objSessionFactory.openSession();            
            Sector objSector = new Sector(nombreSector, descripcionSector, "Activo");
            sesion.beginTransaction();
            sesion.save(objSector);
            sesion.getTransaction().commit();
            response.getWriter().write("200");            
            sesion.close();  
            objSessionFactory.close();
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
        
        
    }

    protected void versectores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        
        try{            
            int countRows = 1;
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();            
            Query query = sesion.createQuery("FROM Sector");                        
            List<Sector> listaSector = query.list();            
                        
            response.setCharacterEncoding("UTF-8");
            for(Sector sector : listaSector){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+sector.getNombreSector()+"</td>"
                                              + "<td>"+sector.getDescripcionSector()+"</td>"
                                              + "<td class='text-right'>"+sector.getEstado()+ "</td>"
                                              + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-link btn-xs' data-original-title='Ver Sector'>"
                                                    + "<i class='fa fa-eye blue-corp'></i>"
                                                + "</a>"   
                                                + "<button onclick='verDatosSector("+sector.getIdSector()+")' data-toggle='modal' data-target='#ModalEditarSector' rel='tooltip' title='' class='btn btn-link btn-xs' data-original-title='Editar'>"
                                                    + "<i class='fa fa-edit gray-corp'></i>"
                                                + "</button>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-info btn-link btn-xs' data-original-title='Ver Sector'>"
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
    protected void cargardatossector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            String idSector = request.getParameter("idSector");
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Sector WHERE IdSector = "+idSector+"");
            
            List<Sector> listaSector = query.list();
            Gson gson = new Gson();
            String json = gson.toJson(listaSector);
            response.getWriter().write(json);
            
            sesion.close();
            objSessionFactory.close();
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }    
    }
    protected void editarsector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            
            String id = request.getParameter("IdSector");
            
            int idEstado = Integer.parseInt(id);            
            String estado = request.getParameter("EstadoSector");
            String nombre = request.getParameter("NombreSector");
            String descripcion = request.getParameter("DescripcionSector");
            
            Session sesion = objSessionFactory.openSession();
            
            Query querySector = sesion.createQuery("FROM Sector WHERE idSector = "+id+"");
            List<Sector> listaSector = querySector.list();
            for(Sector sector : listaSector){
            
                if (!sector.getEstado().equals(estado)) {
                
                    System.out.println("Esta cambiando el estado");
                    
                    sesion.beginTransaction();
                    Query queryActualizarCanal = sesion.createSQLQuery("UPDATE Canal SET Estado='"+estado+"' WHERE Sector="+id+"");
                    queryActualizarCanal.executeUpdate();
                    sesion.getTransaction().commit();

                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector = "+id+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for (Canal canal : listaCanal){

                        sesion.beginTransaction();
                        Query queryActualizarArea = sesion.createSQLQuery("UPDATE Area SET Estado = '"+estado+"' WHERE Canal="+canal.getIdCanal()+"");
                        queryActualizarArea.executeUpdate();
                        sesion.getTransaction().commit();

                    }
                    //////////////// STATUS ADMINISTRADORES Y USUARIOS /////////////
                    
                    //////////// STATUS PARA CANAL /////////////////
                    Query queryCanalUsuarios = sesion.createQuery("FROM Canal WHERE Sector = "+id+"");
                    List<Canal> listaCanalUsuarios = queryCanalUsuarios.list();
                    for(Canal canal : listaCanalUsuarios){
                    
                        ////////////////7COORECCIN DEL CODIGO DE ABAJO ///////////////
                        Query queryCargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal='"+canal.getIdCanal()+"'");
                        List<Canal_Cargo>ListaCargo = queryCargo.list();
                        for(Canal_Cargo canal_cargo : ListaCargo){
                        
                            sesion.beginTransaction();
                            Query queryActuzalizarUsuario = sesion.createSQLQuery("UPDATE usuario SET Estado='"+estado+"' WHERE Cargo="+canal_cargo.getCargo().getIdCargo()+"");
                            queryActuzalizarUsuario.executeUpdate();
                            sesion.getTransaction().commit();
                        }
                        
                        ///////////// STATUS PARA AREAS /////////////////
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal= "+canal.getIdCanal()+"");
                        List<Area>ListaArea = queryArea.list();
                        for(Area area : ListaArea){
                            
                            ////////////////7COORECCIN DEL CODIGO DE ABAJO ///////////////
                            Query queryCargoPro = sesion.createQuery("FROM Area_Cargo WHERE Area='"+area.getIdArea()+"'");
                            List<Area_Cargo>ListaCargoPro = queryCargoPro.list();
                            for(Area_Cargo area_cargo : ListaCargoPro){

                                sesion.beginTransaction();
                                Query queryActuzalizarUsuario = sesion.createSQLQuery("UPDATE usuario SET Estado='"+estado+"' WHERE Cargo="+area_cargo.getCargo().getIdCargo()+"");
                                queryActuzalizarUsuario.executeUpdate();
                                sesion.getTransaction().commit();
                            }    
                        
                        }
                        
                    }

                }
                
            }
            
            sesion.beginTransaction();
            Query query = sesion.createSQLQuery("UPDATE sector SET Estado='"+estado+"', NombreSector='"+nombre+"', Descripcion='"+descripcion+"' WHERE idSector="+idEstado+"");
            query.executeUpdate();
            sesion.getTransaction().commit();
            
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write("200");
        }catch(Exception e){
            response.getWriter().write("500");
            System.err.println(e);
        }
        
    }
    
    protected void cargarcombosector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Sector WHERE Estado='Activo'");
            List<Sector> listaSector = query.list();
            
            Gson gson = new Gson();
            String json = gson.toJson(listaSector);
            response.getWriter().write(json);
            
            sesion.close();
            objSessionFactory.close();
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
