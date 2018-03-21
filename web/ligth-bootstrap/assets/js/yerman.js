$(document).ready(function(){
    //LocalStorage para el recuerdame almacenando el documento o email
    $('#remember_me').click(function() {
        if ($('#remember_me').is(':checked')) {
            localStorage.docEmail = $('#txtUsuarioSesion').val();
            localStorage.checkBox = $('#remember_me').val();
        } else {
            localStorage.docEmail = '';
            localStorage.checkBox = '';
        }
    });
    if (localStorage.checkBox && localStorage.checkBox != '') {
        $('#remember_me').attr('checked', 'checked');
        $('#txtUsuarioSesion').val(localStorage.docEmail);
    } else {
        $('#remember_me').removeAttr('checked');
        $('#txtUsuarioSesion').val('');
    }
    
    //Onchange del input file en la edicion del perfil para actualizar el txtArchivo
    $("#txtFotoFile").on('change',function(){
        validarImagen(this);
    });

    //Funcion para actualizar los datos del perfil
    $('#frmEditarPerfil').validate({
        rules: {
            txtNombre: {
                required: true,
                minlength: 5
            },
            txtApellido: {
                required: true,
                minlength: 5
            },
            txtDireccion:{
                required:true,
                minlength: 5
            },
            txtTelefono:{
                required:true
            },
            txtEmail:{
                email:true,
                required:true,
                validarEmail:true
            },
            txtCelular:{
                required:true,
                validarTelefono:true
            },
            cmbGenero:{
                required:true
            },
            dtFechaNacimiento:{
                required:true
            }
        },
        messages: {
            txtNombre: {
                required: 'Este campo es obligatorio',
                minlength: 'Debes ingresar 5 caracteres como minimo'
            },
            txtApellido: {
                required: 'Este campo es obligatorio',
                minlength: 'Debes ingresar 5 caracteres como minimo'
            },
            txtDireccion:{
                required:'Este campo es obligatorio',
                minlength: 'Debes ingresar 5 caracteres como minimo'
            },
            txtTelefono:{
                required:'Este campo es obligatorio'
            },
            txtEmail:{
                email:'Ingresa un email valido',
                required:'Este campo es obligatorio',
                validarEmail:'Ingresa un email valido'
            },
            txtCelular:{
                required:'Este campo es obligatorio',
                validarTelefono:'Ingresa un telefono celular valido'
            },
            cmbGenero:{
                required:'Este campo es obligatorio'
            },
            dtFechaNacimiento:{
                required:'Este campo es obligatorio'
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
        },
        submitHandler: function () {
            var txtIdUsuario = $("#txtIdUsuario").val();
            var txtNombre = $("#txtNombre").val();
            var txtApellido = $("#txtApellido").val();
            var txtDireccion = $("#txtDireccion").val();
            var txtTelefono = $("#txtTelefono").val();
            var txtEmail = $("#txtEmail").val();
            var txtCelular = $("#txtCelular").val();
            var cmbGenero = $("#cmbGenero").val();
            var dtFechaNacimiento = moment($("#dtFechaNacimiento").val()).format("YYYY/MM/DD");
            var txtFoto = $("#txtFoto").val();
            $.ajax({
                url:"/SaleslandColombiaApp/usuario/verificarEmail",
                method:"post",
                data:{
                    email: txtEmail,
                    idusuario: txtIdUsuario
                },
                dataType:"json"
            }).done(function(data){
                console.log(data);
               if(data > "0"){
                   swal({
                        title: "Advertencia",
                        text: "¿Toda tu informacion es correcta?",
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
                        }).then((confirm) => {
                            if (confirm) {
                                setTimeout(function(){
                                    //Subir Foto
                                    var data = new FormData();
                                    $.each($('#txtFotoFile')[0].files, function(i, file) {
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
                                    //ActualizarPerfil
                                    $.ajax({
                                        url:"/SaleslandColombiaApp/usuario/actualizarPerfil",
                                        method:"post",
                                        data:{
                                            idUsuario: txtIdUsuario, 
                                            nombres: txtNombre, 
                                            apellidos: txtApellido, 
                                            direccion: txtDireccion, 
                                            telefono: txtTelefono,
                                            email: txtEmail,
                                            celular: txtCelular,
                                            genero: cmbGenero,
                                            fechaNacimiento: dtFechaNacimiento,
                                            foto: txtFoto
                                        },
                                        dataType:"json"
                                    }).done(function(data){
                                        if(data > "0"){
                                            window.location.href = "/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/verPerfil.jsp";
                                        }else{
                                            swal("Error","Algo ocurrio al momento de procesar tu solicitud","error");
                                        }
                                    });
                                },2000);
                            }
                        });
               }else{
                    swal("Advertencia", "El correo electronico que ingresaste ya se encuentra en uso", "warning");
               }
            });
        }
    });
    //Funcion para recuperar cuenta mediante envio de codigo al email o al telefono
    $("#btnRecuperarCuenta").on("click", function(){
        swal({
            
        }).then((confirm) =>{
            if(confirm){
                
            }
        });
    });
});

