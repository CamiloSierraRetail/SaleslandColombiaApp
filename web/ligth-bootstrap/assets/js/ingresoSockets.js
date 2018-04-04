var websocket = new WebSocket("ws://"+location.host+"/SaleslandColombiaApp/ingresoSala");


/////////////////////// FUNCION PARA CONECTARSE AL SOCKET EN EL EVENTO ONMESSAGE /////////////////////////
websocket.onmessage = function (message){

    var dt = JSON.parse(message.data);


    if (dt[0] == "muchos") {

        var contador = -1;
        $("#usuariosIngresados").html("");

        for (var i = 0, max = dt.length; i < max; i++) {

            if (contador == 5) {

                var nombre = dt[i-4].split(" ");
                var apellido = dt[i-3].split(" ");
                var entrada = dt[i-1].split(" ");
                var horaEntrada = entrada[2].split(":");
                var salida = dt[i].split(" ");
                var horaSalida = salida[2].split(":");
                
                $("#usuariosIngresados").append("<li class='nav-item'>"
                                                    +"<div class='ctli'>"
                                                        +"<div class='row'>"
                                                            +"<div class='col-md-3'>"
                                                                +"<a href='#'>"
                                                                    +"<img id='imagenPerfil"+dt[i-5]+"' src='../../assets/img/imagenesDePerfil/"+dt[i-2]+"' alt=''/>"
                                                                +"</a>"
                                                            +"</div>"
                                                            +"<div class='col-md-9'>"
                                                                +"<p style='padding: 0; margin:0;'>"+nombre[0]+" "+apellido[0]+"</p>"
                                                                +"<p id='hora"+dt[i-5]+"' style='font-size:11px; margin:0; padding: 0;'>"+entrada[0]+" "+horaEntrada[0]+":"+horaEntrada[1]+" - "+salida[0]+" "+horaSalida[0]+":"+horaSalida[1]+"</p>"
                                                            +"</div>"
                                                        +"</div>"
                                                    +"</div>"
                                               +"</li>");

                if (horaEntrada[0] != "00") {

                    $("#imagenPerfil"+dt[i-5]+"").css({'opacity': '1', 'border': '2px solid #44bd32'});
                    if (horaSalida[0] != "00") {

                        $("#imagenPerfil"+dt[i-5]+"").css({'opacity': '0.5', 'border': '2px solid #f0932b'});
                    }                                                                                                            
                }

                contador = 0;
            }else{
                contador++;
            }

        } 


    }else if (dt[0] == "uno") {

        var entrada2 = dt[5].split(" ");
        var horaEntrada2 = entrada2[2].split(":");
        var salida2 = dt[6].split(" ");
        var horaSalida2 = salida2[2].split(":");

        $("#hora"+dt[1]+"").text(""+entrada2[0]+" "+horaEntrada2[0]+":"+horaEntrada2[1]+" - "+salida2[0]+" "+horaSalida2[0]+":"+horaSalida2[1]+"");

        if (horaEntrada2[0] != "00" && horaSalida2[0] != "00") {


            alert("salida");                                
            $("#imagenPerfil"+dt[1]+"").css({'opacity': '0.5', 'border': '2px solid #f0932b'});

        }else if (horaEntrada2[0] != "00") {

            $("#imagenPerfil"+dt[1]+"").css({'opacity': '1', 'border': '2px solid #22a6b3'});                            

        } 

    } 

};




