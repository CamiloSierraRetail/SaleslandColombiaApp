package Controlador;

import Modelo.Ingreso;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                    
                case "promedioingresos":
                    promedioIngresos(request, response);
                    break;
                    
                case "weeklyChart":
                    weeklyChart(request, response);
                    break;
                case "usuariosingresados":
                    usuariosIngresados(request, response);
                    break;
                default:
                    response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/ingreso/ingresousuario.jsp");
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
        String fechaIngreso = "", horaIngreso="", fechaTarde = "", horaTarde="", fechaTemprano = "", horaTemprano="", fechaJusto = "", horaJusto="";
        try{
            int totalIngresos = 0, ingresosBien = 0, ingresosMal = 0, ingresosJusto = 0;
            
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Query query = sesion.createQuery("FROM Ingreso WHERE Usuario = "+objUsuario.getIdUsuario()+"");
            List<Ingreso> ListaIngreso = query.list();
            totalIngresos = ListaIngreso.size();
            if(ListaIngreso.size() > 0){
                for(Ingreso ingreso : ListaIngreso){

                    if (ingreso.getTipo().equals("Ingreso")) {
                        fechaIngreso = String.valueOf(ingreso.getFecha());
                        horaIngreso = String.valueOf(ingreso.getHora());
                        
                        if (ingreso.getObservacion().equals("Tarde")) {
                            ingresosMal++;
                            fechaTarde = String.valueOf(ingreso.getFecha());
                            horaTarde = String.valueOf(ingreso.getHora());
                            
                        }else if (ingreso.getObservacion().equals("Temprano")) {
                            ingresosBien++;                            
                            fechaTemprano = String.valueOf(ingreso.getFecha());
                            horaTemprano = String.valueOf(ingreso.getHora());
                        }else if (ingreso.getObservacion().equals("Justo")) {
                            ingresosJusto++;
                            fechaJusto = String.valueOf(ingreso.getFecha());
                            horaJusto = String.valueOf(ingreso.getHora());
                        }


                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        fechaIngreso = String.valueOf(ingreso.getFecha());
                        horaIngreso = String.valueOf(ingreso.getHora());
                        
                        if (ingreso.getObservacion().equals("Tarde")) {
                            ingresosBien++;
                            fechaTarde = String.valueOf(ingreso.getFecha());
                            horaTarde = String.valueOf(ingreso.getHora());                            
                        }else if (ingreso.getObservacion().equals("Temprano")) {                            
                            ingresosMal++;
                            fechaTemprano = String.valueOf(ingreso.getFecha());
                            horaTemprano = String.valueOf(ingreso.getHora());                            
                        }else if (ingreso.getObservacion().equals("Justo")) {
                            ingresosJusto++;                           
                            fechaJusto = String.valueOf(ingreso.getFecha());
                            horaJusto = String.valueOf(ingreso.getHora());
                        }
                        
                        
                    }
                }

                JSONArray  horasSemanaJson = new JSONArray();
                horasSemanaJson.add(totalIngresos);
                horasSemanaJson.add(ingresosBien);
                horasSemanaJson.add(ingresosMal);
                horasSemanaJson.add(ingresosJusto);

                horasSemanaJson.add(fechaIngreso);
                horasSemanaJson.add(horaIngreso);
                
                horasSemanaJson.add(fechaTemprano);
                horasSemanaJson.add(horaTemprano);
                
                horasSemanaJson.add(fechaTarde);
                horasSemanaJson.add(horaTarde);
                
                horasSemanaJson.add(fechaJusto);
                horasSemanaJson.add(horaJusto);
                
                response.getWriter().write(horasSemanaJson.toJSONString());
            }else{
                response.getWriter().write("undefined");
            }
            
        }catch(Exception e){
            response.getWriter().write("500");
            System.err.println(e);
        }
        
    }
    
    protected void weeklyChart(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try{
            String idusuario = request.getParameter("idusuario");
            String inLunes = "", inMartes = "", inMiercoles = "", inJueves = "", inViernes = "", inSabado = "", inDomingo = "",
                   outLunes = "", outMartes = "", outMiercoles = "", outJueves = "", outViernes = "", outSabado = "", outDomingo = "";
            
            String finalDate = "";
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm");
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm");
            Date dt = new Date();
            LocalDate ld = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-1);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-2);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-3);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-4);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-5);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-6);
            if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) finalDate = ld.getYear()+"-"+"0"+ld.getMonthValue()+"-"+String.valueOf(ld.getDayOfMonth()-7);
            
            Session s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("FROM Ingreso WHERE Usuario = '"+idusuario+"' AND Fecha > '"+finalDate+"'");
            List<Ingreso> listInOut = q.list();
            for (Ingreso item : listInOut) {
                String valorCompleto = String.valueOf(item.getHora());
                String valorDividido[] = valorCompleto.split(" ");
                String hora[] = valorDividido[1].split(":");
                int horaFinal = Integer.valueOf(hora[0]);
                if(item.getTipo().equals("Ingreso")){
                    if(item.getDia().equals("Lunes")){                       
                        if(horaFinal < 12) inLunes = sdf12.format(item.getHora())+" AM";
                        else inLunes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Martes")){
                        if(horaFinal < 12) inMartes = sdf12.format(item.getHora())+" AM";
                        else inMartes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Miercoles")){
                        if(horaFinal < 12) inMiercoles = sdf12.format(item.getHora())+" AM";
                        else inMiercoles = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Jueves")){
                        if(horaFinal < 12) inJueves = sdf12.format(item.getHora())+" AM";
                        else inJueves = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Viernes")){
                        if(horaFinal < 12) inViernes = sdf12.format(item.getHora())+" AM";
                        else inViernes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Sabado")){
                        if(horaFinal < 12) inSabado = sdf12.format(item.getHora())+" AM";
                        else inSabado = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Domingo")){
                        if(horaFinal < 12) inDomingo = sdf12.format(item.getHora())+" AM";
                        else inDomingo = sdf24.format(item.getHora())+" PM";
                    }
                }
                if(item.getTipo().equals("Salida")){
                    if(item.getDia().equals("Lunes")){                       
                        if(horaFinal < 12) outLunes = sdf12.format(item.getHora())+" AM";
                        else outLunes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Martes")){
                        if(horaFinal < 12) outMartes = sdf12.format(item.getHora())+" AM";
                        else outMartes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Miercoles")){
                        if(horaFinal < 12) outMiercoles = sdf12.format(item.getHora())+" AM";
                        else outMiercoles = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Jueves")){
                        if(horaFinal < 12) outJueves = sdf12.format(item.getHora())+" AM";
                        else outJueves = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Viernes")){
                        if(horaFinal < 12) outViernes = sdf12.format(item.getHora())+" AM";
                        else outViernes = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Sabado")){
                        if(horaFinal < 12) outSabado = sdf12.format(item.getHora())+" AM";
                        else outSabado = sdf24.format(item.getHora())+" PM";
                    }
                    if(item.getDia().equals("Domingo")){
                        if(horaFinal < 12) outDomingo = sdf12.format(item.getHora())+" AM";
                        else outDomingo = sdf24.format(item.getHora())+" PM";
                    }
                }
            }
           
            JSONArray jsonIngresos = new JSONArray();
            jsonIngresos.add(inLunes+"/"+outLunes);
            jsonIngresos.add(inMartes+"/"+outMartes);
            jsonIngresos.add(inMiercoles+"/"+outMiercoles);
            jsonIngresos.add(inJueves+"/"+outJueves);
            jsonIngresos.add(inViernes+"/"+outViernes);
            jsonIngresos.add(inSabado+"/"+outSabado);
            jsonIngresos.add(inDomingo+"/"+outDomingo);
            
            response.getWriter().write(jsonIngresos.toJSONString());
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    protected void usuariosIngresados(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
            
//            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
//            
//            Session sesion = HibernateUtil.getSessionFactory().openSession();
//            if (objUsuario.getCargo().getTipo().equals("Director")) {
//                
//                Query query = sesion.createQuery("FROM Sector_Cargo WHERE Cargo = "+objUsuario.getCargo()+"");
//                List<Sector_Cargo> listaSector_Cargo = query.list();
//                for(Sector_Cargo sector_cargo : listaSector_Cargo){
//                
//                    //String usuario = sector_cargo.getSector().get
//                
//                }
//                
//                
//                
//                //Query query = sesion.createQuery("FROM Usuario WHERE Cago = "+objUsuario.get+"")
//                
//                
//            }
            
            
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
