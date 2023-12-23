

        var fechaActual = moment().format("DD/MM/YYYY");
        const inserteFecha = document.getElementById("fecha");
        console.log(fechaActual);
        inserteFecha.innerText = fechaActual;

        function generarPDF() {
            const elementoParaConvertir = document.getElementById("contenido"); // <-- Aquí puedes elegir cualquier elemento del DOM
       
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

    function sumaTotal(){
        console.log("se llama");
        let sumandos = document.getElementsByClassName('sumando');
        let sumatoria = 0;
        Array.from(sumandos).forEach((sumando)=>{
            sumatoria += Number(sumando.value);
        })

        let sumaTotal = document.getElementById('suma');
        sumaTotal.innerText = sumatoria;

    }

    window.addEventListener("load", formatFecha);
    
       