<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Empleados</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between my-4">
        <div>
            <button class="btn btn-success" data-toggle="modal" data-target="#empleadoModal" onclick="clearForm()">Nuevo Empleado</button>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Regresar al Inicio</a>
        </div>
    </div>

    <h2>Lista de Empleados</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Número DUI</th>
            <th>Nombre</th>
            <th>Usuario</th>
            <th>Número de Teléfono</th>
            <th>Correo Institucional</th>
            <th>Fecha de Nacimiento</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="empleado" items="${empleados}">
            <tr>
                <td>${empleado.idEmpleado}</td>
                <td>${empleado.numeroDUI}</td>
                <td>${empleado.nombrePersona}</td>
                <td>${empleado.usuario}</td>
                <td>${empleado.numeroTelefono}</td>
                <td>${empleado.correoInstitucional}</td>
                <td>${empleado.fechaNacimiento}</td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#empleadoModal"
                            onclick="editEmpleado(${empleado.idEmpleado}, '${empleado.numeroDUI}', '${empleado.nombrePersona}', '${empleado.usuario}', '${empleado.numeroTelefono}', '${empleado.correoInstitucional}', '${empleado.fechaNacimiento}')">Editar</button>
                    <a href="${pageContext.request.contextPath}/empleados?action=delete&id=${empleado.idEmpleado}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar este empleado?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="empleadoModal" tabindex="-1" role="dialog" aria-labelledby="empleadoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/empleados" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="empleadoModalLabel">Empleado</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idEmpleado" name="idEmpleado">
                    <div class="form-group">
                        <label for="numeroDUI">Número DUI</label>
                        <input type="text" class="form-control" id="numeroDUI" name="numeroDUI" required>
                    </div>
                    <div class="form-group">
                        <label for="nombrePersona">Nombre</label>
                        <input type="text" class="form-control" id="nombrePersona" name="nombrePersona" required>
                    </div>
                    <div class="form-group">
                        <label for="usuario">Usuario</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" required>
                    </div>
                    <div class="form-group">
                        <label for="numeroTelefono">Número de Teléfono</label>
                        <input type="text" class="form-control" id="numeroTelefono" name="numeroTelefono" required>
                    </div>
                    <div class="form-group">
                        <label for="correoInstitucional">Correo Institucional</label>
                        <input type="email" class="form-control" id="correoInstitucional" name="correoInstitucional" required>
                    </div>
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.9/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function editEmpleado(id, numeroDUI, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) {
        document.getElementById('idEmpleado').value = id;
        document.getElementById('numeroDUI').value = numeroDUI;
        document.getElementById('nombrePersona').value = nombrePersona;
        document.getElementById('usuario').value = usuario;
        document.getElementById('numeroTelefono').value = numeroTelefono;
        document.getElementById('correoInstitucional').value = correoInstitucional;
        document.getElementById('fechaNacimiento').value = fechaNacimiento;
        document.getElementById('empleadoModalLabel').innerText = 'Editar Empleado';
        document.querySelector('form').action = "${pageContext.request.contextPath}/empleados?action=update";
    }

    function clearForm() {
        document.getElementById('idEmpleado').value = '';
        document.getElementById('numeroDUI').value = '';
        document.getElementById('nombrePersona').value = '';
        document.getElementById('usuario').value = '';
        document.getElementById('numeroTelefono').value = '';
        document.getElementById('correoInstitucional').value = '';
        document.getElementById('fechaNacimiento').value = '';
        document.getElementById('empleadoModalLabel').innerText = 'Nuevo Empleado';
        document.querySelector('form').action = "${pageContext.request.contextPath}/empleados?action=insert";
    }
</script>
</body>
</html>
