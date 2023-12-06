/*Elegir provincia y ciudad, llamado a la api*/

async function obtenerProvincia(){
    fetch("https://apis.datos.gob.ar/georef/api/provincias?orden=nombre&campos=id,nombre")
    .then(res=>res.json())
    .then(data=>{
        
        const selectProvincia=document.getElementsByClassName("selectProvincia") 
        
        const selectLocatCiudad=document.getElementById("ciudadesLocat")
        const selectLocadCiudad=document.getElementById("ciudadesLocad")
        const selectCodCiudad=document.getElementById("ciudadesCod")
        const selectInmCiudad=document.getElementById("ciudadesInm")

        const selectLocat=document.getElementById("provinciasLocat")
        const selectLocad=document.getElementById("provinciasLocad")
        const selectInm=document.getElementById("provinciasInm")
        const selectCod=document.getElementById("provinciasCod")

        Array.from(selectProvincia).forEach(select =>{
        data.provincias.forEach(provincia=> {
            
            
            let nuevaOpcion = document.createElement("option");
            nuevaOpcion.value = provincia.id;
            nuevaOpcion.text = provincia.nombre;
            select.add(nuevaOpcion)
            } 
             )
        })
          selectCod.addEventListener("change",()=>rellenaCiudades(selectCod.value,selectCodCiudad))
          selectInm.addEventListener("change",()=>rellenaCiudades(selectInm.value,selectInmCiudad))
          selectLocad.addEventListener("change",()=>rellenaCiudades(selectLocad.value,selectLocadCiudad))
          selectLocat.addEventListener("change",()=>rellenaCiudades(selectLocat.value,selectLocatCiudad))

            

    
    }).catch(error=>{
        console.log(error)
    })


    async function rellenaCiudades(provinciaId,ciudadSelect) {
        
        // Obtiene el ID de la provincia seleccionada
        
      
        // Realiza la solicitud a la API para obtener las ciudades de la provincia
        fetch("https://apis.datos.gob.ar/georef/api/localidades?max=1000&campos=id,nombre&orden=nombre&provincia="+ provinciaId)
          .then(response => response.json())
          .then(data => {

           
            // Limpia las opciones anteriores en el select de ciudades
            ciudadSelect.innerHTML = "";
      
            // Agrega las ciudades obtenidas de la API al select de ciudades
            data.localidades.forEach(ciudad => {
                let nuevaOpcion = document.createElement("option");
                nuevaOpcion.value = ciudad.id;
                nuevaOpcion.text = ciudad.nombre;
                ciudadSelect.add(nuevaOpcion)}   )
          })
          .catch(error => console.error("Error al obtener ciudades:", error));
      }

}



obtenerProvincia()

/*eliminar 1 archivo seleccionado*/

function agregarFuncionInput(input, contenedor) {
    input.addEventListener("change", function () {
        // Elimina todos los elementos existentes en el contenedor
        contenedor.innerHTML = "";

        if (input.files.length > 0) {
            const fileName = input.files[0].name;

            // Crea un nuevo elemento <li> y agrega el nombre del archivo como contenido
            const liItem = document.createElement("li");
            liItem.textContent = fileName;

            // Crea el botón "Quitar"
            const myButtonRemove = document.createElement("button");
            myButtonRemove.textContent = "Quitar";
            myButtonRemove.classList.add("btn")
            myButtonRemove.classList.add("button-margin")
            myButtonRemove.classList.add("btn-primary")
            myButtonRemove.addEventListener("click", function () {
                // Elimina el elemento <li>
                liItem.remove();

                // Limpia el input correspondiente
                input.value = "";

                // Oculta el botón "Quitar"
                myButtonRemove.style.display = 'none';
            });

            const myButtonOpen = document.createElement("a");
        myButtonOpen.textContent = "Abrir";
        myButtonOpen.classList.add("button-margin")
        myButtonOpen.classList.add("btn");
        myButtonOpen.classList.add("btn-primary");
        myButtonOpen.type="button";
        myButtonOpen.href = URL.createObjectURL(input.files[0]);
        myButtonOpen.target = "_blank";

        
            liItem.appendChild(myButtonOpen);
            // Agrega el botón "Quitar" al elemento <li>
            liItem.appendChild(myButtonRemove);

            // Agrega el elemento <li> al contenedor de vista previa
            contenedor.appendChild(liItem);
        }
    });
}

// Obtén los elementos de input y contenedor específicos
const wordFile = document.getElementById("word");
const previewWord = document.getElementById("previewWord");

const otroFile = document.getElementById("pdf");
const previewOtro = document.getElementById("previewPdf");

// Aplica la funcionalidad a los inputs específicos
agregarFuncionInput(wordFile, previewWord);
agregarFuncionInput(otroFile, previewOtro);

/*seleccionar varios archivos*/

 /*
  * Variables
  */

 let filesList = [];


 const classDragOver = "drag-over";
 const fileInputMulti = document.querySelector("#multi-selector-uniq #files");

 // DEMO Preview
 const multiSelectorUniqPreview = document.querySelector("#multi-selector-uniq #preview");

 /*
  * Functions
  */

 /**
  * Returns the index of an Array of Files from its name. If there are multiple files with the same name, the last one will be returned.
  * @param {string} name - Name file.
  * @param {Array<File>} list - List of files.
  * @return number
  */
 function getIndexOfFileList(name, list) {
     return list.reduce(
         (position, file, index) => (file.name === name ? index : position),
         -1
     );
 }

 /**
  * Returns a File in text.
  * @param {File} file
  * @return {Promise<string>}
  */
 async function encodeFileToText(file) {
     return file.text().then((text) => {
         return text;
     });
 }

 /**
  * Returns an Array from the union of 2 Arrays of Files avoiding repetitions.
  * @param {Array<File>} newFiles
  * @param {Array<File>} currentListFiles
  * @return Promise<File[]>
  */
 async function getUniqFiles(newFiles, currentListFiles) {
     return new Promise((resolve) => {
         Promise.all(newFiles.map((inputFile) => encodeFileToText(inputFile))).then(
             (inputFilesText) => {
                //  Check all the files to save
                 Promise.all(
                     currentListFiles.map((savedFile) => encodeFileToText(savedFile))
                 ).then((savedFilesText) => {
                     let newFileList = currentListFiles;
                     inputFilesText.forEach((inputFileText, index) => {
                         if (!savedFilesText.includes(inputFileText)) {
                             newFileList = newFileList.concat(newFiles[index]);
                         }
                     });
                     resolve(newFileList);
                 });
             }
         );
     });
 }

 /**
  * Only DEMO. Render preview.
  * @param currentFileList
  * @Only .EMO> param target.
  * @
  */
 function renderPreviews(currentFileList, target, inputFile) {
    target.textContent = "";
    currentFileList.forEach((file, index) => {
        const myLi = document.createElement("li");
        myLi.textContent = file.name;
        myLi.setAttribute("draggable", 'true');
        myLi.dataset.key = file.name;
        myLi.addEventListener("drop", eventDrop);
        myLi.addEventListener("dragover", eventDragOver);

        const myButtonRemove = document.createElement("button");
        myButtonRemove.textContent = "Quitar";
        myButtonRemove.classList.add("btn")
        myButtonRemove.classList.add("button-margin");
        myButtonRemove.classList.add("btn-primary");
        myButtonRemove.addEventListener("click", () => {
            filesList = deleteArrayElementByIndex(currentFileList, index);
            inputFile.files = arrayFilesToFileList(filesList);
            return renderPreviews(filesList, multiSelectorUniqPreview, inputFile);
        });

        const myButtonOpen = document.createElement("a");
        myButtonOpen.textContent = "Abrir";
        myButtonOpen.classList.add("btn");
        myButtonOpen.classList.add("button-margin");
        myButtonOpen.classList.add("btn-primary");
        myButtonOpen.type="button";
        myButtonOpen.href = URL.createObjectURL(file);
        myButtonOpen.target = "_blank";

        myLi.appendChild(myButtonOpen);
        myLi.appendChild(myButtonRemove);
        target.appendChild(myLi);

    });
}

 /**
  * Returns a copy of the array by removing one position by index.
  * @param {Array<any>} list
  * @param {number} index
  * @return {Array<any>} list
  */
 function deleteArrayElementByIndex(list, index) {
     return list.filter((item, itemIndex) => itemIndex !== index);
 }

 /**
  * Returns a FileLists from an array containing Files.
  * @param {Array<File>} filesList
  * @return {FileList}
  */
 function arrayFilesToFileList(filesList) {
     return filesList.reduce(function (dataTransfer, file) {
         dataTransfer.items.add(file);
         return dataTransfer;
     }, new DataTransfer()).files;
 }


 /**
  * Returns a copy of the Array by swapping 2 indices.
  * @param {number} firstIndex
  * @param {number} secondIndex
  * @param {Array<any>} list
  */
 function arraySwapIndex(firstIndex, secondIndex, list) {
     const tempList = list.slice();
     const tmpFirstPos = tempList[firstIndex];
     tempList[firstIndex] = tempList[secondIndex];
     tempList[secondIndex] = tmpFirstPos;
     return tempList;
 }

 /*
  * Events
  */

 // Input file
 fileInputMulti.addEventListener("input", async () => {
   //   Get files list from <input>
     const newFilesList = Array.from(fileInputMulti.files);
    //  Update list files
     filesList = await getUniqFiles(newFilesList, filesList);
     // Only DEMO. Redraw
     renderPreviews(filesList, multiSelectorUniqPreview, fileInputMulti);
     // Set data to input
     fileInputMulti.files = arrayFilesToFileList(filesList);
 });



  //Drag and drop

  //Drag Start - Moving element.
 let myDragElement = undefined;
 document.addEventListener("dragstart", (event) => {
     // Saves which element is moving.
     myDragElement = event.target;
 });

 // Drag over - Element that is below the element that is moving.
 function eventDragOver(event) {
    //  Remove from all elements the class that will show that it is a drop zone.
     event.preventDefault();
     multiSelectorUniqPreview
         .querySelectorAll("li")
         .forEach((item) => item.classList.remove(classDragOver));

     // On the element above it, the class is added to show that it is a drop zone.
     event.target.classList.add(classDragOver);
 }

//  Drop - Element on which it is dropped.
 function eventDrop(event) {
     // The element that is underneath the element that is moving when it is released is captured.
     const myDropElement = event.target;
     // The positions of the elements in the array are swapped. The dataset key is used as an index.
     filesList = arraySwapIndex(
         getIndexOfFileList(myDragElement.dataset.key, filesList),
         getIndexOfFileList(myDropElement.dataset.key, filesList),
         filesList
     );
    //  The content of the input file is updated.
     fileInputMulti.files = arrayFilesToFileList(filesList);
 //     Only DEMO. Changes are redrawn.
     renderPreviews(filesList, multiSelectorUniqPreview, fileInputMulti);
 }



/*validar nombre*/



    function validacionInputLocador(){

        const apellidoLocador = document.getElementById("apellidoLocador").value 
        const nameLocador = document.getElementById("nameLocador").value
    
    
    if(!(/^[A-Z]+$/i).test(nameLocador) || nameLocatario == null || nameLocatario.length == 0 ) { 
        alert('[ERROR] El nombre no puede estar vacío o contiene caracteres prohibidos')
        return false;}

        else if(!(/^[A-Z]+$/i).test(apellidoLocador) || apellido == null || apellido.length == 0 ) { 
            alert('[ERROR] El apellido no puede estar vacío o contiene caracteres prohibidos')
            return false;}

        

        else{
            return true;}
        
}

function validacionInputLocatario(){

    const apellidoLocatario = document.getElementById("apellidoLocatario").value 
    const nameLocatario = document.getElementById("nameLocatario").value


if(!(/^[A-Z]+$/i).test(nameLocatario) || nameLocatario == null || nameLocatario.length == 0 ) { 
    alert('[ERROR] El nombre no puede estar vacío o contiene caracteres prohibidos')
    return false;}

    else if(!(/^[A-Z]+$/i).test(apellidoLocatario) || apellido == null || apellido.length == 0 ) { 
        alert('[ERROR] El apellido no puede estar vacío o contiene caracteres prohibidos')
        return false;}

    

    else{
        return true;}
    
}

function validacionInputCodeu(){

    const apellidoCodeu = document.getElementById("apellidoCodeu").value 
    const nameCodeu = document.getElementById("nameCodeu").value


if(!(/^[A-Z]+$/i).test(nameCodeu) || nameCodeu == null || nameCodeu.length == 0 ) { 
    alert('[ERROR] El nombre no puede estar vacío o contiene caracteres prohibidos')
    return false;}

    else if(!(/^[A-Z]+$/i).test(apellidoCodeu) || apellido == null || apellido.length == 0 ) { 
        alert('[ERROR] El apellido no puede estar vacío o contiene caracteres prohibidos')
        return false;}

    

    else{
        return true;}
    
}

    function validarInput(event) {
        const inputValue = event.target.value;
        const regex = /[a-z,]/i;
    
        if (!regex.test(inputValue)) {
            // Si el valor no coincide con la expresión regular, elimina el último carácter
            event.target.value = inputValue.slice(0, -1);
        }
    
        const lastChar = inputValue.slice(-1);
    
        // Verifica si el último carácter es un número y elimínalo si es necesario
        if (!isNaN(lastChar)) {
            event.target.value = inputValue.slice(0, -1);
        }
    }
    


    

    

    document.getElementById("opciones").addEventListener("change", function () {
        mostrarOpcionesSeleccionadas();
    });

    function agregarOpcion() {
        var select = document.getElementById("opciones");
        var opcionesSeleccionadas = document.getElementById("opcionesSeleccionadas");
        var opcionesArray = obtenerArrayDesdeCampoOculto();

        for (var i = 0; i < select.options.length; i++) {
            if (select.options[i].selected) {
                var opcion = select.options[i].value;
                var codeuName = select.options[i].label;
                console.log(opcion)
                console.log(opcionesArray)
                console.log(select.options[i])
                // Verifica si la opción ya está en el array antes de agregarla
                if (opcionesArray.indexOf(opcion) === -1) {
                    opcionesArray.push(opcion);

                    // Agrega la opción a la lista visible
                    var nuevaOpcion = document.createElement("li");
                    nuevaOpcion.classList.add("d-flex")
                    
                    nuevaOpcion.innerHTML =  '<div class="capitalizar" style="text-align: center;">' +
                    codeuName +
                    '<button class="btn-primary button-remove" type="button" onclick="quitarOpcion(\'' + opcion + '\')">Quitar</button>' +
                  '</div>';
                    opcionesSeleccionadas.appendChild(nuevaOpcion);
                }
            }
        }

        // Actualiza el array oculto en el formulario
        actualizarCampoOculto(opcionesArray);
    }

    function quitarOpcion(opcion) {
        var opcionesSeleccionadas = document.getElementById("opcionesSeleccionadas");
        var opcionesArray = obtenerArrayDesdeCampoOculto();

        // Elimina la opción de la lista visible
        for (var i = 0; i < opcionesSeleccionadas.childNodes.length; i++) {
            if (opcionesSeleccionadas.childNodes[i].innerHTML.includes(opcion)) {
                opcionesSeleccionadas.removeChild(opcionesSeleccionadas.childNodes[i]);
                break;
            }
        }

        // Elimina la opción del array
        opcionesArray = opcionesArray.filter(function (item) {
            return item !== opcion;
        });

        // Actualiza el array oculto en el formulario
        actualizarCampoOculto(opcionesArray);
    }

    function actualizarCampoOculto(opcionesArray) {
        var inputOpciones = document.getElementById("opcionesSeleccionadasInput");

        // Si el campo oculto ya existe, actualiza su valor; de lo contrario, créalo
        if (inputOpciones) {
            inputOpciones.value = JSON.stringify(opcionesArray);
        } else {
            inputOpciones = document.createElement("input");
            inputOpciones.type = "hidden";
            inputOpciones.name = "opcionesSeleccionadas";
            inputOpciones.id = "opcionesSeleccionadasInput";
            inputOpciones.value = JSON.stringify(opcionesArray);
            document.getElementById("formContrato").appendChild(inputOpciones);
        }
    }

    function obtenerArrayDesdeCampoOculto() {
        var inputOpciones = document.getElementById("opcionesSeleccionadasInput");

        if (inputOpciones && inputOpciones.value) {
            return JSON.parse(inputOpciones.value);
        } else {
            return [];
        }
    }

    function mostrarOpcionesSeleccionadas() {
        // Puedes implementar esto si deseas mostrar las opciones seleccionadas al cambiar la selección
    }















