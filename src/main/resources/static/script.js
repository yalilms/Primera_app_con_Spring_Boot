document.addEventListener('DOMContentLoaded', function() {
    cargarEnemigos();

    const form = document.getElementById('enemigoForm');
    form.addEventListener('submit', agregarEnemigo);

    const cancelBtn = document.getElementById('cancelBtn');
    cancelBtn.addEventListener('click', cancelarEdicion);

    // Event delegation para botones de editar y eliminar
    document.addEventListener('click', function(e) {
        if (e.target.classList.contains('btn-editar')) {
            const id = e.target.getAttribute('data-id');
            const nombre = e.target.getAttribute('data-nombre');
            const afiliacion = e.target.getAttribute('data-afiliacion');
            const pais = e.target.getAttribute('data-pais');
            editarEnemigo(id, nombre, afiliacion, pais);
        }

        if (e.target.classList.contains('btn-eliminar')) {
            const id = e.target.getAttribute('data-id');
            eliminarEnemigo(id);
        }
    });
});

async function cargarEnemigos() {
    try {
        const response = await fetch('/api/enemigo');
        const data = await response.json();

        const tbody = document.getElementById('enemigosBody');
        const table = document.getElementById('usuariosTable');
        const noData = document.getElementById('noData');

        tbody.innerHTML = '';

        if (data.length > 0) {
            table.style.display = 'table';
            noData.style.display = 'none';

            data.forEach(enemigo => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${enemigo.id}</td>
                    <td>${enemigo.nombre}</td>
                    <td>${enemigo.afiliacionpolitica}</td>
                    <td>${enemigo.pais}</td>
                    <td>
                        <button class="btn-editar" data-id="${enemigo.id}" data-nombre="${enemigo.nombre}" data-afiliacion="${enemigo.afiliacionpolitica}" data-pais="${enemigo.pais}">Editar</button>
                        <button class="btn-eliminar" data-id="${enemigo.id}">Eliminar</button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        } else {
            table.style.display = 'none';
            noData.style.display = 'block';
        }
    } catch (error) {
        console.error('Error al cargar enemigos:', error);
        mostrarMensaje('Error al cargar los enemigos', 'error');
    }
}

async function agregarEnemigo(event) {
    event.preventDefault();

    const id = document.getElementById('enemigoId').value;
    const nombre = document.getElementById('nombre').value;
    const afiliacionpolitica = document.getElementById('afiliacionpolitica').value;
    const pais = document.getElementById('pais').value;

    const enemigo = {
        nombre: nombre,
        afiliacionpolitica: afiliacionpolitica,
        pais: pais
    };

    try {
        let response;
        if (id) {
            response = await fetch(`/api/enemigo/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(enemigo)
            });
        } else {
            response = await fetch('/api/enemigo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(enemigo)
            });
        }

        if (response.ok) {
            mostrarMensaje(id ? 'Enemigo actualizado exitosamente' : 'Enemigo agregado exitosamente', 'exito');
            document.getElementById('enemigoForm').reset();
            cancelarEdicion();
            cargarEnemigos();
        } else {
            mostrarMensaje('Error al procesar el enemigo', 'error');
        }
    } catch (error) {
        console.error('Error al procesar enemigo:', error);
        mostrarMensaje('Error al conectar con el servidor', 'error');
    }
}

function mostrarMensaje(texto, tipo) {
    const mensaje = document.getElementById('mensaje');
    mensaje.textContent = texto;
    mensaje.className = 'mensaje ' + tipo;

    setTimeout(() => {
        mensaje.className = 'mensaje';
    }, 3000);
}

function editarEnemigo(id, nombre, afiliacionpolitica, pais) {
    document.getElementById('enemigoId').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('afiliacionpolitica').value = afiliacionpolitica;
    document.getElementById('pais').value = pais;

    document.getElementById('formTitle').textContent = 'Editar Enemigo';
    document.getElementById('submitBtn').textContent = 'Actualizar Enemigo';
    document.getElementById('cancelBtn').style.display = 'inline-block';
}

function cancelarEdicion() {
    document.getElementById('enemigoId').value = '';
    document.getElementById('enemigoForm').reset();
    document.getElementById('formTitle').textContent = 'Agregar Nuevo Enemigo';
    document.getElementById('submitBtn').textContent = 'Agregar Enemigo';
    document.getElementById('cancelBtn').style.display = 'none';
}

async function eliminarEnemigo(id) {
    if (!confirm('¿Estás seguro de que deseas eliminar este enemigo?')) {
        return;
    }

    try {
        const response = await fetch(`/api/enemigo/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            mostrarMensaje('Enemigo eliminado exitosamente', 'exito');
            cargarEnemigos();
        } else {
            mostrarMensaje('Error al eliminar el enemigo', 'error');
        }
    } catch (error) {
        console.error('Error al eliminar enemigo:', error);
        mostrarMensaje('Error al conectar con el servidor', 'error');
    }
}
