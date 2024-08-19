package www.udb.edu.sv.gestionempleados.dao;

import www.udb.edu.sv.gestionempleados.model.TipoContratacion;
import www.udb.edu.sv.gestionempleados.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContratacionDAO {

    private Connection connection;

    public TipoContratacionDAO() {
        connection = DBConnection.getConnection();
    }

    public List<TipoContratacion> getAllTiposContratacion() {
        List<TipoContratacion> tiposContratacion = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TipoContratacion";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int idTipoContratacion = rs.getInt("idTipoContratacion");
                String tipoContratacion = rs.getString("tipoContratacion");

                TipoContratacion tipo = new TipoContratacion(idTipoContratacion, tipoContratacion);
                tiposContratacion.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposContratacion;
    }

    public void insertTipoContratacion(TipoContratacion tipoContratacion) {
        try {
            String sql = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tipoContratacion.getTipoContratacion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTipoContratacion(TipoContratacion tipoContratacion) {
        try {
            String sql = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tipoContratacion.getTipoContratacion());
            statement.setInt(2, tipoContratacion.getIdTipoContratacion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTipoContratacion(int idTipoContratacion) {
        try {
            String sql = "DELETE FROM TipoContratacion WHERE idTipoContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idTipoContratacion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TipoContratacion getTipoContratacion(int idTipoContratacion) {
        TipoContratacion tipo = null;
        try {
            String sql = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idTipoContratacion);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String tipoContratacion = rs.getString("tipoContratacion");
                tipo = new TipoContratacion(idTipoContratacion, tipoContratacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipo;
    }
}
