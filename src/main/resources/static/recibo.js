

        var fechaActual = moment().format("DD/MM/YYYY");
        const inserteFecha = document.getElementById("fecha");
        
        inserteFecha.innerText = fechaActual;

        async function generarPDF() {
            const elementoParaConvertir = document.getElementById("contenido");
            let boton = document.getElementById("boton");
        
            boton.classList.add("btn-secondary");
            boton.setAttribute("disabled", true);
            boton.innerText = "Cargando...";
        
            let nombreCliente = document.getElementById("nombreCliente").innerText;
            let fechaActual = document.getElementById("fecha").innerText;
        
            // Obtener todos los elementos textarea en la página
    const textareas = document.querySelectorAll('textarea');
    const replacements = [];

    // Reemplazar cada textarea con un párrafo temporalmente
    textareas.forEach((textarea) => {
        const textareaContent = textarea.value;

        // Crear un nuevo párrafo con el mismo tamaño y estilo del textarea
        const newParagraph = document.createElement('p');
        newParagraph.textContent = textareaContent;
        newParagraph.style.cssText = window.getComputedStyle(textarea).cssText; // Copia el estilo del textarea
        newParagraph.style.whiteSpace = 'pre-wrap'; // Para que respete los saltos de línea

        // Reemplazar el textarea con el nuevo párrafo
        textarea.parentNode.replaceChild(newParagraph, textarea);

        // Guardar la referencia del párrafo y textarea para restaurarlos más tarde
        replacements.push({ textarea, newParagraph });
    });
        
            const pdf = await html2pdf()
                .set({
                    margin: 0,
                    filename: `${nombreCliente}_${fechaActual}.pdf`,
                    image: {
                        type: 'jpeg',
                        quality: 0.8,
                    },
                    html2canvas: {
                        scale: 2,
                        letterRendering: true,
                        width: 793,
                        height: 1000,
                        scrollY: 20,
                        scrollX: -10,
                    },
                    jsPDF: {
                        unit: "in",
                        format: "a4",
                        orientation: 'portrait'
                    }
                })
                .from(elementoParaConvertir)
                .save()
                .toPdf().output('blob')
                .then((data) => {
                    
        
                    const idCuota = document.getElementById('idCuota').innerText;
                    const tipoRecibo = document.getElementById('tipoRecibo').innerText;
                    const identificador = document.getElementById('identificador').innerText;
                    const formData = new FormData();
                    formData.append('tipoRecibo', tipoRecibo);
                    formData.append('identificador', identificador);
                    formData.append('file', new File([data], `${nombreCliente}_${fechaActual}.pdf`, { type: 'application/pdf' }));
                    formData.append('idCuota', idCuota);
        
                    fetch('/api/pdf/guardar', {
                        method: 'POST',
                        body: formData,
                    })
                    .then(data => {
                        let esCuota1 = document.getElementById('esCuota1');
                        let enviarForm = document.getElementById('miFormulario');
        
                        if (esCuota1) {
                            enviarForm.submit();
                        }
                    })
                    .catch(error => console.error('Error al enviar el archivo al servidor', error))
                    .finally(() => {
                        boton.classList.remove("btn-secondary");
                        boton.removeAttribute("disabled");
                        boton.innerText = "Generar PDF";
        
                        // Restaurar el textarea después de generar el PDF
                        newParagraph.parentNode.replaceChild(textarea, newParagraph);
                    });
                });
        
        
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
    
       