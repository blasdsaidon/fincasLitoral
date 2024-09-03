document.addEventListener('DOMContentLoaded', function () {
    var tbody = document.getElementById('datos-tbody');
    var totalHonorariosPorMes = 0;
    var mesActual = null;
    var filas = document.querySelectorAll('.dato-row');

    filas.forEach(function (row) {
        var mesAno = row.querySelector('.mes-ano').textContent;
        var descuentoHono = parseFloat(row.querySelector('.descuento-honorario').textContent);
        var nombrePropietario = row.querySelector('.nombre-propietario').textContent;

        if (mesActual !== mesAno) {
            // Insertar celda al final del mes anterior
            if (mesActual !== null) {
                insertarCeldaFinalMes(mesActual, totalHonorariosPorMes);
                totalHonorariosPorMes = 0;
            }

            mesActual = mesAno;
        }

        totalHonorariosPorMes += descuentoHono;
    });

    // Insertar la última celda si hay datos
    if (filas.length > 0) {
        insertarCeldaFinalMes(mesActual, totalHonorariosPorMes);
    }

    function insertarCeldaFinalMes(mes, total) {
        // Crear una nueva fila con la celda al final del mes
        var fila = document.createElement('tr');
        fila.innerHTML = '<td>Total ' + obtenerNombreMes(mes) + '</td>' + // Total Mes
            '<td></td>' + // Número de Cuota (puede ser vacío)
            '<td></td>' + // Número de Contrato (puede ser vacío)
            '<td>' + total.toFixed(2) + '</td>' + // Descuento Honorario
            '<td></td>'; // Nombre Propietario (puede ser vacío)

        // Buscar la última fila del mes manualmente
        var ultimaFilaDelMes = null;
        for (var i = filas.length - 1; i >= 0; i--) {
            if (filas[i].querySelector('.mes-ano').textContent === mes) {
                ultimaFilaDelMes = filas[i];
                break;
            }
        }

        // Resaltar toda la fila
        fila.classList.add('total-row');

        // Insertar la fila después de la última fila del mes
        if (ultimaFilaDelMes) {
            ultimaFilaDelMes.parentNode.insertBefore(fila, ultimaFilaDelMes.nextSibling);
        }
    }

    // Función para obtener el nombre del mes a partir de la fecha (formato "yyyy-MM-dd")
    function obtenerNombreMes(fecha) {
        var partesFecha = fecha.split('-');
        var numeroMes = parseInt(partesFecha[1]);
        var meses = [
            'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
        ];
        return meses[numeroMes - 1];
    }
});
