package www.udb.edu.sv.gestionempleados.controller;

import www.udb.edu.sv.gestionempleados.dao.EmpleadoDAO;
import www.udb.edu.sv.gestionempleados.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO;

    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertEmpleado(request, response);
                break;
            case "update":
                updateEmpleado(request, response);
                break;
            case "delete":
                deleteEmpleado(request, response);
                break;
            default:
                listEmpleados(request, response);
                break;
        }
    }

    private void listEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/empleado-list.jsp").forward(request, response);
    }

    private void insertEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numeroDUI = request.getParameter("numeroDUI");
        String nombrePersona = request.getParameter("nombrePersona");
        String usuario = request.getParameter("usuario");
        String numeroTelefono = request.getParameter("numeroTelefono");
        String correoInstitucional = request.getParameter("correoInstitucional");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

        Empleado nuevoEmpleado = new Empleado(numeroDUI, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento);
        empleadoDAO.insertEmpleado(nuevoEmpleado);
        response.sendRedirect("empleados?action=list");
    }

    private void updateEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("idEmpleado");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("empleados?action=list");
            return;
        }

        int idEmpleado = Integer.parseInt(idStr);
        String numeroDUI = request.getParameter("numeroDUI");
        String nombrePersona = request.getParameter("nombrePersona");
        String usuario = request.getParameter("usuario");
        String numeroTelefono = request.getParameter("numeroTelefono");
        String correoInstitucional = request.getParameter("correoInstitucional");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

        Empleado empleadoActualizado = new Empleado(idEmpleado, numeroDUI, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento);
        empleadoDAO.updateEmpleado(empleadoActualizado);
        response.sendRedirect("empleados?action=list");
    }

    private void deleteEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.deleteEmpleado(idEmpleado);
        response.sendRedirect("empleados?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}