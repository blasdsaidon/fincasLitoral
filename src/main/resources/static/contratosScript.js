function compararFechasSeguro() {
    // Obtener la fecha actual
     // Obtener la fecha actual
     var fechaActual = moment().format["DD/MM/YYYY"];
     var filasOcultas=[];
     // Obtener la fecha de la tabla
     var elementosFecha = document.querySelectorAll('.seguroVence');

    elementosFecha.forEach(function(elemento) {
        var fechaTablaString = moment(elemento.innerText);
    
     
     // Calcular la diferencia en días
     var diferenciaEnDias =fechaTablaString.diff(fechaActual,'days');
        
     


     // Pintar el campo si la diferencia es de 40 días o menos
     if (diferenciaEnDias <= 15) {
         elemento.classList.add('destacar');
        }
    })
};


var filasOcultasMostradas = false;

function compararFechas() {
    // Obtener la fecha actual
     // Obtener la fecha actual
     var fechaActual = moment().format["DD/MM/YYYY"];
     var filasOcultas=[];
     // Obtener la fecha de la tabla
     var elementosFecha = document.querySelectorAll('.fechaTabla');
     // Obtener rescindidos de la tabla
     var rescindidos = document.querySelectorAll('.rescindido');
    elementosFecha.forEach(function(elemento) {
        var fechaTablaString = moment(elemento.innerText);
    
     
     // Calcular la diferencia en días
     var diferenciaEnDias =fechaTablaString.diff(fechaActual,'days');
        
     


     // Pintar el campo si la diferencia es de 30 días o menos
     if (diferenciaEnDias <= 40 && diferenciaEnDias == 0 ) {
         elemento.classList.add('destacar');
        } else if (diferenciaEnDias <= 0) {
            elemento.classList.add('vencido');
            // Ocultar la fila entera
            let fila = elemento.closest('tr'); // Navega al elemento <tr> más cercano
            if (fila) {
                fila.hidden = true; // Oculta la fila
                fila.classList.add('vencidas')
                filasOcultas.push(fila);
               
            }
         
     }
     
    })

    rescindidos.forEach(function(rescindido){

        let fila = rescindido.closest('tr'); // Navega al elemento <tr> más cercano
            if (fila) {
                fila.hidden = true; // Oculta la fila
                fila.classList.add('vencidas')
                filasOcultas.push(fila);
               
            }

    })

    if (filasOcultas.length > 0) {
        document.getElementById("mostrarFilasOcultas").style.display = "block";
    }

    moverFilasOcultasAlInicio(filasOcultas);

    function moverFilasOcultasAlInicio(filasOcultas) {
        var tbody = document.querySelector("#miTabla tbody");
    
        filasOcultas.forEach(function(fila) {
            tbody.insertBefore(fila, tbody.firstChild); // Mueve la fila oculta al inicio del tbody
        });
    }
}



function mostrarFilas() {
   
    var filasOcultas = document.querySelectorAll('.vencidas');
    filasOcultas.forEach(function(fila) {
        fila.hidden=!fila.hidden;
    })
    /* if (filasOcultasMostradas) {
        // Si las filas ya están mostradas, las volvemos a ocultar
        filasOcultas.forEach(function(fila) {
            fila.hidden = true;
        });
        document.getElementById("mostrarFilasOcultas").innerText = "Mostrar filas ocultas";
    } else {
        // Mostrar las filas ocultas
        filasOcultas.forEach(function(fila) {
            fila.hidden = false;
        });
        document.getElementById("mostrarFilasOcultas").innerText = "Ocultar filas ocultas";
    }

    // Cambiar el estado de las filas ocultas
    filasOcultasMostradas = !filasOcultasMostradas; */
}
function alertaVence(){
    let fechaActual = moment();
    let fechaCuota = document.getElementById("fechaUltimaLoca")?.innerText;
// Obtener la fecha de la cuota con Moment.js
let fechaCuotaMoment = moment(fechaCuota, 'YYYY-MM-DD');
if (fechaActual.isAfter(fechaCuotaMoment)) {
    alert("Hay cuotas vencidas")
}

}
let manualInput = false; // Flag para detectar input manual
 
function calcularIntereses(){
    // Evitar el cálculo si el valor fue ingresado manualmente
    if (manualInput) return;

    // Obtener la fecha actual con Moment.js
    let fechaActual = moment();

    let fechaCuota = document.getElementById("fechaUltimaLoca").innerText;
    // Obtener la fecha de la cuota con Moment.js
    let fechaCuotaMoment = moment(fechaCuota, 'YYYY-MM-DD');

    // Verificar si la fecha actual es posterior a la fecha de la cuota
    if (fechaActual.isAfter(fechaCuotaMoment)) {
        // Calcular la diferencia de días solo si la fecha actual es posterior
        let diferenciaDias = fechaActual.diff(fechaCuotaMoment, 'days');

        

        let locacion = document.getElementById("montoLocararios").value;
        if(locacion != null) {
            let intereses = locacion * diferenciaDias * 0.02;
            // Establecer el valor del campo sin disparar el input manual
            document.getElementById("interesesLoca").value = intereses;
        }
    }
}

// Detectar cuando el usuario escribe manualmente en el campo de intereses
document.getElementById("interesesLoca")?.addEventListener("input", function() {
    manualInput = true; // Cambiar el flag a true si el usuario escribe
});

// En caso de que se necesite recalcular los intereses
document.getElementById("recalcularIntereses")?.addEventListener("click", function() {
    manualInput = false; // Cambiar el flag a false para permitir el cálculo
    calcularIntereses(); // Llamar a la función de cálculo
});



function calculaHonorarios(){
let porcentaje=document.getElementById("porcentajeHono")?.innerText;

let locacion=document.getElementById("montoHonorarios")?.value;

if(porcentaje&&locacion){
let honorarios=locacion*porcentaje/100;

document.getElementById("restandoHono").value=honorarios;

}

}


       

function cuotaActual() {
    
    var cuotaActualElements = document.querySelectorAll('.cuotaActual');
    cuotaActualElements.forEach(element => {
        let periodoActualiza = JSON.parse(element.dataset.periodoActualiza);
        var miObjeto = JSON.parse(element.dataset.miObjeto);
        let fechaActual = moment().format("YYYY-MM-DD");
        let cuotaActual;
        let miObjetoSort = miObjeto.sort((a, b) => moment(a.mesAno, "YYYY-MM-DD").unix() - moment(b.mesAno, "YYYY-MM-DD").unix());
        
        for (let i = 0; i < miObjetoSort.length; i++) {
            if (!miObjetoSort[i].realizado ) {
                cuotaActual = miObjetoSort[i].mesAno;
               
                if((miObjetoSort[i].numeroCuota-1)%periodoActualiza==0){
                    cuotaActual+='<i style="cursor: help; margin-left: 3px;" title="Próximo aumento" class="bi bi-info-circle"></i>'
                }
                break;
            }
        }

        if (element) {
            element.innerHTML = cuotaActual || 'No hay cuotas futuras';

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
   
    let sumatoria = 0;
    Array.from(sumandos).forEach((sumando)=>{
      if(sumando.value) {
        const numFormateado = parseFloat(sumando.value).toFixed(2);
        sumando.value = numFormateado;
        sumatoria += Number(numFormateado);
      } 
    })
    if(restando && restando.value) {
        const numFormateado = parseFloat(restando.value).toFixed(2);
        restando.value = numFormateado;
        sumatoria=sumatoria-Number(numFormateado);
    }
    if(total){ 
        total.innerText = parseFloat(sumatoria).toFixed(2) ;}
   

}

function sumaLoca(){
    //calcularIntereses();
    
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
    compararFechasSeguro();
    sumaHono();
    alertaVence();
    sumaLoca();
    syncInput1();
    syncInput2();
    syncInput3();
    syncInput4();
    
    
});

 // Obtener referencias a los inputs por su ID
 
  
 


 // Función para sincronizar input1 con input2
 function syncInput1() {
    const input1 = document.getElementById('montoAgua');
    if(document.getElementById('montoAguaHono')){

        document.getElementById('montoAguaHono').value=input1.value;}
 }

 // Función para sincronizar input2 con input1
 function syncInput2() {
    const input2 = document.getElementById('montoAguaHono');
    if(document.getElementById('montoAgua')){
        document.getElementById('montoAgua').value = input2.value;
    }
 }
 function syncInput3(event) {
    const input3 = document.getElementById('montoTasa');
    if(document.getElementById('montoTasaHono')){
        document.getElementById('montoTasaHono').value = input3.value;
    }
 }
 function syncInput4(event) {
    const input4 = document.getElementById('montoTasaHono');
    if(document.getElementById('montoTasa')){
        document.getElementById('montoTasa').value = input4.value;
    }
 }

 // Añadir los event listeners para los dos inputs
 

function searchTable() {
    // Obtener el valor de búsqueda
    let input = document.getElementById('searchInput');
    let filter = input.value.toUpperCase();
    let table = document.querySelector(".custom-table");
    let tr = table.getElementsByTagName("tr");

    // Iterar sobre todas las filas de la tabla (excepto el encabezado)
    for (let i = 1; i < tr.length; i++) {
        let tdArray = tr[i].getElementsByTagName("td");
        let found = false;

        // Buscar en cada celda de la fila actual
        for (let j = 0; j < tdArray.length; j++) {
            let td = tdArray[j];
            if (td) {
                if (td.innerText.toUpperCase().indexOf(filter) > -1) {
                    found = true;
                    break;
                }
            }
        }

        // Mostrar u ocultar la fila según si se encontró una coincidencia
        if (found) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}
