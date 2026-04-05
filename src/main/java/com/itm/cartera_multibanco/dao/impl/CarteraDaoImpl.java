package com.itm.cartera_multibanco.dao.impl;

import com.itm.cartera_multibanco.dao.CarteraDao;
import com.itm.cartera_multibanco.model.Cartera;
import com.itm.cartera_multibanco.util.ConexionBD;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CarteraDaoImpl implements CarteraDao {

    private final ConexionBD conexionBD;

    public CarteraDaoImpl(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Cartera findByCedula(String cedula) {
        String sql = "SELECT cedula, saldo FROM cartera WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cartera cartera = new Cartera();
                    cartera.setCedula(rs.getString("cedula"));
                    cartera.setSaldo(rs.getBigDecimal("saldo"));
                    return cartera;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error consultando cartera", e);
        }

        return null;
    }

    @Override
    public int save(Cartera cartera) {
        String sql = "INSERT INTO cartera (cedula, saldo) VALUES (?, ?)";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cartera.getCedula());
            ps.setBigDecimal(2, cartera.getSaldo());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando cartera", e);
        }
    }

    @Override
    public int updateSaldo(String cedula, BigDecimal saldo) {
        String sql = "UPDATE cartera SET saldo = ? WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, saldo);
            ps.setString(2, cedula);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando saldo de cartera", e);
        }
    }
}