package Controlador;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONArray;


@ServerEndpoint("/ingresoSala")
public class ingresoSocket {
    
   static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session UserSession) {
        
        System.out.println("ON OPEN ----------------->  " + UserSession);
        chatroomUsers.add(UserSession);
        
        System.out.println("#####    " + chatroomUsers.size());
        
    }

    @OnClose
    public void onClose(Session UserSession) {
        
        System.out.println("ON CLOSE ------------------------>  " + UserSession);
        chatroomUsers.remove(UserSession);
        
    }

    @OnMessage
    public void onMessage(String mensaje, Session UserSession) throws IOException, EncodeException{
        
        accionesSocket objAccionesSocket = new accionesSocket();
        String accion[] = mensaje.split("-");
        
        if (accion[0].equals("CargarUsuarios")) {
            
            
            String jsonUsuarios = objAccionesSocket.mostrarEmpleados(Integer.parseInt(accion[1]), accion[2]);
            
            Iterator<Session> iterador = chatroomUsers.iterator();
        
            while(iterador.hasNext()){
                System.out.println("ESTOY EN EL ITERADOR ENVIANDO A TODOS LOS USUARIOS CONECTADOS");
                iterador.next().getBasicRemote().sendObject(jsonUsuarios);

            }
            
        }else if (accion[0].equals("IngresarUsuario")) {
            
            String jsonUsuarios = objAccionesSocket.ingresoEmpleado(accion[1], accion[2]);
            System.out.println("ESTAMOS HACIENDO EL INGRESO DE UN SUSUARIO");
            
            Iterator<Session> iterador = chatroomUsers.iterator();
            while(iterador.hasNext()){
                System.out.println("ESTOY EN EL ITERADOR ENVIANDO A TODOS LOS USUARIOS CONECTADOS");
                iterador.next().getBasicRemote().sendObject(jsonUsuarios);

            }
        }
        
        System.out.println("ON MESSAGE   -------------->  " +mensaje +  "   -------->   "+ UserSession);
        
        
    }

    @OnError
    public void onError(Throwable t) {
        
        System.out.println("ON ERROR -----------------> "+t);
        System.err.println(t);
    }
    
}
