package Controlador;

import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
        
        String accion[] = mensaje.split("-");
        if (accion[0].equals("CargarUsuarios")) {
            
            accionesSocket objAccionesSocket = new accionesSocket();
            String listaUsuarios = objAccionesSocket.mostrarEmpleados(Integer.parseInt(accion[1]), accion[2]);
            
            Iterator<Session> iterador = chatroomUsers.iterator();
        
            while(iterador.hasNext()){
                System.out.println("ESTOY EN EL ITERADOR ENVIANDO A TODOS LOS USUARIOS CONECTADOS");
                iterador.next().getBasicRemote().sendObject(listaUsuarios);

            }
            
        }else{
        
            Iterator<Session> iterador = chatroomUsers.iterator();
        
            while(iterador.hasNext()){
                System.out.println("ESTOY EN EL ITERADOR ENVIANDO A TODOS LOS USUARIOS CONECTADOS");
                String jsonUltimo = new Gson().toJson("TU MENSAJE LLEGO AL SOCKET Y AHORA ESTA DE REGRESO");
                iterador.next().getBasicRemote().sendText(jsonUltimo.toString());

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
