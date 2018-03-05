///////////////////////// REGISTRAR AREA ///////////////////////////////////////
$('#frmRegistrarArea').validate({
    rules: {
        NombreArea: {
            required: true,
            minlength: 5
        },
        DescripcionArea: {
            required: true,
            minlength: 15,
            maxlength: 80
        },
        Canal:{
            required: true
        }
    }, messages: {

        NombreArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        Canal:{
            required: "Este campo es requerido"
        }
    }, errorElement: 'div',
    errorPlacement: function (error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error);
        } else {
            error.insertAfter(element);
        }
    }, submitHandler: function () {

        swal({
            title: "Confirmar Datos",
            text: "¿Está seguro que desea realizar el registro?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {
                
                var canalArea = $("#cmbCanal").val();
                var nombreArea = $("#txtNombreArea").val();
                var descripcionArea = $("#txtDescripcionArea").val();
                $.post("/SaleslandColombiaApp/area/registrararea",{CanalArea:canalArea,NombreArea:nombreArea,descripcionArea:descripcionArea},function (responsetext) {
                    if(responsetext == "200"){
                        swal("Registro exitoso", "El canal ha sido registrado exitosamente", "success").then((willDelete) => {
                            if (willDelete) {
                                //window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp";
                            }
                        });

                    } else {
                        swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
                    }
                });
            }
        });
    }
});
////////////////// LISTAR TODAS LAS AREAS ////////////////////////////
function listarArea() {

    $.post("/SaleslandColombiaApp/area/verarea", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
            
        }else{
             
            $("#listadoCanales").html("");           
            $("#listadoArea").append(responseText);            
        } 
        
    });
}
///////////////////// VER DATOS  ///////////////////////////////
function verDatosArea(){
    
    var url = ""+window.location+"";
    var IdArea = url.split("_");
    $.post("/SaleslandColombiaApp/area/cargardatosarea",{idArea:IdArea[1]},function(responseText){
       
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un erro en el servidor, por favor intentalo nuevamente", "error");
        }else{
            
            var dt = JSON.parse(responseText);
            console.log(dt);
            if (dt[3] == "Activo") {
                $("#cmbEditarEstadoArea").val('Activo');
            }else{
                
                $("#cmbEditarEstadoArea").val('Inactivo');
            }
            $("#txtEditarNombreArea").val(dt[1]);
            $("#txtEditarDescripcionArea").val(dt[2]);
            $("#cmbCanal").val(""+dt[4]+"");
        }
        
    });
    
}
/////////////////////////// EDITAR Area //////////////////////////////////////////////
$('#frmEditarArea').validate({
    rules:{
        EditarEstadoArea:{
            
          required:true  
        },
        EditarNombreArea:{
            required:true,
            minlength:5
        },
        EditarDescripcionArea:{
            
            required:true,
            minlength:15,
            maxlength:80
        },
        EditarEstadoCanalArea:{
            
            required:true,
            
        }
    },messages:{
        EditarEstadoArea:{
            
          required:"Este campo es requerido"  
        },
        EditarNombreArea:{
            required:"Este campo es requerido",
            minlength:"Ingresa 5 caracteres como minimo"
        },
        EditarDescripcionArea:{
            required:"Este campo es requerido",
            minlength:"Ingresa 15 caracteres como minimo",
            maxlength:"Ingresa 80 caracteres como maximo"
        },
        EditarEstadoCanalArea:{
            required:"Este campo es requerido",          

        }
    }, errorElement: 'div',
    errorPlacement: function (error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error);
        } else {
            error.insertAfter(element);
        }
    }, submitHandler: function () {

        swal({

        title: "Editar Area",
        text: "¿Está seguro que desea reemplazar los datos del canal?",
        icon: "info",
        buttons: true,
        closeonconfirm: false,
        buttons: ["No, Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {

              var url = ""+window.location+"";
              var idArea = url.split("_");
              var nombreArea = $("#txtEditarNombreArea").val();
              var descripcionArea = $("#txtEditarDescripcionArea").val();
              var estadoArea = $("#cmbEditarEstadoArea").val();
              var canalArea = $("#cmbCanal").val();
              $.post("/SaleslandColombiaApp/area/editararea",{IdArea:idArea[1],NombreArea:nombreArea,DescripcionArea:descripcionArea,EstadoArea:estadoArea,CanalArea:canalArea},function (responsetext) {
                   if(responsetext == "200"){

                        swal("Cambios Guardados", "Los cambios del sector han guardado exitosamente", "success").then((willDelete) => {
                          if (willDelete) {                          
                            window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/area/listararea.jsp";
                          }
                        });

                    }else{                    
                        swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error").then((willDelete) => {
                         
                        });                                        
                    }                        
                });          
            }
        });                        
    }        
});
//CARGAR CANAL
function cargarCanal(){
    
    $.post("/SaleslandColombiaApp/canal/cargacombocanal",function (responseText){
        
        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocuttió un error al intentar cargar la información.", "error");
        }else{
            
            var dt = JSON.parse(responseText);
            
            
            var counta = 0;
            for (var i = 0, max = dt.length; i < max; i++) {
                
                if (counta == 1) {
                    counta = 0;
                    
                }else{
                    
                    $("#cmbCanal").append("<option value='"+dt[i]+"'>"+dt[i+1]+"</option>")
                    counta++;
                }
                
                
            }
            
            /*for (var key in dt) {
                if (dt.hasOwnProperty(key)) {
                  var val = dt[key];
                    
                    $("#cmbCanal").append("<option value='"+val['IdCanal']+"'>"+val['NombreCanal']+"</option>")
                    
                }
            }*/
        } 
        
    });
}