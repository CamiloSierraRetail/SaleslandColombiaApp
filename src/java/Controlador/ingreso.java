package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Ingreso;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
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
                case "cargarPromedioEmpleados" :
                    cargarPromedioEmpleados(request, response);
                    break;
                case "cargarPromedios":
                    cargarPromedios(request, response);
                    
                    break;
                case "cargarChartPeomedioEmpleados":
                    cargarChartPeomedioEmpleados(request, response);
                    
                    break;
                case "cargarChartPromedioDias":
                    cargarChartPromedioDias(request, response);
                    break;
                case "ingresoManual":
                    ingresoManual(request, response);
                    break;
                default:                    
                    response.sendRedirect("/SaleslandColombiaApp/ligth-bootstrap/Pages/alertas/404.jsp");
                    break;
            }
            
        }
        
    }
    protected void usuarioIngreso(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
                    
        try{          
            System.out.println("METODO DE INGRESO, ANTES DE COMENZAR CON LOS ERRORES******************************");
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            String Dia = request.getParameter("Dia");
            System.out.println(Dia);
            String UsuarioID = request.getParameter("UsuarioID");
            String Fecha = request.getParameter("Fecha");
            String hora = request.getParameter("Hora");
            String Minutos = request.getParameter("Minutos");            
            Query query = sesion.createQuery("FROM Usuario WHERE Documento='"+UsuarioID+"'");
            List<Usuario> listaUsuario = query.list();
            response.setCharacterEncoding("UTF-8");            
            
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
                            
                            
                            Ingreso objIngreso = new Ingreso(Dia, new Date(), new Date(), "Ingreso", "Lector", observacionIngreso, usuario.getHorario(), objUsuario);
                            sesion.beginTransaction();
                            sesion.save(objIngreso);
                            sesion.getTransaction().commit();                            


                            response.getWriter().write("Ingreso"+observacionIngreso);
                            
                        }else if(listaIngreso.size() == 1){
                            
                            // HACER LA VALIDACION DE QUE SOLO SE PUEDA MARCAR SALIDA DESPUES DE 5 MINUTOS
                            //DEJAR PENDIENTE PARA CUANDO ESTÉ DISPONIBLE EL LECTO DE CODIGOS DE BARRAS
                            
                            
                                        
                            //Query queryValidarSalida = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+new Date()+"' AND (Hora BETWEEN  )");
                            String spaceSplit [] = String.valueOf(new Date()).split(" ");
                            String horaSplit [] = String.valueOf(spaceSplit[3]).split(":");
                            String horaQuery = "";
                            String minutosQuery = "";

                            if (Integer.parseInt(horaSplit[1]) <= 05) {

                                horaQuery = String.valueOf(Integer.parseInt(horaSplit[0]) - 1);
                                minutosQuery = "55";
                            }else{

                                horaQuery = String.valueOf(Integer.parseInt(horaSplit[0]));
                                minutosQuery = String.valueOf(Integer.parseInt(Minutos) - 5);

                            }
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");System.out.println("######################################");
                            System.out.println(Fecha);
                            
                            Query queryValidarSalida = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+Fecha+"' AND (Hora BETWEEN '"+horaQuery+":"+minutosQuery+"' AND '"+spaceSplit[3]+"')");
                            List<Ingreso> listaIngresoReciente = queryValidarSalida.list();
                            System.out.println("QUERY:  FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+Fecha+"' AND (Hora BETWEEN '"+horaQuery+":"+minutosQuery+"' AND '"+spaceSplit[3]+"')");
                            System.out.println("tamaño del resultado  --->   " + listaIngresoReciente.size());
                            if (listaIngresoReciente.size() == 0) {
                             
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

                                        Ingreso objIngreso = new Ingreso(Dia,new Date(), new Date(), "Salida", "Lector", observacionIngreso, usuario.getHorario(), objUsuario);
                                        sesion.beginTransaction();
                                        sesion.save(objIngreso);
                                        sesion.getTransaction().commit();                                    


                                        response.getWriter().write("Salida"+observacionIngreso);

                                    }

                                }
                                
                            }else{
                            
                                response.getWriter().write("300");
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
            sesion.close();
            objSessionFactory.close();
            
        }catch(Exception e){
            System.err.println(e);
            response.getWriter().write("500");
        }
    }
    protected void promedioIngresos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String fechaUltimoIngreso_Salida = "", horaUltimoIngreso_Salida="", fechaCorrecto = "", horaCorrecto="", fechaErroneo = "", horaErroneo="", fechaJusto = "", horaJusto="";
        
        try{                        
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            
            int totalIngresos = 0, ingresosBien = 0, ingresosMal = 0, ingresosJusto = 0;
            
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            Session sesion = objSessionFactory.openSession();
            Query query = sesion.createQuery("FROM Ingreso WHERE Usuario = "+objUsuario.getIdUsuario()+"");
            List<Ingreso> ListaIngreso = query.list();
            totalIngresos = ListaIngreso.size();
            if(ListaIngreso.size() > 0){
                for(Ingreso ingreso : ListaIngreso){

                    if (ingreso.getTipo().equals("Ingreso")) {
                        fechaUltimoIngreso_Salida = String.valueOf(ingreso.getFecha());
                        horaUltimoIngreso_Salida = String.valueOf(ingreso.getHora());
                        
                        if (ingreso.getObservacion().equals("Tarde")) {
                            ingresosMal++;
                            fechaErroneo = String.valueOf(ingreso.getFecha());
                            horaErroneo = String.valueOf(ingreso.getHora());
                            
                        }else if (ingreso.getObservacion().equals("Temprano")) {
                            ingresosBien++;                            
                            fechaCorrecto = String.valueOf(ingreso.getFecha());
                            horaCorrecto = String.valueOf(ingreso.getHora());
                        }else if (ingreso.getObservacion().equals("Justo")) {
                            ingresosJusto++;
                            fechaJusto = String.valueOf(ingreso.getFecha());
                            horaJusto = String.valueOf(ingreso.getHora());
                        }


                    }else if (ingreso.getTipo().equals("Salida")) {
                        
                        fechaUltimoIngreso_Salida = String.valueOf(ingreso.getFecha());
                        horaUltimoIngreso_Salida = String.valueOf(ingreso.getHora());
                        
                        if (ingreso.getObservacion().equals("Tarde")) {
                            ingresosBien++;
                            fechaCorrecto = String.valueOf(ingreso.getFecha());
                            horaCorrecto = String.valueOf(ingreso.getHora());                            
                        }else if (ingreso.getObservacion().equals("Temprano")) {                            
                            ingresosMal++;
                            fechaErroneo = String.valueOf(ingreso.getFecha());
                            horaErroneo = String.valueOf(ingreso.getHora());                            
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

                horasSemanaJson.add(fechaUltimoIngreso_Salida);
                horasSemanaJson.add(horaUltimoIngreso_Salida);
                
                horasSemanaJson.add(fechaCorrecto);
                horasSemanaJson.add(horaCorrecto);
                
                horasSemanaJson.add(fechaErroneo);
                horasSemanaJson.add(horaErroneo);
                
                horasSemanaJson.add(fechaJusto);
                horasSemanaJson.add(horaJusto);
                
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(horasSemanaJson.toJSONString());
            }else{
                response.getWriter().write("undefined");
            }
            sesion.close();
            objSessionFactory.close();
        }catch(Exception e){
            response.getWriter().write("500");
            System.err.println(e);
        }
        
    }
    
    protected void weeklyChart(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {                        
        
        try{       
            SessionFactory objSessionFactory;                        
            objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();                               
            
            String tipo = "", tipo1 = "";
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
            
            Session s = objSessionFactory.openSession();
            Query q = s.createQuery("FROM Ingreso WHERE Usuario = '"+idusuario+"' AND Fecha > '"+finalDate+"'");
            List<Ingreso> listInOut = q.list();
            for (Ingreso item : listInOut) {
                String valorCompleto = String.valueOf(item.getHora());
                String valorDividido[] = valorCompleto.split(" ");
                String hora[] = valorDividido[1].split(":");                
                int horaFinal = Integer.valueOf(hora[0]);
                if(item.getTipo().equals("Ingreso")){
                    tipo="Ingreso";
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
                    tipo1="Salida";
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
            jsonIngresos.add(inLunes+"/"+tipo+"/"+outLunes+"/"+tipo1);
            jsonIngresos.add(inMartes+"/"+tipo+"/"+outMartes+"/"+tipo1);
            jsonIngresos.add(inMiercoles+"/"+tipo+"/"+outMiercoles+"/"+tipo1);
            jsonIngresos.add(inJueves+"/"+tipo+"/"+outJueves+"/"+tipo1);
            jsonIngresos.add(inViernes+"/"+tipo+"/"+outViernes+"/"+tipo1);
            jsonIngresos.add(inSabado+"/"+tipo+"/"+outSabado+"/"+tipo1);
            jsonIngresos.add(inDomingo+"/"+tipo+"/"+outDomingo+"/"+tipo1);
            
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonIngresos.toJSONString());
            
            s.close();
            objSessionFactory.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
        
    protected void cargarPromedioEmpleados(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
    
            String UsuariosQuery = "";            
            int contador = 0;
            int countRows = 1;
            List<Usuario> listaUsuario = null;     
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            String promedioEntradaA = "00:00:00", promedioSalidaA = "00:00:00", promedioEntradaB = "00:00:00", promedioSalidaB = "00:00:00";
            
            
            /////////////////////////////// DIRECTOR ////////////////////////////////////////////////////////////
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    listaUsuario = buscarUsuario(sector_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                    
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector="+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                        
                            listaUsuario.addAll(buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal="+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){
                        
                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area = "+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){
                            
                                listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                            }
                        
                        }
                    
                    }
                    
                }
                
                
                
                for(Usuario usuario : listaUsuario){
            
                    if (contador == 0) {
                        
                        UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                        contador++;
                        
                    }else{
                    
                        UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";
                    
                    }
                    countRows++;
                }
                
                
                JSONArray usuarioJson = new JSONArray();
                
                System.out.println("LA QUERY DEL ERROR ES:                        " + "SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'A' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                
                if (UsuariosQuery != "") {
                    
                    Query queryPromedioIngresoUsuario_A = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'A' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_A = queryPromedioIngresoUsuario_A.list();                
                    for (Object datos : listaIngreso_A) {

                        if (datos != null) {
                            promedioEntradaA = datos.toString();
                        }

                    }

                    Query queryPromedioIngresoUsuario_B = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'B' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_B = queryPromedioIngresoUsuario_B.list();
                    for (Object datos : listaIngreso_B) {

                        if (datos != null) {

                            promedioEntradaB = datos.toString();

                        }

                    }

                    Query queryPromedioSalidaUsuario_A = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'A' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_A = queryPromedioSalidaUsuario_A.list();
                    for (Object datos : listaSalida_A) {

                        if (datos != null) {

                            promedioSalidaA = datos.toString();

                        }


                    }

                    Query queryPromedioSalidaUsuario_B = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'B' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_B = queryPromedioSalidaUsuario_B.list();
                    for (Object datos : listaSalida_B) {

                        if (datos != null) {

                            promedioSalidaB = datos.toString();

                        }

                    }
                    String horas_A = "0", horas_B = "0";
                    ////////////////////_____________________________QUERY'S XD PARA SACAR LAS HORAS TRABAJADAS______________ //////////////////////////
                    Query queryHorasTrabajadas_A = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaA+"', '2003-05-01 "+promedioSalidaA+"')");
                    List<Object> Horas_A = queryHorasTrabajadas_A.list();
                    for (Object datos : Horas_A) {

                        if (datos != null) {

                            horas_A = datos.toString();
                        }

                    }

                    Query queryHorasTrabajadas_B = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaB+"', '2003-05-01 "+promedioSalidaB+"')");
                    List<Object> Horas_B = queryHorasTrabajadas_B.list();
                    for (Object datos : Horas_B) {

                        if (datos != null) {

                            horas_B = datos.toString();

                        }

                    }

                    usuarioJson.add(promedioEntradaA);
                    usuarioJson.add(promedioEntradaB);
                    usuarioJson.add(promedioSalidaA);
                    usuarioJson.add(promedioSalidaB);
                    usuarioJson.add(horas_A);
                    usuarioJson.add(horas_B);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(usuarioJson.toJSONString());
                    
                }else{
                
                    response.getWriter().write("204");
                
                }
                
                
                
                
                
            ///////////////////////////// JEFE DE CANAL - COORDINADOR DE CANAL ////////////////////////////////////////////////////////////
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
                Query queryCanalCargo1 = sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objUsuario.getCargo().getIdCargo()+"");
                List<Canal_Cargo> listaCanal_Cargo1 = queryCanalCargo1.list();
                for(Canal_Cargo canal_cargo1 : listaCanal_Cargo1){
                
                    Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                    
                    for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                        
                        if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getTipo().equals("JefeCanal")) {
                            
                            ///////////////////////////// ELIMINAR ESTE IF, OPTIMIZAR CODE /////////////////////////////
                            System.out.println("jejefesote xd alv");
                            
                        }else{
                        
                            List<Usuario> listaUsuariosBuscados = buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                        
                            if (listaUsuario == null) {

                                listaUsuario = listaUsuariosBuscados;

                            }else{
                                listaUsuario.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    System.out.println("AREA AREA AREa aREA AREA arEAR arEA ARAEARARAERAEARAEARAEAREARAEARAE          " + listaArea.size());
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));

                        }

                    }
                    
                }
                
                for(Usuario usuario : listaUsuario){
            
                    if (contador == 0) {
                        
                        UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                        contador++;
                        
                    }else{
                    
                        UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";
                    
                    }
                    countRows++;
                }
                
                
                if (UsuariosQuery != "") {
                    
                    JSONArray usuarioJson = new JSONArray();
                
                    Query queryPromedioIngresoUsuario_A = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'A' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_A = queryPromedioIngresoUsuario_A.list();                
                    for (Object datos : listaIngreso_A) {

                        if (datos != null) {
                            promedioEntradaA = datos.toString();
                        }

                    }

                    Query queryPromedioIngresoUsuario_B = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'B' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_B = queryPromedioIngresoUsuario_B.list();
                    for (Object datos : listaIngreso_B) {

                        if (datos != null) {

                            promedioEntradaB = datos.toString();

                        }

                    }

                    Query queryPromedioSalidaUsuario_A = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'A' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_A = queryPromedioSalidaUsuario_A.list();
                    for (Object datos : listaSalida_A) {

                        if (datos != null) {

                            promedioSalidaA = datos.toString();

                        }


                    }

                    Query queryPromedioSalidaUsuario_B = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'B' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_B = queryPromedioSalidaUsuario_B.list();
                    for (Object datos : listaSalida_B) {

                        if (datos != null) {

                            promedioSalidaB = datos.toString();

                        }

                    }
                    String horas_A = "0", horas_B = "0";
                    ////////////////////_____________________________QUERY'S XD PARA SACAR LAS HORAS TRABAJADAS______________ //////////////////////////
                    Query queryHorasTrabajadas_A = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaA+"', '2003-05-01 "+promedioSalidaA+"')");
                    List<Object> Horas_A = queryHorasTrabajadas_A.list();
                    for (Object datos : Horas_A) {

                        if (datos != null) {

                            horas_A = datos.toString();
                        }

                    }

                    Query queryHorasTrabajadas_B = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaB+"', '2003-05-01 "+promedioSalidaB+"')");
                    List<Object> Horas_B = queryHorasTrabajadas_B.list();
                    for (Object datos : Horas_B) {

                        if (datos != null) {

                            horas_B = datos.toString();

                        }

                    }

                    usuarioJson.add(promedioEntradaA);
                    usuarioJson.add(promedioEntradaB);
                    usuarioJson.add(promedioSalidaA);
                    usuarioJson.add(promedioSalidaB);
                    usuarioJson.add(horas_A);
                    usuarioJson.add(horas_B);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(usuarioJson.toJSONString());
                    
                }else{
                    response.getWriter().write("204");
                }
                
                
                
                
                
            /////////////////////////////// JEFE DE AREA ////////////////////////////////////////////////////////////
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
            
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                        
                        
                        List<Usuario> listaUsuariosBuscados = buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                            
                        if (listaUsuario == null) {

                            listaUsuario = listaUsuariosBuscados;

                        }else{
                            listaUsuario.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }
                
                for(Usuario usuario : listaUsuario){
            
                    if (contador == 0) {
                        
                        UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                        contador++;
                        
                    }else{
                    
                        UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";
                    
                    }
                    countRows++;
                }
                
                if (UsuariosQuery != "") {
                    
                    JSONArray usuarioJson = new JSONArray();
                
                    Query queryPromedioIngresoUsuario_A = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'A' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_A = queryPromedioIngresoUsuario_A.list();                
                    for (Object datos : listaIngreso_A) {

                        if (datos != null) {
                            promedioEntradaA = datos.toString();
                        }

                    }

                    Query queryPromedioIngresoUsuario_B = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'B' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_B = queryPromedioIngresoUsuario_B.list();
                    for (Object datos : listaIngreso_B) {

                        if (datos != null) {

                            promedioEntradaB = datos.toString();

                        }

                    }

                    Query queryPromedioSalidaUsuario_A = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'A' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_A = queryPromedioSalidaUsuario_A.list();
                    for (Object datos : listaSalida_A) {

                        if (datos != null) {

                            promedioSalidaA = datos.toString();

                        }


                    }

                    Query queryPromedioSalidaUsuario_B = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'B' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_B = queryPromedioSalidaUsuario_B.list();
                    for (Object datos : listaSalida_B) {

                        if (datos != null) {

                            promedioSalidaB = datos.toString();

                        }

                    }
                    String horas_A = "0", horas_B = "0";
                    ////////////////////_____________________________QUERY'S XD PARA SACAR LAS HORAS TRABAJADAS______________ //////////////////////////
                    Query queryHorasTrabajadas_A = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaA+"', '2003-05-01 "+promedioSalidaA+"')");
                    List<Object> Horas_A = queryHorasTrabajadas_A.list();
                    for (Object datos : Horas_A) {

                        if (datos != null) {

                            horas_A = datos.toString();
                        }

                    }

                    Query queryHorasTrabajadas_B = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaB+"', '2003-05-01 "+promedioSalidaB+"')");
                    List<Object> Horas_B = queryHorasTrabajadas_B.list();
                    for (Object datos : Horas_B) {

                        if (datos != null) {

                            horas_B = datos.toString();

                        }

                    }

                    usuarioJson.add(promedioEntradaA);
                    usuarioJson.add(promedioEntradaB);
                    usuarioJson.add(promedioSalidaA);
                    usuarioJson.add(promedioSalidaB);
                    usuarioJson.add(horas_A);
                    usuarioJson.add(horas_B);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(usuarioJson.toJSONString());
                    
                }else{
                
                    response.getWriter().write("204");
                
                }
            }else if (objUsuario.getCargo().getTipo().equals("Recepcion")) {
                
                Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE idUsuario != "+objUsuario.getIdUsuario()+"");                
                listaUsuario = queryUsuarios.list();
                
                
                for(Usuario usuario : listaUsuario){
            
                    if (contador == 0) {
                        
                        UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                        contador++;
                        
                    }else{
                    
                        UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";
                    
                    }
                    countRows++;
                }
                
                if (UsuariosQuery != "") {
                    
                    JSONArray usuarioJson = new JSONArray();
                
                    Query queryPromedioIngresoUsuario_A = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'A' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_A = queryPromedioIngresoUsuario_A.list();                
                    for (Object datos : listaIngreso_A) {

                        if (datos != null) {
                            promedioEntradaA = datos.toString();
                        }

                    }

                    Query queryPromedioIngresoUsuario_B = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE (Horario = 'B' AND Tipo = 'Ingreso' AND ("+UsuariosQuery+"))");
                    List<Object> listaIngreso_B = queryPromedioIngresoUsuario_B.list();
                    for (Object datos : listaIngreso_B) {

                        if (datos != null) {

                            promedioEntradaB = datos.toString();

                        }

                    }

                    Query queryPromedioSalidaUsuario_A = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'A' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_A = queryPromedioSalidaUsuario_A.list();
                    for (Object datos : listaSalida_A) {

                        if (datos != null) {

                            promedioSalidaA = datos.toString();

                        }


                    }

                    Query queryPromedioSalidaUsuario_B = sesion.createSQLQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM ingreso WHERE (Horario = 'B' AND Tipo = 'Salida' AND ("+UsuariosQuery+"))");
                    List<Object> listaSalida_B = queryPromedioSalidaUsuario_B.list();
                    for (Object datos : listaSalida_B) {

                        if (datos != null) {

                            promedioSalidaB = datos.toString();

                        }

                    }
                    String horas_A = "0", horas_B = "0";
                    ////////////////////_____________________________QUERY'S XD PARA SACAR LAS HORAS TRABAJADAS______________ //////////////////////////
                    Query queryHorasTrabajadas_A = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaA+"', '2003-05-01 "+promedioSalidaA+"')");
                    List<Object> Horas_A = queryHorasTrabajadas_A.list();
                    for (Object datos : Horas_A) {

                        if (datos != null) {

                            horas_A = datos.toString();
                        }

                    }

                    Query queryHorasTrabajadas_B = sesion.createSQLQuery("SELECT TIMESTAMPDIFF(HOUR, '2003-05-01 "+promedioEntradaB+"', '2003-05-01 "+promedioSalidaB+"')");
                    List<Object> Horas_B = queryHorasTrabajadas_B.list();
                    for (Object datos : Horas_B) {

                        if (datos != null) {

                            horas_B = datos.toString();

                        }

                    }

                    usuarioJson.add(promedioEntradaA);
                    usuarioJson.add(promedioEntradaB);
                    usuarioJson.add(promedioSalidaA);
                    usuarioJson.add(promedioSalidaB);
                    usuarioJson.add(horas_A);
                    usuarioJson.add(horas_B);
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(usuarioJson.toJSONString());
                
                }else{
                
                    response.getWriter().write("204");
                
                }
                
            }
            sesion.close();
            objSessionFactory.close();
        }catch(HibernateException ex){
        
            response.getWriter().write("500");
            System.err.println(ex);
        
        }
    
    }
    
    //////// METODO PARA BUSCAR A LOS USUARIOS QUE SE VAN A LISTAR /////////
    private List<Usuario> buscarUsuario(int cargo, int usuarioEnSesion){
    
        List<Usuario> listaUsuarios = null;
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            Query queryUsuario = sesion.createQuery("FROM Usuario WHERE Cargo="+cargo+" AND idUsuario != "+usuarioEnSesion+"");            
            listaUsuarios = queryUsuario.list();
            sesion.close();
            objSessionFactory.close();
        }catch(HibernateException ex){
        
            System.err.println(ex);
        }
        return listaUsuarios;
    }
    
    
    ///////////////////////////// METODO PARA CARGAR LOS PROMEDIO DE ENTRADA Y SALIDA DE LOS USUARIOS ////////////////////
    protected void cargarPromedios(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try{
        
            // HORARIO A O B
            String tipoHorario = request.getParameter("Horario");            
            // LA ACCION PUEDE SER ENTRADA Y SALIDA
            String accion = request.getParameter("Accion");
            String orden = "";
            if (accion.equals("Ingreso")) {
            
                orden="DESC";
            }else{
                orden = "ASC";
            }
            
            String UsuariosQuery = "";            
            int contador = 0;
            int countRows = 1;
            List<Usuario> listaUsuario = null;     
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            response.setCharacterEncoding("UTF-8");
            
            /////////////////////////////// DIRECTOR ////////////////////////////////////////////////////////////
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    listaUsuario = buscarUsuario(sector_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                    
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector="+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                        
                            listaUsuario.addAll(buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal="+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){
                        
                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area = "+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){                                                            
                                
                                listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                            }
                        
                        }
                    
                    }
                    
                }
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                               
                Query queryCanalCargo1 = sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objUsuario.getCargo().getIdCargo()+"");
                List<Canal_Cargo> listaCanal_Cargo1 = queryCanalCargo1.list();
                for(Canal_Cargo canal_cargo1 : listaCanal_Cargo1){
                
                    Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                    
                    for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                        
                        if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getTipo().equals("JefeCanal")) {
                            
                            System.out.println("jejefesote xd alv");
                            
                        }else{
                        
                            List<Usuario> listaUsuariosBuscados = buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                        
                            if (listaUsuario == null) {

                                listaUsuario = listaUsuariosBuscados;

                            }else{
                                listaUsuario.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));

                        }

                    }
                    
                }
                
               
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                                
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                        
                        
                        List<Usuario> listaUsuariosBuscados = buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                            
                        if (listaUsuario == null) {

                            listaUsuario = listaUsuariosBuscados;

                        }else{
                            listaUsuario.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }                
                
            }else if (objUsuario.getCargo().getTipo().equals("Recepcion")) {
            
                Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE idUsuario != "+objUsuario.getIdUsuario()+"");
                listaUsuario = queryUsuarios.list();
            }
            
            
            for(Usuario usuario : listaUsuario){

                if (contador == 0) {

                    UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                    contador++;

                }else{

                    UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";

                }
                countRows++;
            }


            JSONArray usuarioJson = new JSONArray();                
            Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE (Horario = '"+tipoHorario+"' AND Tipo = '"+accion+"' AND ("+UsuariosQuery+")) GROUP BY Usuario ORDER BY SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) "+orden+"");
            queryIngreso.setMaxResults(4);
            List<Ingreso> listaIngreso = queryIngreso.list();
            for(Ingreso ingreso : listaIngreso){

                String nombre [] = ingreso.getUsuario().getNombre().split(" ");
                String apellido [] = ingreso.getUsuario().getApellido().split(" ");

                usuarioJson.add(ingreso.getUsuario().getIdUsuario());
                usuarioJson.add(ingreso.getUsuario().getFoto());
                usuarioJson.add(nombre[0]);
                usuarioJson.add(apellido[0]);

                Query queryPromedioUsuario = sesion.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE Tipo='"+accion+"' AND Usuario = "+ingreso.getUsuario().getIdUsuario()+"");                    
                List<Object> promedioUsuario = queryPromedioUsuario.list();
                for (Object datos : promedioUsuario) {

                    usuarioJson.add(datos.toString());

                }

            }

            response.getWriter().write(usuarioJson.toJSONString());
            
            
            
            ////////////// PENDIENTE POR HACER LOS OTROS USUARIOS
            sesion.close();
            objSessionFactory.close();
        }catch(Exception ex){
        
            response.getWriter().write("500");
            System.err.println(ex);
        }
    
    }
    protected void cargarChartPeomedioEmpleados(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
            String Accion = request.getParameter("Accion");
            String Horario = request.getParameter("Horario");
            String UsuariosQuery = "";            
            int contador = 0;
            int countRows = 1;
            List<Usuario> listaUsuario = null;     
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            /////////////////////////////// DIRECTOR ////////////////////////////////////////////////////////////
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    listaUsuario = buscarUsuario(sector_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                    
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector="+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                        
                            listaUsuario.addAll(buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal="+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){
                        
                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area = "+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){
                                                            
                                listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                            }
                        
                        }
                    
                    }
                    
                }
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                               
                Query queryCanalCargo1 = sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objUsuario.getCargo().getIdCargo()+"");
                List<Canal_Cargo> listaCanal_Cargo1 = queryCanalCargo1.list();
                for(Canal_Cargo canal_cargo1 : listaCanal_Cargo1){
                
                    Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                    
                    for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                        
                        if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getTipo().equals("JefeCanal")) {
                            
                            System.out.println("jejefesote xd alv");
                            
                        }else{
                        
                            List<Usuario> listaUsuariosBuscados = buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                        
                            if (listaUsuario == null) {

                                listaUsuario = listaUsuariosBuscados;

                            }else{
                                listaUsuario.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));

                        }

                    }
                    
                }
                
               
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                                
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                        
                        
                        List<Usuario> listaUsuariosBuscados = buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                            
                        if (listaUsuario == null) {

                            listaUsuario = listaUsuariosBuscados;

                        }else{
                            listaUsuario.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }                
                
            }else if (objUsuario.getCargo().getTipo().equals("Recepcion")) {
                
                Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE idUsuario != "+objUsuario.getIdUsuario()+"");                
                listaUsuario = queryUsuarios.list();
                
            }
        
            for(Usuario usuario : listaUsuario){
            
                if (contador == 0) {

                    UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                    contador++;

                }else{

                    UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";

                }
                countRows++;
            }
            int temprano = 0, tarde = 0, justo = 0;
            JSONArray usuarioJson = new JSONArray();
            Query queryPromedioChartUsuarios = sesion.createQuery("SELECT Observacion ,COUNT(Observacion) FROM Ingreso WHERE Tipo = '"+Accion+"' AND Horario = '"+Horario+"' AND("+UsuariosQuery+") GROUP BY Observacion");
            List<Object[]> listaIngreso = queryPromedioChartUsuarios.list();
            for (Object[] datos : listaIngreso) {

                if (datos[0].equals("Tarde")) {
                    tarde = Integer.parseInt(String.valueOf(datos[1]));
                }else if (datos[0].equals("Temprano")) {
                    temprano = Integer.parseInt(String.valueOf(datos[1]));
                }else if (datos[0].equals("Justo")) {
                    justo = Integer.parseInt(String.valueOf(datos[1]));
                }
                
                //usuarioJson.add(datos);
                System.out.println("Los datos para cargar el chart son:     ??¡¡¡¡¡¡¡¡¡¡¡??????¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿¿¿¿¿    ->>>>>>>    " + datos[0] + "        " + datos[1]);

            }
            usuarioJson.add(justo);
            usuarioJson.add(tarde);
            usuarioJson.add(temprano);
            sesion.close();
            objSessionFactory.close();
            
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(usuarioJson.toJSONString());
            
        }catch(Exception ex){
        
            System.err.println(ex);
            response.getWriter().write("500");
        }
    }
    
    protected void cargarChartPromedioDias(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        try{
            JSONArray usuarioJson = new JSONArray();
            String UsuariosQuery = "";            
            int contador = 0;
            int countRows = 1;
            List<Usuario> listaUsuario = null;     
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            /////////////////////////////// DIRECTOR ////////////////////////////////////////////////////////////
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector_Cargo.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    listaUsuario = buscarUsuario(sector_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                    
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector="+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                        
                            listaUsuario.addAll(buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal="+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){
                        
                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area = "+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){
                                                            
                                listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));
                            
                            }
                        
                        }
                    
                    }
                    
                }
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                               
                Query queryCanalCargo1 = sesion.createQuery("FROM Canal_Cargo WHERE Cargo = "+objUsuario.getCargo().getIdCargo()+"");
                List<Canal_Cargo> listaCanal_Cargo1 = queryCanalCargo1.list();
                for(Canal_Cargo canal_cargo1 : listaCanal_Cargo1){
                
                    Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                    
                    for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                        
                        if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getTipo().equals("JefeCanal")) {
                            
                            System.out.println("jejefesote xd alv");
                            
                        }else{
                        
                            List<Usuario> listaUsuariosBuscados = buscarUsuario(canal_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                        
                            if (listaUsuario == null) {

                                listaUsuario = listaUsuariosBuscados;

                            }else{
                                listaUsuario.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuario.addAll(buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario()));

                        }

                    }
                    
                }                            
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                                
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                        
                        
                        List<Usuario> listaUsuariosBuscados = buscarUsuario(area_cargo.getCargo().getIdCargo(), objUsuario.getIdUsuario());
                            
                        if (listaUsuario == null) {

                            listaUsuario = listaUsuariosBuscados;

                        }else{
                            listaUsuario.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }                
                
            }else if (objUsuario.getCargo().getTipo().equals("Recepcion")) {
                
                Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE idUsuario != "+objUsuario.getIdUsuario()+"");                
                listaUsuario = queryUsuarios.list();
                
            }
        
            for(Usuario usuario : listaUsuario){
            
                if (contador == 0) {

                    UsuariosQuery = "Usuario = "+usuario.getIdUsuario()+"";
                    contador++;

                }else{

                    UsuariosQuery = UsuariosQuery + " OR Usuario = "+usuario.getIdUsuario()+"";

                }
                countRows++;
            }
            
            Query queryPromedioDias_A = sesion.createQuery("SELECT Horario, Dia, Tipo, SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE Horario = 'A' AND ("+UsuariosQuery+") GROUP BY Dia, Tipo");
            List<Object[]> listaPromedioDias_A = queryPromedioDias_A.list();
            
            Query queryPromedioDias_B = sesion.createQuery("SELECT Horario, Dia, Tipo, SEC_TO_TIME(AVG(TIME_TO_SEC(Hora))) as Promedio_Ingreso FROM Ingreso WHERE Horario = 'B' AND ("+UsuariosQuery+") GROUP BY Dia, Tipo");
            List<Object[]> listaPromedioDias_B = queryPromedioDias_B.list();
           
            for (Object[] datos : listaPromedioDias_A) {
                
                usuarioJson.add(datos[0] + " " + datos[1]+ " " + datos[2]+ " " + datos[3]);
            }
            
            for (Object[] datos : listaPromedioDias_B) {
                
                usuarioJson.add(datos[0] + " " + datos[1]+ " " + datos[2]+ " " + datos[3]);
            }
                        
            sesion.close();
            objSessionFactory.close();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(usuarioJson.toJSONString());
            
        }catch(Exception ex){
        
            response.getWriter().write("500");
            System.out.println(ex);
        }
    
    }
    protected void ingresoManual(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       
        try{
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            
            
            
            
            
            String Dia = request.getParameter("Dia");            
            String UsuarioID = request.getParameter("UsuarioID");
            String Fecha = request.getParameter("Fecha");
            String hora = request.getParameter("Hora");
            String Minutos = request.getParameter("Minutos");
            String horaMilitar = request.getParameter("HoraMilitar");            
            Query query = sesion.createQuery("FROM Usuario WHERE Documento='"+UsuarioID+"'");
            List<Usuario> listaUsuario = query.list();
            response.setCharacterEncoding("UTF-8");            
            
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
                            
                            
                            sesion.beginTransaction();
                            Query queryIngresoManual = sesion.createSQLQuery("INSERT INTO ingreso (idIngreso, Dia, Fecha, Hora, Tipo, Modalidad, Observacion, Horario, Usuario) VALUES (NULL, '"+Dia+"', '"+Fecha+"', '"+horaMilitar+"', 'Ingreso', 'Manual', '"+observacionIngreso+"', '"+usuario.getHorario()+"', "+objUsuario.getIdUsuario()+")");
                            queryIngresoManual.executeUpdate();
                            
                            
                            //Ingreso objIngreso = new Ingreso(Dia, new Date(), new Date(), "Ingreso", "Lector", observacionIngreso, usuario.getHorario(), objUsuario);
                            //sesion.beginTransaction();
                            //sesion.save(objIngreso);
                            sesion.getTransaction().commit();                            


                            response.getWriter().write("Ingreso"+observacionIngreso);
                            
                        }else if(listaIngreso.size() == 1){
                            
                            // HACER LA VALIDACION DE QUE SOLO SE PUEDA MARCAR SALIDA DESPUES DE 5 MINUTOS
                            //DEJAR PENDIENTE PARA CUANDO ESTÉ DISPONIBLE EL LECTO DE CODIGOS DE BARRAS
                            
                            
                                        
                            //Query queryValidarSalida = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+new Date()+"' AND (Hora BETWEEN  )");
                            String spaceSplit [] = String.valueOf(new Date()).split(" ");
                            String horaSplit [] = String.valueOf(spaceSplit[3]).split(":");
                            String horaQuery = "";
                            String minutosQuery = "";

                            if (Integer.parseInt(horaSplit[1]) <= 05) {

                                horaQuery = String.valueOf(Integer.parseInt(horaSplit[0]) - 1);
                                minutosQuery = "55";
                            }else{

                                horaQuery = String.valueOf(Integer.parseInt(horaSplit[0]));
                                minutosQuery = String.valueOf(Integer.parseInt(Minutos) - 5);

                            }
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");
                            System.out.println("######################################");System.out.println("######################################");
                            System.out.println(Fecha);
                            
                            Query queryValidarSalida = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+Fecha+"' AND (Hora BETWEEN '"+horaQuery+":"+minutosQuery+"' AND '"+spaceSplit[3]+"')");
                            List<Ingreso> listaIngresoReciente = queryValidarSalida.list();
                            System.out.println("QUERY:  FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+Fecha+"' AND (Hora BETWEEN '"+horaQuery+":"+minutosQuery+"' AND '"+spaceSplit[3]+"')");
                            System.out.println("tamaño del resultado  --->   " + listaIngresoReciente.size());
                            if (listaIngresoReciente.size() == 0) {
                             
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

                                        
                                        
                                        sesion.beginTransaction();
                                        Query queryIngresoManual = sesion.createSQLQuery("INSERT INTO ingreso (idIngreso, Dia, Fecha, Hora, Tipo, Modalidad, Observacion, Horario, Usuario) VALUES (NULL, '"+Dia+"', '"+Fecha+"', '"+horaMilitar+"', 'Salida', 'Manual', '"+observacionIngreso+"', '"+usuario.getHorario()+"', "+objUsuario.getIdUsuario()+")");
                                        queryIngresoManual.executeUpdate();
                                        
                                        //Ingreso objIngreso = new Ingreso(Dia,new Date(), new Date(), "Salida", "Lector", observacionIngreso, usuario.getHorario(), objUsuario);
                                        //sesion.beginTransaction();
                                        //sesion.save(objIngreso);
                                        sesion.getTransaction().commit();                                    


                                        response.getWriter().write("Salida"+observacionIngreso);

                                    }

                                }
                                
                            }else{
                            
                                response.getWriter().write("300");
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
            
            
            
            
            
            
            
            
            
            sesion.close();
            objSessionFactory.close();        
        }catch(Exception ex){
            System.err.println(ex);
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
