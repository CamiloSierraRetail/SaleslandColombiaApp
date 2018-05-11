package Controlador;

import Modelo.Area_Cargo;
import Modelo.Canal_Cargo;
import Modelo.Permiso;
import Modelo.Sector_Cargo;
import Modelo.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class permiso extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modelo para el manejo de todos los controladores
        String url[] = request.getRequestURI().split("/");
        
        if (url.length >= 3) {
            
            switch (url[3]){
                            
                case "registrarPermiso":
                    registrarPermiso(request, response);
                    break;
                    
                case "listarPermisos":
                    listarPermisos(request, response);
                    break;
                
            }
            
        }
        
    }

    protected void registrarPermiso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
        try{            
            
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            Usuario objJefeDirecto = new Usuario();
            
            String motivo = request.getParameter("Motivo");
            String descripcion = request.getParameter("Descripcion");
            String inicio = request.getParameter("Inicio");
            String fin = request.getParameter("Fin");
            String archivo = request.getParameter("Archivo");
            
            
            String InicioCompmplete [] = inicio.split(" ");
            String FinCompmplete [] = fin.split(" ");
            
            
            System.out.println("sdsdjsjsjsjsjs al archivo es ---->         "+archivo+ "  FECHA DE INICIO ----->     " + inicio+ "        " + fin);
            
            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
                        
            if (objUsuario.getCargo().getTipo().equals("Director")) {
            
                System.out.println("EL ROL QUE MANEJA ES DE UN DIRECTOR.");
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeCanal")) {
                
                Canal_Cargo objCanal_Cargo = (Canal_Cargo) sesion.createQuery("FROM Canal_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"").uniqueResult();
                
                Query querySector_Cargo = sesion.createQuery("FROM Sector_Cargo WHERE Sector="+objCanal_Cargo.getCanal().getSector().getIdSector()+"");
                List<Sector_Cargo> listaSectorCargo = querySector_Cargo.list();
                
                for(Sector_Cargo sector_cargo : listaSectorCargo){
                    
                    if (sector_cargo.getCargo().getTipo().equals("Director") && sector_cargo.getCargo().getEstado().equals("Activo")) {
                        
                        objJefeDirecto = (Usuario) sesion.createQuery("FROM Usuario WHERE Estado='Activo' AND Cargo = "+sector_cargo.getCargo().getIdCargo()+"").uniqueResult();                        
                    }
                
                }
                
                
            }else if (objUsuario.getCargo().getTipo().equals("CoordinadorCanal")) {
                
                Canal_Cargo objCanal_Cargo = (Canal_Cargo) sesion.createQuery("FROM Canal_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"").uniqueResult();
                
                
                Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+objCanal_Cargo.getCanal().getIdCanal()+"");
                List<Canal_Cargo> listaCanal_cargo = queryCanal_Cargo.list();
                
                for(Canal_Cargo canal_cargo : listaCanal_cargo){
                
                    if (canal_cargo.getCargo().getTipo().equals("JefeCanal") && canal_cargo.getCargo().getEstado().equals("Activo")) {
                        
                        objJefeDirecto = (Usuario) sesion.createQuery("FROM Usuario WHERE Estado='Activo' AND Cargo="+canal_cargo.getCargo().getIdCargo()+"").uniqueResult();                        
                    }
                
                }
                
            }else if (objUsuario.getCargo().getTipo().equals("JefeArea")) {
                
                Area_Cargo objArea_Cargo = (Area_Cargo)sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"");
                
                Query queryCanal_Cargo = sesion.createQuery("FROM Canal_Cargo WHERE Canal="+objArea_Cargo.getArea().getCanal().getIdCanal()+"");
                List<Canal_Cargo> listaCanal_Cargo = queryCanal_Cargo.list();
                
                for(Canal_Cargo canal_cargo : listaCanal_Cargo){
                
                    if (canal_cargo.getCargo().getTipo().equals("CoordinadorCanal") && canal_cargo.getCargo().getEstado().equals("Activo")) {
                        
                        objJefeDirecto = (Usuario) sesion.createQuery("FROM Usuario WHERE Estado='Activo' AND Cargo="+canal_cargo.getCargo().getIdCargo()+"").uniqueResult();                        
                        
                    }
                
                }
                
                             
            }else if (objUsuario.getCargo().getTipo().equals("Empleado")) {
                
                
                Area_Cargo objArea_Cargo = (Area_Cargo) sesion.createQuery("FROM Area_Cargo WHERE Cargo="+objUsuario.getCargo().getIdCargo()+"").uniqueResult();
                
                Query queryArea_Cargo = sesion.createQuery("FROM Area_Cargo WHERE Area="+objArea_Cargo.getArea().getIdArea()+"");
                List<Area_Cargo> listaArea_Cargo = queryArea_Cargo.list();
                
                for(Area_Cargo area_cargo : listaArea_Cargo){
                
                    
                    if (area_cargo.getCargo().equals("JefeArea") && area_cargo.getCargo().getEstado().equals("Activo")) {
                        
                        objJefeDirecto = (Usuario) sesion.createQuery("FROM Usuario WHERE Estado='Actio' AND Cargo = "+area_cargo.getCargo().getIdCargo()+"").uniqueResult();                        
                    }
                    
                }
                
            }
            
            if (objJefeDirecto.getDocumento() != null) {
                
                
                System.out.println("EL JEFEDIRECTO ES  ------------------------>     "  + objJefeDirecto.getNombre());                        
                
                Permiso objPermiso = new Permiso(motivo, descripcion, new Date(), new Date(), new Date(), new Date(), archivo, "Enviado", objUsuario, objJefeDirecto);
                
                sesion.beginTransaction();
                sesion.save(objPermiso);
                sesion.getTransaction().commit();
                
                response.getWriter().write("200");
                
                
            }else{
            
                response.getWriter().write("204");
                
            }            
            sesion.close();
            objSessionFactory.close();                       
        
        }catch(Exception ex){
            System.err.println(ex);
            response.getWriter().write("500");
        }
    }
    protected void listarPermisos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try{
            Usuario objUsuario = (Usuario) request.getSession().getAttribute("UsuarioIngresado");
            int countRows = 1;

            SessionFactory objSessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Session sesion = objSessionFactory.openSession();
            
            Query queryPermisos = sesion.createQuery("FROM Permiso WHERE UsuarioEnvia="+objUsuario.getIdUsuario()+"");
            List<Permiso> listaPermiso = queryPermisos.list();
            
            
            response.setCharacterEncoding("UTF-8");
            for(Permiso permiso : listaPermiso){
                
                String fechaInicio[] = String.valueOf(permiso.getFechaInicio()).split(" ");
                String horaI[] = String.valueOf(permiso.getHoraInicio()).split(" ");
                String horaInicio[] = horaI[1].split(":");
                
                String fechaFin[] = String.valueOf(permiso.getFechaFin()).split(" ");
                String horaF[] = String.valueOf(permiso.getHoraFin()).split(" ");
                String horaFin[] = horaF[1].split(":");
                
                String nombre[] = permiso.getUsuarioRecibe().getNombre().split(" ");
                String apellido[] = permiso.getUsuarioRecibe().getApellido().split(" ");
                
                response.getWriter().write("<tr>"
                                              + "<td class='text-center'>"+countRows+"</td>"
                                              + "<td class='text-center'>"+permiso.getMotivo()+"</td>"
                                              + "<td class='text-center'>"+permiso.getDescripcion()+"</td>"
                                              + "<td class='text-center'>"+fechaInicio[0]+"   "+horaInicio[0]+":"+horaInicio[1]+"</td>"
                                              + "<td class='text-center'>"+fechaFin[0]+"   "+horaFin[0]+":"+horaFin[1]+ "</td>"
                                              + "<td class='text-center'>"+nombre[0]+ " "+apellido[0]+"</td>"
                                              + "<td class='text-center'>"+permiso.getEstado()+"</td>"
                                              + "<td class='td-actions text-center'>"
                                                + "<a href='/SaleslandColombiaApp/ligth-bootstrap/assets/filesPermisos/"+permiso.getDocAnexo()+"' target='_blank' rel='tooltip' title='' class='btn btn-link btn-xs' data-original-title='Archivo adjunto'>"
                                                    + "<i class='fa fa-file blue-corp'></i>"
                                                + "</a>"   
                                                + "<button onclick='verDatosSector("+permiso.getIdPermiso()+")' data-toggle='modal' data-target='#ModalEditarSector' rel='tooltip' title='' class='btn btn-link btn-xs' data-original-title='Editar'>"
                                                    + "<i class='fa fa-edit gray-corp'></i>"
                                                + "</button>"
                                               
                                              + "</td>"
                                         + "</tr>");
                countRows++;                            
            
            }
            
            sesion.close();
            objSessionFactory.close();
        
        }catch(Exception ex){
        
            System.out.println(ex);
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
