    $('#frmRegistrarSector').validate({
    rules: {
        NombreSector: {
            required: true,
            minlength: 2
        },
        DescripcionSector: {
            required: true,
            minlength: 15,
            maxlength: 80
        }
    }, messages: {

        NombreSector: {
            required: "Este campo es requerido",
            minlength: "Ingresa 2 caracteres como minimo"
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
        text: "¿Estás seguro que desea realizar el registro?",
        icon: "info",
        buttons: true,
        closeonconfirm: false,
        buttons: ["Cancelar", "Sí"]
        }).then((willDelete) => {
            if (willDelete) {

                var nombreSector = $("#txtNombreSector").val();
                var descripcionSector = $("#txtDescripcionSector").val();
                $.post("../../../sector/registrar",{NombreSector:nombreSector,DescripcionSector:descripcionSector},function (responsetext) {
                    if(responsetext == "200"){

                        swal("Registro exitoso", "El sector ha sido registrado exitosamente", "success").then((willDelete) => {
                            if (willDelete) {
                                $("#modalRegistrarSector").removeClass('show');
                                $("body").removeClass('modal-open');
                                $("body").css("padding-right","");
                                $("div").removeClass('modal-backdrop');
                                                                                                   
                                listarSectores();
                                
                                $("#txtNombreSector").val("");
                                $("#txtDescripcionSector").val("");
                            }
                        });

                    } else {
                        swal("Ocurrio un error", "Lo sentimos, los datos del sector no fueron guardados, por favor intentalo nuevamente.", "error");
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
function verDatosSector(id){
                
    $.post("/SaleslandColombiaApp/sector/cargardatossector",{idSector:id},function(responseText){

        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un erro en el servidor, por favor intentalo nuevamente", "error");
        }else{

            var dt = JSON.parse(responseText);            
            $.each(dt, function (){
                $("#idSector").val(id);
                if (this['Estado'] == "Activo") {                                
                    $("#cmbEditarEstadoSector").val("Activo");
                }else{
                    $("#cmbEditarEstadoSector").val("Inactivo");
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

        title: "Actualizar Sector",
        text: "¿Está seguro que desea reemplazar los datos del sector?",
        icon: "warning",
        buttons: true,
        buttons: {
            cancel:{
                text: "Cancelar",
                value: true,
                visible: true,
                closeModal: true
            },
            confirm: {
                text: "Sí",
                value: true,
                visible: true,
                closeModal: false
            }
        }
        })
        .then((willDelete) => {
            if (willDelete) {

              var idSector = $("#idSector").val();
              var nombreSector = $("#txtEditarNombreSector").val();
              var descripcionSector = $("#txtEditarDescripcionSector").val();
              var estadoSector = $("#cmbEditarEstadoSector").val();
              $.post("/SaleslandColombiaApp/sector/editarsector",{IdSector:idSector,NombreSector:nombreSector,DescripcionSector:descripcionSector,EstadoSector:estadoSector},function (responsetext) {
                   if(responsetext == "200"){

                        swal("Sector Actualizado", "Los datos del sector se han actualizado satisfactoriamente.", "success").then((willDelete) => {
                          if (willDelete) {
                              
                              $("#ModalEditarSector").modal('toggle');                             
                              listarSectores();
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
    $("#tablaModificada").html("");
    $("#tablaModificada").append("<div class='toolbar' id='toolbar'>"
                                    +"<button class='btn btn-outline btn-round' data-toggle='modal' data-target='#modalRegistrarSector'>"                                                
                                        +"Nuevo"
                                            +"<span class='btn-label'>"
                                                +"<i class='fa fa-plus'></i>"
                                            +"</span>"
                                    +"</button>"
                                    +"<!--        Here you can write extra buttons/actions for the toolbar              -->"
                                +"</div>"
                                +"<table id='bootstrap-table' data-toolbar='#toolbar' class='table'>"
                                    +"<thead>"
                                        +"<tr>"
                                            +"<th class='text-center'>#</th>"
                                            +"<th>Nombre</th>"
                                            +"<th>Descripción</th>"
                                            +"<th class='text-right'>Estado</th>"
                                            +"<th class='text-right'>Acciones</th>"
                                        +"</tr>"
                                    +"</thead>"
                                    +"<tbody id='listadoSectores'>"

                                    +"</tbody>"
                                +"</table>");
                        
    $.post("/SaleslandColombiaApp/sector/versectores", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");

        } else {
            $("#listadoSectores").append(responseText);
        }
        // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
        //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto        
        botstrapPaginacionTabla(5,false, true, true, true, 'right');
       
    });

}
////////////////// LISTAR TODOS LOS CARGOS ////////////////////////////
function listarCargos() {
    $("#tablaModificada").html("");
    $("#tablaModificada").append("<div class='toolbar' id='toolbar'>"
                                    +"<button class='btn btn-outline btn-round' data-toggle='modal' data-target='#modalRegistrarCargo'>"                                                
                                        +"Nuevo"
                                            +"<span class='btn-label'>"
                                                +"<i class='fa fa-plus'></i>"
                                            +"</span>"
                                    +"</button>"
                                    +"<!--        Here you can write extra buttons/actions for the toolbar              -->"
                                +"</div>"
                                +"<table id='bootstrap-table' data-toolbar='#toolbar' class='table'>"
                                    +"<thead>"
                                        +"<tr>"
                                            +"<th class='text-center'>#</th>"
                                            +"<th class='text-center'>Nombre</th>"
                                            +"<th class='text-center'>Descripción</th>"                                            
                                            +"<th class='text-center'>Tipo</th>"
                                            +"<th class='text-center'>Sector</th>"
                                            +"<th class='text-center'>Canal</th>"
                                            +"<th class='text-center'>Area</th>"
                                            +"<th class='text-right'>Estado</th>"
                                            +"<th class='text-right'>Acciones</th>"
                                        +"</tr>"
                                    +"</thead>"
                                    +"<tbody id='listadoCargos'>"

                                    +"</tbody>"
                                +"</table>");
    
    $.post("/SaleslandColombiaApp/cargo/vercargos", function (responseText) {
        
        if (responseText == "500") {
            
            swal("Ocurrio un error", "Lo sentimos tus datos no fueron cargados.", "error");
            
        } else {
            
            $("#listadoCargos").append(responseText);  
            // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
            //botstrapPaginacionTabla(tamanio,showRefresh, search, showToggle, showColumns, alineacion){
            botstrapPaginacionTabla(5, false, true, false, true, 'right');
        }
        
    });
}

//////////////////////////   FUNCION PARA CARGAR LOS DATOS DEL CARGO ////////////////////////////////////
function verDatosCargo(id){
    
    $.post("/SaleslandColombiaApp/cargo/cargarCargo",{id:id},function (responseText) {
       
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo ocurrió un error al intentar cargar los datos del cargo.", "error");
        }else{
            
            var dt = JSON.parse(responseText);
            
            $("#idCargo").val(dt[0]);
            $("#txtEditarNombreCargo").val(dt[1]);
            $("#txtEditarDescripcionCargo").val(dt[2]);
            $("#cmbEditarTipo").val(dt[3]);
            $("#cmbEditarEstadoCargo").val(dt[4]);
            
            
            $("#cmbEditarTipo").prop('disabled', true);
            if (dt[3] == "Director") {
                
                $("#nEditarSector").show();
                $("#nEditarCanal").hide();
                $("#nEditarArea").hide();
                
                cargarSectores();
                
                $("#cmbEditarSector").val(dt[5]);
                $("#cmbEditarSector").prop('disabled', true);
            }else if (dt[3] == "JefeCanal" || dt[3] == "CoordinadorCanal") {
                                
                $("#nEditarSector").show();
                $("#nEditarCanal").show();
                $("#nEditarArea").hide();
                
                cargarSectores();
                cargarCanal();
                
                $("#cmbEditarSector").val(dt[5]);
                $("#cmbEditarSector").prop('disabled', true);
                $("#cmbEditarCanal").val(dt[6]);
                $("#cmbEditarCanal").prop('disabled', true);
                
            }else if (dt[3] == "JefeArea" || dt[3] == "Empleado") {
                    
                cargarAreas();
                cargarSectores();
                cargarCanal();                
                
                
                $("#nEditarSector").show();
                $("#nEditarCanal").show();
                $("#nEditarArea").show();
                                  
                $("#cmbEditarSector").val(dt[5]);
                $("#cmbEditarCanal").val(dt[6]);                
                setTimeout(function (){                    
                    $("#cmbEditarArea").val(dt[7]);                    
                },300);
                                
                $("#cmbEditarSector").prop('disabled', true);
                $("#cmbEditarCanal").prop('disabled', true);
                $("#cmbEditarArea").prop('disabled', true);
                                
            }                                    
            
        }
        
    });    
}
  

///////////////// CARGAR COMBO DE SECTORES /////////////////////////////////
function cargarSectores(){
    
    $.post("/SaleslandColombiaApp/sector/cargarcombosector",function (responseText){
        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocurrió un error al intentar cargar los sectores.", "error");
        }else{
            $("#cmbEditarSector").html("");
            $("#cmbSector").html("");
            var dt = JSON.parse(responseText);
            
            for (var key in dt) {
                if (dt.hasOwnProperty(key)) {
                    var val = dt[key];                    
                    $("#cmbEditarSector").append("<option value='"+val['IdSector']+"'>"+val['NombreSector']+"</option>");
                    $("#cmbSector").append("<option value='"+val['IdSector']+"'>"+val['NombreSector']+"</option>");                    
                }
            }
        } 
        
    });
}
//////////////// HACE LA CARGA DE LOS SECTORES DEPENDIENTES ////////////////////////////////////
function cargarSectoresDependientes(){    
    $.post("/SaleslandColombiaApp/sector/cargarcombosector",function (responseText){
        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocuttió un error al intentar cargar los sectores.", "error");
        }else{
            $("#cmbEditarSector").html("");
            $("#cmbSector").html("");
            
            var dt = JSON.parse(responseText);             
            
            $("#cmbSector").append("<option>Seleccione el sector</option>");
            $("#cmbEditarSector").append("<option>Seleccione el sector</option>");
                                               
            for (var key in dt) {
                if (dt.hasOwnProperty(key)) {
                    var val = dt[key];                    
                    $("#cmbEditarSector").append("<option value='"+val['IdSector']+"'>"+val['NombreSector']+"</option>");
                    $("#cmbSector").append("<option value='"+val['IdSector']+"'>"+val['NombreSector']+"</option>");                    
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
            minlength: 3
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
            minlength: "Ingresa 3 caracteres como minimo"
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
    },submitHandler: function () {
        if ($("#cmbSector").val() == "Selecciona el sector") {
                    
            swal("Selecciona el sector", "Para poder continuar con el registro por favor selecciona el sector.", "warning");

        }else{
            
            swal({
            title: "Confirmar Datos",
            text: "¿Estás seguro que desea realizar el registro?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
            }).then((willDelete) => {
                if (willDelete) {

                    var sectorCanal = $("#cmbSector").val();
                    var nombreCanal = $("#txtNombreCanal").val();
                    var descripcionCanal = $("#txtDescripcionCanal").val();
                    $.post("/SaleslandColombiaApp/canal/registrar",{SectorCanal:sectorCanal,NombreCanal:nombreCanal,DescripcionCanal:descripcionCanal},function (responsetext) {
                        if(responsetext == "200"){

                            swal("Registro exitoso", "El canal ha sido registrado exitosamente", "success").then((willDelete) => {
                                if (willDelete) {
                                    $("#modalRegistrarCanal").modal('toggle');
                                    listarCanales();
                                }
                            });

                        } else {
                            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
                        }
                    });                                                                   
                }
            });
            
        }        
    }
});
////////////////// LISTAR TODOS LOS CANALES ////////////////////////////
function listarCanales() {

    $("#tablaModificada").html("");
    $("#tablaModificada").append("<div class='toolbar' id='toolbar'>"
                                    +"<button class='btn btn-outline btn-round' data-toggle='modal' data-target='#modalRegistrarCanal'>"                                                
                                        +"Nuevo"
                                            +"<span class='btn-label'>"
                                                +"<i class='fa fa-plus'></i>"
                                            +"</span>"
                                    +"</button>"
                                    +"<!--        Here you can write extra buttons/actions for the toolbar              -->"
                                +"</div>"
                                +"<table id='bootstrap-table' data-toolbar='#toolbar' class='table'>"
                                    +"<thead>"
                                        +"<tr>"
                                            +"<th class='text-center'>#</th>"
                                            +"<th>Nombre</th>"
                                            +"<th>Descripción</th>"
                                            +"<th>Sector</th>"   
                                            +"<th class='text-right'>Estado</th>"
                                            +"<th class='text-right'>Acciones</th>"
                                        +"</tr>"
                                    +"</thead>"
                                    +"<tbody id='listadoCanales'>"

                                    +"</tbody>"
                                +"</table>");

    $.post("/SaleslandColombiaApp/canal/varcanales", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
            
        }else{
            
            $("#listadoCanales").append(responseText);            
        }
         // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
        //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto
        botstrapPaginacionTabla(5, false, true, true, true, 'right');
    });
}
///////////////////// VER DATOS CANAL ///////////////////////////////
function verDatosCanal(id){
    $("#idCanal").val(id);
    $.post("/SaleslandColombiaApp/canal/cargardatoscanal",{idCanal:id},function(responseText){
       
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un erro en el servidor, por favor intentalo nuevamente", "error");
        }else{
            
            var dt = JSON.parse(responseText);
            
            if (dt[3] == "Activo") {                
                $("#cmbEditarEstadoCanal").val('Activo');
                $("#cmbEditarEstadoCanal").attr("disabled", false);
                $("#lblSector").show();
                $("#cmbEditarSector").show();
                 
            }else{
                if (dt[6] == "Inactivo") {
                    
                    $("#cmbEditarEstadoCanal").attr("disabled", "disabled");
                }
                $("#cmbEditarEstadoCanal").val('Inactivo');
                $("#lblSector").hide();
                $("#cmbEditarSector").hide();
                
                
            }
            $("#txtEditarNombreCanal").val(dt[1]);
            $("#txtEditarDescripcionCanal").val(dt[2]);    
            $("#cmbEditarSector").val(""+dt[4]+"");
            
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
            minlength:3
        },
        EditarDescripcionCanal:{
            
            required:true,
            minlength:15,
            maxlength:80
        },
        EditarSectorCanal:{
            
            required:true
            
        }
    },messages:{
        EditarEstadoCanal:{
            
          required:"Este campo es requerido"  
        },
        EditarNombreCanal:{
            required:"Este campo es requerido",
            minlength:"Ingresa 3 caracteres como minimo"
        },
        EditarDescripcionCanal:{
            required:"Este campo es requerido",
            minlength:"Ingresa 15 caracteres como minimo",
            maxlength:"Ingresa 80 caracteres como maximo"
        },
        EditarSectorCanal:{
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
            text: "¿Estás seguro que deseas reemplazar los datos del canal?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
        }).then((willDelete) => {
            if (willDelete) {

                var idCanal = $("#idCanal").val();
                var nombreCanal = $("#txtEditarNombreCanal").val();
                var descripcionCanal = $("#txtEditarDescripcionCanal").val();
                var estadoCanal = $("#cmbEditarEstadoCanal").val();
                var sectorCanal = $("#cmbEditarSector").val();
                $.post("/SaleslandColombiaApp/canal/editarcanal",{IdCanal:idCanal,NombreCanal:nombreCanal,DescripcionCanal:descripcionCanal,EstadoCanal:estadoCanal,SectorCanal:sectorCanal},function (responsetext) {
                     if(responsetext == "200"){

                        swal("Cambios Guardados", "Los cambios del sector han guardado exitosamente", "success").then((willDelete) => {
                            if (willDelete) {

                                $("#ModalEditarCanal").modal('toggle');                                  
                                listarCanales();
                            }
                        });

                    }else{                    
                        swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");                                        
                    }                        
                });  


            }
        });                                
    }        
});
///////////////////////// CARGAR COMBO CARGO ////////////////////// BORRAR
function cargarCargoOOOOOO(idSector){
    alert("combo cargo functio ---> "+idSector);
    $.post("/SaleslandColombiaApp/cargo/getallcargosregistrousuario",{IdSector:idSector},function (responseText){
        if (responseText.equals("500")) {
            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
        }else{
            
            alert(responseText);
            var dt = JSON.parse(responseText);
            for (var key in dt) {
                if (dt.hasOwnProperty(key)) {
                    var val = dt[key];
                    alert("INGRESANDO LA DATA AL COMBO");
                    $('#lolComboCargo').append(new Option("text", 54654));
                    alert("FINISH DATA INGRESADA ---> " + val['IdCargo'] +" ------> "+val['NombreCargo']);
                }
            }
            
        }
        
    });
    
}
//////////////////////// CARGAR TABLA PARA EL REGISTRO /////////////////////////////
function cargarCargosSectores(){
    
    $.post("/SaleslandColombiaApp/usuario/cargartablaregistro",function (responseText){
        if (responseText == "500") {
            swal("Error al cargar los cargos", "Ocurrió un erro en el servidor, favor intentalo más tarde.", "error");
        }else{
            
            $("#listadoCargosSectores").append(responseText);
            // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
            //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto
            botstrapPaginacionTabla(5, false, true, false, false, 'left');
        }
        
    });
}
////////// FUNCION PARA VALIDAR EL FORMATO DE LA IMAGEN ////////////////////////////
$("#fileImagenUsuario").change(function(){
    validarImagen(this);
});

var fileTypes = ["jpg","jpeg","png"];
function validarImagen(imagen){
    if (imagen.files && imagen.files[0]) {
        var extension = imagen.files[0].name.split('.').pop().toLowerCase(),  //file extension from input file
        isSuccess = fileTypes.indexOf(extension) > -1;  //is extension in acceptable types

        if (isSuccess) { 
            var reader = new FileReader();
            reader.onload = function (e) {
                $("#fileImagenUsuarioNombre").val(imagen.files[0].name);
                $("#txtFoto").val(imagen.files[0].name);
            }
            reader.readAsDataURL(imagen.files[0]);
        }
        else{
            $("#fileImagenUsuario").val("");
            swal("Advertencia","El archivo seleccionado no es una imagen, solo puedes subir archivos con extension jpg, jpeg y png.","warning");
        }
    }
}

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
    
    //Validar fecha: https://stackoverflow.com/questions/43276378/jquery-validate-date-between-a-date-range
    $.validator.addMethod("dateRange", function(value, element, params) {
        
       try {           
            var date = new Date(value);
            if (date >= params.from && date <= params.to) {
              return true;
            }
        } catch (e) {}
      return false;
    }, 'message');

    var fromDate = new Date("2017-02-01");
    
    var toDate = new Date("2017-12-31");
    $.validator.addClassRules({
      myDateFieldRangeValidate: {
        dateRange: {
          from: fromDate,
          to: toDate
        }
      }
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
            maxlength:50            
        },ApellidoUsuario:{
            required: true,
            minlength:3,
            maxlength:50
        },EmailUsuario:{
            email:true,
            required:true,
            validarEmail:true

        },ContraseniaUsuario:{

            required:true,
            minlength:8,
            pwcheck1:true,
            pwcheck2:true,
            pwcheck3:true

        },ConfirmarContraseniaUsuario:{
            
            required:true,
            equalTo:"#txtContraseniaUsuario"
        },DireccionUsuario:{
            
            required:true,
            minlength:10,
            maxlength:35
        },GeneroUsuario:{            
            required:true
        },CelularUsuario:{
            
            required:true,
            validarTelefono:true
        },TelefonoUsuario:{
            
            required:true
            
        },FechaNacimientoUsuario:{
            
            required:true            
        },HorarioUsuario:{
            
            required:true
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
            maxlength:"Ingresa 50 caracteres como maximo"
        },ApellidoUsuario:{
            required: "Este campo es requerido",
            minlength:"Ingresa como minimo 3 caracteres",
            maxlength:"Ingresa como maximo 50 caracteres"
        },EmailUsuario:{
            email:"Ingresa una direccion de correo electronico valida",
            required:"Este campo es requerido",
            validarEmail:"Ingresa una direccion de correo electronico valida"

        },ContraseniaUsuario:{

            required:"Este campo es requerido",
            minlength:"Ingresa 8 caracteres como minimo",
            pwcheck1:"La contraseña debe contener una minuscula como minimo",
            pwcheck2:"La contraseña debe contener un número como minimo",
            pwcheck3:"La contraseña debe contener una mayuscula como minimo"

        },ConfirmarContraseniaUsuario:{
            required:"Este campo es requerido",
            equalTo:"Las contraseñas no coinciden"
        },DireccionUsuario:{
            
            required:"Este campo es requerido",
            minlength:"Ingresa 10 caracteres como minimo",
            maxlength:"Ingresa 35 caracteres como maximo"
        },GeneroUsuario:{            
            required:"Este campo es requerido"
        },CelularUsuario:{            
            required:"Este campo es requerido",
            validarTelefono:"Ingresa un número de celular valido"
        },TelefonoUsuario:{            
            required:"Este campo es requerido"
        },FechaNacimientoUsuario:{            
            required:"Este campo es requerido"            
        },HorarioUsuario:{
            
            required:"Este campo es requerido"
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

        var cargo = $('input[name = CargoUsuario]:checked').val();
        if (cargo) {
            
            swal({
                title: "Confirmar Datos",
                text: "¿Estas seguro de que los datos del usuario son correctos?",
                icon: "info",
                buttons: true,
                closeonconfirm: false,
                buttons: ["Cancelar", "Sí"]
                }).then((willDelete) => {
                    if (willDelete) {
                    
                      var TipoDocumento = $("#cmbTipoDocumentoUsuario").val();
                      var Documento = $("#txtDocumentoUsuario").val();
                      var Nombres = $("#txtNombreUsuario").val();
                      var Apellidos = $("#txtApellidoUsuario").val();
                      var Email = $("#txtEmailUsuario").val();
                      var Contrasenia = $("#txtConfirmarContraseniaUsuario").val();
                      var Direccion = $("#txtDireccionUsuario").val();
                      var Genero = $("#cmbGeneroUsuario").val();
                      var Celular = $("#txtCelularUsuario").val();
                      var Telefono = $("#txtTelefonoUSuario").val();
                      var FechaNacnimiento = $("#datetimepicker").val();
                      var ImagenPerfil = $("#fileImagenUsuarioNombre").val();
                      var Horario = $("#cmbHorarioUsuario").val();
                      //Agregar el CARGO
                        ////////Subir IMAGEN DE PERFIL/////////
                        var data = new FormData();
                        $.each($('#fileImagenUsuario')[0].files, function(i, file) {
                            data.append('file-'+i, file);
                        });
                        $.ajax({
                            url: '/SaleslandColombiaApp/SubirImagenPerfil',
                            data: data,
                            dataType: 'text',
                            processData: false,
                            contentType: false,
                            type: 'POST'
                        });
                        $.post("/SaleslandColombiaApp/usuario/registrar",{TipoDocumentoUsuario:TipoDocumento,DocumentoUsuario:Documento,NombreUsuario:Nombres,ApellidoUsuario:Apellidos,EmailUsuario:Email,ContraseniaUsuario:Contrasenia,DireccionUsuario:Direccion,GeneroUsuario:Genero,CelularUsuario:Celular,TelefonoUsuario:Telefono,FechaNacimientoUsuario:FechaNacnimiento,ImagenPerfilUsuario:ImagenPerfil,CargoUsuario:cargo,Horario:Horario},function (responsetext) {                                
                            if(responsetext == "200"){

                                swal("Cambios Guardados", "El usuario ha sido registrado exitosamente", "success").then((willDelete) => {
                                  if (willDelete) {                          
                                    window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/listadousuarios.jsp";
                                  }
                                });

                            }else if(responsetext == "226"){

                                swal("Usuario no registrado", "Es probable que datos personales del usuario correspondan a los datos personales de un usuario registrado anteriormente", "warning");

                            }else{                    
                                swal("Ocurrio un error", "Lo sentimos, tus datos no fueron registrados por favor intentalo nuevamente.", "error");                                        
                            }                        
                        });          
                }
            });
        }else{
            
            swal("Selecciona el cargo", "Por favor selecciona el cargo para poder finalizar el registro.", "warning");
            
        }
                                
    }  
});

///////////////// FUNCION PARA INICIALIZAR LOS FORMULARIOS DEL REGISTRO //////////////////////
function InicializarFormularioRegistro() {   
    // Wizard Initialization
    $('.card-wizard').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'nextSelector': '.btn-next',
        'previousSelector': '.btn-previous',

        onNext: function(tab, navigation, index) {
            var $valid = $('#frmRegistrarUsuario').valid();
            if (!$valid) {
                $validator.focusInvalid();
                return false;
            }
        },

        onInit: function(tab, navigation, index) {
            //check number of tabs and fill the entire row
            var $total = navigation.find('li').length;
            var $wizard = navigation.closest('.card-wizard');

            $first_li = navigation.find('li:first-child a').html();
            $moving_div = $('<div class="moving-tab">' + $first_li + '</div>');
            $('.card-wizard .wizard-navigation').append($moving_div);

            refreshAnimation($wizard, index);

            $('.moving-tab').css('transition', 'transform 0s');
        },

        onTabClick: function(tab, navigation, index) {
            var $valid = $('#frmRegistrarUsuario').valid();

            if (!$valid) {
                return false;
            } else {
                return true;
            }
        },

        onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;

            var $wizard = navigation.closest('.card-wizard');

            // If it's the last tab then hide the last button and show the finish instead
            if ($current >= $total) {
                $($wizard).find('.btn-next').hide();
                $($wizard).find('.btn-finish').show();
            } else {
                $($wizard).find('.btn-next').show();
                $($wizard).find('.btn-finish').hide();
            }

            button_text = navigation.find('li:nth-child(' + $current + ') a').html();

            setTimeout(function() {
                $('.moving-tab').text(button_text);
            }, 150);

            var checkbox = $('.footer-checkbox');

            if (!index == 0) {
                $(checkbox).css({
                    'opacity': '0',
                    'visibility': 'hidden',
                    'position': 'absolute'
                });
            } else {
                $(checkbox).css({
                    'opacity': '1',
                    'visibility': 'visible'
                });
            }

            refreshAnimation($wizard, index);
        }
    });


    // Prepare the preview for profile picture
    $("#wizard-picture").change(function() {
        readURL(this);
    });

    $('[data-toggle="wizard-radio"]').click(function() {
        wizard = $(this).closest('.card-wizard');
        wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
        $(this).addClass('active');
        $(wizard).find('[type="radio"]').removeAttr('checked');
        $(this).find('[type="radio"]').attr('checked', 'true');
    });

    $('[data-toggle="wizard-checkbox"]').click(function() {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $(this).find('[type="checkbox"]').removeAttr('checked');
        } else {
            $(this).addClass('active');
            $(this).find('[type="checkbox"]').attr('checked', 'true');
        }
    });

    $('.set-full-height').css('height', 'auto');

    //Function to show image before upload

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(window).resize(function() {
        $('.card-wizard').each(function() {
            $wizard = $(this);

            index = $wizard.bootstrapWizard('currentIndex');
            refreshAnimation($wizard, index);

            $('.moving-tab').css({
                'transition': 'transform 0s'
            });
        });
    });

    function refreshAnimation($wizard, index) {
        $total = $wizard.find('.nav li').length;
        $li_width = 100 / $total;

        total_steps = $wizard.find('.nav li').length;
        move_distance = $wizard.width() / total_steps;
        index_temp = index;
        vertical_level = 0;

        mobile_device = $(document).width() < 600 && $total > 3;

        if (mobile_device) {
            move_distance = $wizard.width() / 2;
            index_temp = index % 2;
            $li_width = 50;
        }

        $wizard.find('.nav li').css('width', $li_width + '%');

        step_width = move_distance;
        move_distance = move_distance * index_temp;

        $current = index + 1;

        if ($current == 1 || (mobile_device == true && (index % 2 == 0))) {
            move_distance -= 8;
        } else if ($current == total_steps || (mobile_device == true && (index % 2 == 1))) {
            move_distance += 8;
        }

        if (mobile_device) {
            vertical_level = parseInt(index / 2);
            vertical_level = vertical_level * 38;
        }

        $wizard.find('.moving-tab').css('width', step_width);
        $('.moving-tab').css({
            'transform': 'translate3d(' + move_distance + 'px, ' + vertical_level + 'px, 0)',
            'transition': 'all 0.5s cubic-bezier(0.29, 1.42, 0.79, 1)'

        });
    }
}

////////////////////////////////// INICIO DE SESION //////////////////////////////////////////////
$("#frmIniciarSesion").validate({
    
    rules:{
        
        UsuarioSesion:{            
            required:true            
        },ContraseniaSesion:{            
            required:true
        }
    },messages:{
        
        UsuarioSesion:{            
            required:"Introduce tu correo electronico o número de documento"            
        },ContraseniaSesion:{            
            required:"Introduce tu contraseña"
        }
        
    },errorElement: 'div',
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error);
            } else {
                error.insertAfter(element);
            }
    }, submitHandler: function () {
        if ($('#remember_me').is(':checked')) {
            localStorage.docEmail = $('#txtUsuarioSesion').val();
            localStorage.checkBox = $('#remember_me').val();
        } else {
            localStorage.docEmail = '';
            localStorage.checkBox = '';
        } 
        $("#divBtn").hide();
        $("#preloader").show();
        var usuario = $("#txtUsuarioSesion").val();
        var contrasenia = $("#txtContraseniaSesion").val();        
        $.post("/SaleslandColombiaApp/usuario/IniciarSesion",{Usuario:usuario,Contrasenia:contrasenia},function (responsetext) {                                               
            $("#txtUsuarioSesion").prop('disabled', false);
            $("#txtContraseniaSesion").prop('disabled', false);
            $("#txtUsuarioSesion").focus();
            if (responsetext == "Empleado") {
                window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/empleado/indexempleado.jsp";
                
            }else if (responsetext == "Administrador") {
                window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/administrador/indexadministrador.jsp";
                
            }else if (responsetext == "403") {
                $.notify({
                    icon: "nc-icon nc-key-25",
                    message: "No puedes acceder al sistema porque tu estado actual es inactivo."
                },{
                    type: 'danger',
                    timer: 3000,
                    placement: {
                        from: 'bottom',
                        align: 'right'
                    }
                });
                $("#divBtn").show();
                $("#preloader").hide();
            }else if (responsetext == "404") {
                $.notify({
                    icon: "nc-icon nc-key-25",
                    message: "Usuario o contraseña incorrectos, por favor verifica tus datos e intentalo nuevamente."
                },{
                    type: 'danger',
                    timer: 3000,
                    placement: {
                        from: 'bottom',
                        align: 'right'
                    }
                });
                $("#divBtn").show();
                $("#preloader").hide();
            }else if (responsetext == 500) {
                $.notify({
                    icon: "nc-icon nc-fav-remove",
                    message: "Ocurrio un erro al tratar de realizar el ingreso, por favor intentalo mas tarde."
                },{
                    type: 'danger',
                    timer: 3000,
                    placement: {
                        from: 'bottom',
                        align: 'right'
                    }
                });
                $("#divBtn").show();
                $("#preloader").hide(); 
            }
            
        });

    }
    
});
//////////////////////////////// CERRAR SESION//////////////////////////////
$(".cerrarSesion").click(function (){
    swal({
        title: "Cerrar Sesión",
        text: "¿Estas seguro que deseas cerrar sesión?",
        icon: "info",
        buttons: true,
        closeonconfirm: false,
        buttons: ["No, Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {
              
                $.post("/SaleslandColombiaApp/usuario/CerrarSesion",function (responsetext) {
                    if(responsetext == "200"){
                        
                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp";

                    }else{

                        swal("Usuario no registrado", "Los datos del usuario ya se encuentran registrados.", "warning");

                    }
                });          
            }
        });
    
});
////////////////////// INGRESO Y SALIDA DEL USUARIO ////////////////////////
function ingreso(){
    
    var fullDate = new Date();
    var weekday = new Array(7);
    weekday[0] = "Domingo";
    weekday[1] = "Lunes";
    weekday[2] = "Martes";
    weekday[3] = "Miercoles";
    weekday[4] = "Jueves";
    weekday[5] = "Viernes";
    weekday[6] = "Sabado";
    var n = weekday[fullDate.getDay()];
    
    var hora = fullDate.getHours();
    var minutos = fullDate.getMinutes();
    //convert month to 2 digits
    var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
    var currentDate = fullDate.getFullYear() + "/" + twoDigitMonth + "/" + fullDate.getDate();
    
    var UsuarioID = $("#txtUsuarioIngreso").val();
    $.post("/SaleslandColombiaApp/ingreso/ingresousuario",{UsuarioID:UsuarioID,Fecha:currentDate,Hora:hora,Minutos:minutos, Dia:n},function (responseText) {
        //alert(responseText);
        websocket.send("IngresarUsuario-"+UsuarioID+"-"+currentDate);
        if (responseText == "500") {
            swal("Error", "Ocurrió un error mientras estabamos tratando de ingresa tus datos", "error");
        }else if (responseText == "302") {
            alert("Se hace el registro de la entrada o la salida");
        }else if (responseText == "406"){
            alert("Más de un suauario registrado");
        }else if (responseText == "407") {
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "El usuario ya registro entrada y salida en el dia de hoy."
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if (responseText == "404") {
            //alert("Usuario no encontrado");
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "El ingreso del usuario no ha sido registrado, por favor confirme que este registrado."
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if(responseText == "IngresoTarde"){     
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Usuario ingresado con retardo."
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
            //websocket2.send("IngresarUsuario-"+UsuarioID);
        }else if(responseText == "IngresoTemprano"){
            
            
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Usuario ingresado temprano"
            },{
                type: 'success',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if(responseText == "IngresoJusto"){
            
            
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Usuario ingresado a tiempo"
            },{
                type: 'warning',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if(responseText == "SalidaTarde"){
            
            
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Salida registrada."
            },{
                type: 'success',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if(responseText == "SalidaTemprano"){
            
            
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Salida temprana."
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else if(responseText == "SalidaJusto"){
            
            
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Salida justo a tiempo."
            },{
                type: 'warning',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }
        
    });
    $("#txtUsuarioIngreso").removeAttr("disabled"); 
    $("#txtUsuarioIngreso").focus();
    $("#txtUsuarioIngreso").val("");

}
/////////////////////////// LISTAR TODOS LOS USUARIOS REGISTRADOS ///////////////////////////////
function listarUsuarios(){
    
    
    $.post("/SaleslandColombiaApp/usuario/listarUsuarios",function (responseText){
        
        if (responseText == 500) {
            $.notify({
                icon: "nc-icon nc-spaceship",
                message: "Error el al cargar los usuarios"
            },{
                type: 'danger',
                timer: 3000,
                placement: {
                    from: 'bottom',
                    align: 'right'
                }
            });
        }else{
            
            $("#listadoUsuario").append(responseText);
        }
        // orden de datos tamaño,showRefresh, search, showToggle, showColumns
        botstrapPaginacionTabla(5, false, true, true, true);
    });
    
    
}

/////////////////// FUNCION PARA OBTENER EL PROMEDIO DE LOS INGRESOS DEL USUARIO ////////////////////7
function cargarPromedio(){
    
    $.post("/SaleslandColombiaApp/ingreso/promedioingresos",function (responseText){
        if (responseText == "500") {
            swal("Datos no cargados", "Los datos de las estadisticas del usuario no se lograron cargar, por favor intentalo nuevamente.", "error");
        }else if(responseText != "undefined"){
            var dt = JSON.parse(responseText);
            $("#numeroIngresos").text(dt[0]);
            $("#ingresosCorrectos").text(dt[1]);
            $("#ingresosErroneos").text(dt[2]);
            $("#ingresosJusto").text(dt[3]);
            
            var in2 = dt[4].split(" ");
            var in3 = dt[5].split(" ");          
            var inOk2 = dt[6].split(" ");
            var inOk3 = dt[7].split(" ");
            var inM2 = dt[8].split(" ");
            var inM3 = dt[9].split(" ");                        
            var inJ2 = dt[10].split(" ");
            var inJ3 = dt[11].split(" ");
            
            if (in3[1] == undefined) {
                
                $("#ultimoIngreso").append('Por el momento no tienes ingresos.');
            }else{
                
                $("#ultimoIngreso").append('Ultimo ingreso '+moment(in2[0]+" "+in3[1]).fromNow());
            }
            
            if (inOk3[1] == undefined) {
                
                $("#ultimoIngresoCorrecto").append('Por el momento no tienes ingresos correctos.');
            }else{
                $("#ultimoIngresoCorrecto").append('Ultimo ingreso correcto '+moment(inOk2[0]+" "+inOk3[1]).fromNow());
                
            }
            
            if (inM3[1] == undefined) {
                
                $("#ultimoIngresoErroneo").append('Por el momento no tienes ingresos erroneos.');
                
            }else{
                
                $("#ultimoIngresoErroneo").append('Ultimo ingreso erroneo '+moment(inM2[0]+" "+inM3[1]).fromNow());
                
            }
     
            if (inJ3[1] == undefined) {
                $("#ultimoIngresoJusto").append('Por el momento no tienes ingresos a tiempo.');
            }else{
                $("#ultimoIngresoJusto").append('Ultimo ingreso a tiempo '+moment(inJ2[0]+" "+inJ3[1]).fromNow());                
            }
            
        }else{
            $("#ultimoIngreso").append('Por el momento no tienes ingresos.');
            $("#ultimoIngresoCorrecto").append('Por el momento no tienes ingresos correctos.');
            $("#ultimoIngresoErroneo").append('Por el momento no tienes ingresos erroneos.');
            $("#ultimoIngresoJusto").append('Por el momento no tienes ingresos a tiempo.');
        }
        
        var correctosPorcentaje = Math.round((dt[1]/dt[0])*100);
        var erroneosPorcentaje = Math.round((dt[2]/dt[0])*100); 
        var justoPorcentaje = Math.round((dt[3]/dt[0])*100);

        var chart = new Chartist.Pie('#chartPromedio', {
          series: [correctosPorcentaje, erroneosPorcentaje, justoPorcentaje],
          labels: ["%"+correctosPorcentaje+"", "%"+erroneosPorcentaje+"", "%"+justoPorcentaje+""]
        }, {
          donut: true,
          showLabel: true,
          donutWidth: 50  
        });

        chart.on('draw', function(data) {
          if(data.type === 'slice') {
            // Get the total path length in order to use for dash array animation
            var pathLength = data.element._node.getTotalLength();

            // Set a dasharray that matches the path length as prerequisite to animate dashoffset
            data.element.attr({
              'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
            });

            // Create animation definition while also assigning an ID to the animation for later sync usage
            var animationDefinition = {
              'stroke-dashoffset': {
                id: 'anim' + data.index,
                dur: 1900,
                from: -pathLength + 'px',
                to:  '0px',
                easing: Chartist.Svg.Easing.easeOutQuint,
                // We need to use `fill: 'freeze'` otherwise our animation will fall back to initial (not visible)
                fill: 'freeze'
              }
            };

            // If this was not the first slice, we need to time the animation so that it uses the end sync event of the previous animation
            if(data.index !== 0) {
              animationDefinition['stroke-dashoffset'].begin = 'anim' + (data.index - 1) + '.end';
            }

            // We need to set an initial value before the animation starts as we are not in guided mode which would do that for us
            data.element.attr({
              'stroke-dashoffset': -pathLength + 'px'
            });

            // We can't use guided mode as the animations need to rely on setting begin manually
            // See http://gionkunz.github.io/chartist-js/api-documentation.html#chartistsvg-function-animate
            data.element.animate(animationDefinition, false);
          }
        });
        
        
    });
    
}



//////////////////////////////////////////////////////////////// CRUD DE AREAS ///////////////////////////////////////



///////////////////////// REGISTRAR AREA ///////////////////////////////////////
$('#frmRegistrarArea').validate({
    rules: {
        NombreArea: {
            required: true,
            minlength: 3
        },
        DescripcionArea: {
            required: true,
            minlength: 15,
            maxlength: 80
        },
        Canal: {
            required: true
        }
    }, messages: {

        NombreArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 3 caracteres como minimo"
        },
        DescripcionArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        Canal: {
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
        
    
        if ($("#cmbCanal").val() == "Selecciona el canal") {
                    
            swal("Selecciona el canal", "Para poder continuar con el registro por favor selecciona el canal.", "warning");

        }else{
            
            swal({
                title: "Confirmar Datos",
                text: "¿Está seguro que desea realizar el registro?",
                icon: "info",
                buttons: true,
                closeonconfirm: false,
                buttons: ["Cancelar", "Sí"]
            }).then((willDelete) => {
                if (willDelete) {

                    var canalArea = $("#cmbCanal").val();
                    var nombreArea = $("#txtNombreArea").val();
                    var descripcionArea = $("#txtDescripcionArea").val();
                    $.post("/SaleslandColombiaApp/area/registrararea", {CanalArea: canalArea, NombreArea: nombreArea, descripcionArea: descripcionArea}, function (responsetext) {
                        if (responsetext == "200") {
                            swal("Registro exitoso", "El Area ha sido registrada exitosamente", "success").then((willDelete) => {
                                if (willDelete) {

                                    $("#modalRegistrarArea").removeClass('show');
                                    $("body").removeClass('modal-open');
                                    $("body").css("padding-right","");
                                    $("div").removeClass('modal-backdrop');
                                    listarArea();

                                    $("#cmbCanal").val("");
                                    $("#txtNombreArea").val("");
                                    $("#txtDescripcionArea").val("");

                                }
                            });

                        } else {
                            swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
                        }
                    });                                
                }
            }); 
            
        }
           
    }
});
////////////////// LISTAR TODAS LAS AREAS ////////////////////////////
function listarArea() {
    
    $("#tablaModificada").html("");
    $("#tablaModificada").append("<div class='toolbar' id='toolbar'>"
                                    +"<button class='btn btn-outline btn-round' data-toggle='modal' data-target='#modalRegistrarArea'>"                                                
                                        +"Nuevo"
                                            +"<span class='btn-label'>"
                                                +"<i class='fa fa-plus'></i>"
                                            +"</span>"
                                    +"</button>"
                                    +"<!--        Here you can write extra buttons/actions for the toolbar              -->"
                                +"</div>"
                                +"<table id='bootstrap-table' data-toolbar='#toolbar' class='table'>"
                                    +"<thead>"
                                        +"<tr>"
                                            +"<th class='text-center'>#</th>"
                                            +"<th>Nombre</th>"
                                            +"<th>Descripción</th>"
                                            +"<th>Canal</th>"   
                                            +"<th class='text-right'>Estado</th>"
                                            +"<th class='text-right'>Acciones</th>"
                                        +"</tr>"
                                    +"</thead>"
                                    +"<tbody id='listadoArea'>"

                                    +"</tbody>"
                                +"</table>");

    $.post("/SaleslandColombiaApp/area/verarea", function (responseText) {

        if (responseText == "500") {

            swal("Ocurrio un error", "Lo sentimos a ocurrido un problema, por favor intentalo nuevamente.", "error");

        } else {

            $("#listadoArea").append(responseText);
            // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
            //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto
            botstrapPaginacionTabla(5, false, true, true, true, 'right');
        }
        

    });
}
///////////////////// VER DATOS  ///////////////////////////////
function verDatosArea(id) {
    
    $("#idArea").val(id);
    $.post("/SaleslandColombiaApp/area/cargardatosarea", {idArea: id}, function (responseText) {
        
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un error en el servidor, por favor intentalo nuevamente", "error");
        } else {

            var dt = JSON.parse(responseText);                        
                        
            if (dt[3] == "Activo") {                                
                $("#cmbEditarEstadoArea").val('Activo');
                $("#cmbEditarEstadoArea").attr("disabled", false);
                $("#lblCanal").show();
                $("#cmbCanalEditar").show();                 
            }else{                                
                
                $("#cmbEditarEstadoArea").val('Inactivo');
                $("#cmbEditarEstadoArea").attr("disabled", "disabled");                
                $("#lblCanal").hide();
                $("#cmbCanalEditar").hide();                
            }
                                  
            $("#txtEditarNombreArea").val(dt[1]);
            $("#txtEditarDescripcionArea").val(dt[2]);        
            $("#cmbEditarCanal").val("" + dt[4] + "");
            
        }

    });

}
/////////////////////////// EDITAR AREA //////////////////////////////////////////////
$('#frmEditarArea').validate({
    rules: {
        EditarEstadoArea: {

            required: true
        },
        EditarNombreArea: {
            required: true,
            minlength: 3
        },
        EditarDescripcionArea: {

            required: true,
            minlength: 15,
            maxlength: 80
        },
        EditarEstadoCanalArea: {

            required: true

        }
    }, messages: {
        EditarEstadoArea: {

            required: "Este campo es requerido"
        },
        EditarNombreArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 3 caracteres como minimo"
        },
        EditarDescripcionArea: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        EditarEstadoCanalArea: {
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
            title: "Editar Canal",
            text: "¿Estás seguro que deseas reemplazar los datos del canal?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
        }).then((willDelete) => {
            if (willDelete) {
                
                var idArea = $("#idArea").val();
                var nombreArea = $("#txtEditarNombreArea").val();
                var descripcionArea = $("#txtEditarDescripcionArea").val();
                var estadoArea = $("#cmbEditarEstadoArea").val();
                var canalArea = $("#cmbCanalEditar").val();
                $.post("/SaleslandColombiaApp/area/editararea", {IdArea: idArea, NombreArea: nombreArea, DescripcionArea: descripcionArea, EstadoArea: estadoArea, CanalArea: canalArea}, function (responsetext) {
                    if (responsetext == "200") {

                        swal("Cambios Guardados", "Los cambios del area han guardado exitosamente", "success").then((willDelete) => {
                            if (willDelete) {
                                
                                $("#ModalEditarArea").modal('toggle');                                  
                                listarArea();
                                
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
////////////////////////////////////////// FUNCIONES PARA CARGOS ////////////////////////////////////////////
///////////////////////////////FUNCION REGISTRAR CARGOS ///////////////////////////////////////////
$('#frmRegistrarCargos').validate({
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
        Tipo: {
            required: true,
        },
        Sector: {
            required: true
        },
        Canal: {
            required: true
        },
        Area: {
            required: true
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
            minlength: "El salario minimo es de 781,242 "
        },
        Tipo: {
            required: "Este campo es requerido"
        },
        Sector: {
            required: "Este campo es requerido"
        },
        Canal: {
            required: "Este campo es requerido"
        },
        Area: {
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

        
        if ($("#cmbTipo").val() == "Seleccione el tipo de usuario") {
            
            swal("El tipo de usuarios es requerido", "Para completar el registro debes seleccionar un tipo de usuario", "warning");
            
        }else{
         
            swal({
                title: "Confirmar Datos",
                text: "¿Está seguro que desea realizar el registro?",
                icon: "info",
                buttons: true,
                closeonconfirm: false,
                buttons: ["Cancelar", "Sí"]
            }).then((willDelete) => {
                if (willDelete) {

                    if ($("#cmbTipo").val() == "Director") {

                        if ($("#cmbSector").val() == "Seleccione el sector") {

                            swal("El sector es requerido", "Para completar el registro debes seleccionar un sector", "warning");

                        }else{

                            realizarRegistro();

                        }

                    }else if ($("#cmbTipo").val() == "JefeCanal" || $("#cmbTipo").val() == "CoordinadorCanal" ) {

                        if ($("#cmbSector").val() == "Seleccione el sector" || $("#cmbCanal").val() == "Seleccione el canal") {

                            swal("Completa los campos del registro", "Para completar el registro debes seleccionar un sector y un canal", "warning");

                        }else{

                            realizarRegistro();
                        }

                    }else if ($("#cmbTipo").val() == "Empleado" || $("#cmbTipo").val() == "JefeArea") {

                        if ($("#cmbSector").val() == "Seleccione el sector" || $("#cmbCanal").val() == "Seleccione el canal" || $("#cmbArea").val() == "Seleccione el area") {

                            swal("Completa los campos del registro", "Para poder continuar con el registro selecciona los campos solicitados.", "warning");

                        }else{
                            realizarRegistro();
                        }

                    }

                    function realizarRegistro() {
                        var nombreCargo = $("#txtNombreCargo").val();
                        var descripcionCargo = $("#txtDescripcionCargo").val();                    
                        var tipo = $("#cmbTipo").val();
                        var sector = $("#cmbSector").val();
                        var canal = $("#cmbCanal").val();
                        var area = $("#cmbArea").val();

                        $.post("/SaleslandColombiaApp/cargo/registrarcargo", {NombreCargo: nombreCargo, Descripcion: descripcionCargo, Tipo: tipo, Sector: sector, Canal: canal, Area: area }, function (responsetext) {
                            if (responsetext == "200") {
                                swal("Registro exitoso", "El cargo ha sido registrado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {

                                        $("#modalRegistrarCargo").modal('toggle');
                                        $("#txtNombreCargo").val("");
                                        $("#txtDescripcionCargo").val("");
                                        $("#cmbTipo").prop('selectedIndex', 0);
                                        $("#nsector").hide();
                                        $("#ncanal").hide();
                                        $("#narea").hide();

                                        listarCargos();

                                    }
                                });

                            } else {
                                swal("Ocurrio un error", "Lo sentimos ha ocurrido un error, por favor intentalo nuevamente.", "error");
                            }
                        });
                    }
                }
            });                        
        }        
    }
});

///////////////////////  FUNCIONES PARA REALIZAR Y VALIDAR EL REGISTRO DE LOS CARGOS ////////////////////////////
$("#cmbTipo").change(function (){
    var tipo = $("#cmbTipo").val();

    $("#cmbSector").html("<option>Seleccione el sector</option>");
    $("#cmbCanal").html("<option>Seleccione el canal</option>");
    $("#cmbArea").html("<option>Seleccione el area</option>");

    if (tipo == "Director") {               
        $("#nsector").show();  
        $("#ncanal").hide();
        $("#narea").hide();
        cargarSectoresDependientes();
        
    }else if (tipo == "JefeCanal" || tipo == "CoordinadorCanal" ) {                                                             
        $("#nsector").show();
        $("#ncanal").show();
        $("#narea").hide();
        cargarSectoresDependientes();              
    }else if (tipo == "Empleado" || tipo == "JefeArea"){

        $("#nsector").show();
        $("#ncanal").show();
        $("#narea").show();
        cargarSectoresDependientes();
    }

 });
 //////////_____________________________________________________     EDITAR CARGO      ________________________________________/////////
 $("#cmbEditarTipo").change(function (){    
    var tipo = $("#cmbEditarTipo").val();    
    $("#cmbEditarSector").html("<option>Seleccione el sector</option>");
    $("#cmbEditarCanal").html("<option>Seleccione el canal</option>");
    $("#cmbEditarArea").html("<option>Seleccione el area</option>");

    if (tipo == "Director") {               
        $("#nEditarSector").show();  
        $("#nEditarCanal").hide();
        $("#nEditarArea").hide();
        cargarSectoresDependientes();
    }else if (tipo == "JefeCanal" || tipo == "CoordinadorCanal" ) {                                                             
        $("#nEditarSector").show();
        $("#nEditarCanal").show();
        $("#nEditarArea").hide();
        cargarSectoresDependientes();             
    }else if (tipo == "Empleado" || tipo == "JefeArea"){

        $("#nEditarSector").show();
        $("#nEditarCanal").show();
        $("#nEditarArea").show();
        cargarSectoresDependientes();
    }

 });
///////////////////////////  EVENTO DEL SECTOR /////////////////////////////////
 $("#cmbSector").change(function (){
     if ($("#cmbTipo").val() != "Director" ) { 

        $("#cmbCanal").html("<option>Seleccione el canal</option>");
        $("#cmbArea").html("<option>Seleccione el area</option>");
        cargarCanalesDependientes("#cmbSector","#cmbCanal");

     }                
 });
 
  $("#cmbEditarSector").change(function (){
     if ($("#cmbEditarTipo").val() != "Director" ) { 

        $("#cmbEditarCanal").html("<option>Seleccione el canal</option>");
        $("#cmbEditarArea").html("<option>Seleccione el area</option>");
        cargarCanalesDependientes("#cmbEditarSector","#cmbEditarCanal");

     }                
 });

///////////////////////////  EVENTO DEL CANAL /////////////////////////////////            
 $("#cmbCanal").change(function (){
     if ($("#cmbTipo").val() == "Empleado" || $("#cmbTipo").val() == "JefeArea" ) { 

        cargarAreasDependientes("#cmbCanal","#cmbArea");

     }                
 });
 $("#cmbEditarCanal").change(function (){
     if ($("#cmbEditarTipo").val() == "Empleado" || $("#cmbEditarTipo").val() == "JefeArea" ) { 

        cargarAreasDependientes("#cmbEditarCanal","#cmbEditarArea");

     }                
 });
 
 ///////////////////////////////// FUNCION PARA CARGAR LOS CANALES /////////////////////////////7
 function cargarCanalesDependientes(idSector, idCanal){
     var idsector = $(idSector).val();                
     $.post("/SaleslandColombiaApp/canal/cargarcanalesdependientes",{idSector:idsector},function(responseText) {                
         if (responseText == "500") {

             swal("Ocurrio un error", "Lo sentimos, los datos de los canales no se lograron cargar, por favor intentalo mas tarde.", "error");

         }else{
             var selectCount = 0;
             var contador = 0;
             var dt = JSON.parse(responseText); 
             $(idCanal).html("");
             for (var i = 0, max = dt.length; i < max; i++) {

                 if (contador == 1) {
                     contador = 0;

                 }else{

                     if (selectCount == 0) {
                         $(idCanal).append("<option>Seleccione el canal</option>");
                         selectCount++;
                     }
                     $(idCanal).append("<option value='"+dt[i]+"'>"+dt[i+1]+"</option>");
                     contador++;
                 }
             }

         }

     });
 }
 ////////////7 FUNCION PARA CARGAR LAS AREAS //////////////////
 function cargarAreasDependientes (idCanal2, idArea){
     var idCanal = $(idCanal2).val(); 
     $.post("/SaleslandColombiaApp/area/cargarareasdependientes",{idCanal:idCanal},function(responseText) {
         if (responseText == "500") {

             swal("Ocurrio un error", "Lo sentimos, los datos de los canales no se lograron cargar, por favor intentalo mas tarde.", "error");

         }else{
             var contador = 0;
             var selectCount = 0;
             var dt = JSON.parse(responseText); 
             $(idArea).html("");
             for (var i = 0, max = dt.length; i < max; i++) {

                 if (selectCount == 0) {
                     $(idArea).append("<option>Seleccione el area</option>");
                     selectCount++;
                 }

                 if (contador == 1) {
                     contador = 0;

                 }else{
                     $(idArea).append("<option value='"+dt[i]+"'>"+dt[i+1]+"</option>");
                     contador++;
                 }
             } 

         }

     }); 
 }
 
 ///////////////////// FUNCION PARA VER LOS USUARIOS DESDE LA TABLA DE USUARIOS ///////////////////////////////////
 function VerUsuariosTabla(idUsuario){
     
     $.post("/SaleslandColombiaApp/usuario/verusuariostabla",{IdUsuario:idUsuario},function(responseText){
         
        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, los datos del usuario no se lograron cargar.", "error");
        }else{
            
           var dt = JSON.parse(responseText);  
           
           $("#imgPerfilUsuario").attr("src", "../../assets/img/imagenesDePerfil/"+dt[1]+"");
           $("#lblNombreApellido").text(dt[3]);
           $("#lblRolEmpleado").text(dt[4]);
           $("#lblDescripcionEmpleado").text(dt[5]);
            
        }
         
                
     });
     
     
 }
 
 /////////////////////////////////// FUNCION PARA CERGAR LOS PROMEDIOS PARA MONITOREAR A LOS EMPLEADOS ///////////////////
 
 function cargarPromedioEmpleados(){
     
     $.post("/SaleslandColombiaApp/ingreso/cargarPromedioEmpleados", function (responseText){
         
        if (responseText == "500") {
            
            swal("Ocurrio un error", "Lo sentimos, los datos de los empleados no se lograron cargar, por favor intentalo nuevamente", "error");
            
        }else{
            
            var dt = JSON.parse(responseText); 
            $("#lblPromedioEntrada_A").text(dt[0]);
            $("#lblPromedioEntrada_B").text(dt[1]);
            $("#lblPromedioSalida_A").text(dt[2]);
            $("#lblPromedioSalida_B").text(dt[3]);
            $("#lblPromedioHorasTrabajadas_A").html("&nbsp &nbsp &nbsp &nbsp;"+dt[4])+"";
            $("#lblPromedioHorasTrabajadas_B").html("&nbsp &nbsp &nbsp &nbsp;"+dt[5])+"";
        }
         
     });
     
 }
 
 
 /////////////////// FUNCION PARA CARGAR EL MODAL DEL PROMEDIO DE INGRESOS /////////////////////////////////////
$(".cargarPromedioEntrada").click(function (){    
    
    $("#tab1").addClass("show active");
    $("#linkTab1").addClass("active");
    $("#tab2").removeClass("show active");
    $("#linkTab2").removeClass("active");
    
    $("#lblTituloPromedio").text("PROMEDIO DE ENTRADA");    
    cargarRankingUsuarios("Ingreso","A", "#chartPromedioHorariosA", "#tblIngresoHA");
    
});

 /////////////////// FUNCION PARA CARGAR EL MODAL DEL PROMEDIO DE INGRESOS /////////////////////////////////////
 $(".cargarPromedioSalida").click(function (){
     
    $("#tab1").addClass("show active");
    $("#linkTab1").addClass("active");
    $("#tab2").removeClass("show active");
    $("#linkTab2").removeClass("active");
     
    $("#lblTituloPromedio").text("PROMEDIO DE SALIDA");
    cargarRankingUsuarios("Salida","A", "#chartPromedioHorariosA", "#tblIngresoHA");
    
 });

$("#linkTab2").click(function (){
    
    var pagina = $("#lblTituloPromedio").text();
    
    if (pagina == "PROMEDIO DE ENTRADA") {
        
        //alert("d"+$("#chartPromedioHorariosB").text()+"a");
        
        //if ($("#tblIngresoHB").text() == "") {
            //alert("Entrada b");
            cargarRankingUsuarios("Ingreso","B", "#chartPromedioHorariosB", "#tblIngresoHB");
            
        //}
        
    }else{
        
        //if ($("#tblIngresoHB").text() == "") {
            //alert("salida B");
            cargarRankingUsuarios("Salida","B", "#chartPromedioHorariosB", "#tblIngresoHB");
            
        //}
    }
    
});
function cargarRankingUsuarios(accion, horario, idChart, idTablaUSuarios){
    
    $.post("/SaleslandColombiaApp/ingreso/cargarPromedios",{Accion:accion, Horario:horario},function (responseText) {        
        if (responseText == "500") {
            
            swal("Ocurrio un error", "Lo sentimos, los datos de los empleados no se lograron cargar, por favor intentalo nuevamente", "error");
            
        }else{
            var contador = 0;
            var dt = JSON.parse(responseText);            
            $(idTablaUSuarios).html("");
            for (var i = 0, max = dt.length; i < max; i++) {
                
                if (contador == 0) {
                    
                    $(idTablaUSuarios).append("<tr>"
                                            +"<td>"
                                                +"<div class='flag'>"
                                                    +"<img style='height: 40px; width: 40px; border-radius: 50%;' src='../../assets/img/imagenesDePerfil/"+dt[i+1]+"'>"
                                                +"</div>"
                                            +"</td>"
                                            +"<td>"
                                                +""+dt[i+2]+" "+dt[i+3]+""
                                            +"</td>"                                            
                                            +"<td>"+dt[i+4]+"</dt>"
                                       +"</tr>");
                    contador++;
                }else if (contador == 4) {
                    
                    contador = 0;
                }else{
                    
                    contador++;
                }
            }
            
            if (dt.length > 0) {
                
                if (horario == "A") {
                    
                    $("#contentTabA").show();
                    if (accion == "Ingreso") {
                        
                        $("#lblDescripcionTabA").text("Este es el promedio de entrada de los trabajadores menor ranqueados dentro de tu equipo de trabajo.");
                        
                    }else{
                        
                        $("#lblDescripcionTabA").text("Este es el promedio de Salida de los trabajadores menor ranqueados dentro de tu equipo de trabajo.");
                    }
                    
                }else{
                    
                    $("#contentTabB").show();
                    if (accion == "Salida") {
                        
                        $("#lblDescripcionTabB").text("Este es el promedio de salida de los trabajadores menor ranqueados dentro de tu equipo de trabajo.");
                        
                    }else{
                        
                        $("#lblDescripcionTabB").text("Este es el promedio de entrada de los trabajadores menor ranqueados dentro de tu equipo de trabajo.");
                    }
                }
                
                setTimeout(function(){
                    cargarChartPeomedioEmpleados(accion, horario, idChart);
                }, 150);                 
            }else{
                
                
                if (horario == "A") {
                    
                    $("#contentTabA").hide();                      
                    $("#lblDescripcionTabA").text("Por el momento no hay estadisticas de tu equipo de trajajo.");
                    
                }else{
                    
                    $("#contentTabB").hide();                     
                    $("#lblDescripcionTabB").text("Por el momento no hay estadisticas de tu equipo de trajajo.");
                    
                }
                
            }
        }        
    });
    
}
function cargarChartPeomedioEmpleados(accion, horario, idChart){
    
    $.post("/SaleslandColombiaApp/ingreso/cargarChartPeomedioEmpleados",{Accion:accion, Horario:horario},function (responseText) {
        
        if (responseText == "500") {
            
            swal("Ocurrio un error", "Lo sentimos, los datos de los empleados no se lograron cargar, por favor intentalo nuevamente", "error");
        }else{
            $(""+idChart+"").html("");
            
            var dt = JSON.parse(responseText);
            
            var total = dt[0] + dt[1] + dt[2];
            
            
            var correctosPorcentaje = Math.round((dt[2]/total)*100);
            var erroneosPorcentaje = Math.round((dt[1]/total)*100); 
            var justoPorcentaje = Math.round((dt[0]/total)*100);

            var chart = new Chartist.Pie(""+idChart+"", {
              series: [correctosPorcentaje, erroneosPorcentaje, justoPorcentaje],
              labels: ["%"+correctosPorcentaje+"", "%"+erroneosPorcentaje+"", "%"+justoPorcentaje+""]            
            }, {
              donut: true,
              showLabel: true,
              donutWidth: 40
            });

            chart.on('draw', function(data) {
              if(data.type === 'slice') {
                // Get the total path length in order to use for dash array animation
                var pathLength = data.element._node.getTotalLength();

                // Set a dasharray that matches the path length as prerequisite to animate dashoffset
                data.element.attr({
                  'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
                });

                // Create animation definition while also assigning an ID to the animation for later sync usage
                var animationDefinition = {
                  'stroke-dashoffset': {
                    id: 'anim' + data.index,
                    dur: 1500,
                    from: -pathLength + 'px',
                    to:  '0px',
                    easing: Chartist.Svg.Easing.easeOutQuint,
                    // We need to use `fill: 'freeze'` otherwise our animation will fall back to initial (not visible)
                    fill: 'freeze'
                  }
                };

                // If this was not the first slice, we need to time the animation so that it uses the end sync event of the previous animation
                if(data.index !== 0) {
                  animationDefinition['stroke-dashoffset'].begin = 'anim' + (data.index - 1) + '.end';
                }

                // We need to set an initial value before the animation starts as we are not in guided mode which would do that for us
                data.element.attr({
                  'stroke-dashoffset': -pathLength + 'px'
                });

                // We can't use guided mode as the animations need to rely on setting begin manually
                // See http://gionkunz.github.io/chartist-js/api-documentation.html#chartistsvg-function-animate
                data.element.animate(animationDefinition, false);
              }
            });
            
        }
        
    });    
}


/////////////////////// FUNCION PARA CARGAR CHART DE PROMEDIO POR DIAS ////////////////////////////////
function cargarChartPromedioDias(){
    
    $.post("/SaleslandColombiaApp/ingreso/cargarChartPromedioDias", function (responseText){
       
        if (responseText == "500") {
            
            swal("Ocurrio un error", "Lo sentimos, los datos de los empleados no se lograron cargar, por favor intentalo nuevamente", "error");
            
        }else{
                  
            var LunesIn_A, LunesOut_A, LunIn_A, LunOut_A;
            var MartesIn_A, MartesOut_A, MarIn_A, MarOut_A;
            var MiercolesIn_A, MiercolesOut_A, MierIn_A, MierOut_A;
            var JuevesIn_A, JuevesOut_A, JueIn_A, JueOut_A;
            var ViernesIn_A, ViernesOut_A, VierIn_A, VierOut_A;
            
            var LunesIn_B, LunesOut_B, LunIn_B, LunOut_B;
            var MartesIn_B, MartesOut_B, MarIn_B, MarOut_B;
            var MiercolesIn_B, MiercolesOut_B, MierIn_B, MierOut_B;
            var JuevesIn_B, JuevesOut_B, JueIn_B, JueOut_B;
            var ViernesIn_B, ViernesOut_B, VierIn_B, VierOut_B;
            
            
            var dt = JSON.parse(responseText);
            
            
            for (var i = 0, max = dt.length; i < max; i++) {
                
                var data = dt[i].split(" ");
                var hora = data[3].split(":");
                
                if (data[0] == "A") {
                    
                    if (data[2] == "Ingreso") {
                        
                        if (data[1] == "Lunes") {
                            LunIn_A = hora[0];
                            LunesIn_A =  data[3];
                        }else if (data[1] == "Martes") {
                            MarIn_A = hora[0];
                            MartesIn_A = data[3]
                        }else if (data[1] == "Miercoles") {
                            MierIn_A = hora[0];
                            MiercolesIn_A = data[3];
                        }else if (data[1] == "Jueves") {
                            JueIn_A = hora[0];
                            JuevesIn_A = data[3];
                        }else if (data[1] == "Viernes") {
                            VierIn_A = hora[0];
                            ViernesIn_A = data[3];                        
                        }
                        
                    }else if (data[2] == "Salida") {
                        
                        if (data[1] == "Lunes") {
                            LunOut_A = hora[0];
                            LunesOut_A =  data[3];
                        }else if (data[1] == "Martes") {
                            MarOut_A = hora[0];
                            MartesOut_A = data[3]
                        }else if (data[1] == "Miercoles") {
                            MierOut_A = hora[0];
                            MiercolesOut_A = data[3];
                        }else if (data[1] == "Jueves") {
                            JueOut_A = hora[0];
                            JuevesOut_A = data[3];
                        }else if (data[1] == "Viernes") {
                            VierOut_A = hora[0];
                            ViernesOut_A = data[3];                        
                        }
                        
                    }
                    
                }else if (data[0] == "B") {
                    
                    if (data[2] == "Ingreso") {
                        
                        if (data[1] == "Lunes") {
                            LunIn_B = hora[0];
                            LunesIn_B =  data[3];
                        }else if (data[1] == "Martes") {
                            MarIn_B = hora[0];
                            MartesIn_B = data[3]
                        }else if (data[1] == "Miercoles") {
                            MierIn_B = hora[0];
                            MiercolesIn_B = data[3];
                        }else if (data[1] == "Jueves") {
                            JueIn_B = hora[0];
                            JuevesIn_B = data[3];
                        }else if (data[1] == "Viernes") {
                            VierIn_B = hora[0];
                            ViernesIn_B = data[3];                        
                        }
                        
                    }else if (data[2] == "Salida") {
                        
                        if (data[1] == "Lunes") {
                            LunOut_B = hora[0];
                            LunesOut_B =  data[3];
                        }else if (data[1] == "Martes") {
                            MarOut_B = hora[0];
                            MartesOut_B = data[3]
                        }else if (data[1] == "Miercoles") {
                            MierOut_B = hora[0];
                            MiercolesOut_B = data[3];
                        }else if (data[1] == "Jueves") {
                            JueOut_B = hora[0];
                            JuevesOut_B = data[3];
                        }else if (data[1] == "Viernes") {
                            VierOut_B = hora[0];
                            ViernesOut_B = data[3];                        
                        }
                        
                    }
                    
                }
                
            }
            
            var chart = new Chartist.Line('.ct-chart', {
                labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'],                
                series: [
                    //Chart series para los ingresos _A
                    [
                        {meta: LunesIn_A, value: LunIn_A},
                        {meta: MartesIn_A, value: MarIn_A},
                        {meta: MiercolesIn_A, value: MierIn_A},
                        {meta: JuevesIn_A, value: JueIn_A},
                        {meta: ViernesIn_A, value: VierIn_A}
                    ],
                    //Chart series para las salidas _A
                    [
                        {meta: LunesOut_A, value: LunOut_A},
                        {meta: MartesOut_A, value: MarOut_A},
                        {meta: MiercolesOut_A, value: MierOut_A},
                        {meta: JuevesOut_A, value: JueOut_A},
                        {meta: ViernesOut_A, value: VierOut_A}
                    ],
                    //Chart series para los ingresos _B
                    [
                        {meta: LunesIn_B, value: LunIn_B},
                        {meta: MartesIn_B, value: MarIn_B},
                        {meta: MiercolesIn_B, value: MierIn_B},
                        {meta: JuevesIn_B, value: JueIn_B},
                        {meta: ViernesIn_B, value: VierIn_B}
                    ],
                    //Chart series para las salidas _B
                    [
                        {meta: LunesOut_B, value: LunOut_B},
                        {meta: MartesOut_B, value: MarOut_B},
                        {meta: MiercolesOut_B, value: MierOut_B},
                        {meta: JuevesOut_B, value: JueOut_B},
                        {meta: ViernesOut_B, value: VierOut_B}
                    ]
                ]
            },{
                low: 0,
                axisY: {
                    onlyInteger:true, 
                    labelInterpolationFnc: function(value) {
                        
                        return (value +":00");
                        
                    }
                },
                plugins: [
                    Chartist.plugins.tooltip({
                        pointClass: 'customTooltipOnPoint',
                    })
                ]
            });
            // Let's put a sequence number aside so we can use it in the event callbacks
            var seq = 0,
                delays = 80,
                durations = 500;

            // Once the chart is fully created we reset the sequence
            chart.on('created', function() {
                seq = 0;
            });

            // On each drawn element by Chartist we use the Chartist.Svg API to trigger SMIL animations
            chart.on('draw', function(data) {
                seq++;

                if(data.type === 'line') {
                  // If the drawn element is a line we do a simple opacity fade in. This could also be achieved using CSS3 animations.
                  data.element.animate({
                    opacity: {
                      // The delay when we like to start the animation
                      begin: seq * delays + 1000,
                      // Duration of the animation
                      dur: durations,
                      // The value where the animation should start
                      from: 0,
                      // The value where it should end
                      to: 1
                    }
                  });
                } else if(data.type === 'label' && data.axis === 'x') {
                  data.element.animate({
                    y: {
                      begin: seq * delays,
                      dur: durations,
                      from: data.y + 100,
                      to: data.y,
                      // We can specify an easing function from Chartist.Svg.Easing
                      easing: 'easeOutQuart'
                    }
                  });
                } else if(data.type === 'label' && data.axis === 'y') {
                  data.element.animate({
                    x: {
                      begin: seq * delays,
                      dur: durations,
                      from: data.x - 100,
                      to: data.x,
                      easing: 'easeOutQuart'
                    }
                  });
                }else if(data.type === 'point') {
                    var v;

                    var circle = new Chartist.Svg('circle', {
                        cx: [data.x],
                        cy: [data.y],
                        r: [5],
                        'ct:value': v,
                        'ct:meta': data.meta,
                        class: 'customTooltipOnPoint'
                    }, 'ct-point');

                    // With data.element we get the Chartist SVG wrapper and we can replace the original point drawn by Chartist with our newly created triangle
                    data.element.replace(circle);

                    data.element.animate({
                        x1: {
                          begin: seq * delays,
                          dur: durations,
                          from: data.x - 10,
                          to: data.x,
                          easing: 'easeOutQuart'
                        },
                        x2: {
                          begin: seq * delays,
                          dur: durations,
                          from: data.x - 10,
                          to: data.x,
                          easing: 'easeOutQuart'
                        },
                        opacity: {
                          begin: seq * delays,
                          dur: durations,
                          from: 0,
                          to: 1,
                          easing: 'easeOutQuart'
                        }
                    });
                } else if(data.type === 'grid') {
                  // Using data.axis we get x or y which we can use to construct our animation definition objects
                  var pos1Animation = {
                    begin: seq * delays,
                    dur: durations,
                    from: data[data.axis.units.pos + '1'] - 30,
                    to: data[data.axis.units.pos + '1'],
                    easing: 'easeOutQuart'
                  };

                  var pos2Animation = {
                    begin: seq * delays,
                    dur: durations,
                    from: data[data.axis.units.pos + '2'] - 100,
                    to: data[data.axis.units.pos + '2'],
                    easing: 'easeOutQuart'
                  };

                  var animations = {};
                  animations[data.axis.units.pos + '1'] = pos1Animation;
                  animations[data.axis.units.pos + '2'] = pos2Animation;
                  animations['opacity'] = {
                    begin: seq * delays,
                    dur: durations,
                    from: 0,
                    to: 1,
                    easing: 'easeOutQuart'
                  };

                  data.element.animate(animations);
                }
              });
            
        }
        
    });
    
}
/////////////////////////    FUNCION PARA ELIMINAR EL CARGO      //////////////////////////////////////////////
function eliminarCargo(idCargo){
        
    swal({
        title: "Eliminar Cargo",
        text: "¿Está seguro que desea eliminar el cargo?",
        icon: "warning",
        buttons: true,
        closeonconfirm: false,
        buttons: ["Cancelar", "Sí"]
    })
    .then((willDelete) => {
        if (willDelete) {
            
            $.post("/SaleslandColombiaApp/cargo/eliminarCargo",{IdCargo:idCargo},function (responseText) {
                
                if (responseText == "500") {
                    swal("Ocurrio un error", "Lo sentimos, los datos de los empleados no se lograron cargar, por favor intentalo nuevamente", "error");
                }else if (responseText == "200") {

                    swal("Cargo eliminado", "El cargo se ha eliminado correctamente.", "success").then((willDelete) => {
                        if (willDelete) {
                               
                            listarCargos();

                        }
                    });

                }else if (responseText == "204"){

                    swal("No se puede eliminar este cargo", "No puedes eliminar este cargo porque hay empleados relacionados a este.", "info");
                }
                
            });                            
        }    
    });                        
}
//_________________________________________________________  PERMISOS _______________________________________________________________//
$("#file").change(function(){
    archivoPermiso(this);
});

var fileTypes = ["pdf"];
function archivoPermiso(imagen){
    if (imagen.files && imagen.files[0]) {
        var extension = imagen.files[0].name.split('.').pop().toLowerCase(),  //file extension from input file
        isSuccess = fileTypes.indexOf(extension) > -1;  //is extension in acceptable types

        if (isSuccess) { 
            var reader = new FileReader();
            reader.onload = function (e) {
                //$("#fileImagenUsuarioNombre").val(imagen.files[0].name);
                //$("#txtFoto").val(imagen.files[0].name);
            }
            reader.readAsDataURL(imagen.files[0]);
        }
        else{
            $("#file").val("");
            swal("Formato incorrecto","El formato del archivo seleccionado no es correcto. Solo se admite formato pdf.","warning");
        }
    }
}
///////////////////////////////         FUNCION PARA SILICITAR LOS PERMISOS        ///////////////////////////////////////////
$('#frmSolicitarPermiso').validate({
    rules: {
        MotivoPermiso: {
            required: true,
            minlength: 5
        },
        DescripcionPermiso: {
            required: true,
            minlength: 15,
            maxlength: 80
        },
        InicioPermiso: {
            required: true
        },
        FinPermiso: {
            required: true
        },
        ArchivoPermiso: {
            required: false
        }
    }, messages: {

        MotivoPermiso: {
            required: "Este campo es requerido",
            minlength: "Ingresa 5 caracteres como minimo"
        },
        DescripcionPermiso: {
            required: "Este campo es requerido",
            minlength: "Ingresa 15 caracteres como minimo",
            maxlength: "Ingresa 80 caracteres como maximo"
        },
        InicioPermiso: {
            required: "Este campo es requerido",
            minlength: "El salario minimo es de 781,242 "
        },
        FinPermiso: {
            required: "Este campo es requerido"
        },
        ArchivoPermiso: {
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
            title: "Solicitar permiso",
            text: "¿Está seguro que desea solicitar el permiso?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
        })
        .then((willDelete) => {
            if (willDelete) {

                var motivo = $("#txtMotivoPermiso").val();
                var descripcion = $("#txtDescripcionPermiso").val();
                var inicio = $("#datetimepicker").val();
                var fin = $("#tdFinPermiso").val();                
                var filename = $('input[type=file]').val().split('\\').pop();
                
                ////////SUBIR IMAGEN DE ARCHIVO ADJUNTO/////////
                var data = new FormData();
                $.each($('#file')[0].files, function(i, file) {
                    data.append('file-'+i, file);
                });
                $.ajax({
                    url: '/SaleslandColombiaApp/SubirArchivoPermiso',
                    data: data,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST'
                });

                $.post("/SaleslandColombiaApp/permiso/registrarPermiso",{Motivo:motivo, Descripcion:descripcion, Inicio:inicio, Fin:fin, Archivo:filename},function (responseText) {

                    alert(responseText);

                    if (responseText == "500") {
                        swal("Ocurrio un error", "Lo sentimos, no se pudo realizar la solicitud del permiso, por favor intentalo más tarde.", "error");
                    }else if(responseText == "204"){

                        swal("Ocurrio un error", "La información de los roles está truncada.", "error");

                    }else if (responseText == "200") {
                    
                        swal("Permiso solicitado", "El permiso ha sido solicitado, serás notificado cuando eas respondido.", "success").then((willDelete) => {
                            if (willDelete) {

                                $("#modalRegistrarPermiso").modal('toggle');

                            }
                        });                                                
                    }

                });                    
            }    
        });            
    }
});
////////////////////////////                  FUNCION PARA LISTAR LOS PERMISOS              /////////////////////////////////////////////////
function listarPermisos() {    
    $("#tablaModificada").html("");
    $("#tablaModificada").append("<div class='toolbar' id='toolbar'>"
                                    +"<button class='btn btn-outline btn-round' data-toggle='modal' data-target='#modalRegistrarPermiso'>"                                                
                                        +"Nuevo"
                                            +"<span class='btn-label'>"
                                                +"<i class='fa fa-plus'></i>"
                                            +"</span>"
                                    +"</button>"
                                    +"<!--        Here you can write extra buttons/actions for the toolbar              -->"
                                +"</div>"
                                +"<table id='bootstrap-table' data-toolbar='#toolbar' class='table'>"
                                    +"<thead>"
                                        +"<tr>"
                                            +"<th class='text-center'>#</th>"
                                            +"<th class='text-center'>Motivo</th>"
                                            +"<th class='text-center'>Descripción</th>"
                                            +"<th class='text-center'>Fecha de inicio</th>"
                                            +"<th class='text-center'>Fecha de fin</th>"
                                            +"<th class='text-center'>Jefe</th>"
                                            +"<th class='text-center'>Estado</th>"
                                            +"<th class='text-center'>Acciones</th>"
                                        +"</tr>"
                                    +"</thead>"
                                    +"<tbody id='listadoPermisos'>"

                                    +"</tbody>"
                                +"</table>");
                        
    $.post("/SaleslandColombiaApp/permiso/listarPermisos", function (responseText) {       

        if (responseText == "500") {

            swal("Ocurrio un error", "Ocurrió un erro mientra intentebamos listar los permisos, por favlo intentelo más tarde.", "error");

        } else {
            $("#listadoPermisos").append(responseText);
        }
        // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
        //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto        
        botstrapPaginacionTabla(5,false, true, true, true, 'right');
       
    });

}

(function() {
  
  'use strict';

  $('.input-file').each(function() {
    var $input = $(this),
        $label = $input.next('.js-labelFile'),
        labelVal = $label.html();
    
   $input.on('change', function(element) {
      var fileName = '';
      if (element.target.value) fileName = element.target.value.split('\\').pop();
      fileName ? $label.addClass('has-file').find('.js-fileName').html(fileName) : $label.removeClass('has-file').html(labelVal);
   });
  });

})();
