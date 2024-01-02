

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

            html2pdf()
            .set({
                margin: 0,
                filename: 'documento.pdf',
                image: {
                    type: 'png',
                    quality: 0.98,
                    
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
            .then(()=>{
                boton.classList.remove("btn-secondary");
                boton.removeAttribute("disabled");
                boton.innerText = "Generar PDF"
            })
            .catch(err => console.log(err));

            

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
    
       