package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Cargo;
import Modelo.Sector;
import Modelo.Sector_Cargo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
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

            }
        }
    }
    private void vercargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int countRows = 1;
            Session sesion = HibernateUtil.getSessionFactory().openSession();
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
                
                response.getWriter().write("<tr>"
                        + "<td class='text-center'>" + countRows + "</td>"
                        + "<td>" + cargo.getNombreCargo() + "</td>"
                        + "<td>" + cargo.getDescripcion() + "</td>"                        
                        + "<td>" + cargo.getTipo() + "</td>"
                        + "<td>" + sector + "</td>"
                        + "<td>" + canal + "</td>"
                        + "<td>" + area + "</td>"
                        + "<td>" + cargo.getEstado() + "</td>"
                          + "<td class='td-actions text-right'>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-info btn-link btn-xs' data-original-title='Ver Sector'>"
                                                    + "<i class='fa fa-user'></i>"
                                                + "</a>"   
                                                + "<a href='/SaleslandColombiaApp/ligth-bootstrap/Pages/cargo/editarcargo.jsp?_"+cargo.getIdCargo()+"' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Editar'>"
                                                    + "<i class='fa fa-edit'></i>"
                                                + "</a>"
                                                + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Eliminar'>"
                                                    + "<i class='fa fa-times'></i>"
                                                + "</a>"
                                              + "</td>"
                        + "</tr>");
                countRows++;            
            }
                        
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
            System.out.println("...............................................");
            String IdCargo = request.getParameter("idCargo");
            System.out.println("----------------> " + IdCargo);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
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
            response.getWriter().write(canalJson.toJSONString());

        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }
    
    private void editarCargo(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
                 System.out.println("ENTRasdasdasdsadsadsadassdOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        try{
            String id = request.getParameter("IdCargo");
             System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            int IdCargo = Integer.parseInt(id);
            System.out.println(IdCargo);
            String estado = request.getParameter("Estado");
             String nombreCargo = request.getParameter("NombreCargo");
            String descripcion = request.getParameter("Descripcion");            
            String tipo = request.getParameter("Tipo");
            String sector = request.getParameter("Sector");
            String canal = request.getParameter("Canal");
            String area = request.getParameter("Area");
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createSQLQuery("UPDATE cargo SET Estado='"+estado+"', NombreCargo='"+nombreCargo+"', Descripcion='"+descripcion+"', Tipo='"+tipo+"', Sector='"+sector+"', Canal='"+canal+"', Area='"+area+"' WHERE idCargo="+IdCargo+"");
            query.executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
           
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
                                    
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Cargo objCargo = new Cargo(NombreCargo, Descripcion, Tipo, "Activo");
            sesion.beginTransaction();
            sesion.save(objCargo);
            sesion.getTransaction().commit();

            Query queryCargo = sesion.createQuery("FROM Cargo ORDER BY idCargo DESC");
            queryCargo.setMaxResults(1);
            List<Cargo> listaCargo = queryCargo.list();
            for(Cargo cargo : listaCargo){
            
                Sector objSector = new Sector();
                objSector.setIdSector(Integer.parseInt(Sector));
                Sector_Cargo objSector_Cargo = new Sector_Cargo(cargo, objSector);
                sesion.beginTransaction();
                sesion.save(objSector_Cargo);
                sesion.getTransaction().commit();
                
                if (!"Seleccione".equals(Canal)) {
                
                    
                    Canal objCanal = new Canal();
                    objCanal.setIdCanal(Integer.parseInt(Canal));
                    //Canal_Cargo objCanal_Cargo = new Canal_Cargo(cargo, objCanal);
                    sesion.beginTransaction();
                    //sesion.save(objCanal_Cargo);
                    sesion.getTransaction().commit();
                    
                }
                if (!"Seleccione".equals(Area)) {
                    
                    Area objArea = new Area();
                    objArea.setIdArea(Integer.parseInt(Area));
                    //Area_Cargo objArea_Cargo = new Area_Cargo(cargo, objArea);
                    sesion.beginTransaction();
                    //sesion.save(objArea_Cargo);
                    sesion.getTransaction().commit();
                }
            
            }

            sesion.close();
            response.getWriter().write("200");

        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }
}
