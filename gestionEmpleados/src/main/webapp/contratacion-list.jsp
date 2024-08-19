<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Contrataciones</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between my-4">
        <div>
            <button class="btn btn-success" data-toggle="modal" data-target="#contratacionModal" onclick="clearForm()">Nueva Contratación</button>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Regresar al Inicio</a>
        </div>
    </div>

    <h2>Lista de Contrataciones</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Departamento</th>
            <th>Empleado</th>
            <th>Cargo</th>
            <th>Tipo de Contratación</th>
            <th>Fecha</th>
            <th>Salario</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contratacion" items="${contrataciones}">
            <tr>
                <td>${contratacion.idContratacion}</td>
                <td>${contratacion.departamento.nombreDepartamento}</td>
                <td>${contratacion.empleado.nombrePersona}</td>
                <td>${contratacion.cargo.cargo}</td>
                <td>${contratacion.tipoContratacion.tipoContratacion}</td>
                <td>${contratacion.fechaContratacion}</td>
                <td>${contratacion.salario}</td>
                <td><c:if test="${contratacion.estado}">Activo</c:if><c:if test="${!contratacion.estado}">Inactivo</c:if></td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#contratacionModal"
                            onclick="editContratacion(${contratacion.idContratacion}, ${contratacion.departamento.idDepartamento}, ${contratacion.empleado.idEmpleado}, ${contratacion.cargo.idCargo}, ${contratacion.tipoContratacion.idTipoContratacion}, '${contratacion.fechaContratacion}', ${contratacion.salario}, ${contratacion.estado})">Editar</button>
                    <a href="${pageContext.request.contextPath}/contrataciones?action=delete&id=${contratacion.idContratacion}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar esta contratación?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="contratacionModal" tabindex="-1" role="dialog" aria-labelledby="contratacionModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/contrataciones" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="contratacionModalLabel">Contratación</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idContratacion" name="idContratacion">
                    <div class="form-group">
                        <label for="idDepartamento">Departamento</label>
                        <select class="form-control" id="idDepartamento" name="idDepartamento" required>
                            <c:forEach var="departamento" items="${departamentos}">
                                <option value="${departamento.idDepartamento}">${departamento.nombreDepartamento}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="idEmpleado">Empleado</label>
                        <select class="form-control" id="idEmpleado" name="idEmpleado" required>
                            <c:forEach var="empleado" items="${empleados}">
                                <option value="${empleado.idEmpleado}">${empleado.nombrePersona}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="idCargo">Cargo</label>
                        <select class="form-control" id="idCargo" name="idCargo" required>
                            <c:forEach var="cargo" items="${cargos}">
                                <option value="${cargo.idCargo}">${cargo.cargo}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="idTipoContratacion">Tipo de Contratación</label>
                        <select class="form-control" id="idTipoContratacion" name="idTipoContratacion" required>
                            <c:forEach var="tipoContratacion" items="${tiposContratacion}">
                                <option value="${tipoContratacion.idTipoContratacion}">${tipoContratacion.tipoContratacion}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="fechaContratacion">Fecha de Contratación</label>
                        <input type="date" class="form-control" id="fechaContratacion" name="fechaContratacion" required>
                    </div>
                    <div class="form-group">
                        <label for="salario">Salario</label>
                        <input type="number" step="0.01" class="form-control" id="salario" name="salario" required>
                    </div>
                    <div class="form-group">
                        <label for="estado">Estado</label>
                        <select class="form-control" id="estado" name="estado" required>
                            <option value="true">Activo</option>
                            <option value="false">Inactivo</option>
                        </select>
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
    function editContratacion(id, idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) {
        document.getElementById('idContratacion').value = id;
        document.getElementById('idDepartamento').value = idDepartamento;
        document.getElementById('idEmpleado').value = idEmpleado;
        document.getElementById('idCargo').value = idCargo;
        document.getElementById('idTipoContratacion').value = idTipoContratacion;
        document.getElementById('fechaContratacion').value = fechaContratacion;
        document.getElementById('salario').value = salario;
        document.getElementById('estado').value = estado;
        document.getElementById('contratacionModalLabel').innerText = 'Editar Contratación';
        document.querySelector('form').action = "${pageContext.request.contextPath}/contrataciones?action=update";
    }

    function clearForm() {
        document.getElementById('idContratacion').value = '';
        document.getElementById('idDepartamento').value = '';
        document.getElementById('idEmpleado').value = '';
        document.getElementById('idCargo').value = '';
        document.getElementById('idTipoContratacion').value = '';
        document.getElementById('fechaContratacion').value = '';
        document.getElementById('salario').value = '';
        document.getElementById('estado').value = 'true';
        document.getElementById('contratacionModalLabel').innerText = 'Nueva Contratación';
        document.querySelector('form').action = "${pageContext.request.contextPath}/contrataciones?action=insert";
    }
</script>
</body>
</html>
