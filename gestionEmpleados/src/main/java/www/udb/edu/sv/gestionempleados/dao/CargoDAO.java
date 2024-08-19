package www.udb.edu.sv.gestionempleados.dao;

import www.udb.edu.sv.gestionempleados.model.Cargo;
import www.udb.edu.sv.gestionempleados.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    private Connection connection;

    public CargoDAO() {
        connection = DBConnection.getConnection();
    }

    public List<Cargo> getAllCargos() {
        List<Cargo> cargos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Cargos";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idCargo = rs.getInt("idCargo");
                String cargo = rs.getString("cargo");
                String descripcionCargo = rs.getString("descripcionCargo");
                boolean jefatura = rs.getBoolean("jefatura");

                Cargo c = new Cargo(idCargo, cargo, descripcionCargo, jefatura);
                cargos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    public void insertCargo(Cargo cargo) {
        try {
            String sql = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cargo.getCargo());
            statement.setString(2, cargo.getDescripcionCargo());
            statement.setBoolean(3, cargo.isJefatura());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCargo(Cargo cargo) {
        try {
            String sql = "UPDATE Cargos SET cargo = ?, descripcionCargo = ?, jefatura = ? WHERE idCargo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cargo.getCargo());
            statement.setString(2, cargo.getDescripcionCargo());
            statement.setBoolean(3, cargo.isJefatura());
            statement.setInt(4, cargo.getIdCargo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCargo(int idCargo) {
        try {
            String sql = "DELETE FROM Cargos WHERE idCargo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCargo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cargo getCargo(int idCargo) {
        Cargo cargo = null;
        try {
            String sql = "SELECT * FROM Cargos WHERE idCargo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCargo);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String nombreCargo = rs.getString("cargo");
                String descripcionCargo = rs.getString("descripcionCargo");
                boolean jefatura = rs.getBoolean("jefatura");
                cargo = new Cargo(idCargo, nombreCargo, descripcionCargo, jefatura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargo;
    }
}
