<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- Importamos la libreria Java.sql.*--%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
        <%
         // Connection con=null; 
              Connection cn=null;
        try{
        String servidor="localhost";
        String puerto="3306";
        String usuario="root";
        String password="";
        String bd="saleslandcolombiadb";
        String url="com.mysql.jdbc.Driver";
        String cadenaConex="jdbc:mysql://"+servidor+":"+puerto+"/"+bd;
      
        //Cargar el Driver
            Class.forName(url).newInstance();
            //Abrir la conexion
            cn=DriverManager.getConnection(cadenaConex,usuario,password);
          
          
       // Class.forName("com.mysql.jdbc.Driver");
        //con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/eurekabank");
        out.println("Coneccion Disponible");
        }catch (Exception ex){
        out.println("Error"+ex.getMessage());
        }
      
        %>
    </body>
</html>