
package Controlador;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;


public class ExcelServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "../../web/ligth-bootstrap/Pages/reportes/ExcelReport.jasper"; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
              Connection con = null;               
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/saleslandcolombiadb", "root", "");        
        //cargamos parametros del reporte (si tiene).
        Map parametros = new HashMap();

        //Generar XLS. 
        //Preparacion del reporte (en esta etapa se inserta el valor del query en el reporte).
         
        JasperPrint jasperPrint=JasperFillManager.fillReport(this.getServletContext().getRealPath(UPLOAD_DIRECTORY), parametros, con);

        //Nombre archivo resultado.
        //Sugerencia: traten de generar un nombre dinamico concatenando fecha y hora para evitar se pisen los reportes al tener el mismo nombre.
        String xlsFileName = "ReporteExcel.xls";
        String xlsFilesSource ="";

        //Creacion del XLS
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,xlsFilesSource + xlsFileName);

        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporter.exportReport();

        //En este punto ya esta Creado el XLS


        //Ahora lo Voy a Leer Y A forzar al Navegador Muestre Dialogo Para descargar el archivo
        //Funciona En IE y Firefox


        //Leer el archivo.
        File f = new File (xlsFilesSource + xlsFileName);

        //Configurar el tipo de archivo.
        response.setContentType ("application/vnd.ms-excel");

        //Obtener el Nombre del archivo.
        String name = f.getName().substring(f.getName().lastIndexOf("/") + 1,f.getName().length());

        //Configurar cabecera y nombre de archivo a desplegar en DialogBox.
        response.setHeader ("Content-Disposition", "attachment;filename=\"" + xlsFileName + "\"");


        InputStream in = new FileInputStream(f);
        ServletOutputStream outs = response.getOutputStream();

        int bit = 256;
        int i = 0;

        while ((bit) >= 0) {
            bit = in.read();
            outs.write(bit);
        }

        outs.flush();
        outs.close();
        in.close();
           
        } catch (Exception e) {
            
            System.err.println(e);
        } finally {
         
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
