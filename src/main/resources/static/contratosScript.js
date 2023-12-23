
function compararFechas() {
    // Obtener la fecha actual
     // Obtener la fecha actual
     var fechaActual = moment().format["DD/MM/YYYY"];

     // Obtener la fecha de la tabla
     var elementosFecha = document.querySelectorAll('.fechaTabla');

    elementosFecha.forEach(function(elemento) {
        var fechaTablaString = moment(elemento.innerText);
    
     console.log(fechaTablaString.diff(fechaActual,'days'));
     // Calcular la diferencia en días
     var diferenciaEnDias =fechaTablaString.diff(fechaActual,'days');
        
    


     // Pintar el campo si la diferencia es de 30 días o menos
     if (diferenciaEnDias <= 30 && diferenciaEnDias > 10) {
         elemento.classList.add('resaltar');
     }else if(diferenciaEnDias <= 10) {
        elemento.classList.add('destacar');
     }
     
    })
}


function cuotaActual() {
    var cuotaActualElements = document.querySelectorAll('.cuotaActual');
    cuotaActualElements.forEach(element => {
        var miObjeto = JSON.parse(element.dataset.miObjeto);
        let fechaActual = moment().format("YYYY-MM-DD");
        let cuotaActual;
        let miObjetoSort = miObjeto.sort((a, b) => moment(a.mesAno, "YYYY-MM-DD").unix() - moment(b.mesAno, "YYYY-MM-DD").unix());
        
        for (let i = 0; i < miObjetoSort.length; i++) {
            if (moment(miObjetoSort[i].mesAno, "YYYY-MM-DD").unix() > moment(fechaActual).unix()) {
                cuotaActual = miObjetoSort[i].mesAno;
                break;
            }
        }

        if (element) {
            element.textContent = cuotaActual || 'No hay cuotas futuras';

            let cuotaFecha = moment(cuotaActual);
            var diferenciaEnDias = cuotaFecha.diff(fechaActual, 'days');

            if (diferenciaEnDias <= 5) {
                element.classList.add('resaltar');
            }
        }
    });
}






// Llamar a la función al cargar la página
window.addEventListener("load", function() {
    compararFechas();
    cuotaActual();
});