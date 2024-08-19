<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="www.udb.edu.sv.gestionempleados.util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Empleados</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
            max-width: 700px;
        }
        .btn-custom {
            width: 100%;
            margin-bottom: 10px;
            background-color: #17a2b8; /* Color personalizado para uniformar todos los botones */
            color: white;
        }
        .status-badge {
            font-size: 16px;
            padding: 10px;
        }
        .divider {
            margin: 20px 0;
            border-top: 1px solid #ddd;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <h1 class="mb-4">Gestión de Empleados</h1>

    <div>
        <!-- Label de conexión -->
        <%
            boolean isConnected = false;
            try {
                if (DBConnection.getConnection() != null) {
                    isConnected = true;
                }
            } catch (Exception e) {
                isConnected = false;
            }
        %>
        <span class="badge <%= isConnected ? "badge-success" : "badge-danger" %> status-badge">
            <%= isConnected ? "Conexión exitosa a la base de datos" : "Fallo en la conexión a la base de datos" %>
        </span>
    </div>

    <br/><br/>

    <!-- Enlaces -->
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/departamentos?action=list" class="btn btn-custom">Módulo Departamentos</a>
        <a href="${pageContext.request.contextPath}/cargos?action=list" class="btn btn-custom">Módulo Cargos</a>
        <a href="${pageContext.request.contextPath}/tipocontratacion?action=list" class="btn btn-custom">Módulo Tipo de Contratación</a>

        <div class="divider"></div> <!-- Línea divisora -->

        <a href="${pageContext.request.contextPath}/empleados?action=list" class="btn btn-custom">Módulo Empleados</a>
        <a href="${pageContext.request.contextPath}/contrataciones?action=list" class="btn btn-custom">Módulo Contrataciones</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.9/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
