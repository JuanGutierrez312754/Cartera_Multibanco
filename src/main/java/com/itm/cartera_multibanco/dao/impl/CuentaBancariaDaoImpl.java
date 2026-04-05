package com.itm.cartera_multibanco.dao.impl;

import com.itm.cartera_multibanco.dao.CuentaBancariaDao;
import com.itm.cartera_multibanco.model.CuentaBancaria;
import com.itm.cartera_multibanco.util.ConexionBD;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CuentaBancariaDaoImpl implements CuentaBancariaDao {

    private final ConexionBD conexionBD;

    public CuentaBancariaDaoImpl(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<CuentaBancaria> findByCedula(String cedula) {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        String sql = "SELECT cuenta, cedula, banco_id, saldo FROM cuentas_bancarias WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CuentaBancaria cuenta = new CuentaBancaria();
                    cuenta.setCuenta(rs.getString("cuenta"));
                    cuenta.setCedula(rs.getString("cedula"));
                    cuenta.setBancoId(rs.getInt("banco_id"));
                    cuenta.setSaldo(rs.getBigDecimal("saldo"));
                    cuentas.add(cuenta);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error consultando cuentas bancarias por cédula", e);
        }

        return cuentas;
    }

    @Override
    public CuentaBancaria findByCuenta(String cuentaNumero) {
        String sql = "SELECT cuenta, cedula, banco_id, saldo FROM cuentas_bancarias WHERE cuenta = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cuentaNumero);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CuentaBancaria cuenta = new CuentaBancaria();
                    cuenta.setCuenta(rs.getString("cuenta"));
                    cuenta.setCedula(rs.getString("cedula"));
                    cuenta.setBancoId(rs.getInt("banco_id"));
                    cuenta.setSaldo(rs.getBigDecimal("saldo"));
                    return cuenta;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando cuenta bancaria", e);
        }

        return null;
    }

    @Override
    public int save(CuentaBancaria cuentaBancaria) {
        String sql = "INSERT INTO cuentas_bancarias (cuenta, cedula, banco_id, saldo) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cuentaBancaria.getCuenta());
            ps.setString(2, cuentaBancaria.getCedula());
            ps.setInt(3, cuentaBancaria.getBancoId());
            ps.setBigDecimal(4, cuentaBancaria.getSaldo());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando cuenta bancaria", e);
        }
    }

    @Override
    public int updateSaldo(String cuenta, BigDecimal saldo) {
        String sql = "UPDATE cuentas_bancarias SET saldo = ? WHERE cuenta = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, saldo);
            ps.setString(2, cuenta);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando saldo de cuenta bancaria", e);
        }
    }

    @Override
    public int delete(String cuenta) {
        String sql = "DELETE FROM cuentas_bancarias WHERE cuenta = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cuenta);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando cuenta bancaria", e);
        }
    }
}