package www.udb.edu.sv.gestionempleados.controller;

import www.udb.edu.sv.gestionempleados.dao.CargoDAO;
import www.udb.edu.sv.gestionempleados.model.Cargo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cargos")
public class CargoServlet extends HttpServlet {

    private CargoDAO cargoDAO;

    public void init() {
        cargoDAO = new CargoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertCargo(request, response);
                break;
            case "update":
                updateCargo(request, response);
                break;
            case "delete":
                deleteCargo(request, response);
                break;
            default:
                listCargos(request, response);
                break;
        }
    }

    private void listCargos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargos = cargoDAO.getAllCargos();
        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("/cargo-list.jsp").forward(request, response);
    }

    private void insertCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombreCargo = request.getParameter("cargo");
        String descripcionCargo = request.getParameter("descripcionCargo");
        boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        Cargo nuevoCargo = new Cargo(nombreCargo, descripcionCargo, jefatura);
        cargoDAO.insertCargo(nuevoCargo);
        response.sendRedirect("cargos?action=list");
    }

    private void updateCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCargo = Integer.parseInt(request.getParameter("idCargo"));
        String nombreCargo = request.getParameter("cargo");
        String descripcionCargo = request.getParameter("descripcionCargo");
        boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        Cargo cargoActualizado = new Cargo(idCargo, nombreCargo, descripcionCargo, jefatura);
        cargoDAO.updateCargo(cargoActualizado);
        response.sendRedirect("cargos?action=list");
    }

    private void deleteCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCargo = Integer.parseInt(request.getParameter("id"));
        cargoDAO.deleteCargo(idCargo);
        response.sendRedirect("cargos?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}