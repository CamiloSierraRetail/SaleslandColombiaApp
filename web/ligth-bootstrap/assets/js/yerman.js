$(document).ready(function(){
    //LocalStorage para el recuerdame almacenando el username
    $('#remember_me').click(function() {
        if ($('#remember_me').is(':checked')) {
            localStorage.username = $('#txtUsuarioSesion').val();
            localStorage.checkBox = $('#remember_me').val();
        } else {
            localStorage.username = '';
            localStorage.checkBox = '';
        }
    });
    if (localStorage.checkBox && localStorage.checkBox != '') {
        $('#remember_me').attr('checked', 'checked');
        $('#txtUsuarioSesion').val(localStorage.username);
    } else {
        $('#remember_me').removeAttr('checked');
        $('#txtUsuarioSesion').val('');
    }
});

