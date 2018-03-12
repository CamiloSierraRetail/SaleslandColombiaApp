package Controlador;

import Modelo.Ingreso;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;


public class ingreso extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
            
                //Usar y crear cada caso para cada una de las acciones que se vayan a realizar
                case "registrar":
                    
                    break;
                case "ingresousuario":
                    usuarioIngreso(request, response);
                    break;
            }
            
        }
        
    }
    protected void usuarioIngreso(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
            
            String UsuarioID = request.getParameter("UsuarioID");
            String Fecha = request.getParameter("Fecha");
            String hora = request.getParameter("Hora");
            String Minutos = request.getParameter("Minutos");
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Usuario WHERE Documento='"+UsuarioID+"'");
            List<Usuario> listaUsuario = query.list();
            
            if (listaUsuario.size() == 1) {
                for (Usuario usuario : listaUsuario){            
                    if (usuario.getEstado().equals("Activo")) {
               
                        Query queryIngresos = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+Fecha+"'");
                        List<Ingreso> listaIngreso = queryIngresos.list();
                        
                        Usuario objUsuario = new Usuario();
                        objUsuario.setIdUsuario(usuario.getIdUsuario());
                        
                        
                        System.out.println("listaIngreso .size --------------------------> "+listaIngreso.size());
                        
                        if (listaIngreso.size() == 0) {
                            System.out.println("Tipo de horario ---------> " + usuario.getHorario() + "   Hora ----------> "+hora + "          Minutos----------> "+Minutos);
                            String observacionIngreso = "";
                            if (usuario.getHorario().equals("A")) {
                                
                                if (Integer.parseInt(hora) > 8 && Integer.parseInt(Minutos) > 5) {
                                
                                    observacionIngreso = "Tarde";

                                }else if (Integer.parseInt(hora) < 8 && Integer.parseInt(Minutos) < 55) {

                                    observacionIngreso = "Temprano";

                                }else{

                                    observacionIngreso = "Justo";
                                }
                                
                            }else if (usuario.getHorario().equals("B")) {
                                
                                if (Integer.parseInt(hora) > 7 && Integer.parseInt(Minutos) > 5) {
                                
                                    observacionIngreso = "Tarde";

                                }else if (Integer.parseInt(hora) < 7 && Integer.parseInt(Minutos) < 55) {

                                    observacionIngreso = "Temprano";

                                }else{

                                    observacionIngreso = "Justo";
                                }
                                
                            }
                            
                            
                            Ingreso objIngreso = new Ingreso("Lunes", new Date(), new Date(), "Ingreso", "Lector", observacionIngreso, objUsuario);
                            sesion.beginTransaction();
                            sesion.save(objIngreso);
                            sesion.getTransaction().commit();
                            sesion.close();


                            response.getWriter().write("Ingreso"+observacionIngreso);
                            
                        }else if(listaIngreso.size() == 1){
                            
                            for(Ingreso ingreso : listaIngreso){
                                
                                String observacionIngreso = "";
                                if (usuario.getHorario().equals("A")) {
                                
                                    if (Integer.parseInt(hora) > 18 && Integer.parseInt(Minutos) > 5) {

                                        observacionIngreso = "Tarde";

                                    }else if (Integer.parseInt(hora) < 18 && Integer.parseInt(Minutos) < 55) {

                                        observacionIngreso = "Temprano";

                                    }else{

                                        observacionIngreso = "Justo";
                                    }

                                }else if (usuario.getHorario().equals("B")) {

                                    if (Integer.parseInt(hora) > 17 && Integer.parseInt(Minutos) > 5) {

                                        observacionIngreso = "Tarde";

                                    }else if (Integer.parseInt(hora) < 17 && Integer.parseInt(Minutos) < 55) {

                                        observacionIngreso = "Temprano";

                                    }else{

                                        observacionIngreso = "Justo";
                                    }

                                }
                                
                                if (ingreso.getTipo().equals("Ingreso")) {
                                    
                                    Ingreso objIngreso = new Ingreso("Lunes",new Date(), new Date(), "Salida", "Lector", observacionIngreso, objUsuario);
                                    sesion.beginTransaction();
                                    sesion.save(objIngreso);
                                    sesion.getTransaction().commit();
                                    sesion.close();


                                    response.getWriter().write("Salida"+observacionIngreso);
                                    
                                }
                            
                            }
                            
                        }
                        
                    }
                }                
            }else if (listaUsuario.size() > 1) {
                response.getWriter().write("406");
            }else if (listaUsuario.size() == 0) {
                response.getWriter().write("404");
            }
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
