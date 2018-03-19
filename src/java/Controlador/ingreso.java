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
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;


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
                    
                case "promedioimgresos":
                    promedioIngresos(request, response);
                    break;
                    
                case "chartSemana":
                    chartSemana(request, response);
                    break;
            }
            
        }
        
    }
    protected void usuarioIngreso(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
            String Dia = request.getParameter("Dia");
            System.out.println(Dia);
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
                        
                        if (listaIngreso.size() == 0) {
                            
                            String observacionIngreso = "";
                            if (usuario.getHorario().equals("A")) {
                                
                                if (Integer.parseInt(hora) == 8 && Integer.parseInt(Minutos) > 5 || Integer.parseInt(hora) >= 9) {
                                
                                    observacionIngreso = "Tarde";

                                }else if (Integer.parseInt(hora) <= 7 && Integer.parseInt(Minutos) < 55 || Integer.parseInt(hora) < 7) {

                                    observacionIngreso = "Temprano";

                                }else{

                                    observacionIngreso = "Justo";
                                }
                                
                            }else if (usuario.getHorario().equals("B")) {
                                
                                if (Integer.parseInt(hora) >= 7 && Integer.parseInt(Minutos) > 5 || Integer.parseInt(hora) > 7) {
                                
                                    observacionIngreso = "Tarde";

                                }else if (Integer.parseInt(hora) <= 6 && Integer.parseInt(Minutos) < 55 || Integer.parseInt(hora) < 6) {

                                    observacionIngreso = "Temprano";

                                }else{

                                    observacionIngreso = "Justo";
                                }
                                
                            }
                            
                            
                            Ingreso objIngreso = new Ingreso(Dia, new Date(), new Date(), "Ingreso", "Lector", observacionIngreso, objUsuario);
                            sesion.beginTransaction();
                            sesion.save(objIngreso);
                            sesion.getTransaction().commit();
                            sesion.close();


                            response.getWriter().write("Ingreso"+observacionIngreso);
                            
                        }else if(listaIngreso.size() == 1){
                            
                            for(Ingreso ingreso : listaIngreso){
                                
                                String observacionIngreso = "";
                                if (usuario.getHorario().equals("A")) {
                                
                                    if (Integer.parseInt(hora) >= 18 && Integer.parseInt(Minutos) > 5 || Integer.parseInt(hora) > 18) {

                                        observacionIngreso = "Tarde";

                                    }else if (Integer.parseInt(hora) <= 17 && Integer.parseInt(Minutos) < 55 || Integer.parseInt(hora) < 17) {

                                        observacionIngreso = "Temprano";

                                    }else{

                                        observacionIngreso = "Justo";
                                    }

                                }else if (usuario.getHorario().equals("B")) {

                                    if (Integer.parseInt(hora) >= 17 && Integer.parseInt(Minutos) > 5 || Integer.parseInt(hora) > 17) {

                                        observacionIngreso = "Tarde";

                                    }else if (Integer.parseInt(hora) <= 16 && Integer.parseInt(Minutos) < 55 || Integer.parseInt(hora) < 16) {

                                        observacionIngreso = "Temprano";

                                    }else{

                                        observacionIngreso = "Justo";
                                    }

                                }
                                
                                if (ingreso.getTipo().equals("Ingreso")) {
                                    
                                    Ingreso objIngreso = new Ingreso(Dia,new Date(), new Date(), "Salida", "Lector", observacionIngreso, objUsuario);
                                    sesion.beginTransaction();
                                    sesion.save(objIngreso);
                                    sesion.getTransaction().commit();
                                    sesion.close();


                                    response.getWriter().write("Salida"+observacionIngreso);
                                    
                                }
                            
                            }
                            
                        }else{
                        
                            response.getWriter().write("407");
                        }
                        
                    }else{
                        response.getWriter().write("Inactivo");
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
    protected void promedioIngresos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try{
            int totalIngresos = 0, ingresosBien = 0, ingresosMal = 0, ingresosJusto = 0;
            
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Ingreso WHERE Usuario = "+objUsuario.getIdUsuario()+"");
            List<Ingreso> ListaIngreso = query.list();
            totalIngresos = ListaIngreso.size();
            for(Ingreso ingreso : ListaIngreso){
            
                if (ingreso.getTipo().equals("Ingreso")) {
                    
                    if (ingreso.getObservacion().equals("Tarde")) {
                    
                        ingresosMal++;
                        
                    }else if (ingreso.getObservacion().equals("Temprano")) {
                        
                        ingresosBien++;
                        
                    }else if (ingreso.getObservacion().equals("Justo")) {
                        
                        ingresosJusto++;
                        
                    }
                    
                    
                }else if (ingreso.getTipo().equals("Salida")) {
                    
                    if (ingreso.getObservacion().equals("Tarde")) {
                        ingresosBien++;
                    }else if (ingreso.getObservacion().equals("Temprano")) {
                        ingresosMal++;
                    }else if (ingreso.getObservacion().equals("Justo")) {
                        ingresosJusto++;
                    }
                    
                }
            
            }
            JSONArray  horasSemanaJson = new JSONArray();
            horasSemanaJson.add(totalIngresos);
            horasSemanaJson.add(ingresosBien);
            horasSemanaJson.add(ingresosMal);
            horasSemanaJson.add(ingresosJusto);
            
            response.getWriter().write(horasSemanaJson.toJSONString());
            
        }catch(Exception e){
            response.getWriter().write("500");
            System.err.println(e);
        }
        
    }
    protected void chartSemana(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try{
            
            String Dia = request.getParameter("Dia");
            String Fecha [] = request.getParameter("Fecha").split("/");
            String fechaQuery = "";
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            String horaIngresoLunes = "0.2", horaSalidaLunes = "0.2";
            String horaIngresoMartes = "0.2", horaSalidaMartes = "0.2";
            String horaIngresoMiercoles = "0.2", horaSalidaMiercoles = "0.2";
            String horaIngresoJueves = "0.2", horaSalidaJueves = "0.2";
            String horaIngresoViernes = "0.2", horaSalidaViernes = "0.2";
            String horaIngresoSabado = "0.2", horaSalidaSabado = "0.2";
            String horaIngresoDominngo = "0.2", horaSalidaDomingo = "0.2";            
            
            if (Dia == "Domingo") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-7);                
            }else if (Dia.equals("Lunes")) {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-1);
            }else if (Dia == "Martes") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-2);
            }else if (Dia == "Miercoles") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-3);
            }else if (Dia == "Jueves") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-4);
            }else if (Dia == "Viernes") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-5);
            }else if (Dia == "Sabado") {
                fechaQuery = Fecha[0]+"-"+Fecha[1]+"-"+String.valueOf(Integer.parseInt(Fecha[2])-6);
            }
            
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Ingreso WHERE Usuario = "+objUsuario.getIdUsuario()+" AND Fecha > '"+fechaQuery+"'");
            List<Ingreso> ListaIngreso = query.list();
            for(Ingreso ingreso : ListaIngreso){
                String hora [] = String.valueOf(ingreso.getHora()).split(" ");
                String horaParseada [] = hora[1].split(":");
                
                if (ingreso.getDia().equals("Domingo")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoDominngo = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaDomingo = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                    
                }else if (ingreso.getDia().equals("Lunes")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoLunes = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaLunes = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                    
                }else if (ingreso.getDia().equals("Martes")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoMartes = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaMartes = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                    
                }else if (ingreso.getDia().equals("Miercoles")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoMiercoles = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaMiercoles = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                    
                }else if (ingreso.getDia().equals("Jueves")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoJueves = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaJueves = horaParseada[0]+"."+horaParseada[1]; 
                        
                    }
                    
                }else if (ingreso.getDia().equals("Viernes")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoViernes = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaViernes = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                    
                }else if (ingreso.getDia().equals("Sabado")) {
                    
                    if (ingreso.getTipo().equals("Ingreso")) {
                    
                        horaIngresoSabado = horaParseada[0]+"."+horaParseada[1];
                        
                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        horaSalidaSabado = horaParseada[0]+"."+horaParseada[1];
                        
                    }
                }
             
            }
            JSONArray  horasSemanaJson = new JSONArray();
            horasSemanaJson.add(horaIngresoLunes);
            horasSemanaJson.add(horaSalidaLunes);
            horasSemanaJson.add(horaIngresoMartes);
            horasSemanaJson.add(horaSalidaMartes);
            horasSemanaJson.add(horaIngresoMiercoles);
            horasSemanaJson.add(horaSalidaMiercoles);
            horasSemanaJson.add(horaIngresoJueves);
            horasSemanaJson.add(horaSalidaJueves);
            horasSemanaJson.add(horaIngresoViernes);
            horasSemanaJson.add(horaSalidaViernes);
            horasSemanaJson.add(horaIngresoSabado);
            horasSemanaJson.add(horaSalidaSabado);
            horasSemanaJson.add(horaIngresoDominngo);
            horasSemanaJson.add(horaSalidaDomingo);
            
            response.getWriter().write(horasSemanaJson.toJSONString());
            
        }catch(Exception e){
        
            response.getWriter().write("500");
            System.out.println(e);
        
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
