package com.itm.cartera_multibanco.dao.impl;

import com.itm.cartera_multibanco.dao.BancoDao;
import com.itm.cartera_multibanco.model.Banco;
import com.itm.cartera_multibanco.util.ConexionBD;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BancoDaoImpl implements BancoDao {

    private final ConexionBD conexionBD;

    public BancoDaoImpl(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<Banco> findAll() {
        List<Banco> bancos = new ArrayList<>();
        String sql = "SELECT id, nombre FROM bancos";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Banco banco = new Banco();
                banco.setId(rs.getInt("id"));
                banco.setNombre(rs.getString("nombre"));
                bancos.add(banco);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error consultando bancos", e);
        }

        return bancos;
    }

    @Override
    public Banco findById(Integer id) {
        String sql = "SELECT id, nombre FROM bancos WHERE id = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Banco banco = new Banco();
                    banco.setId(rs.getInt("id"));
                    banco.setNombre(rs.getString("nombre"));
                    return banco;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando banco por id", e);
        }

        return null;
    }
}