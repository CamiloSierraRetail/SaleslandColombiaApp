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

public class cargo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url[] = request.getRequestURI().split("/");

        if (url.length >= 3) {

            switch (url[3]) {

                //USAR Y CREAR CADA CASO SEGUN CORRESPONDA .
                case "registrarcargo":

                    registrarcargo(request, response);

                    break;
                case "vercargos":
                    vercargos(request, response);
                    break;

                case "cargardatoscargo":
                    cargaDatosCargo(request, response);
                    break;
                case "editarcargo":
                    editarCargo(request, response);
                    break;
                case "cargarCargo":
                    cargarCargo(request, response);
                    break;
                case "eliminarCargo":
                    eliminarCargo(request, response);
                    break;
                default:
                    response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/alertas/404.jsp");
                    break;
                

            }
        }
    }
    private void vercargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {            
            int countRows = 1;
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Cargo");
            List<Cargo> listaCargo = query.list();
            
            for(Cargo cargo : listaCargo){
                
                String sector = "-", canal = "-", area = "-";
                
                Query querySector = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+cargo.getIdCargo()+"");
                List<Sector_Cargo> listaCargo_Sector = querySector.list();
                for(Sector_Cargo sector_cargo : listaCargo_Sector){
                    
                    sector = sector_cargo.getSector().getNombreSector();
                
                }
                
                
                Query queryCanal = sesion.createQuery("FROM Canal_Cargo WHERE Cargo="+cargo.getIdCargo()+"");
                List<Canal_Cargo> listaCanal = queryCanal.list();
                for(Canal_Cargo canal_cargo : listaCanal){
                
                    if (canal_cargo.getCanal() != null) {
                        sector = canal_cargo.getCanal().getSector().getNombreSector();
                        canal = canal_cargo.getCanal().getNombreCanal();
                    }
                    
                }
                
                
                Query queryArea = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+cargo.getIdCargo()+"");
                List<Area_Cargo> listaArea = queryArea.list();
                for(Area_Cargo area_cargo : listaArea){
                
                    if (area_cargo.getArea() != null) {
                        sector = area_cargo.getArea().getCanal().getSector().getNombreSector();
                        canal = area_cargo.getArea().getCanal().getNombreCanal();
                        area = area_cargo.getArea().getNombreArea();
                    }
                    
                }
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("<tr>"
                        + "<td class='text-center'>" + countRows + "</td>"
                        + "<td>" + cargo.getNombreCargo() + "</td>"
                        + "<td>" + cargo.getDescripcion() + "</td>"                        
                        + "<td>" + cargo.getTipo() + "</td>"
                        + "<td>" + sector + "</td>"
                        + "<td>" + canal + "</td>"
                        + "<td>" + area + "</td>"
                        + "<td>" + cargo.getEstado() + "</td>"
                        + "<td class='td-actions text-center'>"                                                                             
                            + "<button onclick='verDatosCargo("+cargo.getIdCargo()+")' data-toggle='modal' data-target='#modalEditarCargo' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                + "<i class='fa fa-edit blue-corp'></i>"
                            + "</button>"
                            + "<button onclick='eliminarCargo("+cargo.getIdCargo()+")' data-toggle='modal' data-target='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                + "<i class='fa fa-remove'></i>"
                            + "</button>"
                        + "</td>"
                        + "</tr>");
                countRows++;            
            }
            sesion.close();  
            objSessionFactory.close();
        } catch (Exception e) {

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

    private void cargaDatosCargo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {                        
            String IdCargo = request.getParameter("idCargo");    
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Cargo WHERE idCargo=" + IdCargo + "");
            JSONArray canalJson = new JSONArray();
            List<Cargo> listaCargo = query.list();
            for (Cargo cargo : listaCargo) {

                canalJson.add(cargo.getIdCargo());
                canalJson.add(cargo.getNombreCargo());
                canalJson.add(cargo.getDescripcion());                
                canalJson.add(cargo.getTipo());
                canalJson.add(cargo.getEstado());
                
            }
            sesion.close();
            objSessionFactory.close();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(canalJson.toJSONString());            
        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }
    
    private void editarCargo(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
                 
        try{     
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            String idCargo = request.getParameter("IdCargo");
            String nombre = request.getParameter("NombreCargo");
            String descripcion = request.getParameter("Descripcion");
            String estado = request.getParameter("Estado");
                        
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();            
            
            Cargo objCargo = (Cargo) sesion.createQuery("FROM Cargo WHERE idCargo="+idCargo+"").uniqueResult();
            
            if (!objCargo.getEstado().equals(estado)) {
                
                sesion.beginTransaction();
                Query queryUsuarios = sesion.createSQLQuery("UPDATE usuario SET Estado='"+estado+"' WHERE idUSuario != "+objUsuario.getIdUsuario()+" AND Cargo="+objCargo.getIdCargo()+"");
                queryUsuarios.executeUpdate();
                sesion.getTransaction().commit();
                
            }
                        
            sesion.beginTransaction();
            Query query = sesion.createSQLQuery("UPDATE cargo SET Estado='"+estado+"', NombreCargo='"+nombre+"', Descripcion='"+descripcion+"' WHERE idCargo="+objCargo.getIdCargo()+"");
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

    private void registrarcargo(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        try {            
            
            String NombreCargo = request.getParameter("NombreCargo");
            String Descripcion = request.getParameter("Descripcion");            
            String Tipo = request.getParameter("Tipo");
            String Sector = request.getParameter("Sector");
            String Canal = request.getParameter("Canal");
            String Area = request.getParameter("Area");
                    
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Cargo objCargo = new Cargo(NombreCargo, Descripcion, Tipo, "Activo");
            sesion.beginTransaction();
            sesion.save(objCargo);
            sesion.getTransaction().commit();

            Query queryCargo = sesion.createQuery("FROM Cargo ORDER BY idCargo DESC");
            queryCargo.setMaxResults(1);
            List<Cargo> listaCargo = queryCargo.list();
            for(Cargo cargo : listaCargo){
            
                
                if (cargo.getTipo().equals("Director") && !"Seleccione el sector".equals(Sector)) {
                    
                    Sector objSector = new Sector();
                    objSector.setIdSector(Integer.parseInt(Sector));
                    Sector_Cargo objSector_Cargo = new Sector_Cargo(cargo, objSector);
                    sesion.beginTransaction();
                    sesion.save(objSector_Cargo);
                    sesion.getTransaction().commit();
                    
                }
                
                if ((cargo.getTipo().equals("JefeCanal") || cargo.getTipo().equals("CoordinadorCanal"))  && !"Seleccione el canal".equals(Canal)) {
                
                    
                    Canal objCanal = new Canal();
                    objCanal.setIdCanal(Integer.parseInt(Canal));
                    Canal_Cargo objCanal_Cargo = new Canal_Cargo(cargo, objCanal);
                    sesion.beginTransaction();
                    sesion.save(objCanal_Cargo);
                    sesion.getTransaction().commit();
                    
                }
                if ((cargo.getTipo().equals("JefeArea") || cargo.getTipo().equals("Empleado")) && !"Seleccione el area".equals(Area)) {
                    
                    Area objArea = new Area();
                    objArea.setIdArea(Integer.parseInt(Area));
                    Area_Cargo objArea_Cargo = new Area_Cargo(cargo, objArea);
                    sesion.beginTransaction();
                    sesion.save(objArea_Cargo);
                    sesion.getTransaction().commit();
                }
            
            }

            sesion.close();
            objSessionFactory.close();
            response.getWriter().write("200");            
        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }
    private void cargarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            JSONArray cargoJson = new JSONArray();
            String idCargo = request.getParameter("id");
            String Sector = "", Canal="", Area="";
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            Cargo objCargo = (Cargo) sesion.createQuery("FROM Cargo WHERE IdCargo="+idCargo+"").uniqueResult();
            
            if (objCargo.getTipo().equals("Director")) {
                
                Sector_Cargo objSectorCargo = (Sector_Cargo) sesion.createQuery("FROM Sector_Cargo WHERE Cargo = "+objCargo.getIdCargo()+"").uniqueResult();
                Sector = String.valueOf(objSectorCargo.getSector().getIdSector());
                
                
            }else if (objCargo.getTipo().equals("JefeCanal") || objCargo.getTipo().equals("CoordinadorCanal")) {
                
                Canal_Cargo objCanal_Cargo = (Canal_Cargo) sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objCargo.getIdCargo()+"").uniqueResult();
                               
                Canal = String.valueOf(""+objCanal_Cargo.getCanal().getIdCanal()+"");
                Sector = String.valueOf(objCanal_Cargo.getCanal().getSector().getIdSector());
                
            }else if (objCargo.getTipo().equals("JefeArea") || objCargo.getTipo().equals("Empleado")) {
                
                Area_Cargo objArea_Cargo = (Area_Cargo) sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objCargo.getIdCargo()+"").uniqueResult();
                
                Sector = String.valueOf(objArea_Cargo.getArea().getCanal().getSector().getIdSector());
                Canal = String.valueOf(objArea_Cargo.getArea().getCanal().getIdCanal());
                Area = String.valueOf(objArea_Cargo.getArea().getIdArea());
                
            }
            cargoJson.add(objCargo.getIdCargo());
            cargoJson.add(objCargo.getNombreCargo());
            cargoJson.add(objCargo.getDescripcion());
            cargoJson.add(objCargo.getTipo());
            cargoJson.add(objCargo.getEstado());            
            cargoJson.add(Sector);
            cargoJson.add(Canal);
            cargoJson.add(Area);
            
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(cargoJson.toJSONString());
            
            
            sesion.close();
            objSessionFactory.close();
        }catch(Exception ex){
            
            response.getWriter().write("500");
            System.err.println(ex);
        }
    
    }
    private void eliminarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            String idCargo = request.getParameter("IdCargo");
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE Cargo="+idCargo+"");
            List<Usuario> listaUsuarios = queryUsuarios.list();
            
            if (listaUsuarios.size() > 0) {
                response.getWriter().write("204");
            }else{
            
                Cargo objCargo1 = (Cargo) sesion.createQuery("FROM Cargo WHERE idCargo = "+idCargo+"").uniqueResult();                
                
                if (objCargo1.getTipo().equals("Director")) {
                                                            
                    sesion.beginTransaction();
                    Query deleteSector_Cargo = sesion.createSQLQuery("DELETE FROM sector_cargo WHERE cargo="+idCargo+"");
                    deleteSector_Cargo.executeUpdate();                    
                    sesion.getTransaction().commit();
                    
                }else if (objCargo1.getTipo().equals("JefeCanal") || objCargo1.getTipo().equals("CoordinadorCanal")) {
                    
                    sesion.beginTransaction();
                    Query deleteCanal_Cargo = sesion.createSQLQuery("DELETE FROM canal_cargo WHERE cargo="+idCargo+"");
                    deleteCanal_Cargo.executeUpdate();                    
                    sesion.getTransaction().commit();
                    
                }else if (objCargo1.getTipo().equals("JefeArea") || objCargo1.getTipo().equals("Empleado")) {
                    
                    sesion.beginTransaction();
                    Query deleteArea_Cargo = sesion.createSQLQuery("DELETE FROM area_cargo WHERE cargo="+idCargo+"");
                    deleteArea_Cargo.executeUpdate();                    
                    sesion.getTransaction().commit();                                        
                }
                                
                
                sesion.beginTransaction();
                Query deleteCargo = sesion.createSQLQuery("DELETE FROM cargo WHERE idCargo="+idCargo+"");
                int ff = deleteCargo.executeUpdate();
                sesion.getTransaction().commit();
                                
                response.getWriter().write("200");
            }
                        
            sesion.close();
            objSessionFactory.close();            
            
        }catch(Exception ex){
            response.getWriter().write("500");
            System.err.println(ex);
        }
        
    }
}
