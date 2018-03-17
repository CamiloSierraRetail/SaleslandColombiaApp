package Controlador;

import Modelo.Area;
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
            
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Canal objCanal = new Canal(NombreCanal, DescripcionCanal, "Activo", objSector);
            sesion.beginTransaction();
            sesion.save(objCanal);
            sesion.getTransaction().commit();
            sesion.close();
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
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Canal");
            List<Canal> listaCanal = query.list();
            for(Canal canal : listaCanal){
            
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td>"+canal.getNombreCanal()+"</td>"
                                              + "<td>"+canal.getDescripcion()+"</td>"
                                              + "<td>"+canal.getSector().getNombreSector()+"</td>"
                                              + "<td class='text-right'>"+canal.getEstado()
                                                      
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
                                                + "<a href='/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/editarcanal.jsp?_"+canal.getIdCanal()+"' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
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
    
    protected void cargaDatosCanal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            System.out.println("...............................................");
            String idCanal = request.getParameter("idCanal");
            System.out.println("----------------> "+idCanal);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
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
            
            Sector objSector = new Sector();
            objSector.setIdSector(Integer.parseInt(Sector));
            Canal objCanal = new Canal(Nombre, Descripcion, Estado, objSector);
            objCanal.setIdCanal(Integer.parseInt(idCanal));
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            
            
            /// ACTUALIZA EL ESTADO DEL AREA /////////////
            Query queyBuscarAreas = sesion.createQuery("FROM Area WHERE Canal="+idCanal+"");
            List<Area> listaAreas = queyBuscarAreas.list();
            for(Area area : listaAreas){
            
                Query queryCargo = sesion.createQuery("FROM Cargo WHERE area='"+area.getIdArea()+"'");
                List<Cargo>ListaCargo = queryCargo.list();
                for(Cargo cargo : ListaCargo){
                
                    Query query = sesion.createSQLQuery("UPDATE usuario SET Estado='"+Estado+"' WHERE cargo="+cargo.getIdCargo()+"");
                }
                
//                Query queryEmpleado = sesion.createQuery("FROM Empleado WHERE Area = "+area.getIdArea()+"");
//                List<Empleado> listaEmpleado = queryEmpleado.list();
//                for(Empleado empleado : listaEmpleado){
//                
//                    /// ACTUALIZA EL ESTADO DE LOS USUARIOS QUE PERTENESCAN A ESTA AREA ///////////////
//                    sesion.beginTransaction();
//                    Query queryUsuario = sesion.createSQLQuery("UPDATE usuario SET Estado='"+Estado+"' WHERE idUsuario="+empleado.getUsuario().getIdUsuario()+"");
//                    queryUsuario.executeUpdate();
//                    sesion.getTransaction().commit();
//                }
                
            }
            
            sesion.beginTransaction();
            Query queryAreas = sesion.createSQLQuery("UPDATE area SET Estado='"+Estado+"' WHERE Canal="+idCanal+"");
            queryAreas.executeUpdate();
            sesion.getTransaction().commit();
            
            
            sesion.beginTransaction();
            sesion.update(objCanal);
            sesion.getTransaction().commit();
            sesion.close();
            
            response.getWriter().write("200");
            
        }catch(Exception e){
        
            System.err.println(e);
            response.getWriter().write("500");
        }
    
    }
      private void cargacomboCanal(HttpServletRequest request, HttpServletResponse response)   
        throws ServletException, IOException {
    
        try{
        
            System.out.println("Combo asas Canal");
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            
            Query query = sesion.createQuery("FROM Canal WHERE Estado='Activo'");
            JSONArray  canalJson = new JSONArray();

            List<Canal> listaCanal = query.list();
            for(Canal canal : listaCanal){
            
                canalJson.add(canal.getIdCanal());
                canalJson.add(canal.getNombreCanal());            
            }
            response.getWriter().write(canalJson.toJSONString());    
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
