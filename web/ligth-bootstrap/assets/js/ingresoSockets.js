var websocket = new WebSocket("ws://localhost:8080/SaleslandColombiaApp/ingresoSala");
            
function on_Open(){

    websocket.onopen = function (){

        alert("on open");
    };

}            

function on_Message(){

    websocket.onmessage = function (){

        alert("on message");

    };
}





