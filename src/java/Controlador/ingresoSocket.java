package Controlador;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Bryan Ossa
 */
@ServerEndpoint("/ingresoSala")
public class ingresoSocket {
    
   static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session UserSession) {
        
        System.out.println("ON OPEN ----------------->  " + UserSession);
        chatroomUsers.add(UserSession);
        
    }

    @OnClose
    public void onClose(Session UserSession) {
        
        System.out.println("ON CLOSE ------------------------>  " + UserSession);
        chatroomUsers.remove(UserSession);
        
    }

    @OnMessage
    public void onMessage(String mensaje, Session UserSession) throws IOException{
        
        System.out.println("ON MESSAGE   -------------->  " +mensaje +  "   -------->   "+ UserSession);
        
        
        Iterator<Session> iterador = chatroomUsers.iterator();               
        
        while(iterador.hasNext()){
                    
            iterador.next().getBasicRemote().sendText(mensaje);
        
        }
        
    }

    @OnError
    public void onError(Throwable t) {
        
        System.out.println("ON ERROR -----------------> "+t);
        System.err.println(t);
    }
    
}
