
function compararFechas() {
    // Obtener la fecha actual
     // Obtener la fecha actual
     var fechaActual = moment().format["YYYY/MM/DD"];

     // Obtener la fecha de la tabla
     var elementosFecha = document.querySelectorAll('.fechaTabla');

    elementosFecha.forEach(function(elemento) {
        var fechaTablaString = moment(elemento.innerText);
    
     console.log(fechaTablaString.diff(fechaActual,'days'));
     // Calcular la diferencia en días
     var diferenciaEnDias =fechaTablaString.diff(fechaActual,'days');
 
     // Pintar el campo si la diferencia es de 30 días o menos
     if (diferenciaEnDias <= 30) {
         elemento.classList.add('resaltar');
     }
    })
}

// Llamar a la función al cargar la página

window.addEventListener("load", function() {
    compararFechas();
});