$(document).ready(function(){
    //LocalStorage para el recuerdame almacenando el documento o email
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
                                            window.location.href = "/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/editarperfilusuario.jsp";
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

//Funcion para cargar datos semanales del chart
    function loadWeeklyData(idUsuario){
        $.ajax({
            url:"/SaleslandColombiaApp/ingreso/weeklyChart",
            method: "post",
            data:{
                idusuario: idUsuario
            },
            dataType:"json"
        }).done(function(data){
           if(data !== undefined){
               initIngresosChart(data);
           }else{
               swal('Error', 'Algo ocurrio al momento de solicitar los datos', 'error');
           }
        });
    }

//Funcion para inicializar chart de ingresos y salidas a lo largo de la semana
    function initIngresosChart(dt){  
        var variable;
        var Lunes = dt[0].split("/");
        var LunIn = Lunes[0].split(":");
        var LunOut = Lunes[1].split(":");
        
        var Martes = dt[1].split("/");
        var MarIn = Martes[0].split(":");
        var MarOut = Martes[1].split(":");
        
        var Miercoles = dt[2].split("/");  
        var MierIn = Miercoles[0].split(":");
        var MierOut = Miercoles[1].split(":");
        
        var Jueves = dt[3].split("/");
        var JueIn = Jueves[0].split(":");
        var JueOut = Jueves[1].split(":");
        
        var Viernes = dt[4].split("/");
        var VierIn = Viernes[0].split(":");
        var VierOut = Viernes[1].split(":");
        
        var Sabado = dt[5].split("/");
        var SaIn = Sabado[0].split(":");
        var SaOut = Sabado[1].split(":");
        
        var Domingo = dt[6].split("/");
        var DoIn = Domingo[0].split(":");
        var DoOut = Domingo[1].split(":");
        
        
        var chart = new Chartist.Line('.ct-chart', {
            labels: ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'],
            series: [
                //Chart series para los ingresos
                [
                    {meta: Lunes[0], value: LunIn[0]},
                    {meta: Martes[0], value: MarIn[0]},
                    {meta: Miercoles[0], value: MierIn[0]},
                    {meta: Jueves[0], value: JueIn[0]},
                    {meta: Viernes[0], value: VierIn[0]},
                    {meta: Sabado[0], value: SaIn[0]},
                    {meta: Domingo[0], value: DoIn[0]}
                ],
                //Chart series para las salidas
                [
                    {meta: Lunes[1], value: LunOut[0]},
                    {meta: Martes[1], value: MarOut[0]},
                    {meta: Miercoles[1], value: MierOut[0]},
                    {meta: Jueves[1], value: JueOut[0]},
                    {meta: Viernes[1], value: VierOut[0]},
                    {meta: Sabado[1], value: SaOut[0]},
                    {meta: Domingo[1], value: DoOut[0]}
                ]
            ]
        },{
            low: 0,
            axisY: {
                onlyInteger:true, 
                labelInterpolationFnc: function(value) {
                    if(value < 12){
                        variable = 'Ingreso';
                        return (value +"AM");
                    }else{
                        variable = 'Salida';
                        return (value +"PM");
                    }
                }
            },
            plugins: [
                Chartist.plugins.tooltip({
                    pointClass: 'customTooltipOnPoint'
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
            } else if(data.type === 'point') {
                console.log(variable);
                var circle = new Chartist.Svg('circle', {
                    cx: [data.x],
                    cy: [data.y],
                    r: [5],
                    'ct:value': variable,
                    'ct:meta': data.meta,
                    class: 'customTooltipOnPoint'
                }, 'ct-point');

                // With data.element we get the Chartist SVG wrapper and we can replace the original point drawn by Chartist with our newly created triangle
                data.element.replace(circle);

                data.element.animate({
                    x1: {
                      begin: seq * delays,
                      dur: durations,
                      from: cx - 10,
                      to: cx,
                      easing: 'easeOutQuart'
                    },
                    x2: {
                      begin: seq * delays,
                      dur: durations,
                      from: cx - 10,
                      to: cx,
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

