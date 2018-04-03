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
    
    
    public String mostrarEmpleados(int idUsuario, String fecha){
    
        List<Usuario> listaUsuarios = null;
        JSONArray usuarioJson = new JSONArray();
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
                                    
                                    System.out.println("************************** LA FECHA ES ---------->    " + fecha);
                                    
                                }
                                
                            }
                        
                        }
                       
                    }
                
                }
                
                   
                System.out.println("ESTE ES EL FOOOOOOOORRRRRRRRRRRR %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   " + listaUsuarios.size());
                for(Usuario usuario : listaUsuarios){
                    
                    String entrada = "Ingreso: 1970-01-0 00:00:00", salida = "Salida: 1970-01-0 00:00:00";
                    
                    Query queryIngreso = sesion.createQuery("FROM Ingreso WHERE Usuario="+usuario.getIdUsuario()+" AND Fecha='"+fecha+"'");
                    List<Ingreso> listaIngreso = queryIngreso.list();
                    for(Ingreso ingreso : listaIngreso){                                            
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$ ESTRO XDXDXDXDXDXDXDXDXDXDXDXDX");
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
                    
                }
                
                    
                
                
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal") || objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                
            }
            
            
        
        }catch(Exception ex){
            System.err.println(ex);            
        }
              
      return usuarioJson.toJSONString();
    }
        
    private List<Usuario> getUsuarios(int idUsuario, int idCargo){
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Query queryUsuarios = sesion.createQuery("FROM Usuario WHERE Cargo="+idCargo+" AND idUsuario != "+idUsuario+"");
        List<Usuario> listaUsuarios = queryUsuarios.list();
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°   <QUERY 7> buscando A LOS EMPLEADOS SADASDDSA DSA DAS SD DSA DSA S");
        return listaUsuarios;        
    
    }
}
