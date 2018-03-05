    $('#frmRegistrarSector').validate({
    rules: {
        NombreSector: {
            required: true,
            minlength: 5
        },
        DescripcionSector: {
            required: true,
            minlength: 15,
            maxlength: 80
        }
    }, messages: {

        NombreSector: {
            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionSector: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
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

              var nombreSector = $("#txtNombreSector").val();
              var descripcionSector = $("#txtDescripcionSector").val();
              $.post("../../../sector/registrar",{NombreSector:nombreSector,DescripcionSector:descripcionSector},function (responsetext) {
                  if(responsetext == "200"){

                                swal("Registro exitoso", "El sector ha sido registrado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {
                                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp";
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
////////////////// LISTAR TODOS LOS SECTORES ////////////////////////////
function listarSectores() {

    $.post("/SaleslandColombiaApp/sector/versectores", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
            
        }else{
            $("#listadoSectores").html("");           
            $("#listadoSectores").append(responseText);            
        } 
        
    });

}
//REGISTRAR CARGOS EN LA EMPRESA//
$('#frmRegistrarCargo').validate({
    rules: {
        NombreCargo: {

            required: true,
            minlength: 5
        },
        DescripcionCargo: {

            required: true,
            minlength: 15,
            maxlength: 80
        },
        Salario:{
            required: true,
            min: 781.242
        }
        
        
    }, messages: {

        NombreCargo: {

            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionCargo: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        Salario: {
            required: "Este campo es requerido",
            min: "El valor minimo es de 781.242"
            
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

                        var nombreCargo = $("#txtNombreCargo").val();
                        var descripcionCargo = $("#txtDescripcionCargo").val();
                        var salario = $("#txtSalario").val();
                        $.post("../../../cargo/registrar", {NombreCargo: nombreCargo, DescripcionCargo: descripcionCargo, Salario: salario}, function (responsetext) {
                            if (responsetext == "200") {

                                swal("Registro exitoso", "El sector ha sido registrado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {
                                        //window.location = "/FutPlay/GaiaTemplate/index.html";
                                    }
                                });

                            } else {
                                swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error").then((willDelete) => {

                                });
                            }
                        });
                    }
                });
    }
});
////////////////// LISTAR TODOS LOS CARGOS ////////////////////////////
function listarCargos() {
    $.post("/SaleslandColombiaApp/cargo/vercargos", function (responseText) {
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos tus datos no fueron cargados.", "error");
        } else {
            $("#listadoCargos").html("");
            $("#listadoCargos").append(responseText);
            swal("Ok", "Listado", "success");
        }
    });
}


////////////////////////////// CARGAR DATOS SECTOR ////////////////////////////
function verDatosSector(){
    
    var url = ""+window.location+"";
    var idSector = url.split("_");
    $.post("/SaleslandColombiaApp/sector/cargardatossector",{idSector:idSector[1]},function(responseText){
       
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un erro en el servidor, por favor intentalo nuevamente", "error");
        }else{
            
            var dt = JSON.parse(responseText);            
            $.each(dt, function (){
                if (this['Estado'] == "Activo") {
                    
                    $("#cmbEditarEstadoSector").val('Activo');
                }else{
                    
                    $("#cmbEditarEstadoSector").val('Inactivo');
                }               
               $("#txtEditarNombreSector").val(this['NombreSector']);
               $("#txtEditarDescripcionSector").val(this['DescripcionSector']);
                
            });
        }
        
    });
    
}

/////////////////////////// EDITAR SECTOR //////////////////////////////////////////////
$('#frmEditarSector').validate({
    rules:{
        EditarNombreSector:{
            required:true,
            minlength:5
        },
        EditarDescripcionSector:{
            
            required:true,
            minlength:15,
            maxlength:80
        },
        EditarEstadoSector:{
            
            required:true,
            
        }
    },messages:{
        
        EditarNombreSector:{
            required:"Este campo es requerido",
            minlength:"Ingresa 5 caracteres como minimo"
        },
        EditarDescripcionSector:{
            required:"Este campo es requerido",
            minlength:"Ingresa 15 caracteres como minimo",
            maxlength:"Ingresa 80 caracteres como maximo"
        },
        EditarEstadoSector:{
            required:"Este campo es requerido",          
            
        },
        DescripcionSector: {

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

        title: "Editar Sector",
        text: "¿Está seguro que desea reemplazar los datos del sector?",
        icon: "info",
        buttons: true,
        closeonconfirm: false,
        buttons: ["No, Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {

              var url = ""+window.location+"";
              var idSector = url.split("_");
              var nombreSector = $("#txtEditarNombreSector").val();
              var descripcionSector = $("#txtEditarDescripcionSector").val();
              var estadoSector = $("#cmbEditarEstadoSector").val();
              $.post("/SaleslandColombiaApp/sector/editarsector",{IdSector:idSector[1],NombreSector:nombreSector,DescripcionSector:descripcionSector,EstadoSector:estadoSector},function (responsetext) {
                   if(responsetext == "200"){

                        swal("Cambios Guardados", "Los cambios del sector han guardado exitosamente", "success").then((willDelete) => {
                          if (willDelete) {                          
                            window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/sector/listarsectores.jsp";
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
////////////////// LISTAR TODOS LOS SECTORES ////////////////////////////
function listarSectores() {

    $.post("/SaleslandColombiaApp/sector/versectores", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");

        } else {
            $("#listadoSectores").html("");
            $("#listadoSectores").append(responseText);
        }

    });

}
//REGISTRAR CARGOS EN LA EMPRESA//
$('#frmRegistrarCargo').validate({
    rules: {
        NombreCargo: {

            required: true,
            minlength: 5
        },
        DescripcionCargo: {

            required: true,
            minlength: 15,
            maxlength: 80
        },
        Salario:{
            required: true,
            min: 781.242
        }
     
        
        
    }, messages: {

        NombreCargo: {

            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionCargo: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        Salario: {
            required: "Este campo es requerido",
            min: "El valor minimo es de 781.242"
            
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

                        var nombreCargo = $("#txtNombreCargo").val();
                        var descripcionCargo = $("#txtDescripcionCargo").val();
                        var salario = $("#txtSalario").val();
                        $.post("../../../cargo/registrar", {NombreCargo: nombreCargo, DescripcionCargo: descripcionCargo, Salario: salario}, function (responsetext) {
                            if (responsetext == "200") {

                                swal("Registro exitoso", "El sector ha sido registrado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {
                                        //window.location = "/FutPlay/GaiaTemplate/index.html";
                                    }
                                });

                            } else {
                                swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error").then((willDelete) => {

                                });
                            }
                        });
                    }
                });
    }
});
////////////////// LISTAR TODOS LOS CARGOS ////////////////////////////
function listarCargos() {
    $.post("/SaleslandColombiaApp/cargo/vercargos", function (responseText) {
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos tus datos no fueron cargados.", "error");
        } else {
            $("#listadoCargos").html("");
            $("#listadoCargos").append(responseText);
            swal("Ok", "Listado", "success");
        }
    });
}
///////////////// CARGAR COMBO DE SECTORES /////////////////////////////////
function cargarSectores(){
    
    $.post("/SaleslandColombiaApp/sector/cargarcombosector",function (responseText){
        
        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocuttió un error al intentar cargar los sectores.", "error");
        }else{
            var dt = JSON.parse(responseText);   
            for (var key in dt) {
                if (dt.hasOwnProperty(key)) {
                  var val = dt[key];
                    $("#cmbSector").append("<option value='"+val['IdSector']+"'>"+val['NombreSector']+"</option>")
                    
                }
            }
        } 
        
    });
}
///////////////////////// REGISTRAR CANAL ///////////////////////////////////////
$('#frmRegistrarCanal').validate({
    rules: {
        NombreCanal: {
            required: true,
            minlength: 5
        },
        DescripcionCanal: {
            required: true,
            minlength: 15,
            maxlength: 80
        },
        Sector:{
            required: true
        }
    }, messages: {

        NombreCanal: {
            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionCanal: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        Sector:{
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
                
                var sectorCanal = $("#cmbSector").val();
                var nombreCanal = $("#txtNombreCanal").val();
                var descripcionCanal = $("#txtDescripcionCanal").val();
                $.post("/SaleslandColombiaApp/canal/registrar",{SectorCanal:sectorCanal,NombreCanal:nombreCanal,DescripcionCanal:descripcionCanal},function (responsetext) {
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
////////////////// LISTAR TODOS LOS CANALES ////////////////////////////
function listarCanales() {

    $.post("/SaleslandColombiaApp/canal/varcanales", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
            
        }else{
             
            $("#listadoSectores").html("");           
            $("#listadoCanales").append(responseText);            
        } 
        
    });
}
///////////////////// VER DATOS CANAL ///////////////////////////////
function verDatosCanal(){
    
    var url = ""+window.location+"";
    var IdCanal = url.split("_");
    $.post("/SaleslandColombiaApp/canal/cargardatoscanal",{idCanal:IdCanal[1]},function(responseText){
       
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un erro en el servidor, por favor intentalo nuevamente", "error");
        }else{
            
            var dt = JSON.parse(responseText);
            console.log(dt);
            if (dt[3] == "Activo") {
                $("#cmbEditarEstadoCanal").val('Activo');
            }else{
                
                $("#cmbEditarEstadoCanal").val('Inactivo');
            }
            $("#txtEditarNombreCanal").val(dt[1]);
            $("#txtEditarDescripcionCanal").val(dt[2]);
            $("#cmbSector").val(""+dt[4]+"");
        }
        
    });
    
}
/////////////////////////// EDITAR CANAL //////////////////////////////////////////////
$('#frmEditarCanal').validate({
    rules:{
        EditarEstadoCanal:{
            
          required:true  
        },
        EditarNombreCanal:{
            required:true,
            minlength:5
        },
        EditarDescripcionCanal:{
            
            required:true,
            minlength:15,
            maxlength:80
        },
        EditarEstadoSectorCanal:{
            
            required:true,
            
        }
    },messages:{
        EditarEstadoCanal:{
            
          required:"Este campo es requerido"  
        },
        EditarNombreCanal:{
            required:"Este campo es requerido",
            minlength:"Ingresa 5 caracteres como minimo"
        },
        EditarDescripcionCanal:{
            required:"Este campo es requerido",
            minlength:"Ingresa 15 caracteres como minimo",
            maxlength:"Ingresa 80 caracteres como maximo"
        },
        EditarEstadoSectorCanal:{
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

        title: "Editar Canal",
        text: "¿Está seguro que desea reemplazar los datos del canal?",
        icon: "info",
        buttons: true,
        closeonconfirm: false,
        buttons: ["No, Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {

              var url = ""+window.location+"";
              var idCanal = url.split("_");
              var nombreCanal = $("#txtEditarNombreCanal").val();
              var descripcionCanal = $("#txtEditarDescripcionCanal").val();
              var estadoCanal = $("#cmbEditarEstadoCanal").val();
              var sectorCanal = $("#cmbSector").val();
              $.post("/SaleslandColombiaApp/canal/editarcanal",{IdCanal:idCanal[1],NombreCanal:nombreCanal,DescripcionCanal:descripcionCanal,EstadoCanal:estadoCanal,SectorCanal:sectorCanal},function (responsetext) {
                   if(responsetext == "200"){

                        swal("Cambios Guardados", "Los cambios del sector han guardado exitosamente", "success").then((willDelete) => {
                          if (willDelete) {                          
                            window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp";
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
//////////////////////////////// REGISTRAR USUARIO ///////////////////////////////////
$("#frmRegistrarUsuario").validate({
    
    rules:{
        
        TipoDocumentoUsuario:{
            
            required:true
        },
    }
});