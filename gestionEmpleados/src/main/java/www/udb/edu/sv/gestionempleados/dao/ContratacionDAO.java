package www.udb.edu.sv.gestionempleados.dao;

import www.udb.edu.sv.gestionempleados.model.*;
import www.udb.edu.sv.gestionempleados.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionDAO {

    private Connection connection;

    public ContratacionDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Contratacion> getAllContrataciones() {
        List<Contratacion> contrataciones = new ArrayList<>();
        try {
            String sql = "SELECT c.idContratacion, d.idDepartamento, d.nombreDepartamento, e.idEmpleado, e.nombrePersona, ca.idCargo, ca.cargo, tc.idTipoContratacion, tc.tipoContratacion, c.fechaContratacion, c.salario, c.estado " +
                    "FROM Contrataciones c " +
                    "JOIN Departamento d ON c.idDepartamento = d.idDepartamento " +
                    "JOIN Empleados e ON c.idEmpleado = e.idEmpleado " +
                    "JOIN Cargos ca ON c.idCargo = ca.idCargo " +
                    "JOIN TipoContratacion tc ON c.idTipoContratacion = tc.idTipoContratacion";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idContratacion = rs.getInt("idContratacion");
                Departamento departamento = new Departamento(rs.getInt("idDepartamento"), rs.getString("nombreDepartamento"), null);
                Empleado empleado = new Empleado(rs.getInt("idEmpleado"), null, rs.getString("nombrePersona"), null, null, null, null);
                Cargo cargo = new Cargo(rs.getInt("idCargo"), rs.getString("cargo"), null, false);
                TipoContratacion tipoContratacion = new TipoContratacion(rs.getInt("idTipoContratacion"), rs.getString("tipoContratacion"));
                Date fechaContratacion = rs.getDate("fechaContratacion");
                double salario = rs.getDouble("salario");
                boolean estado = rs.getBoolean("estado");

                Contratacion contratacion = new Contratacion(idContratacion, departamento, empleado, cargo, tipoContratacion, fechaContratacion, salario, estado);
                contrataciones.add(contratacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrataciones;
    }

    public void insertContratacion(Contratacion contratacion) {
        try {
            String sql = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, contratacion.getDepartamento().getIdDepartamento());
            statement.setInt(2, contratacion.getEmpleado().getIdEmpleado());
            statement.setInt(3, contratacion.getCargo().getIdCargo());
            statement.setInt(4, contratacion.getTipoContratacion().getIdTipoContratacion());
            statement.setDate(5, contratacion.getFechaContratacion());
            statement.setDouble(6, contratacion.getSalario());
            statement.setBoolean(7, contratacion.isEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContratacion(Contratacion contratacion) {
        try {
            String sql = "UPDATE Contrataciones SET idDepartamento = ?, idEmpleado = ?, idCargo = ?, idTipoContratacion = ?, fechaContratacion = ?, salario = ?, estado = ? WHERE idContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, contratacion.getDepartamento().getIdDepartamento());
            statement.setInt(2, contratacion.getEmpleado().getIdEmpleado());
            statement.setInt(3, contratacion.getCargo().getIdCargo());
            statement.setInt(4, contratacion.getTipoContratacion().getIdTipoContratacion());
            statement.setDate(5, contratacion.getFechaContratacion());
            statement.setDouble(6, contratacion.getSalario());
            statement.setBoolean(7, contratacion.isEstado());
            statement.setInt(8, contratacion.getIdContratacion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContratacion(int idContratacion) {
        try {
            String sql = "DELETE FROM Contrataciones WHERE idContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idContratacion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contratacion getContratacion(int idContratacion) {
        Contratacion contratacion = null;
        try {
            String sql = "SELECT c.idContratacion, d.idDepartamento, d.nombreDepartamento, e.idEmpleado, e.nombrePersona, ca.idCargo, ca.cargo, tc.idTipoContratacion, tc.tipoContratacion, c.fechaContratacion, c.salario, c.estado " +
                    "FROM Contrataciones c " +
                    "JOIN Departamento d ON c.idDepartamento = d.idDepartamento " +
                    "JOIN Empleados e ON c.idEmpleado = e.idEmpleado " +
                    "JOIN Cargos ca ON c.idCargo = ca.idCargo " +
                    "JOIN TipoContratacion tc ON c.idTipoContratacion = tc.idTipoContratacion " +
                    "WHERE c.idContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idContratacion);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Departamento departamento = new Departamento(rs.getInt("idDepartamento"), rs.getString("nombreDepartamento"), null);
                Empleado empleado = new Empleado(rs.getInt("idEmpleado"), null, rs.getString("nombrePersona"), null, null, null, null);
                Cargo cargo = new Cargo(rs.getInt("idCargo"), rs.getString("cargo"), null, false);
                TipoContratacion tipoContratacion = new TipoContratacion(rs.getInt("idTipoContratacion"), rs.getString("tipoContratacion"));
                Date fechaContratacion = rs.getDate("fechaContratacion");
                double salario = rs.getDouble("salario");
                boolean estado = rs.getBoolean("estado");

                contratacion = new Contratacion(idContratacion, departamento, empleado, cargo, tipoContratacion, fechaContratacion, salario, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratacion;
    }
}
