<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Tipos de Contratación</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between my-4">
        <div>
            <button class="btn btn-success" data-toggle="modal" data-target="#tipoContratacionModal" onclick="clearForm()">Nuevo Tipo de Contratación</button>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Regresar al Inicio</a>
        </div>
    </div>

    <h2>Lista de Tipos de Contratación</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tipo de Contratación</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tipoContratacion" items="${tiposContratacion}">
            <tr>
                <td>${tipoContratacion.idTipoContratacion}</td>
                <td>${tipoContratacion.tipoContratacion}</td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#tipoContratacionModal"
                            onclick="editTipoContratacion(${tipoContratacion.idTipoContratacion}, '${tipoContratacion.tipoContratacion}')">Editar</button>
                    <a href="${pageContext.request.contextPath}/tipocontratacion?action=delete&id=${tipoContratacion.idTipoContratacion}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de eliminar este tipo de contratación?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="tipoContratacionModal" tabindex="-1" role="dialog" aria-labelledby="tipoContratacionModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/tipocontratacion" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="tipoContratacionModalLabel">Tipo de Contratación</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idTipoContratacion" name="idTipoContratacion">
                    <div class="form-group">
                        <label for="tipoContratacion">Tipo de Contratación</label>
                        <input type="text" class="form-control" id="tipoContratacion" name="tipoContratacion" required>
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
    function editTipoContratacion(id, tipoContratacion) {
        document.getElementById('idTipoContratacion').value = id;
        document.getElementById('tipoContratacion').value = tipoContratacion;
        document.getElementById('tipoContratacionModalLabel').innerText = 'Editar Tipo de Contratación';
        document.querySelector('form').action = "${pageContext.request.contextPath}/tipocontratacion?action=update";
    }

    function clearForm() {
        document.getElementById('idTipoContratacion').value = '';
        document.getElementById('tipoContratacion').value = '';
        document.getElementById('tipoContratacionModalLabel').innerText = 'Nuevo Tipo de Contratación';
        document.querySelector('form').action = "${pageContext.request.contextPath}/tipocontratacion?action=insert";
    }
</script>
</body>
</html>
