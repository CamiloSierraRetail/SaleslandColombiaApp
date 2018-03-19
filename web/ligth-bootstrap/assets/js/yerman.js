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
        var file = $(this)[0].files[0];
        $("#txtFoto").val(file.name);
    });
    
    //Funcion para actualizar los datos del perfil
    $('#frmRegistrarSector').validate({
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
                
            },
            txtEmail:{
                
            },
            txtCelular:{
                
            },
            cmbGenero:{
                
            },
            dtFechaNacimiento:{
                
            }
        },
        messages: {
            NombreSector: {
                required: "Este campo es requerido",
                minlength: "Ingresa 5 caracteres como minimo"
            },
            DescripcionSector: {
                required: "Este campo es requerido",
                minlength: "Ingresa 15 caracteres como minimo",
                maxlength: "Ingresa 80 caracteres como maximo"
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
            
        }
    });
});

