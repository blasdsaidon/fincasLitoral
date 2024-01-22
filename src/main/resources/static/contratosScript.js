
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
function alertaVence(){
    let fechaActual = moment();
    let fechaCuota = document.getElementById("fechaUltimaLoca").innerText;
// Obtener la fecha de la cuota con Moment.js
let fechaCuotaMoment = moment(fechaCuota, 'YYYY-MM-DD');
if (fechaActual.isAfter(fechaCuotaMoment)) {
    alert("Hay cuotas vencidas")
}

}
function calcularIntereses(){

    // Obtener la fecha actual con Moment.js
let fechaActual = moment();

let fechaCuota = document.getElementById("fechaUltimaLoca").innerText;
// Obtener la fecha de la cuota con Moment.js
let fechaCuotaMoment = moment(fechaCuota, 'YYYY-MM-DD');

// Verificar si la fecha actual es posterior a la fecha de la cuota
if (fechaActual.isAfter(fechaCuotaMoment)) {
    // Calcular la diferencia de días solo si la fecha actual es posterior
    let diferenciaDias = fechaActual.diff(fechaCuotaMoment, 'days');

    console.log(diferenciaDias)

    let locacion = document.getElementById("montoLocararios").value;
    if(locacion!=null){

    let intereses = locacion*diferenciaDias*0.02

        
        
    document.getElementById("interesesLoca").value=intereses;
    
    }

}

}

function calculaHonorarios(){
let porcentaje=document.getElementById("porcentajeHono").innerText;
console.log(porcentaje)
let locacion=document.getElementById("montoLocararios").value;
console.log(locacion)
if(porcentaje&&locacion){
let honorarios=locacion*porcentaje/100;
console.log(honorarios);
document.getElementById("restandoHono").value=honorarios;
console.log(document.getElementById("restandoHono"))
}

}



function cuotaActual() {
    var cuotaActualElements = document.querySelectorAll('.cuotaActual');
    cuotaActualElements.forEach(element => {
        var miObjeto = JSON.parse(element.dataset.miObjeto);
        let fechaActual = moment().format("YYYY-MM-DD");
        let cuotaActual;
        let miObjetoSort = miObjeto.sort((a, b) => moment(a.mesAno, "YYYY-MM-DD").unix() - moment(b.mesAno, "YYYY-MM-DD").unix());
        
        for (let i = 0; i < miObjetoSort.length; i++) {
            if (!miObjetoSort[i].realizado ) {
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

// function setearMontos() {
    
//         let element = document.getElementById('listaHonorarios')
//         console.log(element.dataset);
//       if(element) { 
//         let arrayHonorarios = JSON.parse(element.dataset.listaHonorarios);
//         let fechaActual = moment().format("YYYY-MM-DD");
//         let cuotaActual;
        
        
//         for (let i = 0; i < arrayHonorarios.length; i++) {
//             if (moment(arrayHonorarios[i].mesAno, "YYYY-MM-DD").unix() > moment(fechaActual).unix()) {
//                 cuotaActual = arrayHonorarios[i];
//                 break;
//             }
//         }

//         if(cuotaActual.importe) document.getElementById('montoHonorarios').setAttribute('value', cuotaActual.importe );
//         // document.getElementById('cuotaFecha').setAttribute('value', cuotaActual.importe );
//         if(cuotaActual.importeTasa) document.getElementById('montoTasa').setAttribute('value', cuotaActual.importeTasa );
//         if(cuotaActual.importeAgua) document.getElementById('montoAgua').setAttribute('value', cuotaActual.importeAgua );
//         if(cuotaActual.mesAno) Array.from(document.getElementsByClassName('fechaCuota')).forEach(item => item.innerText = cuotaActual.mesAno) 
//        sumaTotal()}
// }



function sumaTotal(sumandos, restando, total){
    console.log(sumandos)
    console.log(restando)
    console.log(total)
    let sumatoria = 0;
    Array.from(sumandos).forEach((sumando)=>{
      if(sumando.value)  sumatoria += Number(sumando.value);
    })
    if(restando && restando.value) sumatoria=sumatoria-Number(restando.value);
    
    total.innerText = sumatoria;

}

function sumaLoca(){
    calcularIntereses();
    
    let sumandos = document.getElementsByClassName('sumandoLoca');
    let restando = document.getElementById('restandoLoca')
    let totalSuma = document.getElementById('sumaLoca');
    sumaTotal(sumandos, restando, totalSuma);
}

function sumaHono(){
    calculaHonorarios();
    let sumandos = document.getElementsByClassName('sumandoHono');
    let restando = document.getElementById('restandoHono')
    let totalSuma = document.getElementById('sumaHono');
    sumaTotal(sumandos, restando, totalSuma);
}



document.addEventListener('DOMContentLoaded', function() {
    var btns = document.getElementsByClassName('eliminarArchivoBtn');
    
    Array.from(btns).forEach((btn)=>{
    btn.addEventListener('click', function() {
        var idArchivo = this.getAttribute('data-idArchivo');
        var idContrato = this.getAttribute('data-idContrato');

        // Confirmación de eliminación
        if(confirm("¿Estás seguro de que quieres eliminar este archivo?")) {
            var xhr = new XMLHttpRequest();
            
            xhr.open('DELETE', '/archivo/eliminar/' + idArchivo + '/' + idContrato, true);
            
            xhr.onload = function() {
                if(xhr.status === 200) {
                    alert('Archivo eliminado correctamente');
                    // Aquí puedes redirigir o realizar otras acciones después de eliminar
                } else {
                    alert('Error al eliminar el archivo');
                }
            };
            
            xhr.onerror = function() {
                alert('Error de red');
            };
            
            xhr.send();

            location.reload();
        }
    });
});
});

function cargando(){
    let botones = document.getElementsByClassName("boton");


    Array.from(botones).forEach((boton) =>{
    boton.classList.add("btn-secondary");
    boton.setAttribute("disabled", true);
    boton.innerText = "Cargando..."
})
    
};


// Llamar a la función al cargar la página
window.addEventListener("load", function() {
    compararFechas();
    cuotaActual();
    sumaHono();
    alertaVence();
    sumaLoca();
    
    
});