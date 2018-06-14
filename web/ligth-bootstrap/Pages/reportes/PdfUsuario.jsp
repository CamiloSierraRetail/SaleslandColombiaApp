<%-- 
  
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@page import="java.io.*"%>
<%@page import="java.util.*"%>

<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>

<%@page import="javax.servlet.ServletResponse"%>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Usuario - Salesland Colombia</title>
    </head>
    <body>

        <%           
                Connection con = null;
                
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/saleslandcolombiadb", "root", "");
                
            
                File reportfile = new File(application.getRealPath("ligth-bootstrap//Pages//reportes//JasperUsuarios.jasper"));
                
                Map<String, Object> parameter = new HashMap<String, Object>();

                String valor1 = request.getParameter("txtparametro");
                String valor2 = request.getParameter("txtfechainicial");
                String valor3 = request.getParameter("txtfechafinal");

                parameter.put("doc", new String(valor1));
                parameter.put("fech1", new String(valor2));
                parameter.put("fech2", new String(valor3));

                byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, con);

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=\"reporte.pdf\"");
                response.setContentLength(bytes.length);
              
                ServletOutputStream outputstream = response.getOutputStream();
                outputstream.write(bytes, 0, bytes.length);
          
                outputstream.flush();
                outputstream.close();
              
         
           
        %>
        <script type="text/javascrpt">
            
        </script>

    </body>
</html>
