/////////////////////////// REGISTRAR AREA ///////////////////////////////////////
//$('#frmRegistrarArea').validate({
//    rules: {
//        NombreArea: {
//            required: true,
//            minlength: 5
//        },
//        DescripcionArea: {
//            required: true,
//            minlength: 15,
//            maxlength: 80
//        },
//        Canal: {
//            required: true
//        }
//    }, messages: {
//
//        NombreArea: {
//            required: "Este campo es requerido",
//            minlength: "Ingresa 5 caracteres como minimo"
//        },
//        DescripcionArea: {
//            required: "Este campo es requerido",
//            minlength: "Ingresa 15 caracteres como minimo",
//            maxlength: "Ingresa 80 caracteres como maximo"
//        },
//        Canal: {
//            required: "Este campo es requerido"
//        }
//    }, errorElement: 'div',
//    errorPlacement: function (error, element) {
//        var placement = $(element).data('error');
//        if (placement) {
//            $(placement).append(error);
//        } else {
//            error.insertAfter(element);
//        }
//    }, submitHandler: function () {
//
//        swal({
//            title: "Confirmar Datos",
//            text: "¿Está seguro que desea realizar el registro?",
//            icon: "info",
//            buttons: true,
//            closeonconfirm: false,
//            buttons: ["Cancelar", "Sí"]
//        })
//                .then((willDelete) => {
//                    if (willDelete) {
//
//                        var canalArea = $("#cmbCanal").val();
//                        var nombreArea = $("#txtNombreArea").val();
//                        var descripcionArea = $("#txtDescripcionArea").val();
//                        $.post("/SaleslandColombiaApp/area/registrararea", {CanalArea: canalArea, NombreArea: nombreArea, descripcionArea: descripcionArea}, function (responsetext) {
//                            if (responsetext == "200") {
//                                swal("Registro exitoso", "El Area ha sido registrada exitosamente", "success").then((willDelete) => {
//                                    if (willDelete) {
//                                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/area/listararea.jsp";
//                                    }
//                                });
//
//                            } else {
//                                swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error");
//                            }
//                        });
//                    }
//                });
//    }
//});
//////////////////// LISTAR TODAS LAS AREAS ////////////////////////////
//function listarArea() {
//
//    $.post("/SaleslandColombiaApp/area/verarea", function (responseText) {
//
//        if (responseText == "500") {
//
//            swal("Ocurrio un error", "Lo sentimos a ocurrido un problema, por favor intentalo nuevamente.", "error");
//
//        } else {
//
//            $("#listadoCanales").html("");
//            $("#listadoArea").append(responseText);
//        }
//         // orden de datos tamaño,showRefresh, search, showToggle, showColumns, alineacion, texto
//        //tamanio,showRefresh, search, showToggle, showColumns, alineacion, texto
//        botstrapPaginacionTabla(5, false, true, true, true, 'right');
//
//    });
//}
/////////////////////// VER DATOS  ///////////////////////////////
//function verDatosArea() {
//
//    var url = "" + window.location + "";
//    var IdArea = url.split("_");
//    $.post("/SaleslandColombiaApp/area/cargardatosarea", {idArea: IdArea[1]}, function (responseText) {
//
//        if (responseText == "500") {
//            swal("Ocurrio un error", "Lo sentimos, ocurrió un error en el servidor, por favor intentalo nuevamente", "error");
//        } else {
//
//            var dt = JSON.parse(responseText);
//            console.log(dt);
//            if (dt[3] == "Activo") {
//                $("#cmbEditarEstadoArea").val('Activo');
//            } else {
//
//                $("#cmbEditarEstadoArea").val('Inactivo');
//            }
//            $("#txtEditarNombreArea").val(dt[1]);
//            $("#txtEditarDescripcionArea").val(dt[2]);
//            $("#cmbCanal").val("" + dt[4] + "");
//        }
//
//    });
//
//}
///////////////////////////// EDITAR AREAAAA //////////////////////////////////////////////
//$('#frmEditarArea').validate({
//    rules: {
//        EditarEstadoArea: {
//
//            required: true
//        },
//        EditarNombreArea: {
//            required: true,
//            minlength: 5
//        },
//        EditarDescripcionArea: {
//
//            required: true,
//            minlength: 15,
//            maxlength: 80
//        },
//        EditarEstadoCanalArea: {
//
//            required: true,
//
//        }
//    }, messages: {
//        EditarEstadoArea: {
//
//            required: "Este campo es requerido"
//        },
//        EditarNombreArea: {
//            required: "Este campo es requerido",
//            minlength: "Ingresa 5 caracteres como minimo"
//        },
//        EditarDescripcionArea: {
//            required: "Este campo es requerido",
//            minlength: "Ingresa 15 caracteres como minimo",
//            maxlength: "Ingresa 80 caracteres como maximo"
//        },
//        EditarEstadoCanalArea: {
//            required: "Este campo es requerido",
//
//        }
//    }, errorElement: 'div',
//    errorPlacement: function (error, element) {
//        var placement = $(element).data('error');
//        if (placement) {
//            $(placement).append(error);
//        } else {
//            error.insertAfter(element);
//        }
//    }, submitHandler: function () {
//
//        swal({
//
//            title: "Editar Area",
//            text: "¿Está seguro que desea reemplazar los datos del Area?",
//            icon: "info",
//            buttons: true,
//            closeonconfirm: false,
//            buttons: ["No, Cancelar", "Sí"]
//        })
//                .then((willDelete) => {
//                    if (willDelete) {
//
//                        var url = "" + window.location + "";
//                        var idArea = url.split("_");
//                        var nombreArea = $("#txtEditarNombreArea").val();
//                        var descripcionArea = $("#txtEditarDescripcionArea").val();
//                        var estadoArea = $("#cmbEditarEstadoArea").val();
//                        var canalArea = $("#cmbCanal").val();
//                        $.post("/SaleslandColombiaApp/area/editararea", {IdArea: idArea[1], NombreArea: nombreArea, DescripcionArea: descripcionArea, EstadoArea: estadoArea, CanalArea: canalArea}, function (responsetext) {
//                            if (responsetext == "200") {
//
//                                swal("Cambios Guardados", "Los cambios del area han guardado exitosamente", "success").then((willDelete) => {
//                                    if (willDelete) {
//                                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/area/listararea.jsp";
//                                    }
//                                });
//
//                            } else {
//                                swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.", "error").then((willDelete) => {
//
//                                });
//                            }
//                        });
//                    }
//                });
//    }
//});
//////CARGAR CANAL
function cargarCanal() {

    $.post("/SaleslandColombiaApp/canal/cargacombocanal", function (responseText) {

        if (responseText == 500) {
            swal("Ocurrio un error", "Ocurrio un error al intentar cargar la información.", "error");
        } else {

            $("#cmbCanal").html("");
            $("#cmbEditarCanal").html("");
            
            var dt = JSON.parse(responseText);

            var counta = 0;
            for (var i = 0, max = dt.length; i < max; i++) {

                if (counta == 1) {
                    counta = 0;

                } else {

                    $("#cmbCanal").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");
                    $("#cmbEditarCanal").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");                    
                    counta++;
                }


            }

        }

    });
}

///////////////// CARGAR COMBO DE AREAS /////////////////////////////////
function cargarAreas(){    
     $.post("/SaleslandColombiaApp/area/cargarcomboarea", function (responseText) {

        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocurrió un error al intentar cargar la información.", "error");
        } else {
                        
            $("#cmbArea").html("");
            $("#cmbEditarArea").html("");
            var dt = JSON.parse(responseText);
            
            var counta = 0;
            for (var i = 0, max = dt.length; i < max; i++) {

                if (counta == 1) {
                    counta = 0;

                } else {

                    $("#cmbArea").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");
                    $("#cmbEditarArea").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");
                    counta++;
                }
            }              
        }
    });
}
/////////////////////////EDITAR CARGO ///////////////////////////////////////////
$('#frmEditarCargo').validate({
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
        Salario: {
            required: true,
            minlength: 6,
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


        if ($("#cmbEditarTipo").val() == "Director") {
                    
            if ($("#cmbEditarSector").val() == "Seleccione el sector") {

                swal("El sector es requerido", "Para completar el registro debes seleccionar un sector", "warning");

            }else{

                editarCargo();

            }

        }else if ($("#cmbEditarTipo").val() == "JefeCanal" || $("#cmbEditarTipo").val() == "CoordinadorCanal" ) {

            if ($("#cmbEditarSector").val() == "Seleccione el sector" || $("#cmbEditarCanal").val() == "Seleccione el canal") {

                swal("Completa los campos del registro", "Para poder continuar con el registro selecciona los campos solicitados.", "warning");

            }else{

                editarCargo();
            }

        }else if ($("#cmbEditarTipo").val() == "Empleado" || $("#cmbEditarTipo").val() == "JefeArea") {

            if ($("#cmbEditarSector").val() == "Seleccione el sector" || $("#cmbEditarCanal").val() == "Seleccione el canal" || $("#cmbArea").val() == "Seleccione el area") {

                swal("Completa los campos del registro", "Para poder continuar con el registro selecciona los campos solicitados.", "warning");

            }else{
                editarCargo();
            }

        }
        
        function editarCargo(){
            
         
            swal({
                title: "Confirmar Datos",
                text: "¿Está seguro que desea actualizar la información del cargo?",
                icon: "info",
                buttons: true,
                closeonconfirm: false,
                buttons: ["Cancelar", "Sí"]
            })
            .then((willDelete) => {
                if (willDelete) {
                    
                    var idCargo = $("#idCargo").val();
                    var nombreCargo = $("#txtEditarNombreCargo").val();
                    var descripcionCargo = $("#txtEditarDescripcionCargo").val();
                    var estadoCargo = $("#cmbEditarEstadoCargo").val();                                                        

                    $.post("/SaleslandColombiaApp/cargo/editarcargo", {IdCargo:idCargo, NombreCargo: nombreCargo, Descripcion: descripcionCargo, Estado: estadoCargo}, function (responsetext) {

                        if (responsetext == "200") {
                            swal("Actualizado Correctamente", "El Cargo ha sido actualizado exitosamente", "success").then((willDelete) => {
                                if (willDelete) {
                                    
                                    $("#modalEditarCargo").modal('toggle');                                    
                                    listarCargos();
                                }
                            });

                        } else {
                            swal("Ocurrio un error", "Lo sentimos tus datos de Actualizacion no fueron registrados, por favor intentalo nuevamente.", "error");
                        }
                    });
                }
            });
            
        }      
    }
});
///////////////////////////////  VALIDACION DEL REPORTE DE SECTORES /////////////////////////////////////////////////
$("#FrmReporteUsuarios").validate({
   
    rules:{
        txtsector:{
            required:true
            
        },txthorario:{
            required:true
            
        },txtfechainicial:{
            required:true
            
        },txtfechafinal:{
            
            required:true
        }
        
    },messages:{
                
        txtsector:{
            required:"Este campo es requerido"
            
        },txthorario:{
            required:"Este campo es requerido"
            
        },txthorario:{
            
            required:"Este campo es requerido"
        },txtfechainicial:{
            required:"Este campo es requerido"
            
        },txtfechafinal:{
            
            required:"Este campo es requerido"
        }
        
    },
    errorElement: 'div',
    errorPlacement: function (error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error);
        } else {
            error.insertAfter(element);
        }
    }   
});