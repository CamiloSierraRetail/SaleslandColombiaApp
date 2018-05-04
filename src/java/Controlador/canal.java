package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Cargo;
import Modelo.Sector;
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


public class canal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Convertir a json
            En caso de necesitar ver codigo completo en buscarEquipoEncuentroFutPlayFinal (._.)
            String json = new Gson().toJson(listaEquipo);
            response.getWriter().write(json);
        */
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
            
                //Usar y crear cada caso para cada una de las acciones que se vayan a realizar
                case "registrar":
                    registrarCanal(request, response);
                    break;
                    
                case "varcanales":
                     verCanales(request, response);
                    break;
                    
                case "cargardatoscanal":
                    cargaDatosCanal(request, response);
                    break;
                case "editarcanal":
                    editarCanal(request, response);
                    break;
                case "cargacombocanal":
                    cargacomboCanal(request, response);
                    break;
                case "cargarcanalesdependientes":                    
                    cargarCanalesDependientes(request, response);
                    break;
            }
            
        }
    }

    protected void registrarCanal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{            
            String SectorCanal = request.getParameter("SectorCanal");
            String NombreCanal = request.getParameter("NombreCanal");
            String DescripcionCanal = request.getParameter("DescripcionCanal");
            
            Sector objSector = new Sector();
            objSector.setIdSector(Integer.parseInt(SectorCanal));
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Canal objCanal = new Canal(NombreCanal, DescripcionCanal, "Activo", objSector);
            sesion.beginTransaction();
            sesion.save(objCanal);
            sesion.getTransaction().commit();
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write("200");            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
        
    }
    protected void verCanales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{            
            int countRows = 1;
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Canal");
            List<Canal> listaCanal = query.list();
            response.setCharacterEncoding("UTF-8");
            for(Canal canal : listaCanal){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+canal.getNombreCanal()+"</td>"
                                              + "<td>"+canal.getDescripcion()+"</td>"
                                              + "<td>"+canal.getSector().getNombreSector()+"</td>"
                                              + "<td class='text-right'>"+canal.getEstado()+ "</td>"
                                              + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-info btn-link btn-xs' data-original-title='Ver Canal'>"
                                                    + "<i class='fa fa-eye blue-corp'></i>"
                                                + "</a>"   
                                                + "<button onclick='verDatosCanal("+canal.getIdCanal()+")' data-toggle='modal' data-target='#ModalEditarCanal' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                    + "<i class='fa fa-edit gray-corp'></i>"
                                                + "</button>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Estadisticas'>"
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
    
    protected void cargaDatosCanal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{                        
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            String idCanal = request.getParameter("idCanal");
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Canal WHERE idCanal="+idCanal+"");
            JSONArray  canalJson = new JSONArray();
            List<Canal> listaCanal = query.list();
            for(Canal canal : listaCanal){
            
                canalJson.add(canal.getIdCanal());
                canalJson.add(canal.getNombreCanal());
                canalJson.add(canal.getDescripcion());
                canalJson.add(canal.getEstado());
                canalJson.add(canal.getSector().getIdSector());
                canalJson.add(canal.getSector().getNombreSector());
                canalJson.add(canal.getSector().getEstado());
                
            }
            
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write(canalJson.toJSONString());            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
    protected void editarCanal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{            
            String idCanal = request.getParameter("IdCanal");
            String Estado = request.getParameter("EstadoCanal");
            String Nombre = request.getParameter("NombreCanal");
            String Descripcion = request.getParameter("DescripcionCanal");
            String Sector = request.getParameter("SectorCanal");                        
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
                        
            Query querySector = sesion.createQuery("FROM Canal WHERE idCanal = "+idCanal+"");
            List<Canal> listaCanal = querySector.list();
            for(Canal canal : listaCanal){
            
                if (!canal.getEstado().equals(Estado)) {
                
                    sesion.beginTransaction();
                    Query queryActualizarArea = sesion.createSQLQuery("UPDATE Area SET Estado = '"+Estado+"' WHERE Canal="+idCanal+"");
                    queryActualizarArea.executeUpdate();
                    sesion.getTransaction().commit();
                    
                    
                    ///////////// STATUS PARA AREAS /////////////////
                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal= "+canal.getIdCanal()+"");
                    List<Area>ListaArea = queryArea.list();
                    for(Area area : ListaArea){

                        ////////////////7COORECCIN DEL CODIGO DE ABAJO ///////////////
                        Query queryCargoPro = sesion.createQuery("FROM Area_Cargo WHERE Area='"+area.getIdArea()+"'");
                        List<Area_Cargo>ListaCargoPro = queryCargoPro.list();
                        for(Area_Cargo area_cargo : ListaCargoPro){

                            sesion.beginTransaction();
                            Query queryActuzalizarUsuario = sesion.createSQLQuery("UPDATE usuario SET Estado='"+Estado+"' WHERE Cargo="+area_cargo.getCargo().getIdCargo()+"");
                            queryActuzalizarUsuario.executeUpdate();
                            sesion.getTransaction().commit();
                        }    

                    }
                
                }
            
            }

            sesion.beginTransaction();
            Query query = sesion.createSQLQuery("UPDATE canal SET Estado='"+Estado+"', NombreCanal='"+Nombre+"', Descripcion='"+Descripcion+"' WHERE idCanal="+idCanal+"");
            query.executeUpdate();
            sesion.getTransaction().commit();
            
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write("200");            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
      private void cargacomboCanal(HttpServletRequest request, HttpServletResponse response)   
        throws ServletException, IOException {
    
        try{            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            Query query = sesion.createQuery("FROM Canal WHERE Estado='Activo'");
            JSONArray  canalJson = new JSONArray();

            List<Canal> listaCanal = query.list();
            for(Canal canal : listaCanal){
            
                canalJson.add(canal.getIdCanal());
                canalJson.add(canal.getNombreCanal());            
            }
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write(canalJson.toJSONString());                
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        
        }
        
    }
    protected void cargarCanalesDependientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{                
            String idSector = request.getParameter("idSector");                        
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Canal WHERE Sector="+idSector+"");
            List<Canal> listaCanal = query.list();
            JSONArray canalJson = new JSONArray();
            
            for(Canal canal : listaCanal){
            
                canalJson.add(canal.getIdCanal());
                canalJson.add(canal.getNombreCanal());
                
            }
            sesion.close();
            objSessionFactory.close();
            response.getWriter().write(canalJson.toJSONString());            
        }catch(Exception e){
        
            response.getWriter().write("500");
            System.err.println(e);
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
