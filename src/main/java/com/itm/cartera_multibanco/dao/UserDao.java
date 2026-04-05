package com.itm.cartera_multibanco.dao;

import com.itm.cartera_multibanco.model.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    boolean existsByUsername(String username);
    int save(User user);
    int update(String cedula, User user);
    int delete(String cedula);
}