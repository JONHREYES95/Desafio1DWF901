<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Departamentos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between my-4">
        <div>
            <button class="btn btn-success" data-toggle="modal" data-target="#departamentoModal" onclick="clearForm()">Nuevo Departamento</button>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Regresar al Inicio</a>
        </div>
    </div>

    <h2>Lista de Departamentos</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="departamento" items="${departamentos}">
            <tr>
                <td>${departamento.idDepartamento}</td>
                <td>${departamento.nombreDepartamento}</td>
                <td>${departamento.descripcionDepartamento}</td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#departamentoModal"
                            onclick="editDepartamento(${departamento.idDepartamento}, '${departamento.nombreDepartamento}', '${departamento.descripcionDepartamento}')">Editar</button>
                    <a href="${pageContext.request.contextPath}/departamentos?action=delete&id=${departamento.idDepartamento}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar este departamento?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="departamentoModal" tabindex="-1" role="dialog" aria-labelledby="departamentoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/departamentos" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="departamentoModalLabel">Departamento</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idDepartamento" name="idDepartamento">
                    <div class="form-group">
                        <label for="nombreDepartamento">Nombre</label>
                        <input type="text" class="form-control" id="nombreDepartamento" name="nombreDepartamento" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcionDepartamento">Descripción</label>
                        <textarea class="form-control" id="descripcionDepartamento" name="descripcionDepartamento" required></textarea>
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
    function editDepartamento(id, nombre, descripcion) {
        document.getElementById('idDepartamento').value = id;
        document.getElementById('nombreDepartamento').value = nombre;
        document.getElementById('descripcionDepartamento').value = descripcion;
        document.getElementById('departamentoModalLabel').innerText = 'Editar Departamento';
        document.querySelector('form').action = "${pageContext.request.contextPath}/departamentos?action=update";
    }

    function clearForm() {
        document.getElementById('idDepartamento').value = '';
        document.getElementById('nombreDepartamento').value = '';
        document.getElementById('descripcionDepartamento').value = '';
        document.getElementById('departamentoModalLabel').innerText = 'Nuevo Departamento';
        document.querySelector('form').action = "${pageContext.request.contextPath}/departamentos?action=insert";
    }
</script>
</body>
</html>
