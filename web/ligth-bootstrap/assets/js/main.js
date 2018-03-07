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
////////////////////Funciones para validar password email y telefono////////////////7
    $.validator.addMethod("pwcheck1", function(value) {
       return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) 
           && /[a-z]/.test(value) // evalua que tenga una minuscula como minimo
    });
    $.validator.addMethod("pwcheck2", function(value) {
       return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) 
           && /\d/.test(value) // evalua que tenga un digito como minimo
    });
    $.validator.addMethod("pwcheck3", function(value) {
       return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) 
           && /[A-Z]/.test(value) // evalua que tenga una mayuscula como minimo
    });
    $.validator.addMethod("validarEmail", function(value){
       var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
       return pattern.test(value);
    });
    $.validator.addMethod("validarTelefono", function(value){
       var pattern = /^3[0,1,2,3,5][0-9]{8}$/;
       return pattern.test(value);
    });
//////////////////////////////// REGISTRAR USUARIO ///////////////////////////////////
$("#frmRegistrarUsuario").validate({
    
    rules:{
        
        TipoDocumentoUsuario:{
            
            required:true
        },DocumentoUsuario:{
            required:true,
            maxlength:15,
            minlength:10
        },NombreUsuario:{
            required:true,
            minlength:3,
            text:true
        },ApellidoUsuario:{
            required: true,
            minlength:3
        },EmailUsuario:{
            email:true,
            required:true,
            validarEmail:true
            
        },ContrseniaUsuario:{
            
            required:true,
            minlength:8,
            pwcheck1:true,
            pwcheck2:true,
            pwcheck3:true
            
        },ConfirmarContraseniaUsuario:{
            
            equalTo:"#txtConfirmarContraseniaUsuario"
        }
    },messages:{
        
        TipoDocumentoUsuario:{
            
            required:"Este campo es requerido"
            
        },DocumentoUsuario:{
            required:"Este campo es requerido",
            maxlength:"Ingresa 15 caracteres como maximo",
            minlength:"Ingresa 10 caracteres como minimo"
            
        },NombreUsuario:{
            required:"Este campo es requerido",
            minlength:"Ingresa 3 caracteres como minimo",
            text:"Solo se admite texto en este campo"
        },ApellidoUsuario:{
            required: "Este campo es requerido",
            minlength:"Ingresa como minimo 3 caracteres"
        },EmailUsuario:{
            email:"Ingresa una direccion de correo electronico valida",
            required:"Este campo es requerido",
            validarEmail:"Ingresa una direccion de correo electronico valida"
            
        },ContrseniaUsuario:{
            
            required:"Este campo es requerido",
            minlength:"Ingresa 8 caracteres como minimo",
            pwcheck1:"La contraseña debe contener una minuscula como minimo",
            pwcheck2:"La contraseña debe contener un digito como minimo",
            pwcheck3:"La contraseña debe contener una mayuscula como minimo"
            
        },ConfirmarContraseniaUsuario:{
            
            equalTo:"La contraseña no coincide"
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


          alert("Validacion correcta (._.)");
//        swal({
//
//        title: "Editar Canal",
//        text: "¿Está seguro que desea reemplazar los datos del canal?",
//        icon: "info",
//        buttons: true,
//        closeonconfirm: false,
//        buttons: ["No, Cancelar", "Sí"]
//        })
//        .then((willDelete) => {
//            if (willDelete) {
//
//              var url = ""+window.location+"";
//              var idCanal = url.split("_");
//              var nombreCanal = $("#txtEditarNombreCanal").val();
//              var descripcionCanal = $("#txtEditarDescripcionCanal").val();
//              var estadoCanal = $("#cmbEditarEstadoCanal").val();
//              var sectorCanal = $("#cmbSector").val();
//              $.post("/SaleslandColombiaApp/canal/editarcanal",{IdCanal:idCanal[1],NombreCanal:nombreCanal,DescripcionCanal:descripcionCanal,EstadoCanal:estadoCanal,SectorCanal:sectorCanal},function (responsetext) {
//                   if(responsetext == "200"){
//
//                        swal("Cambios Guardados", "Los cambios del sector han guardado exitosamente", "success").then((willDelete) => {
//                          if (willDelete) {                          
//                            window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/canal/listarcanal.jsp";
//                          }
//                        });
//
//                    }else{                    
//                        swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error").then((willDelete) => {
//                         
//                        });                                        
//                    }                        
//                });          
//            }
//        });                        
    }  
});