package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Ingreso;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.json.simple.JSONArray;


public class accionesSocket {
    
    JSONArray usuarioJson = new JSONArray();
    public String mostrarEmpleados(int idUsuario, String fecha){
    
        List<Usuario> listaUsuarios = null;        
        usuarioJson.add("muchos");        
        SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        
        try{
            System.out.println("TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY TRY ");            
            System.out.println("pasó el open session factory AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            Session sesion = objSessionFactory.openSession();
            Usuario objUsuario = (Usuario)sesion.createQuery("FROM Usuario WHERE idUsuario="+idUsuario+"").uniqueResult();
            
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Sector_Cargo> listaSector_Cargo = querySector.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                                  
                    List<Usuario> listaUsuariosBuscados = getUsuarios(idUsuario, sector_cargo.getCargo().getIdCargo());                    
                    if (listaUsuarios == null) {

                        listaUsuarios = listaUsuariosBuscados;

                    }else{
                        listaUsuarios.addAll(listaUsuariosBuscados);

                    }

                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector = "+sector_cargo.getSector().getIdSector()+"");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                                            
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal.getIdCanal()+"");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                            
                            listaUsuarios.addAll(getUsuarios(idUsuario, canal_cargo.getCargo().getIdCargo()));                                                        
                        
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal.getIdCanal()+"");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){

                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){

                                listaUsuarios.addAll(getUsuarios(idUsuario, area_cargo.getCargo().getIdCargo()));

                            }

                        }
                       
                    }
                
                }
                  
                getJsonUsuarios(listaUsuarios, fecha);
                    
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
                        
                            List<Usuario> listaUsuariosBuscados = getUsuarios(idUsuario, canal_cargo.getCargo().getIdCargo());
                        
                            if (listaUsuarios == null) {

                                listaUsuarios = listaUsuariosBuscados;

                            }else{
                                listaUsuarios.addAll(listaUsuariosBuscados);

                            }
                            
                        }
                        
                    }

                    Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal_cargo1.getCanal().getIdCanal()+"");
                    List<Area> listaArea = queryArea.list();
                    for(Area area : listaArea){

                        Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                        List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                        for(Area_Cargo area_cargo : listaArea_Cargo){

                            listaUsuarios.addAll(getUsuarios(idUsuario, area_cargo.getCargo().getIdCargo()));

                        }

                    }
                    
                }
                
                getJsonUsuarios(listaUsuarios, fecha);
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
               
                Query queryArea_Cargo1 = sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                List<Area_Cargo> listaArea_Cargo1 = queryArea_Cargo1.list();
                for(Area_Cargo area_cargo1 : listaArea_Cargo1){
                                                 
                    Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area_cargo1.getArea().getIdArea()+"");
                    List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                    for(Area_Cargo area_cargo : listaArea_Cargo){
                                                
                        
                        List<Usuario> listaUsuariosBuscados = getUsuarios(idUsuario, area_cargo.getCargo().getIdCargo());
                            
                        if (listaUsuarios == null) {

                            listaUsuarios = listaUsuariosBuscados;

                        }else{
                            listaUsuarios.addAll(listaUsuariosBuscados);

                        }
                         
                    }
                
                }
                getJsonUsuarios(listaUsuarios, fecha);
                
            }else if (objUsuario.getCargo().getTipo().equals("Recepcion")) {
                
                Query queryUsuariosRecepcion = sesion.createQuery("FROM Usuario WHERE idUsuario != "+objUsuario.getIdUsuario()+"");
                List<Usuario> listaUsuariosRecepcion = queryUsuariosRecepcion.list();
                
                for(Usuario usuarios : listaUsuariosRecepcion){
                
                    String entrada = "Ingreso: 1970-01-0 00:00:00", salida = "Salida: 1970-01-0 00:00:00";

                    Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuarios.getIdUsuario()+" AND Fecha='"+fecha+"'");
                    List<Ingreso> listaIngreso = queryIngreso.list();

                    int contador = 0;

                    if (listaIngreso.size() == 1) {

                        for(Ingreso ingreso : listaIngreso){

                            if (ingreso.getTipo().equals("Ingreso")) {

                                entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                            }else if (ingreso.getTipo().equals("Salida")) {


                                salida = "Salida: " + String.valueOf(ingreso.getHora());
                            }

                            usuarioJson.add(usuarios.getIdUsuario());
                            usuarioJson.add(usuarios.getNombre());
                            usuarioJson.add(usuarios.getApellido());
                            usuarioJson.add(usuarios.getFoto());
                            usuarioJson.add(entrada);
                            usuarioJson.add(salida);

                        }

                    }else if (listaIngreso.size() == 2) {

                        for(Ingreso ingreso : listaIngreso){

                            if (contador == 0) {
                                if (ingreso.getTipo().equals("Ingreso")) {

                                    entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                                }

                            }else if (contador == 1) {

                                if (ingreso.getTipo().equals("Salida")) {


                                    salida = "Salida: " + String.valueOf(ingreso.getHora());
                                }

                                usuarioJson.add(usuarios.getIdUsuario());
                                usuarioJson.add(usuarios.getNombre());
                                usuarioJson.add(usuarios.getApellido());
                                usuarioJson.add(usuarios.getFoto());
                                usuarioJson.add(entrada);
                                usuarioJson.add(salida);


                            }
                            contador++;
                        }

                    }else if (listaIngreso.size() == 0){

                        usuarioJson.add(usuarios.getIdUsuario());
                        usuarioJson.add(usuarios.getNombre());
                        usuarioJson.add(usuarios.getApellido());
                        usuarioJson.add(usuarios.getFoto());
                        usuarioJson.add(entrada);
                        usuarioJson.add(salida);

                    }                     

                    
                }
                
            }
        sesion.close();
        objSessionFactory.close();
        }catch(Exception ex){
            System.err.println(ex);            
        }
        
        return usuarioJson.toJSONString();
    }
        
    private List<Usuario> getUsuarios(int idUsuario, int idCargo){
        
        List<Usuario> listaUsuarios = null;        
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            
            Session sesion = objSessionFactory.openSession();
            Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE Cargo="+idCargo+" AND idUsuario != "+idUsuario+"");
            listaUsuarios = queryUsuarios.list();        
            
            sesion.close();
            objSessionFactory.close();
        }catch(HibernateException ex){
            System.err.println(ex);
        }
        return listaUsuarios;
    }
    
    
    ///////////////////////// METODOS PARA REALIZAR EL REGISTRO DEL INGRESO DE LOS USUARIOS //////////////////////////
    public String ingresoEmpleado(String idEmpleado, String fecha){
            
        JSONArray usuarioJson = new JSONArray();  
        
        SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session sesion = objSessionFactory.openSession();
        try{
            
            Usuario objUsuario = (Usuario) sesion.createQuery("FROM Usuario WHERE Documento="+idEmpleado+"").uniqueResult();
            
            String entrada = "Ingreso: 1970-01-0 00:00:00", salida = "Salida: 1970-01-0 00:00:00";
                    
            Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE Usuario="+objUsuario.getIdUsuario()+" AND Fecha='"+fecha+"'");
            List<Ingreso> listaIngreso = queryIngreso.list();

            int contador = 0;

            if (listaIngreso.size() == 1) {

                for(Ingreso ingreso : listaIngreso){
                    if (ingreso.getTipo().equals("Ingreso")) {

                        entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                    }else if (ingreso.getTipo().equals("Salida")) {


                        salida = "Salida: " + String.valueOf(ingreso.getHora());
                    }
                    usuarioJson.add("uno");
                    usuarioJson.add(objUsuario.getIdUsuario());
                    usuarioJson.add(objUsuario.getNombre());
                    usuarioJson.add(objUsuario.getApellido());
                    usuarioJson.add(objUsuario.getFoto());
                    usuarioJson.add(entrada);
                    usuarioJson.add(salida);

                }

            }else if (listaIngreso.size() == 2) {

                for(Ingreso ingreso : listaIngreso){

                    if (contador == 0) {
                        if (ingreso.getTipo().equals("Ingreso")) {

                            entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                        }

                    }else if (contador == 1) {

                        if (ingreso.getTipo().equals("Salida")) {


                            salida = "Salida: " + String.valueOf(ingreso.getHora());
                        }
                        
                        usuarioJson.add("uno");
                        usuarioJson.add(objUsuario.getIdUsuario());
                        usuarioJson.add(objUsuario.getNombre());
                        usuarioJson.add(objUsuario.getApellido());
                        usuarioJson.add(objUsuario.getFoto());
                        usuarioJson.add(entrada);
                        usuarioJson.add(salida);
                    }
                    contador++;
                }
            }
            
            
        }catch(Exception ex){
        
            System.err.println(ex);
        }finally{
            sesion.close();            
            objSessionFactory.close();
        }
        return usuarioJson.toJSONString();        
    }
    
    private void getJsonUsuarios(List<Usuario> listaUsuarios, String fecha){
            
        
        try{
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            for(Usuario usuario : listaUsuarios){
                
                String entrada = "Ingreso: 1970-01-0 00:00:00", salida = "Salida: 1970-01-0 00:00:00";

                Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+fecha+"'");
                List<Ingreso> listaIngreso = queryIngreso.list();

                int contador = 0;

                if (listaIngreso.size() == 1) {

                    for(Ingreso ingreso : listaIngreso){

                        if (ingreso.getTipo().equals("Ingreso")) {

                            entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                        }else if (ingreso.getTipo().equals("Salida")) {


                            salida = "Salida: " + String.valueOf(ingreso.getHora());
                        }

                        usuarioJson.add(usuario.getIdUsuario());
                        usuarioJson.add(usuario.getNombre());
                        usuarioJson.add(usuario.getApellido());
                        usuarioJson.add(usuario.getFoto());
                        usuarioJson.add(entrada);
                        usuarioJson.add(salida);

                    }

                }else if (listaIngreso.size() == 2) {

                    for(Ingreso ingreso : listaIngreso){

                        if (contador == 0) {
                            if (ingreso.getTipo().equals("Ingreso")) {

                                entrada = "Ingreso: " + String.valueOf(ingreso.getHora());

                            }

                        }else if (contador == 1) {

                            if (ingreso.getTipo().equals("Salida")) {


                                salida = "Salida: " + String.valueOf(ingreso.getHora());
                            }

                            usuarioJson.add(usuario.getIdUsuario());
                            usuarioJson.add(usuario.getNombre());
                            usuarioJson.add(usuario.getApellido());
                            usuarioJson.add(usuario.getFoto());
                            usuarioJson.add(entrada);
                            usuarioJson.add(salida);


                        }
                        contador++;
                    }

                }else if (listaIngreso.size() == 0){

                    usuarioJson.add(usuario.getIdUsuario());
                    usuarioJson.add(usuario.getNombre());
                    usuarioJson.add(usuario.getApellido());
                    usuarioJson.add(usuario.getFoto());
                    usuarioJson.add(entrada);
                    usuarioJson.add(salida);

                }                     

            }
        sesion.close();
        objSessionFactory.close();
        }catch(HibernateException ex){
            System.err.println(ex);
        }
       
    }
    
}
