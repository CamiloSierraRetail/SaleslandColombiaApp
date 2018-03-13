package Controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class SubirImagenPerfil extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "/ligth-bootstrap/assets/img/imagenesDePerfil/"; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        System.out.println("SUUUUBBBBIIIIIRRRR AAAAARCHIVO");
        if(ServletFileUpload.isMultipartContent(request)){
            System.out.println("ESTOY DENTRO DEL IF DE SUBIR EL ARCHIVO");
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);       
                System.out.println("Estoy en el try");
                for(FileItem item : multiparts){
                    System.out.println("MULTIPARTES -->");
                    try{
                        if(!item.isFormField()){
                            String name = new File(item.getName()).getName();
                            item.write( new File(this.getServletContext().getRealPath(UPLOAD_DIRECTORY) + File.separator + name));
                            
                            System.out.println("CARAJO ----> "+name);
                        }
                    }
                    catch(Exception ex){
                        System.out.println("error "+ex);
                    }
                }           
                
            } catch (Exception ex) {
                System.err.println(ex);
                System.out.println("file uploading has failed");
            }                  

        }else{           
            System.out.println("only mutipart bitch");
       }
        
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
