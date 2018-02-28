$(".registrarSector").click(function (e){
    e.preventDefault();
    swal({
        title: "Confirmar datos",
        text: "¿Estas seguro de que deseas registrar un nuevo sector?",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
      },
      function(){
        setTimeout(function(){
            
            
            var nombreSector = $("#txtNombreSector").val();
            var descripcionSector = $("#txtDescripcionSector").val();
            
            $.post("../../../sector/registrar",{NombreSector:nombreSector,DescripcionSector:descripcionSector},function (responsetext) {
                        
               
                if(responsetext == "200"){
                    
                    swal({
                        title: "Success",
                        text: "Sector registrado exitosamente",
                        type: "success",
                        showCancelButton: false,
                        closeOnConfirm: false,
                        showLoaderOnConfirm: true
                    },function(){
                        //window.location = "/FutPlay/GaiaTemplate/index.html";
                    });
                    
                }else{
                    
                    swal({
                        title: "Ocurrio un error",
                        text: "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.",
                        type: "error",
                        showCancelButton: false,
                        closeOnConfirm: true,
                        showLoaderOnConfirm: true
                    },function(){
                        //window.location = "/FutPlay/GaiaTemplate/index.html";
                    });
                    //swal("Ocurrio un error", "Lo sentimos tus datos no fueron registrados, por favor intentalo nuevamente.","error");
                    
                }
                        
            });
            
        }, 2000);
    });
    
});