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

            var dt = JSON.parse(responseText);


            var counta = 0;
            for (var i = 0, max = dt.length; i < max; i++) {

                if (counta == 1) {
                    counta = 0;

                } else {

                    $("#cmbCanal").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");
                    $("#cmbCanalEditar").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>");                    
                    counta++;
                }


            }

        }

    });
}
///////////////////////////////METODO REGISTRAR CARGOS ///////////////////////////////////////////
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
                        var tipo = $("#cmbTipo").val();
                        var sector = $("#cmbSector").val();
                        var canal = $("#cmbCanal").val();
                        var area = $("#cmbArea").val();
                        $.post("/SaleslandColombiaApp/cargo/registrarcargo", {NombreCargo: nombreCargo, Descripcion: descripcionCargo, Salario: salario, Tipo: tipo, Sector: sector, Canal: canal, Area: area }, function (responsetext) {
                            if (responsetext == "200") {
                                swal("Registro exitoso", "El Cargo ha sido registrado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {
                                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/cargo/listarcargo.jsp";
                                    }
                                });

                            } else {
                                swal("Ocurrio un error", "Lo sentimos ha ocurrido un error, por favor intentalo nuevamente.", "error");
                            }
                        });
                    }
                });
    }
});



///////////////// CARGAR COMBO DE AREAS /////////////////////////////////
function cargarAreas(){
    
     $.post("/SaleslandColombiaApp/area/cargarcomboarea", function (responseText) {

        if (responseText == 500) {
            swal("Ocurrio un error", "Lo ocuttió un error al intentar cargar la información.", "error");
        } else {

            var dt = JSON.parse(responseText);


            var counta = 0;
            for (var i = 0, max = dt.length; i < max; i++) {

                if (counta == 1) {
                    counta = 0;

                } else {

                    $("#cmbArea").append("<option value='" + dt[i] + "'>" + dt[i + 1] + "</option>")
                    counta++;
                }


            }      
        }

    });
}

/////////////////FUNCION CARGAR CARGOS//////////////////////
function verDatosCargo() {

    var url = "" + window.location + "";
    var IdCargo = url.split("_");
    $.post("/SaleslandColombiaApp/cargo/cargardatoscargo", {idCargo: IdCargo[1]}, function (responseText) {

        if (responseText == "500") {
            swal("Ocurrio un error", "Lo sentimos, ocurrió un error en el servidor, por favor intentalo nuevamente", "error");
        } else {

            var dt = JSON.parse(responseText);
            console.log(dt);
            if (dt[8] == "Activo") {
                $("#cmbEditarEstadoCargo").val('Activo');
            } else {

                $("#cmbEditarEstadoCargo").val('Inactivo');
            }   
            $("#txteditarNombreCargo").val(dt[1]);
            $("#txteditarDescripcionCargo").val(dt[2]);
            $("#txteditarSalario").val(dt[3]);
            $("#cmbeditarTipo").val("" + dt[4] + "");
            $("#cmbeditarSector").val("" + dt[5] + "");
            $("#cmbeditarCanal").val("" + dt[6] + "");
            $("#cmbeditarArea").val("" + dt[7] + "");
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

        swal({
            title: "Confirmar Datos",
            text: "¿Está seguro que desea remplazar estos campos?",
            icon: "info",
            buttons: true,
            closeonconfirm: false,
            buttons: ["Cancelar", "Sí"]
        })
                .then((willDelete) => {
                    if (willDelete) {
                        var nombreCargo = $("#txteditarNombreCargo").val();
                        var descripcionCargo = $("#txteditarDescripcionCargo").val();
                        var salario = $("#txteditarSalario").val();
                        var estadoCargo = $("#cmbEditarEstadoCargo").val();
                        var tipo = $("#cmbeditarTipo").val();
                        var sector = $("#cmbSector").val();
                        var canal = $("#cmbCanal").val();
                        var area = $("#cmbArea").val();     
                        alert(responsetext);
                        alert ("asdasdsadsad");
                        $.post("/SaleslandColombiaApp/cargo/editarcargo", {NombreCargo: nombreCargo, Descripcion: descripcionCargo, Salario: salario, Estado: estadoCargo, Tipo: tipo, Sector: sector, Canal: canal, Area: area }, function (responsetext) {
                            
                            if (responsetext == "200") {
                                swal("Actualizado Correctamente", "El Cargo ha sido actualizado exitosamente", "success").then((willDelete) => {
                                    if (willDelete) {
                                        window.location = "/SaleslandColombiaApp/ligth-bootstrap/Pages/cargo/listarcargo.jsp";
                                    }
                                });

                            } else {
                                swal("Ocurrio un error", "Lo sentimos tus datos de Actualizacion no fueron registrados, por favor intentalo nuevamente.", "error");
                            }
                        });
                    }
                });
    }
});


    
