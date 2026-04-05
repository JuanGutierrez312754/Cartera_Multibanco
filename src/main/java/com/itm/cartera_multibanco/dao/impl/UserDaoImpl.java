package com.itm.cartera_multibanco.dao.impl;

import com.itm.cartera_multibanco.dao.UserDao;
import com.itm.cartera_multibanco.model.User;
import com.itm.cartera_multibanco.util.ConexionBD;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final ConexionBD conexionBD;

    public UserDaoImpl(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<User> findAll() {
        List<User> usuarios = new ArrayList<>();
        String sql = "SELECT cedula, nombre_completo, username, clave FROM users";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setCedula(rs.getString("cedula"));
                user.setNombreCompleto(rs.getString("nombre_completo"));
                user.setUsername(rs.getString("username"));
                user.setClave(rs.getString("clave"));
                usuarios.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error consultando usuarios", e);
        }

        return usuarios;
    }

    @Override
    public User findByCedula(String cedula) {
        String sql = "SELECT cedula, nombre_completo, username, clave FROM users WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setCedula(rs.getString("cedula"));
                    user.setNombreCompleto(rs.getString("nombre_completo"));
                    user.setUsername(rs.getString("username"));
                    user.setClave(rs.getString("clave"));
                    return user;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por cédula", e);
        }

        return null;
    }

    @Override
    public boolean existsByCedula(String cedula) {
        String sql = "SELECT 1 FROM users WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error validando cédula", e);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error validando username", e);
        }
    }

    @Override
    public int save(User user) {
        String sql = "INSERT INTO users (cedula, nombre_completo, username, clave) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getCedula());
            ps.setString(2, user.getNombreCompleto());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getClave());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error insertando usuario", e);
        }
    }

    @Override
    public int update(String cedula, User user) {
        String sql = "UPDATE users SET nombre_completo = ?, username = ?, clave = ? WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getNombreCompleto());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getClave());
            ps.setString(4, cedula);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando usuario", e);
        }
    }

    @Override
    public int delete(String cedula) {
        String sql = "DELETE FROM users WHERE cedula = ?";

        try (Connection conn = conexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando usuario", e);
        }
    }
}