package Controlador;

import Modelo.Area;
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
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            for (Cargo cargo : listaCargo) {

                response.getWriter().write("<tr>"
                        + "<td class='text-center'>" + countRows + "</td>"
                        + "<td>" + cargo.getNombreCargo() + "</td>"
                        + "<td>" + cargo.getDescripcion() + "</td>"
                        + "<td>" + cargo.getSalario() + "</td>"
                        + "<td>" + cargo.getTipo() + "</td>"
                        + "<td>" + cargo.getSector() + "</td>"
                        + "<td>" + cargo.getCanal() + "</td>"
                        + "<td>" + cargo.getArea() + "</td>"
                        + "<td>" + cargo.getEstado() + "</td>"
                        + "<td class='td-actions text-right'>"
                        + "<a href='/SaleslandColombiaApp/sector/cargardatoscargos/" + cargo.getIdCargo() + "' rel='tooltip' title='' class='btn btn-warning btn-link btn-xs' data-original-title='Edit Profile'>"
                        + "<i class='fa fa-edit'></i>"
                        + "</a>"
                        + "<a href='#' rel='tooltip' title='' class='btn btn-danger btn-link btn-xs' data-original-title='Remove'>"
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
            String idCargo = request.getParameter("idCargo");
            System.out.println("----------------> " + idCargo);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Area WHERE idCargo=" + idCargo + "");
            JSONArray canalJson = new JSONArray();
            List<Cargo> listaCargo = query.list();
            for (Cargo cargo : listaCargo) {

                canalJson.add(cargo.getIdCargo());
                canalJson.add(cargo.getNombreCargo());
                canalJson.add(cargo.getDescripcion());
                canalJson.add(cargo.getSalario());
                canalJson.add(cargo.getTipo());
                canalJson.add(cargo.getEstado());
                canalJson.add(cargo.getSector());
                canalJson.add(cargo.getCanal());
                canalJson.add(cargo.getArea());
            }
            response.getWriter().write(canalJson.toJSONString());

        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }

    private void editarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String NombreCargo = request.getParameter("NombreCargo");
            String Descripcion = request.getParameter("Descripcion");
            String Salario = request.getParameter("Salario");
            String Tipo = request.getParameter("Tipo");
            String Estado = request.getParameter("Estado");
            String Sector = request.getParameter("Sector");
            String Canal = request.getParameter("Canal");
            String Area = request.getParameter("Area");

            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Cargo objCargo = new Cargo(NombreCargo, Descripcion, Double.parseDouble(Salario), Tipo, Estado, Sector, Canal, Area);

            sesion.beginTransaction();
            sesion.update(objCargo);
            sesion.getTransaction().commit();
            sesion.close();

            response.getWriter().write("200");

        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }
    } 

    private void registrarcargo(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        try {
            String NombreCargo = request.getParameter("NombreCargo");
            String Descripcion = request.getParameter("Descripcion");
            String Salario = request.getParameter("Salario");
            String Tipo = request.getParameter("Tipo");
            String Sector = request.getParameter("Sector");
            String Canal = request.getParameter("Canal");
            String Area = request.getParameter("Area");
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Cargo objCargo = new Cargo(NombreCargo, Descripcion, Double.parseDouble(Salario), Tipo, "Activo", Sector, Canal, Area);
            sesion.beginTransaction();
            sesion.save(objCargo);
            sesion.getTransaction().commit();
            sesion.close();
            response.getWriter().write("200");

        } catch (Exception e) {

            System.err.println(e);
            response.getWriter().write("500");
        }

    }
}
