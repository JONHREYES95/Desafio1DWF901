package www.udb.edu.sv.gestionempleados.controller;

import www.udb.edu.sv.gestionempleados.dao.DepartamentoDAO;
import www.udb.edu.sv.gestionempleados.model.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/departamentos")
public class DepartamentoServlet extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    public void init() {
        departamentoDAO = new DepartamentoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertDepartamento(request, response);
                break;
            case "update":
                updateDepartamento(request, response);
                break;
            case "delete":
                deleteDepartamento(request, response);
                break;
            default:
                listDepartamentos(request, response);
                break;
        }
    }

    private void listDepartamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Departamento> departamentos = departamentoDAO.getAllDepartamentos();
        request.setAttribute("departamentos", departamentos);
        request.getRequestDispatcher("/departamento-list.jsp").forward(request, response);
    }

    private void insertDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreDepartamento = request.getParameter("nombreDepartamento");
        String descripcionDepartamento = request.getParameter("descripcionDepartamento");

        Departamento nuevoDepartamento = new Departamento(nombreDepartamento, descripcionDepartamento);
        departamentoDAO.insertDepartamento(nuevoDepartamento);
        response.sendRedirect("departamentos?action=list");
    }

    private void updateDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        String nombreDepartamento = request.getParameter("nombreDepartamento");
        String descripcionDepartamento = request.getParameter("descripcionDepartamento");

        Departamento departamentoActualizado = new Departamento(idDepartamento, nombreDepartamento, descripcionDepartamento);
        departamentoDAO.updateDepartamento(departamentoActualizado);
        response.sendRedirect("departamentos?action=list");
    }

    private void deleteDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idDepartamento = Integer.parseInt(request.getParameter("id"));
        departamentoDAO.deleteDepartamento(idDepartamento);
        response.sendRedirect("departamentos?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
