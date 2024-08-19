package www.udb.edu.sv.gestionempleados.dao;

import www.udb.edu.sv.gestionempleados.model.Departamento;
import www.udb.edu.sv.gestionempleados.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    private Connection connection;

    public DepartamentoDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Departamento> getAllDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Departamento";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idDepartamento = rs.getInt("idDepartamento");
                String nombreDepartamento = rs.getString("nombreDepartamento");
                String descripcionDepartamento = rs.getString("descripcionDepartamento");

                Departamento departamento = new Departamento(idDepartamento, nombreDepartamento, descripcionDepartamento);
                departamentos.add(departamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    public void insertDepartamento(Departamento departamento) {
        try {
            String sql = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, departamento.getNombreDepartamento());
            statement.setString(2, departamento.getDescripcionDepartamento());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartamento(Departamento departamento) {
        try {
            String sql = "UPDATE Departamento SET nombreDepartamento = ?, descripcionDepartamento = ? WHERE idDepartamento = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, departamento.getNombreDepartamento());
            statement.setString(2, departamento.getDescripcionDepartamento());
            statement.setInt(3, departamento.getIdDepartamento());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartamento(int idDepartamento) {
        try {
            String sql = "DELETE FROM Departamento WHERE idDepartamento = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDepartamento);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Departamento getDepartamento(int idDepartamento) {
        Departamento departamento = null;
        try {
            String sql = "SELECT * FROM Departamento WHERE idDepartamento = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDepartamento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String nombreDepartamento = rs.getString("nombreDepartamento");
                String descripcionDepartamento = rs.getString("descripcionDepartamento");
                departamento = new Departamento(idDepartamento, nombreDepartamento, descripcionDepartamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamento;
    }
}
