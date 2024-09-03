

        var fechaActual = moment().format("DD/MM/YYYY");
        const inserteFecha = document.getElementById("fecha");
        console.log(fechaActual);
        inserteFecha.innerText = fechaActual;

        async function generarPDF() {
            const elementoParaConvertir = document.getElementById("contenido"); // <-- Aquí puedes elegir cualquier elemento del DOM
            let boton = document.getElementById("boton");

            boton.classList.add("btn-secondary");
            boton.setAttribute("disabled", true);
            boton.innerText = "Cargando..."

            let nombreCliente = document.getElementById("nombreCliente").innerText
            let fechaActual = document.getElementById("fecha").innerText
            
            const pdf = await html2pdf()

            .set({
                margin: 0,
                filename: `${nombreCliente}_${fechaActual}.pdf`,
                image: {
                    type: 'jpeg',
                    quality: 0.8,
                    
                },
                html2canvas: {
                    scale: 2, // A mayor escala, mejores gráficos, pero más peso
                    letterRendering: true,
                   width: 793,
                   height: 1000,
                   scrollY: 20,
                   scrollX: -10,
                    
                    
                },
                jsPDF: {
                    unit: "in",
                    format: "a4",
                    orientation: 'portrait' // landscape o portrait
                }
            })
            .from(elementoParaConvertir)
            .save()
            .toPdf().output('blob').then( (data) => {
               
                console.log(data);
           
    // Convierte el objeto PDF en un Blob para enviarlo al servidor
    const idCuota = document.getElementById('idCuota').innerText;
    const tipoRecibo = document.getElementById('tipoRecibo').innerText;
    const identificador = document.getElementById('identificador').innerText;
    const formData = new FormData();
    formData.append('tipoRecibo',tipoRecibo);
    formData.append('identificador',identificador);
    formData.append('file', new File([data], `${nombreCliente}_${fechaActual}.pdf`, { type: 'application/pdf' }));
    formData.append('idCuota', idCuota);
    // Envía el archivo al servidor
   fetch('/api/pdf/guardar', {
        method: 'POST',
        body: formData,
    })

    
    .then(data => {
        let esCuota1=document.getElementById('esCuota1')
        let enviarForm=document.getElementById('miFormulario');
         
        if(esCuota1){
         
         enviarForm.submit();
     }

    })
    .catch(error => console.error('Error al enviar el archivo al servidor', error))
    .finally(() => {
        // Restaura el estado del botón después de completar la operación
        boton.classList.remove("btn-secondary");
        boton.removeAttribute("disabled");
        boton.innerText = "Generar PDF";
        
    }) 

})
        // Descarga el PDF en el navegador
       
           

        };
        
        function formatFecha() {
            
            moment.locale('es', {
                months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split('_'),
                monthsShort: 'Enero._Feb._Mar_Abr._May_Jun_Jul._Ago_Sept._Oct._Nov._Dec.'.split('_'),
                weekdays: 'Domingo_Lunes_Martes_Miercoles_Jueves_Viernes_Sabado'.split('_'),
                weekdaysShort: 'Dom._Lun._Mar._Mier._Jue._Vier._Sab.'.split('_'),
                weekdaysMin: 'Do_Lu_Ma_Mi_Ju_Vi_Sa'.split('_')
              })
        // Obtener la fecha en formato ISO desde el elemento span
        var fechasIso = document.getElementsByClassName('fechaIso');
        let añosIso = document.getElementsByClassName('añoIso');
        Array.from(fechasIso).forEach((fechaIso)=>{
        // Utilizar moment.js para formatear la fecha
        var fechaFormateada = moment(fechaIso.innerText).format('MMMM');
        
        // Insertar la fecha formateada de nuevo en el elemento span
        

        Array.from(añosIso).forEach((añoIso)=>{

            let fechaAño = moment(fechaIso.innerText).format('yyyy');

            añoIso.innerText = fechaAño;
        })
        fechaIso.innerText = fechaFormateada;
    })
    }

    function sumaTotal(sumandos, restando, total){
        
        let sumatoria = 0;
        Array.from(sumandos).forEach((sumando)=>{
            console.log(sumando)

          if(sumando.innerText) {
            
            const textFormateado = parseFloat(sumando.innerText).toFixed(2);
            sumando.innerText = textFormateado;
            sumatoria += Number(textFormateado);
          }
            else if(sumando.value){
                const numFormateado = parseFloat(sumando.value).toFixed(2);
                sumando.value = numFormateado;
                 sumatoria += Number(numFormateado);
            }
        })
        if(restando && restando.innerText) {
            const textFormateado = parseFloat(restando.innerText).toFixed(2);
            restando.innerText = textFormateado;
        sumatoria=sumatoria-Number(textFormateado);
        }
        else if(restando && restando.value) {
            const numFormateado = parseFloat(restando.value).toFixed(2);
            restando.value = numFormateado;
            sumatoria = sumatoria - Number(numFormateado);
        }
        total.innerText = parseFloat(sumatoria).toFixed(2);
    
    }

    function sumaHono(){
        let sumandos = document.getElementsByClassName('sumandoHono');
        let restando = document.getElementById('restandoHono')
        let totalSuma = document.getElementById('sumaHono');
        sumaTotal(sumandos, restando, totalSuma);
    }

    

    window.addEventListener("load", function() {
    formatFecha();
    sumaHono();
});
    
       