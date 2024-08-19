<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Cargos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between my-4">
        <div>
            <button class="btn btn-success" data-toggle="modal" data-target="#cargoModal" onclick="clearForm()">Nuevo Cargo</button>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Regresar al Inicio</a>
        </div>
    </div>

    <h2>Lista de Cargos</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Cargo</th>
            <th>Descripción</th>
            <th>Jefatura</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cargo" items="${cargos}">
            <tr>
                <td>${cargo.idCargo}</td>
                <td>${cargo.cargo}</td>
                <td>${cargo.descripcionCargo}</td>
                <td><c:if test="${cargo.jefatura}">Sí</c:if><c:if test="${!cargo.jefatura}">No</c:if></td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#cargoModal"
                            onclick="editCargo(${cargo.idCargo}, '${cargo.cargo}', '${cargo.descripcionCargo}', ${cargo.jefatura})">Editar</button>
                    <a href="${pageContext.request.contextPath}/cargos?action=delete&id=${cargo.idCargo}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar este cargo?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="cargoModal" tabindex="-1" role="dialog" aria-labelledby="cargoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/cargos" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="cargoModalLabel">Cargo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idCargo" name="idCargo">
                    <div class="form-group">
                        <label for="cargo">Cargo</label>
                        <input type="text" class="form-control" id="cargo" name="cargo" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcionCargo">Descripción</label>
                        <textarea class="form-control" id="descripcionCargo" name="descripcionCargo" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="jefatura">¿Es Jefatura?</label>
                        <select class="form-control" id="jefatura" name="jefatura" required>
                            <option value="true">Sí</option>
                            <option value="false">No</option>
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
    function editCargo(id, cargo, descripcion, jefatura) {
        document.getElementById('idCargo').value = id;
        document.getElementById('cargo').value = cargo;
        document.getElementById('descripcionCargo').value = descripcion;
        document.getElementById('jefatura').value = jefatura;
        document.getElementById('cargoModalLabel').innerText = 'Editar Cargo';
        document.querySelector('form').action = "${pageContext.request.contextPath}/cargos?action=update";
    }

    function clearForm() {
        document.getElementById('idCargo').value = '';
        document.getElementById('cargo').value = '';
        document.getElementById('descripcionCargo').value = '';
        document.getElementById('jefatura').value = 'false';
        document.getElementById('cargoModalLabel').innerText = 'Nuevo Cargo';
        document.querySelector('form').action = "${pageContext.request.contextPath}/cargos?action=insert";
    }
</script>
</body>
</html>
