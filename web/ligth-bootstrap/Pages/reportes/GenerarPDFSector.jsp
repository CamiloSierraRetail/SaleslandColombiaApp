<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>

<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>

<%@page import="javax.servlet.ServletResponse"%>

<%@include file="conexion.jsp" %> %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>pdf</title>
    </head>
    <body>
 <%
 //Ruta del reporte
 File reportfile=new File(application.getRealPath("ReportSectores.jasper"));

 Map<String,Object> parameter=new HashMap<String,Object>();
 //alamcena el contenido del ireport
 byte[] bytes= JasperRunManager.runReportToPdf(reportfile.getPath(),parameter,cn);
 //salida en formato pdf
 response.setContentType("application/pdf");
 response.setContentLength(bytes.length);
 ServletOutputStream outputstram= response.getOutputStream();
 outputstram.write(bytes,0,bytes.length);
 //Limpiamos los flujos de salidas para que no consuma RAM o este abiero innecesariamente
 //Objeto de salida
 outputstram.flush();
 //cerramos
 outputstram.close();
 %>
    </body>
</html>
