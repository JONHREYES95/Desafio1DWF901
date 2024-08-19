package www.udb.edu.sv.gestionempleados.controller;

import www.udb.edu.sv.gestionempleados.dao.*;
import www.udb.edu.sv.gestionempleados.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/contrataciones")
public class ContratacionServlet extends HttpServlet {

    private ContratacionDAO contratacionDAO;
    private DepartamentoDAO departamentoDAO;
    private EmpleadoDAO empleadoDAO;
    private CargoDAO cargoDAO;
    private TipoContratacionDAO tipoContratacionDAO;

    public void init() {
        contratacionDAO = new ContratacionDAO();
        departamentoDAO = new DepartamentoDAO();
        empleadoDAO = new EmpleadoDAO();
        cargoDAO = new CargoDAO();
        tipoContratacionDAO = new TipoContratacionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertContratacion(request, response);
                break;
            case "update":
                updateContratacion(request, response);
                break;
            case "delete":
                deleteContratacion(request, response);
                break;
            default:
                listContrataciones(request, response);
                break;
        }
    }

    private void listContrataciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contratacion> contrataciones = contratacionDAO.getAllContrataciones();
        List<Departamento> departamentos = departamentoDAO.getAllDepartamentos();
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        List<Cargo> cargos = cargoDAO.getAllCargos();
        List<TipoContratacion> tiposContratacion = tipoContratacionDAO.getAllTiposContratacion();

        request.setAttribute("contrataciones", contrataciones);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("empleados", empleados);
        request.setAttribute("cargos", cargos);
        request.setAttribute("tiposContratacion", tiposContratacion);
        request.getRequestDispatcher("/contratacion-list.jsp").forward(request, response);
    }

    private void insertContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        int idCargo = Integer.parseInt(request.getParameter("idCargo"));
        int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
        Date fechaContratacion = Date.valueOf(request.getParameter("fechaContratacion"));
        double salario = Double.parseDouble(request.getParameter("salario"));
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Departamento departamento = departamentoDAO.getDepartamento(idDepartamento);
        Empleado empleado = empleadoDAO.getEmpleado(idEmpleado);
        Cargo cargo = cargoDAO.getCargo(idCargo);
        TipoContratacion tipoContratacion = tipoContratacionDAO.getTipoContratacion(idTipoContratacion);

        Contratacion nuevaContratacion = new Contratacion(departamento, empleado, cargo, tipoContratacion, fechaContratacion, salario, estado);
        contratacionDAO.insertContratacion(nuevaContratacion);
        response.sendRedirect("contrataciones?action=list");
    }

    private void updateContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idContratacion = Integer.parseInt(request.getParameter("idContratacion"));
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        int idCargo = Integer.parseInt(request.getParameter("idCargo"));
        int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
        Date fechaContratacion = Date.valueOf(request.getParameter("fechaContratacion"));
        double salario = Double.parseDouble(request.getParameter("salario"));
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Departamento departamento = departamentoDAO.getDepartamento(idDepartamento);
        Empleado empleado = empleadoDAO.getEmpleado(idEmpleado);
        Cargo cargo = cargoDAO.getCargo(idCargo);
        TipoContratacion tipoContratacion = tipoContratacionDAO.getTipoContratacion(idTipoContratacion);

        Contratacion contratacionActualizada = new Contratacion(idContratacion, departamento, empleado, cargo, tipoContratacion, fechaContratacion, salario, estado);
        contratacionDAO.updateContratacion(contratacionActualizada);
        response.sendRedirect("contrataciones?action=list");
    }

    private void deleteContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idContratacion = Integer.parseInt(request.getParameter("id"));
        contratacionDAO.deleteContratacion(idContratacion);
        response.sendRedirect("contrataciones?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
