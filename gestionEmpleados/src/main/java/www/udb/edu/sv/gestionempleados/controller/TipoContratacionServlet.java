package www.udb.edu.sv.gestionempleados.controller;

import www.udb.edu.sv.gestionempleados.dao.TipoContratacionDAO;
import www.udb.edu.sv.gestionempleados.model.TipoContratacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tipocontratacion")
public class TipoContratacionServlet extends HttpServlet {

    private TipoContratacionDAO tipoContratacionDAO;

    public void init() {
        tipoContratacionDAO = new TipoContratacionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertTipoContratacion(request, response);
                break;
            case "update":
                updateTipoContratacion(request, response);
                break;
            case "delete":
                deleteTipoContratacion(request, response);
                break;
            default:
                listTiposContratacion(request, response);
                break;
        }
    }

    private void listTiposContratacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TipoContratacion> tiposContratacion = tipoContratacionDAO.getAllTiposContratacion();
        request.setAttribute("tiposContratacion", tiposContratacion);
        request.getRequestDispatcher("/tipo-contratacion-list.jsp").forward(request, response);
    }

    private void insertTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipoContratacion = request.getParameter("tipoContratacion");

        TipoContratacion nuevoTipoContratacion = new TipoContratacion(tipoContratacion);
        tipoContratacionDAO.insertTipoContratacion(nuevoTipoContratacion);
        response.sendRedirect("tipocontratacion?action=list");
    }

    private void updateTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
        String tipoContratacion = request.getParameter("tipoContratacion");

        TipoContratacion tipoActualizado = new TipoContratacion(idTipoContratacion, tipoContratacion);
        tipoContratacionDAO.updateTipoContratacion(tipoActualizado);
        response.sendRedirect("tipocontratacion?action=list");
    }

    private void deleteTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idTipoContratacion = Integer.parseInt(request.getParameter("id"));
        tipoContratacionDAO.deleteTipoContratacion(idTipoContratacion);
        response.sendRedirect("tipocontratacion?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
