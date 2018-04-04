package Controlador;

import Modelo.Area;
import Modelo.Area_Cargo;
import Modelo.Canal;
import Modelo.Canal_Cargo;
import Modelo.Ingreso;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;


public class accionesSocket {
    
    JSONArray usuarioJson = new JSONArray();
    public String mostrarEmpleados(int idUsuario, String fecha){
    
        List<Usuario> listaUsuarios = null;
        
        usuarioJson.add("muchos");
        try{
        
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Usuario objUsuario = (Usuario)sesion.createQuery("FROM Usuario WHERE idUsuario="+idUsuario+"").uniqueResult();
            //System.out.println("<QUERY 1>  {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{ ----    " + objUsuario.getCargo().getIdCargo());
            
            if (objUsuario.getCargo().getTipo().equals("Director")) {
                
                Query querySector = sesion.createQuery("FROM Sector_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                //System.out.println("<QUERY 2>");
                List<Sector_Cargo> listaSector_Cargo = querySector.list();
                for(Sector_Cargo sector_cargo : listaSector_Cargo){
                
                    //System.out.println("EL NUMERO Y LOS SECTORES SON --------------------->     " + listaSector_Cargo.size() + "         " + sector_cargo.getCargo().getNombreCargo());
                    listaUsuarios = getUsuarios(idUsuario, sector_cargo.getCargo().getIdCargo());                   
                    
                    Query queryCanal = sesion.createQuery("FROM Canal WHERE Sector = "+sector_cargo.getSector().getIdSector()+"");
                    //System.out.println("<QUERY 3>");
                    List<Canal> listaCanal = queryCanal.list();
                    for(Canal canal : listaCanal){
                    
                        
                        Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal = "+canal.getIdCanal()+"");
                        //System.out.println("<QUERY 4>");
                        List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                        for(Canal_Cargo canal_cargo : listaCanal_Cargo){                        
                            
                            //System.out.println("EL NUMERO Y LOS CANALES SON --------------------->     " + listaCanal_Cargo.size() + "         " + canal_cargo.getCargo().getNombreCargo());
                            listaUsuarios.addAll(getUsuarios(idUsuario, canal_cargo.getCargo().getIdCargo()));                                                        
                        
                        }
                        
                        Query queryArea = sesion.createQuery("FROM Area WHERE Canal = "+canal.getIdCanal()+"");
                        //System.out.println("<QUERY 5>");
                        List<Area> listaArea = queryArea.list();
                        for(Area area : listaArea){

                            Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+area.getIdArea()+"");
                            //System.out.println("<QUERY 6>");
                            List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                            for(Area_Cargo area_cargo : listaArea_Cargo){

                                //System.out.println("EL NUMERO Y LOS AREAS SON --------------------->     " + listaArea_Cargo.size() + "         " + area_cargo.getCargo().getNombreCargo());
                                listaUsuarios.addAll(getUsuarios(idUsuario, area_cargo.getCargo().getIdCargo()));
//                                System.out.println("CARGO    "+area_cargo.getCargo().getNombreCargo()+"   "+area_cargo.getCargo().getIdCargo()+"         >_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<>_<     "+area_cargo.getArea().getNombreArea());
//                                System.out.println("************************** LA FECHA ES ---------->    " + fecha);

                            }

                        }
                       
                    }
                
                }
                  
                getJsonUsuarios(listaUsuarios, fecha);
                    
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
               
                
                
                listaUsuarios.addAll(getUsuarios(idUsuario, 3));
                listaUsuarios.addAll(getUsuarios(idUsuario, 4));
                
                
                
                
                getJsonUsuarios(listaUsuarios, fecha);
                
                
                
                
                
                
                
                
                
                
                
                
                
               
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                
            }
          
        }catch(Exception ex){
            System.err.println(ex);            
        }
        
        System.out.println("EL RESULTADO FINAL DEL JSON   "+usuarioJson);
        return usuarioJson.toJSONString();
    }
        
    private List<Usuario> getUsuarios(int idUsuario, int idCargo){
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE Cargo="+idCargo+" AND idUsuario != "+idUsuario+"");
        List<Usuario> listaUsuarios = queryUsuarios.list();
        
        System.out.println("BUSCANDO EMPLEADOS TAMAÑO DE LA LISTA =======================      " + listaUsuarios.size());
//        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°   <QUERY 7> buscando A LOS EMPLEADOS SADASDDSA DSA DAS SD DSA DSA S");
        return listaUsuarios;        
    
    }
    
    
    ///////////////////////// METODOS PARA REALIZAR EL REGISTRO DEL INGRESO DE LOS USUARIOS //////////////////////////
    public String ingresoEmpleado(String idEmpleado, String fecha){
    
        
        JSONArray usuarioJson = new JSONArray();
        
        try{
                        
            Session sesion = HibernateUtil.getSessionFactory().openSession();
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
        }
        return usuarioJson.toJSONString();        
    }
    
    private void getJsonUsuarios(List<Usuario> listaUsuarios, String fecha){
    
        
        for(Usuario usuario : listaUsuarios){
                    
//          System.out.println("EL NOMBRE DEL USUARIO ES ........**************$$$$$$$$$ -------->    " +usuario.getNombre());
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String entrada = "Ingreso: 1970-01-0 00:00:00", salida = "Salida: 1970-01-0 00:00:00";

            Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+fecha+"'");
            List<Ingreso> listaIngreso = queryIngreso.list();


//                    System.out.println("^^^^^^^^^^^^^^^^^^^   TAMAÑO DE LA LISTA DE INGRESOS ------>    " + listaIngreso.size());
            int contador = 0;

            if (listaIngreso.size() == 1) {

                for(Ingreso ingreso : listaIngreso){
//                            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$ ESTRO XDXDXDXDXDXDXDXDXDXDXDXDX");
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
    
    }
    
    
}
