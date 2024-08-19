package www.udb.edu.sv.gestionempleados.dao;

import www.udb.edu.sv.gestionempleados.model.Empleado;
import www.udb.edu.sv.gestionempleados.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
                empleado.setNumeroDUI(resultSet.getString("numeroDUI"));
                empleado.setNombrePersona(resultSet.getString("nombrePersona"));
                empleado.setUsuario(resultSet.getString("usuario"));
                empleado.setNumeroTelefono(resultSet.getString("numeroTelefono"));
                empleado.setCorreoInstitucional(resultSet.getString("correoInstitucional"));
                empleado.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Empleado getEmpleado(int idEmpleado) {
        Empleado empleado = null;
        String sql = "SELECT * FROM Empleados WHERE idEmpleado = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idEmpleado);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
                empleado.setNumeroDUI(resultSet.getString("numeroDUI"));
                empleado.setNombrePersona(resultSet.getString("nombrePersona"));
                empleado.setUsuario(resultSet.getString("usuario"));
                empleado.setNumeroTelefono(resultSet.getString("numeroTelefono"));
                empleado.setCorreoInstitucional(resultSet.getString("correoInstitucional"));
                empleado.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Utiliza un logger en un entorno real
        }
        return empleado;
    }

    public boolean insertEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleados(numeroDUI, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, empleado.getNumeroDUI());
            preparedStatement.setString(2, empleado.getNombrePersona());
            preparedStatement.setString(3, empleado.getUsuario());
            preparedStatement.setString(4, empleado.getNumeroTelefono());
            preparedStatement.setString(5, empleado.getCorreoInstitucional());
            preparedStatement.setDate(6, empleado.getFechaNacimiento());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmpleado(Empleado empleado) {
        String sql = "UPDATE Empleados SET numeroDUI = ?, nombrePersona = ?, usuario = ?, numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ? WHERE idEmpleado = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, empleado.getNumeroDUI());
            preparedStatement.setString(2, empleado.getNombrePersona());
            preparedStatement.setString(3, empleado.getUsuario());
            preparedStatement.setString(4, empleado.getNumeroTelefono());
            preparedStatement.setString(5, empleado.getCorreoInstitucional());
            preparedStatement.setDate(6, empleado.getFechaNacimiento());
            preparedStatement.setInt(7, empleado.getIdEmpleado());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmpleado(int idEmpleado) {
        String sql = "DELETE FROM Empleados WHERE idEmpleado = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idEmpleado);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
